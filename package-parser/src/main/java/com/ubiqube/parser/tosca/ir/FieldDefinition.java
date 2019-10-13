package com.ubiqube.parser.tosca.ir;

import java.util.Map.Entry;
import java.util.Set;

import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.ValueObject;

public class FieldDefinition {

	public FieldDefinition(final Set<Entry<String, ValueObject>> entries) {
		for (final Entry<String, ValueObject> entry : entries) {
			final String key = entry.getKey();
			if ("type".equals(key)) {
			} else if ("description".equals(key)) {

			} else if ("required".equals(key)) {

			} else if ("default".equals(key)) {

			} else if ("status".equals(key)) {

			} else if ("constraints".equals(key)) {

			} else if ("entry_schema".equals(key)) {

			} else if ("external-schema".equals(key)) {

			} else if ("metadata".equals(key)) {

			} else {
				throw new ParseException("Unknown FieldDefinition: " + key);
			}
		}
	}
}
