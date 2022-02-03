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
package com.ubiqube.etsi.mano.service.vim.sfc;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsSfcTask;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.sys.System;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class OsSfcSystem implements System<NsSfcTask> {

	@Override
	public String getProviderId() {
		return "OPENSTACK_V3";
	}

	@Override
	public SystemBuilder getImplementation(final OrchestrationService<NsSfcTask> orchestrationService, final VirtualTask<NsSfcTask> virtualTask, final SystemConnections vim) {
		final SystemBuilder<NsSfcTask> builder = orchestrationService.createEmptySystemBuilder();
		// builder.add(src, dest);
		return null;
	}

}
