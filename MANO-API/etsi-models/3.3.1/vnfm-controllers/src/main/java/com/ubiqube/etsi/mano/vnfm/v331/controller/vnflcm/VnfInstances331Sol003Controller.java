package com.ubiqube.etsi.mano.vnfm.v331.controller.vnflcm;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.ChangeCurrentVnfPkgRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.ChangeVnfFlavourRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.CreateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.CreateVnfSnapshotRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.HealVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.RevertToVnfSnapshotRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.TerminateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfInfoModificationRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfInstance;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class VnfInstances331Sol003Controller implements VnfInstances331Sol003Api {

	@Override
	public ResponseEntity<List<VnfInstance>> vnfInstancesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesPost(@Valid final CreateVnfRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeExtConnPost(final String vnfInstanceId, @Valid final ChangeExtVnfConnectivityRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeFlavourPost(final String vnfInstanceId, @Valid final ChangeVnfFlavourRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeVnfpkgPost(final String vnfInstanceId, @Valid final ChangeCurrentVnfPkgRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdCreateSnapshotPost(final String vnfInstanceId, @Valid final CreateVnfSnapshotRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdDelete(final String vnfInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesVnfInstanceIdGet(final String vnfInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdHealPost(final String vnfInstanceId, @Valid final HealVnfRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdInstantiatePost(final String vnfInstanceId, @Valid final InstantiateVnfRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdOperatePost(final String vnfInstanceId, @Valid final OperateVnfRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesVnfInstanceIdPatch(final String vnfInstanceId, @Valid final VnfInfoModificationRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdRevertToSnapshotPost(final String vnfInstanceId, @Valid final RevertToVnfSnapshotRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScalePost(final String vnfInstanceId, @Valid final ScaleVnfRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScaleToLevelPost(final String vnfInstanceId, @Valid final ScaleVnfToLevelRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(final String vnfInstanceId, @Valid final TerminateVnfRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

}
