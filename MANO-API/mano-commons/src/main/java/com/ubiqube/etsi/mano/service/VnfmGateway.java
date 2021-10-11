package com.ubiqube.etsi.mano.service;

public interface VnfmGateway {

	Class<?> getVnfPackageClass();

	Class<?> getVnfPackageSubscriptionClass();

	Class<?> getPkgmSubscriptionRequest();
}
