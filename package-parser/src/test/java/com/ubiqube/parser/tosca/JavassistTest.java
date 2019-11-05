package com.ubiqube.parser.tosca;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Size;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistTest {
	private static final String DIRECTORY_NAME = "src/main/java";
	private final Map<String, CtClass> cache = new HashMap<>();

	@Test
	void testName() throws Exception {
		final ToscaParser tp = new ToscaParser();
		final ToscaContext root = tp.parse("src/test/resources/web_mysql_tosca.yaml");
		final Map<String, CapabilityTypes> caps = root.getCapabilities();

		final ClassPool pool = ClassPool.getDefault();
		final CapabilityTypes rootNode = caps.get("tosca.capabilities.Root");
		CtClass cc = pool.makeClass("tosca.capabilities.Root");
		cache.put("tosca.capabilities.Root", cc);
		cc.writeFile(DIRECTORY_NAME);

		CapabilityTypes cap = caps.get("tosca.capabilities.Node");
		cc = pool.makeClass("tosca.capabilities.Node");
		if (null != cap.getDerivedFrom()) {
			cc.setSuperclass(cache.get(cap.getDerivedFrom()));
		}
		cache.put("tosca.capabilities.Node", cc);
		cc.writeFile(DIRECTORY_NAME);

		cap = caps.get("tosca.capabilities.Container");
		cc = pool.makeClass("tosca.capabilities.Container");
		if (null != cap.getDerivedFrom()) {
			cc.setSuperclass(cache.get(cap.getDerivedFrom()));
		}

		final Set<Entry<String, ValueObject>> attrsEntr = cap.getProperties().getProperties().entrySet();
		for (final Entry<String, ValueObject> entry : attrsEntr) {
			final CtField field = CtField.make("private " + convert(entry.getValue().getType()) + " " + entry.getKey() + ";", cc);
			cc.addField(field);
			CtMethod m = CtNewMethod.getter("get" + entry.getKey(), field);
			cc.addMethod(m);
			m = CtNewMethod.setter("set" + entry.getKey(), field);
			cc.addMethod(m);
		}
		cc.writeFile(DIRECTORY_NAME);
	}

	private static String convert(final String type) {
		if ("integer".equals(type)) {
			return Integer.class.getName();
		}
		if ("scalar-unit.size".equals(type)) {
			return Size.class.getName();
		}
		if ("scalar-unit.frequency".equals(type)) {
			return Frequency.class.getName();
		}
		return type;
	}
}
