package com.ubiqube.etsi.mano.controller.nsperfo.sol005;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.model.nsperfo.sol005.CreatePmJobRequest;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.PmJobsPmJobIdReportsReportIdGetResponse;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.PmJobsPostResponse;

public class PmJobsSol005Api implements PmJobsSol005 {
	/**
	 * Query PM jobs.
	 *
	 * \&quot;The client can use this method to retrieve information about PM
	 * jobs\&quot;
	 *
	 */
	@Override
	public ResponseEntity<List<Object>> pmJobsGet(String accept, String contentType, String filter, String allFields, String include, String exclude, String excludeDefault) {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

	/**
	 * Delete a PM job.
	 *
	 * This method terminates an individual PM job.
	 *
	 */
	@Override
	public void pmJobsPmJobIdDelete(String pmJobId) {
		// TODO: Implement...

	}

	/**
	 * Read a single PM job.
	 *
	 * The client can use this method for reading an individual PM job.
	 *
	 */
	@Override
	public ResponseEntity<PmJobsPostResponse> pmJobsPmJobIdGet(String pmJobId, String accept) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Read an individual performance report.
	 *
	 * The client can use this method for reading an individual performance report.
	 *
	 */
	@Override
	public ResponseEntity<PmJobsPmJobIdReportsReportIdGetResponse> pmJobsPmJobIdReportsReportIdGet(String pmJobId, String reportId, String accept) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Create a PM job.
	 *
	 * The POST method creates a PM job. This method shall follow the provisions
	 * specified in the Tables 7.4.2.3.1-1 and 7.4.2.3.1-2 for URI query parameters,
	 * request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<PmJobsPostResponse> pmJobsPost(CreatePmJobRequest createPmJobRequest, String accept, String contentType) {
		// TODO: Implement...

		return null;
	}

}
