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
package com.ubiqube.etsi.mano.dao.mano.v2;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.VnfStorage;

@Entity
public class StorageTask extends Task {
	/** Version. */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private VnfStorage vnfStorage;

	private String parentAlias;

	public VnfStorage getVnfStorage() {
		return vnfStorage;
	}

	public void setVnfStorage(final VnfStorage vnfStorage) {
		this.vnfStorage = vnfStorage;
	}

	public String getParentAlias() {
		return parentAlias;
	}

	public void setParentAlias(final String parentAlias) {
		this.parentAlias = parentAlias;
	}

}
