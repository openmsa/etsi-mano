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
package com.ubiqube.etsi.mano.vnfm.service.system;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.vnfm.SecurityGroupTask;
import com.ubiqube.etsi.mano.dao.mano.vnfm.SecurityRuleTask;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.system.AbstractVimSystem;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.uow.SecurityGroupUowV2;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.uow.SecurityRuleUowV2;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.SecurityRuleVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class SecurityGroupSystem extends AbstractVimSystem<SecurityGroupTask> {
	private final Vim vim;

	public SecurityGroupSystem(final Vim vim, final VimManager vimManager) {
		super(vimManager);
		this.vim = vim;
	}

	@Override
	public String getProviderId() {
		return "SECURITY_GROUP";
	}

	@Override
	protected SystemBuilder getImplementation(final OrchestrationService<SecurityGroupTask> orchestrationService, final VirtualTask<SecurityGroupTask> virtualTask, final VimConnectionInformation vimConnectionInformation) {
		final SystemBuilder s = orchestrationService.createEmptySystemBuilder();
		final SecurityGroupUowV2 src = new SecurityGroupUowV2(virtualTask, vim, vimConnectionInformation);
		final SecurityRuleTask task = new SecurityRuleTask(virtualTask.getParameters().getAlias(), virtualTask.getParameters().getSecurityGroup(), virtualTask.getParameters().getToscaName());
		final SecurityRuleUowV2 dst = new SecurityRuleUowV2(new SecurityRuleVt(task), vim, vimConnectionInformation);
		s.add(src, dst);
		return s;
	}

}
