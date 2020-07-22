package com.ubiqube.etsi.mano.model;

public class ExternalManagedVirtualLink {
	private String extManagedVirtualLinkId = null;

	private String vmfVirtualLinkDescId = null;

	private String vimId = null;

	private String resourceProviderId = null;

	private String resourceId = null;

	public String getExtManagedVirtualLinkId() {
		return extManagedVirtualLinkId;
	}

	public void setExtManagedVirtualLinkId(final String extManagedVirtualLinkId) {
		this.extManagedVirtualLinkId = extManagedVirtualLinkId;
	}

	public String getVmfVirtualLinkDescId() {
		return vmfVirtualLinkDescId;
	}

	public void setVmfVirtualLinkDescId(final String vmfVirtualLinkDescId) {
		this.vmfVirtualLinkDescId = vmfVirtualLinkDescId;
	}

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
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

}
