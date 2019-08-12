package com.ubiqube.etsi.mano.controller.nslcm.sol003;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
@RestController
public class VnfLcmOpOccsSol003Api implements VnfLcmOpOccsSol003 {

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsGet(String accept) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdCancelPost(String vnfLcmOpOccId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdFailPost(String vnfLcmOpOccId, String accept) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdGet(String vnfLcmOpOccId, String accept) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRetryPost(String vnfLcmOpOccId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(String vnfLcmOpOccId) {
		throw new GenericException("TODO");
	}
}
