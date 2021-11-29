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
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.ubiqube.parser.tosca.DataType;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.TriggerDefinition;
import com.ubiqube.parser.tosca.ValueObject;
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

import edu.emory.mathcs.backport.java.util.Arrays;

public class JavaWalker extends AbstractWalker {
	private static final Logger LOG = LoggerFactory.getLogger(JavaWalker.class);
	private static final String VALUE = "value";

	private final JCodeModel codeModel = new JCodeModel();
	private final Map<String, JDefinedClass> cache = new HashMap<>();
	private final Map<String, DataType> primitive = new HashMap<>();
	private final Map<String, JPackage> cachePackage = new HashMap<>();
	private boolean nonnull = true;
	private final String directoryOutput;

	public JavaWalker(final String _directoryOutput) {
		directoryOutput = _directoryOutput;
	}

	@Override
	public void startDocument() {
		// Nothing.
	}

	@Override
	public void onPrimitiveObject(final String key, final DataType val) {
		primitive.put(key, val);
	}

	@Override
	public void onClassStart(final String className) {
		currentClassName = className;
		final JPackage pack = getPackage(className);
		if (null == pack) {
			throw new ParseException("Unable to add a file without a package.");
		}
		currentClass = createClass(pack, ClassUtils.getClassName(className));
	}

	@Override
	public void onClassTerminate() {
		if ("java.lang.Object".equals(currentClass._extends().fullName())) {
			currentClass._extends(ToscaInernalBase.class);
		}
		cache.put(currentClassName, currentClass);
		currentClass = null;
	}

	@Override
	public void onDataTypeExtend(final String derivedFrom) {
		JClass superClass = cache.get(derivedFrom);
		if (null == superClass) {
			superClass = codeModel.ref(getClassOf(ClassUtils.toscaToJava(derivedFrom)));
		}
		currentClass._extends(superClass);
	}

	@Override
	public void startField(final String fieldName, final String type, final boolean multi) {
		final Class<?> conv = Converters.convert(type);
		JClass typ = null;
		if (null == conv) {
			typ = cache.get(type);
			if (null == typ) {
				typ = codeModel.directClass(type);
			}
		}
		LOG.debug("Starting field {} => {} r={}, multi={}", fieldName, type, typ, multi);
		if (multi) {
			handleMulti(conv, typ, fieldName);

		} else {
			handleSingle(conv, typ, fieldName);
		}
	}

	private void handleSingle(final Class<?> conv, final JClass typ, final String fieldName) {
		if (null != typ) {
			currentField = currentClass.field(JMod.PRIVATE, typ, fieldName);
		} else if (null != conv) {
			currentField = currentClass.field(JMod.PRIVATE, conv, fieldName);
		} else {
			currentField = currentClass.field(JMod.PRIVATE, Object.class, fieldName);
			LOG.warn("Unknown type for fieldname {}", fieldName);
		}
	}

	private void handleMulti(final Class<?> conv, final JClass typ, final String fieldName) {
		final JClass list = codeModel.ref(List.class);
		if (null != typ) {
			list.narrow(typ);
		} else if (null != conv) {
			list.narrow(conv);
		}
		currentField = currentClass.field(JMod.PRIVATE, list, fieldName);
	}

	@Override
	public void startField(final String fieldName, final ValueObject value) {
		currentField = currentClass.field(JMod.PRIVATE, resolvVo(value), fieldName);
		currentField.annotate(Valid.class);
	}

	@Override
	public void onFieldJavadoc(final String text) {
		currentField.javadoc().add(text);
	}

	@Override
	public void onFieldAnnotate(final Class<? extends Annotation> class1) {
		currentField.annotate(class1);
	}

	@Override
	public void onFieldJsonName(final String key) {
		currentField.annotate(JsonProperty.class).param(VALUE, key);
	}

	@Override
	public void onFieldSetDefaultValue(final Object def) {
		currentField.init(Converters.convert(def, currentField.type()));
	}

	@Override
	public void onFieldConstraints(final Constraint x) {
		// XXX: All numéric values maybe scalars.
		if (x instanceof Pattern) {
			currentField.annotate(javax.validation.constraints.Pattern.class).param("regexp", ((Pattern) x).getValue());
		} else if (x instanceof GreaterOrEqual) {
			currentField.annotate(DecimalMin.class).param(VALUE, ((GreaterOrEqual) x).getValue()).param("inclusive", true);
		} else if (x instanceof GreaterThan) {
			currentField.annotate(DecimalMin.class).param(VALUE, ((GreaterThan) x).getValue());
		} else if (x instanceof LessOrEqual) {
			currentField.annotate(DecimalMax.class).param(VALUE, ((LessOrEqual) x).getValue()).param("inclusive", true);
		} else if (x instanceof LessThan) {
			currentField.annotate(DecimalMax.class).param(VALUE, ((LessThan) x).getValue());
		} else if (x instanceof ValidValues) {
			// XXX .
		} else if (x instanceof InRange) {
			final InRange ir = (InRange) x;
			currentField.annotate(Min.class).param(VALUE, Double.valueOf(ir.getMin()).intValue());
			currentField.annotate(Max.class).param(VALUE, Double.valueOf(ir.getMax()).intValue());
		} else if (x instanceof MinLength) {
			final MinLength ml = (MinLength) x;
			currentField.annotate(javax.validation.constraints.Size.class).param("min", Integer.parseInt(ml.getValue()));
		} else if (x instanceof Equal) {
			// XXX .
		} else {
			throw new ParseException("Unknown constraint: " + x.getClass().getCanonicalName());
		}
	}

