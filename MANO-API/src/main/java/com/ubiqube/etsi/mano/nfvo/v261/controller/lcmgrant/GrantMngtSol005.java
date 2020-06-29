package com.ubiqube.etsi.mano.nfvo.v261.controller.lcmgrant;

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
import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.GrantsResponseJpa;
import com.ubiqube.etsi.mano.service.event.EventManager;

import ma.glasnost.orika.MapperFacade;

@Profile("!NFVM")
@Service
@Transactional(TxType.NEVER)
public class GrantMngtSol005 implements GrantManagement {

	private static final Logger LOG = LoggerFactory.getLogger(GrantMngtSol005.class);

	private final GrantsResponseJpa grantsResponseJpa;
	private final MapperFacade mapper;
	private final EventManager eventManager;

	private final ObjectMapper objectMapper;

	public GrantMngtSol005(final GrantsResponseJpa _grantsJpa, final MapperFacade _mapper, final EventManager _eventManager, final ObjectMapper _objectMapper) {
		grantsResponseJpa = _grantsJpa;
		mapper = _mapper;
		eventManager = _eventManager;
		objectMapper = _objectMapper;
	}

	@Override
	public GrantResponse get(final UUID grantId) {
		final Optional<GrantResponse> grantOpt = grantsResponseJpa.findById(grantId);
		return grantOpt.orElseThrow(() -> new NotFoundException("Unable to find grant " + grantId));
	}

	@Override
	public GrantResponse post(final GrantsRequest grantRequest) {
		final GrantResponse grants = mapper.map(grantRequest, GrantResponse.class);
		grants.setAvailable(Boolean.FALSE);
		final GrantResponse grantsDb = grantsResponseJpa.save(grants);
		LOG.debug("Sending grants {}", grantsDb.getId());
		eventManager.sendGrant(grantsDb.getId(), new HashMap<>());
		LOG.info("Grant request {} sent.", grantsDb.getId());
		return grantsDb;
	}

}
