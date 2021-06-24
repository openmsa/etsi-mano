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
package com.ubiqube.etsi.mano.service.graph.uow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.service.graph.TestParameters;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

public class VlUow implements UnitOfWork<VnfTask, TestParameters> {

	@Override
	public String getName() {
		return "test-vl";
	}

	@Override
	public String getToscaName() {
		return "test-vl";
	}

	@Override
	public String exec(final TestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task getTaskEntity() {
		// TODO Auto-generated method stub
		return new VnfTask();
	}

	@Override
	public String rollback(final TestParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WfDependency> getDependencies() {
		final List<WfDependency> ret = new ArrayList<>();
		return ret;
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(Network.class, "test-vl", UUID.randomUUID()));
	}

}
