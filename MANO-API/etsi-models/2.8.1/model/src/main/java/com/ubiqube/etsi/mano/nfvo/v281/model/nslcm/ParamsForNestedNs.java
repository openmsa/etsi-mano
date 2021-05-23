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
package com.ubiqube.etsi.mano.nfvo.v281.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.KeyValuePairs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type specifies additional parameters on a per-nested NS instance basis.  It shall comply with the provisions defined in Table 6.5.3.21a-1. 
 */
@ApiModel(description = "This type specifies additional parameters on a per-nested NS instance basis.  It shall comply with the provisions defined in Table 6.5.3.21a-1. ")
@Validated

public class ParamsForNestedNs   {
  @JsonProperty("nsProfileId")
  private String nsProfileId = null;

  @JsonProperty("additionalParam")
  @Valid
  private List<KeyValuePairs> additionalParam = null;

  public ParamsForNestedNs nsProfileId(String nsProfileId) {
    this.nsProfileId = nsProfileId;
    return this;
  }

  /**
   * Identifier of a NsProfile to which the additional parameters apply. 
   * @return nsProfileId
  **/
  @ApiModelProperty(required = true, value = "Identifier of a NsProfile to which the additional parameters apply. ")
  @NotNull


  public String getNsProfileId() {
    return nsProfileId;
  }

  public void setNsProfileId(String nsProfileId) {
    this.nsProfileId = nsProfileId;
  }

  public ParamsForNestedNs additionalParam(List<KeyValuePairs> additionalParam) {
    this.additionalParam = additionalParam;
    return this;
  }

  public ParamsForNestedNs addAdditionalParamItem(KeyValuePairs additionalParamItem) {
    if (this.additionalParam == null) {
      this.additionalParam = new ArrayList<>();
    }
    this.additionalParam.add(additionalParamItem);
    return this;
  }

  /**
   * Additional parameters that are to be applied on a per nested NS instance. 
   * @return additionalParam
  **/
  @ApiModelProperty(value = "Additional parameters that are to be applied on a per nested NS instance. ")

  @Valid

  public List<KeyValuePairs> getAdditionalParam() {
    return additionalParam;
  }

  public void setAdditionalParam(List<KeyValuePairs> additionalParam) {
    this.additionalParam = additionalParam;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParamsForNestedNs paramsForNestedNs = (ParamsForNestedNs) o;
    return Objects.equals(this.nsProfileId, paramsForNestedNs.nsProfileId) &&
        Objects.equals(this.additionalParam, paramsForNestedNs.additionalParam);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsProfileId, additionalParam);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParamsForNestedNs {\n");
    
    sb.append("    nsProfileId: ").append(toIndentedString(nsProfileId)).append("\n");
    sb.append("    additionalParam: ").append(toIndentedString(additionalParam)).append("\n");
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

