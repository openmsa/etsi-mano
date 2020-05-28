package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class NsLcmOpOccsResourceChanges {
	@OneToMany(cascade = CascadeType.ALL)
	private Set<NsInstantiatedVnf> affectedVnfs = null;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<NsInstantiatedPnf> affectedPnfs = null;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<NsInstantiatedVl> affectedVls = null;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<NsInstantiatedVnffg> affectedVnffgs = null;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<NsInstantiatedNs> affectedNss = null;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<NsInstantiatedSap> affectedSaps = null;

	public Set<NsInstantiatedVnf> getAffectedVnfs() {
		return affectedVnfs;
	}

	public void setAffectedVnfs(final Set<NsInstantiatedVnf> affectedVnfs) {
		this.affectedVnfs = affectedVnfs;
	}

	public Set<NsInstantiatedPnf> getAffectedPnfs() {
		return affectedPnfs;
	}

	public void setAffectedPnfs(final Set<NsInstantiatedPnf> affectedPnfs) {
		this.affectedPnfs = affectedPnfs;
	}

	public Set<NsInstantiatedVl> getAffectedVls() {
		return affectedVls;
	}

	public void setAffectedVls(final Set<NsInstantiatedVl> affectedVls) {
		this.affectedVls = affectedVls;
	}

	public Set<NsInstantiatedVnffg> getAffectedVnffgs() {
		return affectedVnffgs;
	}

	public void setAffectedVnffgs(final Set<NsInstantiatedVnffg> affectedVnffgs) {
		this.affectedVnffgs = affectedVnffgs;
	}

	public Set<NsInstantiatedNs> getAffectedNss() {
		return affectedNss;
	}

	public void setAffectedNss(final Set<NsInstantiatedNs> affectedNss) {
		this.affectedNss = affectedNss;
	}

	public Set<NsInstantiatedSap> getAffectedSaps() {
		return affectedSaps;
	}

	public void setAffectedSaps(final Set<NsInstantiatedSap> affectedSaps) {
		this.affectedSaps = affectedSaps;
	}

	public void addInstantiatedSap(final NsInstantiatedSap sap) {
		if (affectedSaps == null) {
			affectedSaps = new HashSet<>();
		}
		affectedSaps.add(sap);
	}

	public void addInstantiatedVirtualLink(final NsInstantiatedVl vl) {
		if (affectedVls == null) {
			affectedVls = new HashSet<>();
		}
		affectedVls.add(vl);
	}

	public void addInstantiatedNs(final NsInstantiatedNs nss) {
		if (affectedNss == null) {
			affectedNss = new HashSet<>();
		}
		affectedNss.add(nss);
	}

	public void addInstantiatedVnf(final NsInstantiatedVnf vnf) {
		if (affectedVnfs == null) {
			affectedVnfs = new HashSet<>();
		}
		affectedVnfs.add(vnf);
	}

}
