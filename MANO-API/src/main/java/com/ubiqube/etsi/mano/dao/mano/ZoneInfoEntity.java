package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ZoneInfoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String resourceProviderId = null;

	private String vimConnectionId = null;

	private String zoneId = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private GrantResponse grants;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public GrantResponse getGrants() {
		return grants;
	}

	public void setGrants(final GrantResponse grants) {
		this.grants = grants;
	}

}
