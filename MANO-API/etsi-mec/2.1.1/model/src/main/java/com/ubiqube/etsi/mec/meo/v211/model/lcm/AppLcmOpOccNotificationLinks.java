package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.LinkType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource.
 */
@ApiModel(description = "Links to resources related to this resource.")
@Validated
public class AppLcmOpOccNotificationLinks   {
  @JsonProperty("appInstance")
  private LinkType appInstance = null;

  @JsonProperty("subscription")
  private LinkType subscription = null;

  @JsonProperty("appLcmOpOcc")
  private LinkType appLcmOpOcc = null;

  public AppLcmOpOccNotificationLinks appInstance(LinkType appInstance) {
    this.appInstance = appInstance;
    return this;
  }

  /**
   * Get appInstance
   * @return appInstance
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LinkType getAppInstance() {
    return appInstance;
  }

  public void setAppInstance(LinkType appInstance) {
    this.appInstance = appInstance;
  }

  public AppLcmOpOccNotificationLinks subscription(LinkType subscription) {
    this.subscription = subscription;
    return this;
  }

  /**
   * Get subscription
   * @return subscription
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LinkType getSubscription() {
    return subscription;
  }

  public void setSubscription(LinkType subscription) {
    this.subscription = subscription;
  }

  public AppLcmOpOccNotificationLinks appLcmOpOcc(LinkType appLcmOpOcc) {
    this.appLcmOpOcc = appLcmOpOcc;
    return this;
  }

  /**
   * Get appLcmOpOcc
   * @return appLcmOpOcc
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LinkType getAppLcmOpOcc() {
    return appLcmOpOcc;
  }

  public void setAppLcmOpOcc(LinkType appLcmOpOcc) {
    this.appLcmOpOcc = appLcmOpOcc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppLcmOpOccNotificationLinks appLcmOpOccNotificationLinks = (AppLcmOpOccNotificationLinks) o;
    return Objects.equals(this.appInstance, appLcmOpOccNotificationLinks.appInstance) &&
        Objects.equals(this.subscription, appLcmOpOccNotificationLinks.subscription) &&
        Objects.equals(this.appLcmOpOcc, appLcmOpOccNotificationLinks.appLcmOpOcc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appInstance, subscription, appLcmOpOcc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppLcmOpOccNotificationLinks {\n");
    
    sb.append("    appInstance: ").append(toIndentedString(appInstance)).append("\n");
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
    sb.append("    appLcmOpOcc: ").append(toIndentedString(appLcmOpOcc)).append("\n");
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
