package com.ubiqube.etsi.mano.dao.mano;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class AffectedVl {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@ManyToOne
	private VnfVl virtualLinkDesc = null;

	private ChangeType changeType = null;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private ResourceHandleEntity networkResource = null;

	@ElementCollection
	private Map<String, String> metadata = new HashMap<>();

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfVl getVirtualLinkDesc() {
		return virtualLinkDesc;
	}

	public void setVirtualLinkDesc(final VnfVl virtualLinkDesc) {
		this.virtualLinkDesc = virtualLinkDesc;
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
