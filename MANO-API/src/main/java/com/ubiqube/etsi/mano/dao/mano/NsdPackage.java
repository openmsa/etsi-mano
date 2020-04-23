package com.ubiqube.etsi.mano.dao.mano;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.model.nsd.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class NsdPackage implements BaseEntity {
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

	@ManyToMany
	@JoinColumn
	private Set<VnfPackage> vnfPkgIds;

	@ManyToMany
	@JoinColumn
	private Set<PnfDescriptor> pnfdInfoIds;

	@ManyToMany
	@JoinColumn
	private Set<NsdPackage> nestedNsdInfoIds;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private NsdOnboardingStateType nsdOnboardingState;

	@IndexedEmbedded
	private FailureDetails onboardingFailureDetails;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private PackageOperationalStateType nsdOperationalState;

	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private PackageUsageStateType nsdUsageState;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<NsdUserDefinedData> userDefinedData;

	@Override
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

	public NsdOnboardingStateType getNsdOnboardingState() {
		return nsdOnboardingState;
	}

	public void setNsdOnboardingState(final NsdOnboardingStateType nsdOnboardingState) {
		this.nsdOnboardingState = nsdOnboardingState;
	}

	public FailureDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final FailureDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public PackageOperationalStateType getNsdOperationalState() {
		return nsdOperationalState;
	}

	public void setNsdOperationalState(final PackageOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
	}

	public PackageUsageStateType getNsdUsageState() {
		return nsdUsageState;
	}

	public void setNsdUsageState(final PackageUsageStateType nsdUsageState) {
		this.nsdUsageState = nsdUsageState;
	}

	public Set<NsdUserDefinedData> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Set<NsdUserDefinedData> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

}
