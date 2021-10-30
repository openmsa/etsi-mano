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
package com.ubiqube.etsi.mano.vnfm.v351.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * If the VIM requires the use of virtual compute resource flavours during compute resource instantiation, it is assumed that such flavours are selected or created by the NFVO based on the information in the virtual compute descriptor defined in the VNFD. This type defines the mapping between a virtual compute descriptor in the VNFD and the corresponding compute resource flavour managed by the NFVO in the VIM. 
 */
@Schema(description = "If the VIM requires the use of virtual compute resource flavours during compute resource instantiation, it is assumed that such flavours are selected or created by the NFVO based on the information in the virtual compute descriptor defined in the VNFD. This type defines the mapping between a virtual compute descriptor in the VNFD and the corresponding compute resource flavour managed by the NFVO in the VIM. ")
@Validated


public class VimComputeResourceFlavour   {
  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("vnfdVirtualComputeDescId")
  private String vnfdVirtualComputeDescId = null;

  @JsonProperty("vimFlavourId")
  private String vimFlavourId = null;

  public VimComputeResourceFlavour vimConnectionId(String vimConnectionId) {
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

  public VimComputeResourceFlavour resourceProviderId(String resourceProviderId) {
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

  public VimComputeResourceFlavour vnfdVirtualComputeDescId(String vnfdVirtualComputeDescId) {
    this.vnfdVirtualComputeDescId = vnfdVirtualComputeDescId;
    return this;
  }

  /**
   * Get vnfdVirtualComputeDescId
   * @return vnfdVirtualComputeDescId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdVirtualComputeDescId() {
    return vnfdVirtualComputeDescId;
  }

  public void setVnfdVirtualComputeDescId(String vnfdVirtualComputeDescId) {
    this.vnfdVirtualComputeDescId = vnfdVirtualComputeDescId;
  }

  public VimComputeResourceFlavour vimFlavourId(String vimFlavourId) {
    this.vimFlavourId = vimFlavourId;
    return this;
  }

  /**
   * Get vimFlavourId
   * @return vimFlavourId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVimFlavourId() {
    return vimFlavourId;
  }

  public void setVimFlavourId(String vimFlavourId) {
    this.vimFlavourId = vimFlavourId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VimComputeResourceFlavour vimComputeResourceFlavour = (VimComputeResourceFlavour) o;
    return Objects.equals(this.vimConnectionId, vimComputeResourceFlavour.vimConnectionId) &&
        Objects.equals(this.resourceProviderId, vimComputeResourceFlavour.resourceProviderId) &&
        Objects.equals(this.vnfdVirtualComputeDescId, vimComputeResourceFlavour.vnfdVirtualComputeDescId) &&
        Objects.equals(this.vimFlavourId, vimComputeResourceFlavour.vimFlavourId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vimConnectionId, resourceProviderId, vnfdVirtualComputeDescId, vimFlavourId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimComputeResourceFlavour {\n");
    
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    vnfdVirtualComputeDescId: ").append(toIndentedString(vnfdVirtualComputeDescId)).append("\n");
    sb.append("    vimFlavourId: ").append(toIndentedString(vimFlavourId)).append("\n");
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
