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
package com.ubiqube.etsi.mano.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;

@Service
public class PropertiesConfiguration implements Configuration {

	private final Properties props;

	public PropertiesConfiguration() {
		props = new Properties();
		final String confDirName = System.getProperty("user.home") + "/.mano/";
		final String filename = confDirName + "configuration.properties";

		final File confDir = new File(confDirName);
		if (!confDir.exists()) {
			confDir.mkdir();
		}

		final File confFile = new File(filename);
		if (!confFile.exists()) {
			throw new GenericException("Unable to find " + confFile);
		}

		try (InputStream inStream = new FileInputStream(filename);) {
			props.load(inStream);
		} catch (final IOException e) {
			throw new GenericException(e);
		}

	}

	@Override
	public <T> T get(final String _key) {
		return (T) props.get(_key);
	}

	public void set(final String string, final Object root) {
		props.put(string, root);
	}

	@Override
	public <T> ConfigurationBuilder<T> build(final String _key) {
		return new ConfigurationBuilder<>(this, _key);
	}

}
