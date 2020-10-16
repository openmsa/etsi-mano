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

import javax.persistence.Embeddable;

@Embeddable
public class VnfScaleInfo implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private ScaleTypeEnum scaleType = null;

	private Integer numberOfSteps = null;

	private String aspectId;

	public VnfScaleInfo() {
		// Nothing.
	}

	public VnfScaleInfo(final ScaleTypeEnum scaleType, final String aspectId, final Integer numberOfSteps) {
		super();
		this.scaleType = scaleType;
		this.numberOfSteps = numberOfSteps;
		this.aspectId = aspectId;
	}

	public ScaleTypeEnum getScaleType() {
		return scaleType;
	}

	public void setScaleType(final ScaleTypeEnum scaleType) {
		this.scaleType = scaleType;
	}

	public Integer getNumberOfSteps() {
		return numberOfSteps;
	}

	public void setNumberOfSteps(final Integer numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}

	public String getAspectId() {
		return aspectId;
	}

	public void setAspectId(final String aspectId) {
		this.aspectId = aspectId;
	}

}
