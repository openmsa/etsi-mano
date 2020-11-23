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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantVimAssetsEntity;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.ZoneInfoEntity;

public class GrantsRequest implements BaseEntity {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id = null;

	private String vnfdId;

	private String flavourId;

	private NsdChangeType operation;

	private boolean isAutomaticInvocation;

	private String instantiationLevelId;

	private VnfLcmOpOccs vnfLcmOpOccs = null;

	private Set<VimConnectionInformation> vimConnections = null;

	private Set<ZoneInfoEntity> zones = null;

	private String computeReservationId = null;

	private String networkReservationId = null;

	private String storageReservationId = null;

	private Set<GrantInformation> addResources = new HashSet<>();

	private Set<GrantInformation> tempResources = new HashSet<>();

	private Set<GrantInformation> removeResources = new HashSet<>();

	private Set<GrantInformation> updateResources = new HashSet<>();

	private GrantVimAssetsEntity vimAssets = null;

	private Set<ExtVirtualLinkDataEntity> extVirtualLinks = null;

	private Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks = null;

	private Map<String, String> additionalParams = null;

	private Set<VimConstraints> vimConstraints;
	/**
	 * Flag to say if grants have been, inspected.
	 */
	private Boolean available;

	private VnfInstance vnfInstance;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Set<VimConnectionInformation> getVimConnections() {
		return vimConnections;
	}

	public void setVimConnections(final Set<VimConnectionInformation> vimConnections) {
		this.vimConnections = vimConnections;
	}

	public Set<ZoneInfoEntity> getZones() {
		return zones;
	}

	public void setZones(final Set<ZoneInfoEntity> zones) {
		this.zones = zones;
	}

	public String getComputeReservationId() {
		return computeReservationId;
	}

	public void setComputeReservationId(final String computeReservationId) {
		this.computeReservationId = computeReservationId;
	}

	public String getNetworkReservationId() {
		return networkReservationId;
	}

	public void setNetworkReservationId(final String networkReservationId) {
		this.networkReservationId = networkReservationId;
	}

	public String getStorageReservationId() {
		return storageReservationId;
	}

	public void setStorageReservationId(final String storageReservationId) {
		this.storageReservationId = storageReservationId;
	}

	public Set<GrantInformation> getAddResources() {
		return addResources;
	}

	public void setAddResources(final Set<GrantInformation> addResources) {
		this.addResources = addResources;
	}

	public Set<GrantInformation> getTempResources() {
		return tempResources;
	}

	public void setTempResources(final Set<GrantInformation> tempResources) {
		this.tempResources = tempResources;
	}

	public Set<GrantInformation> getRemoveResources() {
		return removeResources;
	}

	public void setRemoveResources(final Set<GrantInformation> removeResources) {
		this.removeResources = removeResources;
	}

	public Set<GrantInformation> getUpdateResources() {
		return updateResources;
	}

	public void setUpdateResources(final Set<GrantInformation> updateResources) {
		this.updateResources = updateResources;
	}

	public GrantVimAssetsEntity getVimAssets() {
		return vimAssets;
	}

	public void setVimAssets(final GrantVimAssetsEntity vimAssets) {
		this.vimAssets = vimAssets;
	}

	public Set<ExtVirtualLinkDataEntity> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(final Set<ExtVirtualLinkDataEntity> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	public Set<ExtManagedVirtualLinkDataEntity> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(final Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks) {
		this.extManagedVirtualLinks = extManagedVirtualLinks;
	}

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public NsdChangeType getOperation() {
		return operation;
	}

	public void setOperation(final NsdChangeType operation) {
		this.operation = operation;
	}

	public boolean isAutomaticInvocation() {
		return isAutomaticInvocation;
	}

	public void setAutomaticInvocation(final boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
	}

	public String isInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(final Boolean available) {
		this.available = available;
	}

	public VnfLcmOpOccs getVnfLcmOpOccs() {
		return vnfLcmOpOccs;
	}

	public void setVnfLcmOpOccs(final VnfLcmOpOccs vnfLcmOpOccs) {
		this.vnfLcmOpOccs = vnfLcmOpOccs;
	}

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public Set<VimConstraints> getVimConstraints() {
		return vimConstraints;
	}

	public void setVimConstraints(final Set<VimConstraints> vimConstraints) {
		this.vimConstraints = vimConstraints;
	}

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final VnfInstance _vnfInstance) {
		vnfInstance = _vnfInstance;
	}

}
