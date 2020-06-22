package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class VnfInstanceScaleInfo implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String aspectId = null;

	private Integer scaleLevel = null;

	public VnfInstanceScaleInfo() {
		// Nothing.
	}

	public VnfInstanceScaleInfo(@NotNull final String aspectId2, @NotNull final Integer scaleLevel2) {
		aspectId = aspectId2;
		scaleLevel = scaleLevel2;
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Nonnull
	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(final String aspectId) {
		this.aspectId = aspectId;
	}

	@Nonnull
	public Integer getScaleLevel() {
		return scaleLevel;
	}

	public void setScaleLevel(final Integer scaleLevel) {
		this.scaleLevel = scaleLevel;
	}

	@Override
	public String toString() {
		return "VnfInstanceScaleInfo [id=" + id + ", aspectId=" + aspectId + ", scaleLevel=" + scaleLevel + "]";
	}

}
