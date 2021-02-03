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
package com.ubiqube.etsi.mano.service.plan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfStartUow;
import com.ubiqube.etsi.mano.service.plan.contributors.AbstractVnfPlanContributor;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfPlanner extends Planner<VnfTask, VnfPackage, VnfParameters, Blueprint<VnfTask>> {

	public VnfPlanner(final List<AbstractVnfPlanContributor> _planContributors) {
		super((List<? extends PlanContributor<VnfPackage, Blueprint<VnfTask>, VnfTask, VnfParameters>>) ((Object) _planContributors));
	}

	@Override
	protected UnitOfWork<VnfTask, VnfParameters> getStartNode() {
		return new VnfStartUow();
	}

}
