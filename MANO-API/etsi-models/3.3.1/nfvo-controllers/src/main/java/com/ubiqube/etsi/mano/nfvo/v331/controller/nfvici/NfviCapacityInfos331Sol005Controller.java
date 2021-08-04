package com.ubiqube.etsi.mano.nfvo.v331.controller.nfvici;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v331.model.nfvici.NfviCapacityInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class NfviCapacityInfos331Sol005Controller implements NfviCapacityInfos331Sol005Api {

	@Override
	public ResponseEntity<List<NfviCapacityInfo>> nfviCapacityInfosGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<NfviCapacityInfo> nfviCapacityInfosVimIdGet(final String vimId, @Valid final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
