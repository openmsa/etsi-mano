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
package com.ubiqube.etsi.mano.grammar;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class JsonBeanProperty {

	private PropertyDescriptor propertyDescriptor;

	private String jsonName;

	private Map<String, JsonBeanProperty> right;

	private List<JsonBeanProperty> listAccessors;

	public JsonBeanProperty() {
		// Nothing.
	}

	public JsonBeanProperty(final PropertyDescriptor propertyDescriptor, final String _jsonName) {
		super();
		this.propertyDescriptor = propertyDescriptor;
		jsonName = _jsonName;
	}

	public PropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	public void setPropertyDescriptor(final PropertyDescriptor propertyDescriptor) {
		this.propertyDescriptor = propertyDescriptor;
	}

	public String getJsonName() {
		return jsonName;
	}

	public void setJsonName(final String _jsonName) {
		jsonName = _jsonName;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(" [ ");
		sb.append(" jsonName=").append(toIndentedString(jsonName)).append("\n");
		if (right == null) {
			sb.append("    propertyDescriptor=").append(toIndentedString(propertyDescriptor.getPropertyType())).append("\n");
		} else {
			sb.append("    right=").append(toIndentedString(right)).append("\n");
		}
		sb.append("]\n");
		return sb.toString();
	}

	private static String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	public Map<String, JsonBeanProperty> getRight() {
		return right;
	}

	public void setRight(final Map<String, JsonBeanProperty> res) {
		right = res;
	}

	public void setAccessorsList(final List<JsonBeanProperty> listObject) {
		listAccessors = listObject;
	}

	public List<JsonBeanProperty> getListAccessors() {
		return listAccessors;
	}

}
