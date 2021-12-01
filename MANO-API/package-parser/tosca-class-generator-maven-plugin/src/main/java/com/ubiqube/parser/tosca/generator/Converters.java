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
package com.ubiqube.parser.tosca.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JType;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.scalar.Size;

public class Converters {
	private static final Logger LOG = LoggerFactory.getLogger(Converters.class);

	private Converters() {
		// Nothing.
	}

	public static JExpression convert(final JCodeModel codeModel, final Object def, final Class<?> jType) {
		LOG.debug("def={} jType={}", def, jType);
		if (jType.equals(Long.class)) {
			return JExpr.lit(Long.parseLong((String) def));
		}
		if (jType.equals(String.class)) {
			return JExpr.lit((String) def);
		}
		if (jType.equals(Boolean.class)) {
			return JExpr.lit((Boolean) def);
		}
		if (jType.equals(Character.class)) {
			return JExpr.lit(((String) def).charAt(0));
		} else if (jType.equals(Double.class)) {
			return JExpr.lit(Double.parseDouble((String) def));
		} else if (jType.equals(Float.class)) {
			return JExpr.lit(((Double) def).floatValue());
		} else if (jType.equals(Integer.class)) {
			if (def.getClass().equals(Integer.class)) {
				return JExpr.lit((Integer) def);
			}
			return JExpr.lit(Integer.parseInt((String) def));
		} else if (jType.equals(Size.class)) {
			return JExpr._new(codeModel._ref(Size.class));
		}
		throw new ParseException("Unknown type : " + jType);
	}

	public static JExpression convert(final Object def, final JType type) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("def={} jType={}", def, type.name());
		}
		// XXX to do.
		switch (type.name()) {
		case "String":
			return JExpr.lit((String) def);
		case "Boolean":
			return JExpr.lit((Boolean) def);
		case "Integer":
			if (def.getClass().equals(Integer.class)) {
				return JExpr.lit((Integer) def);
			}
			return JExpr.lit(Integer.parseInt((String) def));
		default:
			throw new ParseException("Unknown type: " + type.name());
		}
	}

}
