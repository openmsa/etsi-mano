package com.ubiqube.parser.tosca;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.ImportDeserializer;

@JsonDeserialize(using = ImportDeserializer.class)
public class Imports {

	private final Map<String, Import> imports;

	public Imports(final Map<String, Import> _imports) {
		imports = _imports;
	}

	public Set<Entry<String, Import>> entrySet() {
		return imports.entrySet();
	}

	public void putAll(final Imports imports2) {
		imports.putAll(imports2.imports);
	}
}
