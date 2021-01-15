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
package com.ubiqube.etsi.mec.meo.service.pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.etsi.mano.service.pkg.ToscaException;
import com.ubiqube.etsi.mec.meo.service.AppPackageProvider;
import com.ubiqube.etsi.mec.meo.service.AppRegistryHandler;
import com.ubiqube.parser.tosca.csar.CsarParser;

@Service
public class ToscaRegistryHandler implements AppRegistryHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaRegistryHandler.class);

	@Override
	public boolean isProcessable(final byte[] data) {
		final ObjectMapper mapper = getMapper();
		try {
			if (((data.length <= 10) || ((data[0] != 'P') || (data[1] != 'K')))) {
				LOG.debug("Not a Zip File.");
				return false;
			}
			final File filename = fetchData(data);
			final CsarParser cp = new CsarParser(filename.toString());
			final String ep = cp.getEntryDefinition();

			final JsonNode tree = mapper.readTree(ep.getBytes());
			final Optional<JsonNode> obj = Optional.ofNullable(tree.get("metadata"));
			if (obj.isEmpty()) {
				LOG.debug("No metadata");
				return false;
			}
			final Optional<JsonNode> templateAuthor = Optional.ofNullable(obj.get().get("template_author"));
			if (templateAuthor.isEmpty()) {
				LOG.debug("No template author metadata.");
				return false;
			}
			final Optional<String> content = templateAuthor.filter(JsonNode::isTextual).map(JsonNode::asText);
			if (content.isEmpty()) {
				LOG.debug("Template author is not a string.");
				return false;
			}
			if (!"UBIQUBE_MEC".equals(content.get())) {
				LOG.debug("Bad Author.");
				return false;
			}
			return true;
		} catch (final IOException e) {
			LOG.warn("", e);
		}
		return false;
	}

	@Override
	public String getName() {
		return "app-tosca-provider";
	}

	@Override
	public AppPackageProvider getNewInstance(final byte[] data) {
		return new AppToscaProvider(data);
	}

	private static ObjectMapper getMapper() {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final SimpleModule module = new SimpleModule();
		mapper.registerModule(module);

		return mapper;
	}

	private static File fetchData(final byte[] data) {
		File tempFile;
		try {
			tempFile = File.createTempFile("tosca", ".zip");
		} catch (final IOException e) {
			throw new ToscaException(e);
		}
		try (final OutputStream os = new FileOutputStream(tempFile)) {
			os.write(data);
		} catch (final IOException e) {
			throw new ToscaException(e);
		}
		return tempFile;
	}

}
