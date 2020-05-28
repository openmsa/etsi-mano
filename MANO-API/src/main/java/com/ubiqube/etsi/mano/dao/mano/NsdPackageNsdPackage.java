package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NsdPackageNsdPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private NsdPackage parent;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsdPackage child;

	private String toscaName;

	private String toscaId;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public NsdPackage getParent() {
		return parent;
	}

	public void setParent(final NsdPackage parent) {
		this.parent = parent;
	}

	public NsdPackage getChild() {
		return child;
	}

	public void setChild(final NsdPackage child) {
		this.child = child;
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
