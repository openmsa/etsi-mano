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
package com.ubiqube.mano.scale;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScale;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsByStepsData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleNsToLevelData;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScaleType;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.ScalingDirectionType;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.StepMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VnfScalingLevelMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VnfScalingStepMapping;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.nfvo.service.NsScaleStrategy;

@SuppressWarnings("static-method")
class VnfScallingTest {

	private static Set<VnfScalingLevelMapping> getLevelMapping() {
		final VnfScalingLevelMapping sm1 = new VnfScalingLevelMapping("name", "aspect", 1);
		final VnfScalingLevelMapping sm2 = new VnfScalingLevelMapping("name", "aspect", 2);
		return Set.of(sm1, sm2);
	}

	private static Set<StepMapping> getStepMapping1() {
		final StepMapping s0 = new StepMapping(0, 1);
		final StepMapping s1 = new StepMapping(1, 2);
		final StepMapping s2 = new StepMapping(2, 3);
		return Set.of(s0, s1, s2);
	}

	private static Set<StepMapping> getStepMapping2() {
		final StepMapping s0 = new StepMapping(0, 3);
		final StepMapping s1 = new StepMapping(1, 4);
		final StepMapping s2 = new StepMapping(2, 5);
		return Set.of(s0, s1, s2);
	}

	private static Set<VnfScalingStepMapping> getVnfScalingStepMapping() {
		final VnfScalingStepMapping ssm1 = new VnfScalingStepMapping(getStepMapping1(), "aspect");
		final VnfScalingStepMapping ssm2 = new VnfScalingStepMapping(getStepMapping2(), "aspect2");
		return Set.of(ssm1, ssm2);
	}

	@Test
	void testInstantiate() throws Exception {
		final NsScaleStrategy nss = new NsScaleStrategy();
		final NsdPackageVnfPackage nsPackageVnfPackage = new NsdPackageVnfPackage();
		nsPackageVnfPackage.setLevelMapping(getLevelMapping());
		nsPackageVnfPackage.setStepMapping(getVnfScalingStepMapping());
		final NsBlueprint blueprint = new NsBlueprint();
		final NsdInstance nsInstance = new NsdInstance();
		nsInstance.setNsInstantiationLevelId("1");
		blueprint.setNsInstance(nsInstance);
		blueprint.setOperation(PlanOperationType.INSTANTIATE);
		final int x = nss.getNumberOfInstances(nsPackageVnfPackage, blueprint);
		assertEquals(1, x);
	}

	@Test
	void testScaleStep() throws Exception {
		final NsScaleStrategy nss = new NsScaleStrategy();
		final NsdPackageVnfPackage nsPackageVnfPackage = new NsdPackageVnfPackage();
		nsPackageVnfPackage.setLevelMapping(getLevelMapping());
		nsPackageVnfPackage.setStepMapping(getVnfScalingStepMapping());
		final NsBlueprint blueprint = new NsBlueprint();
		final NsdInstance nsInstance = new NsdInstance();
		nsInstance.setNsInstantiationLevelId("1");
		blueprint.setNsInstance(nsInstance);
		blueprint.setOperation(PlanOperationType.SCALE);
		final BlueprintParameters parameters = new BlueprintParameters();
		final NsScale nsScale = new NsScale();
		nsScale.setScaleType(ScaleType.NS);
		final ScaleNsData scaleNsData = new ScaleNsData();
		final ScaleNsByStepsData scaleNsByStepsData = new ScaleNsByStepsData();
		scaleNsByStepsData.setAspectId("aspect");
		scaleNsByStepsData.setNumberOfSteps(1);
		scaleNsByStepsData.setScalingDirection(ScalingDirectionType.OUT);
		scaleNsData.setScaleNsByStepsData(scaleNsByStepsData);
		nsScale.setScaleNsData(scaleNsData);
		parameters.setNsScale(nsScale);
		blueprint.setParameters(parameters);
		final int x = nss.getNumberOfInstances(nsPackageVnfPackage, blueprint);
		assertEquals(2, x);
	}

