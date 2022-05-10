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
package com.ubiqube.etsi.mano.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

public class UbiqubeSourceLocator implements PropertySourceLocator {

	private static final Logger LOG = LoggerFactory.getLogger(UbiqubeSourceLocator.class);

	@Override
	public PropertySource<?> locate(final Environment environment) {
		final Properties props = new Properties();
		final File confDirName = new File(System.getProperty("user.home"), ".mano");
		final File filename = new File(confDirName, "configuration.properties");

		try (InputStream inStream = new FileInputStream(filename);) {
			props.load(inStream);
			LOG.info("Ubiqube Property file loaded.");
		} catch (final IOException e) {
			LOG.warn("Unable to find $HOME/.mano/configuration.properties", e);
		}
		return new UbiqubePropertySource("ubiqube", props);
	}
}
