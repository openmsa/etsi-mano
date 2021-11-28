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
package com.ubiqube.etsi.mano.vnfm.controller.vnfpm;

import static com.ubiqube.etsi.mano.Constants.VNFPMJOB_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNFPMJOB_SEARCH_MANDATORY_FIELDS;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.VimService;
import com.ubiqube.etsi.mano.vnfm.fc.vnfpm.VnfmPmGenericFrontController;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmPmGenericFrontControllerImpl implements VnfmPmGenericFrontController {
	private final VnfmPmController vnfmPmController;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;
	private final MapperFacade mapper;
	private final VimService vimService;

	public VnfmPmGenericFrontControllerImpl(final VnfmPmController vnfmPmController, final VnfLiveInstanceJpa vnfLiveInstanceJpa, final MapperFacade mapper, final VimService vimService) {
		super();
		this.vnfmPmController = vnfmPmController;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
		this.mapper = mapper;
		this.vimService = vimService;
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLink) {
		return vnfmPmController.search(requestParams, clazz, VNFPMJOB_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFPMJOB_SEARCH_MANDATORY_FIELDS, makeLink);
	}

	@Override
	public ResponseEntity<Void> deleteById(final UUID pmJobId) {
		vnfmPmController.delete(pmJobId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public <U> ResponseEntity<U> findById(final UUID pmJobIdn, final Class<U> clazz, final Consumer<U> makeLinks) {
		final com.ubiqube.etsi.mano.dao.mano.pm.PmJob pmJob = vnfmPmController.findById(pmJobIdn);
		final U ret = mapper.map(pmJob, clazz);
		makeLinks.accept(ret);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@Override
	public <U> ResponseEntity<U> findReportById(final String pmJobId, final String reportId, final Class<U> clazz) {
		final com.ubiqube.etsi.mano.dao.mano.pm.PerformanceReport pm = vnfmPmController.findReport(UUID.fromString(pmJobId), UUID.fromString(reportId));
		return ResponseEntity.ok(mapper.map(pm, clazz));
	}

	@Override
	public <U> ResponseEntity<U> pmJobsPost(@Valid final Object createPmJobRequest, final Class<U> clazz, final Consumer<U> makeLinks, final Function<U, String> getSelfLink) {
		com.ubiqube.etsi.mano.dao.mano.pm.PmJob res = mapper.map(createPmJobRequest, com.ubiqube.etsi.mano.dao.mano.pm.PmJob.class);
		final List<VnfLiveInstance> vlis = vnfLiveInstanceJpa.findByResourceIdIn(res.getObjectInstanceIds());
		checkFetchedData(vlis, res.getObjectInstanceIds());
		final VimConnectionInformation vci = vimService.findById(UUID.fromString(vlis.get(0).getVimConnectionId())).orElseThrow();
		res.setVimConnectionInformation(vci);
		res = vnfmPmController.save(res);
		final U obj = mapper.map(res, clazz);
		makeLinks.accept(obj);
		final String link = getSelfLink.apply(obj);
		return ResponseEntity.created(URI.create(link)).body(obj);
	}

	private static void checkFetchedData(final List<VnfLiveInstance> vlis, final List<String> objectInstanceIds) {
		if (vlis.size() != objectInstanceIds.size()) {
			throw new GenericException("Some of the resources have not been found: " + vlis);
		}
		final String vimRef = vlis.get(0).getVimConnectionId();
		vlis.forEach(x -> {
			if (x.getVimConnectionId().equals(vimRef)) {
				throw new GenericException("");
			}
		});
	}

	@Override
	public <U> ResponseEntity<U> pmJobsPmJobIdPatch(final UUID pmJobId, final Object pmJobModifications) {
		// XXX
		return null;
	}
}
