package com.ubiqube.parser.tosca;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Range;
import com.ubiqube.parser.tosca.scalar.Size;
import com.ubiqube.parser.tosca.scalar.Version;

public class CodeModelTest {
	private static final Logger LOG = LoggerFactory.getLogger(CodeModelTest.class);
	private final JCodeModel codeModel = new JCodeModel();
	private final Map<String, JDefinedClass> cache = new HashMap<>();
	private final Map<String, JPackage> cachePackage = new HashMap<>();
	private final ToscaParser tp = new ToscaParser();
	private ToscaContext root = null;

	@Test
	void testName() throws Exception {
		root = tp.parse("src/test/resources/web_mysql_tosca.yaml");
		final Map<String, CapabilityTypes> caps = root.getCapabilities();
		final Map<String, DataType> dt = root.getDataTypes();
		final Set<Entry<String, DataType>> e = dt.entrySet();
		cache.put("integer", codeModel._class(Integer.class.getName()));
		for (final Entry<String, DataType> entry : e) {
			if (null != cache.get(entry.getKey())) {
				continue;
			}
			final JDefinedClass jd = generateClassFromDataType(entry.getKey(), entry.getValue());
			cache.put(entry.getKey(), jd);
		}

		final CapabilityTypes rootNode = caps.get("tosca.capabilities.Root");
		final JPackage jp = codeModel._package("tosca.capabilities");
		cachePackage.put("tosca.capabilities", jp);
		final JDefinedClass jcRoot = jp._class("Root");
		jcRoot.javadoc().add(rootNode.getDescription());
		cache.put("tosca.capabilities.Root", jcRoot);

		final CapabilityTypes cap = caps.get("tosca.capabilities.Node");
		final JDefinedClass jcNode = jp._class("Node");
		jcNode.javadoc().add(cap.getDescription());
		jcNode._extends(jcRoot);
		cache.put("tosca.capabilities.Node", jcNode);

		final Set<Entry<String, CapabilityTypes>> cape = caps.entrySet();
		for (final Entry<String, CapabilityTypes> entry : cape) {
			if (null != cache.get(entry.getKey())) {
				continue;
			}
			generateClass(entry.getKey(), entry.getValue());
		}

		codeModel.build(new File("."));
	}

	private JDefinedClass generateClassFromDataType(final String className, final DataType definition) throws JClassAlreadyExistsException {
		LOG.info("Generating class {}", className);
		final JPackage pack = getPackage(className);
		final JDefinedClass jc = pack._class(getClassName(className));
		if (null != definition.getDerivedFrom()) {
			JDefinedClass clazz = cache.get(definition.getDerivedFrom());
			if (null == clazz) {
				LOG.debug("Cache missed {}", definition.getDerivedFrom());
				final CapabilityTypes def = root.getCapabilities().get(definition.getDerivedFrom());
				clazz = generateClass(definition.getDerivedFrom(), def);
				cache.put(definition.getDerivedFrom(), clazz);
			}
			jc._extends(clazz);
		}
		if (null != definition.getProperties()) {
			generateFields(jc, definition.getProperties().getProperties());
		}
		cache.put(className, jc);
		return jc;
	}

	private JDefinedClass generateClass(final String className, final CapabilityTypes definition) throws JClassAlreadyExistsException {
		LOG.info("Generating class {}", className);
		final JPackage pack = getPackage(className);
		final JDefinedClass jc = pack._class(getClassName(className));
		if (null != definition.getDerivedFrom()) {
			JDefinedClass clazz = cache.get(definition.getDerivedFrom());
			if (null == clazz) {
				final CapabilityTypes def = root.getCapabilities().get(definition.getDerivedFrom());
				clazz = generateClass(definition.getDerivedFrom(), def);
				cache.put(definition.getDerivedFrom(), clazz);
			}
			jc._extends(clazz);
		}
		if (null != definition.getProperties()) {
			generateFields(jc, definition.getProperties().getProperties());
		}
		cache.put(className, jc);
		return jc;
	}

