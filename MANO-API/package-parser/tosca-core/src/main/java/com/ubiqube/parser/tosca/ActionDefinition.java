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
package com.ubiqube.parser.tosca;

import java.util.Map;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 * @deprecated This file have been split between callActivity*
 */
@Deprecated
public class ActionDefinition {
	private String delegate;
	private String workflow;
	Map<String, Object> inputs;

	public String getDelegate() {
		return delegate;
	}

	public void setDelegate(final String delegate) {
		this.delegate = delegate;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(final String workflow) {
		this.workflow = workflow;
	}

	public Map<String, Object> getInputs() {
		return inputs;
	}

	public void setInputs(final Map<String, Object> inputs) {
		this.inputs = inputs;
	}

}
