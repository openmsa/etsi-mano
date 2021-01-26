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
package com.ubiqube.etsi.mec.mepm.event;

import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.event.ActionType;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MepmActionController {

	private static final Logger LOG = LoggerFactory.getLogger(MepmActionController.class);

	private final MepmActions mepmActions;

	public MepmActionController(final MepmActions _mepmActions) {
		mepmActions = _mepmActions;
	}

	public void dispatch(@NotNull final ActionType actionType, @NotNull final UUID objectId, @NotNull final Map<String, Object> parameters) {
		switch (actionType) {
		case MEPM_INSTANTIATE:
			mepmActions.instantiate(objectId);
			break;
		case MEPM_TERMINATE:
			mepmActions.terminate(objectId);
			break;
		case MEPM_OPERATE:
			mepmActions.operate(objectId);
			break;
		default:
			LOG.warn("Unknown event: {}", actionType);
			break;
		}
	}

}