	@Override
	public void onFieldTerminate() {
		final String fieldName = currentField.name();
		final String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		final JMethod getter = currentClass.method(JMod.PUBLIC, currentField.type(), "get" + methodName);
		if (nonnull) {
			getter.annotate(NotNull.class);
		}
		getter.body()._return(currentField);
		// Setter
		final JMethod setVar = currentClass.method(JMod.PUBLIC, codeModel.VOID, "set" + methodName);
		final JVar param = setVar.param(currentField.type(), currentField.name());
		if (nonnull) {
			param.annotate(NotNull.class);
		}
		setVar.body().assign(JExpr._this().ref(currentField.name()), JExpr.ref(currentField.name()));

		currentField = null;
		nonnull = false;
	}

	@Override
	public void onFieldAnnotate(final Class<? extends Annotation> class1, final String node) {
		LOG.debug("Annotate: {}, {}", class1.getName(), node);
		currentField.annotate(class1).param(VALUE, node);
	}

	@Override
	public void onFieldAnnotate(final Class<? extends Annotation> class1, final String[] array) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Annotate: {}, {}", class1.getName(), Arrays.toString(array));
		}
		final JAnnotationArrayMember ann = currentField.annotate(class1).paramArray(VALUE);
		for (final String string : array) {
			ann.param(string);
		}
	}

	@Override
	public void onFieldNonNull() {
		currentField.annotate(NotNull.class);
		nonnull = true;
	}

	@Override
	public void terminateDocument() {
		try {
			final File file = new File(directoryOutput);
			file.mkdirs();
			codeModel.build(new File(directoryOutput));
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	@Override
	public void onClassDescription(final String description) {
		currentClass.javadoc().add(description);
	}

	private JPackage getPackage(final String key) {
		final String p = ClassUtils.getPackage(key);
		JPackage pack = cachePackage.get(p);
		if (null != pack) {
			return pack;
		}
		pack = codeModel._package(p);
		cachePackage.put(p, pack);
		return pack;
	}

	private static JDefinedClass createClass(final JPackage pack, final String classname) {
		try {
			return pack._class(classname);
		} catch (final JClassAlreadyExistsException e) {
			throw new ParseException(e);
		}
	}

	private JType resolvVo(final ValueObject valueObject) {
		final DataType prim = primitive.get(valueObject.getType());
		if (null != prim) {
			return codeModel.ref(Integer.class);
		}
		final JDefinedClass item = cache.get(valueObject.getType());
		if (null != item) {
			return item;
		}
		final String type = valueObject.getType();
		if ("list".equals(type)) {
			return handleList(valueObject);
		}
		if ("map".equals(type)) {
			return handleMap(valueObject);
		}
		if ("trigger".equals(type)) {
			return codeModel.ref(TriggerDefinition.class);
		}
		final Class<?> conv = Converters.convert(valueObject.getType());
		if (null != conv) {
			return codeModel.ref(conv);
		}
		final Class<?> clazz = getExistingClass(valueObject.getType());
		if (null != clazz) {
			return codeModel.ref(clazz);
		}
		throw new ParseException("Bad tosca type: " + valueObject.getType());
	}

	private JType handleMap(final ValueObject valueObject) {
		final String subType = valueObject.getEntrySchema().getType();
		final Class<?> jTy = Converters.convert(subType);
		if (null != jTy) {
			return codeModel.ref(Map.class).narrow(String.class, jTy);
		}
		final JDefinedClass cahed = cache.get(subType);
		if (null != cahed) {
			return codeModel.ref(Map.class).narrow(String.class).narrow(cahed);
		}
		return codeModel.ref(Map.class).narrow(String.class, getClassOf(subType));
	}

	private JType handleList(final ValueObject valueObject) {
		final String subType = valueObject.getEntrySchema().getType();
		final Class<?> jTy = Converters.convert(subType);
		if (null != jTy) {
			return codeModel.ref(List.class).narrow(jTy);
		}
		if ("trigger".equals(subType)) {
			return codeModel.ref(List.class).narrow(TriggerDefinition.class);
		}
		final JDefinedClass cached = cache.get(subType);
		if (null != cached) {
			return codeModel.ref(List.class).narrow(cached);
		}
		return codeModel.ref(List.class).narrow(getClassOf(subType));
	}

	private static Class<?> getClassOf(final String subType) {
		try {
			return Class.forName(subType);
		} catch (final ClassNotFoundException e) {
			throw new ParseException("Unknown subtype: " + subType, e);
		}
	}

	private static Class<?> getExistingClass(final String type) {
		try {
			return Class.forName(type);
		} catch (final ClassNotFoundException e) {
			LOG.trace("", e);
		}
		return null;
	}
}
