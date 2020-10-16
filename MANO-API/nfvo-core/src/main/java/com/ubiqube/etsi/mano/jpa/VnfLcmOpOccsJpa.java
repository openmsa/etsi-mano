package com.ubiqube.etsi.mano.jpa;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;

public interface VnfLcmOpOccsJpa extends CrudRepository<VnfLcmOpOccs, UUID> {

	Set<VnfLcmOpOccs> findByVnfInstance(VnfInstance vnfInstance);
}
