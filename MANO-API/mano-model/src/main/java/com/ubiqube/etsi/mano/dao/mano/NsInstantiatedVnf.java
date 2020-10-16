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

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditListener.class)
public class NsInstantiatedVnf extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private UUID vnfInstance = null;

	@ManyToOne(cascade = CascadeType.DETACH)
	private NsdPackageVnfPackage nsdPackageVnfPackage;

	private String vnfProfileId = null;

	private String instanceDescription;

	// XXX TO do Add AffectedVnfChangedInfo changedInfo

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public UUID getVnfInstance() {
		return vnfInstance;
	}

	public String getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	public void setVnfInstance(final UUID vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public String getInstanceDescription() {
		return instanceDescription;
	}

	public void setInstanceDescription(final String instanceDescription) {
		this.instanceDescription = instanceDescription;
	}

	public NsdPackageVnfPackage getNsdPackageVnfPackage() {
		return nsdPackageVnfPackage;
	}

	public void setNsdPackageVnfPackage(final NsdPackageVnfPackage nsdPackageVnfPackage) {
		this.nsdPackageVnfPackage = nsdPackageVnfPackage;
	}

}
