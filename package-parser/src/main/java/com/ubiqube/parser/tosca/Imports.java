package com.ubiqube.parser.tosca;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Imports {

	private final Map<String, Import> imports;

	public Imports(final Map<String, Import> _imports) {
		imports = _imports;
	}

	public Set<Entry<String, Import>> entrySet() {
		return imports.entrySet();
	}
}
