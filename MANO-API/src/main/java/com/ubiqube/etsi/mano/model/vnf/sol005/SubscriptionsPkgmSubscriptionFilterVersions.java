package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SubscriptionsPkgmSubscriptionFilterVersions  {
  
  @ApiModelProperty(required = true, value = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
 /**
   * Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
  **/
  private String vnfSoftwareVersion = null;

  @ApiModelProperty(value = "If present, match VNF packages that contain VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. ")
 /**
   * If present, match VNF packages that contain VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. 
  **/
  private List<String> vnfdVersions = null;
 /**
   * Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
   * @return vnfSoftwareVersion
  **/
  @JsonProperty("vnfSoftwareVersion")
  @NotNull
  public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public SubscriptionsPkgmSubscriptionFilterVersions vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

 /**
   * If present, match VNF packages that contain VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. 
   * @return vnfdVersions
  **/
  @JsonProperty("vnfdVersions")
  public List<String> getVnfdVersions() {
    return vnfdVersions;
  }

  public void setVnfdVersions(List<String> vnfdVersions) {
    this.vnfdVersions = vnfdVersions;
  }

  public SubscriptionsPkgmSubscriptionFilterVersions vnfdVersions(List<String> vnfdVersions) {
    this.vnfdVersions = vnfdVersions;
    return this;
  }

  public SubscriptionsPkgmSubscriptionFilterVersions addVnfdVersionsItem(String vnfdVersionsItem) {
    this.vnfdVersions.add(vnfdVersionsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPkgmSubscriptionFilterVersions {\n");
    
    sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
    sb.append("    vnfdVersions: ").append(toIndentedString(vnfdVersions)).append("\n");
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

