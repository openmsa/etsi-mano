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
package com.ubiqube.etsi.mec.mepm.service.graph;

import java.util.List;

import com.github.dexecutor.core.task.ExecutionResult;
import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AppReport {
	private final ExecutionResults<UnitOfWork<AppTask, AppParameters>, String> results;

	public AppReport(final ExecutionResults<UnitOfWork<AppTask, AppParameters>, String> _results) {
		results = _results;
	}

	public List<ExecutionResult<UnitOfWork<AppTask, AppParameters>, String>> getSkipped() {
		return results.getSkipped();
	}

	public List<ExecutionResult<UnitOfWork<AppTask, AppParameters>, String>> getSuccess() {
		return results.getSuccess();
	}

	public List<ExecutionResult<UnitOfWork<AppTask, AppParameters>, String>> getErrored() {
		return results.getErrored();
	}

	public List<ExecutionResult<UnitOfWork<AppTask, AppParameters>, String>> getAll() {
		return results.getAll();
	}

}
