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
package com.ubiqube.etsi.mano.orchestrator;

import java.util.List;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OrchExecutionResultsImpl implements OrchExecutionResults {
	private final List<OrchExecutionResultImpl> results;

	public OrchExecutionResultsImpl(final List<OrchExecutionResultImpl> all) {
		this.results = all;
	}

	@Override
	public List<? extends OrchExecutionResult> getSuccess() {
		return results.stream().filter(x -> x.getResult() == ResultType.SUCCESS).toList();
	}

	@Override
	public List getErrored() {
		return results.stream().filter(x -> x.getResult() == ResultType.ERRORED).toList();
	}

	@Override
	public void addAll(final OrchExecutionResults convertResults) {
		results.addAll(convertResults.getErrored());
		results.addAll(convertResults.getSuccess());
	}

}
