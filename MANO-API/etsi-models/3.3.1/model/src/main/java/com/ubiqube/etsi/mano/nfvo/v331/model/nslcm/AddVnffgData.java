package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type specifies the parameters used for the creation of a new VNFFG instance. It shall comply with the provisions defined in Table 6.5.3.36-1. 
 */
@Schema(description = "This type specifies the parameters used for the creation of a new VNFFG instance. It shall comply with the provisions defined in Table 6.5.3.36-1. ")
@Validated


public class AddVnffgData   {
  @JsonProperty("targetNsInstanceId")
  private String targetNsInstanceId = null;

  @JsonProperty("vnffgName")
  private String vnffgName = null;

  @JsonProperty("description")
  private String description = null;

  public AddVnffgData targetNsInstanceId(String targetNsInstanceId) {
    this.targetNsInstanceId = targetNsInstanceId;
    return this;
  }

  /**
   * Get targetNsInstanceId
   * @return targetNsInstanceId
   **/
  @Schema(description = "")
  
    public String getTargetNsInstanceId() {
    return targetNsInstanceId;
  }

  public void setTargetNsInstanceId(String targetNsInstanceId) {
    this.targetNsInstanceId = targetNsInstanceId;
  }

  public AddVnffgData vnffgName(String vnffgName) {
    this.vnffgName = vnffgName;
    return this;
  }

  /**
   * Human readable name for the VNFFG. 
   * @return vnffgName
   **/
  @Schema(required = true, description = "Human readable name for the VNFFG. ")
      @NotNull

    public String getVnffgName() {
    return vnffgName;
  }

  public void setVnffgName(String vnffgName) {
    this.vnffgName = vnffgName;
  }

  public AddVnffgData description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Human readable description for the VNFFG. 
   * @return description
   **/
  @Schema(required = true, description = "Human readable description for the VNFFG. ")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddVnffgData addVnffgData = (AddVnffgData) o;
    return Objects.equals(this.targetNsInstanceId, addVnffgData.targetNsInstanceId) &&
        Objects.equals(this.vnffgName, addVnffgData.vnffgName) &&
        Objects.equals(this.description, addVnffgData.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(targetNsInstanceId, vnffgName, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddVnffgData {\n");
    
    sb.append("    targetNsInstanceId: ").append(toIndentedString(targetNsInstanceId)).append("\n");
    sb.append("    vnffgName: ").append(toIndentedString(vnffgName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
