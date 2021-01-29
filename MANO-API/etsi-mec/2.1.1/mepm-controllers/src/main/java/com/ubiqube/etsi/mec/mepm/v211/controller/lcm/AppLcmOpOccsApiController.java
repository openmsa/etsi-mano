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
package com.ubiqube.etsi.mec.mepm.v211.controller.lcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceLcmOpOcc;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceLcmOpOccLinks;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.LinkType;
import com.ubiqube.etsi.mec.mepm.controller.lcm.MepmLcmController;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Controller
public class AppLcmOpOccsApiController implements AppLcmOpOccsApi {

	private final MepmLcmController mepmLcmController;

	private final MapperFacade mapper;

	public AppLcmOpOccsApiController(final MepmLcmController _mepmLcmController, final MapperFacade _mapper) {
		mepmLcmController = _mepmLcmController;
		mapper = _mapper;
	}

	@Override
	public ResponseEntity<String> appLcmOpOccsGET(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
		final List<AppBlueprint> resultsDb = mepmLcmController.query(filter);
		final List<AppInstanceLcmOpOcc> results = resultsDb.stream()
				.map(x -> mapper.map(x, AppInstanceLcmOpOcc.class))
				.collect(Collectors.toList());
		results.forEach(x -> x.setLinks(makeLink(x)));
		final ObjectMapper mapperForView = MapperForView.getMapperForView(excludeFields, fields, null, null);
		try {
			return new ResponseEntity<>(mapperForView.writeValueAsString(results), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	private static AppInstanceLcmOpOccLinks makeLink(final AppInstanceLcmOpOcc x) {
		final AppInstanceLcmOpOccLinks links = new AppInstanceLcmOpOccLinks();

		final LinkType appLink = new LinkType();
		appLink.setHref(linkTo(methodOn(AppLcmOpOccsApi.class).appLcmOpOccsbyIdGET(x.getId())).withSelfRel().getHref());
		links.setAppInstance(appLink);
		return links;
	}

	@Override
	public ResponseEntity<AppInstanceLcmOpOcc> appLcmOpOccsbyIdGET(final String appLcmOpOccId) {
		final AppBlueprint resultDb = mepmLcmController.findById(UUID.fromString(appLcmOpOccId));
		final AppInstanceLcmOpOcc entity = mapper.map(resultDb, AppInstanceLcmOpOcc.class);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

}
