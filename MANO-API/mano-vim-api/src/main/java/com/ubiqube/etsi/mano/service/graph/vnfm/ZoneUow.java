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
import com.ubiqube.etsi.mano.dao.mano.dto.VnfInstantiatedBase;
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
