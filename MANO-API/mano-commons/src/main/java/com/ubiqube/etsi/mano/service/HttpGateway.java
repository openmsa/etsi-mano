package com.ubiqube.etsi.mano.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;

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

	String getUrlFor(ApiVersionType type);

	Class<?> getVnfInstanceClass();

	ParameterizedTypeReference<List<?>> getVnfInstanceListParam();

	Object createVnfInstanceRequest(String vnfdId, String vnfInstanceName, String vnfInstanceDescription);

	Class<?> getVnfInstanceInstantiateRequestClass();

	Class<?> getVnfLcmOpOccs();

	Object createVnfInstanceTerminate(CancelModeTypeEnum terminationType, Integer gracefulTerminationTimeout);

	Class<?> getVnfInstanceScaleToLevelRequest();

	Class<?> getVnfInstanceScaleRequest();

	Class<?> getVnfInstanceOperateRequest();

	Class<?> getVnfInstanceChangeExtConnRequest();
}
