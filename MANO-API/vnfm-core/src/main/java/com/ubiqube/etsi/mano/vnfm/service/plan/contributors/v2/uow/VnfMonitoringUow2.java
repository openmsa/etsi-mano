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
package com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.uow;

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.MonitoringTask;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Monitoring;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.vnfm.service.VnfMonitoringService;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfMonitoringUow2 extends AbstractUowV2<MonitoringTask> {
	private final VnfMonitoringService vnfMonitoringService;
	private final VimConnectionInformation vimConnectionInformation;

	public VnfMonitoringUow2(final VirtualTask<MonitoringTask> task, final VnfMonitoringService vnfMonitoringService, final VimConnectionInformation vimConnectionInformation) {
		super(task, Monitoring.class);
		this.vnfMonitoringService = vnfMonitoringService;
		this.vimConnectionInformation = vimConnectionInformation;
	}

	@Override
	public String execute(final Context context) {
		final MonitoringTask params = getTask().getParameters();
		final List<String> l = context.getParent(Compute.class, params.getVnfCompute().getToscaName());
		final String instanceId = l.get(0);
		return vnfMonitoringService.registerMonitoring(instanceId, params.getMonitoringParams(), vimConnectionInformation);
	}

	@Override
	public String rollback(final Context context) {
		final MonitoringTask params = getTask().getParameters();
		vnfMonitoringService.unregister(params.getVimResourceId());
		return null;
	}

}
