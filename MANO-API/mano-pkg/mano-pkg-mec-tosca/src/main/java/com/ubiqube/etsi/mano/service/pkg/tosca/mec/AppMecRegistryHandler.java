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
package com.ubiqube.etsi.mano.service.pkg.tosca.mec;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;
import com.ubiqube.etsi.mano.service.pkg.PkgUtils;
import com.ubiqube.parser.tosca.csar.CsarParser;
import com.ubiqube.parser.tosca.csar.CsarParserImpl;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AppMecRegistryHandler implements PackageDescriptor<AppToscaProvider> {

	private static final Logger LOG = LoggerFactory.getLogger(AppMecRegistryHandler.class);

	@Override
	public boolean isProcessable(final InputStream data) {
		final ObjectMapper mapper = getMapper();
		try {
			if (data.read() != 'P' || data.read() != 'K') {
				LOG.debug("Not a Zip File.");
				return false;
			}
			final File filename = PkgUtils.fetchData(data);
			final CsarParser cp = new CsarParserImpl(filename);
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
	public String getProviderName() {
		return "TOSCA-MEC";
	}

	@Override
	public AppToscaProvider getNewReaderInstance(final InputStream data) {
		return new AppToscaProvider(data);
	}

	private static ObjectMapper getMapper() {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final SimpleModule module = new SimpleModule();
		mapper.registerModule(module);

		return mapper;
	}

}
