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
package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.io.Serializable;
import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.UnitOfWorkBase;
import com.ubiqube.etsi.mano.service.vim.Vim;

public interface NsUnitOfWork extends UnitOfWorkBase, Serializable {

	String exec(final VimConnectionInformation vimConnectionInformation, VnfmInterface vnfm, Vim vim, Map<String, String> context);

	NsInstantiatedBase getResourceHandleEntity();

	String rollback(VimConnectionInformation vimConnectionInformation, VnfmInterface vnfm, Vim vim, String resourceId, Map<String, String> context);

}
