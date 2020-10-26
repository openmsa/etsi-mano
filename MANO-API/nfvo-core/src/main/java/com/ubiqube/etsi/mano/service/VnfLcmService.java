package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.OperateChanges;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.jpa.BlueprintJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedComputeJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedExtCpJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedVirtualLinkJpa;
import com.ubiqube.etsi.mano.jpa.VnfLcmOpOccsJpa;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

@Service
public class VnfLcmService {
	private static final String COULD_NOT_FIND_COMPUTE_RESOURCE = "Could not find compute resource: ";

	private final VnfLcmOpOccsJpa vnfLcmOpOccsJpa;

	private final VnfInstantiedComputeJpa vnfInstantiedComputeJpa;

	private final VnfInstantiedVirtualLinkJpa vnfInstantiedVirtualLinkJpa;

	private final VnfInstantiedExtCpJpa vnfInstantiedExtCpJpa;

	private final BlueprintJpa planJpa;

	private final EntityManager em;

	public VnfLcmService(final VnfLcmOpOccsJpa _vnfLcmOpOccsJpa, final VnfInstantiedComputeJpa _vnfInstantiedCompute, final VnfInstantiedVirtualLinkJpa _vnfInstantiedVirtualLink, final VnfInstantiedExtCpJpa _vnfInstantiedExtCp, final BlueprintJpa _planJpa, final EntityManager _em) {
		vnfLcmOpOccsJpa = _vnfLcmOpOccsJpa;
		vnfInstantiedComputeJpa = _vnfInstantiedCompute;
		vnfInstantiedVirtualLinkJpa = _vnfInstantiedVirtualLink;
		vnfInstantiedExtCpJpa = _vnfInstantiedExtCp;
		planJpa = _planJpa;
		em = _em;
	}

	@Nonnull
	public Blueprint createIntatiateOpOcc(final VnfInstance vnfInstance) {
		return createIntatiateTerminateBlueprint(vnfInstance, PlanOperationType.INSTANTIATE);
	}

	@Nonnull
	public Blueprint createTerminateOpOcc(final VnfInstance vnfInstance) {
		return createIntatiateTerminateBlueprint(vnfInstance, PlanOperationType.TERMINATE);
	}

	@Nonnull
	private Blueprint createIntatiateTerminateBlueprint(final VnfInstance vnfInstance, final PlanOperationType state) {
		final Blueprint blueprint = LcmFactory.createBlueprint(state, vnfInstance.getId());
		return planJpa.save(blueprint);
	}

	@Nonnull
	public VnfLcmOpOccs findById(final UUID id) {
		return vnfLcmOpOccsJpa.findById(id).orElseThrow(() -> new NotFoundException(COULD_NOT_FIND_COMPUTE_RESOURCE + id));
	}

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

	public Blueprint createScaleToLevelOpOcc(final VnfInstance vnfInstance, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		final Blueprint lcmOpOccs = LcmFactory.createVnfLcmOpOccs(PlanOperationType.SCALE_TO_LEVEL, vnfInstance.getId());
		lcmOpOccs.getParameters().setInstantiationLevelId(scaleVnfToLevelRequest.getInstantiationLevelId());
		if (scaleVnfToLevelRequest.getScaleInfo() != null) {
			final Set<ScaleInfo> scaleStatus = scaleVnfToLevelRequest.getScaleInfo().stream()
					.map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel()))
					.collect(Collectors.toSet());
			lcmOpOccs.getParameters().setScaleStatus(scaleStatus);
		}
		return planJpa.save(lcmOpOccs);
	}

	public Blueprint createScaleOpOcc(final VnfInstance vnfInstance, final VnfScaleRequest scaleVnfRequest) {
		final Blueprint lcmOpOccs = LcmFactory.createVnfLcmOpOccs(PlanOperationType.SCALE, vnfInstance.getId());
		lcmOpOccs.getParameters().setNumberOfSteps(scaleVnfRequest.getNumberOfSteps());
		lcmOpOccs.getParameters().setScaleType(scaleVnfRequest.getType());
		lcmOpOccs.getParameters().setAspectId(scaleVnfRequest.getAspectId());
		final Set<ScaleInfo> scaleStatus = vnfInstance.getInstantiatedVnfInfo().getScaleStatus().stream()
				.filter(x -> x.getAspectId().equals(scaleVnfRequest.getAspectId()))
				.map(x -> new ScaleInfo(x.getAspectId(), addDec(scaleVnfRequest.getType(), scaleVnfRequest.getNumberOfSteps(), x.getScaleLevel())))
				.collect(Collectors.toSet());
		lcmOpOccs.getParameters().setScaleStatus(scaleStatus);
		return planJpa.save(lcmOpOccs);
	}

	private static int addDec(@NotNull final ScaleTypeEnum type, final int numberOfSteps, final int scaleLevel) {
		switch (type) {
		case IN:
			return Math.max(0, scaleLevel - numberOfSteps);
		case OUT:
			return scaleLevel + numberOfSteps;
		default:
			throw new GenericException("Scaling value is incorrect: " + type);
		}
	}

	public Blueprint createOperateOpOcc(final VnfInstance vnfInstance, final VnfOperateRequest operateVnfRequest) {
		final Blueprint lcmOpOccs = LcmFactory.createVnfLcmOpOccs(PlanOperationType.OPERATE, vnfInstance.getId());
		final OperateChanges opChanges = lcmOpOccs.getOperateChanges();
		opChanges.setTerminationType(OperationalStateType.fromValue(operateVnfRequest.getChangeStateTo().toString()));
		opChanges.setGracefulTerminationTimeout(operateVnfRequest.getGracefulStopTimeout());
		return planJpa.save(lcmOpOccs);
	}

	public List<Blueprint> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<Blueprint>) sq.getCriteria(filter, Blueprint.class)
				.getResultStream().collect(Collectors.toList());
	}
}
