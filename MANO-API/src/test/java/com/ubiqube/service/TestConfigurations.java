package com.ubiqube.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.Configuration;
import com.ubiqube.etsi.mano.service.ConfigurationBuilder;

public class TestConfigurations implements Configuration {
	private final Properties props;

	public TestConfigurations() {
		props = new Properties();
		try (FileInputStream inStream = new FileInputStream("configuration.properties.tmpl");) {
			props.load(inStream);
		} catch (final FileNotFoundException e) {
			throw new GenericException(e);
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
