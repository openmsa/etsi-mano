package com.ubiqube.etsi.mano.nfvo.v351.controller.nfvici;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v351.model.nfvici.NfviCapacityInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class NfviCapacityInfos351Sol005Controller implements NfviCapacityInfos351Sol005Api {

	@Override
	public ResponseEntity<List<NfviCapacityInfo>> nfviCapacityInfosGet(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<NfviCapacityInfo> nfviCapacityInfosVimIdGet(@Valid final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
