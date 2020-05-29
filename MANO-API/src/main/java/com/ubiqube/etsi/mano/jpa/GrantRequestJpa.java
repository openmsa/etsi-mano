package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;

public interface GrantRequestJpa extends CrudRepository<GrantsRequest, UUID> {

	void deleteByVnfLcmOpOccs(VnfLcmOpOccs lcmOpOccs);
}
