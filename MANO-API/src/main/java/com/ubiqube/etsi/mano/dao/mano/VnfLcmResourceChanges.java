package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;

import javax.persistence.Embeddable;

import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVirtualLink;
import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVirtualStorage;
import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVnfc;

@Embeddable
public class VnfLcmResourceChanges {
	private List<AffectedVnfc> affectedVnfcs = null;

	private List<AffectedVirtualLink> affectedVirtualLinks = null;

	private List<AffectedVirtualStorage> affectedVirtualStorages = null;

	public List<AffectedVnfc> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(final List<AffectedVnfc> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	public List<AffectedVirtualLink> getAffectedVirtualLinks() {
		return affectedVirtualLinks;
	}

	public void setAffectedVirtualLinks(final List<AffectedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
	}

	public List<AffectedVirtualStorage> getAffectedVirtualStorages() {
		return affectedVirtualStorages;
	}

	public void setAffectedVirtualStorages(final List<AffectedVirtualStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
	}

}
