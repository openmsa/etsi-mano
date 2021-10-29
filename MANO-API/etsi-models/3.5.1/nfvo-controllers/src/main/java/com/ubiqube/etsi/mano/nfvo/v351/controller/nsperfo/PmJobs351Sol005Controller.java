package com.ubiqube.etsi.mano.nfvo.v351.controller.nsperfo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo.CreatePmJobRequest;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo.PerformanceReport;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo.PmJob;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo.PmJobModifications;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class PmJobs351Sol005Controller implements PmJobs351Sol005Api {

	@Override
	public ResponseEntity<List<PmJob>> pmJobsGet(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> pmJobsPmJobIdDelete(final String pmJobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PmJob> pmJobsPmJobIdGet(final String pmJobId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PmJobModifications> pmJobsPmJobIdPatch(final String pmJobId, @Valid final PmJobModifications body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PerformanceReport> pmJobsPmJobIdReportsReportIdGet(final String pmJobId, final String reportId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PmJob> pmJobsPost(@Valid final CreatePmJobRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

}
