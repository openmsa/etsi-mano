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

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class VduInstantiationLevel implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private VnfCompute vnfCompute;

	private String levelName;

	private int numberOfInstances;

	public VduInstantiationLevel() {
		// Nothing.
	}

	public VduInstantiationLevel(final int _numberOfInstance) {
		numberOfInstances = _numberOfInstance;
	}

	public VduInstantiationLevel(final String _levelName, final int _numberOfInstance) {
		levelName = _levelName;
		numberOfInstances = _numberOfInstance;
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfCompute getVnfCompute() {
		return vnfCompute;
	}

	public void setVnfCompute(final VnfCompute vnfCompute) {
		this.vnfCompute = vnfCompute;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(final String levelName) {
		this.levelName = levelName;
	}

	public int getNumberOfInstances() {
		return numberOfInstances;
	}

	public void setNumberOfInstances(final int numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}

}
