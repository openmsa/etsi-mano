package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ZoneUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final String zoneName;

	public ZoneUow(final VnfInstantiatedBase _dnsZoneInstantiated, final String _zoneName) {
		super(_dnsZoneInstantiated, _zoneName.replaceAll("\\.", "_"));
		zoneName = _zoneName;
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

}
