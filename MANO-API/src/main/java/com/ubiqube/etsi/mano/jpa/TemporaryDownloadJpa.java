package com.ubiqube.etsi.mano.jpa;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.dao.mano.TemporaryDownload;

@Repository
public interface TemporaryDownloadJpa extends CrudRepository<TemporaryDownload, String> {

	Optional<TemporaryDownload> findByIdAndExpirationDateAfter(String id, Date expiration);
}
