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
public class NsDescriptorsNsdInfoLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsDescriptorsNsdInfoLinksSelf self = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsDescriptorsNsdInfoLinksSelf nsdContent = null;
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

  public NsDescriptorsNsdInfoLinks self(NsDescriptorsNsdInfoLinksSelf self) {
    this.self = self;
    return this;
  }

 /**
   * Get nsdContent
   * @return nsdContent
  **/
  @JsonProperty("nsd_content")
  @NotNull
  public NsDescriptorsNsdInfoLinksSelf getNsdContent() {
    return nsdContent;
  }

  public void setNsdContent(NsDescriptorsNsdInfoLinksSelf nsdContent) {
    this.nsdContent = nsdContent;
  }

  public NsDescriptorsNsdInfoLinks nsdContent(NsDescriptorsNsdInfoLinksSelf nsdContent) {
    this.nsdContent = nsdContent;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsDescriptorsNsdInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    nsdContent: ").append(toIndentedString(nsdContent)).append("\n");
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

