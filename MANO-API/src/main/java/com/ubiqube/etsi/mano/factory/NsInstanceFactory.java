package com.ubiqube.etsi.mano.factory;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVnfInstance;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class NsInstanceFactory {

	@Nonnull
	public static NsInstance createNsInstancesNsInstance(final String _nsdId, final String _description, final String _name, final List<String> nestedNsdInfoIds) {
		final NsInstance nsInstancesNsInstance = new NsInstance();
		nsInstancesNsInstance.setNsdId(_nsdId);
		nsInstancesNsInstance.setNsInstanceDescription(_description);
		nsInstancesNsInstance.setNsInstanceName(_name);
		nsInstancesNsInstance.setNestedNsInstanceId(nestedNsdInfoIds);
		nsInstancesNsInstance.setNsState(InstantiationStateEnum.NOT_INSTANTIATED);
		return nsInstancesNsInstance;
	}

	@Nonnull
	public static NsInstancesNsInstanceVnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance _vnfInstance, final VnfPkgInfo _vnfPkgInfo) {
		final String id = UUID.randomUUID().toString();
		final NsInstancesNsInstanceVnfInstance nsInstancesNsInstanceVnfInstance = new NsInstancesNsInstanceVnfInstance();
		nsInstancesNsInstanceVnfInstance.setId(_vnfInstance.getId());
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setVimId((String) _vnfPkgInfo.getUserDefinedData().get("vimId"));
		nsInstancesNsInstanceVnfInstance.setVnfdId(_vnfPkgInfo.getVnfdId());
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(_vnfPkgInfo.getVnfdVersion());
		nsInstancesNsInstanceVnfInstance.setVnfPkgId(_vnfPkgInfo.getId());
		return nsInstancesNsInstanceVnfInstance;
	}
}
