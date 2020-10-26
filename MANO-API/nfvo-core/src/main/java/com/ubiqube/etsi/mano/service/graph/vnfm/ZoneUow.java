package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.DnsZoneTask;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ZoneUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final String zoneName;

	public ZoneUow(final DnsZoneTask _dnsZoneTask) {
		super(_dnsZoneTask);
		zoneName = _dnsZoneTask.getDomainName();
	}

	@Override
	public UowType getType() {
		return UowType.DNSZONE;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return vim.createDnsZone(vimConnectionInformation, zoneName);
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteDnsZone(vimConnectionInformation, resourceId);
		return null;
	}

	@Override
	protected String getPrefix() {
		return "zone";
	}

	@Override
	public String toString() {
		return "ZoneUow [zoneName=" + zoneName + "]";
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		// Nothing to do.
	}

}
