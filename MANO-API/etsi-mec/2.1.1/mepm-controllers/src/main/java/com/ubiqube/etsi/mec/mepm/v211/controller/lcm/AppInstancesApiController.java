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

import static com.ubiqube.etsi.mano.Constants.getSingleField;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceInfo;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceInfoLinks;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.CreateAppInstanceRequest;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.InstantiateAppRequest;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.LinkType;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.OperateAppRequest;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.TerminateAppRequest;
import com.ubiqube.etsi.mec.mepm.controller.lcm.MepmInstanceController;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Controller
public class AppInstancesApiController implements AppInstancesApi {
	private static final String LOCATION = "Location";

	private static final String APP_SEARCH_DEFAULT_EXCLUDE_FIELDS = "vimConnectionInfo,instantiatedAppState";

	private static final Set<String> APP_SEARCH_MANDATORY_FIELDS = new HashSet<>(Arrays.asList("id"));

	private final MapperFacade mapper;

	private final MepmInstanceController instanceController;

	private final ManoSearchResponseService searchService;

	public AppInstancesApiController(final MapperFacade mapper, final MepmInstanceController appInstanceController, final ManoSearchResponseService _searchService) {
		super();
		this.mapper = mapper;
		this.instanceController = appInstanceController;
		searchService = _searchService;
	}

	@Override
	public ResponseEntity<String> appInstanceGET(final MultiValueMap<String, String> requestParams) {
		final String filter = getSingleField(requestParams, "filter");
		final List<AppInstance> result = instanceController.query(filter);
		return searchService.search(requestParams, AppInstanceInfo.class, APP_SEARCH_DEFAULT_EXCLUDE_FIELDS, APP_SEARCH_MANDATORY_FIELDS, result, AppInstanceInfo.class, AppInstancesApiController::makeLinks);
	}

	@Override
	public ResponseEntity<Void> appInstanceIdDELETE(final String appInstanceId) {
		instanceController.delete(UUID.fromString(appInstanceId));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<AppInstanceInfo> appInstanceIdGET(final String appInstanceId) {
		final AppInstance instance = instanceController.findById(UUID.fromString(appInstanceId));
		final AppInstanceInfo entity = mapper.map(instance, AppInstanceInfo.class);
		entity.setLinks(makeLinks(entity));
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AppInstanceInfo> appInstancePOST(@Valid final CreateAppInstanceRequest body) {
		final AppInstance instance = instanceController.createInstance(body.getAppDId(), body.getAppInstanceDescription(), body.getAppInstanceName());
		final AppInstanceInfo entity = mapper.map(instance, AppInstanceInfo.class);
		entity.setLinks(makeLinks(entity));
		return ResponseEntity.accepted().body(entity);
	}

	@Override
	public ResponseEntity<Void> appLcmInstanciatePOST(@Valid final InstantiateAppRequest body, final String appInstanceId) {
		final AppBlueprint lcm = instanceController.instantiate(UUID.fromString(appInstanceId));
		final String link = makeSeflftLink(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> appLcmOperatePOST(@Valid final OperateAppRequest body, final String appInstanceId) {
		final VnfOperateRequest req = mapper.map(body, VnfOperateRequest.class);
		final AppBlueprint lcm = instanceController.operate(UUID.fromString(appInstanceId), req);
		final String link = makeSeflftLink(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> appLcmTerminatePOST(@Valid final TerminateAppRequest body, final String appInstanceId) {
		final AppBlueprint appBluePrint = instanceController.terminate(UUID.fromString(appInstanceId), CancelModeTypeEnum.fromValue(body.getTerminationType().toString()), body.getGracefulTerminationTimeout());
		final String link = makeSeflftLink(appBluePrint);
		return ResponseEntity.noContent().header(LOCATION, link).build();
	}

	private static String makeSeflftLink(final AppBlueprint appBluePrint) {
		return linkTo(methodOn(AppInstancesApi.class).appInstanceIdGET(appBluePrint.getId().toString())).withSelfRel().getHref();
	}

	private static AppInstanceInfoLinks makeLinks(final AppInstanceInfo instance) {
		final AppInstanceInfoLinks links = new AppInstanceInfoLinks();

		String url = linkTo(methodOn(AppInstancesApi.class).appInstanceIdGET(instance.getAppPkgId())).withSelfRel().getHref();
		LinkType instantiateLink = new LinkType();
		instantiateLink.setHref(url);
		links.setSelf(instantiateLink);

		url = linkTo(methodOn(AppInstancesApi.class).appLcmInstanciatePOST(null, instance.getAppPkgId())).withSelfRel().getHref();
		instantiateLink = new LinkType();
		instantiateLink.setHref(url);
		links.setInstantiate(instantiateLink);

		url = linkTo(methodOn(AppInstancesApi.class).appLcmOperatePOST(null, instance.getAppPkgId())).withSelfRel().getHref();
		instantiateLink = new LinkType();
		instantiateLink.setHref(url);
		links.setOperate(instantiateLink);

		url = linkTo(methodOn(AppInstancesApi.class).appLcmTerminatePOST(null, instance.getAppPkgId())).withSelfRel().getHref();
		instantiateLink = new LinkType();
		instantiateLink.setHref(url);
		links.setTerminate(instantiateLink);

		instance.setLinks(links);
		return links;
	}

}
