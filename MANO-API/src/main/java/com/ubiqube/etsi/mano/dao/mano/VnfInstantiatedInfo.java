package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class VnfInstantiatedInfo {

	private String flavourId = null;

	@Enumerated(EnumType.STRING)
	private OperationalStateType vnfState = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ScaleInfo> scaleStatus = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ExtCpInfo> extCpInfo = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ExtVirtualLinkDataEntity> extVirtualLinkInfo = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<VirtualLinkInfo> extManagedVirtualLinkInfo = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MonitoringParams> monitoringParameters = null;

	private String localizationLanguage = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<VnfInstantiedCompute> vnfcResourceInfo = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<VirtualLinkInfo> virtualLinkResourceInfo = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<VirtualStorageInfo> virtualStorageResourceInfo = null;

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public OperationalStateType getVnfState() {
		return vnfState;
	}

	public void setVnfState(final OperationalStateType vnfState) {
		this.vnfState = vnfState;
	}

	public Set<ScaleInfo> getScaleStatus() {
		return scaleStatus;
	}

	public void setScaleStatus(final Set<ScaleInfo> scaleStatus) {
		this.scaleStatus = scaleStatus;
	}

	public Set<ExtCpInfo> getExtCpInfo() {
		return extCpInfo;
	}

	public void setExtCpInfo(final Set<ExtCpInfo> extCpInfo) {
		this.extCpInfo = extCpInfo;
	}

	public Set<ExtVirtualLinkDataEntity> getExtVirtualLinkInfo() {
		return extVirtualLinkInfo;
	}

	public void setExtVirtualLinkInfo(final Set<ExtVirtualLinkDataEntity> extVirtualLinkInfo) {
		this.extVirtualLinkInfo = extVirtualLinkInfo;
	}

	public Set<VirtualLinkInfo> getExtManagedVirtualLinkInfo() {
		return extManagedVirtualLinkInfo;
	}

	public void setExtManagedVirtualLinkInfo(final Set<VirtualLinkInfo> extManagedVirtualLinkInfo) {
		this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
	}

	public Set<MonitoringParams> getMonitoringParameters() {
		return monitoringParameters;
	}

	public void setMonitoringParameters(final Set<MonitoringParams> monitoringParameters) {
		this.monitoringParameters = monitoringParameters;
	}

	public String getLocalizationLanguage() {
		return localizationLanguage;
	}

	public void setLocalizationLanguage(final String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
	}

	public Set<VnfInstantiedCompute> getVnfcResourceInfo() {
		return vnfcResourceInfo;
	}

	public void setVnfcResourceInfo(final Set<VnfInstantiedCompute> vnfcResourceInfo) {
		this.vnfcResourceInfo = vnfcResourceInfo;
	}

	public Set<VirtualLinkInfo> getVirtualLinkResourceInfo() {
		return virtualLinkResourceInfo;
	}

	public void setVirtualLinkResourceInfo(final Set<VirtualLinkInfo> virtualLinkResourceInfo) {
		this.virtualLinkResourceInfo = virtualLinkResourceInfo;
	}

	public Set<VirtualStorageInfo> getVirtualStorageResourceInfo() {
		return virtualStorageResourceInfo;
	}

	public void setVirtualStorageResourceInfo(final Set<VirtualStorageInfo> virtualStorageResourceInfo) {
		this.virtualStorageResourceInfo = virtualStorageResourceInfo;
	}

	public void addVirtualStorageResourceInfoItem(final VirtualStorageInfo createVStorage) {
		if (null == virtualStorageResourceInfo) {
			virtualStorageResourceInfo = new HashSet<>();
		}
		virtualStorageResourceInfo.add(createVStorage);
	}

	public void addVnfcResourceInfoItem(final VnfInstantiedCompute createCompute) {
		if (null == vnfcResourceInfo) {
			vnfcResourceInfo = new HashSet<>();
		}
		vnfcResourceInfo.add(createCompute);
	}

	public void addVirtualLinkResourceInfoItem(final VirtualLinkInfo createVl) {
		if (null == virtualLinkResourceInfo) {
			virtualLinkResourceInfo = new HashSet<>();
		}
		virtualLinkResourceInfo.add(createVl);
	}

}
