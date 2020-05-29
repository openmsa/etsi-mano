package com.ubiqube.etsi.mano.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.ExtVirtualLinkDataEntityJpa;
import com.ubiqube.etsi.mano.jpa.VnfExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedComputeJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedStorageJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedVirtualLinkJpa;
import com.ubiqube.etsi.mano.jpa.VnfVlJpa;

@Service
public class VnfInstanceService {
	private final ExtVirtualLinkDataEntityJpa extVirtualLinkDataEntityJpa;

	private final VnfInstantiedComputeJpa vnfInstantiedComputeJpa;

	private final VnfInstantiedVirtualLinkJpa vnfInstantiedVirtualLinkJpa;

	private final VnfInstantiedStorageJpa vnfInstantiedStorageJpa;

	private final VnfVlJpa vnfVlJpa;

	private final VnfExtCpJpa vnfExtCpJpa;

	private final VnfInstantiedExtCpJpa vnfInstantiedExtCpJpa;
	private final VnfInstanceJpa vnfInstanceJpa;

	public VnfInstanceService(final ExtVirtualLinkDataEntityJpa _extVirtualLinkDataEntityJpa, final VnfInstantiedComputeJpa _vnfInstantiedComputeJpa, final VnfVlJpa _vnfVlJpa, final VnfExtCpJpa _vnfExtCpJpa, final VnfInstantiedVirtualLinkJpa _vnfInstantiedVirtualLinkJpa, final VnfInstantiedExtCpJpa _vnfInstantiedExtCpJpa, final VnfInstantiedStorageJpa _vnfInstantiedStorageJpa, final VnfInstanceJpa _vnfInstanceJpa) {
		extVirtualLinkDataEntityJpa = _extVirtualLinkDataEntityJpa;
		vnfInstantiedComputeJpa = _vnfInstantiedComputeJpa;
		vnfVlJpa = _vnfVlJpa;
		vnfExtCpJpa = _vnfExtCpJpa;
		vnfInstantiedVirtualLinkJpa = _vnfInstantiedVirtualLinkJpa;
		vnfInstantiedExtCpJpa = _vnfInstantiedExtCpJpa;
		vnfInstantiedStorageJpa = _vnfInstantiedStorageJpa;
		vnfInstanceJpa = _vnfInstanceJpa;
	}

	public List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance) {
		return extVirtualLinkDataEntityJpa.findByVnfInstance(vnfInstance);
	}

	public int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfInstantiedComputeJpa.countByVnfLcmOpOccsVnfInstanceAndVduId(vnfInstance, vnfCompute.getId());
	}

	public Deque<VnfInstantiatedCompute> getLiveComputeInstanceOf(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		// XXX maybe we have a sort problem.
		return vnfInstantiedComputeJpa.findByVnfLcmOpOccsVnfInstanceAndVduId(vnfInstance, vnfCompute.getId()).stream().collect(Collectors.toCollection(ArrayDeque::new));
	}

	public int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x) {
		// return vnfVlJpa.countByVnfInstanceAndVduId(vnfInstance, x.getId());
		return 0;
	}

	public int getNumberOfLiveExtCp(final VnfInstance vnfInstance, final VnfExtCp extCp) {
		// return vnfExtCpJpa.countByVnfInstanceAndVduId(vnfInstance, extCp.getId());
		return 0;
	}

	public VnfInstantiatedCompute save(final VnfInstantiatedCompute vnfInstantiedCompute) {
		return vnfInstantiedComputeJpa.save(vnfInstantiedCompute);
	}

	public VnfInstantiatedVirtualLink save(final VnfInstantiatedVirtualLink vnfInstantiedVirtualLink) {
		return vnfInstantiedVirtualLinkJpa.save(vnfInstantiedVirtualLink);
	}

	public VnfInstantiatedExtCp save(final VnfInstantiatedExtCp vnfInstantiedExtCp) {
		return vnfInstantiedExtCpJpa.save(vnfInstantiedExtCp);
	}

	public VnfInstantiatedStorage save(final VnfInstantiatedStorage vs) {
		return vnfInstantiedStorageJpa.save(vs);
	}

	public int getNumberOfLiveStorage(final VnfInstance vnfInstance, final VnfStorage x) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<VnfInstantiatedCompute> getLiveComputeInstanceOf(final VnfInstance vnfInstance) {
		return vnfInstantiedComputeJpa.findAllByVnfLcmOpOccs_VnfInstance(vnfInstance);
	}

	public List<VnfInstantiatedExtCp> getLiveExtCpInstanceOf(final VnfInstance vnfInstance) {
		return vnfInstantiedExtCpJpa.findAllByVnfLcmOpOccs_VnfInstance(vnfInstance);
	}

	public List<VnfInstantiatedStorage> getLiveStorageInstanceOf(final VnfInstance vnfInstance) {
		return vnfInstantiedStorageJpa.findAllByVnfLcmOpOccs_VnfInstance(vnfInstance);
	}

	public List<VnfInstantiatedVirtualLink> getLiveVirtualLinkInstanceOf(final VnfInstance vnfInstance) {
		return vnfInstantiedVirtualLinkJpa.findAllByVnfLcmOpOccs_VnfInstance(vnfInstance);
	}

	public VnfInstance findBVnfInstanceyVnfPackageId(final UUID id) {
		return vnfInstanceJpa.findByVnfPkg_Id(id).orElseThrow(() -> new NotFoundException("Could not find " + id));
	}

	public VnfInstance findById(final UUID id) {
		return vnfInstanceJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find " + id));
	}

	public VnfInstance save(final VnfInstance vnfInstance) {
		return vnfInstanceJpa.save(vnfInstance);
	}

}
