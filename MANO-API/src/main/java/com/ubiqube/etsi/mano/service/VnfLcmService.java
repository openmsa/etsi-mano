package com.ubiqube.etsi.mano.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.AffectedCompute;
import com.ubiqube.etsi.mano.dao.mano.AffectedExtCp;
import com.ubiqube.etsi.mano.dao.mano.AffectedVl;
import com.ubiqube.etsi.mano.dao.mano.AffectedVs;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmResourceChanges;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.jpa.AffectedComputeJpa;
import com.ubiqube.etsi.mano.jpa.AffectedExtCpJpa;
import com.ubiqube.etsi.mano.jpa.AffectedVlJpa;
import com.ubiqube.etsi.mano.jpa.ResourceHandleEntityJpa;
import com.ubiqube.etsi.mano.jpa.VnfLcmOpOccsJpa;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

@Service
public class VnfLcmService {
	private static final String COULD_NOT_FIND_COMPUTE_RESOURCE = "Could not find compute resource: ";

	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;

	private final VnfLcmOpOccsJpa vnfLcmOpOccsJpa;

	private final ResourceHandleEntityJpa resourceHandleEntityJpa;

	private final AffectedComputeJpa affectedComputeJpa;

	private final AffectedVlJpa affectedVlJpa;

	private final AffectedExtCpJpa affectedExtCpJpa;

	public VnfLcmService(final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final VnfLcmOpOccsJpa _vnfLcmOpOccsJpa, final ResourceHandleEntityJpa _resourceHandleEntityJpa, final AffectedComputeJpa _affectedComputeJpa, final AffectedVlJpa _affectedVlJpa, final AffectedExtCpJpa _affectedExtCpJpa) {
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		vnfLcmOpOccsJpa = _vnfLcmOpOccsJpa;
		resourceHandleEntityJpa = _resourceHandleEntityJpa;
		affectedComputeJpa = _affectedComputeJpa;
		affectedVlJpa = _affectedVlJpa;
		affectedExtCpJpa = _affectedExtCpJpa;
	}

	@Nonnull
	public VnfLcmOpOccs createIntatiateOpOcc(final VnfPackage vnfPackage, final VnfInstance vnfInstance) {
		return createIntatiateTerminateOpOcc(vnfPackage, vnfInstance, LcmOperationType.INSTANTIATE);
	}

	@Nonnull
	public VnfLcmOpOccs createTerminateOpOcc(final VnfPackage vnfPackage, final VnfInstance vnfInstance) {
		return createIntatiateTerminateOpOcc(vnfPackage, vnfInstance, LcmOperationType.TERMINATE);
	}

	@Nonnull
	private VnfLcmOpOccs createIntatiateTerminateOpOcc(final VnfPackage vnfPackage, final VnfInstance vnfInstance, final LcmOperationType state) {
		final VnfLcmOpOccs lcmOpOccs = LcmFactory.createVnfLcmOpOccs(state, vnfInstance.getId());
		copyVnfPkgToLcm(vnfPackage, lcmOpOccs);
		copyVnfInstanceToLcmOpOccs(vnfInstance, lcmOpOccs);
		return vnfLcmOpOccsRepository.save(lcmOpOccs);
	}

	public void updateState(final @Nonnull VnfLcmOpOccs vnfLcmOpOccs, final LcmOperationStateType newState) {
		// XXX Use an update method in a Repository.
		vnfLcmOpOccsRepository.updateState(vnfLcmOpOccs, LcmOperationStateType.PROCESSING);
	}

	public void setGrant(final @Nonnull VnfLcmOpOccs vnfLcmOpOccs, final UUID grantId) {
		vnfLcmOpOccs.setGrantId(grantId.toString());
		vnfLcmOpOccsRepository.save(vnfLcmOpOccs);
	}

