package com.ubiqube.etsi.mano.service;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedComputeJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedVirtualLinkJpa;
import com.ubiqube.etsi.mano.jpa.VnfLcmOpOccsJpa;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

@Service
public class VnfLcmService {
	private static final String COULD_NOT_FIND_COMPUTE_RESOURCE = "Could not find compute resource: ";

	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;

	private final VnfLcmOpOccsJpa vnfLcmOpOccsJpa;

	private final VnfInstantiedComputeJpa vnfInstantiedComputeJpa;

	private final VnfInstantiedVirtualLinkJpa vnfInstantiedVirtualLinkJpa;

	private final VnfInstantiedExtCpJpa vnfInstantiedExtCpJpa;

	public VnfLcmService(final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final VnfLcmOpOccsJpa _vnfLcmOpOccsJpa, final VnfInstantiedComputeJpa _vnfInstantiedCompute, final VnfInstantiedVirtualLinkJpa _vnfInstantiedVirtualLink, final VnfInstantiedExtCpJpa _vnfInstantiedExtCp) {
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		vnfLcmOpOccsJpa = _vnfLcmOpOccsJpa;
		vnfInstantiedComputeJpa = _vnfInstantiedCompute;
		vnfInstantiedVirtualLinkJpa = _vnfInstantiedVirtualLink;
		vnfInstantiedExtCpJpa = _vnfInstantiedExtCp;
	}

	@Nonnull
	public VnfLcmOpOccs createIntatiateOpOcc(final VnfInstance vnfInstance) {
		return createIntatiateTerminateOpOcc(vnfInstance, LcmOperationType.INSTANTIATE);
	}

	@Nonnull
	public VnfLcmOpOccs createTerminateOpOcc(final VnfInstance vnfInstance) {
		return createIntatiateTerminateOpOcc(vnfInstance, LcmOperationType.TERMINATE);
	}

	@Nonnull
	private VnfLcmOpOccs createIntatiateTerminateOpOcc(final VnfInstance vnfInstance, final LcmOperationType state) {
		final VnfLcmOpOccs lcmOpOccs = LcmFactory.createVnfLcmOpOccs(state, vnfInstance.getId());
		return vnfLcmOpOccsRepository.save(lcmOpOccs);
	}

	@Deprecated
	public void updateState(final @Nonnull VnfLcmOpOccs vnfLcmOpOccs, final LcmOperationStateType newState) {
		// XXX Use an update method in a Repository.
		vnfLcmOpOccsRepository.updateState(vnfLcmOpOccs, newState);
	}

	public void setGrant(final @Nonnull VnfLcmOpOccs vnfLcmOpOccs, final UUID grantId) {
		vnfLcmOpOccs.setGrantId(grantId.toString());
		vnfLcmOpOccsRepository.save(vnfLcmOpOccs);
	}

	@SuppressWarnings("null")
	@Nonnull
	public VnfLcmOpOccs findById(final UUID id) {
		return vnfLcmOpOccsJpa.findById(id).orElseThrow(() -> new NotFoundException(COULD_NOT_FIND_COMPUTE_RESOURCE + id));
	}

	@SuppressWarnings("null")
	@Nonnull
	public VnfLcmOpOccs save(final VnfLcmOpOccs lcmOpOccs) {
		return vnfLcmOpOccsJpa.save(lcmOpOccs);
	}

	public Optional<VnfInstantiatedCompute> getInstatiedComputeById(final UUID id) {
		return vnfInstantiedComputeJpa.findById(id);
	}

	public Optional<VnfInstantiatedVirtualLink> getInstatiedVirtualLinkById(final UUID id) {
		return vnfInstantiedVirtualLinkJpa.findById(id);
	}

	public Optional<VnfInstantiatedExtCp> getInstatiedExtCpById(final UUID id) {
		return vnfInstantiedExtCpJpa.findById(id);
	}
}
