package com.ubiqube.parser.tosca.generator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.ubiqube.parser.tosca.CapabilityDefinition;
import com.ubiqube.parser.tosca.CapabilityTypes;
import com.ubiqube.parser.tosca.DataType;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.Requirement;
import com.ubiqube.parser.tosca.RequirementDefinition;
import com.ubiqube.parser.tosca.ToscaClass;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ToscaParser;
import com.ubiqube.parser.tosca.ValueObject;
import com.ubiqube.parser.tosca.annotations.Capability;
import com.ubiqube.parser.tosca.annotations.Node;
import com.ubiqube.parser.tosca.annotations.Relationship;
import com.ubiqube.parser.tosca.api.ToscaInernalBase;
import com.ubiqube.parser.tosca.constraints.Constraint;
import com.ubiqube.parser.tosca.constraints.Equal;
import com.ubiqube.parser.tosca.constraints.GreaterOrEqual;
import com.ubiqube.parser.tosca.constraints.GreaterThan;
import com.ubiqube.parser.tosca.constraints.InRange;
import com.ubiqube.parser.tosca.constraints.LessOrEqual;
import com.ubiqube.parser.tosca.constraints.LessThan;
import com.ubiqube.parser.tosca.constraints.MinLength;
import com.ubiqube.parser.tosca.constraints.Pattern;
import com.ubiqube.parser.tosca.constraints.ValidValues;

public class JavaGenerator {
	private static final String VALUE = "value";
	private static final Logger LOG = LoggerFactory.getLogger(JavaGenerator.class);
	private final JCodeModel codeModel = new JCodeModel();
	private final Map<String, JDefinedClass> cache = new HashMap<>();
	private final Map<String, JPackage> cachePackage = new HashMap<>();

	private ToscaContext root = null;
	private final Map<String, DataType> primitive = new HashMap<>();

	public void generate(final String file) throws JClassAlreadyExistsException, IOException {
		final ToscaParser tp = new ToscaParser(file);
		root = tp.getContext();
		final Map<String, CapabilityTypes> caps = root.getCapabilities();
		final Map<String, DataType> dt = root.getDataTypes();
		final Set<Entry<String, DataType>> e = dt.entrySet();
		for (final Entry<String, DataType> entry : e) {
			if (null != cache.get(entry.getKey())) {
				continue;
			}
			final DataType val = entry.getValue();
			if (isPrimitive(val)) {
				primitive.put(entry.getKey(), val);
			} else {
				final JDefinedClass jd = generateClassFromDataType(entry.getKey(), entry.getValue());
				cache.put(entry.getKey(), jd);
			}
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
		// new File("src/generated/java").mkdirs();
		codeModel.build(new File("src/generated/java"));
	}

	private static boolean isPrimitive(final DataType val) {
		if (null == val.getDerivedFrom()) {
			return false;
		}
		if ("integer".equals(val.getDerivedFrom())) {
			return true;
		}
		if ("string".equals(val.getDerivedFrom())) {
			return true;
		}
		return false;
	}

	private JDefinedClass generateClassFromDataType(final String className, final DataType definition) {
		LOG.info("generateClassFromDataType class {}", className);
		final JPackage pack = getPackage(className);
		if (null == pack) {
			throw new ParseException("Unable to create a class without a package: " + className);
		}
		final JDefinedClass jc = createClass(pack, ClassUtils.getClassName(className));
		if (null != definition.getDerivedFrom()) {
			final JDefinedClass clazz = getExtends(definition.getDerivedFrom());
			jc._extends(clazz);
		} else {
			jc._extends(ToscaInernalBase.class);
		}
		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(jc, x.getProperties()));

		LOG.debug("Caching {}", className);
		cache.put(className, jc);
		return jc;
	}

