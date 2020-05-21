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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class AffectedVs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@ManyToOne
	private VduInstantiationLevel instantiationLevel;

	private VnfStorage virtualStorageDesc = null;

	private ChangeType changeType = null;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ResourceHandleEntity storageResource = null;

	@Transient
	private Map<String, String> metadata = new HashMap<>();

	@ManyToOne
	private VnfLcmOpOccs vnfLcmOpOccs;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfStorage getVirtualStorageDesc() {
		return virtualStorageDesc;
	}

	public void setVirtualStorageDesc(final VnfStorage virtualStorageDesc) {
		this.virtualStorageDesc = virtualStorageDesc;
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

	public VduInstantiationLevel getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final VduInstantiationLevel instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public VnfLcmOpOccs getVnfLcmOpOccs() {
		return vnfLcmOpOccs;
	}

	public void setVnfLcmOpOccs(final VnfLcmOpOccs vnfLcmOpOccs) {
		this.vnfLcmOpOccs = vnfLcmOpOccs;
	}

}
