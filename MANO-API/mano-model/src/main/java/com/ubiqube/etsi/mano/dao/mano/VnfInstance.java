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

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;

@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class VnfInstance implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@Embedded
	private VnfInstanceStatus instantiatedVnfInfo = null;

	@Enumerated(EnumType.STRING)
	@Field
	private InstantiationState instantiationState = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Set<VimConnectionInformation> vimConnectionInfo = new LinkedHashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> vnfConfigurableProperties = null;

	@Field
	private String vnfdId = null;

	@Field
	private String vnfdVersion = null;

	@Field
	private String vnfInstanceDescription = null;

	@Field
	private String vnfInstanceName = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private VnfPackage vnfPkg = null;

	@Field
	private String vnfProductName = null;

	@Field
	private String vnfProvider = null;

	@Field
	private String vnfSoftwareVersion = null;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private NsdInstance nsInstance;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> extensions = null;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vnfInstance")
	private transient Set<Blueprint> blueprints;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vnfInstance")
	private transient Set<ExtVirtualLinkDataEntity> extVirtualLinks;

	// private Set<VnfInstantiatedBase> extManagedVirtualLinks;

	private Audit audit = new Audit();

	@Override
	public UUID getId() {
		return id;
	}

	public VnfInstanceStatus getInstantiatedVnfInfo() {
		return instantiatedVnfInfo;
	}

	public InstantiationState getInstantiationState() {
		return instantiationState;
	}

	public Set<VimConnectionInformation> getVimConnectionInfo() {
		return vimConnectionInfo;
	}

	public String getVnfdId() {
		return vnfdId;
	}

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public String getVnfProductName() {
		return vnfProductName;
	}

	public String getVnfProvider() {
		return vnfProvider;
	}

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public void setInstantiatedVnfInfo(final VnfInstanceStatus instantiatedVnfInfo) {
		this.instantiatedVnfInfo = instantiatedVnfInfo;
	}

	public void setInstantiationState(final InstantiationState instantiationState) {
		this.instantiationState = instantiationState;
	}

	public void setVimConnectionInfo(final Set<VimConnectionInformation> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public void setVnfInstanceDescription(final String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	public void setVnfInstanceName(final String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	public void setVnfPkg(final VnfPackage vnfPkgId) {
		this.vnfPkg = vnfPkgId;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public Map<String, String> getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public VnfPackage getVnfPkg() {
		return vnfPkg;
	}

	public Map<String, String> getExtensions() {
		return extensions;
	}

	public NsdInstance getNsInstance() {
		return nsInstance;
	}

	public void setNsInstance(final NsdInstance nsInstance) {
		this.nsInstance = nsInstance;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public void setVnfConfigurableProperties(final Map<String, String> vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
	}

	public void setExtensions(final Map<String, String> extensions) {
		this.extensions = extensions;
	}

	public Set<Blueprint> getBlueprints() {
		return blueprints;
	}

	public void setBlueprints(final Set<Blueprint> blueprints) {
		this.blueprints = blueprints;
	}

	public Set<ExtVirtualLinkDataEntity> getExtVirtualLinks() {
		return extVirtualLinks;
	}

	public void setExtVirtualLinks(final Set<ExtVirtualLinkDataEntity> extVirtualLinks) {
		this.extVirtualLinks = extVirtualLinks;
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
