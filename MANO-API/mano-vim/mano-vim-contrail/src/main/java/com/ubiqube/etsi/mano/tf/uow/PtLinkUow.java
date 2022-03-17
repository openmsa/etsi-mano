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
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfPortNode;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.tf.ContrailApi;
import com.ubiqube.etsi.mano.tf.entities.PtLinkTask;
import com.ubiqube.etsi.mano.tf.node.PortTupleNode;
import com.ubiqube.etsi.mano.tf.node.PtLinkNode;
import com.ubiqube.etsi.mano.tf.vt.PtLinkVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class PtLinkUow extends AbstractUnitOfWork<PtLinkTask> {
	private final SystemConnections vimConnectionInformation;
	private final PtLinkTask task;

	public PtLinkUow(final PtLinkVt ptLinkVt, final SystemConnections vim) {
		super(ptLinkVt, PtLinkNode.class);
		this.vimConnectionInformation = vim;
		this.task = ptLinkVt.getParameters();
	}

	@Override
	public String execute(final Context context) {
		final ContrailApi api = new ContrailApi();
		final String leftUuid = context.get(VnfPortNode.class, task.getLeftPortId());
		final String portTupleId = context.get(PortTupleNode.class, task.getPortTupleName());
		api.updatePort(vimConnectionInformation, leftUuid, portTupleId, "left");
		final String rightUuid = context.get(VnfPortNode.class, task.getRightPortId());
		api.updatePort(vimConnectionInformation, rightUuid, portTupleId, "right");
		return null;
	}

	@Override
	public String rollback(final Context context) {
		return null;
	}

}
