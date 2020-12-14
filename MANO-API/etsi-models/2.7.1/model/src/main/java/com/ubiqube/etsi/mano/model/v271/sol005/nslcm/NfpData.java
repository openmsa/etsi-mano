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
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.CpGroupInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.NfpRule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type contains information used to create or modify NFP instance parameters for the update of an existing VNFFG instance. It shall comply with the provisions defined in Table 6.5.3.38-1. 
 */
@ApiModel(description = "This type contains information used to create or modify NFP instance parameters for the update of an existing VNFFG instance. It shall comply with the provisions defined in Table 6.5.3.38-1. ")
@Validated
public class NfpData   {
  @JsonProperty("nfpInfoId")
  private String nfpInfoId = null;

  @JsonProperty("nfpName")
  private String nfpName = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("cpGroup")
  @Valid
  private List<CpGroupInfo> cpGroup = null;

  @JsonProperty("nfpRule")
  private NfpRule nfpRule = null;

  public NfpData nfpInfoId(String nfpInfoId) {
    this.nfpInfoId = nfpInfoId;
    return this;
  }

  /**
   * Get nfpInfoId
   * @return nfpInfoId
  **/
  @ApiModelProperty(value = "")
  
    public String getNfpInfoId() {
    return nfpInfoId;
  }

  public void setNfpInfoId(String nfpInfoId) {
    this.nfpInfoId = nfpInfoId;
  }

  public NfpData nfpName(String nfpName) {
    this.nfpName = nfpName;
    return this;
  }

  /**
   * Human readable name for the NFP. It shall be present for the new NFP, and it may be present otherwise. It shall be present for the new NFP, and it may be  present otherwise. 
   * @return nfpName
  **/
  @ApiModelProperty(value = "Human readable name for the NFP. It shall be present for the new NFP, and it may be present otherwise. It shall be present for the new NFP, and it may be  present otherwise. ")
  
    public String getNfpName() {
    return nfpName;
  }

  public void setNfpName(String nfpName) {
    this.nfpName = nfpName;
  }

  public NfpData description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Human readable description for the NFP. It shall be present for the new NFP, and it may be present otherwise. It shall be present for the new NFP, and it may be  present otherwise. 
   * @return description
  **/
  @ApiModelProperty(value = "Human readable description for the NFP. It shall be present for the new NFP, and it may be present otherwise. It shall be present for the new NFP, and it may be  present otherwise. ")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NfpData cpGroup(List<CpGroupInfo> cpGroup) {
    this.cpGroup = cpGroup;
    return this;
  }

  public NfpData addCpGroupItem(CpGroupInfo cpGroupItem) {
    if (this.cpGroup == null) {
      this.cpGroup = new ArrayList<>();
    }
    this.cpGroup.add(cpGroupItem);
    return this;
  }

  /**
   * Group(s) of CPs and/or SAPs which the NFP passes by. Cardinality can be 0 if only updated or newly created NFP classification and selection rule which applied to an existing NFP is provided. At least a CP or an nfpRule shall be present. When multiple identifiers are included, the position of  the identifier in the cpGroup value specifies the position  of the group in the path. 
   * @return cpGroup
  **/
  @ApiModelProperty(value = "Group(s) of CPs and/or SAPs which the NFP passes by. Cardinality can be 0 if only updated or newly created NFP classification and selection rule which applied to an existing NFP is provided. At least a CP or an nfpRule shall be present. When multiple identifiers are included, the position of  the identifier in the cpGroup value specifies the position  of the group in the path. ")
      @Valid
    public List<CpGroupInfo> getCpGroup() {
    return cpGroup;
  }

  public void setCpGroup(List<CpGroupInfo> cpGroup) {
    this.cpGroup = cpGroup;
  }

  public NfpData nfpRule(NfpRule nfpRule) {
    this.nfpRule = nfpRule;
    return this;
  }

  /**
   * Get nfpRule
   * @return nfpRule
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public NfpRule getNfpRule() {
    return nfpRule;
  }

  public void setNfpRule(NfpRule nfpRule) {
    this.nfpRule = nfpRule;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NfpData nfpData = (NfpData) o;
    return Objects.equals(this.nfpInfoId, nfpData.nfpInfoId) &&
        Objects.equals(this.nfpName, nfpData.nfpName) &&
        Objects.equals(this.description, nfpData.description) &&
        Objects.equals(this.cpGroup, nfpData.cpGroup) &&
        Objects.equals(this.nfpRule, nfpData.nfpRule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nfpInfoId, nfpName, description, cpGroup, nfpRule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NfpData {\n");
    
    sb.append("    nfpInfoId: ").append(toIndentedString(nfpInfoId)).append("\n");
    sb.append("    nfpName: ").append(toIndentedString(nfpName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    cpGroup: ").append(toIndentedString(cpGroup)).append("\n");
    sb.append("    nfpRule: ").append(toIndentedString(nfpRule)).append("\n");
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
