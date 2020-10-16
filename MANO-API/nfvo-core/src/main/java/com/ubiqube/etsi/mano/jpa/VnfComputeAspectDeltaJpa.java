package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;

public interface VnfComputeAspectDeltaJpa extends CrudRepository<VnfComputeAspectDelta, UUID> {

	List<VnfComputeAspectDelta> findByVnfComputeAndAspectName(VnfCompute vnfCompute, String aspectName);
}
