package com.ubiqube.etsi.mano;

import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nsd.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOperationalStateType;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdUsageStateType;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public final class Constants {

	private Constants() {
		// Nothing.
	}

	public static final String HASH_ALGORITHM = "SHA-512";

	public static void ensureDisabled(final VnfPkgInfo vnfPkgInfo) {
		if (PackageOperationalStateType.DISABLED != vnfPkgInfo.getOperationalState()) {
			throw new ConflictException("The VNF Package " + vnfPkgInfo.getId() + " is ENABLED.");
		}
	}

	public static void ensureIsEnabled(final VnfPkgInfo vnfPkgInfo) {
		if (PackageOperationalStateType.ENABLED != vnfPkgInfo.getOperationalState()) {
			throw new ConflictException("The VNF Package " + vnfPkgInfo.getId() + " is not in ENABLED state.");
		}
	}

	public static void ensureNotInUse(final VnfPkgInfo vnfPkgInfo) {
		if (PackageUsageStateType.NOT_IN_USE != vnfPkgInfo.getUsageState()) {
			throw new ConflictException("The VNF Package " + vnfPkgInfo.getId() + " is Not In Use State.");
		}
	}

	public static void ensureIsOnboarded(final VnfPkgInfo vnfPkgInfo) {
		if (PackageOnboardingStateType.ONBOARDED != vnfPkgInfo.getOnboardingState()) {
			throw new ConflictException("The VNF Package " + vnfPkgInfo.getId() + " is not in ONBOARDED state.");
		}
	}

	public static void ensureNotOnboarded(final VnfPkgInfo vnfPkgInfo) {
		if (PackageOnboardingStateType.ONBOARDED == vnfPkgInfo.getOnboardingState()) {
			throw new ConflictException("The VNF Package " + vnfPkgInfo.getId() + " is already ONBOARDED");
		}
	}

	public static void ensureInstantiated(final VnfInstance vnfInstance) {
		if (InstantiationStateEnum.INSTANTIATED != vnfInstance.getInstantiationState()) {
			throw new GenericException("The VNF Instance " + vnfInstance.getId() + " is not in INSTANTIATED state.");
		}
	}

	public static void ensureNotInstantiated(final VnfInstance vnfInstance) {
		if (InstantiationStateEnum.INSTANTIATED == vnfInstance.getInstantiationState()) {
			throw new GenericException("The VNF Instance " + vnfInstance.getId() + " is already in INSTANTIATED state.");
		}
	}

	public static void ensureNotInUse(final NsdInfo nsd) {
		if (NsdUsageStateType.NOT_IN_USE == nsd.getNsdUsageState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + " Should be in NOT_IN_USE state.");
		}
	}

	public static void ensureDisabled(final NsdInfo nsd) {
		if (NsdOperationalStateType.DISABLED != nsd.getNsdOperationalState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + " Should be in disabled state.");
		}
	}

	public static void ensureIsEnabled(final NsdInfo nsd) {
		if (NsdOperationalStateType.ENABLED != nsd.getNsdOperationalState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + " is not in ENABLED state.");
		}
	}

	public static void ensureIsOnboarded(final NsdInfo nsd) {
		if (NsdOnboardingStateType.ONBOARDED != nsd.getNsdOnboardingState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + " is not in ONBOARDED state.");
		}
	}

	public static void ensureNotOnboarded(final NsdInfo nsd) {
		if (NsdOnboardingStateType.ONBOARDED == nsd.getNsdOnboardingState()) {
			throw new ConflictException("The NSD package " + nsd.getId() + "is already ONBOARDED state.");
		}
	}

	public static void ensureNotInstantiated(final NsInstance nsInstance) {
		if (InstantiationStateEnum.INSTANTIATED == nsInstance.getNsState()) {
			throw new ConflictException("The Ns instance " + nsInstance.getId() + " is instantiated.");
		}
	}

	public static void ensureInstantiated(final NsInstance nsInstance) {
		if (InstantiationStateEnum.INSTANTIATED != nsInstance.getNsState()) {
			throw new GenericException("The Ns Instance " + nsInstance.getId() + " is instantiated.");
		}
	}

}
