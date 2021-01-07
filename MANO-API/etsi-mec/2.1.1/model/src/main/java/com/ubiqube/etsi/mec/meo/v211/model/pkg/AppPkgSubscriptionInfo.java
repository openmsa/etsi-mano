package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscriptionInfoLinks;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscriptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type represents a subscription to notification of application package management for the onboarding, or operational state change of application package&#x27;
 */
@ApiModel(description = "'The data type represents a subscription to notification of application package management for the onboarding, or operational state change of application package'")
@Validated
public class AppPkgSubscriptionInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("subscriptionType")
  private AppPkgSubscriptionType subscriptionType = null;

  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("_links")
  private AppPkgSubscriptionInfoLinks _links = null;

  public AppPkgSubscriptionInfo id(String id) {
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

  public AppPkgSubscriptionInfo subscriptionType(AppPkgSubscriptionType subscriptionType) {
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
    public AppPkgSubscriptionType getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(AppPkgSubscriptionType subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  public AppPkgSubscriptionInfo callbackUri(String callbackUri) {
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

  public AppPkgSubscriptionInfo _links(AppPkgSubscriptionInfoLinks _links) {
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
    public AppPkgSubscriptionInfoLinks getLinks() {
    return _links;
  }

  public void setLinks(AppPkgSubscriptionInfoLinks _links) {
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
    AppPkgSubscriptionInfo appPkgSubscriptionInfo = (AppPkgSubscriptionInfo) o;
    return Objects.equals(this.id, appPkgSubscriptionInfo.id) &&
        Objects.equals(this.subscriptionType, appPkgSubscriptionInfo.subscriptionType) &&
        Objects.equals(this.callbackUri, appPkgSubscriptionInfo.callbackUri) &&
        Objects.equals(this._links, appPkgSubscriptionInfo._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, subscriptionType, callbackUri, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppPkgSubscriptionInfo {\n");
    
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
