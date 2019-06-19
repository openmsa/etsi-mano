package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Parameters for VNF package information modifications. 
 **/
@ApiModel(description="Parameters for VNF package information modifications. ")
public class VnfPackagesVnfPkgIdPatchQuery {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private VnfPackagesvnfPkgIdVnfPkgInfoModifications vnfPkgInfoModifications = null;
 /**
   * Get vnfPkgInfoModifications
   * @return vnfPkgInfoModifications
  **/
  @JsonProperty("VnfPkgInfoModifications")
  @NotNull
  public VnfPackagesvnfPkgIdVnfPkgInfoModifications getVnfPkgInfoModifications() {
    return vnfPkgInfoModifications;
  }

  public void setVnfPkgInfoModifications(VnfPackagesvnfPkgIdVnfPkgInfoModifications vnfPkgInfoModifications) {
    this.vnfPkgInfoModifications = vnfPkgInfoModifications;
  }

  public VnfPackagesVnfPkgIdPatchQuery vnfPkgInfoModifications(VnfPackagesvnfPkgIdVnfPkgInfoModifications vnfPkgInfoModifications) {
    this.vnfPkgInfoModifications = vnfPkgInfoModifications;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class vnfPackagesVnfPkgIdPatchQuery {\n");
    
    sb.append("    vnfPkgInfoModifications: ").append(toIndentedString(vnfPkgInfoModifications)).append("\n");
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

