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
package com.ubiqube.etsi.mano.vnfm.v281.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v281.model.grant.Link;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@ApiModel(description = "Links to resources related to this resource. ")
@Validated

public class GrantLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("vnfLcmOpOcc")
  private Link vnfLcmOpOcc = null;

  @JsonProperty("vnfInstance")
  private Link vnfInstance = null;

  public GrantLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * URI of this resource. 
   * @return self
  **/
  @ApiModelProperty(required = true, value = "URI of this resource. ")
  @NotNull

  @Valid

  public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public GrantLinks vnfLcmOpOcc(Link vnfLcmOpOcc) {
    this.vnfLcmOpOcc = vnfLcmOpOcc;
    return this;
  }

  /**
   * Related VNF lifecycle management operation occurrence. 
   * @return vnfLcmOpOcc
  **/
  @ApiModelProperty(required = true, value = "Related VNF lifecycle management operation occurrence. ")
  @NotNull

  @Valid

  public Link getVnfLcmOpOcc() {
    return vnfLcmOpOcc;
  }

  public void setVnfLcmOpOcc(Link vnfLcmOpOcc) {
    this.vnfLcmOpOcc = vnfLcmOpOcc;
  }

  public GrantLinks vnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
    return this;
  }

  /**
   * Related VNF instance. 
   * @return vnfInstance
  **/
  @ApiModelProperty(required = true, value = "Related VNF instance. ")
  @NotNull

  @Valid

  public Link getVnfInstance() {
    return vnfInstance;
  }

  public void setVnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrantLinks grantLinks = (GrantLinks) o;
    return Objects.equals(this.self, grantLinks.self) &&
        Objects.equals(this.vnfLcmOpOcc, grantLinks.vnfLcmOpOcc) &&
        Objects.equals(this.vnfInstance, grantLinks.vnfInstance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, vnfLcmOpOcc, vnfInstance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    vnfLcmOpOcc: ").append(toIndentedString(vnfLcmOpOcc)).append("\n");
    sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
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

