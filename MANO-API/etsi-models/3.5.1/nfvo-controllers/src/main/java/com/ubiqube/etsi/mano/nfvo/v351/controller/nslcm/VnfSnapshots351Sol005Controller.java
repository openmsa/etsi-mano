package com.ubiqube.etsi.mano.nfvo.v351.controller.nslcm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfSnapshotInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfSnapshots351Sol005Controller implements VnfSnapshots351Sol005Api {

	@Override
	public ResponseEntity<List<VnfSnapshotInfo>> vnfSnapshotsGet(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotsVnfSnapshotInfoIdDelete(final String vnfSnapshotInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfSnapshotInfo> vnfSnapshotsVnfSnapshotInfoIdGet(final String vnfSnapshotInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
