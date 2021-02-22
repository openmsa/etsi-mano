package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.LcmOpOccNotificationVerbosityType;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.LifecycleChangeNotificationsFilter;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.SubscriptionAuthentication;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription request related to notifications about VNF lifecycle changes. 
 */
@Schema(description = "This type represents a subscription request related to notifications about VNF lifecycle changes. ")
@Validated


public class LccnSubscriptionRequest   {
  @JsonProperty("filter")
  private LifecycleChangeNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("authentication")
  private SubscriptionAuthentication authentication = null;

  @JsonProperty("verbosity")
  private LcmOpOccNotificationVerbosityType verbosity = null;

  public LccnSubscriptionRequest filter(LifecycleChangeNotificationsFilter filter) {
    this.filter = filter;
    return this;
  }

  /**
   * Get filter
   * @return filter
   **/
  @Schema(description = "")
  
    @Valid
    public LifecycleChangeNotificationsFilter getFilter() {
    return filter;
  }

  public void setFilter(LifecycleChangeNotificationsFilter filter) {
    this.filter = filter;
  }

  public LccnSubscriptionRequest callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * Get callbackUri
   * @return callbackUri
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public LccnSubscriptionRequest authentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }

  /**
   * Get authentication
   * @return authentication
   **/
  @Schema(description = "")
  
    @Valid
    public SubscriptionAuthentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
  }

  public LccnSubscriptionRequest verbosity(LcmOpOccNotificationVerbosityType verbosity) {
    this.verbosity = verbosity;
    return this;
  }

  /**
   * Get verbosity
   * @return verbosity
   **/
  @Schema(description = "")
  
    @Valid
    public LcmOpOccNotificationVerbosityType getVerbosity() {
    return verbosity;
  }

  public void setVerbosity(LcmOpOccNotificationVerbosityType verbosity) {
    this.verbosity = verbosity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LccnSubscriptionRequest lccnSubscriptionRequest = (LccnSubscriptionRequest) o;
    return Objects.equals(this.filter, lccnSubscriptionRequest.filter) &&
        Objects.equals(this.callbackUri, lccnSubscriptionRequest.callbackUri) &&
        Objects.equals(this.authentication, lccnSubscriptionRequest.authentication) &&
        Objects.equals(this.verbosity, lccnSubscriptionRequest.verbosity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filter, callbackUri, authentication, verbosity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LccnSubscriptionRequest {\n");
    
    sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
    sb.append("    verbosity: ").append(toIndentedString(verbosity)).append("\n");
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
