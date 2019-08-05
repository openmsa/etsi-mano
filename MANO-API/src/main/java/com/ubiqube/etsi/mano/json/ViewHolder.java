package com.ubiqube.etsi.mano.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

	@Nullable
	public String get() {
		if (propertyParts.isEmpty()) {
			return null;
		}
		return propertyParts.get(0);
	}

	@Nullable
	public String getAndRemove() {
		if (propertyParts.isEmpty()) {
			return null;
		}
		final String value = propertyParts.get(0);
		propertyParts.remove(0);
		return value;
	}
}
