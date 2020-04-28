package com.ubiqube.etsi.mano.dao.mano;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class VirtualLinkInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private UUID vnfVirtualLinkDescId = null;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ResourceHandleEntity networkResource = null;

	/**
	 * reservationId
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private GrantInformation grantInformation = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<LinkPortInfo> vnfLinkPorts = null;

	@ElementCollection
	private Map<String, String> metadata = null;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public UUID getVnfVirtualLinkDescId() {
		return vnfVirtualLinkDescId;
	}

	public void setVnfVirtualLinkDescId(final UUID vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
	}

	public ResourceHandleEntity getNetworkResource() {
		return networkResource;
	}

	public void setNetworkResource(final ResourceHandleEntity networkResource) {
		this.networkResource = networkResource;
	}

	public GrantInformation getGrantInformation() {
		return grantInformation;
	}

	public void setGrantInformation(final GrantInformation grantInformation) {
		this.grantInformation = grantInformation;
	}

	public Set<LinkPortInfo> getVnfLinkPorts() {
		return vnfLinkPorts;
	}

	public void setVnfLinkPorts(final Set<LinkPortInfo> vnfLinkPorts) {
		this.vnfLinkPorts = vnfLinkPorts;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

}
