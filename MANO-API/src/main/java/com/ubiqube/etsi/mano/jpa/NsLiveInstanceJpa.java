package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;

public interface NsLiveInstanceJpa extends CrudRepository<NsLiveInstance, UUID> {

	NsLiveInstance findByNsInstantiatedBaseResourceId(String resourceId);

}
