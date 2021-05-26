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
package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type contains information about a Compute, storage or network resource whose addition/update/deletion was granted. 
 */
@Schema(description = "This type contains information about a Compute, storage or network resource whose addition/update/deletion was granted. ")
@Validated


public class GrantInfo   {
  @JsonProperty("resourceDefinitionId")
  private String resourceDefinitionId = null;

  @JsonProperty("reservationId")
  private String reservationId = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  @JsonProperty("resourceGroupId")
  private String resourceGroupId = null;

  public GrantInfo resourceDefinitionId(String resourceDefinitionId) {
    this.resourceDefinitionId = resourceDefinitionId;
    return this;
  }

  /**
   * Get resourceDefinitionId
   * @return resourceDefinitionId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getResourceDefinitionId() {
    return resourceDefinitionId;
  }

  public void setResourceDefinitionId(String resourceDefinitionId) {
    this.resourceDefinitionId = resourceDefinitionId;
  }

  public GrantInfo reservationId(String reservationId) {
    this.reservationId = reservationId;
    return this;
  }

  /**
   * Get reservationId
   * @return reservationId
   **/
  @Schema(description = "")
  
    public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public GrantInfo vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Get vimConnectionId
   * @return vimConnectionId
   **/
  @Schema(description = "")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public GrantInfo resourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
    return this;
  }

  /**
   * Get resourceProviderId
   * @return resourceProviderId
   **/
  @Schema(description = "")
  
    public String getResourceProviderId() {
    return resourceProviderId;
  }

  public void setResourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
  }

  public GrantInfo zoneId(String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  /**
   * Get zoneId
   * @return zoneId
   **/
  @Schema(description = "")
  
    public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }

  public GrantInfo resourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
    return this;
  }

  /**
   * Get resourceGroupId
   * @return resourceGroupId
   **/
  @Schema(description = "")
  
    public String getResourceGroupId() {
    return resourceGroupId;
  }

  public void setResourceGroupId(String resourceGroupId) {
    this.resourceGroupId = resourceGroupId;
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
        Objects.equals(this.reservationId, grantInfo.reservationId) &&
        Objects.equals(this.vimConnectionId, grantInfo.vimConnectionId) &&
        Objects.equals(this.resourceProviderId, grantInfo.resourceProviderId) &&
        Objects.equals(this.zoneId, grantInfo.zoneId) &&
        Objects.equals(this.resourceGroupId, grantInfo.resourceGroupId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceDefinitionId, reservationId, vimConnectionId, resourceProviderId, zoneId, resourceGroupId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantInfo {\n");
    
    sb.append("    resourceDefinitionId: ").append(toIndentedString(resourceDefinitionId)).append("\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
    sb.append("    resourceGroupId: ").append(toIndentedString(resourceGroupId)).append("\n");
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
