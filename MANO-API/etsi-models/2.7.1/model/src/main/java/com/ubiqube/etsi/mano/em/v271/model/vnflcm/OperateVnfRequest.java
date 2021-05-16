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
package com.ubiqube.etsi.mano.em.v271.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.VnfOperationalStateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Operate VNF\&quot; operation. 
 */
@ApiModel(description = "This type represents request parameters for the \"Operate VNF\" operation. ")
@Validated

public class OperateVnfRequest   {
  @JsonProperty("vnfcInstanceId")
  @Valid
  private List<String> vnfcInstanceId = null;

  @JsonProperty("changeStateTo")
  private VnfOperationalStateType changeStateTo = null;

  /**
   * It signals whether forceful or graceful stop is requested. Ignored if changeStateTo=STARTED. Permitted values: FORCEFUL: The VNFM will stop down the VNF or the affected VNFCs immediately after accepting the request. 
   */
  public enum StopTypeEnum {
    FORCEFUL("FORCEFUL");

    private String value;

    StopTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StopTypeEnum fromValue(String text) {
      for (StopTypeEnum b : StopTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("stopType")
  private StopTypeEnum stopType = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public OperateVnfRequest vnfcInstanceId(List<String> vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
    return this;
  }

  public OperateVnfRequest addVnfcInstanceIdItem(String vnfcInstanceIdItem) {
    if (this.vnfcInstanceId == null) {
      this.vnfcInstanceId = new ArrayList<>();
    }
    this.vnfcInstanceId.add(vnfcInstanceIdItem);
    return this;
  }

  /**
   * Identifier of VNFC instances. Cardinality can be \"0\" to denote that the request applies to the whole VNF and not a specific VNFC instance. 
   * @return vnfcInstanceId
  **/
  @ApiModelProperty(value = "Identifier of VNFC instances. Cardinality can be \"0\" to denote that the request applies to the whole VNF and not a specific VNFC instance. ")


  public List<String> getVnfcInstanceId() {
    return vnfcInstanceId;
  }

  public void setVnfcInstanceId(List<String> vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
  }

  public OperateVnfRequest changeStateTo(VnfOperationalStateType changeStateTo) {
    this.changeStateTo = changeStateTo;
    return this;
  }

  /**
   * The desired operational state (i.e. started or stopped) to change the VNF to. 
   * @return changeStateTo
  **/
  @ApiModelProperty(required = true, value = "The desired operational state (i.e. started or stopped) to change the VNF to. ")
  @NotNull

  @Valid

  public VnfOperationalStateType getChangeStateTo() {
    return changeStateTo;
  }

  public void setChangeStateTo(VnfOperationalStateType changeStateTo) {
    this.changeStateTo = changeStateTo;
  }

  public OperateVnfRequest stopType(StopTypeEnum stopType) {
    this.stopType = stopType;
    return this;
  }

  /**
   * It signals whether forceful or graceful stop is requested. Ignored if changeStateTo=STARTED. Permitted values: FORCEFUL: The VNFM will stop down the VNF or the affected VNFCs immediately after accepting the request. 
   * @return stopType
  **/
  @ApiModelProperty(value = "It signals whether forceful or graceful stop is requested. Ignored if changeStateTo=STARTED. Permitted values: FORCEFUL: The VNFM will stop down the VNF or the affected VNFCs immediately after accepting the request. ")


  public StopTypeEnum getStopType() {
    return stopType;
  }

  public void setStopType(StopTypeEnum stopType) {
    this.stopType = stopType;
  }

  public OperateVnfRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Additional parameters passed by the NFVO as input to the process, specific to the VNF of which the operation status is changed, as declared in the VNFD as part of \"OperateVnfOpConfig\". 
   * @return additionalParams
  **/
  @ApiModelProperty(value = "Additional parameters passed by the NFVO as input to the process, specific to the VNF of which the operation status is changed, as declared in the VNFD as part of \"OperateVnfOpConfig\". ")

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
    OperateVnfRequest operateVnfRequest = (OperateVnfRequest) o;
    return Objects.equals(this.vnfcInstanceId, operateVnfRequest.vnfcInstanceId) &&
        Objects.equals(this.changeStateTo, operateVnfRequest.changeStateTo) &&
        Objects.equals(this.stopType, operateVnfRequest.stopType) &&
        Objects.equals(this.additionalParams, operateVnfRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfcInstanceId, changeStateTo, stopType, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperateVnfRequest {\n");
    
    sb.append("    vnfcInstanceId: ").append(toIndentedString(vnfcInstanceId)).append("\n");
    sb.append("    changeStateTo: ").append(toIndentedString(changeStateTo)).append("\n");
    sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
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

