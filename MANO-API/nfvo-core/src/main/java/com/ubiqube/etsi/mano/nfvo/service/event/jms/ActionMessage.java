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
package com.ubiqube.etsi.mano.nfvo.service.event.jms;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.service.event.ActionType;

public class ActionMessage {
	@NotNull
	private ActionType actionType = ActionType.UNKNOW;
	@NotNull
	private UUID objectId;
	@NotNull
	private Map<String, Object> parameters = new HashMap<>();

	public ActionMessage() {
		objectId = UUID.randomUUID();
	}

	public ActionMessage(@NotNull final ActionType actionType, @NotNull final UUID objectId, @NotNull final Map<String, Object> parameters) {
		super();
		this.actionType = actionType;
		this.objectId = objectId;
		this.parameters = parameters;
	}

	@NotNull
	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(@NotNull final ActionType actionType) {
		this.actionType = actionType;
	}

	@NotNull
	public UUID getObjectId() {
		return objectId;
	}

	@NotNull
	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setObjectId(@NotNull final UUID objectId) {
		this.objectId = objectId;
	}

	public void setParameters(@NotNull final Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "ActionMessage [actionType=" + actionType + ", objectId=" + objectId + ", parameters=" + parameters + "]";
	}

}
