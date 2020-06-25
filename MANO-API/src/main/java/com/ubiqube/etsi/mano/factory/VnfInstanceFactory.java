package com.ubiqube.etsi.mano.factory;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;

public class VnfInstanceFactory {
	private VnfInstanceFactory() {
		// Nothing
	}

	/**
	 * Duplicate of NsInstanceFactory.
	 *
	 * @param vnfInstance
	 * @param vimId
	 * @return
	 */
	@Nonnull
	public static VnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance vnfInstance, final String vimId) {
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setId(vnfInstance.getId());
		nsInstancesNsInstanceVnfInstance.setVnfdId(vnfInstance.getVnfdId());
		nsInstancesNsInstanceVnfInstance.setVnfInstanceDescription(vnfInstance.getVnfInstanceDescription());
		nsInstancesNsInstanceVnfInstance.setVnfInstanceName(vnfInstance.getVnfInstanceName());
		nsInstancesNsInstanceVnfInstance.setVnfPkg(vnfInstance.getVnfPkg());
		nsInstancesNsInstanceVnfInstance.setVnfProductName(vnfInstance.getVnfProductName());
		nsInstancesNsInstanceVnfInstance.setVnfProvider(vnfInstance.getVnfProvider());
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(vnfInstance.getVnfdVersion());
		nsInstancesNsInstanceVnfInstance.setVnfSoftwareVersion(vnfInstance.getVnfSoftwareVersion());
		return nsInstancesNsInstanceVnfInstance;
	}
}
