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
package com.ubiqube.etsi.mano.service.event;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.service.NsBlueprintService;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.graph.NsPlanExecutor;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.service.graph.nfvo.UowNsTaskCreateProvider;
import com.ubiqube.etsi.mano.service.graph.nfvo.UowNsTaskDeleteProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.plan.NsPlanner;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

@Service
public class NfvoActions {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoActions.class);

	private final NsInstanceRepository nsInstanceRepository;
	private final NsdRepository nsdRepository;
	private final VimManager vimManager;
	private final EventManager eventManager;

	private final NsLiveInstanceJpa nsLiveInstanceJpa;
	private final NsPlanExecutor executor;

	private final NsPlanner nsPlanner;
	private NsBlueprintService nsBlueprintService;

	public NfvoActions(final NsInstanceRepository _nsInstanceRepository, final NsdRepository _nsdRepository, final VimManager _vimManager, final EventManager _eventManager, final NsPlanner _nsPlanner, final NsPlanExecutor _executor, final NsLiveInstanceJpa _nsLiveInstanceJpa) {
		super();
		nsInstanceRepository = _nsInstanceRepository;
		nsdRepository = _nsdRepository;
		vimManager = _vimManager;
		eventManager = _eventManager;
		nsPlanner = _nsPlanner;
		executor = _executor;
		nsLiveInstanceJpa = _nsLiveInstanceJpa;
	}

	public void nsTerminate(@Nonnull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-NT");
		final NsBlueprint lcmOpOccs = nsBlueprintService.findById(lcmOpOccsId);
		final NsdInstance nsInstance = nsInstanceRepository.get(lcmOpOccs.getNsInstance().getId());
		try {
			nsTerminateInner(lcmOpOccs, nsInstance);
		} catch (final RuntimeException e) {
			LOG.error("NS Instantiate fail.", e);
			lcmOpOccs.setOperationStatus(OperationStatusType.FAILED);
			nsInstanceRepository.save(nsInstance);
			lcmOpOccs.setError(new FailureDetails(500L, e.getMessage()));
			lcmOpOccs.setStateEnteredTime(new Date());
			nsBlueprintService.save(lcmOpOccs);
			eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
		}
	}

	private void nsTerminateInner(final NsBlueprint blueprint, final NsdInstance nsInstance) {
		// XXX This is not the correct way/
		final VimConnectionInformation vimInfo = electVim(null, null);

		final NsdPackage nsdInfo = nsdRepository.get(nsInstance.getNsdInfo().getId());
		final NsConnections nsConn = new NsConnections();
		nsPlanner.doPlan(nsdInfo, blueprint, null, nsConn.getConnections());
		final NsBlueprint localPlan = nsBlueprintService.save(blueprint);
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		final ListenableGraph<UnitOfWork<NsTask, NsParameters>, ConnectivityEdge<UnitOfWork<NsTask, NsParameters>>> executionPlane = nsPlanner.convertToExecution(localPlan, ChangeType.REMOVED);
		GraphTools.exportGraph(executionPlane, nsdInfo.getId(), nsInstance, "delete", nsdRepository);

		final NsParameters params = new NsParameters(vim);
		final ExecutionResults<UnitOfWork<NsTask, NsParameters>, String> results = executor.execDelete(executionPlane, () -> new UowNsTaskDeleteProvider(params));
		setResultLcmInstance(localPlan, results);
		LOG.info("VNF instance {} / LCM {} Finished.", nsInstance.getId(), blueprint.getId());
	}

	public void nsInstantiate(@Nonnull final UUID blueprintId) {
		Thread.currentThread().setName(blueprintId + "-NI");
		final NsBlueprint nsBlueprint = nsBlueprintService.findById(blueprintId);
		final NsdInstance nsInstance = nsInstanceRepository.get(nsBlueprint.getNsInstance().getId());

		try {
			nsInstantiateInner(nsBlueprint, nsInstance);
		} catch (final RuntimeException e) {
			LOG.error("NS Instantiate fail.", e);
			// We can't save here, we must do an atomic update.
			nsBlueprint.setOperationStatus(OperationStatusType.FAILED);
			nsBlueprint.setError(new FailureDetails(500L, e.getMessage()));
			nsBlueprint.setStateEnteredTime(new Date());
			nsBlueprintService.save(nsBlueprint);
			eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
		}
	}

	public void nsInstantiateInner(@Nonnull final NsBlueprint blueprint, final NsdInstance nsInstance) {
		final UUID nsdId = nsInstance.getNsdInfo().getId();
		final NsdPackage nsdInfo = nsdRepository.get(nsdId);
		// Make plan in lcmOpOccs
		final NsConnections nsConn = new NsConnections();
		nsPlanner.doPlan(nsdInfo, blueprint, null, nsConn.getConnections());
		final NsBlueprint localBlueprint = nsBlueprintService.save(blueprint);
		//
		final VimConnectionInformation vimInfo = electVim(null, null);
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		// Create Ns.
		final Map<String, String> userData = nsdInfo.getUserDefinedData();
		// XXX elect vim?
		final Map<String, String> pubNet = vim.getPublicNetworks(vimInfo);
		final NsParameters params = new NsParameters(vim);
		final ListenableGraph<UnitOfWork<NsTask, NsParameters>, ConnectivityEdge<UnitOfWork<NsTask, NsParameters>>> executionPlane = nsPlanner.convertToExecution(localBlueprint, ChangeType.ADDED);
		final ExecutionResults<UnitOfWork<NsTask, NsParameters>, String> results = executor.execCreate(executionPlane, () -> new UowNsTaskCreateProvider(params));
		setLiveStatus(localBlueprint, results);
		setResultLcmInstance(localBlueprint, results);
		LOG.debug("Done, Saving ...");
		// XXX Send COMPLETED event.
		LOG.info("NSD instance {} / LCM {} Finished.", nsdId, localBlueprint.getId());
		eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
	}

	private static void setResultLcmInstance(@NotNull final NsBlueprint blueprint, final ExecutionResults<UnitOfWork<NsTask, NsParameters>, String> results) {
		if (results.getErrored().isEmpty()) {
			blueprint.setOperationStatus(OperationStatusType.COMPLETED);
		} else {
			blueprint.setOperationStatus(OperationStatusType.FAILED);
		}
		blueprint.setStateEnteredTime(new Date());
	}

	private void setLiveStatus(final NsBlueprint blueprint, final ExecutionResults<UnitOfWork<NsTask, NsParameters>, String> results) {
		results.getSuccess().forEach(x -> {
			final NsTask rhe = x.getId().getTaskEntity();
			final ChangeType ct = rhe.getChangeType();
			if (ct == ChangeType.ADDED) {
				if (null != rhe.getId()) {
					final NsLiveInstance vli = new NsLiveInstance(rhe.getVimResourceId(), rhe, blueprint, blueprint.getNsInstance());
					nsLiveInstanceJpa.save(vli);
				} else {
					LOG.warn("Could not store: {}", x.getId().getName());
				}
			} else if (ct == ChangeType.REMOVED) {
				LOG.info("Removing {}", rhe.getId());
				final NsLiveInstance vli = nsLiveInstanceJpa.findByNsBlueprintId(rhe.getVimResourceId());
				nsLiveInstanceJpa.deleteById(vli.getId());
			}
		});
		LOG.info("Saving NS LCM.");
		nsBlueprintService.save(blueprint);
	}

	private VimConnectionInformation electVim(final String vimId, final Set<GrantInformationExt> set) {
		// XXX: Do some real elections.
		final Set<VimConnectionInformation> vims;
		if (null != vimId) {
			LOG.debug("Getting MSA 2.x VIM");
			vims = vimManager.getVimByType("MSA_20");
		} else {
			LOG.debug("Getting OS v3 VIM");
			vims = vimManager.getVimByType("OPENSTACK_V3");
		}
		if (vims.isEmpty()) {
			throw new GenericException("Couldn't find a VIM.");
		}
		return vims.iterator().next();
	}

}
