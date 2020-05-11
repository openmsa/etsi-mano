package com.ubiqube.etsi.mano.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.jpa.ExtVirtualLinkDataEntityJpa;
import com.ubiqube.etsi.mano.jpa.ResourceDataEntityJpa;

@Service
public class VnfInstanceService {
	private final ExtVirtualLinkDataEntityJpa extVirtualLinkDataEntityJpa;

	private final ResourceDataEntityJpa resourceDataEntityJpa;

	public VnfInstanceService(final ExtVirtualLinkDataEntityJpa _extVirtualLinkDataEntityJpa, final ResourceDataEntityJpa _resourceDataEntityJpa) {
		extVirtualLinkDataEntityJpa = _extVirtualLinkDataEntityJpa;
		resourceDataEntityJpa = _resourceDataEntityJpa;
	}

	public List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance) {
		return extVirtualLinkDataEntityJpa.findByVnfInstance(vnfInstance);
	}

	public List<ResourceHandleEntity> getExtManagedVirtualLinks(final VnfInstance vnfInstance) {
		return resourceDataEntityJpa.findByVnfInstance(vnfInstance);
	}
}
