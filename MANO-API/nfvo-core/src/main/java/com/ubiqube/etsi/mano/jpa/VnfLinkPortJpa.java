package com.ubiqube.etsi.mano.jpa;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public interface VnfLinkPortJpa extends CrudRepository<VnfLinkPort, UUID> {

	Set<VnfLinkPort> findByVnfPackage(VnfPackage vnfPackage);

}
