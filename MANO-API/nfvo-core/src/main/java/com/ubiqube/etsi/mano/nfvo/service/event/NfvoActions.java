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
package com.ubiqube.etsi.mano.nfvo.service.event;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScale;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsVnfScalingStepMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleByStepData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsByStepsData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsToLevelData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleToLevelData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleType;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleVnfData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScalingDirectionType;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.StepMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VnfScaleType;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VnfScalingStepMapping;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsdTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsOrchestrationAdapter;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsWorkflow;
import com.ubiqube.etsi.mano.nfvo.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.service.NsScaleStrategy;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.event.AbstractGenericAction;
import com.ubiqube.etsi.mano.service.event.OrchestrationAdapter;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.rest.ManoClientFactory;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NfvoActions extends AbstractGenericAction {
	private OrchestrationAdapter<?, ?> orchestrationAdapter;
	NsLiveInstanceJpa nsLiveInstanceJpa;
	ManoClientFactory manoClientFactory;

	public NfvoActions(final NsWorkflow workflow, final VimResourceService vimResourceService, final NsOrchestrationAdapter orchestrationAdapter, final NsScaleStrategy nsScaleStrategy) {
		super(workflow, vimResourceService, orchestrationAdapter, nsScaleStrategy);
	}

	@Override
	protected GenericExecParams buildContext(final VimConnectionInformation vimConnection, final Vim vim, final Blueprint blueprint, final Instance vnfInstance) {
		final Map<String, String> context = blueprint.getParameters().getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		// Add all present VL if any.
		return new NsParameters(vim, vimConnection, context, null);
	}

	public void nsInstantiate(final UUID objectId) {
		//
	}

	public void nsInstantiateInner(final NsBlueprint blueprint, final NsdInstance instance) {
		final BlueprintParameters params = instance.getInstantiatedVnfInfo();
		final PackageBase vnfPkg = orchestrationAdapter.getPackage(instance);
		final Set<ScaleInfo> newScale = nsMerge(blueprint, instance);
	}

	private Set<ScaleInfo> nsMerge(final NsBlueprint blueprint, final NsdInstance instance) {
		final BlueprintParameters params = blueprint.getParameters();
		final NsScale nsScale = params.getNsScale();
		if (ScaleType.NS == nsScale.getScaleType()) {
			return nsScale(blueprint, instance, nsScale.getScaleNsData());
		}
		return vnfScale(blueprint, instance, nsScale.getScaleVnfData());
	}

	private Set<ScaleInfo> vnfScale(final NsBlueprint blueprint, final NsdInstance instance, final Set<ScaleVnfData> scaleVnfData) {
		scaleVnfData.forEach(x -> {
			final UUID uuid = UUID.fromString(x.getVnfInstanceId());
			final NsLiveInstance inst = nsLiveInstanceJpa.findById(uuid).orElseThrow(() -> new GenericException("Could not find VNF instance: " + x.getVnfInstanceId()));
			// OUT, IN, TO_INSTANTIATION_LEVEL, TO_SCALE_LEVEL_S_
			if (x.getScaleVnfType() == VnfScaleType.OUT || x.getScaleVnfType() == VnfScaleType.IN) {
				final ScaleTypeEnum type = x.getScaleVnfType() == VnfScaleType.OUT ? ScaleTypeEnum.OUT : ScaleTypeEnum.IN;
				vnfScaleStep(uuid, type, x.getScaleByStepData());
			}
			vnfScaleLevel(uuid, x.getScaleToLevelData());
		});
		return null;
	}

	private void vnfScaleLevel(final UUID vnfInstanceId, final ScaleToLevelData scaleData) {
		final VnfScaleToLevelRequest scaleVnfToLevelRequest = VnfScaleToLevelRequest.of(scaleData);
		manoClientFactory.getClient().vnfInstance(vnfInstanceId).scaleToLevel(scaleVnfToLevelRequest);
	}

	private void vnfScaleStep(final UUID vnfInstanceId, final ScaleTypeEnum scaleType, final ScaleByStepData scaleData) {
		final VnfScaleRequest req = VnfScaleRequest.of(scaleType, scaleData);
	}

	private Set<ScaleInfo> nsScale(final NsBlueprint blueprint, final NsdInstance instance, final ScaleNsData scaleNsData) {
		final ScaleNsByStepsData stepData = scaleNsData.getScaleNsByStepsData();
		if (null == stepData) {
			nsScaleByLevel(blueprint, instance, scaleNsData);
		}
		nsScaleByStep(blueprint, instance, scaleNsData);
		final ScaleNsByStepsData step = scaleNsData.getScaleNsByStepsData();

		return null;
	}

	private void nsScaleByStep(final NsBlueprint blueprint, final NsdInstance instance, final ScaleNsData scaleNsData) {
		final ScaleNsByStepsData scaleData = scaleNsData.getScaleNsByStepsData();
		final List<NsdPackageVnfPackage> concernedVnf = findConcernedVnf(instance.getNsdInfo(), scaleData.getAspectId());
		final NsScaleInfo current = findCuurentScaleInfo(instance.getInstantiatedVnfInfo().getNsScaleStatus(), scaleData.getAspectId());
		final int level = computeLevel(scaleData, Integer.valueOf(current.getNsScaleLevelId()));
		final List<NsLiveInstance> vi = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsVnfTask.class.getSimpleName());
		concernedVnf.forEach(x -> {
			final int wanted = getNumberOfInstance(x, scaleData.getAspectId(), level);
			final List<NsVnfTask> liveInstances = vi.stream().map(NsLiveInstance::getNsTask).map(NsVnfTask.class::cast).filter(y -> y.getNsPackageVnfPackage().getId() == x.getId()).toList();
			if (wanted == liveInstances.size()) {
				return;
			}

		});
		final List<NsdPackageNsdPackage> concernedNS = findConcernedNs(instance.getNsdInfo(), scaleData.getAspectId());
		concernedNS.forEach(x -> {
			final int wanted = getNumberOfInstance(x, scaleData.getAspectId(), level);
			final List<NsdTask> liveInstances = vi.stream().map(NsLiveInstance::getNsTask).map(NsdTask.class::cast).filter(y -> y.getNsdId() == x.getId()).toList();
			if (wanted == liveInstances.size()) {
				return;
			}

		});
		findConcernedVl(instance.getNsdInfo(), scaleData.getAspectId());
	}

	private static int getNumberOfInstance(final NsdPackageNsdPackage pck, final String aspectId, final int level) {
		final NsVnfScalingStepMapping scalingStep = pck.getStepMapping().stream().filter(x -> x.getAspectId().equals(aspectId)).findFirst().orElseThrow();
		final List<StepMapping> p = scalingStep.getLevels().stream().filter(x -> x.getName() <= level).sorted(Comparator.comparingInt(StepMapping::getName).reversed()).toList();
		return p.get(0).getNumberOfInstance();
	}

	private static int getNumberOfInstance(final NsdPackageVnfPackage vnfPackage, final String aspectId, final int level) {
		final VnfScalingStepMapping scalingStep = vnfPackage.getStepMapping().stream().filter(x -> x.getAspectId().equals(aspectId)).findFirst().orElseThrow();
		final List<StepMapping> p = scalingStep.getLevels().stream().filter(x -> x.getName() <= level).sorted(Comparator.comparingInt(StepMapping::getName).reversed()).toList();
		return p.get(0).getNumberOfInstance();
	}

	private static NsScaleInfo findCuurentScaleInfo(final Set<NsScaleInfo> nsScaleStatus, final String aspectId) {
		return nsScaleStatus.stream().filter(x -> x.getNsScalingAspectId().equals(aspectId)).findFirst().orElseGet(() -> new NsScaleInfo(null, aspectId, "0"));
	}

	private static int computeLevel(final ScaleNsByStepsData scaleData, final Integer base) {
		if (scaleData.getScalingDirection() == ScalingDirectionType.IN) {
			return base - scaleData.getNumberOfSteps();
		}
		return base + scaleData.getNumberOfSteps();
	}

	private static List<NsVirtualLink> findConcernedVl(final NsdPackage nsdInfo, final String aspectId) {
		return nsdInfo.getNsVirtualLinks().stream().filter(x -> x.getStepMapping().stream().anyMatch(y -> y.getAspectId().equals(aspectId))).toList();
	}

	private static List<NsdPackageNsdPackage> findConcernedNs(final NsdPackage nsdInfo, final String aspectId) {
		return nsdInfo.getNestedNsdInfoIds().stream().filter(x -> x.getStepMapping().stream().anyMatch(y -> y.getAspectId().equals(aspectId))).toList();
	}

	private static List<NsdPackageVnfPackage> findConcernedVnf(final NsdPackage nsdInfo, final String aspectId) {
		return nsdInfo.getVnfPkgIds().stream().filter(x -> x.getStepMapping().stream().anyMatch(y -> y.getAspectId().equals(aspectId))).toList();
	}

	private static void nsScaleByLevel(final NsBlueprint blueprint, final NsdInstance instance, final ScaleNsData scaleNsData) {
		final ScaleNsToLevelData level = scaleNsData.getScaleNsToLevelData();
		if (level.getNsInstantiationLevel() != null) {

		} else {
			level.getNsScaleInfo().forEach(x -> {
				x.getNsScaleLevelId();
				x.getNsScalingAspectId();
			});
		}

	}

	public void heal(@NotNull final UUID objectId) {
		// TODO Auto-generated method stub
	}

}
