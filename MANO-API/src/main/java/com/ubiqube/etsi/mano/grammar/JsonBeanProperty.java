package com.ubiqube.etsi.mano.grammar;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;

/**
 *
 * @author olivier
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

	public JsonBeanProperty(PropertyDescriptor propertyDescriptor, String _jsonName) {
		super();
		this.propertyDescriptor = propertyDescriptor;
		jsonName = _jsonName;
	}

	public PropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	public void setPropertyDescriptor(PropertyDescriptor propertyDescriptor) {
		this.propertyDescriptor = propertyDescriptor;
	}

	public String getJsonName() {
		return jsonName;
	}

	public void setJsonName(String _jsonName) {
		jsonName = _jsonName;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("JsonBeanProperty [\n");
		sb.append("\t, jsonName=").append(jsonName).append("\n");
		if (right == null) {
			sb.append("\tpropertyDescriptor=").append(propertyDescriptor.getPropertyType()).append("\n");
		} else {
			sb.append("\tright=").append(right).append("\n");
		}
		sb.append("]\n");
		return sb.toString();
	}

	public Map<String, JsonBeanProperty> getRight() {
		return right;
	}

	public void setRight(Map<String, JsonBeanProperty> res) {
		right = res;
	}

	public void setAccessorsList(List<JsonBeanProperty> listObject) {
		listAccessors = listObject;
	}

	public List<JsonBeanProperty> getListAccessors() {
		return listAccessors;
	}

}
