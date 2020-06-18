package com.ubiqube.etsi.mano.dao.mano;

import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ConstraintResourceRef.IdTypeEnum;

public class ConstraintRef {
	private IdTypeEnum idType = null;

	private String resourceId = null;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	public IdTypeEnum getIdType() {
		return idType;
	}

	public void setIdType(final IdTypeEnum idType) {
		this.idType = idType;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
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

}
