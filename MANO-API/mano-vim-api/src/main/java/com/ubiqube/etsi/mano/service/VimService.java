package com.ubiqube.etsi.mano.service;

import java.util.Optional;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

public interface VimService {

	Optional<VimConnectionInformation> findById(UUID id);

}
