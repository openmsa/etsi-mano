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
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.ConstraintRef;

public class VimConstraints implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id;

	private boolean sameResourceGroup;

	private Set<ConstraintRef> resources = new LinkedHashSet<>();

	private VnfGrantsRequest grants;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public boolean isSameResourceGroup() {
		return sameResourceGroup;
	}

	public void setSameResourceGroup(final boolean sameResourceGroup) {
		this.sameResourceGroup = sameResourceGroup;
	}

	public Set<ConstraintRef> getResources() {
		return resources;
	}

	public void setResources(final Set<ConstraintRef> resources) {
		this.resources = resources;
	}

	public VnfGrantsRequest getGrants() {
		return grants;
	}

	public void setGrants(final VnfGrantsRequest grants) {
		this.grants = grants;
	}

}
