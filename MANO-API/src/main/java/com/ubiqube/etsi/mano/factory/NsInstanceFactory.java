package com.ubiqube.etsi.mano.factory;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.CreateNsRequest;

public class NsInstanceFactory {

	private NsInstanceFactory() {
		// Nothing.
	}

	@Nonnull
	public static NsdInstance createNsInstancesNsInstance(final CreateNsRequest nsInstancesCreateNsRequest, final NsdPackage nsd) {
		final NsdInstance nsInstance = new NsdInstance();
		nsInstance.setNsdId(nsInstancesCreateNsRequest.getNsdId());
		nsInstance.setNsInstanceDescription(nsInstancesCreateNsRequest.getNsDescription());
		nsInstance.setNsInstanceName(nsInstancesCreateNsRequest.getNsName());
		final List<NsdInstance> l = nsd.getNestedNsdInfoIds().stream().map(x -> {
			final NsdInstance r = new NsdInstance();
			r.setId(x.getId());
			return r;
		}).collect(Collectors.toList());
		nsInstance.setNestedNsInstance(l);
		nsInstance.setNsState(InstantiationStateEnum.NOT_INSTANTIATED);
		return nsInstance;
	}

	@Nonnull
	public static VnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance _vnfInstance, final VnfPackage _vnfPkgInfo) {
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setId(_vnfInstance.getId());
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setVnfdId(_vnfPkgInfo.getVnfdId());
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(_vnfPkgInfo.getVnfdVersion());
		final VnfPackage vnfPackage = new VnfPackage();
		vnfPackage.setId(_vnfPkgInfo.getId());
		nsInstancesNsInstanceVnfInstance.setVnfPkg(vnfPackage);
		return nsInstancesNsInstanceVnfInstance;
	}
}
