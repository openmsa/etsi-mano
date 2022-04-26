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
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.etsi.mano.sol004.Sol004Onboarding;
import com.ubiqube.etsi.mano.tosca.ArtefactInformations;
import com.ubiqube.etsi.mano.tosca.IResolver;
import com.ubiqube.etsi.mano.tosca.ToscaVersion;

import jakarta.validation.constraints.NotNull;

/**
 * Tosca parser entry point.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaParser {
	private ToscaContext context;
	private final ObjectMapper mapper = getMapper();
	Sol004Onboarding onboarding = new Sol004Onboarding();

	public ToscaParser(final File filename) {
		onboarding.preOnboard(filename.toString());
		final ToscaVersion tv = onboarding.getToscaVersion();
		try {
			final ToscaRoot root = loadToscaBase(tv);
			context = new ToscaContext(root, onboarding.getResolver());
			try (InputStream is = onboarding.getToscaEntryPoint()) {
				final ToscaRoot root2 = mapper.readValue(is, ToscaRoot.class);
				context.addRoot(root2);
			}
			context.resolvImports();
			context.resolvSymbols();
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	public ToscaParser(final String content, final IResolver resolver) {
		try {
			final ToscaRoot root = mapper.readValue(content, ToscaRoot.class);
			context = new ToscaContext(root, resolver);
			context.resolvImports();
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static ToscaRoot loadToscaBase(final ToscaVersion tv) {
		final String fileName = getToscaBase(tv);
		try (final InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
			final String content = IOUtils.toString(stream, Charset.defaultCharset());
			final ObjectMapper mapper = getMapper();
			return mapper.readValue(content.getBytes(), ToscaRoot.class);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static String getToscaBase(final ToscaVersion tv) {
		if (tv == null) {
			return "TOSCA_definition_1_0.yaml";
		}
		if (tv == ToscaVersion.TOSCA_SIMPLE_YAML_1_0 || tv == ToscaVersion.TOSCA_SIMPLE_YAML_1_3 || tv == ToscaVersion.TOSCA_SIMPLE_YAML_1_2) {
			return "TOSCA_definition_1_3.yaml";
		}
		throw new ParseException("Unknown tosca version " + tv);
	}

	private static ObjectMapper getMapper() {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final SimpleModule module = new SimpleModule();
		mapper.registerModule(module);

		return mapper;
	}

	@NotNull
	public List<ArtefactInformations> getFiles() {
		return onboarding.getFiles();
	}

	public ToscaContext getContext() {
		return context;
	}

	public String getEntryFileName() {
		return onboarding.getToscaEntryPointFilename();
	}

	public String getManifestContent() {
		return onboarding.getManifestContent();
	}

	public byte[] getFileContent(final String fileName) {
		return onboarding.getFileContent(fileName);
	}
}