	@Test
	void testScaleStep2() throws Exception {
		final NsScaleStrategy nss = new NsScaleStrategy();
		final NsdPackageVnfPackage nsPackageVnfPackage = new NsdPackageVnfPackage();
		nsPackageVnfPackage.setLevelMapping(getLevelMapping());
		nsPackageVnfPackage.setStepMapping(getVnfScalingStepMapping());
		final NsBlueprint blueprint = new NsBlueprint();
		final NsdInstance nsInstance = new NsdInstance();
		nsInstance.setNsInstantiationLevelId("1");
		blueprint.setNsInstance(nsInstance);
		blueprint.setOperation(PlanOperationType.SCALE);
		final BlueprintParameters parameters = new BlueprintParameters();
		final NsScale nsScale = new NsScale();
		nsScale.setScaleType(ScaleType.NS);
		final ScaleNsData scaleNsData = new ScaleNsData();
		final ScaleNsByStepsData scaleNsByStepsData = new ScaleNsByStepsData();
		scaleNsByStepsData.setAspectId("aspect");
		scaleNsByStepsData.setNumberOfSteps(10);
		scaleNsByStepsData.setScalingDirection(ScalingDirectionType.OUT);
		scaleNsData.setScaleNsByStepsData(scaleNsByStepsData);
		nsScale.setScaleNsData(scaleNsData);
		parameters.setNsScale(nsScale);
		blueprint.setParameters(parameters);
		final int x = nss.getNumberOfInstances(nsPackageVnfPackage, blueprint);
		assertEquals(3, x);
	}

	@Test
	void testScaleStep3() throws Exception {
		final NsScaleStrategy nss = new NsScaleStrategy();
		final NsdPackageVnfPackage nsPackageVnfPackage = new NsdPackageVnfPackage();
		nsPackageVnfPackage.setLevelMapping(getLevelMapping());
		nsPackageVnfPackage.setStepMapping(getVnfScalingStepMapping());
		final NsBlueprint blueprint = new NsBlueprint();
		final NsdInstance nsInstance = new NsdInstance();
		final NsScaleInfo ns1 = new NsScaleInfo();
		ns1.setNsScaleLevelId("1");
		ns1.setNsScalingAspectId("aspect");
		final Set<NsScaleInfo> nsScaleStatus = Set.of(ns1);
		nsInstance.setNsScaleStatus(nsScaleStatus);
		nsInstance.setNsInstantiationLevelId("1");
		blueprint.setNsInstance(nsInstance);
		blueprint.setOperation(PlanOperationType.SCALE);
		final BlueprintParameters parameters = new BlueprintParameters();
		final NsScale nsScale = new NsScale();
		nsScale.setScaleType(ScaleType.NS);
		final ScaleNsData scaleNsData = new ScaleNsData();
		final ScaleNsByStepsData scaleNsByStepsData = new ScaleNsByStepsData();
		scaleNsByStepsData.setAspectId("aspect");
		scaleNsByStepsData.setNumberOfSteps(1);
		scaleNsByStepsData.setScalingDirection(ScalingDirectionType.IN);
		scaleNsData.setScaleNsByStepsData(scaleNsByStepsData);
		nsScale.setScaleNsData(scaleNsData);
		parameters.setNsScale(nsScale);
		blueprint.setParameters(parameters);
		final int x = nss.getNumberOfInstances(nsPackageVnfPackage, blueprint);
		assertEquals(1, x);
	}

	@Test
	void testScaleLevel() throws Exception {
		final NsScaleStrategy nss = new NsScaleStrategy();
		final NsdPackageVnfPackage nsPackageVnfPackage = new NsdPackageVnfPackage();
		nsPackageVnfPackage.setLevelMapping(getLevelMapping());
		nsPackageVnfPackage.setStepMapping(getVnfScalingStepMapping());
		final NsBlueprint blueprint = new NsBlueprint();
		final NsdInstance nsInstance = new NsdInstance();
		final NsScaleInfo ns1 = new NsScaleInfo();
		ns1.setNsScaleLevelId("1");
		ns1.setNsScalingAspectId("aspect");
		final Set<NsScaleInfo> nsScaleStatus = Set.of(ns1);
		nsInstance.setNsScaleStatus(nsScaleStatus);
		nsInstance.setNsInstantiationLevelId("1");
		blueprint.setNsInstance(nsInstance);
		blueprint.setOperation(PlanOperationType.SCALE);
		final BlueprintParameters parameters = new BlueprintParameters();
		final NsScale nsScale = new NsScale();
		nsScale.setScaleType(ScaleType.NS);
		final ScaleNsData scaleNsData = new ScaleNsData();
		final ScaleNsToLevelData scaleNsToLevelData = new ScaleNsToLevelData();
		scaleNsToLevelData.setNsInstantiationLevel("3");
		final NsScaleInfo ns2 = new NsScaleInfo();
		ns2.setNsScaleLevelId("1");
		ns2.setNsScalingAspectId("aspect");
		final Set<NsScaleInfo> nsScaleInfo = Set.of(ns2);
		scaleNsToLevelData.setNsScaleInfo(nsScaleInfo);
		scaleNsData.setScaleNsToLevelData(scaleNsToLevelData);
		nsScale.setScaleNsData(scaleNsData);
		parameters.setNsScale(nsScale);
		blueprint.setParameters(parameters);
		final int x = nss.getNumberOfInstances(nsPackageVnfPackage, blueprint);
		assertEquals(1, x);
	}
}
