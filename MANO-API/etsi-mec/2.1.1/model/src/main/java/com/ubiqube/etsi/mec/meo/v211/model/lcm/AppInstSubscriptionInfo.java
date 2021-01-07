package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstNotificationType;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstSubscriptionInfoLinks;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstSubscriptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type represents a subscription to notification of application instance operational state change.&#x27;
 */
@ApiModel(description = "'The data type represents a subscription to notification of application instance operational state change.'")
@Validated
public class AppInstSubscriptionInfo  implements OneOfinlineResponse201 {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("subscriptionType")
  private AppInstSubscriptionType subscriptionType = null;

  @JsonProperty("notificationType")
  private AppInstNotificationType notificationType = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private AppInstSubscriptionInfoLinks _links = null;

  public AppInstSubscriptionInfo id(String id) {
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

  public AppInstSubscriptionInfo subscriptionType(AppInstSubscriptionType subscriptionType) {
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

  public AppInstSubscriptionInfo notificationType(AppInstNotificationType notificationType) {
    this.notificationType = notificationType;
    return this;
  }

  /**
   * Get notificationType
   * @return notificationType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppInstNotificationType getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(AppInstNotificationType notificationType) {
    this.notificationType = notificationType;
  }

  public AppInstSubscriptionInfo callbackUri(String callbackUri) {
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

  public AppInstSubscriptionInfo _links(AppInstSubscriptionInfoLinks _links) {
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
    public AppInstSubscriptionInfoLinks getLinks() {
    return _links;
  }

  public void setLinks(AppInstSubscriptionInfoLinks _links) {
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
    AppInstSubscriptionInfo appInstSubscriptionInfo = (AppInstSubscriptionInfo) o;
    return Objects.equals(this.id, appInstSubscriptionInfo.id) &&
        Objects.equals(this.subscriptionType, appInstSubscriptionInfo.subscriptionType) &&
        Objects.equals(this.notificationType, appInstSubscriptionInfo.notificationType) &&
        Objects.equals(this.callbackUri, appInstSubscriptionInfo.callbackUri) &&
        Objects.equals(this._links, appInstSubscriptionInfo._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subscriptionType, notificationType, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppInstSubscriptionInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
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
