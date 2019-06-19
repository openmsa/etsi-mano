package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * \"This type represents the links to resources that a PNFD management notification can contain.\"        
 **/
@ApiModel(description="\"This type represents the links to resources that a PNFD management notification can contain.\"        ")
public class SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotificationLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsDescriptorsNsdInfoLinksSelf pnfdInfo = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsDescriptorsNsdInfoLinksSelf subscription = null;
 /**
   * Get pnfdInfo
   * @return pnfdInfo
  **/
  @JsonProperty("pnfdInfo")
  @NotNull
  public NsDescriptorsNsdInfoLinksSelf getPnfdInfo() {
    return pnfdInfo;
  }

  public void setPnfdInfo(NsDescriptorsNsdInfoLinksSelf pnfdInfo) {
    this.pnfdInfo = pnfdInfo;
  }

  public SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotificationLinks pnfdInfo(NsDescriptorsNsdInfoLinksSelf pnfdInfo) {
    this.pnfdInfo = pnfdInfo;
    return this;
  }

 /**
   * Get subscription
   * @return subscription
  **/
  @JsonProperty("subscription")
  @NotNull
  public NsDescriptorsNsdInfoLinksSelf getSubscription() {
    return subscription;
  }

  public void setSubscription(NsDescriptorsNsdInfoLinksSelf subscription) {
    this.subscription = subscription;
  }

  public SubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotificationLinks subscription(NsDescriptorsNsdInfoLinksSelf subscription) {
    this.subscription = subscription;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdOnBoardingNotificationPnfdOnBoardingNotificationLinks {\n");
    
    sb.append("    pnfdInfo: ").append(toIndentedString(pnfdInfo)).append("\n");
    sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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

