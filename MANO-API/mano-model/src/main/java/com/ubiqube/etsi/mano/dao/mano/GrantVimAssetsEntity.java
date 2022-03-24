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

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Embeddable
@Getter
@Setter
public class GrantVimAssetsEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	/**
	 * Mappings between virtual compute descriptors defined in the VNFD and compute
	 * resource flavours managed in the VIM.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<VimComputeResourceFlavourEntity> computeResourceFlavours = new HashSet<>();

	/**
	 * Mappings between software images defined in the VNFD and software images
	 * managed in the VIM.
	 */
	@Valid
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<VimSoftwareImageEntity> softwareImages = new HashSet<>();

	/**
	 * Mappings between snapshot resources defined in the VNF snapshot package and
	 * resources managed in the VIM.
	 */
	@Valid
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<VimSnapshotResources> snapshotResources;

}
