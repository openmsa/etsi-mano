package com.ubiqube.etsi.mano.vnfm.v331.model.vnffm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnffm.FaultyResourceType;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnffm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the faulty virtual resources that have a negative impact on a VNF. 
 */
@Schema(description = "This type represents the faulty virtual resources that have a negative impact on a VNF. ")
@Validated


public class FaultyResourceInfo   {
  @JsonProperty("faultyResource")
  private ResourceHandle faultyResource = null;

  @JsonProperty("faultyResourceType")
  private FaultyResourceType faultyResourceType = null;

  public FaultyResourceInfo faultyResource(ResourceHandle faultyResource) {
    this.faultyResource = faultyResource;
    return this;
  }

  /**
   * Get faultyResource
   * @return faultyResource
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getFaultyResource() {
    return faultyResource;
  }

  public void setFaultyResource(ResourceHandle faultyResource) {
    this.faultyResource = faultyResource;
  }

  public FaultyResourceInfo faultyResourceType(FaultyResourceType faultyResourceType) {
    this.faultyResourceType = faultyResourceType;
    return this;
  }

  /**
   * Get faultyResourceType
   * @return faultyResourceType
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public FaultyResourceType getFaultyResourceType() {
    return faultyResourceType;
  }

  public void setFaultyResourceType(FaultyResourceType faultyResourceType) {
    this.faultyResourceType = faultyResourceType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FaultyResourceInfo faultyResourceInfo = (FaultyResourceInfo) o;
    return Objects.equals(this.faultyResource, faultyResourceInfo.faultyResource) &&
        Objects.equals(this.faultyResourceType, faultyResourceInfo.faultyResourceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(faultyResource, faultyResourceType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FaultyResourceInfo {\n");
    
    sb.append("    faultyResource: ").append(toIndentedString(faultyResource)).append("\n");
    sb.append("    faultyResourceType: ").append(toIndentedString(faultyResourceType)).append("\n");
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
