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
package com.ubiqube.etsi.mano.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.DefaultVduNamingStrategy;
import com.ubiqube.etsi.mano.service.graph.ExecutionPlanner;

@ExtendWith(MockitoExtension.class)
public class ExecutionPlannerTest {
	@Mock
	private VnfInstanceService vnfInstanceService;
	@Mock
	private VnfPackageService vnfPackageService;

	private final ScaleInfo scaleInfo;

	public ExecutionPlannerTest() {
		scaleInfo = new ScaleInfo("demo", 1);
	}

	private ExecutionPlanner createExecutionPlanner() {
		return new ExecutionPlanner(vnfInstanceService, vnfPackageService, new DefaultVduNamingStrategy());
	}

	void testName() throws Exception {
		final ExecutionPlanner executionPlanner = createExecutionPlanner();
		final VnfPackage vnfPakage = new VnfPackage();
		final Set<VnfInstantiationLevels> is = new HashSet<>();
		final VnfInstantiationLevels inst = new VnfInstantiationLevels();
		inst.setLevelName("demo");
		inst.setScaleInfoLevel(0);
		inst.setScaleInfoName("level_1");
		inst.setVnfPackage(vnfPakage);
		is.add(inst);
		vnfPakage.addInstantiationLevel(inst);
		final Set<VnfCompute> vnfComputes = new HashSet<>();
		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setStorages(new HashSet<String>());
		final UUID id = UUID.fromString("a73aae7d-8df6-4183-b5ee-dce6d39b8c09");
		vnfCompute.setId(id);
		vnfComputes.add(vnfCompute);
		vnfPakage.setVnfCompute(vnfComputes);
		final List<VnfComputeAspectDelta> res = new ArrayList<>();
		final VnfComputeAspectDelta aspectDelta = new VnfComputeAspectDelta();
		aspectDelta.setNumberOfInstances(1);
		res.add(aspectDelta);
		vnfPakage.setVnfStorage(new HashSet<VnfStorage>());

		when(vnfPackageService.findAspectDeltaByAspectId(vnfCompute, "demo")).thenReturn(res);
		final VnfLcmOpOccs lcmOpOccs = new VnfLcmOpOccs();
		executionPlanner.makePrePlan("demo", vnfPakage, null, lcmOpOccs, new HashSet<>());
		assertEquals(1, lcmOpOccs.getResourceChanges().getAffectedVnfcs().size());
		final VnfInstantiatedCompute affec = lcmOpOccs.getResourceChanges().getAffectedVnfcs().iterator().next();
		assertEquals(ChangeType.ADDED, affec.getChangeType());
		assertEquals(id, affec.getVduId());
	}

	@Test
	void testRemove() throws Exception {
		final ExecutionPlanner executionPlanner = createExecutionPlanner();
		final VnfPackage vnfPakage = new VnfPackage();
		final Set<VnfInstantiationLevels> is = new HashSet<>();
		final VnfInstantiationLevels inst = new VnfInstantiationLevels();
		inst.setLevelName("demo");
		inst.setScaleInfoLevel(0);
		inst.setScaleInfoName("level_1");
		inst.setVnfPackage(vnfPakage);
		is.add(inst);
		vnfPakage.addInstantiationLevel(inst);
		final Set<VnfCompute> vnfComputes = new HashSet<>();
		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setStorages(new HashSet<String>());
		final UUID id = UUID.fromString("a73aae7d-8df6-4183-b5ee-dce6d39b8c09");
		vnfCompute.setId(id);
		vnfComputes.add(vnfCompute);
		vnfPakage.setVnfCompute(vnfComputes);
		final List<VnfComputeAspectDelta> res = new ArrayList<>();
		final VnfComputeAspectDelta aspectDelta = new VnfComputeAspectDelta();
		aspectDelta.setNumberOfInstances(1);
		res.add(aspectDelta);
		vnfPakage.setVnfStorage(new HashSet<VnfStorage>());
		final Deque<VnfInstantiatedCompute> instantied = new ArrayDeque<>();
		instantied.add(new VnfInstantiatedCompute());
		instantied.add(new VnfInstantiatedCompute());

		// when(vnfPackageService.findAspectDeltaByAspectId(vnfCompute,
		// "demo")).thenReturn(res);
		when(vnfInstanceService.getNumberOfLiveInstance(null, vnfCompute)).thenReturn(2);
		// when(vnfInstanceService.getLiveComputeInstanceOf(null,
		// vnfCompute)).thenReturn(instantied);
		final VnfLcmOpOccs lcmOpOccs = new VnfLcmOpOccs();
		executionPlanner.makePrePlan("demo", vnfPakage, null, lcmOpOccs, new HashSet<>());
		assertEquals(0, lcmOpOccs.getResourceChanges().getAffectedVnfcs().size());
		// final VnfInstantiatedCompute affec =
		// lcmOpOccs.getResourceChanges().getAffectedVnfcs().iterator().next();
		// assertEquals(ChangeType.REMOVED, affec.getChangeType());
		// assertEquals(id, affec.getVduId());
	}

