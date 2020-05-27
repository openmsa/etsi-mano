package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(AuditListener.class)
public class NsInstantiatedVl extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String nsVirtualLinkInstanceId = null;

	private String nsVirtualLinkDescId = null;

	private String vlProfileId = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public String getNsVirtualLinkInstanceId() {
		return nsVirtualLinkInstanceId;
	}

	public void setNsVirtualLinkInstanceId(final String nsVirtualLinkInstanceId) {
		this.nsVirtualLinkInstanceId = nsVirtualLinkInstanceId;
	}

	public String getNsVirtualLinkDescId() {
		return nsVirtualLinkDescId;
	}

	public void setNsVirtualLinkDescId(final String nsVirtualLinkDescId) {
		this.nsVirtualLinkDescId = nsVirtualLinkDescId;
	}

	public String getVlProfileId() {
		return vlProfileId;
	}

	public void setVlProfileId(final String vlProfileId) {
		this.vlProfileId = vlProfileId;
	}

}
