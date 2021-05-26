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
package com.ubiqube.etsi.mano.vnfm.v281.model.vnfpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnfpkgm.PkgmLinks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF package management notification, which informs the receiver that the onboarding process of a VNF package is complete and the package is ready for use. The notification shall be triggered by the NFVO when the \&quot;onboardingState\&quot; attribute of a new VNF package has changed to \&quot;ONBOARDED\&quot;. 
 */
@ApiModel(description = "This type represents a VNF package management notification, which informs the receiver that the onboarding process of a VNF package is complete and the package is ready for use. The notification shall be triggered by the NFVO when the \"onboardingState\" attribute of a new VNF package has changed to \"ONBOARDED\". ")
@Validated

public class VnfPackageOnboardingNotification   {
  @JsonProperty("id")
  private String id = null;

  /**
   * Discriminator for the different notification types. Shall be set to \"VnfPackageOnboardingNotification\" for this notification type. 
   */
  public enum NotificationTypeEnum {
    VNFPACKAGEONBOARDINGNOTIFICATION("VnfPackageOnboardingNotification");

    private String value;

    NotificationTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NotificationTypeEnum fromValue(String text) {
      for (NotificationTypeEnum b : NotificationTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("notificationType")
  private NotificationTypeEnum notificationType = null;

  @JsonProperty("subscriptionId")
  private String subscriptionId = null;

  @JsonProperty("timeStamp")
  private String timeStamp = null;

  @JsonProperty("vnfPkgId")
  private String vnfPkgId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfmInfo")
  @Valid
  private List<String> vnfmInfo = new ArrayList<>();

  @JsonProperty("_links")
  private PkgmLinks links = null;

  public VnfPackageOnboardingNotification id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfPackageOnboardingNotification notificationType(NotificationTypeEnum notificationType) {
    this.notificationType = notificationType;
    return this;
  }

  /**
   * Discriminator for the different notification types. Shall be set to \"VnfPackageOnboardingNotification\" for this notification type. 
   * @return notificationType
  **/
  @ApiModelProperty(required = true, value = "Discriminator for the different notification types. Shall be set to \"VnfPackageOnboardingNotification\" for this notification type. ")
  @NotNull


  public NotificationTypeEnum getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(NotificationTypeEnum notificationType) {
    this.notificationType = notificationType;
  }

  public VnfPackageOnboardingNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * Identifier of the subscription that this notification relates to. 
   * @return subscriptionId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the subscription that this notification relates to. ")
  @NotNull


  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public VnfPackageOnboardingNotification timeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Date-time of the generation of the notification. 
   * @return timeStamp
  **/
  @ApiModelProperty(required = true, value = "Date-time of the generation of the notification. ")
  @NotNull


  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public VnfPackageOnboardingNotification vnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

  /**
   * Identifier of the VNF package. This identifier is allocated by the NFVO. Its value is the same as the value of the \"id\" attribute of the related \"Individual VNF package\" resource. 
   * @return vnfPkgId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNF package. This identifier is allocated by the NFVO. Its value is the same as the value of the \"id\" attribute of the related \"Individual VNF package\" resource. ")
  @NotNull


  public String getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public VnfPackageOnboardingNotification vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It's copied from the VNFD of the on-boarded VNF package. 
   * @return vnfdId
  **/
  @ApiModelProperty(required = true, value = "This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It's copied from the VNFD of the on-boarded VNF package. ")
  @NotNull


  public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfPackageOnboardingNotification vnfmInfo(List<String> vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
    return this;
  }

  public VnfPackageOnboardingNotification addVnfmInfoItem(String vnfmInfoItem) {
    this.vnfmInfo.add(vnfmInfoItem);
    return this;
  }

  /**
   * Specifies VNFMs compatible with the VNF. This information is copied from the VNFD. See table 10.5.2.2-1. 
   * @return vnfmInfo
  **/
  @ApiModelProperty(required = true, value = "Specifies VNFMs compatible with the VNF. This information is copied from the VNFD. See table 10.5.2.2-1. ")
  @NotNull


  public List<String> getVnfmInfo() {
    return vnfmInfo;
  }

  public void setVnfmInfo(List<String> vnfmInfo) {
    this.vnfmInfo = vnfmInfo;
  }

  public VnfPackageOnboardingNotification links(PkgmLinks links) {
    this.links = links;
    return this;
  }

  /**
   * Links to resources related to this notification. 
   * @return links
  **/
  @ApiModelProperty(required = true, value = "Links to resources related to this notification. ")
  @NotNull

  @Valid

  public PkgmLinks getLinks() {
    return links;
  }

  public void setLinks(PkgmLinks links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfPackageOnboardingNotification vnfPackageOnboardingNotification = (VnfPackageOnboardingNotification) o;
    return Objects.equals(this.id, vnfPackageOnboardingNotification.id) &&
        Objects.equals(this.notificationType, vnfPackageOnboardingNotification.notificationType) &&
        Objects.equals(this.subscriptionId, vnfPackageOnboardingNotification.subscriptionId) &&
        Objects.equals(this.timeStamp, vnfPackageOnboardingNotification.timeStamp) &&
        Objects.equals(this.vnfPkgId, vnfPackageOnboardingNotification.vnfPkgId) &&
        Objects.equals(this.vnfdId, vnfPackageOnboardingNotification.vnfdId) &&
        Objects.equals(this.vnfmInfo, vnfPackageOnboardingNotification.vnfmInfo) &&
        Objects.equals(this.links, vnfPackageOnboardingNotification.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, notificationType, subscriptionId, timeStamp, vnfPkgId, vnfdId, vnfmInfo, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackageOnboardingNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfmInfo: ").append(toIndentedString(vnfmInfo)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

