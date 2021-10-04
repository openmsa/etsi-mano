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
package com.ubiqube.etsi.mano.nfvo.service.plan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.nfvo.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.nfvo.service.graph.nfvo.NsStartUow;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.AbstractNsContributor;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.plan.Planner;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsPlanner extends Planner<NsTask, NsdPackage, NsParameters, Blueprint<NsTask, NsdInstance>> {

	public NsPlanner(final List<AbstractNsContributor> _planContributors) {
		super((List<? extends PlanContributor<NsdPackage, Blueprint<NsTask, NsdInstance>, NsTask, NsParameters>>) (Object)_planContributors);
	}

	@Override
	protected UnitOfWork<NsTask, NsParameters> getStartNode() {
		return new NsStartUow();
	}

}
