package com.ubiqube.etsi.mano.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
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

	@Test
	void testName() throws Exception {

		final ExecutionPlanner executionPlanner = new ExecutionPlanner(null, vnfInstanceService, vnfPackageService, new DefaultVduNamingStrategy(), null, null, null, null, null, null);
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
		executionPlanner.makePrePlan("demo", vnfPakage, null, lcmOpOccs);
		assertEquals(1, lcmOpOccs.getResourceChanges().getAffectedVnfcs().size());
		final VnfInstantiatedCompute affec = lcmOpOccs.getResourceChanges().getAffectedVnfcs().iterator().next();
		assertEquals(ChangeType.ADDED, affec.getChangeType());
		assertEquals(id, affec.getVduId());
	}

	@Test
	void testRemove() throws Exception {
		final ExecutionPlanner executionPlanner = new ExecutionPlanner(null, vnfInstanceService, vnfPackageService, new DefaultVduNamingStrategy(), null, null, null, null, null, null);
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

		when(vnfPackageService.findAspectDeltaByAspectId(vnfCompute, "demo")).thenReturn(res);
		when(vnfInstanceService.getNumberOfLiveInstance(null, vnfCompute)).thenReturn(2);
		when(vnfInstanceService.getLiveComputeInstanceOf(null, vnfCompute)).thenReturn(instantied);
		final VnfLcmOpOccs lcmOpOccs = new VnfLcmOpOccs();
		executionPlanner.makePrePlan("demo", vnfPakage, null, lcmOpOccs);
		assertEquals(1, lcmOpOccs.getResourceChanges().getAffectedVnfcs().size());
		final VnfInstantiatedCompute affec = lcmOpOccs.getResourceChanges().getAffectedVnfcs().iterator().next();
		assertEquals(ChangeType.REMOVED, affec.getChangeType());
		assertEquals(id, affec.getVduId());
	}
}
