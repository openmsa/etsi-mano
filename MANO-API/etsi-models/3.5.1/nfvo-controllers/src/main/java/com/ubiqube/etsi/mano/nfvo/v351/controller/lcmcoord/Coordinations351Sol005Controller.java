package com.ubiqube.etsi.mano.nfvo.v351.controller.lcmcoord;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.LcmCoord;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.LcmCoordRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Coordinations351Sol005Controller implements Coordinations351Sol005Api {

	@Override
	public ResponseEntity<Void> coordinationsCancelPost(@Valid final LcmCoord clmCoord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<LcmCoord> coordinationsCoordinationIdGet(final String coordinationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<LcmCoord> coordinationsPost(@Valid final LcmCoordRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

}
