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
package com.ubiqube.etsi.mano.orchestrator.vt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.orchestrator.AbstractVirtualTask;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.TestParameters;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;

public class ProvAVt extends AbstractVirtualTask<TestParameters> {

	@Override
	public List<NamedDependency> getNameDependencies() {
		return new ArrayList<>();
	}

	@Override
	public TestParameters getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProviderId() {
		return "PROVA";
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		return Arrays.asList(new NamedDependency(Network.class, "vl01"));
	}

	@Override
	public boolean isDeleteTask() {
		return false;
	}

	@Override
	public void setParameters(final TestParameters u) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getVimConnectionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
