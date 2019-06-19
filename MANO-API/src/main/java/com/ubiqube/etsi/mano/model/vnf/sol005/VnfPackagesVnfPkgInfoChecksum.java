package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * This type represents the checksum of a VNF package or an artifact file.   
 **/
@ApiModel(description="This type represents the checksum of a VNF package or an artifact file.   ")
public class VnfPackagesVnfPkgInfoChecksum  {
  
  @ApiModelProperty(required = true, value = "Name of the algorithm used to generate the checksum, as defined in ETSI GS NFV-SOL 004 [5]. For example, SHA-256, SHA-512. ")
 /**
   * Name of the algorithm used to generate the checksum, as defined in ETSI GS NFV-SOL 004 [5]. For example, SHA-256, SHA-512. 
  **/
  private String algorithm = null;

  @ApiModelProperty(required = true, value = "The hexadecimal value of the checksum. ")
 /**
   * The hexadecimal value of the checksum. 
  **/
  private String hash = null;
 /**
   * Name of the algorithm used to generate the checksum, as defined in ETSI GS NFV-SOL 004 [5]. For example, SHA-256, SHA-512. 
   * @return algorithm
  **/
  @JsonProperty("algorithm")
  @NotNull
  public String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  public VnfPackagesVnfPkgInfoChecksum algorithm(String algorithm) {
    this.algorithm = algorithm;
    return this;
  }

 /**
   * The hexadecimal value of the checksum. 
   * @return hash
  **/
  @JsonProperty("hash")
  @NotNull
  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public VnfPackagesVnfPkgInfoChecksum hash(String hash) {
    this.hash = hash;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagesVnfPkgInfoChecksum {\n");
    
    sb.append("    algorithm: ").append(toIndentedString(algorithm)).append("\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

