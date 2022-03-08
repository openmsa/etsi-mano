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
package com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.VnfContextExtractorTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.nodes.mec.VnfContextExtractorNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfInstantiateNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfPortNode;
import com.ubiqube.etsi.mano.service.graph.vt.NsVtBase;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfContextExtractorVt extends NsVtBase<VnfContextExtractorTask> {

	private final VnfContextExtractorTask task;

	public VnfContextExtractorVt(final VnfContextExtractorTask nt) {
		super(nt);
		this.task = nt;
	}

	@Override
	public List<NamedDependency> getNameDependencies() {
		return List.of(new NamedDependency(VnfInstantiateNode.class, getParameters().getAlias()));
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		final List<NamedDependency> l = new ArrayList<>();
		l.add(new NamedDependency(VnfContextExtractorNode.class, getParameters().getAlias()));
		final NsdPackageVnfPackage nsdvnf = findVnfd(task.getNsdPackage(), task.getVnfdId());
		nsdvnf.getForwardMapping().forEach(x -> l.add(new NamedDependency(VnfPortNode.class, x.getForwardingName())));
		return l;
	}

	private static NsdPackageVnfPackage findVnfd(final NsdPackage nsdPackage, final String vnfdId) {
		return nsdPackage.getVnfPkgIds().stream().filter(x -> x.getVnfPackage().getVnfdId().equals(vnfdId)).findFirst().orElseThrow(() -> new GenericException("Unable to find vnfd [" + vnfdId + "]"));
	}

	@Override
	public String getFactoryProviderId() {
		return "VNF-EXTRACT";
	}

	@Override
	public String getVimProviderId() {
		return "VNF-EXTRACT";
	}

}
