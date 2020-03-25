package com.ubiqube.etsi.mano.factory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.CreateNsRequest;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class NsInstanceFactory {

	private NsInstanceFactory() {
		// Nothing.
	}

	@Nonnull
	public static NsdInstance createNsInstancesNsInstance(final CreateNsRequest nsInstancesCreateNsRequest, final NsdInfo nsd) {
		final NsdInstance nsInstance = new NsdInstance();
		nsInstance.setNsdId(nsInstancesCreateNsRequest.getNsdId());
		nsInstance.setNsInstanceDescription(nsInstancesCreateNsRequest.getNsDescription());
		nsInstance.setNsInstanceName(nsInstancesCreateNsRequest.getNsName());
		final List<NsdInstance> l = nsd.getNestedNsdInfoIds().stream().map(x -> {
			final NsdInstance r = new NsdInstance();
			r.setId(UUID.fromString(x));
			return r;
		}).collect(Collectors.toList());
		nsInstance.setNestedNsInstance(l);
		nsInstance.setNsState(InstantiationStateEnum.NOT_INSTANTIATED);
		return nsInstance;
	}

	@Nonnull
	public static VnfInstance createNsInstancesNsInstanceVnfInstance(final VnfInstance _vnfInstance, final VnfPkgInfo _vnfPkgInfo) {
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setId(_vnfInstance.getId());
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setVnfdId(_vnfPkgInfo.getVnfdId());
		nsInstancesNsInstanceVnfInstance.setVnfdVersion(_vnfPkgInfo.getVnfdVersion());
		final VnfPackage vnfPackage = new VnfPackage();
		vnfPackage.setId(UUID.fromString(_vnfPkgInfo.getId()));
		nsInstancesNsInstanceVnfInstance.setVnfPkg(vnfPackage);
		return nsInstancesNsInstanceVnfInstance;
	}
}
