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

@Entity
@EntityListeners(AuditListener.class)
public class NsVirtualLink implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NsVlProfile nsVlProfile;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NsVlConnectivityType vlConnectivityType;

	@ManyToOne
	private NsdPackage nsdPackage;

	@Embedded
	private Audit audit;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public NsVlProfile getNsVlProfile() {
		return nsVlProfile;
	}

	public void setNsVlProfile(final NsVlProfile nsVlProfile) {
		this.nsVlProfile = nsVlProfile;
	}

	public NsVlConnectivityType getVlConnectivityType() {
		return vlConnectivityType;
	}

	public void setVlConnectivityType(final NsVlConnectivityType vlConnectivityType) {
		this.vlConnectivityType = vlConnectivityType;
	}

	public NsdPackage getNsdPackage() {
		return nsdPackage;
	}

	public void setNsdPackage(final NsdPackage nsdPackage) {
		this.nsdPackage = nsdPackage;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

}
