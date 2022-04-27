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
package com.ubiqube.parser.tosca.generator;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.Artifact;
import com.ubiqube.parser.tosca.CapabilityDefinition;
import com.ubiqube.parser.tosca.CapabilityTypes;
import com.ubiqube.parser.tosca.DataType;
import com.ubiqube.parser.tosca.GroupType;
import com.ubiqube.parser.tosca.InterfaceType;
import com.ubiqube.parser.tosca.OperationDefinition;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.PolicyDefinition;
import com.ubiqube.parser.tosca.PolicyType;
import com.ubiqube.parser.tosca.Requirement;
import com.ubiqube.parser.tosca.RequirementDefinition;
import com.ubiqube.parser.tosca.ToscaClass;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ToscaParser;
import com.ubiqube.parser.tosca.ValueObject;
import com.ubiqube.parser.tosca.annotations.Capability;
import com.ubiqube.parser.tosca.annotations.Members;
import com.ubiqube.parser.tosca.annotations.Node;
import com.ubiqube.parser.tosca.annotations.Occurence;
import com.ubiqube.parser.tosca.annotations.Relationship;
import com.ubiqube.parser.tosca.constraints.Constraint;

public class ToscaWalker {
	private static final String TOSCA_ARTIFACTS_ROOT = "tosca.artifacts.Root";
	private static final String TOSCA_NODES_ROOT = "tosca.nodes.Root";
	private static final String TOSCA_POLICIES_ROOT = "tosca.policies.Root";
	private static final String STRING = "string";
	private static final Logger LOG = LoggerFactory.getLogger(ToscaWalker.class);
	private ToscaContext root = null;
	private final Map<String, DataType> primitive = new HashMap<>();
	private final Set<String> cache = new HashSet<>();

	public void generate(final String file, final ToscaListener listener) {
		final ToscaParser tp = new ToscaParser(new File(file));
		root = tp.getContext();

		listener.startDocument();
		handleInterfaces(listener);
		handleCapability(listener);
		handleDataType(listener);
		handleArtefact(listener);
		handleNodeType(listener);
		handleGroupType(listener);
		handlePolicies(listener);
		listener.terminateDocument();
	}

	private void handleInterfaces(final ToscaListener listener) {
		final Map<String, InterfaceType> inter = root.getInterfaceTypes();
		final Set<Entry<String, InterfaceType>> interEs = inter.entrySet();
		for (final Entry<String, InterfaceType> entry : interEs) {
			if (cache.contains(entry.getKey())) {
				// XXX to do.
			}
			generateClass(entry.getKey(), entry.getValue(), listener);
		}
	}

	private void handleCapability(final ToscaListener listener) {
		final Map<String, CapabilityTypes> caps = root.getCapabilities();
		final Set<Entry<String, CapabilityTypes>> cape = caps.entrySet();
		for (final Entry<String, CapabilityTypes> entry : cape) {
			if (cache.contains(entry.getKey())) {
				continue;
			}
			generateClass(entry.getKey(), entry.getValue(), listener);
		}
	}

	private void handleDataType(final ToscaListener listener) {
		final Map<String, DataType> dt = root.getDataTypes();
		final Set<Entry<String, DataType>> e = dt.entrySet();
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
	}

	private void handleNodeType(final ToscaListener listener) {
		final Set<Entry<String, ToscaClass>> arts = root.getNodeType().entrySet();
		for (final Entry<String, ToscaClass> entry : arts) {
			if (cache.contains(entry.getKey())) {
				continue;
			}
			if (TOSCA_NODES_ROOT.equals(entry.getKey())) {
				createNodeRoot(listener);
			} else {
				generateToscaClass(entry.getKey(), entry.getValue(), listener);
			}
		}
	}

	private void handleArtefact(final ToscaListener listener) {
		final Set<Entry<String, ToscaClass>> arts = root.getArtifacts().entrySet();
		for (final Entry<String, ToscaClass> entry : arts) {
			if (cache.contains(entry.getKey())) {
				continue;
			}
			if (TOSCA_ARTIFACTS_ROOT.equals(entry.getKey())) {
				createArtifactRoot(listener);
			} else {
				generateToscaClass(entry.getKey(), entry.getValue(), listener);
			}
		}
	}

