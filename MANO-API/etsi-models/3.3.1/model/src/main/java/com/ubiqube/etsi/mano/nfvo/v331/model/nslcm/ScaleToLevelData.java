package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.VnfScaleInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type describes the information used to scale a VNF instance to a target size. The target size is either expressed as an instantiation level of that DF as defined in the VNFD, or given as a list of scale levels, one per scaling aspect of that DF. Instantiation levels and scaling aspects are declared in the VNFD. The NFVO shall then invoke the ScaleVnfToLevel operation towards the appropriate VNFM.. 
 */
@Schema(description = "This type describes the information used to scale a VNF instance to a target size. The target size is either expressed as an instantiation level of that DF as defined in the VNFD, or given as a list of scale levels, one per scaling aspect of that DF. Instantiation levels and scaling aspects are declared in the VNFD. The NFVO shall then invoke the ScaleVnfToLevel operation towards the appropriate VNFM.. ")
@Validated


public class ScaleToLevelData  implements AnyOfScaleToLevelData {
  @JsonProperty("vnfInstantiationLevelId")
  private String vnfInstantiationLevelId = null;

  @JsonProperty("vnfScaleInfo")
  @Valid
  private List<VnfScaleInfo> vnfScaleInfo = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public ScaleToLevelData vnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
    return this;
  }

  /**
   * Get vnfInstantiationLevelId
   * @return vnfInstantiationLevelId
   **/
  @Schema(description = "")
  
    public String getVnfInstantiationLevelId() {
    return vnfInstantiationLevelId;
  }

  public void setVnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
  }

  public ScaleToLevelData vnfScaleInfo(List<VnfScaleInfo> vnfScaleInfo) {
    this.vnfScaleInfo = vnfScaleInfo;
    return this;
  }

  public ScaleToLevelData addVnfScaleInfoItem(VnfScaleInfo vnfScaleInfoItem) {
    if (this.vnfScaleInfo == null) {
      this.vnfScaleInfo = new ArrayList<>();
    }
    this.vnfScaleInfo.add(vnfScaleInfoItem);
    return this;
  }

  /**
   * For each scaling aspect of the current deployment flavor, indicates the target scale level to which the VNF is to be scaled. 
   * @return vnfScaleInfo
   **/
  @Schema(description = "For each scaling aspect of the current deployment flavor, indicates the target scale level to which the VNF is to be scaled. ")
      @Valid
    public List<VnfScaleInfo> getVnfScaleInfo() {
    return vnfScaleInfo;
  }

  public void setVnfScaleInfo(List<VnfScaleInfo> vnfScaleInfo) {
    this.vnfScaleInfo = vnfScaleInfo;
  }

  public ScaleToLevelData additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScaleToLevelData scaleToLevelData = (ScaleToLevelData) o;
    return Objects.equals(this.vnfInstantiationLevelId, scaleToLevelData.vnfInstantiationLevelId) &&
        Objects.equals(this.vnfScaleInfo, scaleToLevelData.vnfScaleInfo) &&
        Objects.equals(this.additionalParams, scaleToLevelData.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstantiationLevelId, vnfScaleInfo, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaleToLevelData {\n");
    
    sb.append("    vnfInstantiationLevelId: ").append(toIndentedString(vnfInstantiationLevelId)).append("\n");
    sb.append("    vnfScaleInfo: ").append(toIndentedString(vnfScaleInfo)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
