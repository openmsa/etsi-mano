package com.ubiqube.etsi.mano.nfvo.v331.model.nfvici;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nfvici.NfviCapacityInfoNotificationsFilter;
import com.ubiqube.etsi.mano.nfvo.v331.model.nfvici.NfviCapacityInfoSubscriptionLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription. It shall comply with the provisions defined in Table 10.5.2.9-1. 
 */
@Schema(description = "This type represents a subscription. It shall comply with the provisions defined in Table 10.5.2.9-1. ")
@Validated


public class NfviCapacityInfoSubscription   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("filter")
  private NfviCapacityInfoNotificationsFilter filter = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private NfviCapacityInfoSubscriptionLinks _links = null;

  public NfviCapacityInfoSubscription id(String id) {
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

  public NfviCapacityInfoSubscription filter(NfviCapacityInfoNotificationsFilter filter) {
    this.filter = filter;
    return this;
  }

  /**
   * Get filter
   * @return filter
   **/
  @Schema(description = "")
  
    @Valid
    public NfviCapacityInfoNotificationsFilter getFilter() {
    return filter;
  }

  public void setFilter(NfviCapacityInfoNotificationsFilter filter) {
    this.filter = filter;
  }

  public NfviCapacityInfoSubscription callbackUri(String callbackUri) {
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

  public NfviCapacityInfoSubscription _links(NfviCapacityInfoSubscriptionLinks _links) {
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
    public NfviCapacityInfoSubscriptionLinks getLinks() {
    return _links;
  }

  public void setLinks(NfviCapacityInfoSubscriptionLinks _links) {
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
    NfviCapacityInfoSubscription nfviCapacityInfoSubscription = (NfviCapacityInfoSubscription) o;
    return Objects.equals(this.id, nfviCapacityInfoSubscription.id) &&
        Objects.equals(this.filter, nfviCapacityInfoSubscription.filter) &&
        Objects.equals(this.callbackUri, nfviCapacityInfoSubscription.callbackUri) &&
        Objects.equals(this._links, nfviCapacityInfoSubscription._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, filter, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NfviCapacityInfoSubscription {\n");
    
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
