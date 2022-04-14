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
import java.util.HashMap;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.parser.tosca.api.ArtefactInformations;

public abstract class AbstractSol001Fs implements Sol001FileSystem {

	private ToscaVersion toscaVersion;
	private Optional<Sol001Version> sol001Version;
	private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

	@Override
	public ToscaVersion getToscaVersion() {
		if (null == toscaVersion) {
			final String entryFile = getEntryFileContent();
			detectVersion(entryFile);
		}
		return toscaVersion;
	}

	protected abstract String getEntryFileContent();

	private void detectVersion(final String content) {
		JsonNode doc;
		try {
			doc = mapper.readTree(content);
		} catch (final JsonProcessingException e) {
			throw new ParseException(e);
		}
		toscaVersion = findToscaVersion(doc).orElseThrow(() -> new ParseException("Unable to find a valid TOSCA version."));
		sol001Version = findMetadataVersion(doc);
	}

	private static Optional<ToscaVersion> findToscaVersion(final JsonNode doc) {
		return Optional.ofNullable(doc.findValue("tosca_definitions_version"))
				.map(JsonNode::asText)
				.map(ToscaVersion::fromValue);
	}

	private static Optional<Sol001Version> findMetadataVersion(final JsonNode doc) {
		return Optional.ofNullable(doc.findValue("metadata"))
				.map(x -> x.findValue("template_version"))
				.map(JsonNode::asText)
				.map(Sol001Version::fromValue);
	}

	protected static ArtefactInformations map(final File filename2) {
		final ArtefactInformations ai = new ArtefactInformations();
		ai.setAlgorithm(ToscaUtils.SHA_512);
		try (FileInputStream fis = new FileInputStream(filename2)) {
			ai.setChecksum(ToscaUtils.computeChecksum(fis));
		} catch (final IOException e) {
			throw new ParseException(e);
		}
		ai.setMetadata(new HashMap<>());
		ai.setPath(filename2.toString());
		return ai;
	}

}
