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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v271.model.nslcm.ExtLinkPortData;
import com.ubiqube.etsi.mano.nfvo.v271.model.nslcm.VnfExtCpData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an external VL. It shall comply with the provisions defined in Table 6.5.3.26-1. 
 */
@ApiModel(description = "This type represents an external VL. It shall comply with the provisions defined in Table 6.5.3.26-1. ")
@Validated
public class ExtVirtualLinkData   {
  @JsonProperty("extVirtualLinkId")
  private String extVirtualLinkId = null;

  @JsonProperty("vimId")
  private String vimId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("extCps")
  @Valid
  private List<VnfExtCpData> extCps = new ArrayList<>();

  @JsonProperty("extLinkPorts")
  @Valid
  private List<ExtLinkPortData> extLinkPorts = null;

  public ExtVirtualLinkData extVirtualLinkId(String extVirtualLinkId) {
    this.extVirtualLinkId = extVirtualLinkId;
    return this;
  }

  /**
   * Get extVirtualLinkId
   * @return extVirtualLinkId
  **/
  @ApiModelProperty(value = "")
  
    public String getExtVirtualLinkId() {
    return extVirtualLinkId;
  }

  public void setExtVirtualLinkId(String extVirtualLinkId) {
    this.extVirtualLinkId = extVirtualLinkId;
  }

  public ExtVirtualLinkData vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * Get vimId
   * @return vimId
  **/
  @ApiModelProperty(value = "")
  
    public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public ExtVirtualLinkData resourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
    return this;
  }

  /**
   * Get resourceProviderId
   * @return resourceProviderId
  **/
  @ApiModelProperty(value = "")
  
    public String getResourceProviderId() {
    return resourceProviderId;
  }

  public void setResourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
  }

  public ExtVirtualLinkData resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * Get resourceId
   * @return resourceId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public ExtVirtualLinkData extCps(List<VnfExtCpData> extCps) {
    this.extCps = extCps;
    return this;
  }

  public ExtVirtualLinkData addExtCpsItem(VnfExtCpData extCpsItem) {
    this.extCps.add(extCpsItem);
    return this;
  }

  /**
   * External CPs of the VNF to be connected to this external VL. 
   * @return extCps
  **/
  @ApiModelProperty(required = true, value = "External CPs of the VNF to be connected to this external VL. ")
      @NotNull
    @Valid
    public List<VnfExtCpData> getExtCps() {
    return extCps;
  }

  public void setExtCps(List<VnfExtCpData> extCps) {
    this.extCps = extCps;
  }

  public ExtVirtualLinkData extLinkPorts(List<ExtLinkPortData> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
    return this;
  }

  public ExtVirtualLinkData addExtLinkPortsItem(ExtLinkPortData extLinkPortsItem) {
    if (this.extLinkPorts == null) {
      this.extLinkPorts = new ArrayList<>();
    }
    this.extLinkPorts.add(extLinkPortsItem);
    return this;
  }

  /**
   * Externally provided link ports to be used to connect external connection points to this external VL. 
   * @return extLinkPorts
  **/
  @ApiModelProperty(value = "Externally provided link ports to be used to connect external connection points to this external VL. ")
      @Valid
    public List<ExtLinkPortData> getExtLinkPorts() {
    return extLinkPorts;
  }

  public void setExtLinkPorts(List<ExtLinkPortData> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtVirtualLinkData extVirtualLinkData = (ExtVirtualLinkData) o;
    return Objects.equals(this.extVirtualLinkId, extVirtualLinkData.extVirtualLinkId) &&
        Objects.equals(this.vimId, extVirtualLinkData.vimId) &&
        Objects.equals(this.resourceProviderId, extVirtualLinkData.resourceProviderId) &&
        Objects.equals(this.resourceId, extVirtualLinkData.resourceId) &&
        Objects.equals(this.extCps, extVirtualLinkData.extCps) &&
        Objects.equals(this.extLinkPorts, extVirtualLinkData.extLinkPorts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extVirtualLinkId, vimId, resourceProviderId, resourceId, extCps, extLinkPorts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtVirtualLinkData {\n");
    
    sb.append("    extVirtualLinkId: ").append(toIndentedString(extVirtualLinkId)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    extCps: ").append(toIndentedString(extCps)).append("\n");
    sb.append("    extLinkPorts: ").append(toIndentedString(extLinkPorts)).append("\n");
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