	private void handleGroupType(final ToscaListener listener) {
		final Set<Entry<String, GroupType>> groups = root.getGroupType().entrySet();
		for (final Entry<String, GroupType> entry : groups) {
			if (cache.contains(entry.getKey())) {
				continue;
			}
			generateGroupType(entry.getKey(), entry.getValue(), listener);
		}
	}

	private void handlePolicies(final ToscaListener listener) {
		final Set<Entry<String, PolicyType>> policies = root.getPoliciesType().entrySet();
		for (final Entry<String, PolicyType> entry : policies) {
			if (cache.contains(entry.getKey())) {
				continue;
			}
			if (TOSCA_POLICIES_ROOT.equals(entry.getKey())) {
				createPolicyRoot(listener);
			} else {
				generatePolicyType(entry.getKey(), entry.getValue(), listener);
			}
		}
	}

	private void createPolicyRoot(final ToscaListener listener) {
		startClass(TOSCA_POLICIES_ROOT, null, listener);

		listener.startField("triggers", ValueObject.mapOf(PolicyDefinition.class.getName()));
		listener.onFieldTerminate();

		cache.add(TOSCA_POLICIES_ROOT);
		listener.terminateClass();
	}

	private void createNodeRoot(final ToscaListener listener) {
		startClass(TOSCA_NODES_ROOT, null, listener);

		listener.startField("artifacts", ValueObject.mapOf(Artifact.class.getName()));
		listener.onFieldTerminate();

		cache.add(TOSCA_NODES_ROOT);
		listener.terminateClass();
	}

	private void createArtifactRoot(final ToscaListener listener) {
		startClass(TOSCA_ARTIFACTS_ROOT, null, listener);

		listener.startField("file", new ValueObject(STRING));
		listener.onFieldTerminate();
		listener.startField("repository", new ValueObject(STRING));
		listener.onFieldTerminate();
		listener.startField("deploy_path", new ValueObject(STRING));
		listener.onFieldTerminate();
		listener.startField("artifact_version", new ValueObject(STRING));
		listener.onFieldTerminate();
		// listener.startField("checksum", new ValueObject(STRING));
		// listener.onFieldTerminate();
		listener.startField("checksum_algorithm", new ValueObject(STRING));
		listener.onFieldTerminate();
		// Add properties as Map string, string ?
		cache.add(TOSCA_ARTIFACTS_ROOT);
		listener.terminateClass();
	}

