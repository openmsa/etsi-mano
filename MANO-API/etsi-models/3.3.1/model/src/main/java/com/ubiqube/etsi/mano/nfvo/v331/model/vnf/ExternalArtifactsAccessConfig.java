package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the access configuration information for downloading
 * external VNF package artifacts. The NFVO can obtain the external VNF package
 * artifact file through the information provided in this structure, together
 * with information provided in the manifest / VNFD. The data structure shall
 * comply with the provisions defined in Table 9.5.2.10-1. If the data structure
 * is part of a response body, security-sensitive attributes shall be excluded
 * as specified in Table 9.5.2.10-1.
 */
@ApiModel(description = "This type represents the access configuration information for downloading external VNF package artifacts. The NFVO can obtain the external VNF package artifact file through the information provided in this structure, together with information provided in the manifest / VNFD. The data structure shall comply with the provisions defined in Table 9.5.2.10-1. If the data structure is part of a response body, security-sensitive attributes shall be excluded as specified in Table 9.5.2.10-1. ")
@Validated
public class ExternalArtifactsAccessConfig {
	@JsonProperty("artifact")
	private ExternalArtifactsAccessConfigArtifact artifact = null;

	public ExternalArtifactsAccessConfig artifact(final ExternalArtifactsAccessConfigArtifact artifact) {
		this.artifact = artifact;
		return this;
	}

	/**
	 * Get artifact
	 *
	 * @return artifact
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public ExternalArtifactsAccessConfigArtifact getArtifact() {
		return artifact;
	}

	public void setArtifact(final ExternalArtifactsAccessConfigArtifact artifact) {
		this.artifact = artifact;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ExternalArtifactsAccessConfig externalArtifactsAccessConfig = (ExternalArtifactsAccessConfig) o;
		return Objects.equals(this.artifact, externalArtifactsAccessConfig.artifact);
	}

	@Override
	public int hashCode() {
		return Objects.hash(artifact);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ExternalArtifactsAccessConfig {\n");

		sb.append("    artifact: ").append(toIndentedString(artifact)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
