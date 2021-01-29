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
package com.ubiqube.etsi.mec.mepm.controller.lcm;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;

public interface MepmInstanceController {

	void delete(UUID fromString);

	AppInstance findById(UUID fromString);

	AppInstance createInstance(@NotNull String appDId, String appInstanceDescription, String appInstanceName);

	AppBlueprint terminate(UUID fromString, @NotNull @Valid CancelModeTypeEnum terminationType, Integer gracefulTerminationTimeout);

	AppBlueprint operate(UUID fromString, VnfOperateRequest req);

	AppBlueprint instantiate(UUID fromString);

}
