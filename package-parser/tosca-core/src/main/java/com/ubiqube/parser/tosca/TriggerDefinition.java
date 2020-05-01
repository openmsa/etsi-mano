package com.ubiqube.parser.tosca;

import java.util.List;

public class TriggerDefinition {
	private String description;
	private String event;
	private TimeInterval schedule;
	private EventFilter target_filter;
	private CondictionClause condition;
	private List<ActionDefinition> action;

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

	public EventFilter getTarget_filter() {
		return target_filter;
	}

	public void setTarget_filter(final EventFilter target_filter) {
		this.target_filter = target_filter;
	}

	public CondictionClause getCondition() {
		return condition;
	}

	public void setCondition(final CondictionClause condition) {
		this.condition = condition;
	}

	public List<ActionDefinition> getAction() {
		return action;
	}

	public void setAction(final List<ActionDefinition> action) {
		this.action = action;
	}

}
