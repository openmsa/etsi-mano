package com.ubiqube.etsi.mano.vnfm.v261.controller.vnfind.sol003;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.vnfm.v261.model.vnfind.VnfIndicator;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Controller
public class Indicators261Sol003Controller implements Indicators261Sol003Api {

	@Override
	public ResponseEntity<List<VnfIndicator>> indicatorsGet(@Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<VnfIndicator>> indicatorsVnfInstanceIdGet(final String vnfInstanceId, @Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfIndicator> indicatorsVnfInstanceIdIndicatorIdGet(final String indicatorId, final String vnfInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
