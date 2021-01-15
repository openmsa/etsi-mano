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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.service.VnfBlueprintService;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.plan.VnfPlanner;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.OpenStackVim;
import com.ubiqube.etsi.mano.service.vim.VimCapability;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@RequestMapping("/poc")
public class Poc {

	private static final Logger LOG = LoggerFactory.getLogger(Poc.class);

	private final OpenStackVim osv;
	private final VimConnectionInformationJpa vimConnectionInformationJpa;
	private final VnfPlanner planner;
	private final VnfBlueprintService blueprintService;

	public Poc(final OpenStackVim _osv, final VimConnectionInformationJpa _vimConnectionInformationJpa, final VnfPlanner _planner, final VnfBlueprintService _blueprintService) {
		osv = _osv;
		vimConnectionInformationJpa = _vimConnectionInformationJpa;
		planner = _planner;
		blueprintService = _blueprintService;
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
}
