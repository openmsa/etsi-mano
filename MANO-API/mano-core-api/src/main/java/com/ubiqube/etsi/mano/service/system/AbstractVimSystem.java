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
package com.ubiqube.etsi.mano.service.system;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.sys.System;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <U> Task parameter.
 */
public abstract class AbstractVimSystem<U> implements System<U> {
	@Override
	public final SystemBuilder<UnitOfWork<U>> getImplementation(final OrchestrationService<U> orchestrationService, final VirtualTask<U> virtualTask, final SystemConnections vimConnectionInformation) {
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(SystemConnections.class, VimConnectionInformation.class).byDefault().register();
		final VimConnectionInformation vimConn = mapperFactory.getMapperFacade().map(vimConnectionInformation, VimConnectionInformation.class);
		return getImplementation(orchestrationService, virtualTask, vimConn);
	}

	protected abstract SystemBuilder<UnitOfWork<U>> getImplementation(final OrchestrationService<U> orchestrationService, final VirtualTask<U> virtualTask, VimConnectionInformation vimConnectionInformation);
}
