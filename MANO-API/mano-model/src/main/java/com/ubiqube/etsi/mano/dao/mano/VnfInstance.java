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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class VnfInstance extends Instance {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private VnfPackage vnfPkg;

	/**
	 * Identifier of the VNFD on which the VNF instance is based. See note 1.
	 */
	private String vnfdId;

	/**
	 * Provider of the VNF and the VNFD. The value is copied from the VNFD.
	 */
	private String vnfProvider;

	/**
	 * Name to identify the VNF Product. The value is copied from the VNFD.
	 */
	private String vnfProductName;

	/**
	 * Software version of the VNF. The value is copied from the VNFD.
	 */
	private String vnfSoftwareVersion;

	/**
	 * Identifies the version of the VNFD. The value is copied from the VNFD.
	 */
	private String vnfdVersion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExtCpInfo> extCpInfo;
}
