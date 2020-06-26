package com.ubiqube.etsi.mano.controller.lcmgrant.sol005;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.GrantsResponseJpa;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.service.event.EventManager;

import ma.glasnost.orika.MapperFacade;

@Profile("!NFVM")
@Service
@Transactional(TxType.NEVER)
public class GrantMngtSol005 implements GrantManagement {

	private static final Logger LOG = LoggerFactory.getLogger(GrantMngtSol005.class);

	private final GrantsResponseJpa grantsJpa;
	private final MapperFacade mapper;
	private final EventManager eventManager;

	private final ObjectMapper objectMapper;

	public GrantMngtSol005(final GrantsResponseJpa _grantsJpa, final MapperFacade _mapper, final EventManager _eventManager, final ObjectMapper _objectMapper) {
		grantsJpa = _grantsJpa;
		mapper = _mapper;
		eventManager = _eventManager;
		objectMapper = _objectMapper;
	}

	@Override
	public GrantResponse get(final UUID grantId) {
		final Optional<GrantResponse> grantOpt = grantsJpa.findById(grantId);
		return grantOpt.orElseThrow(() -> new NotFoundException("Unable to find grant " + grantId));
	}

	@Override
	public GrantResponse post(final GrantRequest grantRequest) {
		try {
			final String content = objectMapper.writeValueAsString(grantRequest);
			Files.write(content.getBytes(Charset.defaultCharset()), new File("grant-request.json"));
			LOG.error("HellO {}", content);
		} catch (final IOException e) {
			LOG.warn("", e);
		}
		GrantResponse grants = mapper.map(grantRequest, GrantResponse.class);
		grants.setAvailable(Boolean.FALSE);
		grants = grantsJpa.save(grants);
		LOG.debug("Sending grants {}", grants.getId());
		eventManager.sendGrant(grants.getId(), new HashMap<String, Object>());
		LOG.info("Grant request {} sent.", grants.getId());
		return grants;
	}

}
