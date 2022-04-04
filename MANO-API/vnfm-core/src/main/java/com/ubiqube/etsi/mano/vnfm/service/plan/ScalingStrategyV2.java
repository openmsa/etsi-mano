/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.vnfm.service.plan;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.NsScaleType;
import com.ubiqube.etsi.mano.vnfm.service.VnfBlueprintService;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class ScalingStrategyV2 implements ScalingStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(ScalingStrategyV2.class);
	private final VnfBlueprintService planService;

	public ScalingStrategyV2(final VnfBlueprintService planService) {
		super();
		this.planService = planService;
	}

	@Override
	public NumberOfCompute getNumberOfCompute(final VnfBlueprint plan, final VnfPackage vnfPackage, final Set<ScaleInfo> scaling, final VnfCompute compute, final VnfInstance instance) {
		if (plan.getOperation() == PlanOperationType.INSTANTIATE) {
			return handleInstantiate(plan, vnfPackage, instance, compute);
		}
		return handleScale(plan, vnfPackage, compute, instance);
	}

	private NumberOfCompute handleInstantiate(final VnfBlueprint plan, final VnfPackage vnfPackage, final VnfInstance instance, final VnfCompute compute) {
		final String level = Optional.ofNullable(plan.getParameters().getInstantiationLevelId()).orElseGet(vnfPackage::getDefaultInstantiationLevel);
		final Optional<VduInstantiationLevel> newLevel = compute.getInstantiationLevel().stream().filter(x -> x.getLevelName().equals(level)).findFirst();
		if (newLevel.isPresent()) {
			return new NumberOfCompute(0, newLevel.get().getNumberOfInstances(), null);
		}
		return new NumberOfCompute(0, 1, null);
	}

	private NumberOfCompute handleScale(final VnfBlueprint plan, final VnfPackage vnfPackage, final VnfCompute compute, final VnfInstance vnfInstance) {
		switch (getScalingType(plan.getParameters())) {
		case NS_SCALE_LEVEL_INST_LEVEL:
			return handleInstantiationLevel(plan.getParameters().getInstantiationLevelId(), vnfPackage, compute);
		case NS_SCALE_LEVEL_SCALE_INFO:
			return handleScaleInfo(plan.getParameters().getScaleStatus(), vnfPackage, compute);
		case NS_SCALE_STEP:
			return handleStep(plan.getParameters(), vnfPackage, compute, vnfInstance);
		default:
			throw new IllegalArgumentException("Unexpected value: " + getScalingType(plan.getParameters()));
		}
	}

	private NumberOfCompute handleStep(final BlueprintParameters parameters, final VnfPackage vnfPackage, final VnfCompute compute, final VnfInstance instance) {
		final int currentInst = planService.getNumberOfLiveInstance(instance, compute);
		final int baseStep = getBaseStep(instance, parameters.getAspectId());
		final List<VnfComputeAspectDelta> stepMapping = findStepMapping(parameters.getAspectId(), compute);
		if (stepMapping.isEmpty()) {
			final Set<String> uniqAspect = vnfPackage.getScaleStatus().stream().map(ScaleInfo::getAspectId).collect(Collectors.toSet());
			if (uniqAspect.isEmpty()) {
				return new NumberOfCompute(currentInst, 1, new ScaleInfo(parameters.getAspectId(), 0));
			}
			if (uniqAspect.size() > 1) {
				LOG.warn("There is multiple aspectId, taking the first one: {}", uniqAspect);
			}
			final String currentAspect = uniqAspect.iterator().next();
			final Optional<ScaleInfo> instanceLevel = instance.getInstantiatedVnfInfo().getScaleStatus().stream().filter(x -> x.getAspectId().equals(currentAspect)).findFirst();
			final List<VnfComputeAspectDelta> instStep = findStepMapping(currentAspect, compute);
			if (instStep.isEmpty()) {
				LOG.warn("Could not find step mapping for aspectId: {}", currentAspect);
				return new NumberOfCompute(currentInst, 1, new ScaleInfo(parameters.getAspectId(), 1));
			}
			if (instanceLevel.isEmpty()) {
				final int s = getStep(instStep, 0);
				return new NumberOfCompute(currentInst, s, new ScaleInfo(parameters.getAspectId(), s));
			}
			final int s = getStep(instStep, instanceLevel.get().getScaleLevel());
			return new NumberOfCompute(currentInst, s, new ScaleInfo(parameters.getAspectId(), s));
		}
		final int newLevel = computeLevel(parameters, baseStep);
		final int s = getStep(stepMapping, newLevel);
		return new NumberOfCompute(currentInst, s, new ScaleInfo(parameters.getAspectId(), s));
	}

	@SuppressWarnings("boxing")
	private static int computeLevel(final BlueprintParameters param, final Integer base) {
		if (param.getScaleType() == ScaleTypeEnum.IN) {
			final int ret = base - param.getNumberOfSteps();
			if (ret < 0) {
				return 0;
			}
			return ret;
		}
		return base + param.getNumberOfSteps();
	}

	private static NumberOfCompute handleScaleInfo(final Set<ScaleInfo> scaleStatus, final VnfPackage vnfPackage, final VnfCompute compute) {
		final List<ScaleInfo> aspect = scaleStatus.stream()
				.filter(x -> contains(x, compute.getScalingAspectDeltas()))
				.toList();
		if (aspect.isEmpty()) {
			// Rebuild initial.
		}
		final VnfComputeAspectDelta scaleInfo = aspect.stream().map(x -> find(x.getScaleLevel(), compute.getScalingAspectDeltas())).findFirst().orElseThrow();
		final int s = scaleInfo.getNumberOfInstances();
		return new NumberOfCompute(0, s, new ScaleInfo(scaleInfo.getLevel() + "", s));
	}

	private static VnfComputeAspectDelta find(final int scaleLevel, final Set<VnfComputeAspectDelta> scalingAspectDeltas) {
		return scalingAspectDeltas.stream().filter(x -> x.getLevel() == scaleLevel).findFirst().orElseThrow();
	}

	private static boolean contains(final ScaleInfo scaleInfo, final Set<VnfComputeAspectDelta> scalingAspectDeltas) {
		return scalingAspectDeltas.stream().anyMatch(x -> x.getAspectName().equals(scaleInfo.getAspectId()));
	}

	private static NumberOfCompute handleInstantiationLevel(final String instantiationLevelId, final VnfPackage bundle, final VnfCompute compute) {
		final Optional<VduInstantiationLevel> scaleInfo = compute.getInstantiationLevel().stream().filter(x -> x.getLevelName().equals(instantiationLevelId)).findFirst();
		if (scaleInfo.isEmpty()) {
			final Integer s = compute.getInitialNumberOfInstance();
			return new NumberOfCompute(0, s, new ScaleInfo(scaleInfo.get().getLevelName(), s));
		}
		final int s = scaleInfo.get().getNumberOfInstances();
		return new NumberOfCompute(0, s, new ScaleInfo(scaleInfo.get().getLevelName(), s));
	}

	private static NsScaleType getScalingType(final BlueprintParameters params) {
		if (params.getInstantiationLevelId() != null) {
			return NsScaleType.NS_SCALE_LEVEL_INST_LEVEL;
		}
		if (null != params.getAspectId()) {
			return NsScaleType.NS_SCALE_STEP;
		}
		if (null != params.getScaleStatus()) {
			return NsScaleType.NS_SCALE_LEVEL_INST_LEVEL;
		}
		throw new GenericException("Unknown scaling mode.");
	}

	@SuppressWarnings("boxing")
	private static int getBaseStep(final VnfInstance instance, final String aspectId) {
		final Set<ScaleInfo> nsScale = instance.getInstantiatedVnfInfo().getScaleStatus();
		if (null == nsScale) {
			return 0;
		}
		return nsScale.stream()
				.filter(x -> x.getAspectId().equals(aspectId))
				.map(ScaleInfo::getScaleLevel)
				.findFirst()
				.orElse(0);
	}

	private static List<VnfComputeAspectDelta> findStepMapping(final String aspectId, final VnfCompute vnfCompute) {
		return vnfCompute.getScalingAspectDeltas().stream()
				.filter(x -> x.getAspectName().equals(aspectId))
				.toList();
	}

	private static int getStep(final List<VnfComputeAspectDelta> vnfComputeAspectDelta, final int i) {
		int instNum = 1;
		final List<VnfComputeAspectDelta> sorted = vnfComputeAspectDelta.stream().sorted(Comparator.comparing(VnfComputeAspectDelta::getLevel)).toList();
		for (final VnfComputeAspectDelta stepMapping : sorted) {
			final int curr = stepMapping.getLevel();
			if (curr == i) {
				return stepMapping.getNumInst();
			}
			if (curr <= i) {
				instNum = stepMapping.getNumInst();
			}
			if (curr > i) {
				return instNum;
			}
		}
		return instNum;
	}
}
