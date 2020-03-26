package com.ubiqube.parser.tosca.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import com.ubiqube.parser.tosca.constraints.Constraint;

public class ToscaWalker {
	private static final Logger LOG = LoggerFactory.getLogger(ToscaWalker.class);
	private ToscaContext root = null;
	private final Map<String, DataType> primitive = new HashMap<>();
	private final Set<String> cache = new HashSet<>();

	public void generate(final String file, final ToscaListener listener) {
		final ToscaParser tp = new ToscaParser(file);
		root = tp.getContext();
		final Map<String, CapabilityTypes> caps = root.getCapabilities();
		final Map<String, DataType> dt = root.getDataTypes();
		final Set<Entry<String, DataType>> e = dt.entrySet();
		listener.startDocument();

		final Set<Entry<String, CapabilityTypes>> cape = caps.entrySet();
		for (final Entry<String, CapabilityTypes> entry : cape) {
			if (cache.contains(entry.getKey())) {
				continue;
			}
			generateClass(entry.getKey(), entry.getValue(), listener);
		}

		for (final Entry<String, DataType> entry : e) {
			final DataType val = entry.getValue();
			if (cache.contains(entry.getKey())) {
				continue;
			}
			if (isPrimitive(val)) {
				primitive.put(entry.getKey(), val);
				listener.onPrimitiveObject(entry.getKey(), val);
			} else {
				generateClassFromDataType(entry.getKey(), entry.getValue(), listener);
				cache.add(entry.getKey());
			}
		}

		Set<Entry<String, ToscaClass>> arts = root.getArtifacts().entrySet();
		for (final Entry<String, ToscaClass> entry : arts) {
			if (cache.contains(entry.getKey())) {
				continue;
			}
			generateToscaClass(entry.getKey(), entry.getValue(), listener);
		}
		arts = root.getNodeType().entrySet();
		for (final Entry<String, ToscaClass> entry : arts) {
			if (cache.contains(entry.getKey())) {
				continue;
			}
			generateToscaClass(entry.getKey(), entry.getValue(), listener);
		}

		listener.terminateDocument();
	}

