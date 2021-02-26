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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.parser.tosca.api.ArtefactInformations;
import com.ubiqube.parser.tosca.csar.CsarParser;

public class ToscaParser {
	Pattern zipMatcher = Pattern.compile("\\.(zip|csar)$");
	private CsarParser csar = null;
	private ToscaContext context;

	public ToscaParser(final String filename) {
		final ObjectMapper mapper = getMapper();

		IResolver resolver;
		if (isZip(filename)) {
			csar = new CsarParser(filename);
			resolver = csar.getResolver();
		} else {
			resolver = new Resolver();
		}
		try {
			final ToscaRoot root = loadToscaBase();
			context = new ToscaContext(root, resolver);
			final ToscaRoot root2;
			if (isZip(filename)) {
				root2 = mapper.readValue(csar.getEntryDefinition(), ToscaRoot.class);
			} else {
				root2 = mapper.readValue(new File(filename), ToscaRoot.class);
			}
			context.addRoot(root2);
			context.resolvImports();
			context.resolvSymbols();

		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	public ToscaParser(final String content, final IResolver resolver) {

		final ObjectMapper mapper = getMapper();
		try {
			final ToscaRoot root = mapper.readValue(content, ToscaRoot.class);
			context = new ToscaContext(root, resolver);
			context.resolvImports();
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private ToscaRoot loadToscaBase() {
		final InputStream stream = this.getClass().getClassLoader().getResourceAsStream("TOSCA_definition_1_0.yaml");
		String content;
		try {
			content = IOUtils.toString(stream, Charset.defaultCharset());
			final ObjectMapper mapper = getMapper();
			return mapper.readValue(content.getBytes(), ToscaRoot.class);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static ObjectMapper getMapper() {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final SimpleModule module = new SimpleModule();
		mapper.registerModule(module);

		return mapper;
	}

	private boolean isZip(final String fileName) {
		final Matcher res = zipMatcher.matcher(fileName);
		return res.find();
	}

	public List<ArtefactInformations> getFiles() {
		return csar.getFiles();
	}

	public ToscaContext getContext() {
		return context;
	}

}
