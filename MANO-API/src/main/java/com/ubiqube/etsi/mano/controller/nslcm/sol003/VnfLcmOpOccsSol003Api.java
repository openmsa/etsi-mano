package com.ubiqube.etsi.mano.controller.nslcm.sol003;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
@Profile({ "default", "VNFM" })
@RestController
public class VnfLcmOpOccsSol003Api implements VnfLcmOpOccsSol003 {

	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmOpOccsSol003Api.class);

	public VnfLcmOpOccsSol003Api() {
		LOG.info("Starting LCM OP OCCS SOL003 Controller.");
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsGet(final String accept) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdCancelPost(final String vnfLcmOpOccId) {
		// VnfLcmOperationOccurenceNotification(result, FAILED_TEMP) NFVO
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdFailPost(final String vnfLcmOpOccId, final String accept) {
		// VnfLcmOperationOccurenceNotification(result, FAILED, changes) NFVO
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdGet(final String vnfLcmOpOccId, final String accept) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRetryPost(final String vnfLcmOpOccId) {
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING, CHANGES) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(final String vnfLcmOpOccId) {
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING, ROLLBACK) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}
}
