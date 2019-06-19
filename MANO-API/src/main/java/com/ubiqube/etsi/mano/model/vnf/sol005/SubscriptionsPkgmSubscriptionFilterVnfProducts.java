package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SubscriptionsPkgmSubscriptionFilterVnfProducts  {
  
  @ApiModelProperty(required = true, value = "Name of the VNF product to match. ")
 /**
   * Name of the VNF product to match. 
  **/
  private String vnfProductName = null;

  @ApiModelProperty(value = "If present, match VNF packages that contain VNF products with certain versions and a certain product name, from one particular provider. ")
  @Valid
 /**
   * If present, match VNF packages that contain VNF products with certain versions and a certain product name, from one particular provider. 
  **/
  private List<SubscriptionsPkgmSubscriptionFilterVersions> versions = null;
 /**
   * Name of the VNF product to match. 
   * @return vnfProductName
  **/
  @JsonProperty("vnfProductName")
  @NotNull
  public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public SubscriptionsPkgmSubscriptionFilterVnfProducts vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

 /**
   * If present, match VNF packages that contain VNF products with certain versions and a certain product name, from one particular provider. 
   * @return versions
  **/
  @JsonProperty("versions")
  public List<SubscriptionsPkgmSubscriptionFilterVersions> getVersions() {
    return versions;
  }

  public void setVersions(List<SubscriptionsPkgmSubscriptionFilterVersions> versions) {
    this.versions = versions;
  }

  public SubscriptionsPkgmSubscriptionFilterVnfProducts versions(List<SubscriptionsPkgmSubscriptionFilterVersions> versions) {
    this.versions = versions;
    return this;
  }

  public SubscriptionsPkgmSubscriptionFilterVnfProducts addVersionsItem(SubscriptionsPkgmSubscriptionFilterVersions versionsItem) {
    this.versions.add(versionsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPkgmSubscriptionFilterVnfProducts {\n");
    
    sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
    sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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

