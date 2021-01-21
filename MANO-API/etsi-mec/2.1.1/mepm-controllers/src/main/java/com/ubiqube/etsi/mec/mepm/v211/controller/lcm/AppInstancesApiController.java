package com.ubiqube.etsi.mec.mepm.v211.controller.lcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBluePrint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
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
	private final MapperFacade mapper;
	private final MepmInstanceController instanceController;

	public AppInstancesApiController(final MapperFacade mapper, final MepmInstanceController appInstanceController) {
		super();
		this.mapper = mapper;
		this.instanceController = appInstanceController;
	}

	@Override
	public ResponseEntity<List<AppInstanceInfo>> appInstanceGET(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
		// TODO Auto-generated method stub
		return null;
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
		final AppBluePrint lcm = instanceController.instantiate(UUID.fromString(appInstanceId));
		final String link = makeSeflftLink(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> appLcmOperatePOST(@Valid final OperateAppRequest body, final String appInstanceId) {
		final VnfOperateRequest req = mapper.map(body, VnfOperateRequest.class);
		final AppBluePrint lcm = instanceController.operate(UUID.fromString(appInstanceId), req);
		final String link = makeSeflftLink(lcm);
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> appLcmTerminatePOST(@Valid final TerminateAppRequest body, final String appInstanceId) {
		final AppBluePrint appBluePrint = instanceController.terminate(UUID.fromString(appInstanceId), CancelModeTypeEnum.fromValue(body.getTerminationType().toString()), body.getGracefulTerminationTimeout());
		final String link = makeSeflftLink(appBluePrint);
		return ResponseEntity.noContent().header(LOCATION, link).build();
	}

	private static String makeSeflftLink(final AppBluePrint appBluePrint) {
		return linkTo(methodOn(AppInstancesApi.class).appInstanceIdGET(appBluePrint.getId().toString())).withSelfRel().getHref();
	}

	private static AppInstanceInfoLinks makeLinks(final AppInstanceInfo instance) {
		final AppInstanceInfoLinks links = new AppInstanceInfoLinks();
		String url = linkTo(methodOn(AppInstancesApi.class).appLcmInstanciatePOST(null, instance.getAppPkgId())).withSelfRel().getHref();
		LinkType instantiateLink = new LinkType();
		instantiateLink.setHref(url);
		links.setInstantiate(instantiateLink);

		url = linkTo(methodOn(AppInstancesApi.class).appLcmOperatePOST(null, instance.getAppPkgId())).withSelfRel().getHref();
		instantiateLink = new LinkType();
		instantiateLink.setHref(url);
		links.setOperate(instantiateLink);

		url = linkTo(methodOn(AppInstancesApi.class).appInstanceIdGET(instance.getAppPkgId())).withSelfRel().getHref();
		instantiateLink = new LinkType();
		instantiateLink.setHref(url);
		links.setSelf(instantiateLink);

		url = linkTo(methodOn(AppInstancesApi.class).appLcmTerminatePOST(null, instance.getAppPkgId())).withSelfRel().getHref();
		instantiateLink = new LinkType();
		instantiateLink.setHref(url);
		links.setTerminate(instantiateLink);
		return links;
	}

}
