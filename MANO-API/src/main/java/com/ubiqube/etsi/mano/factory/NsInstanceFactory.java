package com.ubiqube.etsi.mano.factory;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.CreateNsRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class NsInstanceFactory {

	private NsInstanceFactory() {
		// Nothing.
	}

	@Nonnull
	public static NsInstance createNsInstancesNsInstance(final CreateNsRequest nsInstancesCreateNsRequest, final NsdInfo nsd) {
		final NsInstance nsInstance = new NsInstance();
		nsInstance.setNsdId(nsInstancesCreateNsRequest.getNsdId());
		nsInstance.setNsInstanceDescription(nsInstancesCreateNsRequest.getNsDescription());
		nsInstance.setNsInstanceName(nsInstancesCreateNsRequest.getNsName());
		nsInstance.setNestedNsInstanceId(nsd.getNestedNsdInfoIds());
		nsInstance.setNsState(InstantiationStateEnum.NOT_INSTANTIATED);
		return nsInstance;
	}

	@Nonnull
	public static VnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance _vnfInstance, final VnfPkgInfo _vnfPkgInfo) {
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setId(_vnfInstance.getId());
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setVimId((String) _vnfPkgInfo.getUserDefinedData().get("vimId"));
		nsInstancesNsInstanceVnfInstance.setVnfdId(_vnfPkgInfo.getVnfdId());
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(_vnfPkgInfo.getVnfdVersion());
		nsInstancesNsInstanceVnfInstance.setVnfPkgId(_vnfPkgInfo.getId());
		return nsInstancesNsInstanceVnfInstance;
	}
}
