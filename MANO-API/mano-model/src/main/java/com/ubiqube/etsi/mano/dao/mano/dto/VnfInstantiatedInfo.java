/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.ExtCpInfo;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.MonitoringParams;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;

public class VnfInstantiatedInfo implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String flavourId = null;

	private OperationalStateType vnfState = null;

	private Set<ScaleInfo> scaleStatus = null;

	private Set<ExtCpInfo> extCpInfo = null;

	private Set<ExtVirtualLinkDataEntity> extVirtualLinkInfo = null;

	private Set<VirtualLinkInfo> extManagedVirtualLinkInfo = null;

	private Set<MonitoringParams> monitoringParameters = null;

	private String localizationLanguage = null;

	private Set<VnfInstantiatedCompute> vnfcResourceInfo = null;

	private Set<VirtualLinkInfo> virtualLinkResourceInfo = null;

	private Set<VnfInstantiatedStorage> virtualStorageResourceInfo = null;

	private String instantiationLevelId = null;

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

	public Set<VnfInstantiatedCompute> getVnfcResourceInfo() {
		return vnfcResourceInfo;
	}

	public void setVnfcResourceInfo(final Set<VnfInstantiatedCompute> vnfcResourceInfo) {
		this.vnfcResourceInfo = vnfcResourceInfo;
	}

	public Set<VirtualLinkInfo> getVirtualLinkResourceInfo() {
		return virtualLinkResourceInfo;
	}

	public void setVirtualLinkResourceInfo(final Set<VirtualLinkInfo> virtualLinkResourceInfo) {
		this.virtualLinkResourceInfo = virtualLinkResourceInfo;
	}

	public Set<VnfInstantiatedStorage> getVirtualStorageResourceInfo() {
		return virtualStorageResourceInfo;
	}

	public void setVirtualStorageResourceInfo(final Set<VnfInstantiatedStorage> virtualStorageResourceInfo) {
		this.virtualStorageResourceInfo = virtualStorageResourceInfo;
	}

	public void addVirtualStorageResourceInfoItem(final VnfInstantiatedStorage vStorage) {
		if (null == virtualStorageResourceInfo) {
			virtualStorageResourceInfo = new HashSet<>();
		}
		virtualStorageResourceInfo.add(vStorage);
	}

	public void addVnfcResourceInfoItem(final VnfInstantiatedCompute compute) {
		if (null == vnfcResourceInfo) {
			vnfcResourceInfo = new HashSet<>();
		}
		vnfcResourceInfo.add(compute);
	}

	public void addVirtualLinkResourceInfoItem(final VirtualLinkInfo virtualLink) {
		if (null == virtualLinkResourceInfo) {
			virtualLinkResourceInfo = new HashSet<>();
		}
		virtualLinkResourceInfo.add(virtualLink);
	}

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

}
