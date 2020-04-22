package com.ubiqube.etsi.mano.dao.mano;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class VnfLcmResourceChanges {
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AffectedCompute> affectedVnfcs = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AffectedVl> affectedVirtualLinks = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AffectedVs> affectedVirtualStorages = null;

	public Set<AffectedCompute> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(final Set<AffectedCompute> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	public Set<AffectedVl> getAffectedVirtualLinks() {
		return affectedVirtualLinks;
	}

	public void setAffectedVirtualLinks(final Set<AffectedVl> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
	}

	public Set<AffectedVs> getAffectedVirtualStorages() {
		return affectedVirtualStorages;
	}

	public void setAffectedVirtualStorages(final Set<AffectedVs> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
	}

}
