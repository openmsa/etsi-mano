package com.ubiqube.etsi.mano.jpa;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;

public interface VnfLiveInstanceJpa extends CrudRepository<VnfLiveInstance, UUID> {

	int countByVnfInstanceAndResourceId(VnfInstance vnfInstance, UUID id);

	Set<VnfLiveInstance> findByVnfInstanceAndResourceId(VnfInstance vnfInstance, UUID id);

	VnfLiveInstance findByVnfInstantiatedBaseId(UUID rhe);
}
