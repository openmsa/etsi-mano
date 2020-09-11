package com.ubiqube.etsi.mano;

import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;

public final class Constants {

	private Constants() {
		// Nothing.
	}

	public static final String HASH_ALGORITHM = "SHA-512";

	public static void ensureDisabled(final VnfPackage vnfPackage) {
		if (PackageOperationalState.DISABLED != vnfPackage.getOperationalState()) {
			throw new ConflictException("The VNF Package " + vnfPackage.getId() + " is ENABLED.");
		}
	}

	public static void ensureIsEnabled(final VnfPackage vnfackage) {
		if (PackageOperationalState.ENABLED != vnfackage.getOperationalState()) {
			throw new ConflictException("The VNF Package " + vnfackage.getId() + " is not in ENABLED state.");
		}
	}

	public static void ensureNotInUse(final VnfPackage vnfPackqge) {
		if (PackageUsageState.NOT_IN_USE != vnfPackqge.getUsageState()) {
			throw new ConflictException("The VNF Package " + vnfPackqge.getId() + " is Not In Use State.");
		}
	}

	public static void ensureIsOnboarded(final VnfPackage vnfPackqge) {
		if (OnboardingStateType.ONBOARDED != vnfPackqge.getOnboardingState()) {
			throw new ConflictException("The VNF Package " + vnfPackqge.getId() + " is not in ONBOARDED state.");
		}
	}

	public static void ensureNotOnboarded(final VnfPackage vnfPackage) {
		if (OnboardingStateType.ONBOARDED == vnfPackage.getOnboardingState()) {
			throw new ConflictException("The VNF Package " + vnfPackage.getId() + " is already ONBOARDED");
		}
	}

	public static void ensureInstantiated(final VnfInstance vnfInstance) {
		if (InstantiationState.INSTANTIATED != vnfInstance.getInstantiationState()) {
			throw new GenericException("The VNF Instance " + vnfInstance.getId() + " is not in INSTANTIATED state.");
		}
	}

	public static void ensureNotInstantiated(final VnfInstance vnfInstance) {
		if (InstantiationState.INSTANTIATED == vnfInstance.getInstantiationState()) {
			throw new GenericException("The VNF Instance " + vnfInstance.getId() + " is already in INSTANTIATED state.");
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
		if (InstantiationState.INSTANTIATED == nsInstance.getNsState()) {
			throw new ConflictException("The Ns instance " + nsInstance.getId() + " is instantiated.");
		}
	}

	public static void ensureInstantiated(final NsdInstance nsInstance) {
		if (InstantiationState.INSTANTIATED != nsInstance.getNsState()) {
			throw new GenericException("The Ns Instance " + nsInstance.getId() + " is instantiated.");
		}
	}

}
