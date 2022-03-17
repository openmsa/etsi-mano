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
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.tf.ContrailApi;
import com.ubiqube.etsi.mano.tf.entities.ServiceTemplateTask;
import com.ubiqube.etsi.mano.tf.node.ServiceTemplateNode;

public class ServiceTemplateUow extends AbstractUnitOfWork<ServiceTemplateTask> {
	private final SystemConnections vimConnectionInformation;

	public ServiceTemplateUow(final VirtualTask<ServiceTemplateTask> task, final SystemConnections vimConnectionInformation) {
		super(task, ServiceTemplateNode.class);
		this.vimConnectionInformation = vimConnectionInformation;
	}

	@Override
	public String execute(final Context context) {
		final ContrailApi api = new ContrailApi();
		return api.createServiceTemplate(vimConnectionInformation, getTask().getParameters().getToscaName());
	}

	@Override
	public String rollback(final Context context) {
		final ContrailApi api = new ContrailApi();
		api.deleteServiceTemplate(vimConnectionInformation, getTask().getParameters().getVimResourceId());
		return null;
	}

}
