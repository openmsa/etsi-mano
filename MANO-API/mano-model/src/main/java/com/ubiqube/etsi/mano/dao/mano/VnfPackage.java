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
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexingDependency;

@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class VnfPackage implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	private UUID id;

	private String defaultInstantiationLevel;

	@FullTextField
	private String vnfdId;

	@FullTextField
	private String vnfProvider;

	@FullTextField
	private String vnfProductName;

	@FullTextField
	private String vnfSoftwareVersion;

	@FullTextField
	private String vnfdVersion;

	@FullTextField
	private String flavorId;

	@FullTextField
	private String descriptorId;

	@FullTextField
	private String descriptorVersion;

	@Embedded
	@IndexedEmbedded
	private PkgChecksum checksum;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@IndexedEmbedded
	private Set<AdditionalArtifact> additionalArtifacts;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private OnboardingStateType onboardingState;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private PackageOperationalState operationalState;

	@Enumerated(EnumType.STRING)
	@FullTextField
	private PackageUsageState usageState;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Map<String, String> userDefinedData;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@IndexedEmbedded
	@IndexingDependency(reindexOnUpdate = ReindexOnUpdate.SHALLOW)
	private Set<VnfCompute> vnfCompute = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	@IndexedEmbedded
	private Set<VnfVl> vnfVl = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	@IndexedEmbedded
	private Set<VnfStorage> vnfStorage = new LinkedHashSet<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@IndexedEmbedded
	private Set<VnfLinkPort> vnfLinkPort = new LinkedHashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	@IndexedEmbedded
	private Set<VnfExtCp> vnfExtCp = new LinkedHashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<NsdInstance> nsInstance;

	@Embedded
	private Audit audit;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@IndexedEmbedded
	private Set<VnfInstantiationLevels> vnfInstantiationLevels;

	@ManyToMany(cascade = CascadeType.DETACH, mappedBy = "vnfPackage")
	private Set<NsdPackageVnfPackage> nsdPackages;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(final String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(final String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	public PkgChecksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final PkgChecksum checksum) {
		this.checksum = checksum;
	}

	public Set<AdditionalArtifact> getAdditionalArtifacts() {
		return additionalArtifacts;
	}

	public void setAdditionalArtifacts(final Set<AdditionalArtifact> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
	}

	public OnboardingStateType getOnboardingState() {
		return onboardingState;
	}

	public void setOnboardingState(final OnboardingStateType onboardingState) {
		this.onboardingState = onboardingState;
	}

	public PackageOperationalState getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(final PackageOperationalState operationalState) {
		this.operationalState = operationalState;
	}

	public PackageUsageState getUsageState() {
		return usageState;
	}

	public void setUsageState(final PackageUsageState usageState) {
		this.usageState = usageState;
	}

	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

	public String getDescriptorId() {
		return descriptorId;
	}

	public void setDescriptorId(final String descriptorId) {
		this.descriptorId = descriptorId;
	}

	public String getDescriptorVersion() {
		return descriptorVersion;
	}

	public void setDescriptorVersion(final String descriptorVersion) {
		this.descriptorVersion = descriptorVersion;
	}

	@Nonnull
	public Set<VnfCompute> getVnfCompute() {
		return vnfCompute;
	}

	public void setVnfCompute(final Set<VnfCompute> vnfCompute) {
		this.vnfCompute = vnfCompute;
	}

	public Set<VnfVl> getVnfVl() {
		return vnfVl;
	}

	public void setVnfVl(final Set<VnfVl> vnfVl) {
		this.vnfVl = vnfVl;
	}

	@Nonnull
	public Set<VnfStorage> getVnfStorage() {
		return vnfStorage;
	}

	public void setVnfStorage(final Set<VnfStorage> vnfStorage) {
		this.vnfStorage = vnfStorage;
	}

	public Set<VnfLinkPort> getVnfLinkPort() {
		return vnfLinkPort;
	}

	public void setVnfLinkPort(final Set<VnfLinkPort> vnfLinkPort) {
		this.vnfLinkPort = vnfLinkPort;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public Set<VnfExtCp> getVnfExtCp() {
		return vnfExtCp;
	}

	public void setVnfExtCp(final Set<VnfExtCp> vnfExtCp) {
		this.vnfExtCp = vnfExtCp;
	}

	public String getDefaultInstantiationLevel() {
		return defaultInstantiationLevel;
	}

	public void setDefaultInstantiationLevel(final String defaultInstantiationLevel) {
		this.defaultInstantiationLevel = defaultInstantiationLevel;
	}

	public Set<VnfInstantiationLevels> getVnfInstantiationLevels() {
		return vnfInstantiationLevels;
	}

	public void setVnfInstantiationLevels(final Set<VnfInstantiationLevels> vnfInstantiationLevels) {
		this.vnfInstantiationLevels = vnfInstantiationLevels;
	}

	public Set<NsdInstance> getNsInstance() {
		return nsInstance;
	}

	public void setNsInstance(final Set<NsdInstance> nsInstance) {
		this.nsInstance = nsInstance;
	}

	public void addInstantiationLevel(final VnfInstantiationLevels il) {
		if (null == vnfInstantiationLevels) {
			vnfInstantiationLevels = new HashSet<>();
		}
		il.setVnfPackage(this);
		vnfInstantiationLevels.add(il);
	}

	public Set<NsdPackageVnfPackage> getNsdPackages() {
		return nsdPackages;
	}

	public void setNsdPackages(final Set<NsdPackageVnfPackage> nsdPackages) {
		this.nsdPackages = nsdPackages;
	}

	public void addNsdPackage(final NsdPackageVnfPackage nsdPackage) {
		if (null == nsdPackages) {
			nsdPackages = new HashSet<>();
		}
		nsdPackages.add(nsdPackage);
	}

}
