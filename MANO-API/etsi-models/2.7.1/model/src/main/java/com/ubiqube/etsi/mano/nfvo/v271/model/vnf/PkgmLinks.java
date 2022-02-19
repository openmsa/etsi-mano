package com.ubiqube.etsi.mano.nfvo.v271.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.NotificationLink;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the links to resources that a VNF package management notification can contain.   
 */
@Schema(description = "This type represents the links to resources that a VNF package management notification can contain.   ")
@Validated


public class PkgmLinks   {
  @JsonProperty("vnfPackage")
  private NotificationLink vnfPackage = null;

  @JsonProperty("subscription")
  private NotificationLink subscription = null;

  public PkgmLinks vnfPackage(NotificationLink vnfPackage) {
    this.vnfPackage = vnfPackage;
    return this;
  }

  /**
   * Get vnfPackage
   * @return vnfPackage
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public NotificationLink getVnfPackage() {
    return vnfPackage;
  }

  public void setVnfPackage(NotificationLink vnfPackage) {
    this.vnfPackage = vnfPackage;
  }

  public PkgmLinks subscription(NotificationLink subscription) {
    this.subscription = subscription;
    return this;
  }

  /**
   * Get subscription
   * @return subscription
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public NotificationLink getSubscription() {
    return subscription;
  }

  public void setSubscription(NotificationLink subscription) {
    this.subscription = subscription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PkgmLinks pkgmLinks = (PkgmLinks) o;
    return Objects.equals(this.vnfPackage, pkgmLinks.vnfPackage) &&
        Objects.equals(this.subscription, pkgmLinks.subscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfPackage, subscription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmLinks {\n");
    
    sb.append("    vnfPackage: ").append(toIndentedString(vnfPackage)).append("\n");
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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
