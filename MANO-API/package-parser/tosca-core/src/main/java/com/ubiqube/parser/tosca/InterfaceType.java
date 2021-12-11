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

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class InterfaceType extends ToscaBaseEntity {
	private ToscaProperties inputs;
	// In V1.0 Json any setter is usefull, while not in V1.3
	@JsonAnySetter
	private Map<String, OperationDefinition> operations;
	private NotificationType notifications;

	public ToscaProperties getInputs() {
		return inputs;
	}

	public void setInputs(final ToscaProperties inputs) {
		this.inputs = inputs;
	}

	public Map<String, OperationDefinition> getOperations() {
		return operations;
	}

	public void setOperations(final Map<String, OperationDefinition> operations) {
		this.operations = operations;
	}

	public Object getNotifications() {
		return notifications;
	}

	public void setNotifications(final NotificationType notifications) {
		this.notifications = notifications;
	}

}
