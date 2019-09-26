package com.ubiqube.etsi.mano.factory;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVnfInstance.InstantiationStateEnum;

public class NsInstanceFactory {

	@Nonnull
	public static NsInstancesNsInstance createNsInstancesNsInstance(final String _nsdId, final String _description, final String _name, final List<String> nestedNsdInfoIds) {
		final NsInstancesNsInstance nsInstancesNsInstance = new NsInstancesNsInstance();
		nsInstancesNsInstance.setNsdId(_nsdId);
		nsInstancesNsInstance.setNsInstanceDescription(_description);
		nsInstancesNsInstance.setNsInstanceName(_name);
		nsInstancesNsInstance.setNestedNsInstanceId(nestedNsdInfoIds);
		nsInstancesNsInstance.setNsState(NsStateEnum.NOT_INSTANTIATED);
		return nsInstancesNsInstance;
	}

	@Nonnull
	public static NsInstancesNsInstanceVnfInstance createNsInstancesNsInstanceVnfInstance(final String _vnfInstanceId, final String _vimId, final String _vnfdId, final String _vnfdVersion, final String _vnfPkgid) {
		final String id = UUID.randomUUID().toString();
		final NsInstancesNsInstanceVnfInstance nsInstancesNsInstanceVnfInstance = new NsInstancesNsInstanceVnfInstance();
		nsInstancesNsInstanceVnfInstance.setId(_vnfInstanceId);
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setVimId(_vimId);
		nsInstancesNsInstanceVnfInstance.setVnfdId(_vnfdId);
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(_vnfdVersion);
		nsInstancesNsInstanceVnfInstance.setVnfPkgId(_vnfPkgid);
		return nsInstancesNsInstanceVnfInstance;
	}
}
