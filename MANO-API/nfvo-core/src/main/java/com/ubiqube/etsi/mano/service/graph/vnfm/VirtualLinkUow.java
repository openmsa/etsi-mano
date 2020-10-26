package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class VirtualLinkUow extends AbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VlProtocolData vlProtocolData;

	private final String name;

	private VnfInstantiatedVirtualLink vnfInstantiedVirtualLink;

	private final NetworkTask networkTask;

	public VirtualLinkUow(final NetworkTask _networkTask, final VnfVl vnfVl) {
		super(_networkTask);
		name = _networkTask.getToscaName();
		// XXX
		vlProtocolData = vnfVl.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next();
		networkTask = _networkTask;
	}

	public VlProtocolData getVlProtocolData() {
		return vlProtocolData;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final String domainName = context.get("dns-name");
		final String zoneId = vim.createDnsZone(vimConnectionInformation, name + "." + domainName);
		vnfInstantiedVirtualLink.setZoneId(zoneId);
		networkTask.setVimZoneId(zoneId);
		return vim.createNetwork(vimConnectionInformation, vlProtocolData, vnfInstantiedVirtualLink.getAliasName(), domainName, null);
	}

	@Override
	public UowType getType() {
		return UowType.VL;
	}

	@Override
	public String toString() {
		return "VirtualLinkUow [name=" + name + "]";
	}

	@Override
	protected String getPrefix() {
		return "vl";
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteVirtualLink(vimConnectionInformation, resourceId);
		vim.deleteDnsZone(vimConnectionInformation, vnfInstantiedVirtualLink.getZoneId());
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		g.addEdge(cache.get("zone"), this);
	}

}
