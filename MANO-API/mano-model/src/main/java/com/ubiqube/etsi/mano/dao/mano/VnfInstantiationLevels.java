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

import com.ubiqube.etsi.mano.utils.ToStringIgnore;

@Entity
public class VnfInstantiationLevels implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String levelName;

	private String scaleInfoName;

	private int scaleInfoLevel;

	@ManyToOne
	@ToStringIgnore
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

	@Override
	public String toString() {
		return "VnfInstantiationLevels [id=" + id + ", levelName=" + levelName + ", scaleInfoName=" + scaleInfoName + ", scaleInfoLevel=" + scaleInfoLevel + ", vnfPackage=[Ignored] ]\n";
	}

}
