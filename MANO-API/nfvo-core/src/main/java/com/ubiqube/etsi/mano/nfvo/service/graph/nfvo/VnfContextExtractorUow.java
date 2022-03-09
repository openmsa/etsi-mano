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
package com.ubiqube.etsi.mano.nfvo.service.graph.nfvo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.VnfContextExtractorTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.mec.VnfContextExtractorNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfCreateNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfPortNode;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;

/**
 * Extract VNF instance information and put them inside the WF context.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfContextExtractorUow extends AbstractUnitOfWork<VnfContextExtractorTask> {
	Pattern pVl = Pattern.compile("virtual_link_(?<idx>\\d+)");
	private final VnfmInterface vnfm;
	private final NsdPackage pack;

	public VnfContextExtractorUow(final VirtualTask<VnfContextExtractorTask> task, final VnfmInterface vnfm, final NsdPackage pack) {
		super(task, VnfContextExtractorNode.class);
		this.vnfm = vnfm;
		this.pack = pack;
	}

	@Override
	public String execute(final Context context) {
		final VnfContextExtractorTask task = getTask().getParameters();
		final String vnfInstanceId = context.get(VnfCreateNode.class, task.getToscaName());
		final VnfInstance inst = vnfm.getVnfInstance(task.getServer(), vnfInstanceId);
		inst.getExtCpInfo().forEach(x -> {
			final NsdPackageVnfPackage vnfd = findVnfd(inst.getVnfdId());
			final int idx = toscaNameToVlId(x.getCpdId());
			// virtual_link(_x) -> forwardName
			final ListKeyPair vl = vnfd.getVirtualLinks().stream().filter(y -> y.getIdx() == idx).findFirst().orElseThrow(() -> new GenericException("unable to find index " + idx));
			// forwad to VL
			final String extVl = forwardToVl(vl.getValue());
			context.add(VnfPortNode.class, x.getCpdId(), extVl);
		});
		return null;
	}

	private String forwardToVl(final String net) {
		return pack.getVnffgs().stream()
				.flatMap(x -> x.getNfpd().stream())
				.flatMap(x -> x.getInstancces().stream())
				.flatMap(x -> x.getPairs().stream())
				.map(x -> {
					if (x.getEgress().equals(net)) {
						return x.getEgressVl();
					}
					if (x.getIngress().equals(net)) {
						return x.getIngressVl();
					}
					return null;
				})
				.filter(Objects::nonNull)
				.findFirst()
				.orElseThrow(() -> new GenericException("Unable to find VL " + net));
	}

	@SuppressWarnings("boxing")
	private int toscaNameToVlId(final String cpdId) {
		if ("virtual_link".equals(cpdId)) {
			return 0;
		}
		final Matcher m = pVl.matcher(cpdId);
		if (!m.matches()) {
			throw new GenericException("Unable to match 'virtual_link_' in " + cpdId);
		}
		return Integer.valueOf(m.group("idx"));
	}

	private NsdPackageVnfPackage findVnfd(final String vnfdId) {
		return pack.getVnfPkgIds().stream()
				.filter(x -> x.getVnfPackage().getVnfdId().equals(vnfdId)).findFirst()
				.orElseThrow(() -> new GenericException("Unable to find vnfd id " + vnfdId));
	}

	@Override
	public String rollback(final Context context) {
		// Nothing.
		return null;
	}

}
