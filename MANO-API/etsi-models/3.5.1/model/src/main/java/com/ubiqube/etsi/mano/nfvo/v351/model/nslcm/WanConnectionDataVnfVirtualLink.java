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
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information used to identify the VNF VL for which the WAN connectivity data is applicable. Either a \&quot;nsVirtualLink\&quot; or a \&quot;vnfVirtualLink\&quot; shall be provided, but not both. 
 */
@Schema(description = "Information used to identify the VNF VL for which the WAN connectivity data is applicable. Either a \"nsVirtualLink\" or a \"vnfVirtualLink\" shall be provided, but not both. ")
@Validated


public class WanConnectionDataVnfVirtualLink   {
  @JsonProperty("vnfProfileId")
  private String vnfProfileId = null;

  @JsonProperty("vnfVirtualLinkDescId")
  private String vnfVirtualLinkDescId = null;

  @JsonProperty("vnfVirtualLinkProfileId")
  private String vnfVirtualLinkProfileId = null;

  public WanConnectionDataVnfVirtualLink vnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
    return this;
  }

  /**
   * Get vnfProfileId
   * @return vnfProfileId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfProfileId() {
    return vnfProfileId;
  }

  public void setVnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
  }

  public WanConnectionDataVnfVirtualLink vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
    return this;
  }

  /**
   * Get vnfVirtualLinkDescId
   * @return vnfVirtualLinkDescId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfVirtualLinkDescId() {
    return vnfVirtualLinkDescId;
  }

  public void setVnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
  }

  public WanConnectionDataVnfVirtualLink vnfVirtualLinkProfileId(String vnfVirtualLinkProfileId) {
    this.vnfVirtualLinkProfileId = vnfVirtualLinkProfileId;
    return this;
  }

  /**
   * Get vnfVirtualLinkProfileId
   * @return vnfVirtualLinkProfileId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfVirtualLinkProfileId() {
    return vnfVirtualLinkProfileId;
  }

  public void setVnfVirtualLinkProfileId(String vnfVirtualLinkProfileId) {
    this.vnfVirtualLinkProfileId = vnfVirtualLinkProfileId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WanConnectionDataVnfVirtualLink wanConnectionDataVnfVirtualLink = (WanConnectionDataVnfVirtualLink) o;
    return Objects.equals(this.vnfProfileId, wanConnectionDataVnfVirtualLink.vnfProfileId) &&
        Objects.equals(this.vnfVirtualLinkDescId, wanConnectionDataVnfVirtualLink.vnfVirtualLinkDescId) &&
        Objects.equals(this.vnfVirtualLinkProfileId, wanConnectionDataVnfVirtualLink.vnfVirtualLinkProfileId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfProfileId, vnfVirtualLinkDescId, vnfVirtualLinkProfileId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WanConnectionDataVnfVirtualLink {\n");
    
    sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    vnfVirtualLinkProfileId: ").append(toIndentedString(vnfVirtualLinkProfileId)).append("\n");
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
