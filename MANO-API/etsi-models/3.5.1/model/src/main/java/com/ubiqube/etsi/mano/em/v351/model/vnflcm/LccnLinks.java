package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.NotificationLink;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the links to resources that a notification can contain. 
 */
@Schema(description = "This type represents the links to resources that a notification can contain. ")
@Validated


public class LccnLinks   {
  @JsonProperty("vnfInstance")
  private NotificationLink vnfInstance = null;

  @JsonProperty("subscription")
  private NotificationLink subscription = null;

  @JsonProperty("vnfLcmOpOcc")
  private NotificationLink vnfLcmOpOcc = null;

  public LccnLinks vnfInstance(NotificationLink vnfInstance) {
    this.vnfInstance = vnfInstance;
    return this;
  }

  /**
   * Get vnfInstance
   * @return vnfInstance
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public NotificationLink getVnfInstance() {
    return vnfInstance;
  }

  public void setVnfInstance(NotificationLink vnfInstance) {
    this.vnfInstance = vnfInstance;
  }

  public LccnLinks subscription(NotificationLink subscription) {
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

  public LccnLinks vnfLcmOpOcc(NotificationLink vnfLcmOpOcc) {
    this.vnfLcmOpOcc = vnfLcmOpOcc;
    return this;
  }

  /**
   * Get vnfLcmOpOcc
   * @return vnfLcmOpOcc
   **/
  @Schema(description = "")
  
    @Valid
    public NotificationLink getVnfLcmOpOcc() {
    return vnfLcmOpOcc;
  }

  public void setVnfLcmOpOcc(NotificationLink vnfLcmOpOcc) {
    this.vnfLcmOpOcc = vnfLcmOpOcc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LccnLinks lccnLinks = (LccnLinks) o;
    return Objects.equals(this.vnfInstance, lccnLinks.vnfInstance) &&
        Objects.equals(this.subscription, lccnLinks.subscription) &&
        Objects.equals(this.vnfLcmOpOcc, lccnLinks.vnfLcmOpOcc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstance, subscription, vnfLcmOpOcc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LccnLinks {\n");
    
    sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
    sb.append("    vnfLcmOpOcc: ").append(toIndentedString(vnfLcmOpOcc)).append("\n");
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
