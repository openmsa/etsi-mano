package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents an artifact other than a software image which is contained in a VNF package.  It shall comply with provisions defined in Table 9.5.3.3-1. 
 **/
@ApiModel(description="This type represents an artifact other than a software image which is contained in a VNF package.  It shall comply with provisions defined in Table 9.5.3.3-1. ")
public class VnfPackagesVnfPkgInfoAdditionalArtifacts  {
  
  @ApiModelProperty(required = true, value = "This type represents stack of string values ")
 /**
   * This type represents stack of string values 
  **/
  private String artifactPath = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private VnfPackagesVnfPkgInfoChecksum checksum = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object metadata = null;
 /**
   * This type represents stack of string values 
   * @return artifactPath
  **/
  @JsonProperty("artifactPath")
  @NotNull
  public String getArtifactPath() {
    return artifactPath;
  }

  public void setArtifactPath(String artifactPath) {
    this.artifactPath = artifactPath;
  }

  public VnfPackagesVnfPkgInfoAdditionalArtifacts artifactPath(String artifactPath) {
    this.artifactPath = artifactPath;
    return this;
  }

 /**
   * Get checksum
   * @return checksum
  **/
  @JsonProperty("checksum")
  @NotNull
  public VnfPackagesVnfPkgInfoChecksum getChecksum() {
    return checksum;
  }

  public void setChecksum(VnfPackagesVnfPkgInfoChecksum checksum) {
    this.checksum = checksum;
  }

  public VnfPackagesVnfPkgInfoAdditionalArtifacts checksum(VnfPackagesVnfPkgInfoChecksum checksum) {
    this.checksum = checksum;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return metadata
  **/
  @JsonProperty("metadata")
  public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }

  public VnfPackagesVnfPkgInfoAdditionalArtifacts metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagesVnfPkgInfoAdditionalArtifacts {\n");
    
    sb.append("    artifactPath: ").append(toIndentedString(artifactPath)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

