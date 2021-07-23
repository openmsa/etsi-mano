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
package com.ubiqube.etsi.mano.orchestrator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jgrapht.ListenableGraph;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.orchestrator.cont.ContributorA;
import com.ubiqube.etsi.mano.orchestrator.cont.ContributorB;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Monitoring;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.service.ImplementationService;
import com.ubiqube.etsi.mano.orchestrator.service.SystemManager;
import com.ubiqube.etsi.mano.orchestrator.system.SysA;
import com.ubiqube.etsi.mano.orchestrator.system.SysB;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitA;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitB;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWorkConnectivity;
import com.ubiqube.etsi.mano.orchestrator.vt.ProvAVt;
import com.ubiqube.etsi.mano.orchestrator.vt.ProvBVt;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTaskConnectivity;
import com.ubiqube.etsi.mano.service.sys.System;

@ExtendWith(MockitoExtension.class)
public class OrchestrationTest {

	private static final Logger LOG = LoggerFactory.getLogger(OrchestrationTest.class);

	@Mock
	private SystemManager vimManager;
	private ImplementationService implementationService;

	private Planner getPlanner() {
		final List<System> systems = Arrays.asList(new SysA(), new SysB());

		implementationService = new ImplementationService(systems, vimManager, null);
		final List<PlanContributor> contributors = new ArrayList<>();
		contributors.add(new ContributorA());
		contributors.add(new ContributorB());
		return new PlannerImpl(contributors, implementationService);
	}

	@Test
	void testplanLevel() throws Exception {
		final Planner p = getPlanner();
		final List<Class<? extends Node>> planConstituent = Arrays.asList(Network.class, Compute.class, Monitoring.class);
		final PreExecutionGraph<?> planOpaque = p.makePlan(null, planConstituent, null);
		final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> plan = ((PreExecutionGraphImpl) planOpaque).getCreateGraph();
		assertEquals(1, plan.edgeSet().size());
		final VirtualTaskConnectivity o = plan.edgeSet().iterator().next();
		assertNotNull(o.getSource());
		assertNotNull(o.getTarget());
		assertEquals(o.getSource().getClass(), ProvAVt.class);
		assertEquals(o.getTarget().getClass(), ProvBVt.class);
		//
		Mockito.lenient().when(vimManager.findVimByVimIdAndProviderId("PROVA", "")).thenReturn(TestFactory.createVimConnectionA());
		Mockito.lenient().when(vimManager.findVimByVimIdAndProviderId("PROVB", "")).thenReturn(TestFactory.createVimConnectionB());
		final ExecutionGraph rOpaq = p.implement(planOpaque);
		final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> r = ((ExecutionGraphImpl) rOpaq).getCreateImplementation();
		assertEquals(1, r.edgeSet().size());
		final UnitOfWorkConnectivity e = r.edgeSet().iterator().next();
		assertNotNull(e.getSource());
		assertNotNull(e.getTarget());
		assertEquals(e.getSource().getClass(), UnitA.class);
		assertEquals(e.getTarget().getClass(), UnitB.class);
	}
}
