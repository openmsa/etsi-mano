/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents a threshold. 
 **/
@ApiModel(description="This type represents a threshold. ")
public class ThresholdsThreshold  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String objectInstanceId = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private ThresholdsThresholdCriteria criteria = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private ThresholdsThresholdLinks links = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ThresholdsThreshold id(String id) {
    this.id = id;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return objectInstanceId
  **/
  @JsonProperty("objectInstanceId")
  @NotNull
  public String getObjectInstanceId() {
    return objectInstanceId;
  }

  public void setObjectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
  }

  public ThresholdsThreshold objectInstanceId(String objectInstanceId) {
    this.objectInstanceId = objectInstanceId;
    return this;
  }

 /**
   * Get criteria
   * @return criteria
  **/
  @JsonProperty("criteria")
  @NotNull
  public ThresholdsThresholdCriteria getCriteria() {
    return criteria;
  }

  public void setCriteria(ThresholdsThresholdCriteria criteria) {
    this.criteria = criteria;
  }

  public ThresholdsThreshold criteria(ThresholdsThresholdCriteria criteria) {
    this.criteria = criteria;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public ThresholdsThresholdLinks getLinks() {
    return links;
  }

  public void setLinks(ThresholdsThresholdLinks links) {
    this.links = links;
  }

  public ThresholdsThreshold links(ThresholdsThresholdLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThresholdsThreshold {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    objectInstanceId: ").append(toIndentedString(objectInstanceId)).append("\n");
    sb.append("    criteria: ").append(toIndentedString(criteria)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

