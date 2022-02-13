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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.ZipUtil.Entry;
import com.ubiqube.parser.tosca.csar.CsarParser;
import com.ubiqube.parser.tosca.csar.CsarParserImpl;

class CsarTest {

	@SuppressWarnings("static-method")
	@Test
	void testName() {
		assertTrue(true);
	}

	@SuppressWarnings("static-method")
	@Test
	void testGetFiles() throws IOException {
		ZipUtil.makeToscaZip("/tmp/ubi-tosca.csar", Entry.of("ubi-tosca/Definitions/tosca_ubi.yaml", "Definitions/tosca_ubi.yaml"),
				Entry.of("ubi-tosca/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
		final CsarParser csar = new CsarParserImpl(new File("/tmp/ubi-tosca.csar"));
		final List<?> list = csar.getFiles();
		assertNotNull(list);
		final String def = csar.getEntryDefinition();
		assertNotNull(def);
	}

	@SuppressWarnings("static-method")
	// There is some inputstream problem when 2 test are running.
	void testGetElkFiles() throws IOException {
		ZipUtil.makeToscaZip("/tmp/ubi-tosca.csar",
				Entry.of("csar_elk/Definitions/collectd.yaml", "Definitions/collectd.yaml"),
				Entry.of("csar_elk/Definitions/elasticsearch.yaml", "Definitions/elasticsearch.yaml"),
				Entry.of("csar_elk/Definitions/kibana.yaml", "Definitions/kibana.yaml"),
				Entry.of("csar_elk/Definitions/logstash.yaml", "Definitions/logstash.yaml"),
				Entry.of("csar_elk/Definitions/paypalpizzastore_nodejs_app.yaml", "Definitions/paypalpizzastore_nodejs_app.yaml"),
				Entry.of("csar_elk/Definitions/rsyslog.yaml", "Definitions/rsyslog.yaml"),
				Entry.of("csar_elk/Definitions/tosca_elk.yaml", "Definitions/tosca_elk.yaml"),
				Entry.of("csar_elk/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
		final CsarParser csar = new CsarParserImpl(new File("/tmp/ubi-tosca.csar"));
		final List<?> list = csar.getFiles();
		assertNotNull(list);
	}
}
