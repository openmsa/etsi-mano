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
package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppLcmOpOccSubscriptionFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AppLcmOpOccSubscriptionRequest
 */
@Validated
public class AppLcmOpOccSubscriptionRequest  implements OneOfbody {
  @JsonProperty("appLcmOpOccSubscriptionFilter")
  private AppLcmOpOccSubscriptionFilter appLcmOpOccSubscriptionFilter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("subscriptionType")
  private String subscriptionType = null;

  public AppLcmOpOccSubscriptionRequest appLcmOpOccSubscriptionFilter(AppLcmOpOccSubscriptionFilter appLcmOpOccSubscriptionFilter) {
    this.appLcmOpOccSubscriptionFilter = appLcmOpOccSubscriptionFilter;
    return this;
  }

  /**
   * Get appLcmOpOccSubscriptionFilter
   * @return appLcmOpOccSubscriptionFilter
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AppLcmOpOccSubscriptionFilter getAppLcmOpOccSubscriptionFilter() {
    return appLcmOpOccSubscriptionFilter;
  }

  public void setAppLcmOpOccSubscriptionFilter(AppLcmOpOccSubscriptionFilter appLcmOpOccSubscriptionFilter) {
    this.appLcmOpOccSubscriptionFilter = appLcmOpOccSubscriptionFilter;
  }

  public AppLcmOpOccSubscriptionRequest callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * Get callbackUri
   * @return callbackUri
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public AppLcmOpOccSubscriptionRequest subscriptionType(String subscriptionType) {
    this.subscriptionType = subscriptionType;
    return this;
  }

  /**
   * Shall be set to \"AppLcmOpOccStateChange\".
   * @return subscriptionType
  **/
  @ApiModelProperty(required = true, value = "Shall be set to \"AppLcmOpOccStateChange\".")
      @NotNull

    public String getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(String subscriptionType) {
    this.subscriptionType = subscriptionType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppLcmOpOccSubscriptionRequest appLcmOpOccSubscriptionRequest = (AppLcmOpOccSubscriptionRequest) o;
    return Objects.equals(this.appLcmOpOccSubscriptionFilter, appLcmOpOccSubscriptionRequest.appLcmOpOccSubscriptionFilter) &&
        Objects.equals(this.callbackUri, appLcmOpOccSubscriptionRequest.callbackUri) &&
        Objects.equals(this.subscriptionType, appLcmOpOccSubscriptionRequest.subscriptionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appLcmOpOccSubscriptionFilter, callbackUri, subscriptionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppLcmOpOccSubscriptionRequest {\n");
    
    sb.append("    appLcmOpOccSubscriptionFilter: ").append(toIndentedString(appLcmOpOccSubscriptionFilter)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
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
