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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URISyntaxException;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.vnfpm.VnfmThresholdFrontController;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.CreateThresholdRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.Threshold;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.ThresholdLinks;

@RestController
@RolesAllowed({ "ROLE_NFVO" })
public class Thresholds261Sol003Controller implements Thresholds261Sol003Api {
	private final VnfmThresholdFrontController vnfmThresholdFrontController;

	public Thresholds261Sol003Controller(final VnfmThresholdFrontController vnfmThresholdFrontController) {
		this.vnfmThresholdFrontController = vnfmThresholdFrontController;
	}

	@Override
	public ResponseEntity<Threshold> thresholdsPost(@Valid final CreateThresholdRequest createThresholdRequest) throws URISyntaxException {
		return vnfmThresholdFrontController.thresholdsCreate(createThresholdRequest, Threshold.class, Thresholds261Sol003Controller::makeLinks, Thresholds261Sol003Controller::getSelfLink);
	}

	@Override
	public ResponseEntity<Void> thresholdsThresholdIdDelete(final String thresholdId) {
		return vnfmThresholdFrontController.deleteById(thresholdId);
	}

	@Override
	public ResponseEntity<Threshold> thresholdsThresholdIdGet(final String thresholdId) {
		return vnfmThresholdFrontController.findById(thresholdId, Threshold.class, Thresholds261Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<String> thresholdsGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		return vnfmThresholdFrontController.search(requestParams, nextpageOpaqueMarker, Threshold.class, Thresholds261Sol003Controller::makeLinks);
	}

	private static void makeLinks(final Threshold threshold) {
		final ThresholdLinks thresholdLinks = new ThresholdLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(Thresholds261Sol003Api.class).thresholdsThresholdIdGet(threshold.getId().toString())).withSelfRel().getHref());
		thresholdLinks.setSelf(self);
		threshold.setLinks(thresholdLinks);
	}

	private static String getSelfLink(final Threshold threshold) {
		return linkTo(methodOn(Thresholds261Sol003Api.class).thresholdsThresholdIdGet(threshold.getId().toString())).withSelfRel().getHref();
	}

}
