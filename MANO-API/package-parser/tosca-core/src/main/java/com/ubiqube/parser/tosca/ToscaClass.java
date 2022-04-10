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
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToscaClass extends ToscaBasePropertiesEntity {

	private Map<String, ValueObject> attributes;
	@JsonProperty("mime_type")
	private String mimeType;
	@JsonProperty("file_ext")
	private List<String> fileExt;
	private RequirementDefinition requirements;
	private Map<String, InterfaceType> interfaces;
	private Map<String, CapabilityDefinition> capabilities;
	private Map<String, Artifact> artifacts;
	// Used in relation ship only
	@JsonProperty("valid_target_types")
	private List<String> validTargetTypes;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("ToscaClass [");
		sb.append(super.toString());
		if (null != attributes) {
			sb.append(", attributes=" + attributes + ", ");
		}
		if (null != mimeType) {
			sb.append("mimeType=" + mimeType + ", ");
		}
		if (null != fileExt) {
			sb.append("fileExt=" + fileExt + ", ");
		}
		if (null != requirements) {
			sb.append("requirements=" + requirements + ", ");
		}
		if (null != interfaces) {
			sb.append("interfaces=" + interfaces + ", ");
		}
		if (null != capabilities) {
			sb.append("capabilities=" + capabilities + ", ");
		}
		if (null != artifacts) {
			sb.append("artifacts=" + artifacts + ", ");
		}

		if (null != validTargetTypes) {
			sb.append("valid_target_types=" + validTargetTypes + ", ");
		}
		sb.append("]");
		return sb.toString();
	}

}
