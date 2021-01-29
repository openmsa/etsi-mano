/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstNotificationLinks;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstNotificationType;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.TimeStamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AppInstNotification
 */
@Validated
public class AppInstNotification  implements OneOfbody1 {
  @JsonProperty("_links")
  private AppInstNotificationLinks _links = null;

  @JsonProperty("appDId")
  private String appDId = null;

  @JsonProperty("appInstanceId")
  private String appInstanceId = null;

  @JsonProperty("appPkgId")
  private String appPkgId = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("notificationType")
  private AppInstNotificationType notificationType = null;

  @JsonProperty("subscriptionId")
  private String subscriptionId = null;

  @JsonProperty("timeStamp")
  private TimeStamp timeStamp = null;

  public AppInstNotification _links(AppInstNotificationLinks _links) {
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
    public AppInstNotificationLinks getLinks() {
    return _links;
  }

  public void setLinks(AppInstNotificationLinks _links) {
    this._links = _links;
  }

  public AppInstNotification appDId(String appDId) {
    this.appDId = appDId;
    return this;
  }

  /**
   * The application descriptor identifier identifies the application package and the application descriptor in a globally unique way.
   * @return appDId
  **/
  @ApiModelProperty(required = true, value = "The application descriptor identifier identifies the application package and the application descriptor in a globally unique way.")
      @NotNull

    public String getAppDId() {
    return appDId;
  }

  public void setAppDId(String appDId) {
    this.appDId = appDId;
  }

  public AppInstNotification appInstanceId(String appInstanceId) {
    this.appInstanceId = appInstanceId;
    return this;
  }

  /**
   * Identifier of application instance.
   * @return appInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of application instance.")
      @NotNull

    public String getAppInstanceId() {
    return appInstanceId;
  }

  public void setAppInstanceId(String appInstanceId) {
    this.appInstanceId = appInstanceId;
  }

  public AppInstNotification appPkgId(String appPkgId) {
    this.appPkgId = appPkgId;
    return this;
  }

  /**
   * Identifier of the onboarded application package. 
   * @return appPkgId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the onboarded application package. ")
      @NotNull

    public String getAppPkgId() {
    return appPkgId;
  }

  public void setAppPkgId(String appPkgId) {
    this.appPkgId = appPkgId;
  }

  public AppInstNotification id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"notificationId\" attribute of all these notifications shall have the same value.
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"notificationId\" attribute of all these notifications shall have the same value.")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AppInstNotification notificationType(AppInstNotificationType notificationType) {
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

  public AppInstNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * Identifier of the subscription related to this notification.
   * @return subscriptionId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the subscription related to this notification.")
      @NotNull

    public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public AppInstNotification timeStamp(TimeStamp timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Get timeStamp
   * @return timeStamp
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public TimeStamp getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(TimeStamp timeStamp) {
    this.timeStamp = timeStamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppInstNotification appInstNotification = (AppInstNotification) o;
    return Objects.equals(this._links, appInstNotification._links) &&
        Objects.equals(this.appDId, appInstNotification.appDId) &&
        Objects.equals(this.appInstanceId, appInstNotification.appInstanceId) &&
        Objects.equals(this.appPkgId, appInstNotification.appPkgId) &&
        Objects.equals(this.id, appInstNotification.id) &&
        Objects.equals(this.notificationType, appInstNotification.notificationType) &&
        Objects.equals(this.subscriptionId, appInstNotification.subscriptionId) &&
        Objects.equals(this.timeStamp, appInstNotification.timeStamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_links, appDId, appInstanceId, appPkgId, id, notificationType, subscriptionId, timeStamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppInstNotification {\n");
    
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
    sb.append("    appDId: ").append(toIndentedString(appDId)).append("\n");
    sb.append("    appInstanceId: ").append(toIndentedString(appInstanceId)).append("\n");
    sb.append("    appPkgId: ").append(toIndentedString(appPkgId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
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
