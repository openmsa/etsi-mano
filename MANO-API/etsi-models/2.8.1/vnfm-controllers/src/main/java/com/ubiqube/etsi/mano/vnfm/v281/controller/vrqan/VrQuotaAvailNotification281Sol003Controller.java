package com.ubiqube.etsi.mano.vnfm.v281.controller.vrqan;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v281.model.vrqan.VrQuotaAvailNotification;

@RestController
public class VrQuotaAvailNotification281Sol003Controller implements VrQuotaAvailNotification281Sol003Api {

	@Override
	public ResponseEntity<Void> vrQuotaAvailNotificationGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vrQuotaAvailNotificationPost(final String contentType, @Valid final VrQuotaAvailNotification body) {
		// TODO Auto-generated method stub
		return null;
	}

}
