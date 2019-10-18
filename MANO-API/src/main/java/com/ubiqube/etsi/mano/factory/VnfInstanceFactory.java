package com.ubiqube.etsi.mano.factory;

import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVnfInstance;

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
	public static NsInstancesNsInstanceVnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance vnfInstance, final String vimId) {
		final NsInstancesNsInstanceVnfInstance nsInstancesNsInstanceVnfInstance = new NsInstancesNsInstanceVnfInstance();
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setId(vnfInstance.getId());
		nsInstancesNsInstanceVnfInstance.setVimId(vimId);
		nsInstancesNsInstanceVnfInstance.setVnfdId(vnfInstance.getVnfdId());
		nsInstancesNsInstanceVnfInstance.setVnfInstanceDescription(vnfInstance.getVnfInstanceDescription());
		nsInstancesNsInstanceVnfInstance.setVnfInstanceName(vnfInstance.getVnfInstanceName());
		nsInstancesNsInstanceVnfInstance.setVnfPkgId(vnfInstance.getVnfPkgId());
		nsInstancesNsInstanceVnfInstance.setVnfProductName(vnfInstance.getVnfProductName());
		nsInstancesNsInstanceVnfInstance.setVnfProvider(vnfInstance.getVnfProvider());
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(vnfInstance.getVnfdVersion());
		nsInstancesNsInstanceVnfInstance.setVnfSoftwareVersion(vnfInstance.getVnfSoftwareVersion());
		return nsInstancesNsInstanceVnfInstance;
	}
}
