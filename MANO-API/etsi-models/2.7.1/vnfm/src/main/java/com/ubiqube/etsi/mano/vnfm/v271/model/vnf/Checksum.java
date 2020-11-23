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
package com.ubiqube.etsi.mano.vnfm.v271.model.vnf;

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
@javax.annotation.processing.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-24T10:38:36.740+02:00")

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
	 * NFV-SOL 004. For example, SHA-256, SHA-512.
	 *
	 * @return algorithm
	 **/
	@ApiModelProperty(required = true, value = "Name of the algorithm used to generate the checksum, as defined in ETSI GS NFV-SOL 004. For example, SHA-256, SHA-512. ")
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
