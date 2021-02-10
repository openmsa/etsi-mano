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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nsperfo;

import static com.ubiqube.etsi.mano.Constants.getSingleField;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.common.v261.model.nsperfo.PerformanceReport;
import com.ubiqube.etsi.mano.controller.nspm.NfvoPmController;
import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.CreatePmJobRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.PmJobsCreatePmJobRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.PmJobsPmJob;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.PmJobsPostResponse;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;

import ma.glasnost.orika.MapperFacade;

@RolesAllowed({ "ROLE_OSSBSS" })
public class PmJobsSol005Api implements PmJobsSol005 {
	private static final String PMJ_SEARCH_DEFAULT_EXCLUDE_FIELDS = "";

	private static final Set<String> PMJ_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	private final NfvoPmController nfvoPmController;

	private final MapperFacade mapper;

	private final ManoSearchResponseService searchService;

	public PmJobsSol005Api(final NfvoPmController _nfvoPmController, final MapperFacade _mapper, final ManoSearchResponseService _searchService) {
		nfvoPmController = _nfvoPmController;
		mapper = _mapper;
		searchService = _searchService;
	}

	/**
	 * Query PM jobs.
	 *
	 * \&quot;The client can use this method to retrieve information about PM jobs\&quot;
	 *
	 */
	@Override
	public ResponseEntity<String> pmJobsGet(final MultiValueMap<String, String> requestParams) {
		final String filter = getSingleField(requestParams, "filter");
		final List<PmJob> result = nfvoPmController.query(filter);
		final Consumer<PmJobsPmJob> setLink = x -> {
			/* XXX Missing makeLinks. */};
		return searchService.search(requestParams, PMJ_SEARCH_DEFAULT_EXCLUDE_FIELDS, PMJ_SEARCH_MANDATORY_FIELDS, result, PmJobsPmJob.class, setLink);
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
	public ResponseEntity<PmJobsPostResponse> pmJobsPmJobIdGet(final String pmJobId) {
		final PmJobsPostResponse response = new PmJobsPostResponse();
		final PmJob entity = nfvoPmController.getById(UUID.fromString(pmJobId));
		response.setPmJob(mapper.map(entity, PmJobsPmJob.class));
		return ResponseEntity.ok(response);
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
	public ResponseEntity<PmJobsPostResponse> pmJobsPost(final CreatePmJobRequest createPmJobRequest) {
		final PmJobsCreatePmJobRequest pmJob = createPmJobRequest.getCreatePmJobRequest();
		final PmJob req = mapper.map(pmJob, PmJob.class);
		final PmJob res = nfvoPmController.save(req);
		final PmJobsPostResponse response = new PmJobsPostResponse();
		response.setPmJob(mapper.map(res, PmJobsPmJob.class));
		return ResponseEntity.ok(response);
	}

}
