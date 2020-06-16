package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@EntityListeners(AuditListener.class)
public class VnfInstantiatedCompute extends VnfInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@ElementCollection
	private List<String> storageResourceIds = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CpProtocolDataEntity> vnfcCpInfo = null;

	@ElementCollection
	private final Map<String, String> metadata = new HashMap<>();

	private String flavorId;

	private String imageId;
	/**
	 * Vim name.
	 */
	private String aliasName;

	private String toscaName;

	/**
	 * XXX Should be computed.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> affectedVnfcCpIds = null;

	/**
	 * XXX Should be computed.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> addedStorageResourceIds = null;

	/**
	 * XXX Should be computed. This is a vien of current
	 * VnfStorage.changeType==Removed.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> removedStorageResourceIds = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public List<String> getStorageResourceIds() {
		return storageResourceIds;
	}

	public void setStorageResourceIds(final List<String> storageResourceIds) {
		this.storageResourceIds = storageResourceIds;
	}

	public Set<CpProtocolDataEntity> getVnfcCpInfo() {
		return vnfcCpInfo;
	}

	public void setVnfcCpInfo(final Set<CpProtocolDataEntity> vnfcCpInfo) {
		this.vnfcCpInfo = vnfcCpInfo;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(final String imageId) {
		this.imageId = imageId;
	}

	public Set<String> getAffectedVnfcCpIds() {
		return affectedVnfcCpIds;
	}

	public void setAffectedVnfcCpIds(final Set<String> affectedVnfcCpIds) {
		this.affectedVnfcCpIds = affectedVnfcCpIds;
	}

	public Set<String> getAddedStorageResourceIds() {
		return addedStorageResourceIds;
	}

	public void setAddedStorageResourceIds(final Set<String> addedStorageResourceIds) {
		this.addedStorageResourceIds = addedStorageResourceIds;
	}

	public Set<String> getRemovedStorageResourceIds() {
		return removedStorageResourceIds;
	}

	public void setRemovedStorageResourceIds(final Set<String> removedStorageResourceIds) {
		this.removedStorageResourceIds = removedStorageResourceIds;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(final String aliasName) {
		this.aliasName = aliasName;
	}

	public String getToscaName() {
		return toscaName;
	}

	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

}
