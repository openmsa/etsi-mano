/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
