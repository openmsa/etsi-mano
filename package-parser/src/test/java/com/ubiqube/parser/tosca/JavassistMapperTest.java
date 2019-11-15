package com.ubiqube.parser.tosca;

import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.jxpath.JXPathContext;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistMapperTest {
	@JsonSubTypes({
			@JsonSubTypes.Type(value = String.class)
	})
	private final List<String> test = null;

	@Test
	void testName() throws Exception {
		final ClassPool pool = ClassPool.getDefault();
		final CtClass cc = pool.makeClass("tosca.capabilities.Root");

		final CtField field = CtField.make("private java.util.List entries;", cc);
		cc.addField(field);
		CtMethod m = CtNewMethod.getter("getEntries", field);
		cc.addMethod(m);
		m = CtNewMethod.setter("setEntries", field);
		cc.addMethod(m);

		final ObjectMapper mapper = new ObjectMapper();
		final Object cls = mapper.readValue(new FileInputStream("src/test/resources/javassist.json"), cc.toClass());
		System.out.println(">> " + cls);

		final JXPathContext jxp = JXPathContext.newContext(cls);
		System.out.println("" + jxp.getValue("entries"));

	}
}
