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
package com.ubiqube.parser.tosca;

import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.jxpath.JXPathContext;

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
