package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public interface VnfInstantiationLevelsJpa extends CrudRepository<VnfInstantiationLevels, UUID> {

	List<VnfInstantiationLevels> findByVnfPackageAndLevelName(VnfPackage vnfPackage, String levelName);
}
