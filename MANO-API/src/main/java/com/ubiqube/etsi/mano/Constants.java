package com.ubiqube.etsi.mano;

import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOperationalStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;

public final class Constants {

	private Constants() {
		// Nothing.
	}

	public static final String HASH_ALGORITHM = "SHA-512";

	public static void ensureNotOnboarded(final VnfPkgInfo vnfPkgInfo) {
		if (!OnboardingStateEnum.CREATED.value().equals(vnfPkgInfo.getOnboardingState())) {
			throw new ConflictException("The VNF Package is already onboarded");
		}
	}

	public static void ensureDisabled(final VnfPkgInfo vnfPkgInfo) {
		if (OperationalStateEnum.DISABLED != vnfPkgInfo.getOperationalState()) {
			throw new ConflictException("Packaged is enabled.");
		}
	}

	public static void ensureNotInUse(final VnfPkgInfo vnfPkgInfo) {
		if (!UsageStateEnum.NOT_IN_USE.value().equals(vnfPkgInfo.getUsageState())) {
			throw new ConflictException("VNF Should be in Not In Use State.");
		}
	}

	public static void ensureIsEnabled(final VnfPkgInfo vnfPkgInfo) {
		if (OperationalStateEnum.DISABLED == vnfPkgInfo.getOperationalState()) {
			throw new ConflictException("VNF Package " + vnfPkgInfo.getId() + " is not ENABLED.");
		}
	}

	public static void ensureIsOnboarded(final VnfPkgInfo vnfPkgInfo) {
		if (!OnboardingStateEnum.ONBOARDED.value().equals(vnfPkgInfo.getOnboardingState())) {
			throw new ConflictException("VNF Package " + vnfPkgInfo.getId() + " is not ONBOARDED.");
		}
	}

	public static void ensureInstantiated(final VnfInstance vnfInstance) {
		if (InstantiationStateEnum.INSTANTIATED != vnfInstance.getInstantiationState()) {
			throw new GenericException("Instance " + vnfInstance.getId() + " is not instantiated.");
		}
	}

	public static void ensureNotInstantiated(final VnfInstance vnfInstance) {
		if (InstantiationStateEnum.INSTANTIATED == vnfInstance.getInstantiationState()) {
			throw new GenericException("Instance " + vnfInstance.getId() + " is already instantiated.");
		}
	}

	public static void ensureNotInUse(final NsDescriptorsNsdInfo nsd) {
		if (!NsdUsageStateEnum.IN_USE.value().equals(nsd.getNsdUsageState())) {
			throw new ConflictException("Nsd Should be disabled. " + nsd.getId());
		}
	}

	public static void ensureDisabled(final NsDescriptorsNsdInfo nsd) {
		if (NsdOperationalStateEnum.DISABLED != nsd.getNsdOperationalState()) {
			throw new ConflictException("Nsd Should be disabled. " + nsd.getId());
		}
	}

	public static void ensureOnboarded(final NsDescriptorsNsdInfo nsd) {
		if (NsdOnboardingStateEnum.ONBOARDED.value().equals(nsd.getNsdOnboardingState())) {
			throw new ConflictException("NSD is already Onboarded.");
		}
	}

	public static void ensureNotOnboarded(final NsDescriptorsNsdInfo nsd) {
		if (!NsdOnboardingStateEnum.ONBOARDED.value().equals(nsd.getNsdOnboardingState())) {
			throw new ConflictException("NSD is already Onboarded.");
		}
	}

	public static void ensureEnabled(final NsDescriptorsNsdInfo nsd) {
		if (NsdOperationalStateEnum.ENABLED != nsd.getNsdOperationalState()) {
			throw new ConflictException("NSD " + nsd.getId() + " is not ENABLED state.");
		}
	}

	public static void ensureOnborded(final NsDescriptorsNsdInfo nsd) {
		if (!NsdOnboardingStateEnum.ONBOARDED.value().equals(nsd.getNsdOnboardingState())) {
			throw new ConflictException("NSD " + nsd.getId() + " is not in OBBOARDED state.");
		}
	}

	public static void ensureNotInstantiated(final NsInstancesNsInstance nsInstance) {
		if (NsStateEnum.INSTANTIATED.value().equals(nsInstance.getNsState())) {
			throw new ConflictException("The Ns instance " + nsInstance.getId() + " is instantiated.");
		}
	}

	public static void ensureInstantiated(final NsInstancesNsInstance nsInstance) {
		if (NsStateEnum.INSTANTIATED.value().equals(nsInstance.getNsState())) {
			throw new GenericException("The Ns Instance " + nsInstance.getId() + " is instantiated.");
		}
	}

}
