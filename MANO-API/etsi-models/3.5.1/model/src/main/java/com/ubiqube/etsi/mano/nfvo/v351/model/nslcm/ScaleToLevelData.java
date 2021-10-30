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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfScaleInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type describes the information used to scale a VNF instance to a target size. The target size is either expressed as an instantiation level of that DF as defined in the VNFD, or given as a list of scale levels, one per scaling aspect of that DF. Instantiation levels and scaling aspects are declared in the VNFD. The NFVO shall then invoke the ScaleVnfToLevel operation towards the appropriate VNFM. NOTE: Either the instantiationLevelId attribute or the scaleInfo attribute shall be included. 
 */
@Schema(description = "This type describes the information used to scale a VNF instance to a target size. The target size is either expressed as an instantiation level of that DF as defined in the VNFD, or given as a list of scale levels, one per scaling aspect of that DF. Instantiation levels and scaling aspects are declared in the VNFD. The NFVO shall then invoke the ScaleVnfToLevel operation towards the appropriate VNFM. NOTE: Either the instantiationLevelId attribute or the scaleInfo attribute shall be included. ")
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
   * For each scaling aspect of the current deployment flavor, indicates the target scale level to which the VNF is to be scaled. See note. 
   * @return vnfScaleInfo
   **/
  @Schema(description = "For each scaling aspect of the current deployment flavor, indicates the target scale level to which the VNF is to be scaled. See note. ")
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
