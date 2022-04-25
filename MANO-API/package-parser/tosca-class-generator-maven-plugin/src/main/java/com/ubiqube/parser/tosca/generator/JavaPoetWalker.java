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

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
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
import com.ubiqube.parser.tosca.constraints.MaxLength;
import com.ubiqube.parser.tosca.constraints.MinLength;
import com.ubiqube.parser.tosca.constraints.Pattern;
import com.ubiqube.parser.tosca.constraints.ValidValues;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class JavaPoetWalker implements ToscaListener {
	private static final Logger LOG = LoggerFactory.getLogger(JavaPoetWalker.class);
	private static final String VALUE = "value";

	private final LinkedList<Context> stack = new LinkedList<>();
	private final Map<String, TypeName> cache = new HashMap<>();
	private final Map<String, DataType> primitive = new HashMap<>();
	private String currentClassName;
	private Builder classBuilder;
	private FieldSpec.Builder currentField;
	private TypeName currentFieldType;
	private boolean nonnull;
	private final Path targetFolder;

	public JavaPoetWalker(final String path) {
		targetFolder = Paths.get(path);
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
	public void startClass(final String className) {
		if (null != classBuilder) {
			final Context ctx = new Context(currentClassName, classBuilder, currentField, currentFieldType, nonnull);
			stack.push(ctx);
			LOG.debug("Pushing context => {}", currentClassName);
		}
		onClassStart(className);
	}

	private void onClassStart(final String fullClassName) {
		currentClassName = fullClassName;
		final String pack = ClassUtils.getPackage(fullClassName);
		if (null == pack) {
			throw new ParseException("Unable to add a file without a package.");
		}
		final String name = ClassUtils.getClassName(fullClassName);
		classBuilder = TypeSpec.classBuilder(name).addModifiers(Modifier.PUBLIC);
	}

	@Override
	public void terminateClass() {
		final TypeSpec cb = classBuilder.build();
		if ("java.lang.Object".equals(cb.superclass.toString())) {
			classBuilder.superclass(ToscaInernalBase.class);
		}
		serializeClass();
		cache.put(currentClassName, resolvClass(currentClassName));
		classBuilder = null;
		currentClassName = null;
		if (!stack.isEmpty()) {
			final Context context = stack.pop();
			LOG.debug("Poping context => {} restoring => {}", currentClassName, context.lCurrentClassName);
			classBuilder = context.lClassBuilder;
			currentField = context.lCurrentField;
			currentClassName = context.lCurrentClassName;
			currentFieldType = context.lCurrentFieldType;
			nonnull = context.lNonnull;
		}
	}

	private void serializeClass() {
		final String pack = ClassUtils.getPackage(currentClassName);
		final JavaFile javaFile = JavaFile
				.builder(pack, classBuilder.build())
				.indent("\t")
				.build();
		try {
			javaFile.writeTo(targetFolder);
		} catch (final IOException | RuntimeException e) {
			throw new ParseException("Exception during serialization of : " + currentClassName, e);
		}
	}

	@Override
	public void onDataTypeExtend(final String derivedFrom) {
		TypeName superClass = cache.get(derivedFrom);
		if (null == superClass) {
			superClass = ClassName.get(ClassUtils.getClassOf(ClassUtils.toscaToJava(derivedFrom)));
		}
		classBuilder.superclass(superClass);
	}

	@Override
	public void startField(final String fieldName, final String type, final boolean multi) {
		final Class<?> conv = GenericConverters.convert(type);
		TypeName typ = null;
		if (null == conv) {
			typ = cache.get(type);
			if (null == typ) {
				typ = ClassName.bestGuess(type);
			}
		}
		currentFieldType = typ;
		LOG.debug("Starting field {} => {} r={}, multi={}", fieldName, type, typ, multi);
		if (multi) {
			handleMulti(conv, typ, fieldName);
		} else {
			handleSingle(conv, typ, fieldName);
		}
	}

	private void handleSingle(final Class<?> conv, final TypeName typ, final String fieldName) {
		if (null != typ) {
			currentField = FieldSpec.builder(typ, fieldName, Modifier.PRIVATE);
		} else if (null != conv) {
			final ClassName n = ClassName.get(conv);
			currentField = FieldSpec.builder(n, fieldName, Modifier.PRIVATE);
		} else {
			final ClassName n = ClassName.get(Object.class);
			currentField = FieldSpec.builder(n, fieldName, Modifier.PRIVATE);
			LOG.warn("Unknown type for fieldname {}", fieldName);
		}
	}

	private void handleMulti(final Class<?> conv, final TypeName typ, final String fieldName) {
		final ClassName list = ClassName.get(List.class);
		TypeName subType = null;
		if (null != typ) {
			subType = ParameterizedTypeName.get(list, typ);
		} else if (null != conv) {
			final ClassName convType = ClassName.get(conv);
			subType = ParameterizedTypeName.get(list, convType);
		}
		if (null != subType) {
			currentField = FieldSpec.builder(subType, fieldName, Modifier.PRIVATE);
		} else {
			currentField = FieldSpec.builder(list, fieldName, Modifier.PRIVATE);
		}
	}

	@Override
	public void startField(final String fieldName, final ValueObject value) {
		currentFieldType = resolvVo(value);
		currentField = FieldSpec.builder(currentFieldType, fieldName, Modifier.PRIVATE);
		currentField.addAnnotation(Valid.class);
	}

	@Override
	public void onFieldJavadoc(final String x) {
		currentField.addJavadoc("$L", x);
	}

	@Override
	public void onFieldAnnotate(final Class<? extends Annotation> class1) {
		currentField.addAnnotation(class1);
	}

	@Override
	public void onFieldAnnotate(final Class<? extends Annotation> class1, final String node) {
		LOG.debug("Annotate: {}, {}", class1.getName(), node);
		final AnnotationSpec builder = AnnotationSpec.builder(class1).addMember(VALUE, "$L", "\"" + node + "\"").build();
		currentField.addAnnotation(builder);
	}

	@Override
	public void onFieldJsonName(final String key) {
		final AnnotationSpec annon = AnnotationSpec.builder(JsonProperty.class).addMember(VALUE, "$L", "\"" + key + "\"").build();
		currentField.addAnnotation(annon);
	}

	@Override
	public void onFieldSetDefaultValue(final Object def) {
		currentField.initializer("$L", PoetConverters.convert(def, currentField));
	}

	@Override
	public void onFieldConstraints(final Constraint x) {
		if (x instanceof final Pattern p) {
			final AnnotationSpec builder = AnnotationSpec
					.builder(javax.validation.constraints.Pattern.class)
					.addMember("regexp", "$L", p.getValue())
					.build();
			currentField.addAnnotation(builder);
		} else if (x instanceof final GreaterOrEqual goe) {
			final AnnotationSpec builder = AnnotationSpec
					.builder(DecimalMin.class)
					.addMember(VALUE, "$L", "\"" + goe.getValue() + "\"")
					.addMember("inclusive", "$L", "true")
					.build();
			currentField.addAnnotation(builder);
		} else if (x instanceof final GreaterThan gt) {
			final AnnotationSpec builder = AnnotationSpec
					.builder(DecimalMin.class)
					.addMember(VALUE, "$L", "\"" + gt.getValue() + "\"")
					.build();
			currentField.addAnnotation(builder);
		} else if (x instanceof final LessOrEqual loe) {
			final AnnotationSpec builder = AnnotationSpec
					.builder(DecimalMax.class)
					.addMember(VALUE, "$L", "\"" + loe.getValue() + "\"")
					.build();
			currentField.addAnnotation(builder);
		} else if (x instanceof final LessThan lt) {
			final AnnotationSpec builder = AnnotationSpec
					.builder(DecimalMax.class)
					.addMember(VALUE, "$L", "\"" + lt.getValue() + "\"")
					.addMember("inclusive", "$L", "true")
					.build();
			currentField.addAnnotation(builder);
		} else if (x instanceof ValidValues) {
			// XXX .
		} else if (x instanceof InRange ir) {
			final AnnotationSpec builder = AnnotationSpec
					.builder(Min.class)
					.addMember(VALUE, "$L", Double.valueOf(ir.getMin()).intValue())
					.build();
			currentField.addAnnotation(builder);
			final AnnotationSpec b2 = AnnotationSpec
					.builder(Max.class)
					.addMember(VALUE, "$L", Double.valueOf(ir.getMax()).intValue())
					.build();
			currentField.addAnnotation(b2);
		} else if (x instanceof final MinLength ml) {
			final AnnotationSpec builder = AnnotationSpec
					.builder(javax.validation.constraints.Size.class)
					.addMember("min", "$L", Integer.parseInt(ml.getValue()))
					.build();
			currentField.addAnnotation(builder);
		} else if (x instanceof final MaxLength ml) {
			final AnnotationSpec builder = AnnotationSpec
					.builder(javax.validation.constraints.Size.class)
					.addMember("max", "$L", Integer.parseInt(ml.getValue()))
					.build();
			currentField.addAnnotation(builder);
		} else if (x instanceof Equal) {
			// XXX .
		} else {
			throw new ParseException("Unknown constraint: " + x.getClass().getCanonicalName());
		}
	}

	@Override
	public void onFieldTerminate() {
		classBuilder.addField(currentField.build());
		final FieldSpec cf = currentField.build();
		final String fieldName = cf.name;
		final TypeName fieldType = cf.type;
		final String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		final MethodSpec.Builder mb = MethodSpec
				.methodBuilder("get" + methodName)
				.addModifiers(Modifier.PUBLIC)
				.returns(fieldType)
				.addStatement("return this." + fieldName);
		if (nonnull) {
			mb.addAnnotation(NotNull.class);
		}
		classBuilder.addMethod(mb.build());
		// Setter
		final MethodSpec.Builder methodBuilder = MethodSpec
				.methodBuilder("set" + methodName)
				.addModifiers(Modifier.PUBLIC);
		methodBuilder.addStatement("this." + fieldName + " = " + fieldName);
		if (nonnull) {
			final ParameterSpec param = ParameterSpec.builder(fieldType, fieldName, Modifier.FINAL).addAnnotation(NotNull.class).build();
			methodBuilder.addParameter(param);
		} else {
			methodBuilder.addParameter(fieldType, fieldName, Modifier.FINAL);
		}
		classBuilder.addMethod(methodBuilder.build());
		currentField = null;
		nonnull = false;
	}

	@Override
	public void terminateDocument() {
		// Nothing.
	}

	@Override
	public void onFieldNonNull() {
		currentField.addAnnotation(NotNull.class);
		nonnull = true;
	}

	@Override
	public void onClassDescription(final String description) {
		classBuilder.addJavadoc("$L", description);
	}

	@Override
	public void onFieldAnnotate(final Class<? extends Annotation> class1, final String[] array) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Annotate: {}, {}", class1.getName(), Arrays.toString(array));
		}
		final AnnotationSpec builder = AnnotationSpec.builder(class1)
				.addMember(VALUE, Stream.of(array).map(type -> CodeBlock.of("$L", "\"" + type + "\""))
						.collect(CodeBlock.joining(", ", "{ ", " }")))
				.build();
		currentField.addAnnotation(builder);
	}

	private TypeName resolvVo(final ValueObject valueObject) {
		final DataType prim = primitive.get(valueObject.getType());
		if (null != prim) {
			return ClassName.get(Integer.class);
		}
		final TypeName item = cache.get(valueObject.getType());
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
			return ClassName.get(TriggerDefinition.class);
		}
		final Class<?> conv = GenericConverters.convert(valueObject.getType());
		if (null != conv) {
			return ClassName.get(conv);
		}
		final Class<?> clazz = ClassUtils.getExistingClass(valueObject.getType());
		if (null != clazz) {
			return ClassName.get(clazz);
		}
		throw new ParseException("Bad tosca type: " + valueObject.getType());
	}

	private TypeName handleMap(final ValueObject valueObject) {
		final ClassName map = ClassName.get(Map.class);
		final ClassName string = ClassName.get(String.class);
		final String subType = valueObject.getEntrySchema().getType();
		final Class<?> jTy = GenericConverters.convert(subType);
		if (null != jTy) {
			final ClassName convType = ClassName.get(jTy);
			return ParameterizedTypeName.get(map, string, convType);
		}
		final TypeName cahed = cache.get(subType);
		if (null != cahed) {
			return ParameterizedTypeName.get(map, string, cahed);
		}
		final ClassName convType = ClassName.get(ClassUtils.getClassOf(subType));
		return ParameterizedTypeName.get(map, string, convType);
	}

	private TypeName handleList(final ValueObject valueObject) {
		final ClassName list = ClassName.get(List.class);
		final String subType = valueObject.getEntrySchema().getType();
		final Class<?> jTy = GenericConverters.convert(subType);
		if (null != jTy) {
			final ClassName convType = ClassName.get(jTy);
			return ParameterizedTypeName.get(list, convType);
		}
		if ("trigger".equals(subType)) {
			final ClassName convType = ClassName.get(TriggerDefinition.class);
			return ParameterizedTypeName.get(list, convType);
		}
		final TypeName cached = cache.get(subType);
		if (null != cached) {
			return ParameterizedTypeName.get(list, cached);
		}
		final ClassName convType = ClassName.get(ClassUtils.getClassOf(subType));
		return ParameterizedTypeName.get(list, convType);
	}

	private static TypeName resolvClass(final String className) {
		final int idx = className.lastIndexOf('.');
		if (idx == -1) {
			throw new ParseException("index = -1 in " + className);
		}
		final String pkg = ClassUtils.getPackage(className);
		final String claName = ClassUtils.getClassName(className);
		return ClassName.get(pkg, claName);
	}

	private class Context {
		private final String lCurrentClassName;
		private final Builder lClassBuilder;
		private final FieldSpec.Builder lCurrentField;
		private final TypeName lCurrentFieldType;
		private final boolean lNonnull;

		public Context(final String lCurrentClassName, final Builder lClassBuilder, final com.squareup.javapoet.FieldSpec.Builder lCurrentField, final TypeName lCurrentFieldType, final boolean lNonnull) {
			super();
			this.lCurrentClassName = lCurrentClassName;
			this.lClassBuilder = lClassBuilder;
			this.lCurrentField = lCurrentField;
			this.lCurrentFieldType = lCurrentFieldType;
			this.lNonnull = lNonnull;
		}
	}

}
