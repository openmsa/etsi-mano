package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class GrantInformation {
	private String resourceDefinitionId = null;

	private String reservationId = null;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String zoneId = null;

	private String resourceGroupId = null;

	public String getResourceDefinitionId() {
		return resourceDefinitionId;
	}

	public void setResourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(final String reservationId) {
		this.reservationId = reservationId;
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

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public String getResourceGroupId() {
		return resourceGroupId;
	}

	public void setResourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

}
