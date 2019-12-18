package com.ubiqube.parser.tosca.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;

import javax.validation.constraints.NotNull;

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
import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Range;
import com.ubiqube.parser.tosca.scalar.Size;
import com.ubiqube.parser.tosca.scalar.Time;
import com.ubiqube.parser.tosca.scalar.Version;

public class ToscaWalker {
	private static final Logger LOG = LoggerFactory.getLogger(ToscaWalker.class);
	private final ToscaParser tp = new ToscaParser();
	private ToscaContext root = null;
	private final Map<String, DataType> primitive = new HashMap<>();
	private final Set<String> cache = new HashSet<>();

	public void generate(final String file, final ToscaListener listener) {
		root = tp.parse(file);
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
		listener.startClass(className);
		if (null != definition.getDerivedFrom()) {
			final String derivedFrom = definition.getDerivedFrom();
			getExtends(derivedFrom, listener);
			LOG.debug("deivedFrom: {}", derivedFrom);
			listener.onDataTypeExtend(derivedFrom);
		}
		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		LOG.debug("generateClassFromDataType end {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private void generateClass(final String className, final CapabilityTypes definition, final ToscaListener listener) {
		LOG.debug("generateClass class {}", className);
		listener.startClass(className);

		if (null != definition.getDerivedFrom()) {
			if (!cache.contains(definition.getDerivedFrom())) {
				final CapabilityTypes def = root.getCapabilities().get(definition.getDerivedFrom());
				generateClass(definition.getDerivedFrom(), def, listener);
				cache.add(definition.getDerivedFrom());
			}
			listener.onDataTypeExtend(definition.getDerivedFrom());
		}
		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		cache.add(className);
		listener.terminateClass();
		LOG.debug("generateClass end {}", className);
	}

	private void generateToscaClass(final String className, final ToscaClass toscaClass, final ToscaListener listener) {
		LOG.info("generateToscaClass class {}", className);
		if ("tosca.nodes.Root".equals(className)) {
			LOG.debug("fff");
		}
		listener.startClass(className);
		if (null != toscaClass.getDerivedFrom()) {
			getExtends(toscaClass.getDerivedFrom(), listener);
			listener.onDataTypeExtend(toscaClass.getDerivedFrom());
		}
		Optional.ofNullable(toscaClass.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));

		Optional.ofNullable(toscaClass.getAttributes()).ifPresent(x -> generateFields(listener, x));

		Optional.ofNullable(toscaClass.getCapabilities()).ifPresent(x -> generateCaps(listener, x));

		Optional.ofNullable(toscaClass.getRequirements()).ifPresent(x -> generateRequirements(listener, x));

		LOG.info("Caching {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private void generateFields(final ToscaListener listener, final Map<String, ValueObject> vo) {
		final Set<Entry<String, ValueObject>> attrsEntr = vo.entrySet();
		for (final Entry<String, ValueObject> entry : attrsEntr) {
			final ValueObject val = entry.getValue();
			final String fieldName = fieldCamelCase(entry.getKey());
			// XXX Missing primitive block.
			resolvVo(entry.getValue(), listener);
			final ValueObject value = entry.getValue();
			if (isPrimitive(value.getType())) {
				listener.startField(fieldName, "integer", false);
			} else {
				listener.startField(fieldName, value, false);
			}

			Optional.ofNullable(val.getDescription()).ifPresent(listener::onFieldJavadoc);

			Optional.ofNullable(val.getRequired())
					.filter(Boolean.TRUE::equals)
					.ifPresent(x -> listener.onFieldAnnotate(NotNull.class));
			// Jackson Constraint Annotate
			listener.onFieldJsonName(entry.getKey());
			if (val.getDef() != null) {
				listener.onFieldSetDefaultValue(val.getDef());
			}
			if (!val.getConstraints().isEmpty()) {
				// TODO Add Constraint.
				final List<Constraint> cont = val.getConstraints();
				cont.forEach(listener::onFieldConstraints);
			}
			if (null != primitive.get(val.getType())) {
				final List<Constraint> cont = primitive.get(val.getType()).getConstraints();
				cont.forEach(listener::onFieldConstraints);
			}
			listener.onFieldTerminate();
		}
	}

	private void resolvVo(final ValueObject valueObject, final ToscaListener listener) {
		if (cache.contains(valueObject.getType())) {
			return;
		}
		final String type = valueObject.getType();
		if ("list".equals(type)) {
			final String subType = valueObject.getEntrySchema().getType();
			final Class<?> jTy = convert(subType);
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
		if ("map".equals(type)) {
			final String subType = valueObject.getEntrySchema().getType();
			final Class<?> jTy = convert(subType);
			if (null != jTy) {
				return;
			}
			if (cache.contains(subType)) {
				return;
			}
			// TODO
			LOG.info("Map of {}", subType);
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
			return;
		}
		final DataType dType = root.getDataTypes().get(valueObject.getType());
		if (null != dType) {
			generateClassFromDataType(valueObject.getType(), dType, listener);
			return;
		}
	}

	private static void generateRequirements(final ToscaListener listener, final RequirementDefinition requirements) {
		requirements.getRequirements().forEach((final String x, final Requirement y) -> {
			final String fieldName = fieldCamelCase(x + "_req");
			final List<String> occ = y.getOccurrences();
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
			listener.startField(fieldName, y.getType(), true);
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

	private static boolean isPrimitive(final String type) {
		if (null == type) {
			return false;
		}
		if ("integer".equals(type)) {
			return true;
		}
		if ("string".equals(type)) {
			return true;
		}
		return false;
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
}
