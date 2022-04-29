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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;

import lombok.Getter;
import lombok.Setter;

/**
 * AKA: Cp
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class ConnectionPoint {
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<VlProtocolData> protocol;

	/**
	 * Provides information about whether the CP instantiated from this Cp is in
	 * Trunk mode (802.1Q or other), When operating in "trunk mode", the Cp is
	 * capable of carrying traffic for several VLANs. Absence of this property
	 * implies that trunkMode is not configured for the Cp i.e. It is equivalent to
	 * boolean value "false".
	 */
	private Boolean trunkMode;

	/**
	 * Identifies which protocol the connection point uses for connectivity purposes
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> layerProtocols;

	/**
	 * Identifies the role of the port in the context of the traffic flow patterns
	 * in the VNF or parent NS
	 */
	private String role;

	/**
	 * Provides human-readable information on the purpose of the connection point
	 */
	private String description;

}
