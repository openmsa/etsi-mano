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
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgNotificationLinks;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgNotificationType;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.TimeStamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;This data type represents an application package management notification for informing the subscribers about onboarding application package resources. The notification is triggered when a new application package is onboarded&#x27;
 */
@ApiModel(description = "'This data type represents an application package management notification for informing the subscribers about onboarding application package resources. The notification is triggered when a new application package is onboarded'")
@Validated
public class AppPkgNotification   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("notificationType")
  private AppPkgNotificationType notificationType = null;

  @JsonProperty("subscriptionId")
  private String subscriptionId = null;

  @JsonProperty("timeStamp")
  private TimeStamp timeStamp = null;

  @JsonProperty("appPkgId")
  private String appPkgId = null;

  @JsonProperty("appDId")
  private String appDId = null;

  /**
   * Gets or Sets operationalState
   */
  public enum OperationalStateEnum {
    DISABLED("DISABLED"),
    
    ENABLED("ENABLED");

    private String value;

    OperationalStateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static OperationalStateEnum fromValue(String text) {
      for (OperationalStateEnum b : OperationalStateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("operationalState")
  private OperationalStateEnum operationalState = null;

  @JsonProperty("_links")
  private AppPkgNotificationLinks _links = null;

  public AppPkgNotification id(String id) {
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

  public AppPkgNotification notificationType(AppPkgNotificationType notificationType) {
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
    public AppPkgNotificationType getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(AppPkgNotificationType notificationType) {
    this.notificationType = notificationType;
  }

  public AppPkgNotification subscriptionId(String subscriptionId) {
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

  public AppPkgNotification timeStamp(TimeStamp timeStamp) {
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

  public AppPkgNotification appPkgId(String appPkgId) {
    this.appPkgId = appPkgId;
    return this;
  }

  /**
   * Get appPkgId
   * @return appPkgId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppPkgId() {
    return appPkgId;
  }

  public void setAppPkgId(String appPkgId) {
    this.appPkgId = appPkgId;
  }

  public AppPkgNotification appDId(String appDId) {
    this.appDId = appDId;
    return this;
  }

  /**
   * Get appDId
   * @return appDId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppDId() {
    return appDId;
  }

  public void setAppDId(String appDId) {
    this.appDId = appDId;
  }

  public AppPkgNotification operationalState(OperationalStateEnum operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  /**
   * Get operationalState
   * @return operationalState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public OperationalStateEnum getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(OperationalStateEnum operationalState) {
    this.operationalState = operationalState;
  }

  public AppPkgNotification _links(AppPkgNotificationLinks _links) {
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
    public AppPkgNotificationLinks getLinks() {
    return _links;
  }

  public void setLinks(AppPkgNotificationLinks _links) {
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
    AppPkgNotification appPkgNotification = (AppPkgNotification) o;
    return Objects.equals(this.id, appPkgNotification.id) &&
        Objects.equals(this.notificationType, appPkgNotification.notificationType) &&
        Objects.equals(this.subscriptionId, appPkgNotification.subscriptionId) &&
        Objects.equals(this.timeStamp, appPkgNotification.timeStamp) &&
        Objects.equals(this.appPkgId, appPkgNotification.appPkgId) &&
        Objects.equals(this.appDId, appPkgNotification.appDId) &&
        Objects.equals(this.operationalState, appPkgNotification.operationalState) &&
        Objects.equals(this._links, appPkgNotification._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, notificationType, subscriptionId, timeStamp, appPkgId, appDId, operationalState, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppPkgNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    appPkgId: ").append(toIndentedString(appPkgId)).append("\n");
    sb.append("    appDId: ").append(toIndentedString(appDId)).append("\n");
    sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
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
