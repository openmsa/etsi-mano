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
package com.ubiqube.etsi.mano.service.graph;

import java.util.List;

import com.github.dexecutor.core.task.ExecutionResult;
import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;

public class VnfReport {

	private final ExecutionResults<UnitOfWork<VnfTask, VnfParameters>, String> results;

	public VnfReport(final ExecutionResults<UnitOfWork<VnfTask, VnfParameters>, String> _results) {
		results = _results;
	}

	public List<ExecutionResult<UnitOfWork<VnfTask, VnfParameters>, String>> getSkipped() {
		return results.getSkipped();
	}

	public List<ExecutionResult<UnitOfWork<VnfTask, VnfParameters>, String>> getSuccess() {
		return results.getSuccess();
	}

	public List<ExecutionResult<UnitOfWork<VnfTask, VnfParameters>, String>> getErrored() {
		return results.getErrored();
	}

	public List<ExecutionResult<UnitOfWork<VnfTask, VnfParameters>, String>> getAll() {
		return results.getAll();
	}

}