	private void generatePolicyType(final String className, final PolicyType definition, final ToscaListener listener) {
		LOG.debug("generateClassPolicyType class={}", className);
		startClass(className, definition.getDerivedFrom(), listener);
		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		Optional.ofNullable(definition.getDescription()).ifPresent(listener::onClassDescription);
		// add members
		final ValueObject vo = ValueObject.listOf(STRING);
		listener.startField("targets", vo);
		if (null != definition.getTargets()) {
			definition.getTargets().forEach(listener::onClassDescription);
		}
		listener.onFieldTerminate();
		LOG.debug("generateClassPolicyType end {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private void generateGroupType(final String className, final GroupType definition, final ToscaListener listener) {
		LOG.debug("generateClassGroupType class={}", className);
		startClass(className, definition.getDerivedFrom(), listener);
		Optional.ofNullable(definition.getAttributes()).ifPresent(x -> generateFields(listener, x));
		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		Optional.ofNullable(definition.getDescription()).ifPresent(listener::onClassDescription);
		// add members
		final ValueObject vo = ValueObject.listOf(STRING);
		listener.startField("members", vo);
		if (null != definition.getMembers()) {
			definition.getMembers().forEach(x -> listener.onFieldAnnotate(Members.class, x));
		}
		listener.onFieldTerminate();
		LOG.debug("generateClassGroupType end {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private void generateClassFromPolicyType(final String className, final PolicyType definition, final ToscaListener listener) {
		LOG.debug("generateClassFromPolicyType class={}", className);
		startClass(className, definition.getDerivedFrom(), listener);

		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		Optional.ofNullable(definition.getDescription()).ifPresent(listener::onClassDescription);
		LOG.debug("generateClassFromPolicyType end {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private void generateClassFromDataType(final String className, final DataType definition, final ToscaListener listener) {
		LOG.debug("generateClassFromDataType class={}", className);
		startClass(className, definition.getDerivedFrom(), listener);

		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		Optional.ofNullable(definition.getDescription()).ifPresent(listener::onClassDescription);
		LOG.debug("generateClassFromDataType end {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private void generateClass(final String className, final InterfaceType definition, final ToscaListener listener) {
		LOG.debug("generateClass interface {}", className);
		startClass(className, definition.getDerivedFrom(), listener);
		Optional.ofNullable(definition.getInputs()).ifPresent(x -> generateFields(listener, x.getProperties()));
		Optional.ofNullable(definition.getOperations()).ifPresent(x -> generateOperations(listener, x));
		cache.add(className);
		Optional.ofNullable(definition.getDescription()).ifPresent(listener::onClassDescription);
		listener.terminateClass();
		LOG.debug("generateClass end {}", className);
	}

	private static void generateOperations(final ToscaListener listener, final Map<String, OperationDefinition> operations) {
		operations.entrySet().forEach(x -> {
			final String field = fieldCamelCase(x.getKey());
			listener.startField(field, OperationDefinition.class.getName(), false);
			listener.onFieldTerminate();
		});
	}

	private void generateClass(final String className, final CapabilityTypes definition, final ToscaListener listener) {
		LOG.debug("generateClass class {}", className);
		startClass(className, definition.getDerivedFrom(), listener);

		Optional.ofNullable(definition.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		cache.add(className);
		Optional.ofNullable(definition.getDescription()).ifPresent(listener::onClassDescription);
		listener.terminateClass();
		LOG.debug("generateClass end {}", className);
	}

	private void generateToscaClass(final String className, final ToscaClass toscaClass, final ToscaListener listener) {
		LOG.info("generate class: {}", className);
		startClass(className, toscaClass.getDerivedFrom(), listener);
		Optional.ofNullable(toscaClass.getProperties()).ifPresent(x -> generateFields(listener, x.getProperties()));
		Optional.ofNullable(toscaClass.getAttributes()).ifPresent(x -> generateFields(listener, x));
		Optional.ofNullable(toscaClass.getCapabilities()).ifPresent(x -> generateCaps(listener, x));
		Optional.ofNullable(toscaClass.getRequirements()).ifPresent(x -> generateRequirements(listener, x));
		Optional.ofNullable(toscaClass.getDescription()).ifPresent(listener::onClassDescription);
		Optional.ofNullable(toscaClass.getInterfaces()).ifPresent(x -> generateInterface(listener, x));
		LOG.debug("Caching {}", className);
		cache.add(className);
		listener.terminateClass();
	}

	private static void generateInterface(final ToscaListener listener, final Map<String, InterfaceType> x2) {
		x2.entrySet().forEach(x -> {
			final String v = Optional.ofNullable(x.getValue().getDerivedFrom()).orElse(x.getValue().getType());
			listener.startField(x.getKey(), v, false);
			listener.onFieldTerminate();
		});
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
			if (!value.getConstraints().isEmpty() && !value.getType().startsWith("scalar-unit.")) {
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

	private static boolean isContainer(final String type) {
		return "list".equals(type) || "map".equals(type);
	}

	private void resolvVo(final ValueObject valueObject, final ToscaListener listener) {
		if (cache.contains(valueObject.getType())) {
			return;
		}

		final String type = valueObject.getType();
		if (isContainer(type)) {
			handleContainer(valueObject, listener);
			return;
		}
		if (null != primitive.get(type)) {
			LOG.warn("Type {} is a primitive.", type);
			return;
		}
		final DataType dType = root.getDataTypes().get(valueObject.getType());
		if (null != dType) {
			if (isPrimitive(dType)) {
				LOG.warn("Type {} is an unknown primitive.", type);
				primitive.put(type, dType);
				listener.onPrimitiveObject(type, dType);
				return;
			}
			generateClassFromDataType(valueObject.getType(), dType, listener);
		}
	}

	private void handleContainer(final ValueObject valueObject, final ToscaListener listener) {
		final String subType = valueObject.getEntrySchema().getType();
		final Class<?> jTy = GenericConverters.convert(subType);
		if (null != jTy || cache.contains(subType)) {
			return;
		}
		final DataType dType = root.getDataTypes().get(subType);
		if (null != dType) {
			generateClassFromDataType(subType, dType, listener);
		} else {
			if (classExistOnClassPath(subType)) {
				cache.add(subType);
				return;
			}
			final ToscaClass nt = root.getNodeType().get(subType);
			if (null == nt) {
				throw new IllegalArgumentException(subType + " is undefnied.");
			}
			generateToscaClass(subType,
					nt, listener);
		}
	}

	private static boolean classExistOnClassPath(final String subType) {
		try {
			Class.forName(subType);
			return true;
		} catch (final ClassNotFoundException e) {
			LOG.trace("", e);
		}
		return false;
	}

	private static void generateRequirements(final ToscaListener listener, final RequirementDefinition requirements) {
		final Map<String, Requirement> req = requirements.getRequirements().stream().flatMap(x -> x.entrySet().stream()).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		req.forEach((final String x, final Requirement y) -> {
			final String fieldName = fieldCamelCase(x + "_req");
			LOG.debug("Forcing field Object");
			listener.startField(fieldName, STRING, isList(y.getOccurrences()));
			if (null != y.getOccurrences()) {
				listener.onFieldAnnotate(Occurence.class, y.getOccurrences().toArray(new String[0]));
			}
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
			if (!cache.contains(y.getType()) && !classExistOnClassPath(y.getType())) {
				generateClass(y.getType(), caps, listener);
			}
			if (y.getAttributes() != null && !y.getAttributes().isEmpty()) {
				throw new ParseException("Unable to handle Attributes in " + x + '=' + y.getType());
			}
			if (y.getProperties() != null && !y.getProperties().getProperties().isEmpty()) {
				throw new ParseException("Unable to handle properties in " + x + '=' + y.getType());
			}
			final String fieldName = fieldCamelCase(x);
			LOG.debug("CAPS: Start field {} of type {}", fieldName, y.getType());
			// XXX: Add occurences.
			listener.startField(fieldName, y.getType(), false);
			listener.onFieldJavadoc("Caps.");
			if (null != y.getDescription()) {
				listener.onFieldJavadoc(y.getDescription());
			}
			listener.onFieldTerminate();
		});
	}

	private void getExtends(final String derivedFrom, final ToscaListener listener) {
		if (cache.contains(derivedFrom)) {
			return;
		}
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
			final PolicyType pol = root.getPoliciesType().get(derivedFrom);
			if (null != pol) {
				generateClassFromPolicyType(derivedFrom, pol, listener);
				found = true;
			}
			if (!found && classExistOnClassPath(ClassUtils.toscaToJava(derivedFrom))) {
				found = true;
			}
		}
		if (!found) {
			throw new ParseException("Could not find parent class: " + derivedFrom);
		}
		LOG.debug("Caching {}", derivedFrom);
		cache.add(derivedFrom);
	}

	private static boolean isList(final List<String> occ) {
		if (null == occ || occ.size() < 2) {
			return false;
		}
		final String indice = occ.get(1);
		return "UNBOUNDED".equals(indice) || Integer.parseInt(indice) > 1;
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
		final boolean ret = false;
		return switch (val.getDerivedFrom()) {
		case "integer", STRING -> true;
		default -> false;
		};
	}

}
