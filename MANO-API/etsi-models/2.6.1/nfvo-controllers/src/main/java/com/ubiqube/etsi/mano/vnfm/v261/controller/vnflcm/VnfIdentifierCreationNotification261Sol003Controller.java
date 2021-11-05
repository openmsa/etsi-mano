package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v261.model.vnflcm.VnfIdentifierCreationNotification;

@RestController
public class VnfIdentifierCreationNotification261Sol003Controller implements VnfIdentifierCreationNotification261Sol003Api {

	@Override
	public ResponseEntity<Void> vnfIdentifierCreationNotificationGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfIdentifierCreationNotificationPost(@Valid final VnfIdentifierCreationNotification body) {
		// TODO Auto-generated method stub
		return null;
	}

}
