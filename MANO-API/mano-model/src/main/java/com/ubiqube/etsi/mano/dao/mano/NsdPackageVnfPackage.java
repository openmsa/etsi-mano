package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NsdPackageVnfPackage implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne(cascade = CascadeType.DETACH)
	private VnfPackage vnfPackage;

	@ManyToOne
	private NsdPackage nsdPackage;

	private String toscaName;

	private String toscaId;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfPackage getVnfPackage() {
		return vnfPackage;
	}

	public void setVnfPackage(final VnfPackage vnfPackage) {
		this.vnfPackage = vnfPackage;
	}

	public NsdPackage getNsdPackage() {
		return nsdPackage;
	}

	public void setNsdPackage(final NsdPackage nsdPackage) {
		this.nsdPackage = nsdPackage;
	}

	public String getToscaName() {
		return toscaName;
	}

	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	public String getToscaId() {
		return toscaId;
	}

	public void setToscaId(final String toscaId) {
		this.toscaId = toscaId;
	}

}
