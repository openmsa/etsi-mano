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

package com.ubiqube.etsi.mano.model.v271.sol005.nsperfo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.model.v271.sol005.nsperfo.SubscriptionAuthentication;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents modifications to a threshold. It shall comply with the provisions defined in Table 7.5.2.11-1. 
 */
@ApiModel(description = "This type represents modifications to a threshold. It shall comply with the provisions defined in Table 7.5.2.11-1. ")
@Validated
public class ThresholdModifications   {
  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("authentication")
  private SubscriptionAuthentication authentication = null;

  public ThresholdModifications callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * Get callbackUri
   * @return callbackUri
  **/
  @ApiModelProperty(value = "")
  
    public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public ThresholdModifications authentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }

  /**
   * Get authentication
   * @return authentication
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public SubscriptionAuthentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ThresholdModifications thresholdModifications = (ThresholdModifications) o;
    return Objects.equals(this.callbackUri, thresholdModifications.callbackUri) &&
        Objects.equals(this.authentication, thresholdModifications.authentication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callbackUri, authentication);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThresholdModifications {\n");
    
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
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
