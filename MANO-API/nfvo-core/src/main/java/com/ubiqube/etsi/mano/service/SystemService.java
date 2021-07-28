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
package com.ubiqube.etsi.mano.service;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.entities.Systems;
import com.ubiqube.etsi.mano.repository.jpa.SystemsJpa;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class SystemService {
	private final MapperFacade mapper;
	private final SystemsJpa systemJpa;

	public SystemService(final MapperFacade mapper, final SystemsJpa systemJpa) {
		super();
		this.mapper = mapper;
		this.systemJpa = systemJpa;
	}

	/**
	 * A VIM is a monolithic stack of sub-systems.
	 *
	 * @param vimConnectionInformation
	 * @return The registered system.
	 */
	public Systems registerVim(final VimConnectionInformation vimConnectionInformation) {
		if ("OPENSTACK_V3".equals(vimConnectionInformation.getVimType())) {
			return registerOpenStask(vimConnectionInformation);
		}
		throw new GenericException("Unable to find vim of type: " + vimConnectionInformation.getVimType());
	}

	private Systems registerOpenStask(final VimConnectionInformation vimConnectionInformation) {
		final Systems sys = new Systems();
		SystemConnections sc = mapper.map(vimConnectionInformation, SystemConnections.class);
		sc.setVimType("COMPUTE");
		sys.add(sc);
		sc = mapper.map(vimConnectionInformation, SystemConnections.class);
		sc.setVimType("NETWORK");
		sys.add(sc);
		sc = mapper.map(vimConnectionInformation, SystemConnections.class);
		sc.setVimType("DNS");
		sys.add(sc);
		sc = mapper.map(vimConnectionInformation, SystemConnections.class);
		sc.setVimType("MONITORING");
		sys.add(sc);
		sc = mapper.map(vimConnectionInformation, SystemConnections.class);
		sc.setVimType("VNFEXTCP");
		sys.add(sc);
		return systemJpa.save(sys);
	}
}