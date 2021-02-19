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
package com.ubiqube.etsi.mano.vnfm.v261.controller.vnfpm;

import static com.ubiqube.etsi.mano.Constants.getSingleField;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.vnfpm.VnfmThresholdController;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.CreateThresholdRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.Threshold;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.ThresholdLinks;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Thresholds261Sol002ApiController implements Thresholds261Sol002Api {
	private static final Set<String> VNFTHR_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	private static final String VNFTHR_SEARCH_DEFAULT_EXCLUDE_FIELDS = null;

	private final MapperFacade mapper;

	private final VnfmThresholdController vnfmThresholdController;

	private final ManoSearchResponseService searchService;

	public Thresholds261Sol002ApiController(final MapperFacade mapper, final ManoSearchResponseService searchService, final VnfmThresholdController _vnfmThresholdController) {
		super();
		this.mapper = mapper;
		this.searchService = searchService;
		vnfmThresholdController = _vnfmThresholdController;
	}

	@Override
	public ResponseEntity<String> thresholdsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		final String filter = getSingleField(requestParams, "filter");
		final List<com.ubiqube.etsi.mano.dao.mano.pm.Threshold> result = vnfmThresholdController.query(filter);
		final Consumer<Threshold> setLink = x -> x.setLinks(makeLinks(x));
		return searchService.search(requestParams, Threshold.class, VNFTHR_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNFTHR_SEARCH_MANDATORY_FIELDS, result, Threshold.class, setLink);
	}

	@Override
	public ResponseEntity<Threshold> thresholdsPost(@Valid final CreateThresholdRequest createThresholdRequest) throws URISyntaxException {
		com.ubiqube.etsi.mano.dao.mano.pm.Threshold res = mapper.map(createThresholdRequest, com.ubiqube.etsi.mano.dao.mano.pm.Threshold.class);
		res = vnfmThresholdController.save(res);
		final Threshold ret = mapper.map(res, Threshold.class);
		ret.setLinks(makeLinks(ret));
		return ResponseEntity.created(new URI(ret.getLinks().getSelf().getHref())).body(ret);
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
		ret.setLinks(makeLinks(ret));
		return ResponseEntity.ok(ret);
	}

	private static ThresholdLinks makeLinks(final Threshold x) {
		final ThresholdLinks links = new ThresholdLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(Thresholds261Sol002Api.class).thresholdsThresholdIdGet(x.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();
		link.setHref("");
		// links.setObjects(link);

		x.setLinks(links);
		return links;
	}

}
