package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ScaleInfo implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String aspectId = null;

	private int scaleLevel;

	public ScaleInfo() {
		// Nothing.
	}

	public ScaleInfo(@NotNull final String _aspectId, final int _scaleLevel) {
		aspectId = _aspectId;
		scaleLevel = _scaleLevel;
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(final String aspectId) {
		this.aspectId = aspectId;
	}

	public int getScaleLevel() {
		return scaleLevel;
	}

	public void setScaleLevel(final int scaleLevel) {
		this.scaleLevel = scaleLevel;
	}

}
