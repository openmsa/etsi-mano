package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;

public interface ResourceDataEntityJpa extends CrudRepository<ResourceHandleEntity, UUID> {
	//
	List<ResourceHandleEntity> findByVnfInstance(VnfInstance vnfInstance);

}
