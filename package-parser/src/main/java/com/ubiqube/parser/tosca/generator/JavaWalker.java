package com.ubiqube.parser.tosca.generator;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
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
import com.ubiqube.parser.tosca.ValueObject;
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

public class JavaWalker extends AbstractWalker {
	private static final Logger LOG = LoggerFactory.getLogger(JavaWalker.class);
	private static final String VALUE = "value";

	private final JCodeModel codeModel = new JCodeModel();
	private final Map<String, JDefinedClass> cache = new HashMap<>();
	private final Map<String, DataType> primitive = new HashMap<>();
	private final Map<String, JPackage> cachePackage = new HashMap<>();
	private boolean nonnull = true;

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
		cache.put(currentClassName, currentClass);
		currentClass = null;
	}

	@Override
	public void onDataTypeExtend(final String derivedFrom) {
		final JClass superClass = cache.get(derivedFrom);
		currentClass._extends(superClass);
	}

	@Override
	public void startField(final String fieldName, final String type, final boolean multi) {
		final Class<?> conv = Converters.convert(type);
		JDefinedClass typ = null;
		if (null == conv) {
			typ = cache.get(type);
		}
		LOG.debug("Starting field {} => {} r={}, multi={}", fieldName, type, typ, multi);
		if (multi) {
			handleMulti(conv, typ, fieldName);

		} else {
			handleSingle(conv, typ, fieldName);
		}
	}

	private void handleSingle(final Class<?> conv, final JDefinedClass typ, final String fieldName) {
		if (null != typ) {
			currentField = currentClass.field(JMod.PRIVATE, typ, fieldName);
		} else if (null != conv) {
			currentField = currentClass.field(JMod.PRIVATE, conv, fieldName);
		} else {
			currentField = currentClass.field(JMod.PRIVATE, Object.class, fieldName);
			LOG.error("Unknown type for fieldname {}", fieldName);
		}
	}

	private void handleMulti(final Class<?> conv, final JDefinedClass typ, final String fieldName) {
		final JClass list = codeModel.ref(List.class);
		if (null != typ) {
			list.narrow(typ);
		} else if (null != conv) {
			list.narrow(conv);
		}
		currentField = currentClass.field(JMod.PRIVATE, list, fieldName);
	}

	@Override
	public void startField(final String fieldName, final ValueObject value, final boolean multi) {
		currentField = currentClass.field(JMod.PRIVATE, resolvVo(value), fieldName);
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
			currentField.annotate(Min.class).param(VALUE, Integer.parseInt(ir.getMin()));
			currentField.annotate(Max.class).param(VALUE, Integer.parseInt(ir.getMax()));
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
	public void onFieldNonNull() {
		currentField.annotate(NotNull.class);
		nonnull = true;
	}

	@Override
	public void terminateDocument() {
		try {
			codeModel.build(new File("src/generated2/java"));
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
		pack = codeModel._package(p.toLowerCase());
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
			final String subType = valueObject.getEntrySchema().getType();
			final Class<?> jTy = Converters.convert(subType);
			if (null != jTy) {
				return codeModel.ref(List.class).narrow(jTy);
			}
			final JDefinedClass cached = cache.get(subType);
			if (null != cached) {
				return codeModel.ref(List.class).narrow(cached);
			}
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
		}
		final Class<?> conv = Converters.convert(valueObject.getType());
		if (null != conv) {
			return codeModel.ref(conv);
		}
		throw new ParseException("Bad type: " + valueObject.getType());
	}
}
