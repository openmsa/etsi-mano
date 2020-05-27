package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(AuditListener.class)
public class NsInstantiatedVnffg extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vnffgInstanceId = null;

	private String vnffgdId = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVnffgInstanceId() {
		return vnffgInstanceId;
	}

	public void setVnffgInstanceId(final String vnffgInstanceId) {
		this.vnffgInstanceId = vnffgInstanceId;
	}

	public String getVnffgdId() {
		return vnffgdId;
	}

	public void setVnffgdId(final String vnffgdId) {
		this.vnffgdId = vnffgdId;
	}

}
