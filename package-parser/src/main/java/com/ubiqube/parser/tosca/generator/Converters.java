package com.ubiqube.parser.tosca.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JType;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Range;
import com.ubiqube.parser.tosca.scalar.Size;
import com.ubiqube.parser.tosca.scalar.Time;
import com.ubiqube.parser.tosca.scalar.Version;

public class Converters {
	private static final Logger LOG = LoggerFactory.getLogger(Converters.class);

	private Converters() {
		// Nothing.
	}

	public static Class<?> convert(final String type) {
		if ("integer".equals(type)) {
			return Integer.class;
		}
		if ("scalar-unit.size".equals(type)) {
			return Size.class;
		}
		if ("scalar-unit.frequency".equals(type)) {
			return Frequency.class;
		}
		if ("scalar-unit.time".equals(type)) {
			return Time.class;
		}
		if ("string".equals(type)) {
			return String.class;
		}
		if ("range".equals(type)) {
			return Range.class;
		}
		if ("boolean".equals(type)) {
			return Boolean.class;
		}
		if ("float".equals(type)) {
			return Float.class;
		}
		if ("version".equals(type)) {
			return Version.class;
		}
		return null;
	}

	public static JExpression convert(final JCodeModel codeModel, final Object def, final Class<?> jType) {
		LOG.info("def={} jType={}", def, jType);
		if (jType.equals(Long.class)) {
			return JExpr.lit(Long.parseLong((String) def));
		} else if (jType.equals(String.class)) {
			return JExpr.lit((String) def);
		} else if (jType.equals(Boolean.class)) {
			return JExpr.lit((Boolean) def);
		} else if (jType.equals(Character.class)) {
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
		LOG.info("def={} jType={}", def, type.name());
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
			throw new ParseException("Unknoqn type: " + type.name());
		}
	}

}
