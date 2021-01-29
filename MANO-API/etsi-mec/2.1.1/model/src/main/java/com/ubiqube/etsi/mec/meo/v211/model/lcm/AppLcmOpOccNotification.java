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
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppLcmOpOccNotificationLinks;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppLcmOpOccNotificationType;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.TimeStamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;This data type represents a notification related to state changes of an application LCM operation occurrence which informs the subscribers&#x27;
 */
@ApiModel(description = "'This data type represents a notification related to state changes of an application LCM operation occurrence which informs the subscribers'")
@Validated
public class AppLcmOpOccNotification  implements OneOfbody1 {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("notificationType")
  private AppLcmOpOccNotificationType notificationType = null;

  @JsonProperty("subscriptionId")
  private String subscriptionId = null;

  @JsonProperty("timeStamp")
  private TimeStamp timeStamp = null;

  @JsonProperty("appLcmOpOccId")
  private String appLcmOpOccId = null;

  @JsonProperty("appInstanceId")
  private String appInstanceId = null;

  @JsonProperty("_links")
  private AppLcmOpOccNotificationLinks _links = null;

  public AppLcmOpOccNotification id(String id) {
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

  public AppLcmOpOccNotification notificationType(AppLcmOpOccNotificationType notificationType) {
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
    public AppLcmOpOccNotificationType getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(AppLcmOpOccNotificationType notificationType) {
    this.notificationType = notificationType;
  }

  public AppLcmOpOccNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * Get subscriptionId
   * @return subscriptionId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public AppLcmOpOccNotification timeStamp(TimeStamp timeStamp) {
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

  public AppLcmOpOccNotification appLcmOpOccId(String appLcmOpOccId) {
    this.appLcmOpOccId = appLcmOpOccId;
    return this;
  }

  /**
   * Get appLcmOpOccId
   * @return appLcmOpOccId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppLcmOpOccId() {
    return appLcmOpOccId;
  }

  public void setAppLcmOpOccId(String appLcmOpOccId) {
    this.appLcmOpOccId = appLcmOpOccId;
  }

  public AppLcmOpOccNotification appInstanceId(String appInstanceId) {
    this.appInstanceId = appInstanceId;
    return this;
  }

  /**
   * Get appInstanceId
   * @return appInstanceId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppInstanceId() {
    return appInstanceId;
  }

  public void setAppInstanceId(String appInstanceId) {
    this.appInstanceId = appInstanceId;
  }

  public AppLcmOpOccNotification _links(AppLcmOpOccNotificationLinks _links) {
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
    public AppLcmOpOccNotificationLinks getLinks() {
    return _links;
  }

  public void setLinks(AppLcmOpOccNotificationLinks _links) {
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
    AppLcmOpOccNotification appLcmOpOccNotification = (AppLcmOpOccNotification) o;
    return Objects.equals(this.id, appLcmOpOccNotification.id) &&
        Objects.equals(this.notificationType, appLcmOpOccNotification.notificationType) &&
        Objects.equals(this.subscriptionId, appLcmOpOccNotification.subscriptionId) &&
        Objects.equals(this.timeStamp, appLcmOpOccNotification.timeStamp) &&
        Objects.equals(this.appLcmOpOccId, appLcmOpOccNotification.appLcmOpOccId) &&
        Objects.equals(this.appInstanceId, appLcmOpOccNotification.appInstanceId) &&
        Objects.equals(this._links, appLcmOpOccNotification._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, notificationType, subscriptionId, timeStamp, appLcmOpOccId, appInstanceId, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppLcmOpOccNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    appLcmOpOccId: ").append(toIndentedString(appLcmOpOccId)).append("\n");
    sb.append("    appInstanceId: ").append(toIndentedString(appInstanceId)).append("\n");
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
