package com.ubiqube.etsi.mano.service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.ExtVirtualLinkDataEntityJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedComputeJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedStorageJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedVirtualLinkJpa;
import com.ubiqube.etsi.mano.jpa.VnfLcmOpOccsJpa;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;

@Service
public class VnfInstanceService {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstanceService.class);

	private final ExtVirtualLinkDataEntityJpa extVirtualLinkDataEntityJpa;

	private final VnfInstantiedComputeJpa vnfInstantiedComputeJpa;

	private final VnfInstantiedVirtualLinkJpa vnfInstantiedVirtualLinkJpa;

	private final VnfInstantiedStorageJpa vnfInstantiedStorageJpa;

	private final VnfInstantiedExtCpJpa vnfInstantiedExtCpJpa;

	private final VnfInstanceJpa vnfInstanceJpa;

	private final VnfLcmOpOccsJpa vnfLcmOpOccsJpa;

	private final GrantService grantService;

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfInstanceService(final ExtVirtualLinkDataEntityJpa _extVirtualLinkDataEntityJpa, final VnfInstantiedComputeJpa _vnfInstantiedComputeJpa, final VnfInstantiedVirtualLinkJpa _vnfInstantiedVirtualLinkJpa, final VnfInstantiedExtCpJpa _vnfInstantiedExtCpJpa, final VnfInstantiedStorageJpa _vnfInstantiedStorageJpa, final VnfInstanceJpa _vnfInstanceJpa, final VnfLcmOpOccsJpa _vnfLcmOpOccsJpa, final GrantService _grantService, final VnfLiveInstanceJpa _vnfLiveInstance) {
		extVirtualLinkDataEntityJpa = _extVirtualLinkDataEntityJpa;
		vnfInstantiedComputeJpa = _vnfInstantiedComputeJpa;
		vnfInstantiedVirtualLinkJpa = _vnfInstantiedVirtualLinkJpa;
		vnfInstantiedExtCpJpa = _vnfInstantiedExtCpJpa;
		vnfInstantiedStorageJpa = _vnfInstantiedStorageJpa;
		vnfInstanceJpa = _vnfInstanceJpa;
		vnfLcmOpOccsJpa = _vnfLcmOpOccsJpa;
		grantService = _grantService;
		vnfLiveInstanceJpa = _vnfLiveInstance;
	}

	public List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance) {
		return extVirtualLinkDataEntityJpa.findByVnfInstance(vnfInstance);
	}

	public int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndVduId(vnfInstance, vnfCompute.getId());
	}

	public Deque<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.findByVduIdAndVnfInstance(vnfCompute.getId(), vnfInstance).stream().collect(Collectors.toCollection(ArrayDeque::new));
	}

	public int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndVduId(vnfInstance, x.getId());
	}

	public int getNumberOfLiveExtCp(final VnfInstance vnfInstance, final VnfExtCp extCp) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndVduId(vnfInstance, extCp.getId());
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
		return vnfInstantiedComputeJpa.findByLiveInstanceOfVnfInstance(vnfInstance);
	}

	public List<VnfInstantiatedExtCp> getLiveExtCpInstanceOf(final VnfInstance vnfInstance) {
		return vnfInstantiedExtCpJpa.findByLiveInstanceOfVnfInstance(vnfInstance);
	}

	public List<VnfInstantiatedStorage> getLiveStorageInstanceOf(final VnfInstance vnfInstance) {
		return vnfInstantiedStorageJpa.findByLiveInstanceOfVnfInstance(vnfInstance);
	}

	public List<VnfInstantiatedVirtualLink> getLiveVirtualLinkInstanceOf(final VnfInstance vnfInstance) {
		return vnfInstantiedVirtualLinkJpa.findByLiveInstanceOfVnfInstance(vnfInstance);
	}

	public VnfInstance findBVnfInstanceyVnfPackageId(final NsdInstance nsdInstance, final UUID vnfPackageId) {
		return vnfInstanceJpa.findByVnfPkg_IdAndNsInstance_Id(vnfPackageId, nsdInstance.getId()).orElseThrow(() -> new NotFoundException("Could not find vnf=" + vnfPackageId + " nsInstance=" + nsdInstance.getId()));
	}

	public VnfInstance findById(final UUID id) {
		return vnfInstanceJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find " + id));
	}

	public VnfInstance save(final VnfInstance vnfInstance) {
		return vnfInstanceJpa.save(vnfInstance);
	}

	@Transactional
	public void delete(final UUID vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstanceJpa.findById(vnfInstanceId).orElseThrow(() -> new NotFoundException("Vnf Instance " + vnfInstanceId + " not found."));
		final Set<VnfLcmOpOccs> lcmOpOccs = vnfLcmOpOccsJpa.findByVnfInstance(vnfInstance);
		lcmOpOccs.forEach(x -> {
			LOG.info("Deleting LcmOpOccs {}", x.getId());
			grantService.deleteByLcmOpOccs(x);
			vnfLcmOpOccsJpa.delete(x);
		});
		vnfInstanceJpa.deleteById(vnfInstanceId);
	}

}
