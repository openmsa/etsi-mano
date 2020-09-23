package com.ubiqube.parser.tosca.generator;

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
}
