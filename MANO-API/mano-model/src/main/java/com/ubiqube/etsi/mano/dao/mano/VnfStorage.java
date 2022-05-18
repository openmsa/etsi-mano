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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@EntityListeners(AuditListener.class)
public class VnfStorage implements ImageServiceAware, ToscaEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String toscaId;
	private String toscaName;
	private String state;

	private String type;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private SoftwareImage softwareImage;

	private long size;

	@ManyToOne
	private VnfPackage vnfPackage;

	@Embedded
	private Audit audit;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public SoftwareImage getSoftwareImage() {
		return softwareImage;
	}

	public void setSoftwareImage(final SoftwareImage softwareImage) {
		this.softwareImage = softwareImage;
	}

	public long getSize() {
		return size;
	}

	public void setSize(final long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public String getToscaId() {
		return toscaId;
	}

	@Override
	public void setToscaId(final String toscaId) {
		this.toscaId = toscaId;
	}

	@Override
	public String getToscaName() {
		return toscaName;
	}

	@Override
	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(final String state) {
		this.state = state;
	}

	public VnfPackage getVnfPackage() {
		return vnfPackage;
	}

	public void setVnfPackage(final VnfPackage vnfPackage) {
		this.vnfPackage = vnfPackage;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
