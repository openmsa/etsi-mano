package com.ubiqube.etsi.mano.service;

import org.apache.commons.beanutils.ConvertUtils;

public class ConfigurationBuilder<T> {

	private boolean notNull = false;
	private T def = null;
	private final Configuration conf;
	private final String key;
	private boolean convert = false;

	public ConfigurationBuilder(final Configuration _conf, final String _key) {
		conf = _conf;
		key = _key;
	}

	public ConfigurationBuilder<T> notNull() {
		notNull = true;
		return this;
	}

	public ConfigurationBuilder<T> withDefault(final T t) {
		def = t;
		return this;
	}

	public ConfigurationBuilder<T> convert() {
		convert = true;
		return this;
	}

	public <U> U build() {
		Object val = conf.get(key);
		if (notNull && (null == val)) {
			throw new NullPointerException("Configuration key [" + key + "] is missing.");
		}
		if ((null != def) && (null == val)) {
			val = def;
		}
		if ((null != val) && convert) {
			val = ConvertUtils.convert(val);
		}
		return (U) val;
	}
}
