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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

import com.ubiqube.etsi.mano.tosca.ArtefactInformations;
import com.ubiqube.etsi.mano.tosca.IResolver;
import com.ubiqube.etsi.mano.tosca.Resolver;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Sol001Directory extends AbstractSol001Fs {

	private final File dir;
	private final Properties props;
	private final IResolver resolver;

	public Sol001Directory(final File filename) {
		this.dir = filename;
		this.props = getMetaFileContent();
		this.resolver = new Resolver(dir);
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

	@Override
	public @NotNull List<ArtefactInformations> getFiles() {
		final Path root = Paths.get(dir.toURI());
		try (final Stream<Path> stream = Files.walk(root)) {
			return stream.map(x -> x.relativize(root))
					.map(Path::toFile)
					.map(AbstractSol001Fs::map)
					.toList();
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	@Override
	public String getEntryDefinitionFileName() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Definitions"))
				.orElseGet(() -> props.get("Entry-Definitions"));
	}

	@Override
	public String getManifestContent() {
		final String fileName = getManifestFileName();
		return new String(getFileContent(fileName));
	}

	private String getManifestFileName() {
		return (String) Optional.ofNullable(props.get("ETSI-Entry-Manifest"))
				.orElseGet(() -> props.get("Entry-Manifest"));
	}

	@Override
	public byte[] getFileContent(final String fileName) {
		final Path p = Paths.get(dir.toString(), fileName);
		ToscaUtils.checkPath(Paths.get(dir.toURI()), p);
		try (FileInputStream fis = new FileInputStream(p.toFile())) {
			return ToscaUtils.readAllBytes(fis);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	@Override
	protected String getEntryFileContent() {
		return new String(getFileContent(getEntryDefinitionFileName()), Charset.defaultCharset());
	}

	private Properties getMetaFileContent() {
		try (FileInputStream fi = new FileInputStream(new File(dir, "TOSCA-Metadata/TOSCA.meta"))) {
			props.load(fi);
			return props;
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}
}
