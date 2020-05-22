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
	private Set<VnfInstantiedCompute> affectedVnfcs = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiedVirtualLink> affectedVirtualLinks = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiedStorage> affectedVirtualStorages = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VnfInstantiedExtCp> affectedExtCp = new HashSet<>();

	public Set<VnfInstantiedCompute> getAffectedVnfcs() {
		return affectedVnfcs;
	}

	public void setAffectedVnfcs(final Set<VnfInstantiedCompute> affectedVnfcs) {
		this.affectedVnfcs = affectedVnfcs;
	}

	public void addAffectedVnfcs(final VnfInstantiedCompute affectedCompute) {
		if (null == affectedVnfcs) {
			affectedVnfcs = new HashSet<>();
		}
		affectedVnfcs.add(affectedCompute);
	}

	public Set<VnfInstantiedVirtualLink> getAffectedVirtualLinks() {
		return affectedVirtualLinks;
	}

	public void setAffectedVirtualLinks(final Set<VnfInstantiedVirtualLink> affectedVirtualLinks) {
		this.affectedVirtualLinks = affectedVirtualLinks;
	}

	public Set<VnfInstantiedStorage> getAffectedVirtualStorages() {
		return affectedVirtualStorages;
	}

	public void setAffectedVirtualStorages(final Set<VnfInstantiedStorage> affectedVirtualStorages) {
		this.affectedVirtualStorages = affectedVirtualStorages;
	}

	public void addAffectedVirtualLink(final VnfInstantiedVirtualLink affectedVirtualLink) {
		if (null == affectedVirtualLinks) {
			affectedVirtualLinks = new HashSet<>();
		}
		affectedVirtualLinks.add(affectedVirtualLink);
	}

	public void addAffectedVirtualStorage(final VnfInstantiedStorage affectedVs) {
		if (null == affectedVirtualStorages) {
			affectedVirtualStorages = new HashSet<>();
		}
		affectedVirtualStorages.add(affectedVs);
	}

	public Set<VnfInstantiedExtCp> getAffectedExtCp() {
		return affectedExtCp;
	}

	public void setAffectedExtCp(final Set<VnfInstantiedExtCp> affectedExtCp) {
		this.affectedExtCp = affectedExtCp;
	}

}
