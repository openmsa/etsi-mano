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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an externally provided link port to be used to connect an external connection point to an external VL. NOTE: The value of \&quot;trunkResourceId\&quot; is scoped by the value of \&quot;vimConnectionId\&quot; in the \&quot;resourceHandle\&quot; attribute. 
 */
@Schema(description = "This type represents an externally provided link port to be used to connect an external connection point to an external VL. NOTE: The value of \"trunkResourceId\" is scoped by the value of \"vimConnectionId\" in the \"resourceHandle\" attribute. ")
@Validated


public class ExtLinkPortData   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceHandle")
  private ResourceHandle resourceHandle = null;

  @JsonProperty("trunkResourceId")
  private String trunkResourceId = null;

  public ExtLinkPortData id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ExtLinkPortData resourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

  /**
   * Get resourceHandle
   * @return resourceHandle
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getResourceHandle() {
    return resourceHandle;
  }

  public void setResourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
  }

  public ExtLinkPortData trunkResourceId(String trunkResourceId) {
    this.trunkResourceId = trunkResourceId;
    return this;
  }

  /**
   * Get trunkResourceId
   * @return trunkResourceId
   **/
  @Schema(description = "")
  
    public String getTrunkResourceId() {
    return trunkResourceId;
  }

  public void setTrunkResourceId(String trunkResourceId) {
    this.trunkResourceId = trunkResourceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtLinkPortData extLinkPortData = (ExtLinkPortData) o;
    return Objects.equals(this.id, extLinkPortData.id) &&
        Objects.equals(this.resourceHandle, extLinkPortData.resourceHandle) &&
        Objects.equals(this.trunkResourceId, extLinkPortData.trunkResourceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceHandle, trunkResourceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtLinkPortData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    trunkResourceId: ").append(toIndentedString(trunkResourceId)).append("\n");
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
