/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.common.v261.model.vnf;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the checksum of a VNF package or an artifact file.
 */
@ApiModel(description = "This type represents the checksum of a VNF package or an artifact file. ")
@Validated
public class Checksum {
	@JsonProperty("algorithm")
	private String algorithm = null;

	@JsonProperty("hash")
	private String hash = null;

	public Checksum algorithm(final String algorithm) {
		this.algorithm = algorithm;
		return this;
	}

	/**
	 * Name of the algorithm used to generate the checksum, as defined in ETSI GS
	 * NFV-SOL 004 [5]. For example, SHA-256, SHA-512.
	 *
	 * @return algorithm
	 **/
	@ApiModelProperty(required = true, value = "Name of the algorithm used to generate the checksum, as defined in ETSI GS NFV-SOL 004 [5]. For example, SHA-256, SHA-512. ")
	@NotNull

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(final String algorithm) {
		this.algorithm = algorithm;
	}

	public Checksum hash(final String hash) {
		this.hash = hash;
		return this;
	}

	/**
	 * The hexadecimal value of the checksum.
	 *
	 * @return hash
	 **/
	@ApiModelProperty(required = true, value = "The hexadecimal value of the checksum. ")
	@NotNull

	public String getHash() {
		return hash;
	}

	public void setHash(final String hash) {
		this.hash = hash;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final Checksum checksum = (Checksum) o;
		return Objects.equals(this.algorithm, checksum.algorithm) &&
				Objects.equals(this.hash, checksum.hash);
	}

	@Override
	public int hashCode() {
		return Objects.hash(algorithm, hash);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class Checksum {\n");

		sb.append("    algorithm: ").append(toIndentedString(algorithm)).append("\n");
		sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
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
