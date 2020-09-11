package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LinkPortInfo implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id = null;

	@Embedded
	private transient VimResource resourceHandle = null;

	private String cpInstanceId = null;

	private LinkPortType cpInstanceType = null;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VimResource getResourceHandle() {
		return resourceHandle;
	}

	public void setResourceHandle(final VimResource resourceHandle) {
		this.resourceHandle = resourceHandle;
	}

	public String getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(final String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

	public LinkPortType getCpInstanceType() {
		return cpInstanceType;
	}

	public void setCpInstanceType(final LinkPortType cpInstanceType) {
		this.cpInstanceType = cpInstanceType;
	}

}
