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
package com.ubiqube.etsi.mano.service.event;

import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <P>
 * @param <B>
 */
public interface Workflow<P extends PackageBase, B extends Blueprint, R extends Report> {

	void setWorkflowBlueprint(P vnfPkg, B blueprint, Set<ScaleInfo> newScale);

	R execDelete(B localPlan, GenericExecParams vparams);

	R execCreate(B localPlan, GenericExecParams params);

}
