package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.BlueprintJpa;
import com.ubiqube.etsi.mano.jpa.ComputeTaskJpa;
import com.ubiqube.etsi.mano.jpa.NetworkTaskJpa;

@Service
public class BlueprintService {
	private final BlueprintJpa blueprintJpa;
	private final NetworkTaskJpa networkTaskJpa;
	private final ComputeTaskJpa computeTaskJpa;

	public BlueprintService(final BlueprintJpa _blueprintJpa, final NetworkTaskJpa _networkTaskJpa, final ComputeTaskJpa _computeTaskJpa) {
		blueprintJpa = _blueprintJpa;
		networkTaskJpa = _networkTaskJpa;
		computeTaskJpa = _computeTaskJpa;
	}

	public int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x) {
		return networkTaskJpa.findByLiveInstanceOfVnfInstance(vnfInstance).size();
	}

	public int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute x) {
		return computeTaskJpa.countByOperationStatusAndVnfInstance(OperationStatusType.SUCCESS, vnfInstance);
	}

	public Blueprint save(final Blueprint plan) {
		return blueprintJpa.save(plan);
	}

	public Blueprint updateState(final Blueprint plan, final OperationStatusType processing) {
		plan.setOperationStatus(processing);
		return save(plan);
	}

	public Blueprint findById(final UUID blueprintId) {
		return blueprintJpa.findById(blueprintId).orElseThrow(() -> new GenericException("Blueprint not found " + blueprintId));
	}

}
