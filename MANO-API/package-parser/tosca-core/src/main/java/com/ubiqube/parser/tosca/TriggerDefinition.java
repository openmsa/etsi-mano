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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TriggerDefinition {
	private String description;
	private String event;
	private TimeInterval schedule;
	@JsonProperty("target_filter")
	private EventFilter targetFilter;
	private List<CondictionClause> condition;
	private List<ActivityListDefinition> action;

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(final String event) {
		this.event = event;
	}

	public TimeInterval getSchedule() {
		return schedule;
	}

	public void setSchedule(final TimeInterval schedule) {
		this.schedule = schedule;
	}

	public EventFilter getTargetFilter() {
		return targetFilter;
	}

	public void setTargetFilter(final EventFilter targetFilter) {
		this.targetFilter = targetFilter;
	}

	public List<CondictionClause> getCondition() {
		return condition;
	}

	public void setCondition(final List<CondictionClause> condition) {
		this.condition = condition;
	}

	public List<ActivityListDefinition> getAction() {
		return action;
	}

	public void setAction(final List<ActivityListDefinition> action) {
		this.action = action;
	}

}
