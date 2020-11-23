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
