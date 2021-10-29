package com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the request parameters for creating a new \&quot;Individual VNF snapshot package\&quot; resource.  It shall comply with the provisions defined in table 11.5.2.2-1. 
 */
@Schema(description = "This type represents the request parameters for creating a new \"Individual VNF snapshot package\" resource.  It shall comply with the provisions defined in table 11.5.2.2-1. ")
@Validated


public class CreateVnfSnapshotPkgInfoRequest   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  public CreateVnfSnapshotPkgInfoRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name of the VNF snapshot package. 
   * @return name
   **/
  @Schema(required = true, description = "Human-readable name of the VNF snapshot package. ")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreateVnfSnapshotPkgInfoRequest userDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  /**
   * Get userDefinedData
   * @return userDefinedData
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateVnfSnapshotPkgInfoRequest createVnfSnapshotPkgInfoRequest = (CreateVnfSnapshotPkgInfoRequest) o;
    return Objects.equals(this.name, createVnfSnapshotPkgInfoRequest.name) &&
        Objects.equals(this.userDefinedData, createVnfSnapshotPkgInfoRequest.userDefinedData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, userDefinedData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateVnfSnapshotPkgInfoRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
