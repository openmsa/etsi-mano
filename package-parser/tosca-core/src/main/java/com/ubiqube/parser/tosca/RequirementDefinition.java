package com.ubiqube.parser.tosca;

import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.RequirementDeserialization;

@JsonDeserialize(using = RequirementDeserialization.class)
public class RequirementDefinition {
	Map<String, Requirement> requirements;

	public RequirementDefinition(final Map<String, Requirement> reqMap) {
		requirements = reqMap;
	}

	public Map<String, Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(final Map<String, Requirement> requirements) {
		this.requirements = requirements;
	}

}
