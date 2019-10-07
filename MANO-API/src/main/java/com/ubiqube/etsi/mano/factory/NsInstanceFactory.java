package com.ubiqube.etsi.mano.factory;

import java.util.List;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVnfInstance;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class NsInstanceFactory {

	private NsInstanceFactory() {
		// Nothing.
	}

	@Nonnull
	public static NsInstance createNsInstancesNsInstance(final String _nsdId, final String _description, final String _name, final List<String> nestedNsdInfoIds) {
		final NsInstance nsInstance = new NsInstance();
		nsInstance.setNsdId(_nsdId);
		nsInstance.setNsInstanceDescription(_description);
		nsInstance.setNsInstanceName(_name);
		nsInstance.setNestedNsInstanceId(nestedNsdInfoIds);
		nsInstance.setNsState(InstantiationStateEnum.NOT_INSTANTIATED);
		return nsInstance;
	}

	@Nonnull
	public static NsInstancesNsInstanceVnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance _vnfInstance, final VnfPkgInfo _vnfPkgInfo) {
		final NsInstancesNsInstanceVnfInstance VnfInstance = new NsInstancesNsInstanceVnfInstance();
		VnfInstance.setId(_vnfInstance.getId());
		VnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		VnfInstance.setVimId((String) _vnfPkgInfo.getUserDefinedData().get("vimId"));
		VnfInstance.setVnfdId(_vnfPkgInfo.getVnfdId());
		VnfInstance.setVnfdVersion(_vnfPkgInfo.getVnfdVersion());
		VnfInstance.setVnfPkgId(_vnfPkgInfo.getId());
		return VnfInstance;
	}
}
