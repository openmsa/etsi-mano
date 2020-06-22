package com.ubiqube.etsi.mano.service.event;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.graph.NsExecutionPlanner;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUnitOfWork;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

@Service
public class NfvoActions {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoActions.class);

	private final NsInstanceRepository nsInstanceRepository;
	private final NsLcmOpOccsService nsLcmOpOccsService;
	private final NsdRepository nsdRepository;
	private final VimManager vimManager;
	private final EventManager eventManager;

	private final NsLiveInstanceJpa nsLiveInstanceJpa;
	private final PlanExecutor executor;

	private final NsExecutionPlanner executionPlanner;

	public NfvoActions(final NsInstanceRepository _nsInstanceRepository, final NsdRepository _nsdRepository, final VimManager _vimManager, final EventManager _eventManager, final NsExecutionPlanner _executionPlanner, final PlanExecutor _executor, final NsLcmOpOccsService _nsLcmOpOccsService, final NsLiveInstanceJpa _nsLiveInstanceJpa) {
		super();
		nsInstanceRepository = _nsInstanceRepository;
		nsdRepository = _nsdRepository;
		vimManager = _vimManager;
		eventManager = _eventManager;
		executionPlanner = _executionPlanner;
		executor = _executor;
		nsLcmOpOccsService = _nsLcmOpOccsService;
		nsLiveInstanceJpa = _nsLiveInstanceJpa;
	}

	public void nsTerminate(@Nonnull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-NT");
		final NsLcmOpOccs lcmOpOccs = nsLcmOpOccsService.findById(lcmOpOccsId);
		final NsdInstance nsInstance = nsInstanceRepository.get(lcmOpOccs.getNsInstance().getId());
		try {
			nsTerminateInner(lcmOpOccs, nsInstance);
		} catch (final RuntimeException e) {
			LOG.error("NS Instantiate fail.", e);
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			nsInstanceRepository.save(nsInstance);
			lcmOpOccs.setError(new FailureDetails(500L, e.getMessage()));
			lcmOpOccs.setStateEnteredTime(new Date());
			nsLcmOpOccsService.save(lcmOpOccs);
			eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
		}
	}

	private void nsTerminateInner(final NsLcmOpOccs lcmOpOccs, final NsdInstance nsInstance) {
		// XXX This is not the correct way/
		final VimConnectionInformation vimInfo = electVim(null, null);

		final NsdPackage nsdInfo = nsdRepository.get(nsInstance.getNsdInfo().getId());
		executionPlanner.terminateNsPlan(lcmOpOccs, nsdInfo);

		final Vim vim = vimManager.getVimById(vimInfo.getId());

		ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> plan = executionPlanner.plan(lcmOpOccs, nsInstance);
		plan = GraphTools.revert(plan);

		GraphTools.exportGraph(plan, nsdInfo.getId(), nsInstance, "delete", nsdRepository);

		final ExecutionResults<NsUnitOfWork, String> results = executor.execDeleteNs(plan, vimInfo, vim);
		setResultLcmInstance(lcmOpOccs, nsInstance.getId(), results, InstantiationStateEnum.NOT_INSTANTIATED);
		LOG.info("VNF instance {} / LCM {} Finished.", nsInstance.getId(), lcmOpOccs.getId());
	}

	public void nsInstantiate(@Nonnull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-NI");
		final NsLcmOpOccs lcmOpOccs = nsLcmOpOccsService.findById(lcmOpOccsId);
		final NsdInstance nsInstance = nsInstanceRepository.get(lcmOpOccs.getNsInstance().getId());

		try {
			nsInstantiateInner(lcmOpOccs, nsInstance);
		} catch (final RuntimeException e) {
			LOG.error("NS Instantiate fail.", e);
			// We can't save here, we must do an atomic update.
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			lcmOpOccs.setError(new FailureDetails(500L, e.getMessage()));
			lcmOpOccs.setStateEnteredTime(new Date());
			nsLcmOpOccsService.save(lcmOpOccs);
			eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
		}
	}

	public void nsInstantiateInner(@Nonnull final NsLcmOpOccs lcmOpOccs, final NsdInstance nsInstance) {
		final UUID nsdId = nsInstance.getNsdInfo().getId();
		final NsdPackage nsdInfo = nsdRepository.get(nsdId);
		// Make plan in lcmOpOccs
		executionPlanner.makePrePlan(nsInstance, nsdInfo, lcmOpOccs);
		final NsLcmOpOccs localLcmOpOccs = nsLcmOpOccsService.save(lcmOpOccs);
		final VimConnectionInformation vimInfo = electVim(null, null);
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		// Create Ns.
		final Map<String, String> userData = nsdInfo.getUserDefinedData();
		// XXX elect vim?
		final Map<String, String> pubNet = vim.getPublicNetworks(vimInfo);
		final ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> plan = executionPlanner.plan(localLcmOpOccs, nsInstance);
		GraphTools.exportGraph(plan, nsdId, nsInstance, "create", nsdRepository);
		final ExecutionResults<NsUnitOfWork, String> results = executor.execCreateNs(plan, vimInfo, vim, pubNet);
		LOG.debug("Done, Saving ...");
		setResultLcmInstance(localLcmOpOccs, nsInstance.getId(), results, InstantiationStateEnum.INSTANTIATED);
		// XXX Send COMPLETED event.
		LOG.info("NSD instance {} / LCM {} Finished.", nsdId, localLcmOpOccs.getId());
		eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
	}

	private void setResultLcmInstance(final NsLcmOpOccs lcmOpOccs, @Nonnull final UUID nsInstanceId, final ExecutionResults<NsUnitOfWork, String> results, final InstantiationStateEnum eventType) {
		final NsdInstance nsdInstance = nsInstanceRepository.get(nsInstanceId);
		if (results.getErrored().isEmpty()) {
			lcmOpOccs.setOperationState(LcmOperationStateType.COMPLETED);
			nsdInstance.setNsState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.INSTANTIATED : InstantiationStateEnum.NOT_INSTANTIATED);
			LOG.info("NS result COMPLETED");
		} else {
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			nsdInstance.setNsState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.NOT_INSTANTIATED : InstantiationStateEnum.INSTANTIATED);
			LOG.info("NS result FAILED");
		}
		results.getSuccess().forEach(x -> {
			final NsInstantiatedBase rhe = x.getId().getResourceHandleEntity();
			final NsdChangeType ct = rhe.getChangeType();
			if (ct == NsdChangeType.ADD) {
				final String il = rhe.getInstantiationLevel();
				if (null != rhe.getId()) {
					final NsLiveInstance vli = new NsLiveInstance(rhe.getResourceId(), il, rhe, lcmOpOccs, lcmOpOccs.getNsInstance());
					nsLiveInstanceJpa.save(vli);
				} else {
					LOG.warn("Could not store: {}", x.getId().getName());
				}
			} else if (ct == NsdChangeType.REMOVE) {
				LOG.info("Removing {}", rhe.getId());
				final NsLiveInstance vli = nsLiveInstanceJpa.findByNsInstantiatedBaseResourceId(rhe.getResourceId());
				nsLiveInstanceJpa.deleteById(vli.getId());
			}
		});
		LOG.info("Saving NS Instance.");
		nsInstanceRepository.save(nsdInstance);
		LOG.info("Saving NS LCM.");
		nsLcmOpOccsService.save(lcmOpOccs);
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
