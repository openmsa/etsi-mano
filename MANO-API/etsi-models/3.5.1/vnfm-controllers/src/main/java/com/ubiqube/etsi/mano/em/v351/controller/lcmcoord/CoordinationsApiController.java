package com.ubiqube.etsi.mano.em.v351.controller.lcmcoord;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmCoord;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmCoordRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class CoordinationsApiController implements Coordinations351Sol002Api {

	@Override
	public ResponseEntity<Void> coordinationsCoordinationIdCancelPost(final String coordinationId) {
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
