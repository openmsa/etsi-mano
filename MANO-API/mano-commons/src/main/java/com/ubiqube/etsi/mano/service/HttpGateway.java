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
package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.GrantInterface;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface HttpGateway {

	Class<?> getVnfPackageClass();

	Object createVnfPackageRequest(Map<String, String> userDefinedData);

	ParameterizedTypeReference<List<Class<?>>> getVnfPackageClassList();

	Class<?> getVnfPackageSubscriptionClass();

	Class<?> getPkgmSubscriptionRequest();

	Class<?> getGrantRequest();

	Class<?> getGrantResponse();

	Object createGrantRequest(GrantInterface grant);

	void makeGrantLinks(Object manoGrant);

	String getUrlFor(ApiVersionType type);

	Class<?> getVnfInstanceClass();

	ParameterizedTypeReference<List<Class<?>>> getVnfInstanceListParam();

	ParameterizedTypeReference<List<Class<?>>> getListVnfLcmOpOccs();

	ParameterizedTypeReference<List<Class<?>>> getNsdPackageClassList();

	Object createVnfInstanceRequest(String vnfdId, String vnfInstanceName, String vnfInstanceDescription);

	Class<?> getVnfInstanceInstantiateRequestClass();

	Class<?> getVnfLcmOpOccs();

	Object createVnfInstanceTerminate(CancelModeTypeEnum terminationType, Integer gracefulTerminationTimeout);

	Class<?> getVnfInstanceScaleToLevelRequest();

	Class<?> getVnfInstanceScaleRequest();

	Class<?> getVnfInstanceOperateRequest();

	Class<?> getVnfInstanceChangeExtConnRequest();

	Object createVnfPackageChangeNotification(UUID subscriptionId, UUID vnfPkgId);

	Object createNotificationVnfPackageOnboardingNotification(UUID idsubscriptionId, UUID vnfPkgId);

	Object createNotificationVnfIdentifierCreationNotification(UUID subscriptionId, UUID vnfInstanceId);

	Object createNotificationVnfIdentifierDeletionNotification(UUID subscriptionId, UUID vnfInstanceId);

	Object createNotificationVnfLcmOperationOccurrenceNotification(UUID subscriptionId, UUID vnfLcmOpOccsId);

	String getVersion();

	Optional<String> getHeaderVersion(final ApiVersionType apiVersionType);

	Class<?> getNsdPackageClass();

	Object createNsdPackageRequest(Map<String, Object> userDefinedData);
}
