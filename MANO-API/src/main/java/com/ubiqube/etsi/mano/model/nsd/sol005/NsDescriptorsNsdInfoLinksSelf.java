package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * This type represents a link to a resource. 
 **/
@ApiModel(description="This type represents a link to a resource. ")
public class NsDescriptorsNsdInfoLinksSelf  {
  
  @ApiModelProperty(required = true, value = "URI of the referenced resource. ")
 /**
   * URI of the referenced resource. 
  **/
  private String href = null;
 /**
   * URI of the referenced resource. 
   * @return href
  **/
  @JsonProperty("href")
  @NotNull
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public NsDescriptorsNsdInfoLinksSelf href(String href) {
    this.href = href;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsDescriptorsNsdInfoLinksSelf {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
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

