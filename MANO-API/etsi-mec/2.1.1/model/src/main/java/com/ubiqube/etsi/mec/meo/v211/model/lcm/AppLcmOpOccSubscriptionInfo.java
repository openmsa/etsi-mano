package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppLcmOpOccSubscriptionInfoLinks;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppLcmOpOccSubscriptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;This data type represents a subscription to notifications of application life cycle management operation occurrence&#x27;
 */
@ApiModel(description = "'This data type represents a subscription to notifications of application life cycle management operation occurrence'")
@Validated
public class AppLcmOpOccSubscriptionInfo  implements OneOfinlineResponse201 {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("subscriptionType")
  private AppLcmOpOccSubscriptionType subscriptionType = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private AppLcmOpOccSubscriptionInfoLinks _links = null;

  public AppLcmOpOccSubscriptionInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AppLcmOpOccSubscriptionInfo subscriptionType(AppLcmOpOccSubscriptionType subscriptionType) {
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
    public AppLcmOpOccSubscriptionType getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(AppLcmOpOccSubscriptionType subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  public AppLcmOpOccSubscriptionInfo callbackUri(String callbackUri) {
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

  public AppLcmOpOccSubscriptionInfo _links(AppLcmOpOccSubscriptionInfoLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppLcmOpOccSubscriptionInfoLinks getLinks() {
    return _links;
  }

  public void setLinks(AppLcmOpOccSubscriptionInfoLinks _links) {
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
    AppLcmOpOccSubscriptionInfo appLcmOpOccSubscriptionInfo = (AppLcmOpOccSubscriptionInfo) o;
    return Objects.equals(this.id, appLcmOpOccSubscriptionInfo.id) &&
        Objects.equals(this.subscriptionType, appLcmOpOccSubscriptionInfo.subscriptionType) &&
        Objects.equals(this.callbackUri, appLcmOpOccSubscriptionInfo.callbackUri) &&
        Objects.equals(this._links, appLcmOpOccSubscriptionInfo._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subscriptionType, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppLcmOpOccSubscriptionInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
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
