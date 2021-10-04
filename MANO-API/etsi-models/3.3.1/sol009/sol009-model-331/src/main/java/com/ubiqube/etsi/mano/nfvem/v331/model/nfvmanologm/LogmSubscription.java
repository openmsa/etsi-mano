package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LogmNotificationsFilter;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LogmSubscriptionLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription. It shall comply with the provisions defined in table 8.6.2.3-1.
 */
@Schema(description = "This type represents a subscription. It shall comply with the provisions defined in table 8.6.2.3-1.")
@Validated


public class LogmSubscription   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("filter")
  private LogmNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private LogmSubscriptionLinks _links = null;

  public LogmSubscription id(String id) {
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

  public LogmSubscription filter(LogmNotificationsFilter filter) {
    this.filter = filter;
    return this;
  }

  /**
   * Get filter
   * @return filter
   **/
  @Schema(description = "")
  
    @Valid
    public LogmNotificationsFilter getFilter() {
    return filter;
  }

  public void setFilter(LogmNotificationsFilter filter) {
    this.filter = filter;
  }

  public LogmSubscription callbackUri(String callbackUri) {
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

  public LogmSubscription _links(LogmSubscriptionLinks _links) {
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
    public LogmSubscriptionLinks getLinks() {
    return _links;
  }

  public void setLinks(LogmSubscriptionLinks _links) {
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
    LogmSubscription logmSubscription = (LogmSubscription) o;
    return Objects.equals(this.id, logmSubscription.id) &&
        Objects.equals(this.filter, logmSubscription.filter) &&
        Objects.equals(this.callbackUri, logmSubscription.callbackUri) &&
        Objects.equals(this._links, logmSubscription._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, filter, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogmSubscription {\n");
    
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
