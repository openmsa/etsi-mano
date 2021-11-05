package com.ubiqube.etsi.mano.vnfm.fc.vnfconfig;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface VnfConfigurationFrontController {

	<U> ResponseEntity<U> modify(U body);

	<U> ResponseEntity<U> find();

}
