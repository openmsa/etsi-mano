package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;

public interface VnfLiveInstanceJpa extends CrudRepository<VnfLiveInstance, UUID> {

	int countByVnfInstanceAndVduId(VnfInstance vnfInstance, UUID id);
}
