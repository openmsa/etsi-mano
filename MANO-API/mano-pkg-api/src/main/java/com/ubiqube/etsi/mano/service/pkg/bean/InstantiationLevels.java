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
package com.ubiqube.etsi.mano.service.pkg.bean;

import java.util.List;
import java.util.Map;

public class InstantiationLevels {
	/**
	 * The default instantiation level for this flavour.
	 *
	 */
	private String defaultLevel;
	/**
	 * Describes the various levels of resources that can be used to instantiate the
	 * VNF using this flavour.
	 *
	 */
	private Map<String, InstantiationLevel> levels;

	private List<String> targets;

	public String getDefaultLevel() {
		return defaultLevel;
	}

	public void setDefaultLevel(final String defaultLevel) {
		this.defaultLevel = defaultLevel;
	}

	public Map<String, InstantiationLevel> getLevels() {
		return levels;
	}

	public void setLevels(final Map<String, InstantiationLevel> levels) {
		this.levels = levels;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
