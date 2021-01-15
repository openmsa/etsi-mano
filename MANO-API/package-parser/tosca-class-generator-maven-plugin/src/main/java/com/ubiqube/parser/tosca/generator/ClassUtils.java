package com.ubiqube.parser.tosca.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ClassUtils {
	private ClassUtils() {
		// Nothing.
	}

	public static String getClassName(final String key) {
		if (key.lastIndexOf('.') == -1) {
			return key;
		}
		final int pi = key.lastIndexOf('.');
		return key.substring(pi + 1);
	}

	public static String getPackage(final String key) {
		if (key.lastIndexOf('.') == -1) {
			return null;
		}
		final int pi = key.lastIndexOf('.');
		return key.substring(0, pi);
	}

	public static String toscaToJava(final String derivedFrom) {
		final Pattern p = Pattern.compile("(?<package>.*)(?=\\.)\\.(?<clazz>.*)");
		final Matcher m = p.matcher(derivedFrom);
		if (m.matches()) {
			return m.group("package").toLowerCase() + "." + m.group("clazz");
		}
		return derivedFrom;
	}

}
