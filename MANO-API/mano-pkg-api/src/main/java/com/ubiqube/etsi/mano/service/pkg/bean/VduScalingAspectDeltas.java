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

public class VduScalingAspectDeltas {
	/**
	 * Represents the scaling aspect to which this policy applies
	 * 
	 */
	private String aspect;
	/**
	 * Describes the Vdu.Compute scaling deltas to be applied for every scaling
	 * steps of a particular aspect.
	 * 
	 */
	private Map<String, VduLevel> deltas;
	private List<String> targets;

	public String getAspect() {
		return aspect;
	}

	public void setAspect(final String aspect) {
		this.aspect = aspect;
	}

	public Map<String, VduLevel> getDeltas() {
		return deltas;
	}

	public void setDeltas(final Map<String, VduLevel> deltas) {
		this.deltas = deltas;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
