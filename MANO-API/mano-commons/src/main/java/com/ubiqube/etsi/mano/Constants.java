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
package com.ubiqube.etsi.mano;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;

public final class Constants {

	public static final String HASH_ALGORITHM = "SHA-512";

	public static final String VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS = "softwareImages,additionalArtifacts,userDefinedData,checksum";

	public static final Set<String> VNF_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id", "onboardingState", "operationalState", "usageState", "_links.self.href", "_links.vnfd.href", "_links.packageContent.href"));

	public static final String VNFPMJOB_SEARCH_DEFAULT_EXCLUDE_FIELDS = "reports";

	public static final Set<String> VNFPMJOB_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id", "criteria.collectionPeriod", "criteria.reportingPeriod", "objectInstanceIds"));

	public static final Set<String> VNFTHR_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	public static final String VNFTHR_SEARCH_DEFAULT_EXCLUDE_FIELDS = null;

	private Constants() {
		// Nothing.
	}

	@Nullable
	public static String getSingleField(final MultiValueMap<String, String> bag, final String parameter) {
		if (null == bag) {
			return null;
		}
		final List<String> params = bag.get(parameter);
		if (null == params) {
			return null;
		}
		if (params.isEmpty()) {
			return null;
		}
		if (params.size() > 1) {
			throw new GenericException("Parameter: " + parameter + " could not have multiple occurences.");
		}
		return params.get(0);
	}

	public static void ensureDisabled(final PackageBase vnfPackage) {
		if (PackageOperationalState.DISABLED != vnfPackage.getOperationalState()) {
			throw new ConflictException("The VNF Package " + vnfPackage.getId() + " is ENABLED.");
		}
	}

	public static void ensureIsEnabled(final PackageBase vnfackage) {
		if (PackageOperationalState.ENABLED != vnfackage.getOperationalState()) {
			throw new ConflictException("The VNF Package " + vnfackage.getId() + " is not in ENABLED state.");
		}
	}

	public static void ensureNotInUse(final PackageBase vnfPackqge) {
		if (PackageUsageState.NOT_IN_USE != vnfPackqge.getUsageState()) {
			throw new ConflictException("The VNF Package " + vnfPackqge.getId() + " is Not In Use State.");
		}
	}

	public static void ensureIsOnboarded(final PackageBase vnfPackqge) {
		if (OnboardingStateType.ONBOARDED != vnfPackqge.getOnboardingState()) {
			throw new ConflictException("The VNF Package " + vnfPackqge.getId() + " is not in ONBOARDED state.");
		}
	}

	public static void ensureNotOnboarded(final PackageBase vnfPackage) {
		if (OnboardingStateType.ONBOARDED == vnfPackage.getOnboardingState()) {
			throw new ConflictException("The VNF Package " + vnfPackage.getId() + " is already ONBOARDED");
		}
	}

	public static void ensureInstantiated(final Instance vnfInstance) {
		if (InstantiationState.INSTANTIATED != vnfInstance.getInstantiationState()) {
			throw new ConflictException("The VNF Instance " + vnfInstance.getId() + " is not in INSTANTIATED state.");
		}
	}

	public static void ensureNotInstantiated(final Instance vnfInstance) {
		if (InstantiationState.INSTANTIATED == vnfInstance.getInstantiationState()) {
			throw new ConflictException("The VNF Instance " + vnfInstance.getId() + " is already in INSTANTIATED state.");
		}
	}

	public static void ensureNotInUse(final NsdPackage nsd) {
		if (PackageUsageState.NOT_IN_USE != nsd.getNsdUsageState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + " Should be in NOT_IN_USE state.");
		}
	}

	public static void ensureDisabled(final NsdPackage nsd) {
		if (PackageOperationalState.DISABLED != nsd.getNsdOperationalState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + " Should be in disabled state.");
		}
	}

	public static void ensureIsEnabled(final NsdPackage nsd) {
		if (PackageOperationalState.ENABLED != nsd.getNsdOperationalState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + " is not in ENABLED state.");
		}
	}

	public static void ensureIsOnboarded(final NsdPackage nsd) {
		if (OnboardingStateType.ONBOARDED != nsd.getNsdOnboardingState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + " is not in ONBOARDED state.");
		}
	}

	public static void ensureNotOnboarded(final NsdPackage nsd) {
		if (OnboardingStateType.ONBOARDED == nsd.getNsdOnboardingState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + "is already ONBOARDED state.");
		}
	}

	public static void ensureNotInstantiated(final NsdInstance nsInstance) {
		if (InstantiationState.INSTANTIATED == nsInstance.getInstantiationState()) {
			throw new ConflictException("The Ns instance " + nsInstance.getId() + " is instantiated.");
		}
	}

	public static void ensureInstantiated(final NsdInstance nsInstance) {
		if (InstantiationState.INSTANTIATED != nsInstance.getInstantiationState()) {
			throw new ConflictException("The Ns Instance " + nsInstance.getId() + " is instantiated.");
		}
	}

}
