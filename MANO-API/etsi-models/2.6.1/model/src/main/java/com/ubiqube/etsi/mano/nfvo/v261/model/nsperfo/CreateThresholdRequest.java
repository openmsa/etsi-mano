/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
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

