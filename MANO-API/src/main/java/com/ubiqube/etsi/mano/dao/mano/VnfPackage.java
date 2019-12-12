package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class VnfPackage implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Field
	private String vnfdId;

	@Field
	private String vnfProvider;

	@Field
	private String vnfProductName;

	@Field
	private String vnfSoftwareVersion;

	@Field
	private String vnfdVersion;

	private Checksum checksum;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<SoftwareImage> softwareImages;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<AdditionalArtifact> additionalArtifacts;

	@Enumerated(EnumType.STRING)
	@Field
	@FieldBridge(impl = EnumFieldBridge.class)
	private PackageOnboardingStateType onboardingState;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private PackageOperationalStateType operationalState;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private PackageUsageStateType usageState;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<VnfUserDefinedData> userDefinedData;

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

	public Checksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final Checksum checksum) {
		this.checksum = checksum;
	}

	public Set<SoftwareImage> getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(final Set<SoftwareImage> softwareImages) {
		this.softwareImages = softwareImages;
	}

	public Set<AdditionalArtifact> getAdditionalArtifacts() {
		return additionalArtifacts;
	}

	public void setAdditionalArtifacts(final Set<AdditionalArtifact> additionalArtifacts) {
		this.additionalArtifacts = additionalArtifacts;
	}

	public PackageOnboardingStateType getOnboardingState() {
		return onboardingState;
	}

	public void setOnboardingState(final PackageOnboardingStateType onboardingState) {
		this.onboardingState = onboardingState;
	}

	public PackageOperationalStateType getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(final PackageOperationalStateType operationalState) {
		this.operationalState = operationalState;
	}

	public PackageUsageStateType getUsageState() {
		return usageState;
	}

	public void setUsageState(final PackageUsageStateType usageState) {
		this.usageState = usageState;
	}

	public List<VnfUserDefinedData> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final List<VnfUserDefinedData> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

}
