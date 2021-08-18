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
package com.ubiqube.etsi.mano.nfvo.service;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.StreamSupport;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.vrqan.VrQan;
import com.ubiqube.etsi.mano.nfvo.jpa.VrQanJpa;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.vim.ResourceQuota;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VrQanService {

	private static final Logger LOG = LoggerFactory.getLogger(VrQanService.class);

	private final VimManager vimManager;
	private final ExecutorService es = Executors.newFixedThreadPool(5);
	private final VrQanJpa vrQanJpa;
	private final EventManager em;

	public VrQanService(final VimManager vimManager, final VrQanJpa vrQanJpa, final EventManager em) {
		super();
		this.vimManager = vimManager;
		this.vrQanJpa = vrQanJpa;
		this.em = em;
	}

	@Scheduled(fixedDelay = 1000_000)
	public void run() {
		final Iterable<VimConnectionInformation> l = vimManager.findAllVimconnections();
		StreamSupport.stream(l.spliterator(), false).forEach(x -> {
			es.submit(() -> {
				try {
					final Vim vim = vimManager.getVimById(x.getId());
					final ResourceQuota pr = vim.getQuota(x);
					final Optional<VrQan> ovrqan = vrQanJpa.findByVimId(x.getId());
					final VrQan vrqan = ovrqan.orElseGet(() -> {
						final VrQan vq = new VrQan(x.getId());
						return vrQanJpa.save(vq);
					});
					final VrQan diff = compare(pr, vrqan);
					if (diff.haveValue()) {
						LOG.info("Send notification for vim: {} with diff {}", x.getId(), diff);
						copy(pr, vrqan);
						vrQanJpa.save(vrqan);
						em.sendNotification(NotificationEvent.VRQAN, x.getId());
					}
				} catch (final RuntimeException e) {
					LOG.error("", e);
				}
			});
		});
	}

	@PreDestroy
	public void onClose() {
		es.shutdown();
	}

	private static void copy(final ResourceQuota pr, final VrQan vrqan) {
		vrqan.setFloatingIpUsed(pr.getFloatingIpUsed());
		vrqan.setFloatingIpMax(pr.getFloatingIpMax());
		vrqan.setFloatingFree(pr.getFloatingFree());
		vrqan.setSecurityGroupsUsed(pr.getSecurityGroupsUsed());
		vrqan.setSecurityGroupsMax(pr.getSecurityGroupsMax());
		vrqan.setSecurityGroupsFree(pr.getSecurityGroupsFree());
		vrqan.setRamUsed(pr.getRamUsed());
		vrqan.setRamMax(pr.getRamMax());
		vrqan.setRamFree(pr.getRamFree());
		vrqan.setKeyPairsUsed(pr.getKeyPairsUsed());
		vrqan.setKeyPairsMax(pr.getKeyPairsMax());
		vrqan.setKeyPairsFree(pr.getKeyPairsFree());
		vrqan.setInstanceUsed(pr.getInstanceUsed());
		vrqan.setInstanceMax(pr.getInstanceMax());
		vrqan.setInstanceFree(pr.getInstanceFree());
		vrqan.setVcpuUsed(pr.getVcpuUsed());
		vrqan.setVcpuMax(pr.getVcpuMax());
		vrqan.setVcpuFree(pr.getVcpuFree());
	}

	private static VrQan compare(final ResourceQuota pr, final VrQan vrqan) {
		final VrQan diff = new VrQan();
		diff.setFloatingIpUsed(compare(vrqan.getFloatingIpUsed(), pr.getFloatingIpUsed()));
		diff.setFloatingIpMax(compare(vrqan.getFloatingIpMax(), pr.getFloatingIpMax()));
		diff.setFloatingFree(compare(vrqan.getFloatingFree(), pr.getFloatingFree()));
		diff.setSecurityGroupsUsed(compare(vrqan.getSecurityGroupsUsed(), pr.getSecurityGroupsUsed()));
		diff.setSecurityGroupsMax(compare(vrqan.getSecurityGroupsMax(), pr.getSecurityGroupsMax()));
		diff.setSecurityGroupsFree(compare(vrqan.getSecurityGroupsFree(), pr.getSecurityGroupsFree()));
		diff.setRamUsed(compare(vrqan.getRamUsed(), pr.getRamUsed()));
		diff.setRamMax(compare(vrqan.getRamMax(), pr.getRamMax()));
		diff.setRamFree(compare(vrqan.getRamFree(), pr.getRamFree()));
		diff.setKeyPairsUsed(compare(vrqan.getKeyPairsUsed(), pr.getKeyPairsUsed()));
		diff.setKeyPairsMax(compare(vrqan.getKeyPairsMax(), pr.getKeyPairsMax()));
		diff.setKeyPairsFree(compare(vrqan.getKeyPairsFree(), pr.getKeyPairsFree()));
		diff.setInstanceUsed(compare(vrqan.getInstanceUsed(), pr.getInstanceUsed()));
		diff.setInstanceMax(compare(vrqan.getInstanceMax(), pr.getInstanceMax()));
		diff.setInstanceFree(compare(vrqan.getInstanceFree(), pr.getInstanceFree()));
		diff.setVcpuUsed(compare(vrqan.getVcpuUsed(), pr.getVcpuUsed()));
		diff.setVcpuMax(compare(vrqan.getVcpuMax(), pr.getVcpuMax()));
		diff.setVcpuFree(compare(vrqan.getVcpuFree(), pr.getVcpuFree()));
		return diff;
	}

	private static long compare(final long old, final long ne) {
		return old - ne;
	}

	private static int compare(final int old, final int ne) {
		return old - ne;
	}
}
