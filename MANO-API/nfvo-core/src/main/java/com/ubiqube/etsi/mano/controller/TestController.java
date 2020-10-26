package com.ubiqube.etsi.mano.controller;

import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.plan.Planner;

@Controller
@RequestMapping("/exec")
public class TestController {

	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

	private final PlanExecutor planExecutor;

	private final Planner planner;
	private final VnfPackageService vnfPackageService;
	private final VnfInstanceService vnfInstanceService;

	public TestController(final PlanExecutor _planExecutor, final Planner _planner, final VnfPackageService _vnfPackageService, final VnfInstanceService _vnfInstanceService) {
		planExecutor = _planExecutor;
		planner = _planner;
		vnfPackageService = _vnfPackageService;
		vnfInstanceService = _vnfInstanceService;
	}

	@GetMapping(value = "/plan", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<Blueprint> testPlan() {
		final VnfPackage vnfPackage = vnfPackageService.findById(UUID.fromString("d1814a92-7b09-420b-b212-6f9cd078456b"));
		final VnfInstance vnfInstance = vnfInstanceService.findById(UUID.fromString("e381cdbc-5993-4ae4-9525-89db3237b309"));
		final Blueprint plan = new Blueprint();
		plan.setId(UUID.randomUUID());
		plan.setVnfInstance(vnfInstance);
		final Set<ScaleInfo> scaling = null;
		planner.doPlan(vnfPackage, plan, scaling);
		planner.convertToExecution(plan);
		return ResponseEntity.ok(null);
	}
}
