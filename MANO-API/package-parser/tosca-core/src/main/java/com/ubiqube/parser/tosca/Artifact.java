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
package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Artifact {
	private String file;
	private String type;
	private String repository;
	private String description;
	@JsonProperty("deploy_path")
	private String deployPath;
	@JsonProperty("artifact_version")
	private String artifactVersion;
	private String checksum;
	@JsonProperty("checksum_algorithm")
	private String checksumAlgorithm;
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

	public String getDeployPath() {
		return deployPath;
	}

	public void setDeployPath(final String deployPath) {
		this.deployPath = deployPath;
	}

	public String getArtifactVersion() {
		return artifactVersion;
	}

	public void setArtifactVersion(final String artifactVersion) {
		this.artifactVersion = artifactVersion;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(final String checksum) {
		this.checksum = checksum;
	}

	public String getChecksumAlgorithm() {
		return checksumAlgorithm;
	}

	public void setChecksumAlgorithm(final String checksumAlgorithm) {
		this.checksumAlgorithm = checksumAlgorithm;
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
