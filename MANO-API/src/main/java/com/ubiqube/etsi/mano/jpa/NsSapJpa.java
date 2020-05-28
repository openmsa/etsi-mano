package com.ubiqube.etsi.mano.jpa;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;

public interface NsSapJpa extends CrudRepository<NsSap, UUID> {

	Set<NsSap> findByNsdPackage(NsdPackage nsdInfo);
}
