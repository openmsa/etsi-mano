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

public class SecurityGroupRule {

	/**
	 * Indicates the protocol carried over the IP layer. Permitted values include
	 * any protocol defined in the IANA protocol registry, e.g. TCP, UDP, ICMP, etc.
	 * 
	 */
	private String protocol = "tcp";
	/**
	 * Indicates the protocol carried over the Ethernet layer.
	 * 
	 */
	private String etherType = "ipv4";
	/**
	 * Human readable description of the security group rule.
	 * 
	 */
	private String description;
	/**
	 * Indicates maximum port number in the range that is matched by the security
	 * group rule. If a value is provided at design-time, this value may be
	 * overridden at run-time based on other deployment requirements or constraints.
	 * 
	 */
	private Integer portRangeMax = 65535;
	/**
	 * The direction in which the security group rule is applied. The direction of
	 * 'ingress' or 'egress' is specified against the associated CP. I.e., 'ingress'
	 * means the packets entering a CP, while 'egress' means the packets sent out of
	 * a CP.
	 * 
	 */
	private String direction = "ingress";
	/**
	 * Indicates minimum port number in the range that is matched by the security
	 * group rule. If a value is provided at design-time, this value may be
	 * overridden at run-time based on other deployment requirements or constraints.
	 * 
	 */
	private Integer portRangeMin = 0;
	private List<String> targets;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(final String protocol) {
		this.protocol = protocol;
	}

	public String getEtherType() {
		return etherType;
	}

	public void setEtherType(final String etherType) {
		this.etherType = etherType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getPortRangeMax() {
		return portRangeMax;
	}

	public void setPortRangeMax(final Integer portRangeMax) {
		this.portRangeMax = portRangeMax;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(final String direction) {
		this.direction = direction;
	}

	public Integer getPortRangeMin() {
		return portRangeMin;
	}

	public void setPortRangeMin(final Integer portRangeMin) {
		this.portRangeMin = portRangeMin;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
