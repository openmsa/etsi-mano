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
package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class VirtualLinkUow extends AbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VlProtocolData vlProtocolData;

	private final String name;

	private final VnfInstantiatedVirtualLink vnfInstantiedVirtualLink;

	private final String domainName;

	public VirtualLinkUow(final VnfInstantiatedVirtualLink _vnfInstantiedVirtualLink, final VlProtocolData _vlProtocolData, final String _name, final String _domainName) {
		super(_vnfInstantiedVirtualLink, _name);
		vlProtocolData = _vlProtocolData;
		name = _name;
		vnfInstantiedVirtualLink = _vnfInstantiedVirtualLink;
		domainName = _domainName;
	}

	public VlProtocolData getVlProtocolData() {
		return vlProtocolData;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final String zoneId = vim.createDnsZone(vimConnectionInformation, name + "." + domainName);
		vnfInstantiedVirtualLink.setZoneId(zoneId);
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

}
