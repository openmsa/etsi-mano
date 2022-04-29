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
package com.ubiqube.etsi.mano.dao.mano.pkg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum McioConstraint {
	AFFINITY_NFVI_POP("AFFINITY_NFVI_POP"),
	AFFINITY_ZONE("AFFINITY_ZONE"),
	AFFINITY_ZONE_GROUP("AFFINITY_ZONE_GROUP"),
	AFFINITY_NFVI_NODE("AFFINITY_NFVI_NODE"),
	AFFINITY_CIS_NODE("AFFINITY_CIS_NODE"),
	ANTI_AFFINITY_NFVI_POP("ANTI_AFFINITY_NFVI_POP"),
	ANTI_AFFINITY_ZONE("ANTI_AFFINITY_ZONE"),
	ANTI_AFFINITY_ZONE_GROUP("ANTI_AFFINITY_ZONE_GROUP"),
	ANTI_AFFINITY_NFVI_NODE("ANTI_AFFINITY_NFVI_NODE"),
	ANTI_AFFINITY_CIS_NODE("ANTI_AFFINITY_CIS_NODE"),
	LOCAL_AFFINITY_NFVI_POP("LOCAL_AFFINITY_NFVI_POP"),
	LOCAL_AFFINITY_ZONE("LOCAL_AFFINITY_ZONE"),
	LOCAL_AFFINITY_ZONEGROUP("LOCAL_AFFINITY_ZONEGROUP"),
	LOCAL_AFFINITY_NFVI_NODE("LOCAL_AFFINITY_NFVI_NODE"),
	LOCAL_AFFINITY_CIS_NODE("LOCAL_AFFINITY_CIS_NODE"),
	LOCAL_ANTI_AFFINITY_NFVI_POP("LOCAL_ANTI_AFFINITY_NFVI_POP"),
	LOCAL_ANTI_AFFINITY_ZONE("LOCAL_ANTI_AFFINITY_ZONE"),
	LOCAL_ANTI_AFFINITY_ZONE_GROUP("LOCAL_ANTI_AFFINITY_ZONE_GROUP"),
	LOCAL_ANTI_AFFINITY_NFVI_NODE("LOCAL_ANTI_AFFINITY_NFVI_NODE"),
	LOCAL_ANTI_AFFINITY_CIS_NODE("LOCAL_ANTI_AFFINITY_CIS_NODE"),
	NODE_ADDITIONAL_CAPABILITY_SSD("NODE_ADDITIONAL_CAPABILITY_SSD"),
	NODE_ADDITIONAL_CAPABILITY_DPDK("NODE_ADDITIONAL_CAPABILITY_DPDK"),
	NODE_ADDITIONAL_CAPABILITY_SRIOV("NODE_ADDITIONAL_CAPABILITY_SRIOV"),
	NODE_ADDITIONAL_CAPABILITY_GPU("NODE_ADDITIONAL_CAPABILITY_GPU"),
	NODE_ADDITIONAL_CAPABILITY_FPGA("NODE_ADDITIONAL_CAPABILITY_FPGA"),
	NODE_ADDITIONAL_CAPABILITY_CPU_PIN("NODE_ADDITIONAL_CAPABILITY_CPU_PIN"),
	NODE_CAPABILITY_LOGICAL_NUMA("NODE_CAPABILITY_LOGICAL_NUMA"),
	NODE_POOL("NODE_POOL");

	private final String value;

	McioConstraint(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static McioConstraint fromValue(final String text) {
		for (final McioConstraint b : McioConstraint.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
