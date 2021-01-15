package com.ubiqube.etsi.mec.meo.v211.controller.pkg;

import javax.validation.Valid;

import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.controller.SubscriptionAbstractApi;
import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionManagement;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscription;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscriptionInfo;

import ma.glasnost.orika.MapperFacade;

@Controller
public class PkgSubscriptions211MepmApiController extends SubscriptionAbstractApi<AppPkgSubscriptionInfo> implements PkgSubscriptions211MepmApi {

	public PkgSubscriptions211MepmApiController(final VnfSubscriptionManagement _vnfSubscriptionManagement, final MapperFacade _mapper, final EntityLinks _entityLinks) {
		super(_vnfSubscriptionManagement, _mapper, _entityLinks, AppPkgSubscriptionInfo.class, SubscriptionType.MEOPKG);
	}

	@Override
	public ResponseEntity<AppPkgSubscriptionInfo> subscriptionsPost(@Valid final AppPkgSubscription body) {
		return subscriptionPost(body);
	}

}
