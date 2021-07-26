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
package com.ubiqube.etsi.mano.service.vim;

import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.sys.ServerGroup;
import com.ubiqube.etsi.mano.service.vim.mon.VimMonitoring;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface Vim {

	String getType();

	@NotNull
	Network network(final VimConnectionInformation vimConnectionInformation);

	@NotNull
	Storage storage(final VimConnectionInformation vimConnectionInformation);

	@NotNull
	Dns dns(final VimConnectionInformation vimConnectionInformation);

	@NotNull
	VimMonitoring getMonitoring(VimConnectionInformation vimConnectionInformation);

	@Nonnull
	String getOrCreateFlavor(VimConnectionInformation vimConnectionInformation, String name, int numVcpu, long virtualMemorySize, long disk);

	String createCompute(VimConnectionInformation vimConnectionInformation, String instanceName, String flavorId, String imageId, List<String> networks, List<String> storages, String cloudInitData);

	void deleteCompute(VimConnectionInformation vimConnectionInformation, String resourceId);

	void startServer(VimConnectionInformation vimConnectionInformation, String resourceId);

	void stopServer(VimConnectionInformation vimConnectionInformation, String resourceId);

	List<ServerGroup> getServerGroup(final VimConnectionInformation vimConnectionInformation);

	List<String> getZoneAvailableList(VimConnectionInformation vimConnectionInformation);

	ResourceQuota getQuota(final VimConnectionInformation vimConnectionInformation);

	List<VimCapability> getCaps(final VimConnectionInformation vimConnectionInformation);

	void allocateResources(VimConnectionInformation vimConnectionInformation, GrantInformationExt x);

	void freeResources(VimConnectionInformation vimConnectionInformation, GrantInformationExt x);

}
