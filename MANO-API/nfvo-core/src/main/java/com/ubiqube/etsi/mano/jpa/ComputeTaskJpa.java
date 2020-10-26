package com.ubiqube.etsi.mano.jpa;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;

public interface ComputeTaskJpa extends TaskBaseJpa<ComputeTask> {

	int countByOperationStatusAndVnfInstance(OperationStatusType operationStatusType, VnfInstance vnfInstance);
}