	private static void copyVnfPkgToLcm(final VnfPackage vnfPkg, final VnfLcmOpOccs lcmOpOccs) {
		final VnfLcmResourceChanges changedResources = lcmOpOccs.getResourceChanges();

		vnfPkg.getVnfCompute().forEach(x -> {
			final AffectedCompute affectedCompute = new AffectedCompute();
			affectedCompute.setAddedStorageResourceIds(x.getStorages());
			// XXX affectedCompute.setAffectedVnfcCpIds(affectedVnfcCpIds);
			affectedCompute.setChangeType(ChangeType.ADDED);
			affectedCompute.setVduId(x.getId());
			affectedCompute.setVnfCompute(x);
			changedResources.addAffectedVnfcs(affectedCompute);
		});
		vnfPkg.getVnfVl().forEach(x -> {
			final AffectedVl affectedVirtualLink = new AffectedVl();
			affectedVirtualLink.setChangeType(ChangeType.ADDED);
			affectedVirtualLink.setVirtualLinkDesc(x);
			changedResources.addAffectedVirtualLink(affectedVirtualLink);
		});
		vnfPkg.getVnfStorage().forEach(x -> {
			final AffectedVs affectedVs = new AffectedVs();
			affectedVs.setChangeType(ChangeType.ADDED);
			affectedVs.setVirtualStorageDesc(x);
			changedResources.addAffectedVirtualStorage(affectedVs);
		});
	}

	private static void copyVnfInstanceToLcmOpOccs(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		final VnfInstantiatedInfo inst = vnfInstance.getInstantiatedVnfInfo();
		inst.getVnfcResourceInfo().forEach(x -> {
			final AffectedCompute affected = findLcmOpOccsCompute(lcmOpOccs.getResourceChanges().getAffectedVnfcs(), x.getVduId());
			// XXX affected.setComputeResource(x.getCompResource());
		});

		inst.getVirtualLinkResourceInfo().forEach(x -> {
			final AffectedVl affected = findLcmOpOccsVl(lcmOpOccs.getResourceChanges().getAffectedVirtualLinks(), x.getVnfVirtualLinkDescId());
			affected.setNetworkResource(x.getNetworkResource());
		});

		inst.getVirtualStorageResourceInfo().forEach(x -> {
			final AffectedVs affected = findLcmOpOccsStorage(lcmOpOccs.getResourceChanges().getAffectedVirtualStorages(), x.getVirtualStorageDescId());
			affected.setStorageResource(x.getStorageResource());
		});
	}

	@Nonnull
	private static AffectedVs findLcmOpOccsStorage(final Set<AffectedVs> affectedVirtualStorages, final UUID id) {
		return affectedVirtualStorages.stream()
				.filter(x -> x.getVirtualStorageDesc().getId().compareTo(id) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException(COULD_NOT_FIND_COMPUTE_RESOURCE + id));
	}

	@Nonnull
	private static AffectedVl findLcmOpOccsVl(final Set<AffectedVl> affectedVirtualLinks, final UUID id) {
		return affectedVirtualLinks.stream()
				.filter(x -> x.getVirtualLinkDesc().getId().compareTo(id) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException(COULD_NOT_FIND_COMPUTE_RESOURCE + id));
	}

	@Nonnull
	private static AffectedCompute findLcmOpOccsCompute(final Set<AffectedCompute> affectedVnfcs, final UUID id) {
		return affectedVnfcs.stream()
				.filter(x -> x.getVduId().compareTo(id) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException(COULD_NOT_FIND_COMPUTE_RESOURCE + id));
	}

	@Nonnull
	public VnfLcmOpOccs findById(final UUID id) {
		return vnfLcmOpOccsJpa.findById(id).orElseThrow(() -> new NotFoundException(COULD_NOT_FIND_COMPUTE_RESOURCE + id));
	}

	@Nonnull
	public VnfLcmOpOccs save(final VnfLcmOpOccs lcmOpOccs) {
		return vnfLcmOpOccsJpa.save(lcmOpOccs);
	}

	@Nonnull
	public ResourceHandleEntity save(final ResourceHandleEntity res) {
		return resourceHandleEntityJpa.save(res);
	}

	public Optional<AffectedCompute> getAffectedComputeById(final UUID uuId) {
		return affectedComputeJpa.findById(uuId);
	}

	public Optional<AffectedVl> getAffectedVirtualLinkByVdu(final UUID uuid) {
		return affectedVlJpa.findByVirtualLinkDescId(uuid);
	}

	public Optional<AffectedExtCp> getAffectedExtCpByVdu(final UUID uuid) {
		final VnfExtCp vnfExtCp = new VnfExtCp();
		vnfExtCp.setId(uuid);
		return affectedExtCpJpa.findByExtCp(vnfExtCp);

	}

	public Optional<AffectedExtCp> getAffectedExtCpById(final UUID fromString) {
		return affectedExtCpJpa.findById(fromString);
	}

	public Optional<AffectedVl> getAffectedVirtualLinkById(final UUID fromString) {
		return affectedVlJpa.findById(fromString);
	}
}
