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

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.exception.GenericException;

public class Node {

	public enum Operand {
		EQ("eq"),
		NEQ("neq"),
		GT("gt"),
		LT("lt"),
		GTE("gte"),
		LTE("lte"),
		CONT("cont"),
		NCONT("ncont"),
		IN("in"),
		NIN("nin");
		public final String op;

		private Operand(final String _op) {
			op = _op;
		}
	}

	private String name;
	private Operand op;
	private List<String> value = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Operand getOp() {
		return op;
	}

	public void setOp(final Operand op) {
		this.op = op;
	}

	public String getValue() {
		if (value.size() > 1) {
			throw new GenericException("Calling a multivalue.");
		}
		if (value.isEmpty()) {
			return null;
		}
		return value.get(0);
	}

	public List<String> getValues() {
		return value;
	}

	public void setValue(final List<String> _value) {
		this.value = _value;
	}

	public void addValue(final String _value) {
		value.add(_value);
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", op=" + op + ", value=" + value + "]";
	}

}
