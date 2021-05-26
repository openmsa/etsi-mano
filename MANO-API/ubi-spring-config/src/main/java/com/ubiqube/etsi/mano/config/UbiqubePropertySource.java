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

import java.util.Map;
import java.util.Properties;

import org.springframework.core.env.EnumerablePropertySource;

public class UbiqubePropertySource extends EnumerablePropertySource<Map<String, Object>> {

	private final Properties props;

	protected UbiqubePropertySource(final String name, final Properties _props) {
		super(name);
		props = _props;
	}

	@Override
	public String[] getPropertyNames() {
		return props.keySet().toArray(new String[0]);
	}

	@Override
	public Object getProperty(final String _name) {
		return props.get(_name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result) + ((props == null) ? 0 : props.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UbiqubePropertySource other = (UbiqubePropertySource) obj;
		if (props == null) {
			if (other.props != null) {
				return false;
			}
		} else if (!props.equals(other.props)) {
			return false;
		}
		return true;
	}

}
