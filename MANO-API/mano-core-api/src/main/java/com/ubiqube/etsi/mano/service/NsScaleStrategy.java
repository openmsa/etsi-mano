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
package com.ubiqube.etsi.mano.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.Levelable;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScale;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScaleLevel;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScleStepMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsByStepsData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsToLevelData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleType;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScalingDirectionType;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.StepMapping;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsScaleStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(NsScaleStrategy.class);

	public int getNumberOfInstances(final NsdPackageVnfPackage nsPackageVnfPackage, final NsBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.INSTANTIATE) {
			return handleInstantiate(nsPackageVnfPackage, blueprint);
		}
		if (blueprint.getOperation() == PlanOperationType.SCALE) {
			return handleScaleToLevel(nsPackageVnfPackage, blueprint);
		}
		return 0;
	}

	public int getNumberOfInstances(final NsdPackageNsdPackage nsPackageNsPackage, final NsBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.INSTANTIATE) {
			return handleInstantiate(nsPackageNsPackage, blueprint);
		}
		if (blueprint.getOperation() == PlanOperationType.SCALE) {
			return handleScaleToLevel(nsPackageNsPackage, blueprint);
		}
		return 0;
	}

	/**
	 * Find default number of instance of a given VNF.
	 *
	 * @param nsPackageNsPackage
	 * @param blueprint
	 * @return number of instances.
	 */
	@SuppressWarnings({ "static-method", "boxing" })
	private int handleInstantiate(final Levelable<? extends NsScleStepMapping, ? extends NsScaleLevel> nsPackageNsPackage, final NsBlueprint blueprint) {
		final String defaultInstLevel = getInstantiateLevel(blueprint);
		if (null == defaultInstLevel) {
			LOG.warn("No default instantiation level for NSD {}, creating one instance.", blueprint.getNsInstance().getId());
			return 1;
		}
		final Set<? extends NsScaleLevel> lvlMapping = nsPackageNsPackage.getLevelMapping();
		if (null == lvlMapping || lvlMapping.isEmpty()) {
			return 1;
		}
		return lvlMapping.stream()
				.filter(y -> y.getAspectId().equals(defaultInstLevel))
				.findFirst()
				.map(NsScaleLevel::getNumberOfInstance)
				.orElseGet(() -> {
					LOG.warn("Unable to find aspectId named: {}", defaultInstLevel);
					return 1;
				});
	}

	private static String getInstantiateLevel(final NsBlueprint blueprint) {
		return Optional.ofNullable(blueprint.getNsInstantiationLevelId()).orElse(blueprint.getInstance().getNsInstantiationLevelId());
	}

	private static int handleScaleToLevel(final Levelable<? extends NsScleStepMapping, ? extends NsScaleLevel> nsPackageVnfPackage, final NsBlueprint blueprint) {
		final NsScale scaleDetails = Optional.ofNullable(blueprint.getParameters())
				.map(BlueprintParameters::getNsScale).orElseThrow(() -> new GenericException("Ns scale details is not available."));
		if (scaleDetails.getScaleType() == ScaleType.VNF) {
			throw new GenericException("NS VNF scaling is not supported.");
		}
		return getNumberOfVnf(nsPackageVnfPackage, blueprint.getInstance(), scaleDetails.getScaleNsData());
	}

	public static int getNumberOfVnf(final Levelable<? extends NsScleStepMapping, ? extends NsScaleLevel> vnfPackage, final NsdInstance instance, final ScaleNsData scaleNsData) {
		if (null != scaleNsData.getScaleNsByStepsData()) {
			return getByStepData(scaleNsData.getScaleNsByStepsData(), vnfPackage, instance);
		}
		return getByLevel(scaleNsData.getScaleNsToLevelData(), vnfPackage);
	}

	private static int getByLevel(final ScaleNsToLevelData scaleNsToLevelData, final Levelable<? extends NsScleStepMapping, ? extends NsScaleLevel> vnfPackage) {
		if (scaleNsToLevelData.getNsInstantiationLevel() != null) {
			return byInstantiationLevel(scaleNsToLevelData.getNsInstantiationLevel(), vnfPackage);
		}
		final List<? extends NsScaleLevel> inPkg = vnfPackage.getLevelMapping().stream().filter(x -> containsScaleInfo(x.getAspectId(), scaleNsToLevelData.getNsScaleInfo())).toList();
		if (inPkg.isEmpty()) {
			throw new GenericException("");
		}
		return inPkg.get(0).getNumberOfInstance();
	}

	private static int byInstantiationLevel(final String nsInstantiationLevel, final Levelable<? extends NsScleStepMapping, ? extends NsScaleLevel> vnfPackage) {
		final List<? extends NsScaleLevel> l = vnfPackage.getLevelMapping().stream().filter(x -> x.getAspectId().equals(nsInstantiationLevel)).toList();
		if (l.isEmpty()) {
			throw new GenericException("Unable to find level " + nsInstantiationLevel);
		}
		return l.get(0).getNumberOfInstance();
	}

	private static boolean containsScaleInfo(final String aspectId, final Set<NsScaleInfo> nsScaleInfo) {
		return nsScaleInfo.stream().anyMatch(x -> x.getNsScalingAspectId().equals(aspectId));
	}

	@SuppressWarnings("boxing")
	private static int getByStepData(final ScaleNsByStepsData scaleNsByStepsData, final Levelable<? extends NsScleStepMapping, ? extends NsScaleLevel> vnfPackage, final NsdInstance instance) {
		final int baseStep = getBaseStep(instance, scaleNsByStepsData.getAspectId());
		final Optional<? extends NsScleStepMapping> stepMapping = findStepMapping(scaleNsByStepsData.getAspectId(), vnfPackage);
		if (stepMapping.isEmpty()) {
			final Set<String> uniqAspect = vnfPackage.getStepMapping().stream().map(NsScleStepMapping::getAspectId).collect(Collectors.toSet());
			if (uniqAspect.isEmpty()) {
				return 1;
			}
			if (uniqAspect.size() > 1) {
				LOG.warn("There is multiple aspectId, taking the first one: {}", uniqAspect);
			}
			final String currentAspect = uniqAspect.iterator().next();
			final Optional<ScaleInfo> instanceLevel = instance.getInstantiatedVnfInfo().getNsStepStatus().stream().filter(x -> x.getAspectId().equals(currentAspect)).findFirst();
			final Optional<? extends NsScleStepMapping> instStep = findStepMapping(currentAspect, vnfPackage);
			if (instStep.isEmpty()) {
				LOG.warn("Could not find step mapping for aspectId: {}", currentAspect);
				return 1;
			}
			if (instanceLevel.isEmpty()) {
				return getStep(instStep.get().getLevels(), 0);
			}
			return getStep(instStep.get().getLevels(), instanceLevel.get().getScaleLevel());
		}
		final int newLevel = computeLevel(scaleNsByStepsData, baseStep);
		return getStep(stepMapping.get().getLevels(), newLevel);
	}

	private static Optional<? extends NsScleStepMapping> findStepMapping(final String aspectId, final Levelable<? extends NsScleStepMapping, ? extends NsScaleLevel> vnfPackage) {
		return vnfPackage.getStepMapping().stream()
				.filter(x -> x.getAspectId().equals(aspectId))
				.findFirst();
	}

	@SuppressWarnings("boxing")
	private static int getBaseStep(final NsdInstance instance, final String aspectId) {
		final Set<ScaleInfo> nsScale = instance.getInstantiatedVnfInfo().getNsStepStatus();
		if (null == nsScale) {
			return 0;
		}
		return nsScale.stream()
				.filter(x -> x.getAspectId().equals(aspectId))
				.map(ScaleInfo::getScaleLevel)
				.findFirst()
				.orElse(0);
	}

	private static int getStep(final Set<StepMapping> set, final int i) {
		int instNum = 1;
		final List<StepMapping> sorted = set.stream().sorted(Comparator.comparing(StepMapping::getName)).toList();
		for (final StepMapping stepMapping : sorted) {
			final int curr = stepMapping.getName();
			if (curr == i) {
				return stepMapping.getNumberOfInstance();
			}
			if (curr <= i) {
				instNum = stepMapping.getNumberOfInstance();
			}
			if (curr > i) {
				return instNum;
			}
		}
		return instNum;
	}

	@SuppressWarnings("boxing")
	private static int computeLevel(final ScaleNsByStepsData scaleData, final Integer base) {
		if (scaleData.getScalingDirection() == ScalingDirectionType.IN) {
			return base - scaleData.getNumberOfSteps();
		}
		return base + scaleData.getNumberOfSteps();
	}

	@SuppressWarnings("static-method")
	public NsScaleType getScalingType(final NsScale scaleInfo) {
		if (scaleInfo.getScaleType() == ScaleType.VNF) {
			return NsScaleType.VNF;
		}
		if (null != scaleInfo.getScaleNsData().getScaleNsByStepsData()) {
			return NsScaleType.NS_SCALE_STEP;
		}
		final ScaleNsToLevelData levels = scaleInfo.getScaleNsData().getScaleNsToLevelData();
		if (null != levels.getNsInstantiationLevel()) {
			return NsScaleType.NS_SCALE_LEVEL_INST_LEVEL;
		}
		return NsScaleType.NS_SCALE_LEVEL_SCALE_INFO;
	}

	@SuppressWarnings("static-method")
	public Set<NsScaleInfo> getScaleInfo(final NsScale nsInst) {
		return nsInst.getScaleNsData().getScaleNsToLevelData().getNsScaleInfo();
	}

	public void remapNsScale(final NsScale scalingData, final Instance instance) {
		final NsScaleType type = getScalingType(scalingData);
		switch (type) {
		case NS_SCALE_LEVEL_INST_LEVEL -> handleLevelInstLevel(instance, scalingData.getScaleNsData().getScaleNsToLevelData().getNsInstantiationLevel());
		case NS_SCALE_LEVEL_SCALE_INFO -> handleLevelScaleInfo(instance, scalingData.getScaleNsData().getScaleNsToLevelData().getNsScaleInfo());
		case NS_SCALE_STEP -> handleStep(instance, scalingData.getScaleNsData().getScaleNsByStepsData());
		default -> LOG.warn("Type {} is not supported.", type);
		}
	}

	private static void handleStep(final Instance instance, final ScaleNsByStepsData scaleNsByStepsData) {
		final BlueprintParameters instInfo = instance.getInstantiatedVnfInfo();
		final ScaleInfo oldScaleInfo = instInfo.getNsStepStatus().stream().filter(x -> x.getAspectId().equals(scaleNsByStepsData.getAspectId())).findAny().orElseGet(() -> {
			final ScaleInfo n = new ScaleInfo(scaleNsByStepsData.getAspectId(), 0);
			instInfo.getNsStepStatus().add(n);
			return n;
		});
		if (scaleNsByStepsData.getScalingDirection() == ScalingDirectionType.IN) {
			final int scaleLevel = oldScaleInfo.getScaleLevel() - scaleNsByStepsData.getNumberOfSteps();
			oldScaleInfo.setScaleLevel(scaleLevel < 0 ? 0 : scaleLevel);
		} else {
			final int scaleLevel = oldScaleInfo.getScaleLevel() + scaleNsByStepsData.getNumberOfSteps();
			oldScaleInfo.setScaleLevel(scaleLevel);
		}
	}

	private static void handleLevelScaleInfo(final Instance instance, final Set<NsScaleInfo> nsScaleInfo) {
		instance.getInstantiatedVnfInfo().setNsScaleStatus(nsScaleInfo);
	}

	private static void handleLevelInstLevel(final Instance instance, final String nsInstantiationLevel) {
		instance.getInstantiatedVnfInfo().setInstantiationLevelId(nsInstantiationLevel);
	}

}
