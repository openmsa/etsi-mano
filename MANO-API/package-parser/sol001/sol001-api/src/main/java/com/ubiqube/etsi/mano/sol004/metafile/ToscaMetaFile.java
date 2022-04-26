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
package com.ubiqube.etsi.mano.sol004.metafile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaMetaFile implements ToscaMeta {

	private final Properties props;

	public ToscaMetaFile(final Properties props) {
		this.props = props;
	}

	public static ToscaMetaFile of(final InputStream stream) {
		final Properties props = new Properties();
		try {
			props.load(stream);
			return new ToscaMetaFile(props);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	public boolean check() {
		return haveEntryDefinition() && haveManifest() && haveLicenses();
	}

	private boolean haveLicenses() {
		return null != getLicencesFolder();
	}

	private boolean haveEntryDefinition() {
		return null != getEntryDefinitionFileName();
	}

	private boolean haveManifest() {
		return null != getManifestFileName();
	}

	@Override
	public String getManifestFileName() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Manifest"))
				.orElseGet(() -> props.get("Entry-Manifest"));
	}

	@Override
	public String getEntryDefinitionFileName() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Definitions"))
				.orElseGet(() -> props.get("Entry-Definitions"));
	}

	@Override
	public String getKey(final String key) {
		return (String) props.get(key);
	}

	@Override
	public String getTestingFolder() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Tests"))
				.orElseGet(() -> props.get("Entry-Tests"));
	}

	@Override
	public String getLicencesFolder() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Licenses"))
				.orElseGet(() -> props.get("Entry-Licenses"));
	}

	@Override
	public String getChangeLogFilename() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Change-Log"))
				.orElseGet(() -> props.get("Entry-Change-Log"));
	}

}
