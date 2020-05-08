package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ZoneInfoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String resourceProviderId = null;

	private String vimConnectionId = null;

	private String zoneId = null;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Grants grants;

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

	public Grants getGrants() {
		return grants;
	}

	public void setGrants(final Grants grants) {
		this.grants = grants;
	}

}
