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

import java.io.Serializable;
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
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Entity
@Indexed
public class PnfDescriptor implements BaseEntity, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;
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
	private OnboardingStateType pnfdOnboardingState;
	@Embedded
	private FailureDetails onboardingFailureDetails;
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	private PackageUsageState pnfdUsageState;
	private String userDefinedData;

	@Override
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

	public OnboardingStateType getPnfdOnboardingState() {
		return pnfdOnboardingState;
	}

	public void setPnfdOnboardingState(final OnboardingStateType pnfdOnboardingState) {
		this.pnfdOnboardingState = pnfdOnboardingState;
	}

	public FailureDetails getOnboardingFailureDetails() {
		return onboardingFailureDetails;
	}

	public void setOnboardingFailureDetails(final FailureDetails onboardingFailureDetails) {
		this.onboardingFailureDetails = onboardingFailureDetails;
	}

	public PackageUsageState getPnfdUsageState() {
		return pnfdUsageState;
	}

	public void setPnfdUsageState(final PackageUsageState pnfdUsageState) {
		this.pnfdUsageState = pnfdUsageState;
	}

	public String getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final String userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

}
