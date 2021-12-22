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

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.springframework.core.env.EnumerablePropertySource;

public class UbiqubePropertySource extends EnumerablePropertySource<Map<String, Object>> {

	private final Properties props;

	protected UbiqubePropertySource(final String name, final Properties props) {
		super(name);
		this.props = props;
	}

	@Override
	public String[] getPropertyNames() {
		return props.keySet().toArray(new String[0]);
	}

	@Override
	public Object getProperty(final String name) {
		return props.get(name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		final int result = super.hashCode();
		return prime * result + (props == null ? 0 : props.hashCode());
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj) || (getClass() != obj.getClass())) {
			return false;
		}
		final UbiqubePropertySource other = (UbiqubePropertySource) obj;
		if (!Objects.equals(props, other.props)) {
			return false;
		}
		return true;
	}

}
