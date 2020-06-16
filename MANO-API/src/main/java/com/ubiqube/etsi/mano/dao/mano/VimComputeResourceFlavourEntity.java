package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class VimComputeResourceFlavourEntity {
	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String vnfdVirtualComputeDescId = null;

	private String vimFlavourId = null;

	public VimComputeResourceFlavourEntity() {
		// Nothing
	}

	public VimComputeResourceFlavourEntity(final VimComputeResourceFlavourEntity parent) {
		vimConnectionId = parent.getVimConnectionId();
		resourceProviderId = parent.getResourceProviderId();
		vnfdVirtualComputeDescId = parent.getVnfdVirtualComputeDescId();
		vimFlavourId = parent.getVimFlavourId();
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

	public String getVnfdVirtualComputeDescId() {
		return vnfdVirtualComputeDescId;
	}

	public void setVnfdVirtualComputeDescId(final String vnfdVirtualComputeDescId) {
		this.vnfdVirtualComputeDescId = vnfdVirtualComputeDescId;
	}

	public String getVimFlavourId() {
		return vimFlavourId;
	}

	public void setVimFlavourId(final String vimFlavourId) {
		this.vimFlavourId = vimFlavourId;
	}

}
