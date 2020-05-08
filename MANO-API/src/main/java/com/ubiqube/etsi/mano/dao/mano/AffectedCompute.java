package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.FieldBridge;

import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
public class AffectedCompute {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private UUID vduId = null;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private ChangeType changeType = null;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private ResourceHandleEntity computeResource = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata = new HashMap<>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> affectedVnfcCpIds = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> addedStorageResourceIds = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> removedStorageResourceIds = null;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public UUID getVduId() {
		return vduId;
	}

	public void setVduId(final UUID vduId) {
		this.vduId = vduId;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeType changeType) {
		this.changeType = changeType;
	}

	public ResourceHandleEntity getComputeResource() {
		return computeResource;
	}

	public void setComputeResource(final ResourceHandleEntity computeResource) {
		this.computeResource = computeResource;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
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

	public void addAddedStorageResourceIds(final String y) {
		if (null == addedStorageResourceIds) {
			addedStorageResourceIds = new HashSet<>();
		}
		addedStorageResourceIds.add(y);
	}

}
