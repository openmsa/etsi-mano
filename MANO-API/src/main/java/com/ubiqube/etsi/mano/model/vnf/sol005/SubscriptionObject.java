package com.ubiqube.etsi.mano.model.vnf.sol005;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents a subscription related to notifications about VNF package management. This one is stored in the repository")
public class SubscriptionObject {

	@ApiModelProperty(value = "")
	@Valid
	private SubscriptionsPkgmSubscriptionRequestAuthentication subscriptionsPkgmSubscriptionRequestAuthentication = null;

	@ApiModelProperty(value = "")
	@Valid
	private SubscriptionsPkgmSubscription subscriptionsPkgmSubscription = null;

	public SubscriptionObject() {
		// Nothing.
	}

	public SubscriptionObject(SubscriptionsPkgmSubscriptionRequestAuthentication _subscriptionsPkgmSubscriptionRequestAuthentication, SubscriptionsPkgmSubscription _subscriptionsPkgmSubscription) {
		super();
		subscriptionsPkgmSubscriptionRequestAuthentication = _subscriptionsPkgmSubscriptionRequestAuthentication;
		subscriptionsPkgmSubscription = _subscriptionsPkgmSubscription;
	}

	@JsonProperty("auth")
	public SubscriptionsPkgmSubscriptionRequestAuthentication getSubscriptionsPkgmSubscriptionRequestAuthentication() {
		return subscriptionsPkgmSubscriptionRequestAuthentication;
	}

	public void setSubscriptionsPkgmSubscriptionRequestAuthentication(SubscriptionsPkgmSubscriptionRequestAuthentication _subscriptionsPkgmSubscriptionRequestAuthentication) {
		subscriptionsPkgmSubscriptionRequestAuthentication = _subscriptionsPkgmSubscriptionRequestAuthentication;
	}

	@JsonProperty("subscriptionsPkgmSubscription")
	public SubscriptionsPkgmSubscription getSubscriptionsPkgmSubscription() {
		return subscriptionsPkgmSubscription;
	}

	public void setSubscriptionsPkgmSubscription(SubscriptionsPkgmSubscription _subscriptionsPkgmSubscription) {
		subscriptionsPkgmSubscription = _subscriptionsPkgmSubscription;
	}

}
