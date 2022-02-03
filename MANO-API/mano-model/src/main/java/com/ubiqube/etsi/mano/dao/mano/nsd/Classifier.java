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
package com.ubiqube.etsi.mano.dao.mano.nsd;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 *         Alias NfpRule policy.
 */
@Embeddable
@Getter
@Setter
public class Classifier implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	/**
	 * Indicates a VLAN identifier in an IEEE 802.1Q-2014 tag [14]. Multiple tags
	 * can be included for QinQ stacking.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> vlanTag;

	/**
	 * Indicates the L4 protocol, For IPv4 [15] this corresponds to the field called
	 * "Protocol" to identify the next level protocol. For IPv6 [16] this
	 * corresponds to the field is called the "Next Header" field. Permitted values:
	 * Any keyword defined in the IANA [17] protocol registry.
	 */
	private String protocol;

	/**
	 * For IPv4 [15] a string of "0" and "1" digits that corresponds to the 6-bit
	 * Differentiated Services Code Point (DSCP) field of the IP header. For IPv6
	 * [16] a string of "0" and "1" digits that corresponds to the 6 differentiated
	 * services bits of the traffic class header field.
	 */
	private String dscp;

	/**
	 * Indicates a range of source ports.
	 */
	private int sourcePortRangeMin;
	private int sourcePortRangeMax;

	/**
	 * Indicates a range of destination ports.
	 */
	private int destinationPortRangeMin;
	private int destinationPortRangeMax;
	/**
	 * Indicates the source IP address range in CIDR format.
	 */
	private String sourceIpAddressPrefix;

	/**
	 * Indicates a destination Mac address.
	 */
	private String etherDestinationAddress;

	/**
	 * Indicates the protocol carried over the Ethernet layer.
	 */
	private String etherType;

	/**
	 * Indicates the destination IP address range in CIDR format.
	 */
	private String destinationIpAddressPrefix;

	/**
	 * Indicates a source Mac address.
	 */
	private String etherSourceAddress;

}
