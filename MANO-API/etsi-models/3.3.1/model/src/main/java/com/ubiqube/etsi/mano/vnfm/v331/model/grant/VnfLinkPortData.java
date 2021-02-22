package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an externally provided link port to be used to connect a VNFC connection point  to an exernally managed VL. 
 */
@Schema(description = "This type represents an externally provided link port to be used to connect a VNFC connection point  to an exernally managed VL. ")
@Validated


public class VnfLinkPortData   {
  @JsonProperty("vnfLinkPortId")
  private String vnfLinkPortId = null;

  @JsonProperty("resourceHandle")
  private ResourceHandle resourceHandle = null;

  public VnfLinkPortData vnfLinkPortId(String vnfLinkPortId) {
    this.vnfLinkPortId = vnfLinkPortId;
    return this;
  }

  /**
   * Get vnfLinkPortId
   * @return vnfLinkPortId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfLinkPortId() {
    return vnfLinkPortId;
  }

  public void setVnfLinkPortId(String vnfLinkPortId) {
    this.vnfLinkPortId = vnfLinkPortId;
  }

  public VnfLinkPortData resourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

  /**
   * Get resourceHandle
   * @return resourceHandle
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getResourceHandle() {
    return resourceHandle;
  }

  public void setResourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfLinkPortData vnfLinkPortData = (VnfLinkPortData) o;
    return Objects.equals(this.vnfLinkPortId, vnfLinkPortData.vnfLinkPortId) &&
        Objects.equals(this.resourceHandle, vnfLinkPortData.resourceHandle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfLinkPortId, resourceHandle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfLinkPortData {\n");
    
    sb.append("    vnfLinkPortId: ").append(toIndentedString(vnfLinkPortId)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
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