	@Test
	void testWhenNoDefaultLevel() throws Exception {
		final ExecutionPlanner exec = createExecutionPlanner();
		final Set<VnfInstantiationLevels> vnfInstantiationLevels = new LinkedHashSet<>();

		vnfInstantiationLevels.add(new VnfInstantiationLevels("demo", "left_aspect", 0));

		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setInitialNumberOfInstance(12);
		final Integer i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, null, scaleInfo);
		assertEquals(12, i);
	}

	@Test
	void testWhenWrongLevel() throws Exception {
		final ExecutionPlanner exec = createExecutionPlanner();
		final Set<VnfInstantiationLevels> vnfInstantiationLevels = new LinkedHashSet<>();

		vnfInstantiationLevels.add(new VnfInstantiationLevels("demo", "left_aspect", 0));

		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setInitialNumberOfInstance(12);
		vnfCompute.addVduInstantiationLevel(new VduInstantiationLevel("demo", 1));
		vnfCompute.setScalingAspectDeltas(new HashSet<VnfComputeAspectDelta>());
		final Integer i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "bad", scaleInfo);
		assertEquals(2, i);
	}

	@Test
	void testLevel01() throws Exception {
		final ExecutionPlanner exec = createExecutionPlanner();
		final Set<VnfInstantiationLevels> vnfInstantiationLevels = new LinkedHashSet<>();

		vnfInstantiationLevels.add(new VnfInstantiationLevels("demo", "left_aspect", 0));

		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setInitialNumberOfInstance(12);
		vnfCompute.addVduInstantiationLevel(new VduInstantiationLevel("demo", 1));

		final Set<VnfComputeAspectDelta> scaleDelta = new HashSet<>();
		scaleDelta.add(new VnfComputeAspectDelta("left_aspect", "delta_1", 2, 1, 10, ""));
		vnfCompute.setScalingAspectDeltas(scaleDelta);
		Integer i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "demo", scaleInfo);
		assertEquals(3, i);
		i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "demo", scaleInfo);
		assertEquals(3, i);
	}

	@Test
	void testLevel02() throws Exception {
		final ExecutionPlanner exec = createExecutionPlanner();
		final Set<VnfInstantiationLevels> vnfInstantiationLevels = new LinkedHashSet<>();

		vnfInstantiationLevels.add(new VnfInstantiationLevels("demo", "left_aspect", 0));
		vnfInstantiationLevels.add(new VnfInstantiationLevels("demo", "right_aspect", 1));
		vnfInstantiationLevels.add(new VnfInstantiationLevels("premium", "left_aspect", 1));
		vnfInstantiationLevels.add(new VnfInstantiationLevels("premium", "right_aspect", 0));

		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setInitialNumberOfInstance(12);

		vnfCompute.addVduInstantiationLevel(new VduInstantiationLevel("demo", 1));

		final Set<VnfComputeAspectDelta> scaleDelta = new HashSet<>();
		// scaleDelta.add(new VnfComputeAspectDelta("left_aspect", "delta_1", 0, 1));
		scaleDelta.add(new VnfComputeAspectDelta("right_aspect", "delta_1", 1, 1, 10, ""));
		scaleDelta.add(new VnfComputeAspectDelta("right_aspect", "delta_2", 4, 2, 10, ""));
		vnfCompute.setScalingAspectDeltas(scaleDelta);
		Integer i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "demo", scaleInfo);
		assertEquals(2, i);
		i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "demo", scaleInfo);
		assertEquals(2, i);

		i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "demo", scaleInfo);
		assertEquals(2, i);

		// OverMas scale
		i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "demo", scaleInfo);
		assertEquals(2, i);
	}

	@Test
	void testLevel03() throws Exception {
		final ExecutionPlanner exec = createExecutionPlanner();
		final Set<VnfInstantiationLevels> vnfInstantiationLevels = new LinkedHashSet<>();

		vnfInstantiationLevels.add(new VnfInstantiationLevels("demo", "left_aspect", 0));
		vnfInstantiationLevels.add(new VnfInstantiationLevels("demo", "right_aspect", 1));
		vnfInstantiationLevels.add(new VnfInstantiationLevels("premium", "left_aspect", 1));
		vnfInstantiationLevels.add(new VnfInstantiationLevels("premium", "right_aspect", 0));

		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setInitialNumberOfInstance(12);
		vnfCompute.addVduInstantiationLevel(new VduInstantiationLevel("demo", 1));

		final Set<VnfComputeAspectDelta> scaleDelta = new HashSet<>();
		scaleDelta.add(new VnfComputeAspectDelta("left_aspect", "delta_1", 10, 1, 10, ""));
		scaleDelta.add(new VnfComputeAspectDelta("left_aspect", "delta_1", 0, 2, 10, ""));
		vnfCompute.setScalingAspectDeltas(scaleDelta);
		Integer i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "premium", scaleInfo);
		assertEquals(11, i);
		i = exec.getNumberOfInstance(vnfInstantiationLevels, vnfCompute, "premium", scaleInfo);
		assertEquals(11, i);
	}
}