	private JDefinedClass generateToscaClass(final String className, final ToscaClass toscaClass) {
		LOG.info("generateToscaClass class {}", className);
		final JPackage pack = getPackage(className);
		if (null == pack) {
			throw new ParseException("Cannot create a class without a package : " + className);
		}
		final JDefinedClass jc = createClass(pack, ClassUtils.getClassName(className));
		if (null != toscaClass.getDerivedFrom()) {
			final JDefinedClass clazz = getExtends(toscaClass.getDerivedFrom());
			jc._extends(clazz);
		}
		Optional.ofNullable(toscaClass.getProperties()).ifPresent(x -> generateFields(jc, x.getProperties()));

		Optional.ofNullable(toscaClass.getAttributes()).ifPresent(x -> generateFields(jc, x));

		Optional.ofNullable(toscaClass.getCapabilities()).ifPresent(x -> generateCaps(jc, x));

		Optional.ofNullable(toscaClass.getRequirements()).ifPresent(x -> generateRequirements(jc, x));

		LOG.info("Caching {}", className);
		cache.put(className, jc);
		return jc;

	}

	private JDefinedClass getExtends(final String derivedFrom) {
		JDefinedClass clazz = cache.get(derivedFrom);
		if (null == clazz) {
			final CapabilityTypes def = root.getCapabilities().get(derivedFrom);
			if (null != def) {
				clazz = generateClass(derivedFrom, def);
			} else {
				final ToscaClass node = root.getNodeType().get(derivedFrom);
				if (node != null) {
					clazz = generateToscaClass(derivedFrom, node);
				}
			}
			if (null == clazz) {
				throw new ParseException("Could not find parent class: " + derivedFrom);
			}
			LOG.info("Caching {}", derivedFrom);
			cache.put(derivedFrom, clazz);
		}
		return clazz;
	}

	private void generateRequirements(final JDefinedClass jc, final RequirementDefinition requirements) {
		requirements.getRequirements().forEach((final String x, final Requirement y) -> {
			final String fieldName = fieldCamelCase(x + "_req");
			JFieldVar field = null;
			final List<String> occ = y.getOccurrences();
			if (isList(occ)) {
				field = jc.field(JMod.PRIVATE, List.class, fieldName);
			} else {
				field = jc.field(JMod.PRIVATE, Object.class, fieldName);
			}
			// XXX: Probably one may be a concrete type.
			if (null != y.getNode()) {
				field.annotate(Node.class).param(VALUE, y.getNode());
			}
			if (null != y.getCapability()) {
				field.annotate(Capability.class).param(VALUE, y.getCapability());
			}
			if (null != y.getRelationship()) {
				field.annotate(Relationship.class).param(VALUE, y.getRelationship());
			}
			field.annotate(JsonProperty.class).param(VALUE, x);
			final ValueObject vo = new ValueObject();
			vo.setRequired(Boolean.FALSE);
			createGetterSetter(fieldName, jc, field, vo);
		});

	}

	private static boolean isList(final List<String> occ) {
		if (null == occ) {
			return false;
		}
		if (occ.size() < 2) {
			return false;
		}
		final String indice = occ.get(1);
		return ("UNBOUNDED".equals(indice) || (Integer.parseInt(indice) > 1));
	}

	private void generateCaps(final JDefinedClass jc, final Map<String, CapabilityDefinition> capabilities) {
		capabilities.forEach((final String x, final CapabilityDefinition y) -> {
			JDefinedClass jType = cache.get(y.getType());
			final CapabilityTypes caps = root.getCapabilities().get(y.getType());
			if (null == jType) {
				jType = generateClass(y.getType(), caps);
			}
			if ((y.getAttributes() != null) && !y.getAttributes().isEmpty()) {
				throw new ParseException("Unable to handle Attributes in " + x + '=' + y.getType());
			}
			if ((y.getProperties() != null) && !y.getProperties().getProperties().isEmpty()) {
				throw new ParseException("Unable to handle properties in " + x + '=' + y.getType());
			}
			final String fieldName = fieldCamelCase(x);
			final JFieldVar field = jc.field(JMod.PRIVATE, jType, fieldName);
			field.javadoc().add("Caps.");
			if (null != y.getDescription()) {
				field.javadoc().add(y.getDescription());
			}
			final ValueObject vo = new ValueObject();
			vo.setRequired(Boolean.FALSE);
			createGetterSetter(fieldName, jc, field, vo);
		});
	}

