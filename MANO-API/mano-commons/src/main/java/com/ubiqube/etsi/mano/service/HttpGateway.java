package com.ubiqube.etsi.mano.service;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface HttpGateway {

	Class<?> getVnfPackageClass();

	Class<?> getVnfPackageSubscriptionClass();

	Class<?> getPkgmSubscriptionRequest();

	Class<?> getGrantRequest();

	Class<?> getGrantResponse();

	void makeGrantLinks(Object manoGrant);
}
