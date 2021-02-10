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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

import com.ubiqube.etsi.mano.controller.nspm.NfvoThresholdController;
import com.ubiqube.etsi.mano.dao.mano.pm.Threshold;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.CreateThresholdRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.ThresholdsCreateThresholdRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.ThresholdsPostResponse;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.ThresholdsThreshold;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.ThresholdLinks;

import ma.glasnost.orika.MapperFacade;

@RolesAllowed({ "ROLE_OSSBSS" })
public class ThresholdsSol005Api implements ThresholdsSol005 {
	private static final Set<String> THR_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	private static final String THR_SEARCH_DEFAULT_EXCLUDE_FIELDS = "";

	private final NfvoThresholdController nfvoThresholdController;

	private final MapperFacade mapper;

	private final ManoSearchResponseService searchService;

	public ThresholdsSol005Api(final NfvoThresholdController _nfvoThresholdController, final MapperFacade _mapper, final ManoSearchResponseService _searchService) {
		nfvoThresholdController = _nfvoThresholdController;
		mapper = _mapper;
		searchService = _searchService;
	}

	/**
	 * Query thresholds.
	 *
	 * The client can use this method to query information about thresholds.
	 *
	 */
	@Override
	public ResponseEntity<String> thresholdsGet(final String filter) {
		final List<Threshold> result = nfvoThresholdController.query(filter);
		final Consumer<com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.Threshold> setLink = x -> x.setLinks(makeLinks(x.getId()));
		return searchService.search(new LinkedMultiValueMap<>(), THR_SEARCH_DEFAULT_EXCLUDE_FIELDS, THR_SEARCH_MANDATORY_FIELDS, result, com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.Threshold.class, setLink);
	}

	private ThresholdLinks makeLinks(@NotNull final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create a threshold.
	 *
	 * The POST method can be used by the client to create a threshold. This method shall follow the provisions specified in the table 7.4.5.3.1-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<ThresholdsPostResponse> thresholdsPost(final CreateThresholdRequest createThresholdRequest) {
		final ThresholdsCreateThresholdRequest req = createThresholdRequest.getCreateThresholdRequest();
		final Threshold threshold = mapper.map(req, Threshold.class);
		final Threshold res = nfvoThresholdController.save(threshold);
		final ThresholdsPostResponse resp = new ThresholdsPostResponse();
		resp.setThreshold(mapper.map(res, ThresholdsThreshold.class));
		return ResponseEntity.ok(resp);
	}

	/**
	 * Delete a threshold.
	 *
	 * This method allows to delete a threshold.
	 *
	 */
	@Override
	public void thresholdsThresholdIdDelete(final String thresholdId) {
		nfvoThresholdController.delete(UUID.fromString(thresholdId));
	}

	/**
	 * Query a single threshold.
	 *
	 * The client can use this method for reading an individual threshold. This method shall follow the provisions specified in the Tables 7.4.6.3.2-1 and 7.4.6.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<ThresholdsPostResponse> thresholdsThresholdIdGet(final String thresholdId) {
		final Threshold threshold = nfvoThresholdController.getById(UUID.fromString(thresholdId));
		final ThresholdsPostResponse resp = new ThresholdsPostResponse();
		resp.setThreshold(mapper.map(threshold, ThresholdsThreshold.class));
		return ResponseEntity.ok(resp);
	}

}
