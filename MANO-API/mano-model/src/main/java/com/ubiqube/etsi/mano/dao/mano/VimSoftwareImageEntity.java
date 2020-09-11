package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class VimSoftwareImageEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String vnfdSoftwareImageId = null;

	private String vimSoftwareImageId = null;

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

	public String getVnfdSoftwareImageId() {
		return vnfdSoftwareImageId;
	}

	public void setVnfdSoftwareImageId(final String vnfdSoftwareImageId) {
		this.vnfdSoftwareImageId = vnfdSoftwareImageId;
	}

	public String getVimSoftwareImageId() {
		return vimSoftwareImageId;
	}

	public void setVimSoftwareImageId(final String vimSoftwareImageId) {
		this.vimSoftwareImageId = vimSoftwareImageId;
	}

}
