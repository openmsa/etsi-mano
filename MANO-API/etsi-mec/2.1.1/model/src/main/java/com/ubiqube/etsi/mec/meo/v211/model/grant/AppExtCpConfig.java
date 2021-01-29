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
import com.ubiqube.etsi.mec.meo.v211.model.grant.CpProtocolData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AppExtCpConfig
 */
@Validated
public class AppExtCpConfig   {
  @JsonProperty("cpInstanceId")
  private String cpInstanceId = null;

  @JsonProperty("cpProtocolData")
  @Valid
  private List<CpProtocolData> cpProtocolData = null;

  @JsonProperty("linkPortId")
  private String linkPortId = null;

  public AppExtCpConfig cpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

  /**
   * Identifier of the external CP instance to which this set of configuration parameters is requested to be applied.   Shall be present if this instance has already been created. 
   * @return cpInstanceId
  **/
  @ApiModelProperty(value = "Identifier of the external CP instance to which this set of configuration parameters is requested to be applied.   Shall be present if this instance has already been created. ")
  
    public String getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public AppExtCpConfig cpProtocolData(List<CpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
    return this;
  }

  public AppExtCpConfig addCpProtocolDataItem(CpProtocolData cpProtocolDataItem) {
    if (this.cpProtocolData == null) {
      this.cpProtocolData = new ArrayList<>();
    }
    this.cpProtocolData.add(cpProtocolDataItem);
    return this;
  }

  /**
   * Parameters for configuring the network protocols on the link port that connects the CP to a VL. See note.
   * @return cpProtocolData
  **/
  @ApiModelProperty(value = "Parameters for configuring the network protocols on the link port that connects the CP to a VL. See note.")
      @Valid
    public List<CpProtocolData> getCpProtocolData() {
    return cpProtocolData;
  }

  public void setCpProtocolData(List<CpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
  }

  public AppExtCpConfig linkPortId(String linkPortId) {
    this.linkPortId = linkPortId;
    return this;
  }

  /**
   * Identifier of a pre-configured link port to which the external CP will be associated. See note.
   * @return linkPortId
  **/
  @ApiModelProperty(value = "Identifier of a pre-configured link port to which the external CP will be associated. See note.")
  
    public String getLinkPortId() {
    return linkPortId;
  }

  public void setLinkPortId(String linkPortId) {
    this.linkPortId = linkPortId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppExtCpConfig appExtCpConfig = (AppExtCpConfig) o;
    return Objects.equals(this.cpInstanceId, appExtCpConfig.cpInstanceId) &&
        Objects.equals(this.cpProtocolData, appExtCpConfig.cpProtocolData) &&
        Objects.equals(this.linkPortId, appExtCpConfig.linkPortId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpInstanceId, cpProtocolData, linkPortId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppExtCpConfig {\n");
    
    sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
    sb.append("    cpProtocolData: ").append(toIndentedString(cpProtocolData)).append("\n");
    sb.append("    linkPortId: ").append(toIndentedString(linkPortId)).append("\n");
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
