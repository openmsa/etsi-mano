package com.ubiqube.etsi.mano.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.AffectedVl;
import com.ubiqube.etsi.mano.dao.mano.AffectedVs;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.jpa.AffectedVlJpa;
import com.ubiqube.etsi.mano.jpa.AffectedVsJpa;
import com.ubiqube.etsi.mano.jpa.ExtVirtualLinkDataEntityJpa;
import com.ubiqube.etsi.mano.jpa.ResourceDataEntityJpa;
import com.ubiqube.etsi.mano.jpa.VnfExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedComputeJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedVirtualLinkJpa;
import com.ubiqube.etsi.mano.jpa.VnfVlJpa;

@Service
public class VnfInstanceService {
	private final ExtVirtualLinkDataEntityJpa extVirtualLinkDataEntityJpa;

	private final ResourceDataEntityJpa resourceDataEntityJpa;

	private final VnfInstantiedComputeJpa vnfInstantiedComputeJpa;

	private final VnfInstantiedVirtualLinkJpa vnfInstantiedVirtualLinkJpa;

	private final VnfVlJpa vnfVlJpa;

	private final VnfExtCpJpa vnfExtCpJpa;

	private final AffectedVsJpa affectedVsJpa;

	private final AffectedVlJpa affectedVlJpa;

	private final VnfInstantiedExtCpJpa vnfInstantiedExtCpJpa;

	public VnfInstanceService(final ExtVirtualLinkDataEntityJpa _extVirtualLinkDataEntityJpa, final ResourceDataEntityJpa _resourceDataEntityJpa, final VnfInstantiedComputeJpa _vnfInstantiedComputeJpa, final VnfVlJpa _vnfVlJpa, final VnfExtCpJpa _vnfExtCpJpa, final VnfInstantiedVirtualLinkJpa _vnfInstantiedVirtualLinkJpa, final VnfInstantiedExtCpJpa _vnfInstantiedExtCpJpa, final AffectedVsJpa _affectedVsJpa, final AffectedVlJpa _affectedVlJpa) {
		extVirtualLinkDataEntityJpa = _extVirtualLinkDataEntityJpa;
		resourceDataEntityJpa = _resourceDataEntityJpa;
		vnfInstantiedComputeJpa = _vnfInstantiedComputeJpa;
		vnfVlJpa = _vnfVlJpa;
		vnfExtCpJpa = _vnfExtCpJpa;
		vnfInstantiedVirtualLinkJpa = _vnfInstantiedVirtualLinkJpa;
		vnfInstantiedExtCpJpa = _vnfInstantiedExtCpJpa;
		affectedVsJpa = _affectedVsJpa;
		affectedVlJpa = _affectedVlJpa;
	}

	public List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance) {
		return extVirtualLinkDataEntityJpa.findByVnfInstance(vnfInstance);
	}

	public List<ResourceHandleEntity> getExtManagedVirtualLinks(final VnfInstance vnfInstance) {
		return resourceDataEntityJpa.findByVnfInstance(vnfInstance);
	}

	public int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfInstantiedComputeJpa.countByVnfInstanceAndVduId(vnfInstance, vnfCompute.getId());
	}

	public Deque<VnfInstantiedCompute> getLiveComputeInstanceOf(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		// XXX maybe we have a sort problem.
		return vnfInstantiedComputeJpa.findByVnfInstanceAndVduId(vnfInstance, vnfCompute.getId()).stream().collect(Collectors.toCollection(ArrayDeque::new));
	}

	public int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x) {
		// return vnfVlJpa.countByVnfInstanceAndVduId(vnfInstance, x.getId());
		return 0;
	}

	public int getNumberOfLiveExtCp(final VnfInstance vnfInstance, final VnfExtCp extCp) {
		// return vnfExtCpJpa.countByVnfInstanceAndVduId(vnfInstance, extCp.getId());
		return 0;
	}

	public VnfInstantiedCompute save(final VnfInstantiedCompute vnfInstantiedCompute) {
		return vnfInstantiedComputeJpa.save(vnfInstantiedCompute);
	}

	public VnfInstantiedVirtualLink save(final VnfInstantiedVirtualLink vnfInstantiedVirtualLink) {
		return vnfInstantiedVirtualLinkJpa.save(vnfInstantiedVirtualLink);
	}

	public VnfInstantiedExtCp save(final VnfInstantiedExtCp vnfInstantiedExtCp) {
		return vnfInstantiedExtCpJpa.save(vnfInstantiedExtCp);
	}

	public ResourceHandleEntity save(final ResourceHandleEntity networkResource) {
		return resourceDataEntityJpa.save(networkResource);
	}

	public AffectedVs save(final AffectedVs vs) {
		return affectedVsJpa.save(vs);
	}

	public AffectedVl save(final AffectedVl aVl) {
		return affectedVlJpa.save(aVl);
	}
}
