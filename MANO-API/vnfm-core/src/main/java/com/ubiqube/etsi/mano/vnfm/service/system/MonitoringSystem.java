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
package com.ubiqube.etsi.mano.vnfm.service.system;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.MonitoringTask;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.system.AbstractVimSystem;
import com.ubiqube.etsi.mano.vnfm.service.VnfMonitoringService;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.uow.VnfMonitoringUow2;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MonitoringSystem extends AbstractVimSystem<MonitoringTask> {
	private final VnfMonitoringService vnfMonitoringService;

	public MonitoringSystem(final VnfMonitoringService vnfMonitoringService) {
		super();
		this.vnfMonitoringService = vnfMonitoringService;
	}

	@Override
	public String getProviderId() {
		return "MONITORING";
	}

	@Override
	SystemBuilder<MonitoringTask> getImplementation(final OrchestrationService<MonitoringTask> orchestrationService, final VirtualTask<MonitoringTask> virtualTask, final VimConnectionInformation vimConnectionInformation) {
		return orchestrationService.systemBuilderOf(new VnfMonitoringUow2(virtualTask, vnfMonitoringService, vimConnectionInformation));
	}

}
