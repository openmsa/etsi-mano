package com.ubiqube.etsi.mano.nfvo.v351.controller.nsperfo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo.CreateThresholdRequest;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo.Threshold;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo.ThresholdModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Thresholds351Sol005Controller implements Thresholds351Sol005Api {

	@Override
	public ResponseEntity<List<Threshold>> thresholdsGet(@Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Threshold> thresholdsPost(@Valid final CreateThresholdRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> thresholdsThresholdIdDelete(final String thresholdId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Threshold> thresholdsThresholdIdGet(final String thresholdId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ThresholdModifications> thresholdsThresholdIdPatch(final String thresholdId, @Valid final ThresholdModifications body) {
		// TODO Auto-generated method stub
		return null;
	}

}