	private void generateClassFromDataType(final String className, final DataType definition, final ToscaListener listener) {
		LOG.debug("generateClassFromDataType class={}", className);
		startClass(className, definition.getDerivedFrom(), listener);

		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		if (definition.getDescription() != null) {
			listener.onClassDescription(definition.getDescription());
		}
		LOG.debug("generateClassFromDataType end {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private void generateClass(final String className, final CapabilityTypes definition, final ToscaListener listener) {
		LOG.debug("generateClass class {}", className);
		startClass(className, definition.getDerivedFrom(), listener);

		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		cache.add(className);
		if (definition.getDescription() != null) {
			listener.onClassDescription(definition.getDescription());
		}
		listener.terminateClass();
		LOG.debug("generateClass end {}", className);
	}

	private void generateToscaClass(final String className, final ToscaClass toscaClass, final ToscaListener listener) {
		LOG.info("generateToscaClass class {}", className);
		startClass(className, toscaClass.getDerivedFrom(), listener);

		Optional.ofNullable(toscaClass.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));

		Optional.ofNullable(toscaClass.getAttributes()).ifPresent(x -> generateFields(listener, x));

		Optional.ofNullable(toscaClass.getCapabilities()).ifPresent(x -> generateCaps(listener, x));

		Optional.ofNullable(toscaClass.getRequirements()).ifPresent(x -> generateRequirements(listener, x));
		if (toscaClass.getDescription() != null) {
			listener.onClassDescription(toscaClass.getDescription());
		}
		LOG.info("Caching {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private void startClass(final String classname, final String parent, final ToscaListener listener) {
		listener.startClass(classname);
		if (null != parent) {
			getExtends(parent, listener);
			LOG.debug("deivedFrom: {}->{}", classname, parent);
			listener.onDataTypeExtend(parent);
		}
	}

	private void generateFields(final ToscaListener listener, final Map<String, ValueObject> vo) {
		final Set<Entry<String, ValueObject>> attrsEntr = vo.entrySet();
		for (final Entry<String, ValueObject> entry : attrsEntr) {
			final String fieldName = fieldCamelCase(entry.getKey());
			// XXX Missing primitive block.
			resolvVo(entry.getValue(), listener);
			final ValueObject value = entry.getValue();

			if (primitive.containsKey(value.getType())) {
				listener.startField(fieldName, "integer", false);
			} else {
				listener.startField(fieldName, value);
			}

			Optional.ofNullable(value.getDescription()).ifPresent(listener::onFieldJavadoc);

			Optional.ofNullable(value.getRequired())
					.filter(Boolean.TRUE::equals)
					.ifPresent(x -> listener.onFieldNonNull());
			// Jackson Constraint Annotate
			listener.onFieldJsonName(entry.getKey());
			if (value.getDef() != null) {
				listener.onFieldSetDefaultValue(value.getDef());
			}
			if (!value.getConstraints().isEmpty()) {
				final List<Constraint> cont = value.getConstraints();
				cont.forEach(listener::onFieldConstraints);
			}
			if (primitive.containsKey(value.getType())) {
				final List<Constraint> cont = primitive.get(value.getType()).getConstraints();
				cont.forEach(listener::onFieldConstraints);
			}
			listener.onFieldTerminate();
		}
	}

	private boolean isContainer(final String type) {
		return "list".equals(type) || "map".equals(type);
	}

	private void resolvVo(final ValueObject valueObject, final ToscaListener listener) {
		if (cache.contains(valueObject.getType())) {
			return;
		}

		final String type = valueObject.getType();
		if (isContainer(type)) {
			final String subType = valueObject.getEntrySchema().getType();
			final Class<?> jTy = Converters.convert(subType);
			if (null != jTy) {
				return;
			}
			if (cache.contains(subType)) {
				return;
			}
			final DataType dType = root.getDataTypes().get(subType);
			if (null != dType) {
				generateClassFromDataType(subType, dType, listener);
			} else {
				generateToscaClass(subType,
						root.getNodeType().get(subType), listener);
			}
			return;
		}
		if (null != primitive.get(type)) {
			LOG.error("Type {} is a primitive.", type);
			return;
		}
		final DataType dType = root.getDataTypes().get(valueObject.getType());
		if (null != dType) {
			if (isPrimitive(dType)) {
				LOG.error("Type {} is an unknown primitive.", type);
				primitive.put(type, dType);
				listener.onPrimitiveObject(type, dType);
				return;
			}
			generateClassFromDataType(valueObject.getType(), dType, listener);
		}
	}

	private static void generateRequirements(final ToscaListener listener, final RequirementDefinition requirements) {
		requirements.getRequirements().forEach((final String x, final Requirement y) -> {
			final String fieldName = fieldCamelCase(x + "_req");
			final List<String> occ = y.getOccurrences();
			LOG.debug("Forcing field Object");
			listener.startField(fieldName, "java.lang.Object", isList(occ));

			// XXX: Probably one may be a concrete type.
			if (null != y.getNode()) {
				listener.onFieldAnnotate(Node.class, y.getNode());
			}
			if (null != y.getCapability()) {
				listener.onFieldAnnotate(Capability.class, y.getCapability());
			}
			if (null != y.getRelationship()) {
				listener.onFieldAnnotate(Relationship.class, y.getRelationship());
			}
			listener.onFieldAnnotate(JsonProperty.class, x);
			listener.onFieldTerminate();
		});

	}

	private void generateCaps(final ToscaListener listener, final Map<String, CapabilityDefinition> capabilities) {
		capabilities.forEach((final String x, final CapabilityDefinition y) -> {
			final CapabilityTypes caps = root.getCapabilities().get(y.getType());
			if (!cache.contains(y.getType())) {
				generateClass(y.getType(), caps, listener);
			}
			if ((y.getAttributes() != null) && !y.getAttributes().isEmpty()) {
				throw new ParseException("Unable to handle Attributes in " + x + '=' + y.getType());
			}
			if ((y.getProperties() != null) && !y.getProperties().getProperties().isEmpty()) {
				throw new ParseException("Unable to handle properties in " + x + '=' + y.getType());
			}
			final String fieldName = fieldCamelCase(x);
			LOG.debug("CAPS: Start field {} of type {}", fieldName, y.getType());
			listener.startField(fieldName, y.getType(), false);
			listener.onFieldJavadoc("Caps.");
			if (null != y.getDescription()) {
				listener.onFieldJavadoc(y.getDescription());
			}
			listener.onFieldTerminate();
		});
	}

	private void getExtends(final String derivedFrom, final ToscaListener listener) {
		if (!cache.contains(derivedFrom)) {
			final CapabilityTypes def = root.getCapabilities().get(derivedFrom);
			boolean found = false;
			if (null != def) {
				generateClass(derivedFrom, def, listener);
				found = true;
			} else {
				final ToscaClass node = root.getNodeType().get(derivedFrom);
				if (node != null) {
					generateToscaClass(derivedFrom, node, listener);
					found = true;
				}
				final DataType dt = root.getDataTypes().get(derivedFrom);
				if (null != dt) {
					generateClassFromDataType(derivedFrom, dt, listener);
					found = true;
				}
			}
			if (!found) {
				throw new ParseException("Could not find parent class: " + derivedFrom);
			}
			LOG.info("Caching {}", derivedFrom);
			cache.add(derivedFrom);
		}
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

	private static boolean isPrimitive(final DataType val) {
		if (null == val.getDerivedFrom()) {
			return false;
		}
		boolean ret = false;
		switch (val.getDerivedFrom()) {
		case "integer":
		case "string":
			ret = true;
			break;
		default:
			ret = false;
		}
		return ret;
	}
}
