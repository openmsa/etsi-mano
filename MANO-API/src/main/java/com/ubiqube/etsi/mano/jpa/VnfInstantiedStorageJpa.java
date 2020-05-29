package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;

public interface VnfInstantiedStorageJpa extends CrudRepository<VnfInstantiatedStorage, UUID> {

	List<VnfInstantiatedStorage> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);

}
