package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class AffectedExtCp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private VnfExtCp extCpId = null;

	private ChangeType changeType = null;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ResourceHandleEntity storageResource = null;

	@Transient
	private Map<String, String> metadata = new HashMap<>();

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfExtCp getExtCpId() {
		return extCpId;
	}

	public void setExtCpId(final VnfExtCp extCpId) {
		this.extCpId = extCpId;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeType changeType) {
		this.changeType = changeType;
	}

	public ResourceHandleEntity getStorageResource() {
		return storageResource;
	}

	public void setStorageResource(final ResourceHandleEntity storageResource) {
		this.storageResource = storageResource;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

}
