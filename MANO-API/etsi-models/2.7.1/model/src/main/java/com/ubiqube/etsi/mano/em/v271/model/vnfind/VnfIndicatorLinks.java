package com.ubiqube.etsi.mano.em.v271.model.vnfind;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.Link;
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

public class VnfIndicatorLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("vnfInstance")
  private Link vnfInstance = null;

  public VnfIndicatorLinks self(Link self) {
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

  public VnfIndicatorLinks vnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
    return this;
  }

  /**
   * Link to the related \"Individual VNF instance\" resource. 
   * @return vnfInstance
  **/
  @ApiModelProperty(required = true, value = "Link to the related \"Individual VNF instance\" resource. ")
  @NotNull

  @Valid

  public Link getVnfInstance() {
    return vnfInstance;
  }

  public void setVnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfIndicatorLinks vnfIndicatorLinks = (VnfIndicatorLinks) o;
    return Objects.equals(this.self, vnfIndicatorLinks.self) &&
        Objects.equals(this.vnfInstance, vnfIndicatorLinks.vnfInstance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, vnfInstance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfIndicatorLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    vnfInstance: ").append(toIndentedString(vnfInstance)).append("\n");
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

