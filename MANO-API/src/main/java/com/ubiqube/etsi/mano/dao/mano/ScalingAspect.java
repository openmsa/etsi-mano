package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScalingAspect {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String description;

	private int maxScaleLevel;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> stepDeltas;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public int getMaxScaleLevel() {
		return maxScaleLevel;
	}

	public void setMaxScaleLevel(final int maxScaleLevel) {
		this.maxScaleLevel = maxScaleLevel;
	}

	public List<String> getStepDeltas() {
		return stepDeltas;
	}

	public void setStepDeltas(final List<String> stepDeltas) {
		this.stepDeltas = stepDeltas;
	}

}
