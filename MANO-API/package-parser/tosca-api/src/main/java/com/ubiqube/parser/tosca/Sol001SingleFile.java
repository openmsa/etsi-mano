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
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import com.ubiqube.parser.tosca.api.ArtefactInformations;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Sol001SingleFile extends AbstractSol001Fs {

	private final Resolver resolver;
	private final File filename;

	public Sol001SingleFile(final File filename) {
		resolver = new Resolver(filename);
		this.filename = filename;
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

	@Override
	public @NotNull List<ArtefactInformations> getFiles() {
		return Arrays.asList(map(filename));
	}

	@Override
	public String getEntryDefinitionFileName() {
		return filename.toString();
	}

	@Override
	public String getManifestContent() {
		return "";
	}

	@Override
	public byte[] getFileContent(final String fileName) {
		try (FileInputStream fis = new FileInputStream(fileName)) {
			return ToscaUtils.readAllBytes(fis);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	@Override
	protected String getEntryFileContent() {
		return new String(getFileContent(filename.toString()), Charset.defaultCharset());
	}

}
