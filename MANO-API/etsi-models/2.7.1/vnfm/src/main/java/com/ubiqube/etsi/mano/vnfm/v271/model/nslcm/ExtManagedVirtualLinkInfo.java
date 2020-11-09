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
package com.ubiqube.etsi.mano.vnfm.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.vnfm.v271.model.nslcm.ResourceHandle;
import com.ubiqube.etsi.mano.vnfm.v271.model.nslcm.VnfLinkPortInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExtManagedVirtualLinkInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T10:14:43.989+01:00")

public class ExtManagedVirtualLinkInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfVirtualLinkDescId")
  private String vnfVirtualLinkDescId = null;

  @JsonProperty("networkResource")
  private ResourceHandle networkResource = null;

  @JsonProperty("vnfLinkPorts")
  @Valid
  private List<VnfLinkPortInfo> vnfLinkPorts = null;

  public ExtManagedVirtualLinkInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the externally-managed internal VL and the related externally-managed VL information instance. The identifier is assigned by the NFV-MANO entity that manages this VL instance. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of the externally-managed internal VL and the related externally-managed VL information instance. The identifier is assigned by the NFV-MANO entity that manages this VL instance. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ExtManagedVirtualLinkInfo vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
    return this;
  }

  /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return vnfVirtualLinkDescId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
  @NotNull


  public String getVnfVirtualLinkDescId() {
    return vnfVirtualLinkDescId;
  }

  public void setVnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
  }

  public ExtManagedVirtualLinkInfo networkResource(ResourceHandle networkResource) {
    this.networkResource = networkResource;
    return this;
  }

  /**
   * Reference to the VirtualNetwork resource. 
   * @return networkResource
  **/
  @ApiModelProperty(required = true, value = "Reference to the VirtualNetwork resource. ")
  @NotNull

  @Valid

  public ResourceHandle getNetworkResource() {
    return networkResource;
  }

  public void setNetworkResource(ResourceHandle networkResource) {
    this.networkResource = networkResource;
  }

  public ExtManagedVirtualLinkInfo vnfLinkPorts(List<VnfLinkPortInfo> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
    return this;
  }

  public ExtManagedVirtualLinkInfo addVnfLinkPortsItem(VnfLinkPortInfo vnfLinkPortsItem) {
    if (this.vnfLinkPorts == null) {
      this.vnfLinkPorts = new ArrayList<>();
    }
    this.vnfLinkPorts.add(vnfLinkPortsItem);
    return this;
  }

  /**
   * Link ports of this VL. 
   * @return vnfLinkPorts
  **/
  @ApiModelProperty(value = "Link ports of this VL. ")

  @Valid

  public List<VnfLinkPortInfo> getVnfLinkPorts() {
    return vnfLinkPorts;
  }

  public void setVnfLinkPorts(List<VnfLinkPortInfo> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtManagedVirtualLinkInfo extManagedVirtualLinkInfo = (ExtManagedVirtualLinkInfo) o;
    return Objects.equals(this.id, extManagedVirtualLinkInfo.id) &&
        Objects.equals(this.vnfVirtualLinkDescId, extManagedVirtualLinkInfo.vnfVirtualLinkDescId) &&
        Objects.equals(this.networkResource, extManagedVirtualLinkInfo.networkResource) &&
        Objects.equals(this.vnfLinkPorts, extManagedVirtualLinkInfo.vnfLinkPorts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfVirtualLinkDescId, networkResource, vnfLinkPorts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtManagedVirtualLinkInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
    sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
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

