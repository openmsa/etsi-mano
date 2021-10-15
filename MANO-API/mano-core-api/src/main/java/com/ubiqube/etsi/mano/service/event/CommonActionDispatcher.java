package com.ubiqube.etsi.mano.service.event;

import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class CommonActionDispatcher {
	private final CommonActionController controller;

	public CommonActionDispatcher(final CommonActionController controller) {
		super();
		this.controller = controller;
	}

	public void dispatch(@NotNull final ActionType actionType, @NotNull final UUID objectId, @NotNull final Map<String, Object> parameters) {
		switch (actionType) {
		case REGISTER_NFVO -> controller.registerNfvo(objectId, parameters);
		case REGISTER_VNFM -> controller.registerVnfm(objectId, parameters);
		default -> throw new IllegalArgumentException("Unexpected value: " + actionType);
		}

	}

}
