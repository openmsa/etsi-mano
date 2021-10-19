package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v261.model.vnflcm.VnfIdentifierDeletionNotification;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfIdentifierDeletionNotification261Sol003Controller implements VnfIdentifierDeletionNotification261Sol003Api {

	@Override
	public ResponseEntity<Void> vnfIdentifierDeletionNotificationGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfIdentifierDeletionNotificationPost(@Valid final VnfIdentifierDeletionNotification body) {
		// TODO Auto-generated method stub
		return null;
	}

}
