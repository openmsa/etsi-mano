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
