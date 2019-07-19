package com.ubiqube.etsi.mano.filter;

import java.beans.PropertyDescriptor;

/**
 *
 * @author olivier
 *
 */
public class JsonBeanProperty {

	private PropertyDescriptor propertyDescriptor;
	private String jsonName;

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

}
