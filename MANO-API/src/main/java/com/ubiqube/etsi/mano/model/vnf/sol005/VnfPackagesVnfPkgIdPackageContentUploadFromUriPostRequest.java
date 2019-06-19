package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * The payload body contains the address information based on which the NFVO can obtain the content of the VNF package.          
 **/
@ApiModel(description="The payload body contains the address information based on which the NFVO can obtain the content of the VNF package.          ")
public class VnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private VnfPackagesvnfPkgIdpackageContentuploadFromUriUploadVnfPkgFromUriRequest uploadVnfPkgFromUriRequest = null;
 /**
   * Get uploadVnfPkgFromUriRequest
   * @return uploadVnfPkgFromUriRequest
  **/
  @JsonProperty("UploadVnfPkgFromUriRequest")
  @NotNull
  public VnfPackagesvnfPkgIdpackageContentuploadFromUriUploadVnfPkgFromUriRequest getUploadVnfPkgFromUriRequest() {
    return uploadVnfPkgFromUriRequest;
  }

  public void setUploadVnfPkgFromUriRequest(VnfPackagesvnfPkgIdpackageContentuploadFromUriUploadVnfPkgFromUriRequest uploadVnfPkgFromUriRequest) {
    this.uploadVnfPkgFromUriRequest = uploadVnfPkgFromUriRequest;
  }

  public VnfPackagesVnfPkgIdPackageContentUploadFromUriPostRequest uploadVnfPkgFromUriRequest(VnfPackagesvnfPkgIdpackageContentuploadFromUriUploadVnfPkgFromUriRequest uploadVnfPkgFromUriRequest) {
    this.uploadVnfPkgFromUriRequest = uploadVnfPkgFromUriRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body2 {\n");
    
    sb.append("    uploadVnfPkgFromUriRequest: ").append(toIndentedString(uploadVnfPkgFromUriRequest)).append("\n");
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

