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
import com.ubiqube.etsi.mano.nfvo.v281.model.nslcm.PnfExtCpData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type specifies an PNF to be added to the NS instance and the PNF Profile to use for this PNF. It shall comply with the provisions defined in Table 6.5.3.14-1. 
 */
@ApiModel(description = "This type specifies an PNF to be added to the NS instance and the PNF Profile to use for this PNF. It shall comply with the provisions defined in Table 6.5.3.14-1. ")
@Validated

public class AddPnfData   {
  @JsonProperty("pnfId")
  private String pnfId = null;

  @JsonProperty("pnfName")
  private String pnfName = null;

  @JsonProperty("pnfdId")
  private String pnfdId = null;

  @JsonProperty("pnfProfileId")
  private String pnfProfileId = null;

  @JsonProperty("cpData")
  @Valid
  private List<PnfExtCpData> cpData = null;

  public AddPnfData pnfId(String pnfId) {
    this.pnfId = pnfId;
    return this;
  }

  /**
   * Identifier of the PNF. This identifier is allocated by the OSS/BSS. 
   * @return pnfId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the PNF. This identifier is allocated by the OSS/BSS. ")
  @NotNull


  public String getPnfId() {
    return pnfId;
  }

  public void setPnfId(String pnfId) {
    this.pnfId = pnfId;
  }

  public AddPnfData pnfName(String pnfName) {
    this.pnfName = pnfName;
    return this;
  }

  /**
   * Name of the PNF 
   * @return pnfName
  **/
  @ApiModelProperty(required = true, value = "Name of the PNF ")
  @NotNull


  public String getPnfName() {
    return pnfName;
  }

  public void setPnfName(String pnfName) {
    this.pnfName = pnfName;
  }

  public AddPnfData pnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
    return this;
  }

  /**
   * Identifier of the PNFD on which the PNF is based. 
   * @return pnfdId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the PNFD on which the PNF is based. ")
  @NotNull


  public String getPnfdId() {
    return pnfdId;
  }

  public void setPnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
  }

  public AddPnfData pnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
    return this;
  }

  /**
   * Identifier of related PnfProfile in the NSD on which the PNF is based. 
   * @return pnfProfileId
  **/
  @ApiModelProperty(required = true, value = "Identifier of related PnfProfile in the NSD on which the PNF is based. ")
  @NotNull


  public String getPnfProfileId() {
    return pnfProfileId;
  }

  public void setPnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
  }

  public AddPnfData cpData(List<PnfExtCpData> cpData) {
    this.cpData = cpData;
    return this;
  }

  public AddPnfData addCpDataItem(PnfExtCpData cpDataItem) {
    if (this.cpData == null) {
      this.cpData = new ArrayList<>();
    }
    this.cpData.add(cpDataItem);
    return this;
  }

  /**
   * Address assigned for the PNF external CP(s). 
   * @return cpData
  **/
  @ApiModelProperty(value = "Address assigned for the PNF external CP(s). ")

  @Valid

  public List<PnfExtCpData> getCpData() {
    return cpData;
  }

  public void setCpData(List<PnfExtCpData> cpData) {
    this.cpData = cpData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddPnfData addPnfData = (AddPnfData) o;
    return Objects.equals(this.pnfId, addPnfData.pnfId) &&
        Objects.equals(this.pnfName, addPnfData.pnfName) &&
        Objects.equals(this.pnfdId, addPnfData.pnfdId) &&
        Objects.equals(this.pnfProfileId, addPnfData.pnfProfileId) &&
        Objects.equals(this.cpData, addPnfData.cpData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pnfId, pnfName, pnfdId, pnfProfileId, cpData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddPnfData {\n");
    
    sb.append("    pnfId: ").append(toIndentedString(pnfId)).append("\n");
    sb.append("    pnfName: ").append(toIndentedString(pnfName)).append("\n");
    sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
    sb.append("    pnfProfileId: ").append(toIndentedString(pnfProfileId)).append("\n");
    sb.append("    cpData: ").append(toIndentedString(cpData)).append("\n");
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

