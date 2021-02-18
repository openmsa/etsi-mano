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

package com.ubiqube.etsi.mano.vnfm.v261.controller.nsperfo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.vnfpm.VnfmThresholdController;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.CreateThresholdRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.Threshold;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.ThresholdLinks;

import ma.glasnost.orika.MapperFacade;

@RestController
@RolesAllowed({ "ROLE_NFVO" })
public class ThresholdsSol003Api implements ThresholdsSol003 {
	private final VnfmThresholdController vnfmThresholdController;

	private final MapperFacade mapper;

	public ThresholdsSol003Api(final VnfmThresholdController _vnfmThresholdController, final MapperFacade _mapper) {
		vnfmThresholdController = _vnfmThresholdController;
		mapper = _mapper;
	}

	@Override
	public ResponseEntity<Threshold> thresholdsPost(@Valid final CreateThresholdRequest createThresholdRequest) throws URISyntaxException {
		com.ubiqube.etsi.mano.dao.mano.pm.Threshold res = mapper.map(createThresholdRequest, com.ubiqube.etsi.mano.dao.mano.pm.Threshold.class);
		res = vnfmThresholdController.save(res);
		final Threshold ret = mapper.map(res, Threshold.class);
		ret.setLinks(createLink(res.getId()));
		return ResponseEntity.created(new URI(ret.getLinks().getSelf().getHref())).body(ret);
	}

	private static ThresholdLinks createLink(final UUID id) {
		final ThresholdLinks thresholdLinks = new ThresholdLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(ThresholdsSol003.class).thresholdsThresholdIdGet(id.toString())).withSelfRel().getHref());
		thresholdLinks.setSelf(self);
		return thresholdLinks;
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
		ret.setLinks(createLink(res.getId()));
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<List<Threshold>> thresholdsGet(@Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		final List<com.ubiqube.etsi.mano.dao.mano.pm.Threshold> res = vnfmThresholdController.query(filter);
		final List<Threshold> lst = mapper.mapAsList(res, Threshold.class);
		lst.forEach(x -> x.setLinks(createLink(UUID.fromString(x.getId()))));
		return ResponseEntity.ok(lst);
	}

}
