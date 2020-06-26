package com.ubiqube.etsi.mano.model.vnf;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PkgmSubscription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents a subscription related to notifications about VNF package management. This one is stored in the repository")
public class SubscriptionObject {

	@ApiModelProperty(value = "")
	@Valid
	private SubscriptionAuthentication subscriptionAuthentication = null;

	@ApiModelProperty(value = "")
	@Valid
	private PkgmSubscription pkgmSubscription = null;

	private ApiTypesEnum api;

	public SubscriptionObject() {
		// Nothing.
	}

	public SubscriptionObject(final SubscriptionAuthentication _subscriptionAuthentication, final PkgmSubscription _PkgmSubscription) {
		super();
		subscriptionAuthentication = _subscriptionAuthentication;
		pkgmSubscription = _PkgmSubscription;
	}

	@JsonProperty("auth")
	public SubscriptionAuthentication getSubscriptionAuthentication() {
		return subscriptionAuthentication;
	}

	public void setSubscriptionAuthentication(final SubscriptionAuthentication _subscriptionsPkgmSubscriptionRequestAuthentication) {
		subscriptionAuthentication = _subscriptionsPkgmSubscriptionRequestAuthentication;
	}

	@JsonProperty("subscriptionsPkgmSubscription")
	public PkgmSubscription getPkgmSubscription() {
		return pkgmSubscription;
	}

	public void setPkgmSubscription(final PkgmSubscription _subscriptionsPkgmSubscription) {
		pkgmSubscription = _subscriptionsPkgmSubscription;
	}

	@JsonProperty("api")
	public ApiTypesEnum getApi() {
		return api;
	}

	public void setApi(final ApiTypesEnum api) {
		this.api = api;
	}

}
