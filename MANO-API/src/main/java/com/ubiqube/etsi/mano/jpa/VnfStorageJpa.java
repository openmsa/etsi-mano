package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfStorage;

public interface VnfStorageJpa extends CrudRepository<VnfStorage, UUID> {

}
