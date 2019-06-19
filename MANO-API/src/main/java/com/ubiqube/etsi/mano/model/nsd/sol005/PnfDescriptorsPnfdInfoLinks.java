package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * \"Links to resources related to this resource.\" 
 **/
@ApiModel(description="\"Links to resources related to this resource.\" ")
public class PnfDescriptorsPnfdInfoLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsDescriptorsNsdInfoLinksSelf self = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsDescriptorsNsdInfoLinksSelf pnfdContent = null;
 /**
   * Get self
   * @return self
  **/
  @JsonProperty("self")
  @NotNull
  public NsDescriptorsNsdInfoLinksSelf getSelf() {
    return self;
  }

  public void setSelf(NsDescriptorsNsdInfoLinksSelf self) {
    this.self = self;
  }

  public PnfDescriptorsPnfdInfoLinks self(NsDescriptorsNsdInfoLinksSelf self) {
    this.self = self;
    return this;
  }

 /**
   * Get pnfdContent
   * @return pnfdContent
  **/
  @JsonProperty("pnfd_content")
  @NotNull
  public NsDescriptorsNsdInfoLinksSelf getPnfdContent() {
    return pnfdContent;
  }

  public void setPnfdContent(NsDescriptorsNsdInfoLinksSelf pnfdContent) {
    this.pnfdContent = pnfdContent;
  }

  public PnfDescriptorsPnfdInfoLinks pnfdContent(NsDescriptorsNsdInfoLinksSelf pnfdContent) {
    this.pnfdContent = pnfdContent;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfDescriptorsPnfdInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    pnfdContent: ").append(toIndentedString(pnfdContent)).append("\n");
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

