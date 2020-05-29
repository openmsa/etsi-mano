package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;

public interface VnfInstantiedComputeJpa extends CrudRepository<VnfInstantiatedCompute, UUID> {

	int countByVnfLcmOpOccsVnfInstanceAndVduId(VnfInstance vnfInstance, UUID vduId);

	List<VnfInstantiatedCompute> findByVnfLcmOpOccsVnfInstanceAndVduId(VnfInstance vnfInstance, UUID id);

	List<VnfInstantiatedCompute> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);

}
