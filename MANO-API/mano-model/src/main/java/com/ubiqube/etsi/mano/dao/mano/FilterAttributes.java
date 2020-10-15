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
package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FilterAttributes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String attribute;

	private String value;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(final String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FilterAttributes [id=" + id + ", attribute=" + attribute + ", value=" + value + "]";
	}

}
