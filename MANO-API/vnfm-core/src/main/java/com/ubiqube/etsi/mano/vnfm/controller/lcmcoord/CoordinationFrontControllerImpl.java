package com.ubiqube.etsi.mano.vnfm.controller.lcmcoord;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.vnfm.fc.lcmcoord.CoordinationFrontController;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class CoordinationFrontControllerImpl implements CoordinationFrontController {

	@Override
	public <U> ResponseEntity<U> create(final Object body, final Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> ResponseEntity<U> findById(final String coordinationId, final Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> cancel(final String coordinationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
