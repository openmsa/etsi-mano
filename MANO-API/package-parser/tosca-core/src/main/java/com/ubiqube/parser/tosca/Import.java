package com.ubiqube.parser.tosca;

public class Import {
	private String url;
	private String name;

	public Import(final String _name, final String _url) {
		name = _name;
		url = _url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
