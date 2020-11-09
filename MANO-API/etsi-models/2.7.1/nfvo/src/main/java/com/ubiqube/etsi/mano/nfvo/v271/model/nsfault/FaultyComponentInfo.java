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

package com.ubiqube.etsi.mano.nfvo.v271.model.nsfault;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the faulty component that has a negative impact on an NS.  It shall comply with the provisions defined in Table 8.5.3.4-1. 
 */
@ApiModel(description = "This type represents the faulty component that has a negative impact on an NS.  It shall comply with the provisions defined in Table 8.5.3.4-1. ")
@Validated
public class FaultyComponentInfo   {
  @JsonProperty("faultyNestedNsInstanceId")
  private String faultyNestedNsInstanceId = null;

  @JsonProperty("faultyResourceType")
  private String faultyResourceType = null;

  @JsonProperty("faultyNsVirtualLinkInstanceId")
  private String faultyNsVirtualLinkInstanceId = null;

  public FaultyComponentInfo faultyNestedNsInstanceId(String faultyNestedNsInstanceId) {
    this.faultyNestedNsInstanceId = faultyNestedNsInstanceId;
    return this;
  }

  /**
   * Get faultyNestedNsInstanceId
   * @return faultyNestedNsInstanceId
  **/
  @ApiModelProperty(value = "")
  
    public String getFaultyNestedNsInstanceId() {
    return faultyNestedNsInstanceId;
  }

  public void setFaultyNestedNsInstanceId(String faultyNestedNsInstanceId) {
    this.faultyNestedNsInstanceId = faultyNestedNsInstanceId;
  }

  public FaultyComponentInfo faultyResourceType(String faultyResourceType) {
    this.faultyResourceType = faultyResourceType;
    return this;
  }

  /**
   * Get faultyResourceType
   * @return faultyResourceType
  **/
  @ApiModelProperty(value = "")
  
    public String getFaultyResourceType() {
    return faultyResourceType;
  }

  public void setFaultyResourceType(String faultyResourceType) {
    this.faultyResourceType = faultyResourceType;
  }

  public FaultyComponentInfo faultyNsVirtualLinkInstanceId(String faultyNsVirtualLinkInstanceId) {
    this.faultyNsVirtualLinkInstanceId = faultyNsVirtualLinkInstanceId;
    return this;
  }

  /**
   * Get faultyNsVirtualLinkInstanceId
   * @return faultyNsVirtualLinkInstanceId
  **/
  @ApiModelProperty(value = "")
  
    public String getFaultyNsVirtualLinkInstanceId() {
    return faultyNsVirtualLinkInstanceId;
  }

  public void setFaultyNsVirtualLinkInstanceId(String faultyNsVirtualLinkInstanceId) {
    this.faultyNsVirtualLinkInstanceId = faultyNsVirtualLinkInstanceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FaultyComponentInfo faultyComponentInfo = (FaultyComponentInfo) o;
    return Objects.equals(this.faultyNestedNsInstanceId, faultyComponentInfo.faultyNestedNsInstanceId) &&
        Objects.equals(this.faultyResourceType, faultyComponentInfo.faultyResourceType) &&
        Objects.equals(this.faultyNsVirtualLinkInstanceId, faultyComponentInfo.faultyNsVirtualLinkInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(faultyNestedNsInstanceId, faultyResourceType, faultyNsVirtualLinkInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FaultyComponentInfo {\n");
    
    sb.append("    faultyNestedNsInstanceId: ").append(toIndentedString(faultyNestedNsInstanceId)).append("\n");
    sb.append("    faultyResourceType: ").append(toIndentedString(faultyResourceType)).append("\n");
    sb.append("    faultyNsVirtualLinkInstanceId: ").append(toIndentedString(faultyNsVirtualLinkInstanceId)).append("\n");
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