	private void generateFields(final JDefinedClass jc, final Map<String, ValueObject> vo) {
		final Set<Entry<String, ValueObject>> attrsEntr = vo.entrySet();
		for (final Entry<String, ValueObject> entry : attrsEntr) {
			final ValueObject val = entry.getValue();
			final Class<?> jType = convert(entry.getValue());
			final JFieldVar field;
			if (null != jType) {
				field = jc.field(JMod.PUBLIC, jType, entry.getKey());

			} else {
				field = jc.field(JMod.PUBLIC, findJType(entry.getValue()), entry.getKey());
			}
			if (null != val.getDescription()) {
				field.javadoc().add(val.getDescription());
			}
			if ((null != val.getRequired()) && (val.getRequired() == Boolean.TRUE)) {
				field.annotate(NotNull.class);
			}
			if (val.getDef() != null) {
				// TODO Convert.
				// field.init(JExpr.lit((String) val.getDef()));
			}
			if (!val.getConstraints().isEmpty()) {
				// TODO Add Constraint.
			}
			final JMethod getter = jc.method(JMod.PUBLIC, field.type(), "get" + entry.getKey());
			getter.body()._return(field);
			// Setter
			final JMethod setVar = jc.method(JMod.PUBLIC, codeModel.VOID, "set" + entry.getKey());
			setVar.param(field.type(), field.name());
			setVar.body().assign(JExpr._this().ref(field.name()), JExpr.ref(field.name()));
		}
	}

	private JType findJType(final ValueObject valueObject) {
		final JDefinedClass item = cache.get(valueObject.getType());
		if (null != item) {
			return item;
		}
		final String type = valueObject.getType();
		if ("list".equals(type)) {
			final Class<?> jTy = convert(valueObject.getEntrySchema().getType());
			if (null != jTy) {
				return codeModel.ref(List.class).narrow(jTy);
			}
			return codeModel.ref(List.class).narrow(Object.class);
		}
		if ("map".equals(type)) {
			final Class<?> jTy = convert(valueObject.getEntrySchema().getType());
			if (null != jTy) {
				return codeModel.ref(Map.class).narrow(jTy);
			}
			final JDefinedClass jcTy = cache.get(valueObject.getType());
			if (null != jcTy) {
				return codeModel.ref(Map.class).narrow(jcTy);
			} else {
				// TODO
				// return generateClass(valueObject.getType(),
				// root.getNodeType().get(valueObject.getType()));
				return codeModel.ref(Map.class).narrow(Object.class);
			}
		}
		throw new RuntimeException("Bad type: " + valueObject);
	}

	private static Class<?> convert(final String type) {
		if ("integer".equals(type)) {
			return Integer.class;
		}
		if ("scalar-unit.size".equals(type)) {
			return Size.class;
		}
		if ("scalar-unit.frequency".equals(type)) {
			return Frequency.class;
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
		if ("version".equals(type)) {
			return Version.class;
		}
		return null;
	}

	private static String getClassName(final String key) {
		if (key.lastIndexOf('.') == -1) {
			return key;
		}
		final int pi = key.lastIndexOf('.');
		return key.substring(pi + 1);
	}

	private JPackage getPackage(final String key) {
		if (key.lastIndexOf('.') == -1) {
			return null;
		}
		final int pi = key.lastIndexOf('.');
		final String p = key.substring(0, pi);
		JPackage pack = cachePackage.get(p);
		if (null != pack) {
			return pack;
		}
		pack = codeModel._package(p);
		cachePackage.put(p, pack);
		return pack;
	}

	private static Class<?> convert(final ValueObject valueObject) {
		final String type = valueObject.getType();
		if ("integer".equals(type)) {
			return Integer.class;
		}
		if ("scalar-unit.size".equals(type)) {
			return Size.class;
		}
		if ("scalar-unit.frequency".equals(type)) {
			return Frequency.class;
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
		if ("version".equals(type)) {
			return Version.class;
		}
		return null;
	}
}
