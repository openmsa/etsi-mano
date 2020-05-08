package com.ubiqube.etsi.mano.controller.lcmgrant.sol005;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.Grants;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.GrantsJpa;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

import ma.glasnost.orika.MapperFacade;

@Profile("!NFVM")
@Service
@Transactional(TxType.NEVER)
public class GrantMngtSol005 implements GrantManagement {

	private static final Logger LOG = LoggerFactory.getLogger(GrantMngtSol005.class);

	private final GrantsJpa grantsJpa;
	private final MapperFacade mapper;
	private final EventManager eventManager;

	public GrantMngtSol005(final GrantsJpa _grantsJpa, final MapperFacade _mapper, final EventManager _eventManager) {
		grantsJpa = _grantsJpa;
		mapper = _mapper;
		eventManager = _eventManager;
	}

	@Override
	public Grants get(final UUID grantId) {
		final Optional<Grants> grantOpt = grantsJpa.findById(grantId);
		return grantOpt.orElseThrow(() -> new NotFoundException("Unable to find grant " + grantId));
	}

	@Override
	public Grants post(final GrantRequest grantRequest) {
		Grants grants = mapper.map(grantRequest, Grants.class);
		grants.setAvailable(Boolean.FALSE);
		grants = grantsJpa.save(grants);
		LOG.debug("Sending grants {}", grants.getId());
		eventManager.sendAction(ActionType.GRANT_REQUEST, grants.getId().toString(), new HashMap<String, Object>());
		LOG.info("Grant request {} sent.", grants.getId());
		return grants;
	}

}
