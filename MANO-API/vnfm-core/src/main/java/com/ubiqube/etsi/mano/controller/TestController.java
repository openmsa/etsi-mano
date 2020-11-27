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
package com.ubiqube.etsi.mano.controller;

import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.plan.VnfPlanner;

@Controller
@RequestMapping("/exec")
public class TestController {

	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

	private final PlanExecutor planExecutor;

	private final VnfPlanner planner;
	private final VnfPackageService vnfPackageService;
	private final VnfInstanceService vnfInstanceService;
	private final VimResourceService vimResourceService;

	public TestController(final PlanExecutor _planExecutor, final VnfPlanner _planner, final VnfPackageService _vnfPackageService, final VnfInstanceService _vnfInstanceService, final VimResourceService _vimResourceService) {
		planExecutor = _planExecutor;
		planner = _planner;
		vnfPackageService = _vnfPackageService;
		vnfInstanceService = _vnfInstanceService;
		vimResourceService = _vimResourceService;
	}

	@GetMapping(value = "/plan", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Blueprint> testPlan() {
		final VnfPackage vnfPackage = vnfPackageService.findById(UUID.fromString("d1814a92-7b09-420b-b212-6f9cd078456b"));
		final VnfInstance vnfInstance = vnfInstanceService.findById(UUID.fromString("e381cdbc-5993-4ae4-9525-89db3237b309"));
		final VnfBlueprint plan = new VnfBlueprint();
		plan.setId(UUID.randomUUID());
		plan.setVnfInstance(vnfInstance);
		final Set<ScaleInfo> scaling = null;
		planner.doPlan(vnfPackage, plan, scaling);
		planner.convertToExecution(plan, ChangeType.ADDED);
		vimResourceService.allocate(plan);
		return ResponseEntity.ok(null);
	}
}