	private JDefinedClass generateClass(final String className, final CapabilityTypes definition) {
		LOG.info("generateClass class {}", className);
		final JPackage pack = getPackage(className);
		final JDefinedClass jc;
		if (null != pack) {
			jc = createClass(pack, ClassUtils.getClassName(className));
		} else {
			throw new ParseException("Definition without a package ?");
		}

		if (null != definition.getDerivedFrom()) {
			JDefinedClass clazz = cache.get(definition.getDerivedFrom());
			if (null == clazz) {
				final CapabilityTypes def = root.getCapabilities().get(definition.getDerivedFrom());
				clazz = generateClass(definition.getDerivedFrom(), def);
				cache.put(definition.getDerivedFrom(), clazz);
			}
			jc._extends(clazz);
		}
		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(jc, x.getProperties()));
		cache.put(className, jc);
		return jc;
	}

	private static JDefinedClass createClass(@NotNull final JPackage pack, final String classname) {
		try {
			return pack._class(classname);
		} catch (final JClassAlreadyExistsException e) {
			throw new ParseException(e);
		}
	}

	private void generateFields(final JDefinedClass jc, final Map<String, ValueObject> vo) {
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
				if (null != primitive.get(val.getType())) {
					field = jc.field(JMod.PRIVATE, Integer.class, fieldName);
				} else {
					jType2 = findJType(entry.getValue());
					field = jc.field(JMod.PRIVATE, jType2, fieldName);
				}
			}
			Optional.ofNullable(val.getDescription()).ifPresent(x -> field.javadoc().add(x));

			Optional.ofNullable(val.getRequired())
					.filter(Boolean.TRUE::equals)
					.map(x -> field.annotate(NotNull.class));
			// Jackson Constraint Annotate
			field.annotate(JsonProperty.class).param(VALUE, entry.getKey());
			if (val.getDef() != null) {
				// XXX Convert.
				if (null != jType) {
					field.init(Converters.convert(codeModel, val.getDef(), jType));
				} else {
					LOG.error("could not init the field {} of type {}", entry.getKey(), jType2);
					// field.init(convert(val.getDef(), jType2.));
				}

			}
			if (!val.getConstraints().isEmpty()) {
				// XXX Add Constraint.
				final List<Constraint> cont = val.getConstraints();
				cont.forEach(x -> applyAnnotation(x, field));
			}
			if (null != primitive.get(val.getType())) {
				final List<Constraint> cont = primitive.get(val.getType()).getConstraints();
				cont.forEach(x -> applyAnnotation(x, field));
			}
			createGetterSetter(fieldName, jc, field, val);
		}
	}

	private void createGetterSetter(final String fieldName, final JDefinedClass jc, final JFieldVar field, final ValueObject val) {
		final String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		final JMethod getter = jc.method(JMod.PUBLIC, field.type(), "get" + methodName);
		if ((null != val.getRequired()) && (Boolean.TRUE.equals(val.getRequired()))) {
			getter.annotate(NotNull.class);
		}
		getter.body()._return(field);
		// Setter
		final JMethod setVar = jc.method(JMod.PUBLIC, codeModel.VOID, "set" + methodName);
		final JVar param = setVar.param(field.type(), field.name());
		if ((null != val.getRequired()) && (Boolean.TRUE.equals(val.getRequired()))) {
			param.annotate(NotNull.class);
		}
		setVar.body().assign(JExpr._this().ref(field.name()), JExpr.ref(field.name()));
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
		// XXX: All numéric values maybe scalars.
		if (x instanceof Pattern) {
			field.annotate(javax.validation.constraints.Pattern.class).param("regexp", ((Pattern) x).getValue());
		} else if (x instanceof GreaterOrEqual) {
			field.annotate(DecimalMin.class).param(VALUE, ((GreaterOrEqual) x).getValue()).param("inclusive", true);
		} else if (x instanceof GreaterThan) {
			field.annotate(DecimalMin.class).param(VALUE, ((GreaterThan) x).getValue());
		} else if (x instanceof LessOrEqual) {
			field.annotate(DecimalMax.class).param(VALUE, ((LessOrEqual) x).getValue()).param("inclusive", true);
		} else if (x instanceof LessThan) {
			field.annotate(DecimalMax.class).param(VALUE, ((LessThan) x).getValue());
		} else if (x instanceof ValidValues) {
			// XXX.
		} else if (x instanceof InRange) {
			final InRange ir = (InRange) x;
			field.annotate(Min.class).param(VALUE, Integer.parseInt(ir.getMin()));
			field.annotate(Max.class).param(VALUE, Integer.parseInt(ir.getMax()));
		} else if (x instanceof MinLength) {
			final MinLength ml = (MinLength) x;
			field.annotate(javax.validation.constraints.Size.class).param("min", Integer.parseInt(ml.getValue()));
		} else if (x instanceof Equal) {
			// XXX
		} else {
			throw new ParseException("Unknown constraint: " + x.getClass().getCanonicalName());
		}
	}

	private JType findJType(final ValueObject valueObject) {
		final JDefinedClass item = cache.get(valueObject.getType());
		if (null != item) {
			return item;
		}
		final String type = valueObject.getType();
		if ("list".equals(type)) {
			final String subType = valueObject.getEntrySchema().getType();
			final Class<?> jTy = Converters.convert(subType);
			if (null != jTy) {
				return codeModel.ref(List.class).narrow(jTy);
			}
			final JDefinedClass cached = cache.get(subType);
			if (null != cached) {
				return codeModel.ref(List.class).narrow(cached);
			}
			final DataType dType = root.getDataTypes().get(subType);
			JType cl;
			if (null != dType) {
				cl = generateClassFromDataType(subType, dType);
			} else {
				cl = generateToscaClass(subType,
						root.getNodeType().get(subType));
			}
			return codeModel.ref(List.class).narrow(cl);
		}
		if ("map".equals(type)) {
			final String subType = valueObject.getEntrySchema().getType();
			final Class<?> jTy = Converters.convert(subType);
			if (null != jTy) {
				return codeModel.ref(Map.class).narrow(String.class, jTy);
			}
			final JDefinedClass cahed = cache.get(subType);
			if (null != cahed) {
				return codeModel.ref(Map.class).narrow(String.class).narrow(cahed);
			}
			// XXX
			LOG.info("Map of {}", subType);
			final DataType dType = root.getDataTypes().get(subType);
			JType cl;
			if (null != dType) {
				cl = generateClassFromDataType(subType, dType);
			} else {
				cl = generateToscaClass(subType,
						root.getNodeType().get(subType));
			}
			return codeModel.ref(Map.class).narrow(String.class).narrow(cl);
		}
		final DataType dType = root.getDataTypes().get(valueObject.getType());
		if (null != dType) {
			return generateClassFromDataType(valueObject.getType(), dType);
		}
		throw new ParseException("Bad type: " + valueObject);
	}

	private JPackage getPackage(final String key) {
		final String p = ClassUtils.getPackage(key);
		JPackage pack = cachePackage.get(p);
		if (null != pack) {
			return pack;
		}
		pack = codeModel._package(p.toLowerCase());
		cachePackage.put(p, pack);
		return pack;
	}

	private static Class<?> convert(final ValueObject valueObject) {
		final String type = valueObject.getType();
		return Converters.convert(type);
	}

}
