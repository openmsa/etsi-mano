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
package com.ubiqube.etsi.mano.tf.uow;

import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.tf.ContrailApi;
import com.ubiqube.etsi.mano.tf.entities.ServiceInstanceTask;
import com.ubiqube.etsi.mano.tf.node.ServiceInstanceNode;
import com.ubiqube.etsi.mano.tf.node.ServiceTemplateNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ServiceInstanceUow extends AbstractUnitOfWork<ServiceInstanceTask> {
	private final SystemConnections vimConnectionInformation;
	private final ServiceInstanceTask task;

	public ServiceInstanceUow(final VirtualTask<ServiceInstanceTask> task, final SystemConnections vim) {
		super(task, ServiceInstanceNode.class);
		this.vimConnectionInformation = vim;
		this.task = task.getParameters();
	}

	@Override
	public String execute(final Context context) {
		final ContrailApi api = new ContrailApi();
		final String serviceTemplateId = context.get(ServiceTemplateNode.class, getTask().getParameters().getServiceTemplateId());
		final String left = context.get(Network.class, task.getCpPorts().getIngressVl());
		final String right = context.get(Network.class, task.getCpPorts().getEgressVl());
		return api.createServiceInstance(vimConnectionInformation, getTask().getParameters().getToscaName(), serviceTemplateId, left, right);
	}

	@Override
	public String rollback(final Context context) {
		final ContrailApi api = new ContrailApi();
		api.deleteServiceInstance(vimConnectionInformation, task.getVimResourceId());
		return null;
	}

}
