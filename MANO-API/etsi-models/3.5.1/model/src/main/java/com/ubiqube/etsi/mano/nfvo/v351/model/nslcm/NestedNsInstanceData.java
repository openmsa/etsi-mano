package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type specifies an existing nested NS instance to be used in the NS instance  and if needed, the NsProfile to use for this nested NS instance.  It shall comply with the provisions defined in Table 6.5.3.19a-1. 
 */
@Schema(description = "This type specifies an existing nested NS instance to be used in the NS instance  and if needed, the NsProfile to use for this nested NS instance.  It shall comply with the provisions defined in Table 6.5.3.19a-1. ")
@Validated


public class NestedNsInstanceData   {
  @JsonProperty("nestedNsInstanceId")
  private String nestedNsInstanceId = null;

  @JsonProperty("nsProfileId")
  private String nsProfileId = null;

  public NestedNsInstanceData nestedNsInstanceId(String nestedNsInstanceId) {
    this.nestedNsInstanceId = nestedNsInstanceId;
    return this;
  }

  /**
   * Get nestedNsInstanceId
   * @return nestedNsInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNestedNsInstanceId() {
    return nestedNsInstanceId;
  }

  public void setNestedNsInstanceId(String nestedNsInstanceId) {
    this.nestedNsInstanceId = nestedNsInstanceId;
  }

  public NestedNsInstanceData nsProfileId(String nsProfileId) {
    this.nsProfileId = nsProfileId;
    return this;
  }

  /**
   * Get nsProfileId
   * @return nsProfileId
   **/
  @Schema(description = "")
  
    public String getNsProfileId() {
    return nsProfileId;
  }

  public void setNsProfileId(String nsProfileId) {
    this.nsProfileId = nsProfileId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NestedNsInstanceData nestedNsInstanceData = (NestedNsInstanceData) o;
    return Objects.equals(this.nestedNsInstanceId, nestedNsInstanceData.nestedNsInstanceId) &&
        Objects.equals(this.nsProfileId, nestedNsInstanceData.nsProfileId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nestedNsInstanceId, nsProfileId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NestedNsInstanceData {\n");
    
    sb.append("    nestedNsInstanceId: ").append(toIndentedString(nestedNsInstanceId)).append("\n");
    sb.append("    nsProfileId: ").append(toIndentedString(nsProfileId)).append("\n");
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
