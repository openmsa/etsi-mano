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
public class OrchExecutionResultsImpl<U> implements OrchExecutionResults<U> {
	private final List<OrchExecutionResult<U>> results;

	public OrchExecutionResultsImpl(final List<OrchExecutionResultImpl<U>> all) {
		this.results = all.stream().map(x -> (OrchExecutionResult<U>) x).toList();
	}

	@Override
	public List<OrchExecutionResult<U>> getSuccess() {
		return results.stream().filter(x -> x.getResult() == ResultType.SUCCESS).map(x -> x).toList();
	}

	@Override
	public List<OrchExecutionResult<U>> getErrored() {
		return results.stream().filter(x -> x.getResult() == ResultType.ERRORED).map(x -> x).toList();
	}

	@Override
	public void addAll(final OrchExecutionResults<U> convertResults) {
		results.addAll(convertResults.getErrored());
		results.addAll(convertResults.getSuccess());
	}

}
