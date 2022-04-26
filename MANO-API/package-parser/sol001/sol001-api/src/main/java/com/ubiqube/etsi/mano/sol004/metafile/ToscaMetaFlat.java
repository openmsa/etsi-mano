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

import java.util.List;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaMetaFlat implements ToscaMeta {
	private final String entryfileName;
	private final String manifest;

	public ToscaMetaFlat(final List<String> files) {
		final List<String> l = files.stream().filter(ToscaMetaFlat::isYaml).toList();
		if (l.isEmpty() || l.size() > 1) {
			throw new Sol004Exception("Unable to find a suitable yaml file. #" + l.size());
		}
		this.entryfileName = l.get(0);
		this.manifest = entryfileName.substring(0, entryfileName.lastIndexOf('.')) + ".mf";
	}

	private static boolean isYaml(final String x) {
		return x.endsWith(".yaml") || x.endsWith(".yml");
	}

	@Override
	public String getManifestFileName() {
		return manifest;
	}

	@Override
	public String getEntryDefinitionFileName() {
		return entryfileName;
	}

	@Override
	public String getTestingFolder() {
		return "Tests/";
	}

	@Override
	public String getLicencesFolder() {
		return "Licenses/";
	}

	@Override
	public String getChangeLogFilename() {
		return "ChangeLog.txt";
	}

	@Override
	public String getKey(final String string) {
		return null;
	}

}
