package com.ubiqube.etsi.mano.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

@Repository
public interface VimConnectionInformationJpa extends CrudRepository<VimConnectionInformation, UUID> {

}
