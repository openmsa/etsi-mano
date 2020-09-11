package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(AuditListener.class)
public class NsInstantiatedNs extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String nsInstanceId = null;

	private UUID nsdPackage = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public String getNsInstanceId() {
		return nsInstanceId;
	}

	public void setNsInstanceId(final String nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
	}

	public UUID getNsdPackage() {
		return nsdPackage;
	}

	public void setNsdPackage(final UUID nsdPackage) {
		this.nsdPackage = nsdPackage;
	}

}
