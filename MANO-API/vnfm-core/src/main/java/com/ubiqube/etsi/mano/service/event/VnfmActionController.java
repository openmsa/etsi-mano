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
package com.ubiqube.etsi.mano.service.event;

import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VnfmActionController {

	private static final Logger LOG = LoggerFactory.getLogger(VnfmActionController.class);

	private final VnfmActions vnfmActions;

	public VnfmActionController(final VnfmActions vnfmActions) {
		super();
		this.vnfmActions = vnfmActions;
	}

	public void dispatch(final ActionType eventType, @NotNull final UUID objectId, final Map<String, Object> parameters) {
		switch (eventType) {
		case VNF_SCALE_TO_LEVEL:
			vnfmActions.scaleToLevel(objectId);
			break;
		case VNF_INSTANTIATE:
			vnfmActions.instantiate(objectId);
			break;
		case VNF_TERMINATE:
			vnfmActions.terminate(objectId);
			break;
		case VNF_OPERATE:
			vnfmActions.vnfOperate(objectId);
			break;
		case VNF_CHANGE_CONN:
			vnfmActions.vnfChangeVnfConn(objectId);
			break;
		default:
			LOG.warn("Unknown event: {}", eventType);
			break;
		}
	}

}
