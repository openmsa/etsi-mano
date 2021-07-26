/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ScaleNsData;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ScaleVnfData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a request for the scale NS operation. Either the parameter scaleNsData or the parameter scaleVnfData, but not both shall be provided 
 */
@Schema(description = "This type represents a request for the scale NS operation. Either the parameter scaleNsData or the parameter scaleVnfData, but not both shall be provided ")
@Validated


public class ScaleNsRequest  implements OneOfScaleNsRequest {
  /**
   * Indicates the type of scaling to be performed. Possible values: - SCALE_NS - SCALE_VNF 
   */
  public enum ScaleTypeEnum {
    NS("SCALE_NS"),
    
    VNF("SCALE_VNF");

    private String value;

    ScaleTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ScaleTypeEnum fromValue(String text) {
      for (ScaleTypeEnum b : ScaleTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("scaleType")
  private ScaleTypeEnum scaleType = null;

  @JsonProperty("scaleNsData")
  private ScaleNsData scaleNsData = null;

  @JsonProperty("scaleVnfData")
  @Valid
  private List<ScaleVnfData> scaleVnfData = null;

  @JsonProperty("scaleTime")
  private OffsetDateTime scaleTime = null;

  public ScaleNsRequest scaleType(ScaleTypeEnum scaleType) {
    this.scaleType = scaleType;
    return this;
  }

  /**
   * Indicates the type of scaling to be performed. Possible values: - SCALE_NS - SCALE_VNF 
   * @return scaleType
   **/
  @Schema(required = true, description = "Indicates the type of scaling to be performed. Possible values: - SCALE_NS - SCALE_VNF ")
      @NotNull

    public ScaleTypeEnum getScaleType() {
    return scaleType;
  }

  public void setScaleType(ScaleTypeEnum scaleType) {
    this.scaleType = scaleType;
  }

  public ScaleNsRequest scaleNsData(ScaleNsData scaleNsData) {
    this.scaleNsData = scaleNsData;
    return this;
  }

  /**
   * Get scaleNsData
   * @return scaleNsData
   **/
  @Schema(description = "")
  
    @Valid
    public ScaleNsData getScaleNsData() {
    return scaleNsData;
  }

  public void setScaleNsData(ScaleNsData scaleNsData) {
    this.scaleNsData = scaleNsData;
  }

  public ScaleNsRequest scaleVnfData(List<ScaleVnfData> scaleVnfData) {
    this.scaleVnfData = scaleVnfData;
    return this;
  }

  public ScaleNsRequest addScaleVnfDataItem(ScaleVnfData scaleVnfDataItem) {
    if (this.scaleVnfData == null) {
      this.scaleVnfData = new ArrayList<>();
    }
    this.scaleVnfData.add(scaleVnfDataItem);
    return this;
  }

  /**
   * The necessary information to scale the referenced NS instance. It shall be present when scaleType = SCALE_VNF. 
   * @return scaleVnfData
   **/
  @Schema(description = "The necessary information to scale the referenced NS instance. It shall be present when scaleType = SCALE_VNF. ")
      @Valid
    public List<ScaleVnfData> getScaleVnfData() {
    return scaleVnfData;
  }

  public void setScaleVnfData(List<ScaleVnfData> scaleVnfData) {
    this.scaleVnfData = scaleVnfData;
  }

  public ScaleNsRequest scaleTime(OffsetDateTime scaleTime) {
    this.scaleTime = scaleTime;
    return this;
  }

  /**
   * Get scaleTime
   * @return scaleTime
   **/
  @Schema(description = "")
  
    @Valid
    public OffsetDateTime getScaleTime() {
    return scaleTime;
  }

  public void setScaleTime(OffsetDateTime scaleTime) {
    this.scaleTime = scaleTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScaleNsRequest scaleNsRequest = (ScaleNsRequest) o;
    return Objects.equals(this.scaleType, scaleNsRequest.scaleType) &&
        Objects.equals(this.scaleNsData, scaleNsRequest.scaleNsData) &&
        Objects.equals(this.scaleVnfData, scaleNsRequest.scaleVnfData) &&
        Objects.equals(this.scaleTime, scaleNsRequest.scaleTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scaleType, scaleNsData, scaleVnfData, scaleTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaleNsRequest {\n");
    
    sb.append("    scaleType: ").append(toIndentedString(scaleType)).append("\n");
    sb.append("    scaleNsData: ").append(toIndentedString(scaleNsData)).append("\n");
    sb.append("    scaleVnfData: ").append(toIndentedString(scaleVnfData)).append("\n");
    sb.append("    scaleTime: ").append(toIndentedString(scaleTime)).append("\n");
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
