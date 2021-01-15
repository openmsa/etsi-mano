package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstSubscriptionType;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceState;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceSubscriptionFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AppInstSubscriptionRequest
 */
@Validated
public class AppInstSubscriptionRequest  implements OneOfbody {
  @JsonProperty("subscriptionType")
  private AppInstSubscriptionType subscriptionType = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("appInstanceState")
  private AppInstanceState appInstanceState = null;

  @JsonProperty("appInstanceSubscriptionFilter")
  private AppInstanceSubscriptionFilter appInstanceSubscriptionFilter = null;

  public AppInstSubscriptionRequest subscriptionType(AppInstSubscriptionType subscriptionType) {
    this.subscriptionType = subscriptionType;
    return this;
  }

  /**
   * Get subscriptionType
   * @return subscriptionType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppInstSubscriptionType getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(AppInstSubscriptionType subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  public AppInstSubscriptionRequest callbackUri(String callbackUri) {
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

  public AppInstSubscriptionRequest appInstanceState(AppInstanceState appInstanceState) {
    this.appInstanceState = appInstanceState;
    return this;
  }

  /**
   * Get appInstanceState
   * @return appInstanceState
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AppInstanceState getAppInstanceState() {
    return appInstanceState;
  }

  public void setAppInstanceState(AppInstanceState appInstanceState) {
    this.appInstanceState = appInstanceState;
  }

  public AppInstSubscriptionRequest appInstanceSubscriptionFilter(AppInstanceSubscriptionFilter appInstanceSubscriptionFilter) {
    this.appInstanceSubscriptionFilter = appInstanceSubscriptionFilter;
    return this;
  }

  /**
   * Get appInstanceSubscriptionFilter
   * @return appInstanceSubscriptionFilter
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AppInstanceSubscriptionFilter getAppInstanceSubscriptionFilter() {
    return appInstanceSubscriptionFilter;
  }

  public void setAppInstanceSubscriptionFilter(AppInstanceSubscriptionFilter appInstanceSubscriptionFilter) {
    this.appInstanceSubscriptionFilter = appInstanceSubscriptionFilter;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppInstSubscriptionRequest appInstSubscriptionRequest = (AppInstSubscriptionRequest) o;
    return Objects.equals(this.subscriptionType, appInstSubscriptionRequest.subscriptionType) &&
        Objects.equals(this.callbackUri, appInstSubscriptionRequest.callbackUri) &&
        Objects.equals(this.appInstanceState, appInstSubscriptionRequest.appInstanceState) &&
        Objects.equals(this.appInstanceSubscriptionFilter, appInstSubscriptionRequest.appInstanceSubscriptionFilter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionType, callbackUri, appInstanceState, appInstanceSubscriptionFilter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppInstSubscriptionRequest {\n");
    
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    appInstanceState: ").append(toIndentedString(appInstanceState)).append("\n");
    sb.append("    appInstanceSubscriptionFilter: ").append(toIndentedString(appInstanceSubscriptionFilter)).append("\n");
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
