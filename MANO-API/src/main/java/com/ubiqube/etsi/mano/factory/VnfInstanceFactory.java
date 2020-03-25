package com.ubiqube.etsi.mano.factory;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;

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
	public static VnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance vnfInstance, final String vimId) {
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
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
