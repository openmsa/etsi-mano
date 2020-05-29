package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;

public interface VnfInstantiedExtCpJpa extends CrudRepository<com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp, UUID> {

	List<VnfInstantiatedExtCp> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);
}
