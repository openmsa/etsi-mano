package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Audit implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private LocalDateTime createdOn;

	private LocalDateTime updatedOn;

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(final LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(final LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

}
