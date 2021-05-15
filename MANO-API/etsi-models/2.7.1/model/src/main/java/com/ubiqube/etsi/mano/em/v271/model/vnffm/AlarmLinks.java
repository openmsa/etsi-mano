package com.ubiqube.etsi.mano.em.v271.model.vnffm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.Link;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links for this resource. 
 */
@ApiModel(description = "Links for this resource. ")
@Validated

public class AlarmLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("objectInstance")
  private Link objectInstance = null;

  public AlarmLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * URI of this resource. 
   * @return self
  **/
  @ApiModelProperty(required = true, value = "URI of this resource. ")
  @NotNull

  @Valid

  public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public AlarmLinks objectInstance(Link objectInstance) {
    this.objectInstance = objectInstance;
    return this;
  }

  /**
   * Link to the resource representing the VNF instance to which the notified alarm is correlated. Shall be present if the VNF instance information is accessible as a resource. 
   * @return objectInstance
  **/
  @ApiModelProperty(value = "Link to the resource representing the VNF instance to which the notified alarm is correlated. Shall be present if the VNF instance information is accessible as a resource. ")

  @Valid

  public Link getObjectInstance() {
    return objectInstance;
  }

  public void setObjectInstance(Link objectInstance) {
    this.objectInstance = objectInstance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlarmLinks alarmLinks = (AlarmLinks) o;
    return Objects.equals(this.self, alarmLinks.self) &&
        Objects.equals(this.objectInstance, alarmLinks.objectInstance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, objectInstance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlarmLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    objectInstance: ").append(toIndentedString(objectInstance)).append("\n");
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

