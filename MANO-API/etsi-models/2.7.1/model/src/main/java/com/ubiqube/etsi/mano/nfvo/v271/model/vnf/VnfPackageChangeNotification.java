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
package com.ubiqube.etsi.mano.nfvo.v271.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.PackageChangeType;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.PkgmLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF package management notification, which informs the receiver of a change of the status in an on-boarded VNF package. Only changes in the \&quot;operationalState\&quot; attribute of an on-boarded VNF package and the deletion of the VNF package will be reported. Change in the \&quot;usageState\&quot; and \&quot;onboardingState\&quot; attributes are not reported. The notification shall comply with the provisions defined in Table 9.5.2.9-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO when there is a change in the status of an onboarded VNF package, as follows. • The \&quot;operationalState\&quot; attribute of a VNF package has changed, and the \&quot;onboardingState\&quot; attribute of the package has the value \&quot;ONBOARDED\&quot;. • The on-boarded VNF package has been deleted, and the \&quot;onboardingState\&quot; attribute of the deleted package had the value \&quot;ONBOARDED\&quot;. 
 */
@Schema(description = "This type represents a VNF package management notification, which informs the receiver of a change of the status in an on-boarded VNF package. Only changes in the \"operationalState\" attribute of an on-boarded VNF package and the deletion of the VNF package will be reported. Change in the \"usageState\" and \"onboardingState\" attributes are not reported. The notification shall comply with the provisions defined in Table 9.5.2.9-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO when there is a change in the status of an onboarded VNF package, as follows. • The \"operationalState\" attribute of a VNF package has changed, and the \"onboardingState\" attribute of the package has the value \"ONBOARDED\". • The on-boarded VNF package has been deleted, and the \"onboardingState\" attribute of the deleted package had the value \"ONBOARDED\". ")
@Validated


public class VnfPackageChangeNotification   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("notificationType")
  private String notificationType = null;

  @JsonProperty("subscriptionId")
  private String subscriptionId = null;

  @JsonProperty("timeStamp")
  private OffsetDateTime timeStamp = null;

  @JsonProperty("vnfPkgId")
  private String vnfPkgId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("changeType")
  private PackageChangeType changeType = null;

  @JsonProperty("operationalState")
  private PackageOperationalStateType operationalState = null;

  @JsonProperty("_links")
  private PkgmLinks _links = null;

  public VnfPackageChangeNotification id(String id) {
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

  public VnfPackageChangeNotification notificationType(String notificationType) {
    this.notificationType = notificationType;
    return this;
  }

  /**
   * Discriminator for the different notification types. Shall be set to \"VnfPackageChangeNotification\" for this notification type. 
   * @return notificationType
   **/
  @Schema(required = true, description = "Discriminator for the different notification types. Shall be set to \"VnfPackageChangeNotification\" for this notification type. ")
      @NotNull

    public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public VnfPackageChangeNotification subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * Get subscriptionId
   * @return subscriptionId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public VnfPackageChangeNotification timeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }

  /**
   * Get timeStamp
   * @return timeStamp
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public VnfPackageChangeNotification vnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

  /**
   * Get vnfPkgId
   * @return vnfPkgId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public VnfPackageChangeNotification vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfPackageChangeNotification changeType(PackageChangeType changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Get changeType
   * @return changeType
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public PackageChangeType getChangeType() {
    return changeType;
  }

  public void setChangeType(PackageChangeType changeType) {
    this.changeType = changeType;
  }

  public VnfPackageChangeNotification operationalState(PackageOperationalStateType operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  /**
   * Get operationalState
   * @return operationalState
   **/
  @Schema(description = "")
  
    @Valid
    public PackageOperationalStateType getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(PackageOperationalStateType operationalState) {
    this.operationalState = operationalState;
  }

  public VnfPackageChangeNotification _links(PkgmLinks _links) {
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
    public PkgmLinks getLinks() {
    return _links;
  }

  public void setLinks(PkgmLinks _links) {
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
    VnfPackageChangeNotification vnfPackageChangeNotification = (VnfPackageChangeNotification) o;
    return Objects.equals(this.id, vnfPackageChangeNotification.id) &&
        Objects.equals(this.notificationType, vnfPackageChangeNotification.notificationType) &&
        Objects.equals(this.subscriptionId, vnfPackageChangeNotification.subscriptionId) &&
        Objects.equals(this.timeStamp, vnfPackageChangeNotification.timeStamp) &&
        Objects.equals(this.vnfPkgId, vnfPackageChangeNotification.vnfPkgId) &&
        Objects.equals(this.vnfdId, vnfPackageChangeNotification.vnfdId) &&
        Objects.equals(this.changeType, vnfPackageChangeNotification.changeType) &&
        Objects.equals(this.operationalState, vnfPackageChangeNotification.operationalState) &&
        Objects.equals(this._links, vnfPackageChangeNotification._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, notificationType, subscriptionId, timeStamp, vnfPkgId, vnfdId, changeType, operationalState, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackageChangeNotification {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
    sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
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
