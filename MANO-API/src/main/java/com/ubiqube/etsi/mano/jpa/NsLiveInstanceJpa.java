package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;

public interface NsLiveInstanceJpa extends CrudRepository<NsLiveInstance, UUID> {

	VnfLiveInstance findByNsInstantiatedBaseResourceId(String resourceId);

}
