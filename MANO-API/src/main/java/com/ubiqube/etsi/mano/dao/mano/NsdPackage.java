package com.ubiqube.etsi.mano.dao.mano;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.ubiqube.etsi.mano.dao.mano.common.OnboardingFailureDetails;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class NsdPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Field
	private String nsdId;
	@Field
	private String nsdName;
	@Field
	private String nsdVersion;
	@Field
	private String nsdDesigner;
	@Field
	private String nsdInvariantId;
	@OneToMany
	private Set<VnfPackage> vnfPkgIds;
	@OneToMany
	private Set<PnfDescriptor> pnfdInfoIds;
	@OneToMany
	private Set<NsdPackage> nestedNsdInfoIds;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private OnboardingStateEnum nsdOnboardingState;
	@IndexedEmbedded
	private OnboardingFailureDetails onboardingFailureDetails;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	OperationalStateEnum nsdOperationalState;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	UsageStateEnum nsdUsageState;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getNsdId() {
		return nsdId;
	}

	public void setNsdId(final String nsdId) {
		this.nsdId = nsdId;
	}

	public String getNsdName() {
		return nsdName;
	}

	public void setNsdName(final String nsdName) {
		this.nsdName = nsdName;
	}

	public String getNsdVersion() {
		return nsdVersion;
	}

	public void setNsdVersion(final String nsdVersion) {
		this.nsdVersion = nsdVersion;
	}

	public String getNsdDesigner() {
		return nsdDesigner;
	}

	public void setNsdDesigner(final String nsdDesigner) {
		this.nsdDesigner = nsdDesigner;
	}

	public String getNsdInvariantId() {
		return nsdInvariantId;
	}

	public void setNsdInvariantId(final String nsdInvariantId) {
		this.nsdInvariantId = nsdInvariantId;
	}

	public Set<VnfPackage> getVnfPkgIds() {
		return vnfPkgIds;
	}

	public void setVnfPkgIds(final Set<VnfPackage> vnfPkgIds) {
		this.vnfPkgIds = vnfPkgIds;
	}

	public Set<PnfDescriptor> getPnfdInfoIds() {
		return pnfdInfoIds;
	}

	public void setPnfdInfoIds(final Set<PnfDescriptor> pnfdInfoIds) {
		this.pnfdInfoIds = pnfdInfoIds;
	}

	public Set<NsdPackage> getNestedNsdInfoIds() {
		return nestedNsdInfoIds;
	}

	public void setNestedNsdInfoIds(final Set<NsdPackage> nestedNsdInfoIds) {
		this.nestedNsdInfoIds = nestedNsdInfoIds;
	}

	public OnboardingStateEnum getNsdOnboardingState() {
		return nsdOnboardingState;
	}

	public void setNsdOnboardingState(final OnboardingStateEnum nsdOnboardingState) {
		this.nsdOnboardingState = nsdOnboardingState;
	}

	public OnboardingFailureDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final OnboardingFailureDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public OperationalStateEnum getNsdOperationalState() {
		return nsdOperationalState;
	}

	public void setNsdOperationalState(final OperationalStateEnum nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
	}

	public UsageStateEnum getNsdUsageState() {
		return nsdUsageState;
	}

	public void setNsdUsageState(final UsageStateEnum nsdUsageState) {
		this.nsdUsageState = nsdUsageState;
	}

}
