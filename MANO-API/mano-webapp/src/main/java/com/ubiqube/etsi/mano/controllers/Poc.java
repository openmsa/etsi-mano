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

package com.ubiqube.etsi.mano.controllers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.alarm.AckState;
import com.ubiqube.etsi.mano.dao.mano.alarm.Alarms;
import com.ubiqube.etsi.mano.dao.mano.alarm.EventType;
import com.ubiqube.etsi.mano.dao.mano.alarm.FaultyResourceInfo;
import com.ubiqube.etsi.mano.dao.mano.alarm.PerceivedSeverityType;
import com.ubiqube.etsi.mano.dao.mano.alarm.ResourceHandle;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.dao.mano.vnfi.VimCapability;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.OpenStackVim;
import com.ubiqube.etsi.mano.vnfm.jpa.AlarmsJpa;
import com.ubiqube.etsi.mano.vnfm.service.VnfBlueprintService;
import com.ubiqube.etsi.mano.vnfm.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.vnfm.service.plan.VnfPlanner;

import io.swagger.v3.oas.annotations.Hidden;

@Controller
@Hidden
@RequestMapping("/poc")
public class Poc {

	private static final Logger LOG = LoggerFactory.getLogger(Poc.class);

	private final OpenStackVim osv;
	private final VimConnectionInformationJpa vimConnectionInformationJpa;
	private final VnfPlanner planner;
	private final VnfBlueprintService blueprintService;

	private final AlarmsJpa alarmsJpa;

	private final RestTemplate restTemplate;

	public Poc(final OpenStackVim osv, final VimConnectionInformationJpa vimConnectionInformationJpa, final VnfPlanner planner, final VnfBlueprintService blueprintService,
			final RestTemplate restTemplate, final AlarmsJpa alarmsJpa) {
		this.osv = osv;
		this.vimConnectionInformationJpa = vimConnectionInformationJpa;
		this.planner = planner;
		this.blueprintService = blueprintService;
		this.restTemplate = restTemplate;
		this.alarmsJpa = alarmsJpa;
	}

	@GetMapping(value = "/test/{id}")
	public ResponseEntity<List<VimCapability>> test(@PathVariable(value = "id") final UUID id) {
		final VimConnectionInformation vc = vimConnectionInformationJpa.findById(id).orElseThrow(() -> new GenericException("Vim not found: " + id));
		final List<VimCapability> res = osv.getCaps(vc);
		return ResponseEntity.ok(res);
	}

	@GetMapping(value = "/var")
	public ResponseEntity<List<VimCapability>> test(@Value(value = "${msa.rest-api.url:}") final String _value) {
		LOG.warn("value = {}", _value);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/plan/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> genGraph(@PathVariable("id") final UUID id) {
		final VnfBlueprint plan = blueprintService.findById(id);
		final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> createPlan = planner.convertToExecution(plan, ChangeType.ADDED);
		final JGraphXAdapter<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> adapter = new JGraphXAdapter<>(createPlan);
		adapter.clearSelection();
		adapter.selectAll();
		adapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
		adapter.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_ROUNDED, "1");
		adapter.getStylesheet().getDefaultVertexStyle().put(mxConstants.STYLE_ROUNDED, "1");
		final mxIGraphLayout layout = new mxHierarchicalLayout(adapter);
		layout.execute(adapter.getDefaultParent());
		final BufferedImage img = mxCellRenderer.createBufferedImage(adapter, null, 2, Color.WHITE, true, null);
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(img, headers, HttpStatus.OK);
	}

	@GetMapping(value = "/alarm")
	public void alarm() {
		final Alarms alarm = new Alarms();
		final FaultyResourceInfo rootCauseFaultyResource = new FaultyResourceInfo();
		final ResourceHandle faultyResource = new ResourceHandle();
		faultyResource.setResourceId(UUID.randomUUID());
		faultyResource.setResourceProviderId("PROVIDER");
		faultyResource.setVimConnectionId(UUID.randomUUID());
		faultyResource.setVimLevelResourceType("LEVEL");
		rootCauseFaultyResource.setFaultyResource(faultyResource);
		alarm.setAckState(AckState.ACKNOWLEDGED);
		alarm.setAlarmAcknowledgedTime(LocalDateTime.now());
		alarm.setAlarmChangedTime(LocalDateTime.now());
		alarm.setAlarmClearedTime(LocalDateTime.now());
		alarm.setAlarmRaisedTime(LocalDateTime.now());
		alarm.setEventTime(LocalDateTime.now());
		alarm.setEventType(EventType.COMMUNICATIONS_ALARM);
		alarm.setFaultType("Fault type");
		alarm.setManagedObjectId(UUID.randomUUID());
		alarm.setPerceivedSeverity(PerceivedSeverityType.CLEARED);
		alarm.setProbableCause("Probable cause");
		alarm.setRootCauseFaultyResource(rootCauseFaultyResource);
		alarm.setRootCause(true);
		alarm.setFaultType("Faultly");
		alarmsJpa.save(alarm);
	}

	@GetMapping(value = "/test")
	public void test() {
		restTemplate.postForEntity("http://mano-geo-service/", new HashMap<>(), String.class);
	}

	@GetMapping(value = "/template", produces = "test/html")
	public String template(@RequestParam(name = "name", required = false, defaultValue = "World") final String name, final Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
}
