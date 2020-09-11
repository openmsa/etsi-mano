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
public class NsdPackageNsdPackage implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private NsdPackage parent;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsdPackage child;

	private String toscaName;

	private String toscaId;

	public NsdPackageNsdPackage() {
		// Nothing.
	}

	public NsdPackageNsdPackage(final NsdPackage parent, final NsdPackage child, final String toscaName) {
		super();
		this.parent = parent;
		this.child = child;
		this.toscaName = toscaName;
	}

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
