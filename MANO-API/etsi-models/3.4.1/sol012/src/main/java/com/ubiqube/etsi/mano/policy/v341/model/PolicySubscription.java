package com.ubiqube.etsi.mano.policy.v341.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.policy.v341.model.PolicyNotificationsFilter;
import com.ubiqube.etsi.mano.policy.v341.model.PolicySubscriptionLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription related to notifications about policy changes and policy conflicts.  It shall comply with the provisions defined in table 5.6.2.6-1. 
 */
@Schema(description = "This type represents a subscription related to notifications about policy changes and policy conflicts.  It shall comply with the provisions defined in table 5.6.2.6-1. ")
@Validated


public class PolicySubscription   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("filter")
  private PolicyNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private PolicySubscriptionLinks _links = null;

  public PolicySubscription id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PolicySubscription filter(PolicyNotificationsFilter filter) {
    this.filter = filter;
    return this;
  }

  /**
   * Get filter
   * @return filter
   **/
  @Schema(description = "")
  
    @Valid
    public PolicyNotificationsFilter getFilter() {
    return filter;
  }

  public void setFilter(PolicyNotificationsFilter filter) {
    this.filter = filter;
  }

  public PolicySubscription callbackUri(String callbackUri) {
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

  public PolicySubscription _links(PolicySubscriptionLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public PolicySubscriptionLinks getLinks() {
    return _links;
  }

  public void setLinks(PolicySubscriptionLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PolicySubscription policySubscription = (PolicySubscription) o;
    return Objects.equals(this.id, policySubscription.id) &&
        Objects.equals(this.filter, policySubscription.filter) &&
        Objects.equals(this.callbackUri, policySubscription.callbackUri) &&
        Objects.equals(this._links, policySubscription._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, filter, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PolicySubscription {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
