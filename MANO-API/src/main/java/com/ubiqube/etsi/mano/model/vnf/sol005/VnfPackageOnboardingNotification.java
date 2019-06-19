package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class VnfPackageOnboardingNotification  {
  
  @ApiModelProperty(value = "")
  @Valid
  private NotificationVnfPackageOnboardingNotification vnfPackageOnboardingNotification = null;
 /**
   * Get vnfPackageOnboardingNotification
   * @return vnfPackageOnboardingNotification
  **/
  @JsonProperty("VnfPackageOnboardingNotification")
  public NotificationVnfPackageOnboardingNotification getVnfPackageOnboardingNotification() {
    return vnfPackageOnboardingNotification;
  }

  public void setVnfPackageOnboardingNotification(NotificationVnfPackageOnboardingNotification vnfPackageOnboardingNotification) {
    this.vnfPackageOnboardingNotification = vnfPackageOnboardingNotification;
  }

  public VnfPackageOnboardingNotification vnfPackageOnboardingNotification(NotificationVnfPackageOnboardingNotification vnfPackageOnboardingNotification) {
    this.vnfPackageOnboardingNotification = vnfPackageOnboardingNotification;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackageOnboardingNotification {\n");
    
    sb.append("    vnfPackageOnboardingNotification: ").append(toIndentedString(vnfPackageOnboardingNotification)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

