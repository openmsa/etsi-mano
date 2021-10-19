package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v261.model.vnflcm.VnfLcmOperationOccurrenceNotification;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfLcmOperationOccurrenceNotification261Sol003Controller implements VnfLcmOperationOccurrenceNotification261Sol003Api {

	@Override
	public ResponseEntity<Void> vnfLcmOperationOccurrenceNotificationGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfLcmOperationOccurrenceNotificationPost(@Valid final VnfLcmOperationOccurrenceNotification body) {
		// TODO Auto-generated method stub
		return null;
	}

}
