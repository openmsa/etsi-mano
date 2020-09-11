package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Links for this resource. 
 **/
@ApiModel(description="Links for this resource. ")
public class ThresholdsThresholdLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private PmJobsPmJobReportsLinksSelf self = null;

  @ApiModelProperty(value = "Link to a resource representing the NS instance for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. ")
 /**
   * Link to a resource representing the NS instance for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. 
  **/
  private Object object = null;
 /**
   * Get self
   * @return self
  **/
  @JsonProperty("self")
  @NotNull
  public PmJobsPmJobReportsLinksSelf getSelf() {
    return self;
  }

  public void setSelf(PmJobsPmJobReportsLinksSelf self) {
    this.self = self;
  }

  public ThresholdsThresholdLinks self(PmJobsPmJobReportsLinksSelf self) {
    this.self = self;
    return this;
  }

 /**
   * Link to a resource representing the NS instance for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. 
   * @return object
  **/
  @JsonProperty("object")
  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

  public ThresholdsThresholdLinks object(Object object) {
    this.object = object;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThresholdsThresholdLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    object: ").append(toIndentedString(object)).append("\n");
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

