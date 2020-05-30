package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;

public interface VnfInstantiedComputeJpa extends VnfInstantiedBaseJpa<VnfInstantiatedCompute> {

	int countByVnfLcmOpOccsVnfInstanceAndVduId(VnfInstance vnfInstance, UUID vduId);

	List<VnfInstantiatedCompute> findByVnfLcmOpOccsVnfInstanceAndVduId(VnfInstance vnfInstance, UUID id);

	List<VnfInstantiatedCompute> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);

}
