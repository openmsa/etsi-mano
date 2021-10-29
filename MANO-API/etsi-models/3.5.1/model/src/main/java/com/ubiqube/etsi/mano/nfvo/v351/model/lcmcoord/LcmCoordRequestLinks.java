package com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.lcmcoord.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this request. 
 */
@Schema(description = "Links to resources related to this request. ")
@Validated


public class LcmCoordRequestLinks   {
  @JsonProperty("nsLcmOpOcc")
  private Link nsLcmOpOcc = null;

  @JsonProperty("nsInstance")
  private Link nsInstance = null;

  public LcmCoordRequestLinks nsLcmOpOcc(Link nsLcmOpOcc) {
    this.nsLcmOpOcc = nsLcmOpOcc;
    return this;
  }

  /**
   * Get nsLcmOpOcc
   * @return nsLcmOpOcc
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getNsLcmOpOcc() {
    return nsLcmOpOcc;
  }

  public void setNsLcmOpOcc(Link nsLcmOpOcc) {
    this.nsLcmOpOcc = nsLcmOpOcc;
  }

  public LcmCoordRequestLinks nsInstance(Link nsInstance) {
    this.nsInstance = nsInstance;
    return this;
  }

  /**
   * Get nsInstance
   * @return nsInstance
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getNsInstance() {
    return nsInstance;
  }

  public void setNsInstance(Link nsInstance) {
    this.nsInstance = nsInstance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LcmCoordRequestLinks lcmCoordRequestLinks = (LcmCoordRequestLinks) o;
    return Objects.equals(this.nsLcmOpOcc, lcmCoordRequestLinks.nsLcmOpOcc) &&
        Objects.equals(this.nsInstance, lcmCoordRequestLinks.nsInstance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsLcmOpOcc, nsInstance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LcmCoordRequestLinks {\n");
    
    sb.append("    nsLcmOpOcc: ").append(toIndentedString(nsLcmOpOcc)).append("\n");
    sb.append("    nsInstance: ").append(toIndentedString(nsInstance)).append("\n");
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
