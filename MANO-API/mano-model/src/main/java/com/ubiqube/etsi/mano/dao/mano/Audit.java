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
