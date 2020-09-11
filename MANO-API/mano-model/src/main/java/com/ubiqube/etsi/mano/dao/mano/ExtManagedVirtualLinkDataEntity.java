package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ExtManagedVirtualLinkDataEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vnfVirtualLinkDescId = null;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String resourceId = null;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private GrantResponse grants;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVnfVirtualLinkDescId() {
		return vnfVirtualLinkDescId;
	}

	public void setVnfVirtualLinkDescId(final String vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
	}

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public GrantResponse getGrants() {
		return grants;
	}

	public void setGrants(final GrantResponse grants) {
		this.grants = grants;
	}

}
