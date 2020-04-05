package com.ubiqube.parser.tosca;

public class Artifact {
	private String file;
	private String type;
	private String repository;
	private String description;
	private String deploy_path;
	private String artifact_version;
	private String checksum;
	private String checksum_algorithm;
	private Object properties;

	public String getFile() {
		return file;
	}

	public void setFile(final String file) {
		this.file = file;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDeploy_path() {
		return deploy_path;
	}

	public void setDeploy_path(final String deploy_path) {
		this.deploy_path = deploy_path;
	}

	public String getArtifact_version() {
		return artifact_version;
	}

	public void setArtifact_version(final String artifact_version) {
		this.artifact_version = artifact_version;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(final String checksum) {
		this.checksum = checksum;
	}

	public String getChecksum_algorithm() {
		return checksum_algorithm;
	}

	public void setChecksum_algorithm(final String checksum_algorithm) {
		this.checksum_algorithm = checksum_algorithm;
	}

	public Object getProperties() {
		return properties;
	}

	public void setProperties(final Object properties) {
		this.properties = properties;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(final String repository) {
		this.repository = repository;
	}

}
