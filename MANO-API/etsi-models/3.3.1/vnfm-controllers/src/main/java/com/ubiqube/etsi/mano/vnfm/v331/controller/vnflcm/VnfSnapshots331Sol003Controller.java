package com.ubiqube.etsi.mano.vnfm.v331.controller.vnflcm;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.CreateVnfSnapshotInfoRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfSnapshotInfo;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfSnapshotInfoModificationRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfSnapshotInfoModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class VnfSnapshots331Sol003Controller implements VnfSnapshots331Sol003Api {

	@Override
	public ResponseEntity<List<VnfSnapshotInfo>> vnfSnapshotsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfSnapshotInfo> vnfSnapshotsPost(@Valid final CreateVnfSnapshotInfoRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotsVnfSnapshotInfoIdDelete(final String vnfSnapshotInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<VnfSnapshotInfo>> vnfSnapshotsVnfSnapshotInfoIdGet(final String vnfSnapshotInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfSnapshotInfoModifications> vnfSnapshotsVnfSnapshotInfoIdPatch(final String vnfSnapshotInfoId, @Valid final VnfSnapshotInfoModificationRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> vnfSnapshotsVnfSnapshotInfoIdVnfStateSnapshotGet(final String vnfSnapshotInfoId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

}