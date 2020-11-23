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
package com.ubiqube.etsi.mano.controller.lcmgrant;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;

/**
 * NFVO+VNFM & VNFM Implementation. TODO: Make terminate Async and this will be
 * generic again.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface VnfInstanceLcm {

	List<VnfInstance> get(final Map<String, String> queryParameters);

	Blueprint get(final UUID id);

	VnfInstance post(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription);

	void delete(@Nonnull final UUID vnfInstanceId);

	Blueprint instantiate(@Nonnull final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest);

	Blueprint terminate(@Nonnull final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout);

	Blueprint scaleToLevel(@Nonnull final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest);

	Blueprint scale(@Nonnull final UUID uuid, final VnfScaleRequest scaleVnfRequest);

	Blueprint operate(@Nonnull final UUID uuid, final VnfOperateRequest operateVnfRequest);

}
