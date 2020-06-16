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

	private Integer scaleLevel = null;

	public ScaleInfo() {
		// Nothing.
	}

	public ScaleInfo(@NotNull final String _aspectId, @NotNull final Integer _scaleLevel) {
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

	public Integer getScaleLevel() {
		return scaleLevel;
	}

	public void setScaleLevel(final Integer scaleLevel) {
		this.scaleLevel = scaleLevel;
	}

}
