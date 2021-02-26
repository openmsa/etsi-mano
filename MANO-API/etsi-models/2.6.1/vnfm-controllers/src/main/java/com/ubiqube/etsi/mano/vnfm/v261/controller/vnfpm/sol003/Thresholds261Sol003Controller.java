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

package com.ubiqube.etsi.mano.vnfm.v261.controller.vnfpm.sol003;

import static com.ubiqube.etsi.mano.Constants.VNFTHR_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNFTHR_SEARCH_MANDATORY_FIELDS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.vnfpm.VnfmThresholdController;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.CreateThresholdRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.Threshold;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.ThresholdLinks;

import ma.glasnost.orika.MapperFacade;

@RestController
@RolesAllowed({ "ROLE_NFVO" })
public class Thresholds261Sol003Controller implements Thresholds261Sol003Api {
	private final VnfmThresholdController vnfmThresholdController;

	private final MapperFacade mapper;

	public Thresholds261Sol003Controller(final VnfmThresholdController _vnfmThresholdController, final MapperFacade _mapper) {
		vnfmThresholdController = _vnfmThresholdController;
		mapper = _mapper;
	}

	@Override
	public ResponseEntity<Threshold> thresholdsPost(@Valid final CreateThresholdRequest createThresholdRequest) throws URISyntaxException {
		com.ubiqube.etsi.mano.dao.mano.pm.Threshold res = mapper.map(createThresholdRequest, com.ubiqube.etsi.mano.dao.mano.pm.Threshold.class);
		res = vnfmThresholdController.save(res);
		final Threshold ret = mapper.map(res, Threshold.class);
		makeLinks(ret);
		return ResponseEntity.created(new URI(ret.getLinks().getSelf().getHref())).body(ret);
	}

	private static void makeLinks(final Threshold threshold) {
		final ThresholdLinks thresholdLinks = new ThresholdLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(Thresholds261Sol003Api.class).thresholdsThresholdIdGet(threshold.getId().toString())).withSelfRel().getHref());
		thresholdLinks.setSelf(self);
		threshold.setLinks(thresholdLinks);
	}

	@Override
	public ResponseEntity<Void> thresholdsThresholdIdDelete(final String thresholdId) {
		vnfmThresholdController.delete(UUID.fromString(thresholdId));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Threshold> thresholdsThresholdIdGet(final String thresholdId) {
		final com.ubiqube.etsi.mano.dao.mano.pm.Threshold res = vnfmThresholdController.findById(UUID.fromString(thresholdId));
		final Threshold ret = mapper.map(res, Threshold.class);
		makeLinks(ret);
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<String> thresholdsGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		return vnfmThresholdController.search(requestParams, Threshold.class, VNFTHR_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFTHR_SEARCH_MANDATORY_FIELDS, Thresholds261Sol003Controller::makeLinks);
	}

}
