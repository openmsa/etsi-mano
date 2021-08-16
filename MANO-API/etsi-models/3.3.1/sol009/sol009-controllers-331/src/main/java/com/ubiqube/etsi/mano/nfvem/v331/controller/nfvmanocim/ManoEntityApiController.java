package com.ubiqube.etsi.mano.nfvem.v331.controller.nfvmanocim;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ChangeStateRequest;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoConfigModificationRequest;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoConfigModifications;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoEntity;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoServiceInterface;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoServiceInterfaceModificationRequest;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoServiceInterfaceModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class ManoEntityApiController implements ManoEntityApi {

	@Override
	public ResponseEntity<Void> manoEntityChangeStatePost(@Valid final ChangeStateRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ManoEntity> manoEntityGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ManoServiceInterface>> manoEntityManoInterfacesGet(@Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> manoEntityManoInterfacesManoServiceInterfaceIdChangeStatePost(@Valid final ChangeStateRequest body, final String manoServiceInterfaceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ManoServiceInterface> manoEntityManoInterfacesManoServiceInterfaceIdGet(final String manoServiceInterfaceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ManoServiceInterfaceModifications> manoEntityManoInterfacesManoServiceInterfaceIdPatch(@Valid final ManoServiceInterfaceModificationRequest body, final String manoServiceInterfaceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ManoConfigModifications> manoEntityPatch(@Valid final ManoConfigModificationRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

}
