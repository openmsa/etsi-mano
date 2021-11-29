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

import java.util.List;

import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <U> Parameters.
 */
public interface VirtualTask<U> {

	List<NamedDependency> getNameDependencies();

	List<NamedDependency> getNamedProduced();

	String getFactoryProviderId();

	String getVimProviderId();

	String getVimConnectionId();

	U getParameters();

	void setParameters(U u);

	void setSystemBuilder(SystemBuilder<U> db);

	SystemBuilder<U> getSystemBuilder();

	boolean isDeleteTask();

	String getName();

	String getAlias();
}
