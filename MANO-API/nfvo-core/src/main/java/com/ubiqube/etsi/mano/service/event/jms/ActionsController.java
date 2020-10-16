package com.ubiqube.etsi.mano.service.event.jms;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.ubiqube.etsi.mano.service.event.ActionController;

@Service
@Transactional(TxType.NEVER)
public class ActionsController {

	private static final Logger LOG = LoggerFactory.getLogger(ActionsController.class);

	private final ActionController actionController;

	public ActionsController(final ActionController _actionController) {
		super();
		actionController = _actionController;
	}

	@JmsListener(destination = "system.actions", concurrency = "10-25")
	@Transactional(TxType.NEVER)
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.NEVER)
	public void onEvent(final ActionMessage ev) {
		LOG.info("JMS ActionController Receiving Action: {}", ev);
		actionController.dispatch(ev.getActionType(), ev.getObjectId(), ev.getParameters());
		LOG.info("JMS ActionController Done for event: {}", ev);
	}

}
