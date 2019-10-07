package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VnfScaleInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class VnfScaleInfo   {
  @JsonProperty("aspectId")
  private String aspectId = null;

  @JsonProperty("scaleLevel")
  private Integer scaleLevel = null;

  public VnfScaleInfo aspectId(String aspectId) {
    this.aspectId = aspectId;
    return this;
  }

  /**
   * Identifier of the scaling aspect. 
   * @return aspectId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the scaling aspect. ")
  @NotNull


  public String getAspectId() {
    return aspectId;
  }

  public void setAspectId(String aspectId) {
    this.aspectId = aspectId;
  }

  public VnfScaleInfo scaleLevel(Integer scaleLevel) {
    this.scaleLevel = scaleLevel;
    return this;
  }

  /**
   * Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD. 
   * @return scaleLevel
  **/
  @ApiModelProperty(required = true, value = "Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD. ")
  @NotNull


  public Integer getScaleLevel() {
    return scaleLevel;
  }

  public void setScaleLevel(Integer scaleLevel) {
    this.scaleLevel = scaleLevel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfScaleInfo vnfScaleInfo = (VnfScaleInfo) o;
    return Objects.equals(this.aspectId, vnfScaleInfo.aspectId) &&
        Objects.equals(this.scaleLevel, vnfScaleInfo.scaleLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aspectId, scaleLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfScaleInfo {\n");
    
    sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
    sb.append("    scaleLevel: ").append(toIndentedString(scaleLevel)).append("\n");
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

