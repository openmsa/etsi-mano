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
