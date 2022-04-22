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
import java.io.Writer;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class MetaFileWriter {
	private static final String TOSCA_BASE = """
			TOSCA-Meta-File-Version: 1.0
			CSAR-Version: 1.1
			Created-By: Ubiqube MANO.
			""";
	private static final String ENTRY_DEFINITION = "Entry-Definitions: %s%n";
	private static final String ENTRY_MANIFEST = "ETSI-Entry-Manifest: %s%n";
	private final String entryDefinition;
	private final String manifest;

	public MetaFileWriter(final String entryDefinition, final String manifest) {
		super();
		this.entryDefinition = entryDefinition;
		this.manifest = manifest;
	}

	public void write(final Writer writer) throws IOException {
		writer.write(TOSCA_BASE, 0, TOSCA_BASE.length());
		if (null != entryDefinition) {
			writer.write(String.format(ENTRY_DEFINITION, entryDefinition));
		}
		writer.write(String.format(ENTRY_MANIFEST, manifest));
	}
}
