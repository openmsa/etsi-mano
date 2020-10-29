/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.vnfm.v261.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VnfInstanceSubscriptionFilterVersions
 */
@Validated


public class VnfInstanceSubscriptionFilterVersions   {
  @JsonProperty("vnfSoftwareVersion")
  private String vnfSoftwareVersion = null;

  @JsonProperty("vnfdVersions")
  @Valid
  private List<String> vnfdVersions = null;

  public VnfInstanceSubscriptionFilterVersions vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

  /**
   * Software version to match. 
   * @return vnfSoftwareVersion
  **/
  @ApiModelProperty(required = true, value = "Software version to match. ")
  @NotNull


  public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public VnfInstanceSubscriptionFilterVersions vnfdVersions(List<String> vnfdVersions) {
    this.vnfdVersions = vnfdVersions;
    return this;
  }

  public VnfInstanceSubscriptionFilterVersions addVnfdVersionsItem(String vnfdVersionsItem) {
    if (this.vnfdVersions == null) {
      this.vnfdVersions = new ArrayList<>();
    }
    this.vnfdVersions.add(vnfdVersionsItem);
    return this;
  }

  /**
   * If present, match VNF instances that belong to VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. 
   * @return vnfdVersions
  **/
  @ApiModelProperty(value = "If present, match VNF instances that belong to VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. ")


  public List<String> getVnfdVersions() {
    return vnfdVersions;
  }

  public void setVnfdVersions(List<String> vnfdVersions) {
    this.vnfdVersions = vnfdVersions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInstanceSubscriptionFilterVersions vnfInstanceSubscriptionFilterVersions = (VnfInstanceSubscriptionFilterVersions) o;
    return Objects.equals(this.vnfSoftwareVersion, vnfInstanceSubscriptionFilterVersions.vnfSoftwareVersion) &&
        Objects.equals(this.vnfdVersions, vnfInstanceSubscriptionFilterVersions.vnfdVersions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfSoftwareVersion, vnfdVersions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInstanceSubscriptionFilterVersions {\n");
    
    sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
    sb.append("    vnfdVersions: ").append(toIndentedString(vnfdVersions)).append("\n");
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

