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
package com.ubiqube.etsi.mano.service.pkg;

public class PackageVersion {
	private String flavorId;
	private String name;
	private String version;

	public PackageVersion(final String _version) {
		final String[] parts = _version.split("/");
		if (parts.length == 3) {
			flavorId = parts[0];
			name = parts[1];
			version = parts[2];
		} else if (parts.length == 2) {
			name = parts[1];
			version = parts[2];
		} else if (parts.length == 1) {
			name = parts[0];
		}
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public int countPart() {
		int cnt = 0;
		if (name != null) {
			cnt++;
		}
		if (flavorId != null) {
			cnt++;
		}
		if (version != null) {
			cnt++;
		}
		return cnt;
	}

	@Override
	public String toString() {
		if (countPart() == 1) {
			return name;
		}
		if (countPart() == 2) {
			return name + "/" + version;
		}
		if (countPart() == 3) {
			return flavorId + "/" + name + "/" + version;
		}
		return "error";
	}
}
