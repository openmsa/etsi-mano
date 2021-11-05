package com.ubiqube.etsi.mano.vnfm.fc.lcmcoord;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface CoordinationFrontController {

	<U> ResponseEntity<U> create(Object body, Class<?> clazz);

	<U> ResponseEntity<U> findById(String coordinationId, Class<?> clazz);

	ResponseEntity<Void> cancel(String coordinationId);

}
