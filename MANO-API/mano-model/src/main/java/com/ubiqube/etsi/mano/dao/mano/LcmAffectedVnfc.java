package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.Map;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.search.annotations.FieldBridge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

public class LcmAffectedVnfc {
	@Id
	private String id = null;
	// Probably a vdu instance.
	private String vduId = null;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private ChangeType changeType = null;

	private VimResource computeResource = null;

	@JsonProperty("metadata")
	private Map<String, String> metadata = null;
// Those are also instances.
	private List<String> affectedVnfcCpIds = null;

	private List<String> addedStorageResourceIds = null;

	private List<String> removedStorageResourceIds = null;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getVduId() {
		return vduId;
	}

	public void setVduId(final String vduId) {
		this.vduId = vduId;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeType changeType) {
		this.changeType = changeType;
	}

	public VimResource getComputeResource() {
		return computeResource;
	}

	public void setComputeResource(final VimResource computeResource) {
		this.computeResource = computeResource;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public List<String> getAffectedVnfcCpIds() {
		return affectedVnfcCpIds;
	}

	public void setAffectedVnfcCpIds(final List<String> affectedVnfcCpIds) {
		this.affectedVnfcCpIds = affectedVnfcCpIds;
	}

	public List<String> getAddedStorageResourceIds() {
		return addedStorageResourceIds;
	}

	public void setAddedStorageResourceIds(final List<String> addedStorageResourceIds) {
		this.addedStorageResourceIds = addedStorageResourceIds;
	}

	public List<String> getRemovedStorageResourceIds() {
		return removedStorageResourceIds;
	}

	public void setRemovedStorageResourceIds(final List<String> removedStorageResourceIds) {
		this.removedStorageResourceIds = removedStorageResourceIds;
	}

}
