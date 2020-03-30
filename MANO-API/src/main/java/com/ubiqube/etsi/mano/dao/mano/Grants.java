package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

@Entity
public class Grants {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vnfdId;

	private String flavourId;

	// Must be string because VNFM / NFVO are differents.
	private String vnfInstanceId = null;

	// Must be string because VNFM / NFVO are differents.
	private String vnfLcmOpOccId = null;

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	private List<VimConnectionInformation> vimConnections = null;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "grants")
	private List<ZoneInfoEntity> zones = null;

	@Valid
	@Transient
	private List<ZoneGroupInformation> zoneGroups = null;

	private String computeReservationId = null;

	private String networkReservationId = null;

	private String storageReservationId = null;

	@Valid
	@ElementCollection
	private List<GrantInformation> addResources = null;

	@Valid
	@ElementCollection
	private List<GrantInformation> tempResources = null;

	@Valid
	@ElementCollection
	private List<GrantInformation> removeResources = null;

	@Valid
	@ElementCollection
	private List<GrantInformation> updateResources = null;

	@Embedded
	private GrantVimAssetsEntity vimAssets = null;

	@Valid
	@ElementCollection
	private List<ExtVirtualLinkDataEntity> extVirtualLinks = null;

	@Valid
	@OneToMany(mappedBy = "grants")
	private List<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks = null;

	@Transient
	private Map<String, String> additionalParams = null;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public String getVnfLcmOpOccId() {
		return vnfLcmOpOccId;
	}

	public void setVnfLcmOpOccId(final String vnfLcmOpOccId) {
		this.vnfLcmOpOccId = vnfLcmOpOccId;
	}

	public List<VimConnectionInformation> getVimConnections() {
		return vimConnections;
	}

	public void setVimConnections(final List<VimConnectionInformation> vimConnections) {
		this.vimConnections = vimConnections;
	}

	public List<ZoneInfoEntity> getZones() {
		return zones;
	}

	public void setZones(final List<ZoneInfoEntity> zones) {
		this.zones = zones;
	}

	public List<ZoneGroupInformation> getZoneGroups() {
		return zoneGroups;
	}

	public void setZoneGroups(final List<ZoneGroupInformation> zoneGroups) {
		this.zoneGroups = zoneGroups;
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

	public List<GrantInformation> getAddResources() {
		return addResources;
	}

	public void setAddResources(final List<GrantInformation> addResources) {
		this.addResources = addResources;
	}

	public List<GrantInformation> getTempResources() {
		return tempResources;
	}

	public void setTempResources(final List<GrantInformation> tempResources) {
		this.tempResources = tempResources;
	}

	public List<GrantInformation> getRemoveResources() {
		return removeResources;
	}

	public void setRemoveResources(final List<GrantInformation> removeResources) {
		this.removeResources = removeResources;
	}

	public List<GrantInformation> getUpdateResources() {
		return updateResources;
	}

	public void setUpdateResources(final List<GrantInformation> updateResources) {
		this.updateResources = updateResources;
	}

	public GrantVimAssetsEntity getVimAssets() {
		return vimAssets;
	}

	public void setVimAssets(final GrantVimAssetsEntity vimAssets) {
		this.vimAssets = vimAssets;
	}

	public List<ExtVirtualLinkDataEntity> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(final List<ExtVirtualLinkDataEntity> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
	}

	public List<ExtManagedVirtualLinkDataEntity> getExtManagedVirtualLinks() {
		return extManagedVirtualLinks;
	}

	public void setExtManagedVirtualLinks(final List<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks) {
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

}
