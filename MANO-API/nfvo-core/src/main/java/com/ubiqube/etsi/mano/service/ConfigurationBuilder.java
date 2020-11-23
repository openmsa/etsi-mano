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
