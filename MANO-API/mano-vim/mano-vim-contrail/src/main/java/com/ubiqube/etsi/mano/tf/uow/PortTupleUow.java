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

import java.util.List;

import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.tf.ContrailApi;
import com.ubiqube.etsi.mano.tf.entities.PortTupleTask;
import com.ubiqube.etsi.mano.tf.node.PortTupleNode;
import com.ubiqube.etsi.mano.tf.node.ServiceInstanceNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class PortTupleUow extends AbstractUnitOfWork<PortTupleTask> {

	private final SystemConnections vimConnectionInformation;

	private final PortTupleTask task;

	public PortTupleUow(final VirtualTask<PortTupleTask> task, final SystemConnections vimConnectionInformation) {
		super(task, PortTupleNode.class);
		this.vimConnectionInformation = vimConnectionInformation;
		this.task = task.getParameters();
	}

	@Override
	public String execute(final Context context) {
		final ContrailApi api = new ContrailApi();
		final List<String> serviceInstanceId = context.getParent(ServiceInstanceNode.class, task.getServiceInstanceName());
		return api.createPortTuple(vimConnectionInformation, task.getToscaName(), serviceInstanceId.get(0));
	}

	@Override
	public String rollback(final Context context) {
		final ContrailApi api = new ContrailApi();
		api.deletePortTuple(vimConnectionInformation, task.getVimResourceId());
		return null;
	}

}
