package com.ubiqube.parser.tosca.api;

import java.util.Map;

public class ArtefactInformations {
	private String checksum;
	private String path;
	private Map<String, String> metadata;
	private String algorithm;

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(final String checksum) {
		this.checksum = checksum;
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(final String algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public String toString() {
		return "ArtefactInformations [checksum=" + checksum + ", path=" + path + ", metadata=" + metadata + ", algorithm=" + algorithm + "]";
	}

}
