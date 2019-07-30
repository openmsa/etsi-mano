package com.ubiqube.etsi.mano.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

public class ViewHolder {

	private final List<String> propertyParts;

	public ViewHolder(List<String> propertyParts) {
		this.propertyParts = propertyParts;
	}

	public ViewHolder(@Nonnull String expression) {
		final String[] parts = expression.split("\\.");
		propertyParts = new ArrayList<>(Arrays.asList(parts));
	}

	public boolean shouldRemove(String element) {
		if (propertyParts.isEmpty()) {
			return false;
		}
		if (element.equals(propertyParts.get(0))) {
			propertyParts.remove(0);
			return propertyParts.isEmpty();
		}
		return false;
	}
}
