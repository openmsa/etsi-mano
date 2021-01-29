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
package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GrantInfo
 */
@Validated
public class GrantInfo   {
  @JsonProperty("resourceDefinitionId")
  private String resourceDefinitionId = null;

  @JsonProperty("resourceGroupId")
  private String resourceGroupId = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  public GrantInfo resourceDefinitionId(String resourceDefinitionId) {
    this.resourceDefinitionId = resourceDefinitionId;
    return this;
  }

  /**
   * Identifier of the related \"ResourceDefinition\" structure from the related \"GrantRequest\" structure.
   * @return resourceDefinitionId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the related \"ResourceDefinition\" structure from the related \"GrantRequest\" structure.")
      @NotNull

    public String getResourceDefinitionId() {
    return resourceDefinitionId;
  }

  public void setResourceDefinitionId(String resourceDefinitionId) {
    this.resourceDefinitionId = resourceDefinitionId;
  }

  public GrantInfo resourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
    return this;
  }

  /**
   * Identifier of the \"infrastructure resource group\", logical grouping of virtual resources assigned to a tenant within an Infrastructure Domain, to be provided when allocating the resource.If the VIM connection referenced by \"vimConnectionId\" applies to multiple infrastructure resource groups, this attribute shall be present for new resources.If the VIM connection referenced by \"vimConnectionId\" applies to a single infrastructure resource group, this attribute may be present for new resources. This attribute shall be absent for resources that have already been allocated.
   * @return resourceGroupId
  **/
  @ApiModelProperty(value = "Identifier of the \"infrastructure resource group\", logical grouping of virtual resources assigned to a tenant within an Infrastructure Domain, to be provided when allocating the resource.If the VIM connection referenced by \"vimConnectionId\" applies to multiple infrastructure resource groups, this attribute shall be present for new resources.If the VIM connection referenced by \"vimConnectionId\" applies to a single infrastructure resource group, this attribute may be present for new resources. This attribute shall be absent for resources that have already been allocated.")
  
    public String getResourceGroupId() {
    return resourceGroupId;
  }

  public void setResourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
  }

  public GrantInfo vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Identifier of the VIM connection to be used to manage this resource. Shall be present for new resources, and shall be absent for resources that have already been allocated.
   * @return vimConnectionId
  **/
  @ApiModelProperty(value = "Identifier of the VIM connection to be used to manage this resource. Shall be present for new resources, and shall be absent for resources that have already been allocated.")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public GrantInfo zoneId(String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  /**
   * Reference to the identifier of the \"ZoneInfo\" structure in the \"Grant\" structure defining the resource zone into which this resource is to be placed. Shall be present for new resources if the zones concept is applicable to them (typically, Compute resources), and shall be absent for resources that have already been allocated.
   * @return zoneId
  **/
  @ApiModelProperty(value = "Reference to the identifier of the \"ZoneInfo\" structure in the \"Grant\" structure defining the resource zone into which this resource is to be placed. Shall be present for new resources if the zones concept is applicable to them (typically, Compute resources), and shall be absent for resources that have already been allocated.")
  
    public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrantInfo grantInfo = (GrantInfo) o;
    return Objects.equals(this.resourceDefinitionId, grantInfo.resourceDefinitionId) &&
        Objects.equals(this.resourceGroupId, grantInfo.resourceGroupId) &&
        Objects.equals(this.vimConnectionId, grantInfo.vimConnectionId) &&
        Objects.equals(this.zoneId, grantInfo.zoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceDefinitionId, resourceGroupId, vimConnectionId, zoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantInfo {\n");
    
    sb.append("    resourceDefinitionId: ").append(toIndentedString(resourceDefinitionId)).append("\n");
    sb.append("    resourceGroupId: ").append(toIndentedString(resourceGroupId)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
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
