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
package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.v2.MonitoringTask;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Compute;

public class MonitoringUow extends VnfAbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;

	public MonitoringUow(final MonitoringTask monitoringTask, final VnfCompute _vnfCompute) {
		super(monitoringTask);
		vnfCompute = _vnfCompute;
	}

	@Override
	public String exec(final VnfParameters params) {
		return null;
	}

	@Override
	protected String getPrefix() {
		return "monitoring";
	}

	@Override
	public String rollback(final VnfParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WfDependency> getDependencies() {
		return Arrays.asList(new WfDependency(Compute.class, vnfCompute.getToscaName()));
	}

	@Override
	public List<WfProduce> getProduce() {
		// TODO Auto-generated method stub
		return null;
	}

}
