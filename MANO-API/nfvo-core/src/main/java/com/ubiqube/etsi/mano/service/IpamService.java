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

import java.util.function.BiFunction;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.ipam.Networks;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.ipam.NetworksJpa;

@Service
public class IpamService {

	private final NetworksJpa networksJpa;

	public IpamService(final NetworksJpa _networksJpa) {
		networksJpa = _networksJpa;
	}

	@Transactional(TxType.NEVER)
	public Networks reserveNetwork(final VimConnectionInformation conn, final BiFunction<VimConnectionInformation, Networks, String> func) {
		final Networks network = networksJpa.findFirstFreeNetwork(conn.getId()).orElseThrow(() -> new NotFoundException("Could not find a free network for Vim Id " + conn));
		network.setVimResource("TEMPORARY_ALLOCATED");
		networksJpa.save(network);
		try {
			final String res = func.apply(conn, network);
			network.setVimResource(res);
		} catch (final RuntimeException e) {
			network.setVimResource(null);
			networksJpa.save(network);
			throw e;
		}
		networksJpa.save(network);
		return network;
	}
}
