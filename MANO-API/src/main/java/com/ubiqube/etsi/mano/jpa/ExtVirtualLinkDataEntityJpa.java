package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;

public interface ExtVirtualLinkDataEntityJpa extends CrudRepository<ExtVirtualLinkDataEntity, UUID> {
	List<ExtVirtualLinkDataEntity> findByVnfInstance(VnfInstance vnfInstance);

}
