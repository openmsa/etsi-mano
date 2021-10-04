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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * A part of NSD.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@EntityListeners(AuditListener.class)
public class NsSap implements ToscaEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String toscaName;

	private String role;

	private String description;

	private String externalVirtualLink;

	private String internalVirtualLink;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> layerProtocols;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Set<NsCpProtocolData> protocol;

	private boolean trunkMode;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<SecurityGroup> securityGroups;

	@ManyToOne
	private NsdPackage nsdPackage;

	private String toscaId;

	private String state;

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
	public String getToscaName() {
		return toscaName;
	}

	@Override
	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Set<String> getLayerProtocols() {
		return layerProtocols;
	}

	public void setLayerProtocols(final Set<String> layerProtocols) {
		this.layerProtocols = layerProtocols;
	}

	public boolean isTrunkMode() {
		return trunkMode;
	}

	public void setTrunkMode(final boolean trunkMode) {
		this.trunkMode = trunkMode;
	}

	public Set<NsCpProtocolData> getProtocol() {
		return protocol;
	}

	public void setProtocol(final Set<NsCpProtocolData> protocol) {
		this.protocol = protocol;
	}

	public Set<SecurityGroup> getSecurityGroups() {
		return securityGroups;
	}

	public void addSecurityGroups(final SecurityGroup securityGroup) {
		if (null == securityGroups) {
			securityGroups = new HashSet<>();
		}
		securityGroups.add(securityGroup);
	}

	public void setSecurityGroups(final Set<SecurityGroup> securityGroup) {
		securityGroups = securityGroup;
	}

	public NsdPackage getNsdPackage() {
		return nsdPackage;
	}

	public void setNsdPackage(final NsdPackage nsdPackage) {
		this.nsdPackage = nsdPackage;
	}

	public String getExternalVirtualLink() {
		return externalVirtualLink;
	}

	public void setExternalVirtualLink(final String externalVirtualLink) {
		this.externalVirtualLink = externalVirtualLink;
	}

	public String getInternalVirtualLink() {
		return internalVirtualLink;
	}

	public void setInternalVirtualLink(final String internalVirtualLink) {
		this.internalVirtualLink = internalVirtualLink;
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
	public String getState() {
		return state;
	}

	@Override
	public void setState(final String state) {
		this.state = state;
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
