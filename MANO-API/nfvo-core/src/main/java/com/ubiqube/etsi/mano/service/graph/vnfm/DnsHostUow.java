package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class DnsHostUow extends AbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public DnsHostUow(final Task _task) {
		super(_task);
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return null;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		// TODO Auto-generated method stub

	}

	@Override
	public UowType getType() {
		return UowType.DNSHOST;
	}

	@Override
	protected String getPrefix() {
		return "dns-host";
	}

}
