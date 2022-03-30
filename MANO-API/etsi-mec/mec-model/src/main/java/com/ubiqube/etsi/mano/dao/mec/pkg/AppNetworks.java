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
package com.ubiqube.etsi.mano.dao.mec.pkg;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.AuditListener;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Setter
@Getter
@Table(schema = "mec_meo")
@EntityListeners(AuditListener.class)
public class AppNetworks implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String physicalNetwork;
	/**
	 * Indicates should DHCP service be enabled on the network or not.
	 *
	 *
	 */
	private boolean dhcpEnabled = true;
	/**
	 * A segmentation identifier in the underlying cloud infrastructure. E.g. VLAN ID, GRE tunnel ID, etc..
	 *
	 *
	 */
	private String segmentationId;
	/**
	 * An identifier that represents an existing Network instance in the underlying cloud infrastructure. This property is mutually exclusive with all other properties except network_name. This can be used alone or together with network_name to identify an existing network.
	 *
	 *
	 */
	private String networkId;
	/**
	 * The IP version of the requested network. Valid values are 4 for ipv4 or 6 for ipv6.
	 *
	 *
	 */
	private int ipVersion = 4;
	/**
	 * The IP address to be used as the start of a pool of addresses within the full IP range derived from the cidr block.
	 *
	 *
	 */
	private String startIp;
	/**
	 * An identifier that represents an existing Network instance in the underlying cloud infrastructure or can be used as the name of the newly created network. If network_name is provided and no other properties are provided (with exception of network_id), then an existing network instance will be used. If network_name is provided alongside with more properties then a new network with this name will be created.
	 *
	 *
	 */
	private String networkName;
	/**
	 * The cidr block of the requested network.
	 *
	 *
	 */
	private String cidr;
	/**
	 * The gateway IP address.
	 *
	 *
	 */
	private String gatewayIp;
	/**
	 * It specifies the nature of the physical network in the underlying cloud infrastructure. Examples are flat, vlan, gre or vxlan. For flat and vlan types, physical_network should be provided too.
	 *
	 *
	 */
	private String networkType;
	/**
	 * The IP address to be used as the end of a pool of addresses within the full IP range derived from the cidr block.
	 *
	 *
	 */
	private String endIp;

	@Embedded
	private Audit audit;

}
