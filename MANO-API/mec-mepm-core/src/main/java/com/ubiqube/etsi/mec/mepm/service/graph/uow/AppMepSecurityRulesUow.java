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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mec.pkg.TrafficRuleDescriptor;
import com.ubiqube.etsi.mano.orchestrator.nodes.mec.MepTrafficRulesNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mec.mepm.service.graph.AppParameters;
import com.ubiqube.etsi.mec.mepm.service.graph.mepm.MepSecurityRulesTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AppMepSecurityRulesUow extends AppAbstractUnitOfWork {

	private final MepSecurityRulesTask task;

	public AppMepSecurityRulesUow(final MepSecurityRulesTask _computeTask) {
		super(_computeTask);
		task = _computeTask;
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;

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
		final Set<TrafficRuleDescriptor> rules = task.getAppPkg().getAppTrafficRule();
		if (rules.isEmpty()) {
			return new ArrayList<>();
		}
		return Arrays.asList(new WfDependency(Network.class, task.getToscaName()));
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(MepTrafficRulesNode.class, task.getToscaName(), task.getId()));
	}

	@Override
	protected String getPrefix() {
		return "app-trafrul";
	}

}
