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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nspm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.common.v261.model.nsperfo.PerformanceReport;
import com.ubiqube.etsi.mano.controller.nspm.NfvoPmController;
import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.CreatePmJobRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.PmJobsCreatePmJobRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJobLinks;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class PmJobs261Sol005Controller implements PmJobs261Sol005Api {
	private static final String PMJ_SEARCH_DEFAULT_EXCLUDE_FIELDS = "";

	private static final Set<String> PMJ_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	private final NfvoPmController nfvoPmController;

	private final MapperFacade mapper;

	public PmJobs261Sol005Controller(final NfvoPmController _nfvoPmController, final MapperFacade _mapper) {
		nfvoPmController = _nfvoPmController;
		mapper = _mapper;
	}

	/**
	 * Query PM jobs.
	 *
	 * \&quot;The client can use this method to retrieve information about PM jobs\&quot;
	 *
	 */
	@Override
	public ResponseEntity<String> pmJobsGet(final MultiValueMap<String, String> requestParams) {
		return nfvoPmController.search(requestParams, com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob.class, PMJ_SEARCH_DEFAULT_EXCLUDE_FIELDS, PMJ_SEARCH_MANDATORY_FIELDS, PmJobs261Sol005Controller::makeLinks);
	}

	/**
	 * Delete a PM job.
	 *
	 * This method terminates an individual PM job.
	 *
	 * @return
	 *
	 */
	@Override
	public ResponseEntity<Void> pmJobsPmJobIdDelete(final String pmJobId) {
		nfvoPmController.deleteById(UUID.fromString(pmJobId));
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read a single PM job.
	 *
	 * The client can use this method for reading an individual PM job.
	 *
	 */
	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob> pmJobsPmJobIdGet(final String pmJobId) {
		final PmJob entity = nfvoPmController.getById(UUID.fromString(pmJobId));
		final com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob res = mapper.map(entity, com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob.class);
		return ResponseEntity.ok(res);
	}

	/**
	 * Read an individual performance report.
	 *
	 * The client can use this method for reading an individual performance report.
	 *
	 */
	@Override
	public ResponseEntity<PerformanceReport> pmJobsPmJobIdReportsReportIdGet(final String pmJobId, final String reportId) {
		final com.ubiqube.etsi.mano.dao.mano.pm.PerformanceReport report = nfvoPmController.getReportById(pmJobId, reportId);
		final PerformanceReport mapped = mapper.map(report, PerformanceReport.class);
		return ResponseEntity.ok(mapped);
	}

	/**
	 * Create a PM job.
	 *
	 * The POST method creates a PM job. This method shall follow the provisions specified in the Tables 7.4.2.3.1-1 and 7.4.2.3.1-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob> pmJobsPost(final CreatePmJobRequest createPmJobRequest) {
		final PmJobsCreatePmJobRequest pmJob = createPmJobRequest.getCreatePmJobRequest();
		final PmJob req = mapper.map(pmJob, PmJob.class);
		final PmJob res = nfvoPmController.save(req);
		final com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob ret = mapper.map(res, com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob.class);
		makeLinks(ret);
		return ResponseEntity.ok(ret);
	}

	private static void makeLinks(final com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmJob x) {
		final PmJobLinks links = new PmJobLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(PmJobs261Sol005Api.class).pmJobsPmJobIdGet(x.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();
		link.setHref("");
		// links.setObjects(link);

		x.setLinks(links);
	}

}
