package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LocationConstraints;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the association of location constraints to a nested NS instance to be created according to a specific NS profile. It shall comply with the provisions defined in Table 6.5.3.96-1. 
 */
@Schema(description = "This type represents the association of location constraints to a nested NS instance to be created according to a specific NS profile. It shall comply with the provisions defined in Table 6.5.3.96-1. ")
@Validated


public class NestedNsLocationConstraint   {
  @JsonProperty("nsProfileId")
  private String nsProfileId = null;

  @JsonProperty("locationConstraints")
  private LocationConstraints locationConstraints = null;

  public NestedNsLocationConstraint nsProfileId(String nsProfileId) {
    this.nsProfileId = nsProfileId;
    return this;
  }

  /**
   * Get nsProfileId
   * @return nsProfileId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsProfileId() {
    return nsProfileId;
  }

  public void setNsProfileId(String nsProfileId) {
    this.nsProfileId = nsProfileId;
  }

  public NestedNsLocationConstraint locationConstraints(LocationConstraints locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }

  /**
   * Get locationConstraints
   * @return locationConstraints
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LocationConstraints getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(LocationConstraints locationConstraints) {
    this.locationConstraints = locationConstraints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NestedNsLocationConstraint nestedNsLocationConstraint = (NestedNsLocationConstraint) o;
    return Objects.equals(this.nsProfileId, nestedNsLocationConstraint.nsProfileId) &&
        Objects.equals(this.locationConstraints, nestedNsLocationConstraint.locationConstraints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsProfileId, locationConstraints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NestedNsLocationConstraint {\n");
    
    sb.append("    nsProfileId: ").append(toIndentedString(nsProfileId)).append("\n");
    sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
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
