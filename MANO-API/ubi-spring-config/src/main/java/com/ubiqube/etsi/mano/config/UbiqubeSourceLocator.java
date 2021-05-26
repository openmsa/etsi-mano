/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
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
		} catch (final IOException e) {
			LOG.warn("Unable to find $HOME/.mano/configuration.properties", e);
		}
		return new UbiqubePropertySource("ubiqube", props);
	}
}
