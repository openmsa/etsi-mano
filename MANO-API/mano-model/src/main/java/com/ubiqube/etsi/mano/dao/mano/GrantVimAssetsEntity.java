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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.validation.Valid;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
public class GrantVimAssetsEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<VimComputeResourceFlavourEntity> computeResourceFlavours = new HashSet<>();

	@Valid
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<VimSoftwareImageEntity> softwareImages = new HashSet<>();

	public Set<VimComputeResourceFlavourEntity> getComputeResourceFlavours() {
		return computeResourceFlavours;
	}

	public void setComputeResourceFlavours(final Set<VimComputeResourceFlavourEntity> computeResourceFlavours) {
		this.computeResourceFlavours = computeResourceFlavours;
	}

	public Set<VimSoftwareImageEntity> getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(final Set<VimSoftwareImageEntity> softwareImages) {
		this.softwareImages = softwareImages;
	}

}
