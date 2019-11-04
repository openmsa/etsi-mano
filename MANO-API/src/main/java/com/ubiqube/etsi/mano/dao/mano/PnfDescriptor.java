package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class PnfDescriptor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Field
	private String pnfdId;
	@Field
	private String pnfdName;
	@Field
	private String pnfdersion;
	@Field
	private String pnfdProvider;
	@Field
	private String pnfdInvariantId;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private OnboardingStateEnum pnfdOnboardingState;
	@Embedded
	private FailureDetails onboardingFailureDetails;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private UsageStateEnum pnfdUsageState;
	private String userDefinedData;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getPnfdId() {
		return pnfdId;
	}

	public void setPnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
	}

	public String getPnfdName() {
		return pnfdName;
	}

	public void setPnfdName(final String pnfdName) {
		this.pnfdName = pnfdName;
	}

	public String getPnfdersion() {
		return pnfdersion;
	}

	public void setPnfdersion(final String pnfdersion) {
		this.pnfdersion = pnfdersion;
	}

	public String getPnfdProvider() {
		return pnfdProvider;
	}

	public void setPnfdProvider(final String pnfdProvider) {
		this.pnfdProvider = pnfdProvider;
	}

	public String getPnfdInvariantId() {
		return pnfdInvariantId;
	}

	public void setPnfdInvariantId(final String pnfdInvariantId) {
		this.pnfdInvariantId = pnfdInvariantId;
	}

	public OnboardingStateEnum getPnfdOnboardingState() {
		return pnfdOnboardingState;
	}

	public void setPnfdOnboardingState(final OnboardingStateEnum pnfdOnboardingState) {
		this.pnfdOnboardingState = pnfdOnboardingState;
	}

	public FailureDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final FailureDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public UsageStateEnum getPnfdUsageState() {
		return pnfdUsageState;
	}

	public void setPnfdUsageState(final UsageStateEnum pnfdUsageState) {
		this.pnfdUsageState = pnfdUsageState;
	}

	public String getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final String userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

}
