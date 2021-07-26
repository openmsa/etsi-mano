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
package com.ubiqube.etsi.mec.mepm.service.graph.uow;

import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.orchestrator.nodes.mec.MepDnsRulesNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mec.mepm.service.graph.AppParameters;
import com.ubiqube.etsi.mec.mepm.service.graph.mepm.MepDnsRulesTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AppMepDnsUow extends AppAbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final MepDnsRulesTask task;

	public AppMepDnsUow(final MepDnsRulesTask x) {
		super(x);
		task = x;
	}

	@Override
	public String exec(final AppParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rollback(final AppParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WfDependency> getDependencies() {
		return Arrays.asList(new WfDependency(Compute.class, task.getToscaName()));
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(MepDnsRulesNode.class, task.getToscaName(), task.getId()));
	}

	@Override
	protected String getPrefix() {
		return "app-dns";
	}

}
