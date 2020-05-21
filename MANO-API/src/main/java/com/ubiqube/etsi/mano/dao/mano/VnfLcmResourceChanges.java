package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class VnfLcmResourceChanges implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<AffectedCompute> affectedVnfcs = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<AffectedVl> affectedVirtualLinks = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<AffectedVs> affectedVirtualStorages = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<AffectedExtCp> affectedExtCp = new HashSet<>();

	public Set<AffectedCompute> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(final Set<AffectedCompute> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	public void addAffectedVnfcs(final AffectedCompute affectedCompute) {
		if (null == affectedVnfcs) {
			affectedVnfcs = new HashSet<>();
		}
		affectedVnfcs.add(affectedCompute);
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

	public void addAffectedVirtualLink(final AffectedVl affectedVirtualLink) {
		if (null == affectedVirtualLinks) {
			affectedVirtualLinks = new HashSet<>();
		}
		affectedVirtualLinks.add(affectedVirtualLink);
	}

	public void addAffectedVirtualStorage(final AffectedVs affectedVs) {
		if (null == affectedVirtualStorages) {
			affectedVirtualStorages = new HashSet<>();
		}
		affectedVirtualStorages.add(affectedVs);
	}

	public Set<AffectedExtCp> getAffectedExtCp() {
		return affectedExtCp;
	}

	public void setAffectedExtCp(final Set<AffectedExtCp> affectedExtCp) {
		this.affectedExtCp = affectedExtCp;
	}

}
