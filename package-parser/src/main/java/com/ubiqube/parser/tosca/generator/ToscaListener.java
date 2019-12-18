package com.ubiqube.parser.tosca.generator;

import java.lang.annotation.Annotation;

import com.ubiqube.parser.tosca.DataType;
import com.ubiqube.parser.tosca.ValueObject;
import com.ubiqube.parser.tosca.constraints.Constraint;

public interface ToscaListener {

	void startDocument();

	void onPrimitiveObject(String key, DataType val);

	void startClass(String className);

	void terminateClass();

	void onDataTypeExtend(String derivedFrom);

	void startField(String fieldName, String type, boolean multi);

	void startField(String fieldName, ValueObject value, boolean multi);

	void onFieldJavadoc(String x);

	void onFieldAnnotate(Class<? extends Annotation> class1);

	void onFieldAnnotate(Class<? extends Annotation> class1, String node);

	void onFieldJsonName(String key);

	void onFieldSetDefaultValue(Object def);

	void onFieldConstraints(Constraint x);

	void onFieldTerminate();

	void terminateDocument();

}
