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

import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_MANDATORY_FIELDS;
import static com.ubiqube.etsi.mano.Constants.getSingleField;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
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

	public static final String APP_SEARCH_DEFAULT_EXCLUDE_FIELDS = "virtualComputeDescriptor,swImageDescriptor,virtualStorageDescriptor,appExtCpd,terminateAppInstanceOpConfig,changeAppInstanceStateOpConfig";

	private final MepmLcmController mepmLcmController;

	private final MapperFacade mapper;

	private final ManoSearchResponseService searchService;

	public AppLcmOpOccsApiController(final MepmLcmController _mepmLcmController, final MapperFacade _mapper, final ManoSearchResponseService _searchService) {
		mepmLcmController = _mepmLcmController;
		mapper = _mapper;
		searchService = _searchService;
	}

	@Override
	public ResponseEntity<String> appLcmOpOccsGET(@Nonnull @RequestParam final MultiValueMap<String, String> requestParams) {
		final String filter = getSingleField(requestParams, "filter");
		final List<AppBlueprint> resultsDb = mepmLcmController.query(filter);
		final Consumer<AppInstanceLcmOpOcc> setLink = x -> x.setLinks(makeLink(x));
		return searchService.search(requestParams, APP_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNF_SEARCH_MANDATORY_FIELDS, resultsDb, AppInstanceLcmOpOcc.class, setLink);

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
