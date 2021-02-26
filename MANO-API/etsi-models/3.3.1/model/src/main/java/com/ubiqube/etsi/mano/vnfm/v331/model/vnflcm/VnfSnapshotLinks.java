package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated


public class VnfSnapshotLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("vnfStateSnapshot")
  private Link vnfStateSnapshot = null;

  public VnfSnapshotLinks self(Link self) {
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

  public VnfSnapshotLinks vnfStateSnapshot(Link vnfStateSnapshot) {
    this.vnfStateSnapshot = vnfStateSnapshot;
    return this;
  }

  /**
   * Get vnfStateSnapshot
   * @return vnfStateSnapshot
   **/
  @Schema(description = "")
  
    @Valid
    public Link getVnfStateSnapshot() {
    return vnfStateSnapshot;
  }

  public void setVnfStateSnapshot(Link vnfStateSnapshot) {
    this.vnfStateSnapshot = vnfStateSnapshot;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfSnapshotLinks vnfSnapshotLinks = (VnfSnapshotLinks) o;
    return Objects.equals(this.self, vnfSnapshotLinks.self) &&
        Objects.equals(this.vnfStateSnapshot, vnfSnapshotLinks.vnfStateSnapshot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, vnfStateSnapshot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfSnapshotLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    vnfStateSnapshot: ").append(toIndentedString(vnfStateSnapshot)).append("\n");
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
