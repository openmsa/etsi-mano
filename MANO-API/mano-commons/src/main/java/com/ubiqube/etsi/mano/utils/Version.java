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
package com.ubiqube.etsi.mano.utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ubiqube.etsi.mano.exception.GenericException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Version {
	private final int major;
	private final int minor;
	private final int patch;
	private final Pattern p = Pattern.compile("^(?<major>[0-9]+)\\.(?<minor>[0-9]+)\\.(?<patch>[0-9]+)$");

	public Version(final int major, final int minor, final int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	public Version(final String str) {
		final Matcher m = p.matcher(str);
		if (!m.find()) {
			throw new GenericException("Could not match a valid version: " + str);
		}
		major = Integer.parseInt(m.group("major"));
		minor = Integer.parseInt(m.group("minor"));
		patch = Integer.parseInt(m.group("patch"));
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	@Override
	public String toString() {
		return major + "." + minor + "." + patch;
	}

	@Override
	public int hashCode() {
		return Objects.hash(major, minor, patch);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Version other = (Version) obj;
		return major == other.major && minor == other.minor && patch == other.patch;
	}

}
