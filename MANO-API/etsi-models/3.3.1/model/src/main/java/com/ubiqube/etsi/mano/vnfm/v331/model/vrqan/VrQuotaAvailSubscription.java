package com.ubiqube.etsi.mano.vnfm.v331.model.vrqan;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vrqan.VrQuotaAvailNotificationsFilter;
import com.ubiqube.etsi.mano.vnfm.v331.model.vrqan.VrQuotaAvailSubscriptionLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription related to notifications related to the availability of the virtualised resources quotas. 
 */
@Schema(description = "This type represents a subscription related to notifications related to the availability of the virtualised resources quotas. ")
@Validated


public class VrQuotaAvailSubscription   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("filter")
  private VrQuotaAvailNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private VrQuotaAvailSubscriptionLinks _links = null;

  public VrQuotaAvailSubscription id(String id) {
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

  public VrQuotaAvailSubscription filter(VrQuotaAvailNotificationsFilter filter) {
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

  public VrQuotaAvailSubscription callbackUri(String callbackUri) {
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

  public VrQuotaAvailSubscription _links(VrQuotaAvailSubscriptionLinks _links) {
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
    public VrQuotaAvailSubscriptionLinks getLinks() {
    return _links;
  }

  public void setLinks(VrQuotaAvailSubscriptionLinks _links) {
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
    VrQuotaAvailSubscription vrQuotaAvailSubscription = (VrQuotaAvailSubscription) o;
    return Objects.equals(this.id, vrQuotaAvailSubscription.id) &&
        Objects.equals(this.filter, vrQuotaAvailSubscription.filter) &&
        Objects.equals(this.callbackUri, vrQuotaAvailSubscription.callbackUri) &&
        Objects.equals(this._links, vrQuotaAvailSubscription._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, filter, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VrQuotaAvailSubscription {\n");
    
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
