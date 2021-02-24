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
package com.ubiqube.etsi.mano.vnfm.v261.controller.vnfpm;

import static com.ubiqube.etsi.mano.Constants.VNFPMJOB_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNFPMJOB_SEARCH_MANDATORY_FIELDS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.common.v261.model.nsperfo.PerformanceReport;
import com.ubiqube.etsi.mano.controller.vnfpm.VnfmPmController;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.CreatePmJobRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJobLinks;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class PmJobs261Sol002ApiController implements PmJobs261Sol002Api {
	private final MapperFacade mapper;

	private final VnfmPmController vnfmPmController;

	public PmJobs261Sol002ApiController(final MapperFacade mapper, final VnfmPmController vnfmPmController) {
		super();
		this.mapper = mapper;
		this.vnfmPmController = vnfmPmController;
	}

	@Override
	public ResponseEntity<String> pmJobsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return vnfmPmController.search(requestParams, PmJob.class, VNFPMJOB_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFPMJOB_SEARCH_MANDATORY_FIELDS, PmJobs261Sol002ApiController::makeLinks);
	}

	@Override
	public ResponseEntity<Void> pmJobsPmJobIdDelete(final String pmJobId) {
		vnfmPmController.delete(UUID.fromString(pmJobId));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PmJob> pmJobsPmJobIdGet(final String pmJobId) {
		final com.ubiqube.etsi.mano.dao.mano.pm.PmJob pmJob = vnfmPmController.findById(UUID.fromString(pmJobId));
		final PmJob ret = mapper.map(pmJob, PmJob.class);
		makeLinks(ret);
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<PerformanceReport> pmJobsPmJobIdReportsReportIdGet(final String pmJobId, final String reportId) {
		final com.ubiqube.etsi.mano.dao.mano.pm.PerformanceReport pm = vnfmPmController.findReport(UUID.fromString(pmJobId), UUID.fromString(reportId));
		return ResponseEntity.ok(mapper.map(pm, PerformanceReport.class));
	}

	@Override
	public ResponseEntity<PmJob> pmJobsPost(@Valid final CreatePmJobRequest createPmJobRequest) throws URISyntaxException {
		com.ubiqube.etsi.mano.dao.mano.pm.PmJob res = mapper.map(createPmJobRequest, com.ubiqube.etsi.mano.dao.mano.pm.PmJob.class);
		res = vnfmPmController.save(res);
		final PmJob obj = mapper.map(res, PmJob.class);
		makeLinks(obj);
		return ResponseEntity.created(new URI(obj.getLinks().getSelf().getHref())).body(obj);
	}

	private static void makeLinks(final PmJob x) {
		final PmJobLinks links = new PmJobLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(PmJobs261Sol002Api.class).pmJobsPmJobIdGet(x.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();
		link.setHref("");
		// links.setObjects(link);

		x.setLinks(links);
	}

}
