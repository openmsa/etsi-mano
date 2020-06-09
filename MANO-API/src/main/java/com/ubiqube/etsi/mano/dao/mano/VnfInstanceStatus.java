package com.ubiqube.etsi.mano.dao.mano;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class VnfInstanceStatus {
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<VnfInstanceScaleInfo> scaleStatus = null;

	private String instantiationLevelId = null;

	// Not sure it's needed here.
	@Enumerated(EnumType.STRING)
	private OperationalStateType vnfState = null;

	public Set<VnfInstanceScaleInfo> getScaleStatus() {
		return scaleStatus;
	}

	public void setScaleStatus(final Set<VnfInstanceScaleInfo> scaleStatus) {
		this.scaleStatus = scaleStatus;
	}

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public OperationalStateType getVnfState() {
		return vnfState;
	}

	public void setVnfState(final OperationalStateType vnfState) {
		this.vnfState = vnfState;
	}

}
