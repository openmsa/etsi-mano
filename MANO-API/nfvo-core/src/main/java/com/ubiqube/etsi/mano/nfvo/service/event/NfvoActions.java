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
package com.ubiqube.etsi.mano.nfvo.service.event;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsOrchestrationAdapter;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsWorkflow;
import com.ubiqube.etsi.mano.nfvo.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.service.NsScaleStrategy;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.event.AbstractGenericAction;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NfvoActions extends AbstractGenericAction {
	public NfvoActions(final NsWorkflow workflow, final VimResourceService vimResourceService, final NsOrchestrationAdapter orchestrationAdapter, final NsScaleStrategy nsScaleStrategy) {
		super(workflow, vimResourceService, orchestrationAdapter, nsScaleStrategy);
	}

	@Override
	protected GenericExecParams buildContext(final VimConnectionInformation vimConnection, final Vim vim, final Blueprint blueprint, final Instance vnfInstance) {
		final Map<String, String> context = blueprint.getParameters().getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		// Add all present VL if any.
		return new NsParameters(vim, vimConnection, context, null);
	}

	public void heal(@NotNull final UUID objectId) {
		// TODO Auto-generated method stub
	}

}
