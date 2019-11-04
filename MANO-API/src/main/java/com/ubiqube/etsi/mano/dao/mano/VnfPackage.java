package com.ubiqube.etsi.mano.dao.mano;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class VnfPackage {
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SoftwareImage> softwareImages;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AdditionalArtifact> additionalArtifacts;
	@Enumerated(EnumType.STRING)
	@Field
	@FieldBridge(impl = EnumFieldBridge.class)
	private OnboardingStateEnum onboardingState;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private OperationalStateEnum operationalState;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private UsageStateEnum usageState;

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

	public OnboardingStateEnum getOnboardingState() {
		return onboardingState;
	}

	public void setOnboardingState(final OnboardingStateEnum onboardingState) {
		this.onboardingState = onboardingState;
	}

	public OperationalStateEnum getOperationalState() {
		return operationalState;
	}

	public void setOperationalState(final OperationalStateEnum operationalState) {
		this.operationalState = operationalState;
	}

	public UsageStateEnum getUsageState() {
		return usageState;
	}

	public void setUsageState(final UsageStateEnum usageState) {
		this.usageState = usageState;
	}

}
