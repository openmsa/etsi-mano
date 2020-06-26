package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ubiqube.etsi.mano.model.ResourceHandle;
import com.ubiqube.etsi.mano.model.nslcm.VnfLinkPortInfo.CpInstanceTypeEnum;

@Entity
public class LinkPortInfo implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id = null;

	@Embedded
	private transient ResourceHandle resourceHandle = null;

	private String cpInstanceId = null;

	private CpInstanceTypeEnum cpInstanceType = null;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public ResourceHandle getResourceHandle() {
		return resourceHandle;
	}

	public void setResourceHandle(final ResourceHandle resourceHandle) {
		this.resourceHandle = resourceHandle;
	}

	public String getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(final String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

	public CpInstanceTypeEnum getCpInstanceType() {
		return cpInstanceType;
	}

	public void setCpInstanceType(final CpInstanceTypeEnum cpInstanceType) {
		this.cpInstanceType = cpInstanceType;
	}

}
