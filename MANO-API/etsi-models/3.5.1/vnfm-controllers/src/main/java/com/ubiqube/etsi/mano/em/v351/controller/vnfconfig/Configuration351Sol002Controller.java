package com.ubiqube.etsi.mano.em.v351.controller.vnfconfig;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.em.v351.model.vnfconfig.VnfConfigModifications;
import com.ubiqube.etsi.mano.em.v351.model.vnfconfig.VnfConfiguration;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Configuration351Sol002Controller implements Configuration351Sol002Api {

	@Override
	public ResponseEntity<VnfConfiguration> configurationGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfConfigModifications> configurationPatch(@Valid final VnfConfigModifications body) {
		// TODO Auto-generated method stub
		return null;
	}

}
