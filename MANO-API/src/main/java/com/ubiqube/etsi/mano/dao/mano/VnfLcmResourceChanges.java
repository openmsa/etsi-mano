package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;

import javax.persistence.Embeddable;

import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVirtualLink;
import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVirtualStorage;
import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVnfc;

@Embeddable
public class VnfLcmResourceChanges {
	private final List<AffectedVnfc> affectedVnfcs = null;

	private final List<AffectedVirtualLink> affectedVirtualLinks = null;

	private final List<AffectedVirtualStorage> affectedVirtualStorages = null;

}
