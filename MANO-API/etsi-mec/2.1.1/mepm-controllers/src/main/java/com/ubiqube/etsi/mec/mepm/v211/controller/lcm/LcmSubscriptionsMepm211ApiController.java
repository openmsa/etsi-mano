package com.ubiqube.etsi.mec.mepm.v211.controller.lcm;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.lcm.Body;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.InlineResponse201;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.SubscriptionLinkList;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.SubscriptionType;

@Controller
public class LcmSubscriptionsMepm211ApiController implements LcmSubscriptionsMepm211Api {

    @Override
    public ResponseEntity<SubscriptionLinkList> appLcmSubscriptionsGET(@Valid final String subscriptionType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse201> appLcmSubscriptionsPOST(@Valid final Body body, @NotNull @Valid final SubscriptionType subscriptionType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> individualSubscriptionDELETE(final String subscriptionId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<InlineResponse201> individualSubscriptionGET(final String subscriptionId, @NotNull @Valid final String subscriptionType) {
        // TODO Auto-generated method stub
        return null;
    }

}
