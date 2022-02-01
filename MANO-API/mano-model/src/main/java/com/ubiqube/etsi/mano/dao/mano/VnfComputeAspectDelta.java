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

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Getter
@Setter
public class VnfComputeAspectDelta implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String aspectName;

	private String deltaName;

	private String targetName;

	private int numberOfInstances;

	@ManyToOne
	private VnfCompute vnfCompute;

	private int level;

	private int maxScaleLevel;

	private int numInst;

	public VnfComputeAspectDelta() {
		// Nothing.
	}

	public VnfComputeAspectDelta(final String aspectName, final String deltaName, final int numberOfInstances, final int level, final int maxScaleLevel, final String target, int numInst) {
		super();
		this.aspectName = aspectName;
		this.deltaName = deltaName;
		this.numberOfInstances = numberOfInstances;
		this.level = level;
		this.maxScaleLevel = maxScaleLevel;
		this.targetName = target;
		this.numInst = numInst;
	}
}
