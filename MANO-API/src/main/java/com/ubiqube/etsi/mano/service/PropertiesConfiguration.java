package com.ubiqube.etsi.mano.service;

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
		final String filename = System.getProperty("user.home") + "/.mano/configuration.properties";
		try (InputStream inStream = new FileInputStream(filename);) {
			props.load(inStream);
		} catch (final IOException e) {
			throw new GenericException(e);
		}

	}

	@Override
	public <T> T get(String _key) {
		return (T) props.get(_key);
	}

}
