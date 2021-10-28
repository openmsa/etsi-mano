package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ScaleByStepData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ScaleToLevelData;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents defines the information to scale a VNF instance  to a given level, or to scale a VNF instance by steps. NOTE 1: ETSI GS NFV-IFA 010 (available at https://www.etsi.org/deliver/etsi_gs/NFV-IFA/001_099/010/03.02.01_60/gs_NFV-IFA010v030201p.pdf) specifies that the lifecycle management operations that expandor contract a VNF instance include scale in, scale out, scale up and scale down. Vertical scaling (scale up, scale down) is not supported in the present document. NOTE 2: Either scaletoLevelData or scaleByStepData but not both shall be present. The scaleByStepData is used for scale out/in type of scaling, and the scaleToLevelData is used for scale to instantiation/scale level type of scaling. 
 */
@Schema(description = "This type represents defines the information to scale a VNF instance  to a given level, or to scale a VNF instance by steps. NOTE 1: ETSI GS NFV-IFA 010 (available at https://www.etsi.org/deliver/etsi_gs/NFV-IFA/001_099/010/03.02.01_60/gs_NFV-IFA010v030201p.pdf) specifies that the lifecycle management operations that expandor contract a VNF instance include scale in, scale out, scale up and scale down. Vertical scaling (scale up, scale down) is not supported in the present document. NOTE 2: Either scaletoLevelData or scaleByStepData but not both shall be present. The scaleByStepData is used for scale out/in type of scaling, and the scaleToLevelData is used for scale to instantiation/scale level type of scaling. ")
@Validated


public class ScaleVnfData  implements OneOfScaleVnfData {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  /**
   * Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT - SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of types actually supported depends on the capabilities of the VNF being managed. See note 1. 
   */
  public enum ScaleVnfTypeEnum {
    OUT("SCALE_OUT"),
    
    IN("SCALE_IN"),
    
    TO_INSTANTIATION_LEVEL("SCALE_TO_INSTANTIATION_LEVEL"),
    
    TO_SCALE_LEVEL_S_("SCALE_TO_SCALE_LEVEL(S)");

    private String value;

    ScaleVnfTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ScaleVnfTypeEnum fromValue(String text) {
      for (ScaleVnfTypeEnum b : ScaleVnfTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("scaleVnfType")
  private ScaleVnfTypeEnum scaleVnfType = null;

  @JsonProperty("scaleToLevelData")
  private ScaleToLevelData scaleToLevelData = null;

  @JsonProperty("scaleByStepData")
  private ScaleByStepData scaleByStepData = null;

  public ScaleVnfData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public ScaleVnfData scaleVnfType(ScaleVnfTypeEnum scaleVnfType) {
    this.scaleVnfType = scaleVnfType;
    return this;
  }

  /**
   * Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT - SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of types actually supported depends on the capabilities of the VNF being managed. See note 1. 
   * @return scaleVnfType
   **/
  @Schema(required = true, description = "Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT - SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of types actually supported depends on the capabilities of the VNF being managed. See note 1. ")
      @NotNull

    public ScaleVnfTypeEnum getScaleVnfType() {
    return scaleVnfType;
  }

  public void setScaleVnfType(ScaleVnfTypeEnum scaleVnfType) {
    this.scaleVnfType = scaleVnfType;
  }

  public ScaleVnfData scaleToLevelData(ScaleToLevelData scaleToLevelData) {
    this.scaleToLevelData = scaleToLevelData;
    return this;
  }

  /**
   * Get scaleToLevelData
   * @return scaleToLevelData
   **/
  @Schema(description = "")
  
    @Valid
    public ScaleToLevelData getScaleToLevelData() {
    return scaleToLevelData;
  }

  public void setScaleToLevelData(ScaleToLevelData scaleToLevelData) {
    this.scaleToLevelData = scaleToLevelData;
  }

  public ScaleVnfData scaleByStepData(ScaleByStepData scaleByStepData) {
    this.scaleByStepData = scaleByStepData;
    return this;
  }

  /**
   * Get scaleByStepData
   * @return scaleByStepData
   **/
  @Schema(description = "")
  
    @Valid
    public ScaleByStepData getScaleByStepData() {
    return scaleByStepData;
  }

  public void setScaleByStepData(ScaleByStepData scaleByStepData) {
    this.scaleByStepData = scaleByStepData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScaleVnfData scaleVnfData = (ScaleVnfData) o;
    return Objects.equals(this.vnfInstanceId, scaleVnfData.vnfInstanceId) &&
        Objects.equals(this.scaleVnfType, scaleVnfData.scaleVnfType) &&
        Objects.equals(this.scaleToLevelData, scaleVnfData.scaleToLevelData) &&
        Objects.equals(this.scaleByStepData, scaleVnfData.scaleByStepData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, scaleVnfType, scaleToLevelData, scaleByStepData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaleVnfData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    scaleVnfType: ").append(toIndentedString(scaleVnfType)).append("\n");
    sb.append("    scaleToLevelData: ").append(toIndentedString(scaleToLevelData)).append("\n");
    sb.append("    scaleByStepData: ").append(toIndentedString(scaleByStepData)).append("\n");
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
