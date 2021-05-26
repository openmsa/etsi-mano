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
package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.KeyValuePairs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ScaleVnfRequest
 */
@Validated


public class ScaleVnfRequest   {
  /**
   * Indicates the type of the scale operation requested. Permitted values: * SCALE_OUT: adding additional VNFC instances to the VNF to increase   capacity * SCALE_IN: removing VNFC instances from the VNF in order to release   unused capacity. 
   */
  public enum TypeEnum {
    OUT("SCALE_OUT"),
    
    IN("SCALE_IN");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("aspectId")
  private String aspectId = null;

  @JsonProperty("numberOfSteps")
  private Integer numberOfSteps = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public ScaleVnfRequest type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Indicates the type of the scale operation requested. Permitted values: * SCALE_OUT: adding additional VNFC instances to the VNF to increase   capacity * SCALE_IN: removing VNFC instances from the VNF in order to release   unused capacity. 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "Indicates the type of the scale operation requested. Permitted values: * SCALE_OUT: adding additional VNFC instances to the VNF to increase   capacity * SCALE_IN: removing VNFC instances from the VNF in order to release   unused capacity. ")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public ScaleVnfRequest aspectId(String aspectId) {
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

  public ScaleVnfRequest numberOfSteps(Integer numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
    return this;
  }

  /**
   * Number of scaling steps to be executed as part of this Scale VNF operation. It shall be a positive number and the default value shall be 1. 
   * @return numberOfSteps
  **/
  @ApiModelProperty(value = "Number of scaling steps to be executed as part of this Scale VNF operation. It shall be a positive number and the default value shall be 1. ")


  public Integer getNumberOfSteps() {
    return numberOfSteps;
  }

  public void setNumberOfSteps(Integer numberOfSteps) {
    this.numberOfSteps = numberOfSteps;
  }

  public ScaleVnfRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Additional parameters passed by the NFVO as input to the scaling process, specific to the VNF being scaled, as declared in the VNFD as part of \"ScaleVnfOpConfig\". 
   * @return additionalParams
  **/
  @ApiModelProperty(value = "Additional parameters passed by the NFVO as input to the scaling process, specific to the VNF being scaled, as declared in the VNFD as part of \"ScaleVnfOpConfig\". ")

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
    ScaleVnfRequest scaleVnfRequest = (ScaleVnfRequest) o;
    return Objects.equals(this.type, scaleVnfRequest.type) &&
        Objects.equals(this.aspectId, scaleVnfRequest.aspectId) &&
        Objects.equals(this.numberOfSteps, scaleVnfRequest.numberOfSteps) &&
        Objects.equals(this.additionalParams, scaleVnfRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, aspectId, numberOfSteps, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaleVnfRequest {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
    sb.append("    numberOfSteps: ").append(toIndentedString(numberOfSteps)).append("\n");
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

