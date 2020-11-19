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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Request parameters to create a threshold resource.             
 **/
@ApiModel(description="Request parameters to create a threshold resource.             ")
public class CreateThresholdRequest  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private ThresholdsCreateThresholdRequest createThresholdRequest = null;
 /**
   * Get createThresholdRequest
   * @return createThresholdRequest
  **/
  @JsonProperty("CreateThresholdRequest")
  @NotNull
  public ThresholdsCreateThresholdRequest getCreateThresholdRequest() {
    return createThresholdRequest;
  }

  public void setCreateThresholdRequest(ThresholdsCreateThresholdRequest createThresholdRequest) {
    this.createThresholdRequest = createThresholdRequest;
  }

  public CreateThresholdRequest createThresholdRequest(ThresholdsCreateThresholdRequest createThresholdRequest) {
    this.createThresholdRequest = createThresholdRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateThresholdRequest {\n");
    
    sb.append("    createThresholdRequest: ").append(toIndentedString(createThresholdRequest)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

