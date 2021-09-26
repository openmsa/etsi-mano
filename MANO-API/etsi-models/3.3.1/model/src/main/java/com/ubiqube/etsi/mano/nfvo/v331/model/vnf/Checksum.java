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
package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the checksum of a VNF package or an artifact file. 
 */
@Schema(description = "This type represents the checksum of a VNF package or an artifact file. ")
@Validated


public class Checksum   {
  @JsonProperty("algorithm")
  private String algorithm = null;

  @JsonProperty("hash")
  private String hash = null;

  public Checksum algorithm(String algorithm) {
    this.algorithm = algorithm;
    return this;
  }

  /**
   * Name of the algorithm used to generate the checksum, as defined in ETSI GS NFV-SOL 004 [5]. For example, SHA-256, SHA-512. 
   * @return algorithm
   **/
  @Schema(required = true, description = "Name of the algorithm used to generate the checksum, as defined in ETSI GS NFV-SOL 004 [5]. For example, SHA-256, SHA-512. ")
      @NotNull

    public String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  public Checksum hash(String hash) {
    this.hash = hash;
    return this;
  }

  /**
   * The hexadecimal value of the checksum. 
   * @return hash
   **/
  @Schema(required = true, description = "The hexadecimal value of the checksum. ")
      @NotNull

    public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Checksum checksum = (Checksum) o;
    return Objects.equals(this.algorithm, checksum.algorithm) &&
        Objects.equals(this.hash, checksum.hash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(algorithm, hash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
