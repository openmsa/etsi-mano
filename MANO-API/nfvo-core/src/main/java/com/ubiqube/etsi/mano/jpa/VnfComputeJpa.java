package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;

public interface VnfComputeJpa extends CrudRepository<VnfCompute, UUID> {

	VduInstantiationLevel findByIdAndInstantiationLevelLevelName(UUID x, String scaleInfoName);

}
