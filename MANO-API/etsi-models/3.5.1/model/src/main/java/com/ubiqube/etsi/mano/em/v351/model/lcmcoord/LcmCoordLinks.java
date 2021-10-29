package com.ubiqube.etsi.mano.em.v351.model.lcmcoord;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated


public class LcmCoordLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("vnfLcmOpOcc")
  private Link vnfLcmOpOcc = null;

  @JsonProperty("vnfInstance")
  private Link vnfInstance = null;

  public LcmCoordLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public LcmCoordLinks vnfLcmOpOcc(Link vnfLcmOpOcc) {
    this.vnfLcmOpOcc = vnfLcmOpOcc;
    return this;
  }

  /**
   * Get vnfLcmOpOcc
   * @return vnfLcmOpOcc
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getVnfLcmOpOcc() {
    return vnfLcmOpOcc;
  }

  public void setVnfLcmOpOcc(Link vnfLcmOpOcc) {
    this.vnfLcmOpOcc = vnfLcmOpOcc;
  }

  public LcmCoordLinks vnfInstance(Link vnfInstance) {
    this.vnfInstance = vnfInstance;
    return this;
  }

  /**
   * Get vnfInstance
   * @return vnfInstance
   **/
  @Schema(required = true, description = "")
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
    LcmCoordLinks lcmCoordLinks = (LcmCoordLinks) o;
    return Objects.equals(this.self, lcmCoordLinks.self) &&
        Objects.equals(this.vnfLcmOpOcc, lcmCoordLinks.vnfLcmOpOcc) &&
        Objects.equals(this.vnfInstance, lcmCoordLinks.vnfInstance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, vnfLcmOpOcc, vnfInstance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LcmCoordLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    vnfLcmOpOcc: ").append(toIndentedString(vnfLcmOpOcc)).append("\n");
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
