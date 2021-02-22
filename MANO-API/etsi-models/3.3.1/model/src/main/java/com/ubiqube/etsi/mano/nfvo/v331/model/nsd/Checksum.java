package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

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
