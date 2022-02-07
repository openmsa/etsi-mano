/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.service.event;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.GrantsResponseJpa;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.AbstractGrantAction;
import com.ubiqube.etsi.mano.service.event.elect.VimElection;
import com.ubiqube.etsi.mano.service.vim.NetworkObject;
import com.ubiqube.etsi.mano.service.vim.ResourceQuota;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class GrantAction extends AbstractGrantAction {

	private static final Logger LOG = LoggerFactory.getLogger(GrantAction.class);

	private final GrantsResponseJpa grantJpa;

	private final VimManager vimManager;

	private final VnfPackageService vnfPackageService;

	private final Random rnd;

	private final NsLiveInstanceJpa nsLiveInstanceJpa;

	public GrantAction(final GrantsResponseJpa grantJpa, final VimManager vimManager, final VimElection vimElection, final VnfPackageService vnfPackageService,
			final NsLiveInstanceJpa nsLiveInstanceJpa) {
		super(grantJpa, vimManager, vimElection);
		this.grantJpa = grantJpa;
		this.vimManager = vimManager;
		this.vnfPackageService = vnfPackageService;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
		this.rnd = new Random();
	}

	@Override
	protected Set<VnfCompute> getVnfCompute(final UUID objectId) {
		final GrantResponse grant = grantJpa.findById(objectId).orElseThrow();
		final VnfPackage pkg = vnfPackageService.findByVnfdId(UUID.fromString(grant.getVnfdId()));
		return pkg.getVnfCompute();
	}

	@Override
	protected Set<VnfStorage> getVnfStorage(final UUID objectId) {
		final GrantResponse grant = grantJpa.findById(objectId).orElseThrow();
		final VnfPackage pkg = vnfPackageService.findByVnfdId(UUID.fromString(grant.getVnfdId()));
		return pkg.getVnfStorage();
	}

	private VimConnectionInformation electVim(final String vnfPackageVimId, final GrantResponse grantResponse, final VnfPackage vnfPackage) {
		final Set<VimConnectionInformation> vimConns = grantResponse.getVimConnections();
		String vimId;
		if (null != vimConns && !vimConns.isEmpty()) {
			LOG.info("Selecting vim via Given one.");
			vimId = vimConns.iterator().next().getVimId();
			return vimManager.findVimByVimId(vimId);
		}
		// XXX: Do some real elections.
		final Set<VimConnectionInformation> vims;
		if (null != vnfPackageVimId) {
			LOG.debug("Getting MSA 2.x VIM");
			vims = vimManager.getVimByType("MSA_20");
			return vims.iterator().next();
		}
		LOG.debug("Getting OS v3 VIM");
		vims = vimManager.getVimByType("OPENSTACK_V3");
		if (vims.isEmpty()) {
			throw new GenericException("Couldn't find a VIM.");
		}
		return doNormalElection(vnfPackage, grantResponse, vims);
	}

	private VimConnectionInformation doNormalElection(final VnfPackage vnfPackage, final GrantResponse grantResponse, final Set<VimConnectionInformation> vims) {
		final QuotaNeeded needed = summarizeResources(grantResponse, vnfPackage);
		final List<VimConnectionInformation> vimsSelected = new ArrayList<>();
		vims.parallelStream().forEach(x -> {
			final Vim vim = vimManager.getVimById(x.getId());
			final ResourceQuota quota = vim.getQuota(x);
			if (needed.getRam() > quota.getRamFree()) {
				LOG.debug("Removing vim {}: RAM needed: {} free: {}", x.getVimId(), needed.getRam(), quota.getRamFree());
				return;
			}
			if (needed.getVcpu() > quota.getVcpuFree()) {
				LOG.debug("Removing vim {}: Vcpu needed: {} free: {}", x.getVimId(), needed.getVcpu(), quota.getVcpuFree());
				return;
			}
			vimsSelected.add(x);
		});
		if (vimsSelected.isEmpty()) {
			throw new GenericException("No Vim found, after quota filtering.");
		}
		return vimsSelected.get(rnd.nextInt(vimsSelected.size()));
	}

	private QuotaNeeded summarizeResources(final GrantResponse grantResponse, final VnfPackage vnfPackage) {
		final Set<GrantInformationExt> adds = grantResponse.getAddResources();
		int disk = 0;
		int vcpu = 0;
		int ram = 0;
		for (final GrantInformationExt grantInformationExt : adds) {
			if (grantInformationExt.getType() == ResourceTypeEnum.COMPUTE) {
				final VnfCompute compute = findCompute(vnfPackage, grantInformationExt.getVduId());
				disk += compute.getDiskSize();
				vcpu += compute.getVirtualCpu().getNumVirtualCpu();
				ram += compute.getVirtualMemory().getVirtualMemSize();
			} else if (grantInformationExt.getType() == ResourceTypeEnum.STORAGE) {
				// Cinder.
			}
		}
		return new QuotaNeeded(disk, vcpu, ram);
	}

	private static VnfCompute findCompute(final VnfPackage vnfPackage, final UUID vduId) {
		return vnfPackage.getVnfCompute().stream()
				.filter(x -> x.getId().compareTo(vduId) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("VduId not found " + vduId));
	}

	@Override
	protected InputStream findImage(final SoftwareImage softwareImage, final String vnfdId) {
		final Path path = vnfPackageService.getPathByVnfdId(UUID.fromString(vnfdId));
		try {
			final FileSystemManager fsManager = VFS.getManager();
			final FileObject fo = fsManager.resolveFile("zip:" + path.toString());
			final FileObject img = fo.getChild(softwareImage.getImagePath());
			final FileContent fc = img.getContent();
			return fc.getInputStream();
		} catch (final FileSystemException e) {
			throw new GenericException(e);
		}
	}

	@Override
	protected void getUnmanagedNetworks(final GrantResponse grants, final Vim vim, final VimConnectionInformation vimConnectionInformation) {
		final String vnfInstanceId = grants.getVnfInstanceId();
		final NsLiveInstance res = nsLiveInstanceJpa.findByResourceId(vnfInstanceId);
		if (null == res) {
			LOG.warn("No NS instance found " + vnfInstanceId);
			return;
		}
		final NsVnfTask inst = (NsVnfTask) res.getNsTask();
		final List<ListKeyPair> vl = inst.getNsPackageVnfPackage().getVirtualLinks().stream().filter(x -> x.getValue() != null).toList();
		final List<NetworkObject> vlList = vim.network(vimConnectionInformation)
				.searchByName(vl.stream()
						.map(ListKeyPair::getValue)
						.filter(Objects::nonNull)
						.toList());
		vlList.forEach(x -> grants.getExtVirtualLinks().add(createVl(x, vl, vimConnectionInformation)));
	}

	private static ExtVirtualLinkDataEntity createVl(final NetworkObject x, final List<ListKeyPair> vl, final VimConnectionInformation vimConnectionInformation) {
		final ExtVirtualLinkDataEntity ret = new ExtVirtualLinkDataEntity();
		ret.setExtVirtualLinkId(mapToVl(x, vl));
		ret.setResourceId(x.name());
		ret.setResourceProviderId(vimConnectionInformation.getVimType());
		ret.setVimConnectionId(vimConnectionInformation.getVimId());
		return ret;
	}

	private static String mapToVl(final NetworkObject no, final List<ListKeyPair> vl) {
		final ListKeyPair kp = vl.stream().filter(x -> x.getValue().equals(no.id())).findFirst().orElseThrow();
		if (kp.getIdx() == 0) {
			return "virtual_link";
		}
		return "virtual_link_" + kp.getIdx();
	}

	private static List<ListKeyPair> gatherExtVl(final VnfPackage vnfPkg) {
		return vnfPkg.getVirtualLinks().stream().filter(x -> x.getValue() != null).toList();
	}

}
