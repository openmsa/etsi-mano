package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class VnfInstantiationLevels {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String levelName;

	private String scaleInfoName;

	private int scaleInfoLevel;

	@ManyToOne
	private VnfPackage vnfPackage;

	public VnfInstantiationLevels() {
		// Nothing.
	}

	public VnfInstantiationLevels(final String levelName, final String scaleInfoName, final int scaleInfoLevel) {
		super();
		this.levelName = levelName;
		this.scaleInfoName = scaleInfoName;
		this.scaleInfoLevel = scaleInfoLevel;
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(final String levelName) {
		this.levelName = levelName;
	}

	public String getScaleInfoName() {
		return scaleInfoName;
	}

	public void setScaleInfoName(final String scaleInfoName) {
		this.scaleInfoName = scaleInfoName;
	}

	public int getScaleInfoLevel() {
		return scaleInfoLevel;
	}

	public void setScaleInfoLevel(final int scaleInfoLevel) {
		this.scaleInfoLevel = scaleInfoLevel;
	}

	public VnfPackage getVnfPackage() {
		return vnfPackage;
	}

	public void setVnfPackage(final VnfPackage vnfPackage) {
		this.vnfPackage = vnfPackage;
	}

}
