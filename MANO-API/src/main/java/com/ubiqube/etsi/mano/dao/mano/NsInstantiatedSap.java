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
public class NsInstantiatedSap extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String sapInstanceId = null;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsSap sapd = null;

	private String sapName = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public String getSapInstanceId() {
		return sapInstanceId;
	}

	public void setSapInstanceId(final String sapInstanceId) {
		this.sapInstanceId = sapInstanceId;
	}

	public NsSap getSapd() {
		return sapd;
	}

	public void setSapd(final NsSap sapd) {
		this.sapd = sapd;
	}

	public String getSapName() {
		return sapName;
	}

	public void setSapName(final String sapName) {
		this.sapName = sapName;
	}

}
