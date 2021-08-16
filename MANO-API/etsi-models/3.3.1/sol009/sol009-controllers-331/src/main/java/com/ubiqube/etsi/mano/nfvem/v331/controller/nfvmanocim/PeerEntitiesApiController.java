package com.ubiqube.etsi.mano.nfvem.v331.controller.nfvmanocim;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.CreatePeerEntityRequest;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.PeerEntity;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.PeerEntityConfigModificationRequest;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.PeerEntityConfigModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class PeerEntitiesApiController implements PeerEntitiesApi {

	@Override
	public ResponseEntity<List<PeerEntity>> peerEntitiesGet(@Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> peerEntitiesPeerEntityIdDelete(final String peerEntityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PeerEntity> peerEntitiesPeerEntityIdGet(final String peerEntityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PeerEntityConfigModifications> peerEntitiesPeerEntityIdPatch(@Valid final PeerEntityConfigModificationRequest body, final String peerEntityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PeerEntity> peerEntitiesPost(@Valid final CreatePeerEntityRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

}
