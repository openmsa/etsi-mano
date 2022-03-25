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
package com.ubiqube.etsi.mano.dao.mano.vnfi;

public enum VimCapability {
	HAVE_DNS,
	HAVE_VXNET,
	HAVE_AVAILABILITY_ZONE,
	HAVE_BGP,
	HAVE_NET_MTU,
	HAVE_QOS,
	HAVE_ROUTER,
	REQUIRE_SUBNET_ALLOCATION,
	HAVE_VLAN_TRANSPARENT,
	HAVE_DHCP,
	HAVE_TRUNK,
}
