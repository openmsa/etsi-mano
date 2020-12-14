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
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;

public interface VersionService {

	String getVersion();

	boolean isNfvo();

	Object createNotificationVnfPackageOnboardingNotification(UUID subscriptionId, @Nonnull UUID vnfPkgId);

	Object createVnfPackageChangeNotification(UUID subscriptionId, @Nonnull UUID vnfPkgId);

	List<VnfInstance> vnfInstanceGet(MultiValueMap<String, String> params);

	VnfInstance vnfInstancePost(String vnfdId, String vnfInstanceName, String vnfInstanceDescription);

	VnfBlueprint vnfInstanceOperate(@Nonnull UUID uuid, @Nonnull VnfOperateRequest operateVnfRequest);

	VnfBlueprint vnfInstanceScale(@Nonnull UUID uuid, @Nonnull VnfScaleRequest scaleVnfRequest);

	VnfBlueprint vnfInstanceScaleToLevel(@Nonnull UUID uuid, @Nonnull VnfScaleToLevelRequest scaleVnfToLevelRequest);

	VnfBlueprint vnfInstanceTerminate(@Nonnull UUID vnfInstanceId, @Nonnull CancelModeTypeEnum terminationType, Integer gracefulTerminationTimeout);

	VnfBlueprint vnfInstanceInstantiate(@Nonnull UUID vnfInstanceId, @Nonnull VnfInstantiate instantiateVnfRequest);

	void vnfInstanceDelete(@Nonnull UUID vnfInstanceId);

	VnfBlueprint vnfLcmOpOccsGet(@NotNull UUID id);

}
