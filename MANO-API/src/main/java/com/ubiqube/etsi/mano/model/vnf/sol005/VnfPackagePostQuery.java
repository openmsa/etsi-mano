package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * IndividualVNF package resource creation parameters, as defined in clause 9.5.2.2 
 **/
@ApiModel(description="IndividualVNF package resource creation parameters, as defined in clause 9.5.2.2 ")
public class VnfPackagePostQuery {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private CreateVnfPkgInfoRequest createVnfPkgInfoRequest = null;
 /**
   * Get createVnfPkgInfoRequest
   * @return createVnfPkgInfoRequest
  **/
  @JsonProperty("CreateVnfPkgInfoRequest")
  @NotNull
  public CreateVnfPkgInfoRequest getCreateVnfPkgInfoRequest() {
    return createVnfPkgInfoRequest;
  }

  public void setCreateVnfPkgInfoRequest(CreateVnfPkgInfoRequest createVnfPkgInfoRequest) {
    this.createVnfPkgInfoRequest = createVnfPkgInfoRequest;
  }

  public VnfPackagePostQuery createVnfPkgInfoRequest(CreateVnfPkgInfoRequest createVnfPkgInfoRequest) {
    this.createVnfPkgInfoRequest = createVnfPkgInfoRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagePostQuery {\n");
    
    sb.append("    createVnfPkgInfoRequest: ").append(toIndentedString(createVnfPkgInfoRequest)).append("\n");
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

