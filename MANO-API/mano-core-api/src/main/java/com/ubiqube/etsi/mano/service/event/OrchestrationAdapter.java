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
package com.ubiqube.etsi.mano.service.event;

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.WorkflowEvent;
import com.ubiqube.etsi.mano.service.vim.Vim;

public interface OrchestrationAdapter<B extends Task, V extends VnfInstance> {

	void createLiveInstance(@NotNull Instance vnfInstance, String il, Task task, @NotNull Blueprint<? extends Task, ? extends Instance> blueprint, String vimResourceId);

	void deleteLiveInstance(UUID removedLiveInstance);

	Blueprint<B, V> getBluePrint(UUID blueprintId);

	Instance getInstance(UUID blueprintId);

	PackageBase getPackage(Instance vnfInstance);

	Instance getInstance(Blueprint<B, V> blueprint);

	Blueprint<B, V> save(Blueprint blueprint);

	Instance save(Instance vnfInstance);

	GenericExecParams createParameter(VimConnectionInformation vimConnection, Vim vim, HashMap<String, String> hashMap, Object object);

	Blueprint<B, V> updateState(Blueprint localPlan, OperationStatusType processing);

	void fireEvent(WorkflowEvent instantiateProcessing, @Nonnull UUID id);

}
