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

package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.NsLinkPortInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.ResourceHandle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type specifies the information about an NS VL instance.  It shall comply with the provisions defined in Table 6.5.3.53-1 
 */
@ApiModel(description = "This type specifies the information about an NS VL instance.  It shall comply with the provisions defined in Table 6.5.3.53-1 ")
@Validated
public class NsVirtualLinkInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("nsVirtualLinkDescId")
  private String nsVirtualLinkDescId = null;

  @JsonProperty("nsVirtualLinkProfileId")
  private String nsVirtualLinkProfileId = null;

  @JsonProperty("resourceHandle")
  @Valid
  private List<ResourceHandle> resourceHandle = null;

  @JsonProperty("linkPort")
  @Valid
  private List<NsLinkPortInfo> linkPort = null;

  public NsVirtualLinkInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NsVirtualLinkInfo nsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
    return this;
  }

  /**
   * Get nsVirtualLinkDescId
   * @return nsVirtualLinkDescId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getNsVirtualLinkDescId() {
    return nsVirtualLinkDescId;
  }

  public void setNsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
  }

  public NsVirtualLinkInfo nsVirtualLinkProfileId(String nsVirtualLinkProfileId) {
    this.nsVirtualLinkProfileId = nsVirtualLinkProfileId;
    return this;
  }

  /**
   * Get nsVirtualLinkProfileId
   * @return nsVirtualLinkProfileId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getNsVirtualLinkProfileId() {
    return nsVirtualLinkProfileId;
  }

  public void setNsVirtualLinkProfileId(String nsVirtualLinkProfileId) {
    this.nsVirtualLinkProfileId = nsVirtualLinkProfileId;
  }

  public NsVirtualLinkInfo resourceHandle(List<ResourceHandle> resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

  public NsVirtualLinkInfo addResourceHandleItem(ResourceHandle resourceHandleItem) {
    if (this.resourceHandle == null) {
      this.resourceHandle = new ArrayList<>();
    }
    this.resourceHandle.add(resourceHandleItem);
    return this;
  }

  /**
   * Identifier(s) of the virtualised network resource(s) realizing the VL instance. See note. 
   * @return resourceHandle
  **/
  @ApiModelProperty(value = "Identifier(s) of the virtualised network resource(s) realizing the VL instance. See note. ")
      @Valid
    public List<ResourceHandle> getResourceHandle() {
    return resourceHandle;
  }

  public void setResourceHandle(List<ResourceHandle> resourceHandle) {
    this.resourceHandle = resourceHandle;
  }

  public NsVirtualLinkInfo linkPort(List<NsLinkPortInfo> linkPort) {
    this.linkPort = linkPort;
    return this;
  }

  public NsVirtualLinkInfo addLinkPortItem(NsLinkPortInfo linkPortItem) {
    if (this.linkPort == null) {
      this.linkPort = new ArrayList<>();
    }
    this.linkPort.add(linkPortItem);
    return this;
  }

  /**
   * Link ports of the VL instance. Cardinality of zero indicates that no port has yet been created for the VL instance. 
   * @return linkPort
  **/
  @ApiModelProperty(value = "Link ports of the VL instance. Cardinality of zero indicates that no port has yet been created for the VL instance. ")
      @Valid
    public List<NsLinkPortInfo> getLinkPort() {
    return linkPort;
  }

  public void setLinkPort(List<NsLinkPortInfo> linkPort) {
    this.linkPort = linkPort;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsVirtualLinkInfo nsVirtualLinkInfo = (NsVirtualLinkInfo) o;
    return Objects.equals(this.id, nsVirtualLinkInfo.id) &&
        Objects.equals(this.nsVirtualLinkDescId, nsVirtualLinkInfo.nsVirtualLinkDescId) &&
        Objects.equals(this.nsVirtualLinkProfileId, nsVirtualLinkInfo.nsVirtualLinkProfileId) &&
        Objects.equals(this.resourceHandle, nsVirtualLinkInfo.resourceHandle) &&
        Objects.equals(this.linkPort, nsVirtualLinkInfo.linkPort);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nsVirtualLinkDescId, nsVirtualLinkProfileId, resourceHandle, linkPort);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsVirtualLinkInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nsVirtualLinkDescId: ").append(toIndentedString(nsVirtualLinkDescId)).append("\n");
    sb.append("    nsVirtualLinkProfileId: ").append(toIndentedString(nsVirtualLinkProfileId)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    linkPort: ").append(toIndentedString(linkPort)).append("\n");
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
