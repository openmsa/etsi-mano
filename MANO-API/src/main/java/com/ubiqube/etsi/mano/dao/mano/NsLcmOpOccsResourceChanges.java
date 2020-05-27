package com.ubiqube.etsi.mano.dao.mano;

import java.util.Set;

import javax.persistence.Embeddable;

@Embeddable
public class NsLcmOpOccsResourceChanges {
	private Set<NsInstantiatedVnf> affectedVnfs = null;

	private Set<NsInstantiatedPnf> affectedPnfs = null;

	private Set<NsInstantiatedVl> affectedVls = null;

	private Set<NsInstantiatedVnffg> affectedVnffgs = null;

	private Set<NsInstantiatedNs> affectedNss = null;

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

}
