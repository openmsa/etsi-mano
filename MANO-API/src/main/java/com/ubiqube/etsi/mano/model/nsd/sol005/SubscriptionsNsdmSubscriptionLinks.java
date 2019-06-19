package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

/**
  * Links to resources related to this resource. 
 **/
@ApiModel(description="Links to resources related to this resource. ")
public class SubscriptionsNsdmSubscriptionLinks  {
  
  @ApiModelProperty(value = "")
  @Valid
  private NsDescriptorsNsdInfoLinksSelf self = null;
 /**
   * Get self
   * @return self
  **/
  @JsonProperty("self")
  public NsDescriptorsNsdInfoLinksSelf getSelf() {
    return self;
  }

  public void setSelf(NsDescriptorsNsdInfoLinksSelf self) {
    this.self = self;
  }

  public SubscriptionsNsdmSubscriptionLinks self(NsDescriptorsNsdInfoLinksSelf self) {
    this.self = self;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsNsdmSubscriptionLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
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

