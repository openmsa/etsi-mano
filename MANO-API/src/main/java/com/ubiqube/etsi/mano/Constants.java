package com.ubiqube.etsi.mano;

import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOperationalStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;

public final class Constants {

	private Constants() {
		// Nothing.
	}

	public static final String HASH_ALGORITHM = "SHA-512";

	public static void ensureNotOnboarded(final VnfPkgInfo vnfPkgInfo) {
		if (!"CREATED".equals(vnfPkgInfo.getOnboardingState())) {
			throw new ConflictException("The VNF Package is already onboarded");
		}
	}

	public static void ensureDisabled(final VnfPkgInfo vnfPkgInfo) {
		if (!"DISABLED".equals(vnfPkgInfo.getOperationalState().value())) {
			throw new ConflictException("Packaged is enabled.");
		}
	}

	public static void ensureNotInUse(final VnfPkgInfo vnfPkgInfo) {
		if (!"NOT_IN_USE".equals(vnfPkgInfo.getUsageState())) {
			throw new ConflictException("VNF Should be in Not In Use State.");
		}
	}

	public static void ensureInstantiated(final VnfInstance vnfInstance) {
		if (vnfInstance.getInstantiationState() != InstantiationStateEnum.INSTANTIATED) {
			throw new GenericException("Instance " + vnfInstance.getId() + " is not instantiated.");
		}
	}

	public static void ensureIsEnabled(final VnfPkgInfo vnfPkgInfo) {
		if ("DISABLED".equals(vnfPkgInfo.getOperationalState().value())) {
			throw new ConflictException("VNF Package " + vnfPkgInfo.getId() + " is not ENABLED.");
		}
	}

	public static void ensureIsOnboarded(final VnfPkgInfo vnfPkgInfo) {
		if (!vnfPkgInfo.getOnboardingState().equals(OnboardingStateEnum.ONBOARDED.value())) {
			throw new ConflictException("VNF Package " + vnfPkgInfo.getId() + " is not ONBOARDED.");
		}
	}

	public static void ensureNotInstantiated(final VnfInstance vnfInstance) {
		if (vnfInstance.getInstantiationState() == InstantiationStateEnum.INSTANTIATED) {
			throw new GenericException("Instance " + vnfInstance.getId() + " is already instantiated.");
		}
	}

	public static void ensureNotInUse(final NsDescriptorsNsdInfo nsdInfo) {
		if (!"IN_USE".equals(nsdInfo.getNsdUsageState())) {
			throw new ConflictException("Nsd Should be disabled. " + nsdInfo.getId());
		}
	}

	public static void ensureDisabled(final NsDescriptorsNsdInfo nsdInfo) {
		if (!"DISABLED".equals(nsdInfo.getNsdOperationalState().value())) {
			throw new ConflictException("Nsd Should be disabled. " + nsdInfo.getId());
		}
	}

	public static void ensureOnboarded(final NsDescriptorsNsdInfo nsdInfo) {
		if (nsdInfo.getNsdOnboardingState().contentEquals(NsdOnboardingStateEnum.ONBOARDED.name())) {
			throw new ConflictException("NSD is already Onboarded.");
		}
	}

	public static void ensureNotOnboarded(final NsDescriptorsNsdInfo nsdInfo) {
		if (!nsdInfo.getNsdOnboardingState().contentEquals(NsdOnboardingStateEnum.ONBOARDED.name())) {
			throw new ConflictException("NSD is already Onboarded.");
		}
	}

	public static void ensureEnabled(final NsDescriptorsNsdInfo nsd) {
		if (!nsd.getNsdOperationalState().equals(NsdOperationalStateEnum.ENABLED.value())) {
			throw new ConflictException("NSD " + nsd.getId() + " is not ENABLED state.");
		}
	}

	public static void ensureOnborded(final NsDescriptorsNsdInfo nsd) {
		if (!nsd.getNsdOnboardingState().equals(NsdOnboardingStateEnum.ONBOARDED.value())) {
			throw new ConflictException("NSD " + nsd.getId() + " is not in OBBOARDED state.");
		}
	}

	public static void ensureNotInstantiatef(final NsInstancesNsInstance nsInstance) {
		if (NsStateEnum.INSTANTIATED.value().equals(nsInstance.getNsState())) {
			throw new ConflictException("The ns instance " + nsInstance.getId() + " is instantiated.");
		}
	}

	public static void ensureInstantiated(final NsInstancesNsInstance nsInstancesNsInstance) {
		if (nsInstancesNsInstance.getNsState().equals(NsStateEnum.INSTANTIATED.value())) {
			throw new GenericException("Ns Instance " + nsInstancesNsInstance.getId() + " is already instantiated.");
		}
	}

}
