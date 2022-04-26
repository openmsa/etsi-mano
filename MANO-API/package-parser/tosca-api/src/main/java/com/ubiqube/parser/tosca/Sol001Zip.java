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
package com.ubiqube.parser.tosca;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import com.ubiqube.etsi.mano.tosca.ArtefactInformations;
import com.ubiqube.etsi.mano.tosca.IResolver;
import com.ubiqube.parser.tosca.csar.CsarParserImpl;

import jakarta.validation.constraints.NotNull;

public class Sol001Zip extends AbstractSol001Fs {

	private final CsarParserImpl csar;
	private final IResolver resolver;

	public Sol001Zip(final File filename) {
		csar = new CsarParserImpl(filename);
		resolver = csar.getResolver();
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

	@Override
	public @NotNull List<ArtefactInformations> getFiles() {
		return csar.getFiles();
	}

	@Override
	public String getEntryDefinitionFileName() {
		return csar.getEntryDefinitionFileName();
	}

	@Override
	public String getManifestContent() {
		return csar.getManifestContent();
	}

	@Override
	public byte[] getFileContent(final String fileName) {
		return csar.getFileContent(fileName);
	}

	@Override
	protected String getEntryFileContent() {
		return new String(csar.getFileContent(csar.getEntryDefinitionFileName()), Charset.defaultCharset());
	}
}
