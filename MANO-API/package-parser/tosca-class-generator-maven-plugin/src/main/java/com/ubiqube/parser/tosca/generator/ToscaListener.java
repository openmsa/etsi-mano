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

	void startField(String fieldName, ValueObject value);

	void onFieldJavadoc(String x);

	void onFieldAnnotate(Class<? extends Annotation> class1);

	void onFieldAnnotate(Class<? extends Annotation> class1, String node);

	void onFieldJsonName(String key);

	void onFieldSetDefaultValue(Object def);

	void onFieldConstraints(Constraint x);

	void onFieldTerminate();

	void terminateDocument();

	void onFieldNonNull();

	void onClassDescription(String description);

}
