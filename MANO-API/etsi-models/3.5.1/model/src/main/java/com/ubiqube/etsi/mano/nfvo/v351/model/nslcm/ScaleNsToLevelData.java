package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.NsScaleInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information used to scale an NS instance to a target size. The target size is either expressed as an NS instantiation level or as a list of NS scale levels, one per NS scaling aspect, of the current DF. The NS instantiation levels, the NS scaling aspects and their corresponding NS scale levels applicable to the NS instance are declared in the NSD. NOTE: Either nsInstantiationLevel or nsScaleInfo, but not both, shall be present. 
 */
@Schema(description = "This type represents the information used to scale an NS instance to a target size. The target size is either expressed as an NS instantiation level or as a list of NS scale levels, one per NS scaling aspect, of the current DF. The NS instantiation levels, the NS scaling aspects and their corresponding NS scale levels applicable to the NS instance are declared in the NSD. NOTE: Either nsInstantiationLevel or nsScaleInfo, but not both, shall be present. ")
@Validated


public class ScaleNsToLevelData  implements OneOfScaleNsToLevelData {
  @JsonProperty("nsInstantiationLevel")
  private String nsInstantiationLevel = null;

  @JsonProperty("nsScaleInfo")
  @Valid
  private List<NsScaleInfo> nsScaleInfo = null;

  public ScaleNsToLevelData nsInstantiationLevel(String nsInstantiationLevel) {
    this.nsInstantiationLevel = nsInstantiationLevel;
    return this;
  }

  /**
   * Get nsInstantiationLevel
   * @return nsInstantiationLevel
   **/
  @Schema(description = "")
  
    public String getNsInstantiationLevel() {
    return nsInstantiationLevel;
  }

  public void setNsInstantiationLevel(String nsInstantiationLevel) {
    this.nsInstantiationLevel = nsInstantiationLevel;
  }

  public ScaleNsToLevelData nsScaleInfo(List<NsScaleInfo> nsScaleInfo) {
    this.nsScaleInfo = nsScaleInfo;
    return this;
  }

  public ScaleNsToLevelData addNsScaleInfoItem(NsScaleInfo nsScaleInfoItem) {
    if (this.nsScaleInfo == null) {
      this.nsScaleInfo = new ArrayList<>();
    }
    this.nsScaleInfo.add(nsScaleInfoItem);
    return this;
  }

  /**
   * For each NS scaling aspect of the current DF, defines the target NS scale level to which the NS instance is to be scaled. See note. 
   * @return nsScaleInfo
   **/
  @Schema(description = "For each NS scaling aspect of the current DF, defines the target NS scale level to which the NS instance is to be scaled. See note. ")
      @Valid
    public List<NsScaleInfo> getNsScaleInfo() {
    return nsScaleInfo;
  }

  public void setNsScaleInfo(List<NsScaleInfo> nsScaleInfo) {
    this.nsScaleInfo = nsScaleInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScaleNsToLevelData scaleNsToLevelData = (ScaleNsToLevelData) o;
    return Objects.equals(this.nsInstantiationLevel, scaleNsToLevelData.nsInstantiationLevel) &&
        Objects.equals(this.nsScaleInfo, scaleNsToLevelData.nsScaleInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsInstantiationLevel, nsScaleInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaleNsToLevelData {\n");
    
    sb.append("    nsInstantiationLevel: ").append(toIndentedString(nsInstantiationLevel)).append("\n");
    sb.append("    nsScaleInfo: ").append(toIndentedString(nsScaleInfo)).append("\n");
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
