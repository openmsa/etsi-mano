package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(AuditListener.class)
public class NsInstantiatedSap extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String sapInstanceId = null;

	private String sapdId = null;

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

	public String getSapdId() {
		return sapdId;
	}

	public void setSapdId(final String sapdId) {
		this.sapdId = sapdId;
	}

	public String getSapName() {
		return sapName;
	}

	public void setSapName(final String sapName) {
		this.sapName = sapName;
	}

}
