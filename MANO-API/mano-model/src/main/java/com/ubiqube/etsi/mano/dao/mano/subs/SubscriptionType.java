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
package com.ubiqube.etsi.mano.dao.mano.subs;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum SubscriptionType {
	VNF("VNF"),
	NSD("NSD"),
	ALARM("ALARM"),
	NSLCM("NSLCM"),
	VNFFM("VNFFM"),
	VNFLCM("VNFLCM"),
	VNFPM("VNFPM"),
	VNFIND("VNFIND"),
	VRQAN("VRQAN"),
	MEOPKG("MEOPKG"),
	NSDVNF("NSDVNF"),
	NSPM("NSPM"),
	;

	private String value;

	SubscriptionType(final String string) {
		value = string;
	}

	@Override
	public String toString() {
		return value;
	}
}
