package com.ubiqube.etsi.mano.service.event.jms;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.ubiqube.etsi.mano.service.event.GrantAction;

@Service
@Transactional(TxType.NEVER)
public class GrantController {
	private final GrantAction grantAction;

	public GrantController(final GrantAction _grantAction) {
		grantAction = _grantAction;
	}

	@JmsListener(destination = "system.actions.grants", concurrency = "10-25")
	@Transactional(TxType.NEVER)
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.NEVER)
	public void onEvent(final GrantMessage ev) {
		grantAction.grantRequest(ev.getObjectId());
	}
}
