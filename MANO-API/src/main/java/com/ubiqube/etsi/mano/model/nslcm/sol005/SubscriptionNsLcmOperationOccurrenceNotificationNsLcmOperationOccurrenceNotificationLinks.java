package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents the links to resources that a notification can contain. 
 **/
@ApiModel(description="This type represents the links to resources that a notification can contain. ")
public class SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf nsInstance = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf subscription = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceLinksSelf lcOpOcc = null;
 /**
   * Get nsInstance
   * @return nsInstance
  **/
  @JsonProperty("nsInstance")
  @NotNull
  public NsInstancesNsInstanceLinksSelf getNsInstance() {
    return nsInstance;
  }

  public void setNsInstance(NsInstancesNsInstanceLinksSelf nsInstance) {
    this.nsInstance = nsInstance;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks nsInstance(NsInstancesNsInstanceLinksSelf nsInstance) {
    this.nsInstance = nsInstance;
    return this;
  }

 /**
   * Get subscription
   * @return subscription
  **/
  @JsonProperty("subscription")
  public NsInstancesNsInstanceLinksSelf getSubscription() {
    return subscription;
  }

  public void setSubscription(NsInstancesNsInstanceLinksSelf subscription) {
    this.subscription = subscription;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks subscription(NsInstancesNsInstanceLinksSelf subscription) {
    this.subscription = subscription;
    return this;
  }

 /**
   * Get lcOpOcc
   * @return lcOpOcc
  **/
  @JsonProperty("lcOpOcc")
  public NsInstancesNsInstanceLinksSelf getLcOpOcc() {
    return lcOpOcc;
  }

  public void setLcOpOcc(NsInstancesNsInstanceLinksSelf lcOpOcc) {
    this.lcOpOcc = lcOpOcc;
  }

  public SubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks lcOpOcc(NsInstancesNsInstanceLinksSelf lcOpOcc) {
    this.lcOpOcc = lcOpOcc;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionNsLcmOperationOccurrenceNotificationNsLcmOperationOccurrenceNotificationLinks {\n");
    
    sb.append("    nsInstance: ").append(toIndentedString(nsInstance)).append("\n");
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
    sb.append("    lcOpOcc: ").append(toIndentedString(lcOpOcc)).append("\n");
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

