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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ubiqube.parser.tosca.api.ToscaInernalBase;

public class Bean extends ToscaInernalBase {
	private String name;

	private String targets;

	private String triggers;

	private String a;

	private String b;

	private int i;

	private Integer i2;

	private List<String> members;

	private Map<String, ValueObject> attributes = new HashMap<>();
	private Map<String, Artifact> artifacts = new HashMap<>();

	public String getTargets() {
		return targets;
	}

	public void setTargets(final String targets) {
		this.targets = targets;
	}

	public String getTriggers() {
		return triggers;
	}

	public void setTriggers(final String triggers) {
		this.triggers = triggers;
	}

	public String getA() {
		return a;
	}

	public void setA(final String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(final String b) {
		this.b = b;
	}

	public int getI() {
		return i;
	}

	public void setI(final int i) {
		this.i = i;
	}

	public Integer getI2() {
		return i2;
	}

	public void setI2(final Integer i2) {
		this.i2 = i2;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(final List<String> members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Map<String, ValueObject> getAttributes() {
		return attributes;
	}

	public Map<String, Artifact> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(final Map<String, Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public void setAttributes(final Map<String, ValueObject> attributes) {
		this.attributes = attributes;
	}

}
