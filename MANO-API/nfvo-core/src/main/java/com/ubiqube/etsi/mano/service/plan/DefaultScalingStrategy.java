package com.ubiqube.etsi.mano.service.plan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.BlueprintService;

@Service
public class DefaultScalingStrategy implements ScalingStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultScalingStrategy.class);

	private final BlueprintService planService;

	public DefaultScalingStrategy(final BlueprintService _planService) {
		planService = _planService;
	}

	@Override
	public NumberOfCompute getNumberOfCompute(final Blueprint blueprint, final VnfPackage vnfPakage, final Set<ScaleInfo> scaling, final VnfCompute x) {
		final String instantiationLevelId = blueprint.getParameters().getInstantiationLevelId();
		Set<VnfInstantiationLevels> instantiationLevels = vnfPakage.getVnfInstantiationLevels();
		if (null != instantiationLevelId) {
			// Get Instantiation levels or baseLine levels..
			instantiationLevels = resolvLevelName(instantiationLevelId, 0, vnfPakage.getVnfInstantiationLevels());
			// filter using tis vnfc.
			instantiationLevels = instantiationLevels.stream()
					.filter(y -> match(x, y))
					.collect(Collectors.toSet());
		}
		// Filter myScaling.
		ScaleInfo myscaling = new ScaleInfo("whatEver", 0);
		if (null != scaling) {
			final Set<ScaleInfo> myscalings = scaling.stream()
					.filter(y -> match(x, y))
					.collect(Collectors.toSet());
			if (myscalings.size() > 1) {
				throw new GenericException("VDU " + x.getToscaName() + " have multiple scalings.");
			} else if (!myscalings.isEmpty()) {
				myscaling = myscalings.iterator().next();
			}
		}
		if (!instantiationLevels.isEmpty()) {
			final int currentInst = planService.getNumberOfLiveInstance(blueprint.getVnfInstance(), x);
			final int wantedNumInst = getNumberOfInstance(instantiationLevels, x, instantiationLevelId, myscaling);
			LOG.info("{}: Actual currentInst={} wantedInst={}", x.getToscaName(), currentInst, wantedNumInst);
			return new NumberOfCompute(currentInst, wantedNumInst);
		}
		return new NumberOfCompute(0, 0);
	}

	public static Set<VnfInstantiationLevels> resolvLevelName(final String instantiationLevel, final int level, final Set<VnfInstantiationLevels> vnfInstantiationLevels) {
		return vnfInstantiationLevels.stream()
				.filter(x -> instantiationLevel.equals(x.getLevelName()))
				.filter(x -> x.getScaleInfoLevel() <= level)
				.sorted(Comparator.comparingInt(VnfInstantiationLevels::getScaleInfoLevel))
				.collect(Collectors.toSet());
	}

	private static boolean match(final VnfCompute vnfCompute, final ScaleInfo scaleInfo) {
		return !vnfCompute.getScalingAspectDeltas().stream()
				.filter(x -> x.getAspectName().equals(scaleInfo.getAspectId()))
				.collect(Collectors.toList()).isEmpty();
	}

	public static int getNumberOfInstance(final Set<VnfInstantiationLevels> vnfInstantiationLevels, final VnfCompute vnfCompute, final String instantiationLevel, final ScaleInfo myscaling) {
		if (null == instantiationLevel) {
			return Optional.ofNullable(vnfCompute.getInitialNumberOfInstance()).orElse(Integer.valueOf(1)).intValue();
		}
		// Get base level or 1 instance.
		final VduInstantiationLevel il = vnfCompute.getInstantiationLevel().stream()
				.filter(x -> x.getLevelName().equals(instantiationLevel))
				.findFirst()
				.orElse(new VduInstantiationLevel(1));
		final int base = il.getNumberOfInstances();
		// Get Delta per levels.
		final List<VnfComputeAspectDelta> vnfComputeAspectDeltas = new ArrayList<>();
		for (final VnfComputeAspectDelta vnfComputeAspectDelta : vnfCompute.getScalingAspectDeltas()) {
			final List<VnfInstantiationLevels> instLev = vnfInstantiationLevels.stream()
					.filter(y -> vnfComputeAspectDelta.getAspectName().equals(y.getScaleInfoName()))
					.collect(Collectors.toList());
			if (instLev.isEmpty()) {
				continue;
			}
			vnfComputeAspectDeltas.add(vnfComputeAspectDelta);
		}
		int cnt = 0;
		int apply = 0;
		// Apply delta.
		VnfComputeAspectDelta last = new VnfComputeAspectDelta("", "", 1, 1, 1, null);
		for (final VnfComputeAspectDelta vnfComputeAspectDelta : vnfComputeAspectDeltas) {
			if (vnfComputeAspectDelta.getLevel() <= myscaling.getScaleLevel()) {
				cnt += vnfComputeAspectDelta.getNumberOfInstances();
				last = vnfComputeAspectDelta;
				apply++;
			}
		}
		final int maxLevel = Math.min(myscaling.getScaleLevel(), last.getMaxScaleLevel());
		for (int i = apply; i < maxLevel; i++) {
			cnt += last.getNumberOfInstances();
		}
		return base + cnt;
	}

	private static boolean match(final VnfCompute vnfCompute, final VnfInstantiationLevels vil) {
		if (null == vnfCompute.getScalingAspectDeltas()) {
			return false;
		}
		return !vnfCompute.getScalingAspectDeltas().stream()
				.filter(x -> x.getAspectName().equals(vil.getScaleInfoName()))
				.collect(Collectors.toList()).isEmpty();
	}
}