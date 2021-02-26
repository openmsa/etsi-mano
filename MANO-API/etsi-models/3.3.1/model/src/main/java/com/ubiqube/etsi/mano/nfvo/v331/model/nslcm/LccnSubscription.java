package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LccnSubscriptionLinks;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LcmOpOccNotificationVerbosityType;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LifecycleChangeNotificationsFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription related to notifications about NS lifecycle changes.  It shall comply with the provisions defined in Table 6.5.2.4-1. 
 */
@Schema(description = "This type represents a subscription related to notifications about NS lifecycle changes.  It shall comply with the provisions defined in Table 6.5.2.4-1. ")
@Validated


public class LccnSubscription   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("filter")
  private LifecycleChangeNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("verbosity")
  private LcmOpOccNotificationVerbosityType verbosity = null;

  @JsonProperty("_links")
  private LccnSubscriptionLinks _links = null;

  public LccnSubscription id(String id) {
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

  public LccnSubscription filter(LifecycleChangeNotificationsFilter filter) {
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

  public LccnSubscription callbackUri(String callbackUri) {
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

  public LccnSubscription verbosity(LcmOpOccNotificationVerbosityType verbosity) {
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

  public LccnSubscription _links(LccnSubscriptionLinks _links) {
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
    public LccnSubscriptionLinks getLinks() {
    return _links;
  }

  public void setLinks(LccnSubscriptionLinks _links) {
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
    LccnSubscription lccnSubscription = (LccnSubscription) o;
    return Objects.equals(this.id, lccnSubscription.id) &&
        Objects.equals(this.filter, lccnSubscription.filter) &&
        Objects.equals(this.callbackUri, lccnSubscription.callbackUri) &&
        Objects.equals(this.verbosity, lccnSubscription.verbosity) &&
        Objects.equals(this._links, lccnSubscription._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, filter, callbackUri, verbosity, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LccnSubscription {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    filter: ").append(toIndentedString(filter)).append("\n");
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    verbosity: ").append(toIndentedString(verbosity)).append("\n");
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
