package com.ubiqube.parser.tosca;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.ubiqube.parser.tosca.constraints.Constraint;
import com.ubiqube.parser.tosca.constraints.GreaterOrEqual;
import com.ubiqube.parser.tosca.constraints.GreaterThan;
import com.ubiqube.parser.tosca.constraints.LessOrEqual;
import com.ubiqube.parser.tosca.constraints.LessThan;
import com.ubiqube.parser.tosca.constraints.Pattern;
import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Range;
import com.ubiqube.parser.tosca.scalar.Size;
import com.ubiqube.parser.tosca.scalar.Time;
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
		root = tp.parse("src/test/resources/etsi_nfv_sol001_vnfd_types.yaml");

		// root = tp.parse("src/test/resources/web_mysql_tosca.yaml");
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

		Set<Entry<String, ToscaClass>> arts = root.getArtifacts().entrySet();
		for (final Entry<String, ToscaClass> entry : arts) {
			if (null != cache.get(entry.getKey())) {
				continue;
			}
			generateToscaClass(entry.getKey(), entry.getValue());
		}
		arts = root.getNodeType().entrySet();
		for (final Entry<String, ToscaClass> entry : arts) {
			if (null != cache.get(entry.getKey())) {
				continue;
			}
			generateToscaClass(entry.getKey(), entry.getValue());
		}
		new File("src/generated/java").mkdirs();
		codeModel.build(new File("src/generated/java/"));
	}

	private JDefinedClass generateClassFromDataType(final String className, final DataType definition) throws JClassAlreadyExistsException {
		LOG.info("generateClassFromDataType class {}", className);
		final JPackage pack = getPackage(className);
		final JDefinedClass jc = pack._class(getClassName(className));
		if (null != definition.getDerivedFrom()) {
			JDefinedClass clazz = cache.get(definition.getDerivedFrom());
			if (null == clazz) {
				LOG.debug("Cache missed {}", definition.getDerivedFrom());
				final CapabilityTypes def = root.getCapabilities().get(definition.getDerivedFrom());
				if (def != null) {
					clazz = generateClass(definition.getDerivedFrom(), def);
				} else {
					final DataType dType = root.getDataTypes().get(definition.getDerivedFrom());
					if (null != dType) {
						clazz = generateClassFromDataType(definition.getDerivedFrom(), dType);
					}
				}
			}
			jc._extends(clazz);
		}
		if (null != definition.getProperties()) {
			generateFields(jc, definition.getProperties().getProperties());
		}
		LOG.debug("Caching {}", className);
		cache.put(className, jc);
		return jc;
	}

	private JDefinedClass generateToscaClass(final String className, final ToscaClass toscaClass) throws JClassAlreadyExistsException {
		LOG.info("generateToscaClass class {}", className);
		if ("tosca.nodes.Compute".equals(className)) {
			LOG.error("kkkk");
		}
		final JPackage pack = getPackage(className);
		final JDefinedClass jc = pack._class(getClassName(className));
		if (null != toscaClass.getDerivedFrom()) {
			JDefinedClass clazz = cache.get(toscaClass.getDerivedFrom());
			if (null == clazz) {
				final CapabilityTypes def = root.getCapabilities().get(toscaClass.getDerivedFrom());
				if (null != def) {
					clazz = generateClass(toscaClass.getDerivedFrom(), def);
				} else {
					final ToscaClass node = root.getNodeType().get(toscaClass.getDerivedFrom());
					if (node != null) {
						clazz = generateToscaClass(toscaClass.getDerivedFrom(), node);
					}
				}
				if (null == clazz) {
					LOG.error("Crashing ...");
				}
				LOG.info("Caching {}", toscaClass.getDerivedFrom());
				cache.put(toscaClass.getDerivedFrom(), clazz);
			}
			jc._extends(clazz);
		}
		if (null != toscaClass.getProperties()) {
			generateFields(jc, toscaClass.getProperties().getProperties());
		}
		if (null != toscaClass.getAttributes()) {
			generateFields(jc, toscaClass.getAttributes());
		}
		LOG.info("Caching {}", className);
		cache.put(className, jc);
		return jc;

	}

	private JDefinedClass generateClass(final String className, final CapabilityTypes definition) throws JClassAlreadyExistsException {
		LOG.info("generateClass class {}", className);
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

	private void generateFields(final JDefinedClass jc, final Map<String, ValueObject> vo) throws JClassAlreadyExistsException {
		final Set<Entry<String, ValueObject>> attrsEntr = vo.entrySet();
		for (final Entry<String, ValueObject> entry : attrsEntr) {
			final ValueObject val = entry.getValue();
			final Class<?> jType = convert(entry.getValue());
			final JFieldVar field;
			JType jType2 = null;
			final String fieldName = fieldCamelCase(entry.getKey());
			if (null != jType) {
				field = jc.field(JMod.PRIVATE, jType, fieldName);
			} else {
				jType2 = findJType(entry.getValue());
				field = jc.field(JMod.PRIVATE, jType2, fieldName);
			}
			if (null != val.getDescription()) {
				field.javadoc().add(val.getDescription());
			}
			if ((null != val.getRequired()) && (val.getRequired() == Boolean.TRUE)) {
				field.annotate(NotNull.class);
			}
			// Jackson Annotate
			field.annotate(JsonProperty.class).param("value", entry.getKey());
			if (val.getDef() != null) {
				// TODO Convert.
				if (null != jType) {
					field.init(convert(val.getDef(), jType));
				} else {
					LOG.error("could not init the field {} of type {}", entry.getKey(), jType2);
					// field.init(convert(val.getDef(), jType2.));
				}

			}
			if (!val.getConstraints().isEmpty()) {
				// TODO Add Constraint.
				final List<Constraint> cont = val.getConstraints();
				cont.forEach(x -> {
					applyAnnotation(x, field);
				});
			}
			final String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			final JMethod getter = jc.method(JMod.PUBLIC, field.type(), "get" + methodName);
			if ((null != val.getRequired()) && (val.getRequired() == Boolean.TRUE)) {
				getter.annotate(NotNull.class);
			}
			getter.body()._return(field);
			// Setter
			final JMethod setVar = jc.method(JMod.PUBLIC, codeModel.VOID, "set" + methodName);
			final JVar param = setVar.param(field.type(), field.name());
			if ((null != val.getRequired()) && (val.getRequired() == Boolean.TRUE)) {
				param.annotate(NotNull.class);
			}
			setVar.body().assign(JExpr._this().ref(field.name()), JExpr.ref(field.name()));
		}
	}

	private static String fieldCamelCase(final String key) {
		final java.util.regex.Pattern p = java.util.regex.Pattern.compile("_(.)");
		final Matcher m = p.matcher(key);
		final StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	private static void applyAnnotation(final Constraint x, final JFieldVar field) {
		if (x instanceof Pattern) {
			field.annotate(javax.validation.constraints.Pattern.class).param("regexp", ((Pattern) x).getValue());
		} else if (x instanceof GreaterOrEqual) {
			field.annotate(DecimalMin.class).param("value", ((GreaterOrEqual) x).getValue()).param("inclusive", true);
		} else if (x instanceof GreaterThan) {
			field.annotate(DecimalMin.class).param("value", ((GreaterThan) x).getValue());
		} else if (x instanceof LessOrEqual) {
			field.annotate(DecimalMax.class).param("value", ((LessOrEqual) x).getValue()).param("inclusive", true);
		} else if (x instanceof LessThan) {
			field.annotate(DecimalMax.class).param("value", ((LessThan) x).getValue());
		}
	}

	private JExpression convert(final Object def, final Class<?> jType) {
		LOG.info("def={} jType={}", def, jType);
		if (jType.equals(Long.class)) {
			return JExpr.lit(new Long((String) def));
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
			} else {
				return JExpr.lit(Integer.parseInt((String) def));
			}
		} else if (jType.equals(Size.class)) {
			return JExpr._new(codeModel._ref(Size.class));
		}
		throw new RuntimeException("Unknown type : " + jType);
	}

	private JType findJType(final ValueObject valueObject) throws JClassAlreadyExistsException {
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
			final JDefinedClass cached = cache.get(valueObject.getEntrySchema().getType());
			if (null != cached) {
				return codeModel.ref(List.class).narrow(cached);
			}
			final DataType dType = root.getDataTypes().get(valueObject.getEntrySchema().getType());
			JType cl;
			if (null != dType) {
				cl = generateClassFromDataType(valueObject.getEntrySchema().getType(), dType);
			} else {
				cl = generateToscaClass(valueObject.getEntrySchema().getType(),
						root.getNodeType().get(valueObject.getEntrySchema().getType()));
			}
			return codeModel.ref(List.class).narrow(cl);
		}
		if ("map".equals(type)) {
			final Class<?> jTy = convert(valueObject.getEntrySchema().getType());
			if (null != jTy) {
				return codeModel.ref(Map.class).narrow(String.class, jTy);
			}
			final JDefinedClass jcTy = cache.get(valueObject.getEntrySchema().getType());
			if (null != jcTy) {
				return codeModel.ref(Map.class).narrow(String.class).narrow(jcTy);
			}
			// TODO
			LOG.info("Map of {}", valueObject.getEntrySchema().getType());
			final DataType dType = root.getDataTypes().get(valueObject.getEntrySchema().getType());
			JType cl;
			if (null != dType) {
				cl = generateClassFromDataType(valueObject.getEntrySchema().getType(), dType);
			} else {
				cl = generateToscaClass(valueObject.getEntrySchema().getType(),
						root.getNodeType().get(valueObject.getEntrySchema().getType()));
			}
			return codeModel.ref(Map.class).narrow(String.class).narrow(cl);
		}
		final DataType dType = root.getDataTypes().get(valueObject.getType());
		if (null != dType) {
			return generateClassFromDataType(valueObject.getType(), dType);
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
		return convert(type);
	}
}
