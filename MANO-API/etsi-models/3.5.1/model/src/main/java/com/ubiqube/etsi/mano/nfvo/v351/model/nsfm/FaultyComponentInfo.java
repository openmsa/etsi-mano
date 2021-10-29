package com.ubiqube.etsi.mano.nfvo.v351.model.nsfm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the faulty component that has a negative impact on an NS.  It shall comply with the provisions defined in Table 8.5.3.4-1. NOTE: At least one of the attributes shall be present. 
 */
@Schema(description = "This type represents the faulty component that has a negative impact on an NS.  It shall comply with the provisions defined in Table 8.5.3.4-1. NOTE: At least one of the attributes shall be present. ")
@Validated


public class FaultyComponentInfo   {
  @JsonProperty("faultyNestedNsInstanceId")
  private String faultyNestedNsInstanceId = null;

  @JsonProperty("faultyResourceType")
  private String faultyResourceType = null;

  @JsonProperty("faultyNsVirtualLinkInstanceId")
  private String faultyNsVirtualLinkInstanceId = null;

  public FaultyComponentInfo faultyNestedNsInstanceId(String faultyNestedNsInstanceId) {
    this.faultyNestedNsInstanceId = faultyNestedNsInstanceId;
    return this;
  }

  /**
   * Get faultyNestedNsInstanceId
   * @return faultyNestedNsInstanceId
   **/
  @Schema(description = "")
  
    public String getFaultyNestedNsInstanceId() {
    return faultyNestedNsInstanceId;
  }

  public void setFaultyNestedNsInstanceId(String faultyNestedNsInstanceId) {
    this.faultyNestedNsInstanceId = faultyNestedNsInstanceId;
  }

  public FaultyComponentInfo faultyResourceType(String faultyResourceType) {
    this.faultyResourceType = faultyResourceType;
    return this;
  }

  /**
   * Get faultyResourceType
   * @return faultyResourceType
   **/
  @Schema(description = "")
  
    public String getFaultyResourceType() {
    return faultyResourceType;
  }

  public void setFaultyResourceType(String faultyResourceType) {
    this.faultyResourceType = faultyResourceType;
  }

  public FaultyComponentInfo faultyNsVirtualLinkInstanceId(String faultyNsVirtualLinkInstanceId) {
    this.faultyNsVirtualLinkInstanceId = faultyNsVirtualLinkInstanceId;
    return this;
  }

  /**
   * Get faultyNsVirtualLinkInstanceId
   * @return faultyNsVirtualLinkInstanceId
   **/
  @Schema(description = "")
  
    public String getFaultyNsVirtualLinkInstanceId() {
    return faultyNsVirtualLinkInstanceId;
  }

  public void setFaultyNsVirtualLinkInstanceId(String faultyNsVirtualLinkInstanceId) {
    this.faultyNsVirtualLinkInstanceId = faultyNsVirtualLinkInstanceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FaultyComponentInfo faultyComponentInfo = (FaultyComponentInfo) o;
    return Objects.equals(this.faultyNestedNsInstanceId, faultyComponentInfo.faultyNestedNsInstanceId) &&
        Objects.equals(this.faultyResourceType, faultyComponentInfo.faultyResourceType) &&
        Objects.equals(this.faultyNsVirtualLinkInstanceId, faultyComponentInfo.faultyNsVirtualLinkInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(faultyNestedNsInstanceId, faultyResourceType, faultyNsVirtualLinkInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FaultyComponentInfo {\n");
    
    sb.append("    faultyNestedNsInstanceId: ").append(toIndentedString(faultyNestedNsInstanceId)).append("\n");
    sb.append("    faultyResourceType: ").append(toIndentedString(faultyResourceType)).append("\n");
    sb.append("    faultyNsVirtualLinkInstanceId: ").append(toIndentedString(faultyNsVirtualLinkInstanceId)).append("\n");
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
