package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;

public interface VnfExtCpJpa extends CrudRepository<VnfExtCp, UUID> {

	int countByVnfInstanceAndVduId(VnfInstance vnfInstance, UUID id);

}
