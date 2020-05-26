package com.ubiqube.etsi.mano.service.rest;

import java.util.Properties;

import com.ubiqube.etsi.mano.service.Configuration;
import com.ubiqube.etsi.mano.service.ConfigurationBuilder;

public class MsaConf implements Configuration {
	private final Properties props;

	public MsaConf(final String version, final String server) {
		props = new Properties();
		props.put("msa.rest-api.url", "http://" + server + "/ubi-api-rest");
		props.put("msa.rest-api.user", "ncroot");
		props.put("msa.rest-api.password", "ubiqube");
		props.put("msa.rest-api.version", version);
	}

	@Override
	public <T> T get(final String key) {
		return (T) props.get(key);
	}

	@Override
	public <T> ConfigurationBuilder<T> build(final String _key) {
		return new ConfigurationBuilder<>(this, _key);
	}

}
