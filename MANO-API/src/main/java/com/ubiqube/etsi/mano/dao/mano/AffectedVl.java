package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AffectedVl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private UUID virtualLinkDescId = null;

	private ChangeType changeType = null;

	@Embedded
	private ResourceHandleEntity networkResource = null;

	@ElementCollection
	private Map<String, String> metadata = new HashMap<>();

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public UUID getVirtualLinkDescId() {
		return virtualLinkDescId;
	}

	public void setVirtualLinkDescId(final UUID virtualLinkDescId) {
		this.virtualLinkDescId = virtualLinkDescId;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeType changeType) {
		this.changeType = changeType;
	}

	public ResourceHandleEntity getNetworkResource() {
		return networkResource;
	}

	public void setNetworkResource(final ResourceHandleEntity networkResource) {
		this.networkResource = networkResource;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

}
