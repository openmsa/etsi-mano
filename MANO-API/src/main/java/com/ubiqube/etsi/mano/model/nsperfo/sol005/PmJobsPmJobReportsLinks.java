package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * Links for this resource. 
 **/
@ApiModel(description="Links for this resource. ")
public class PmJobsPmJobReportsLinks  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private PmJobsPmJobReportsLinksSelf self = null;

  @ApiModelProperty(value = "Links to resources representing the NS instances for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. ")
  @Valid
 /**
   * Links to resources representing the NS instances for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. 
  **/
  private List<PmJobsPmJobReportsLinksSelf> objects = null;
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

  public PmJobsPmJobReportsLinks self(PmJobsPmJobReportsLinksSelf self) {
    this.self = self;
    return this;
  }

 /**
   * Links to resources representing the NS instances for which performance information is collected. Shall be present if the NS instance information is accessible as a resource. 
   * @return objects
  **/
  @JsonProperty("objects")
  public List<PmJobsPmJobReportsLinksSelf> getObjects() {
    return objects;
  }

  public void setObjects(List<PmJobsPmJobReportsLinksSelf> objects) {
    this.objects = objects;
  }

  public PmJobsPmJobReportsLinks objects(List<PmJobsPmJobReportsLinksSelf> objects) {
    this.objects = objects;
    return this;
  }

  public PmJobsPmJobReportsLinks addObjectsItem(PmJobsPmJobReportsLinksSelf objectsItem) {
    this.objects.add(objectsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPmJobReportsLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
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

