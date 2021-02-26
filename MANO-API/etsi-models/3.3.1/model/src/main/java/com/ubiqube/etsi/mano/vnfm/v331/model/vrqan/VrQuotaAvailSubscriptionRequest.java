package com.ubiqube.etsi.mano.vnfm.v331.model.vrqan;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vrqan.SubscriptionAuthentication;
import com.ubiqube.etsi.mano.vnfm.v331.model.vrqan.VrQuotaAvailNotificationsFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription request related to notifications related to the availability of the virtualised resources quotas. 
 */
@Schema(description = "This type represents a subscription request related to notifications related to the availability of the virtualised resources quotas. ")
@Validated


public class VrQuotaAvailSubscriptionRequest   {
  @JsonProperty("filter")
  private VrQuotaAvailNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("authentication")
  private SubscriptionAuthentication authentication = null;

  public VrQuotaAvailSubscriptionRequest filter(VrQuotaAvailNotificationsFilter filter) {
    this.filter = filter;
    return this;
  }

  /**
   * Get filter
   * @return filter
   **/
  @Schema(description = "")
  
    @Valid
    public VrQuotaAvailNotificationsFilter getFilter() {
    return filter;
  }

  public void setFilter(VrQuotaAvailNotificationsFilter filter) {
    this.filter = filter;
  }

  public VrQuotaAvailSubscriptionRequest callbackUri(String callbackUri) {
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

  public VrQuotaAvailSubscriptionRequest authentication(SubscriptionAuthentication authentication) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VrQuotaAvailSubscriptionRequest vrQuotaAvailSubscriptionRequest = (VrQuotaAvailSubscriptionRequest) o;
    return Objects.equals(this.filter, vrQuotaAvailSubscriptionRequest.filter) &&
        Objects.equals(this.callbackUri, vrQuotaAvailSubscriptionRequest.callbackUri) &&
        Objects.equals(this.authentication, vrQuotaAvailSubscriptionRequest.authentication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filter, callbackUri, authentication);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VrQuotaAvailSubscriptionRequest {\n");
    
    sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
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
