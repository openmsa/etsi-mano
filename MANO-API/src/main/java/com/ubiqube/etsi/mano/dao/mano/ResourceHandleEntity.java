package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class ResourceHandleEntity {
	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String resourceId = null;

	private String vimLevelResourceType = null;

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

	public String getVimLevelResourceType() {
		return vimLevelResourceType;
	}

	public void setVimLevelResourceType(final String vimLevelResourceType) {
		this.vimLevelResourceType = vimLevelResourceType;
	}

}
