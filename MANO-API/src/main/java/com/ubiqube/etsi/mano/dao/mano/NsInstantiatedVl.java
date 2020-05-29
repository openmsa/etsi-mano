package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditListener.class)
public class NsInstantiatedVl extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String nsVirtualLinkInstanceId = null;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsVirtualLink nsVirtualLinkDesc = null;

	private UUID vlProfileId = null;

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

	public NsVirtualLink getNsVirtualLinkDesc() {
		return nsVirtualLinkDesc;
	}

	public void setNsVirtualLinkDesc(final NsVirtualLink nsVirtualLinkDesc) {
		this.nsVirtualLinkDesc = nsVirtualLinkDesc;
	}

	public UUID getVlProfileId() {
		return vlProfileId;
	}

	public void setVlProfileId(final UUID vlProfileId) {
		this.vlProfileId = vlProfileId;
	}

}
