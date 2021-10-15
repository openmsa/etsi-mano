package com.ubiqube.etsi.mano.service.event.jms;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.ubiqube.etsi.mano.service.event.CommonActionDispatcher;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@Transactional(TxType.NEVER)
public class CommonActionJms {

	private static final Logger LOG = LoggerFactory.getLogger(CommonActionJms.class);

	private final CommonActionDispatcher actionController;

	public CommonActionJms(final CommonActionDispatcher actionController) {
		super();
		this.actionController = actionController;
	}

	@JmsListener(destination = "system.actions.common", concurrency = "5-10")
	@Transactional(TxType.NEVER)
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.NEVER)
	public void onEvent(final ActionMessage ev) {
		LOG.info("JMS Common ActionController Receiving Action: {}", ev);
		actionController.dispatch(ev.getActionType(), ev.getObjectId(), ev.getParameters());
		LOG.info("JMS Common ActionController Done for event: {}", ev);
	}

}
