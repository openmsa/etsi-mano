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
package com.ubiqube.etsi.mano.service.tasks;

import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.MonitoringParams;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class MonitoringTask implements VirtualTask<MonitoringTask> {
	private MonitoringParams parameter;

	@Override
	public List<NamedDependency> getNameDependencies() {
		return Arrays.asList(new NamedDependency(Compute.class, parameter.getVnfComputeName()));
	}

	@Override
	public MonitoringTask getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public String getFactoryProviderId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSystemBuilder(final SystemBuilder db) {
		// TODO Auto-generated method stub

	}

	@Override
	public SystemBuilder getSystemBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDeleteTask() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getVimConnectionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameters(final MonitoringTask u) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVimProviderId() {
		return "GNOCCHI";
	}
}
