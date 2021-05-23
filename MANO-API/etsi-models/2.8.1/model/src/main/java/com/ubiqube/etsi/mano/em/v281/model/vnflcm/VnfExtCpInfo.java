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
package com.ubiqube.etsi.mano.em.v281.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.CpProtocolInfo;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.KeyValuePairs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about an external CP of a VNF. It shall comply with the provisions defined in table 5.5.3.25 1. 
 */
@ApiModel(description = "This type represents information about an external CP of a VNF. It shall comply with the provisions defined in table 5.5.3.25 1. ")
@Validated

public class VnfExtCpInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("cpdId")
  private String cpdId = null;

  @JsonProperty("cpProtocolInfo")
  @Valid
  private List<CpProtocolInfo> cpProtocolInfo = new ArrayList<>();

  @JsonProperty("extLinkPortId")
  private String extLinkPortId = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  @JsonProperty("associatedVnfcCpId")
  private String associatedVnfcCpId = null;

  @JsonProperty("associatedVnfVirtualLinkId")
  private String associatedVnfVirtualLinkId = null;

  public VnfExtCpInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the external CP instance and the related information instance. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of the external CP instance and the related information instance. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfExtCpInfo cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

  /**
   * Identifier of the external CPD, VnfExtCpd, in the VNFD. 
   * @return cpdId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the external CPD, VnfExtCpd, in the VNFD. ")
  @NotNull


  public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public VnfExtCpInfo cpProtocolInfo(List<CpProtocolInfo> cpProtocolInfo) {
    this.cpProtocolInfo = cpProtocolInfo;
    return this;
  }

  public VnfExtCpInfo addCpProtocolInfoItem(CpProtocolInfo cpProtocolInfoItem) {
    this.cpProtocolInfo.add(cpProtocolInfoItem);
    return this;
  }

  /**
   * Network protocol information for this CP. 
   * @return cpProtocolInfo
  **/
  @ApiModelProperty(required = true, value = "Network protocol information for this CP. ")
  @NotNull

  @Valid

  public List<CpProtocolInfo> getCpProtocolInfo() {
    return cpProtocolInfo;
  }

  public void setCpProtocolInfo(List<CpProtocolInfo> cpProtocolInfo) {
    this.cpProtocolInfo = cpProtocolInfo;
  }

  public VnfExtCpInfo extLinkPortId(String extLinkPortId) {
    this.extLinkPortId = extLinkPortId;
    return this;
  }

  /**
   * Identifier of the \"extLinkPortInfo\" structure inside the \"extVirtualLinkInfo\" structure. Shall be present if the CP is associated to a link port. 
   * @return extLinkPortId
  **/
  @ApiModelProperty(value = "Identifier of the \"extLinkPortInfo\" structure inside the \"extVirtualLinkInfo\" structure. Shall be present if the CP is associated to a link port. ")


  public String getExtLinkPortId() {
    return extLinkPortId;
  }

  public void setExtLinkPortId(String extLinkPortId) {
    this.extLinkPortId = extLinkPortId;
  }

  public VnfExtCpInfo metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Metadata about this external CP. 
   * @return metadata
  **/
  @ApiModelProperty(value = "Metadata about this external CP. ")

  @Valid

  public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }

  public VnfExtCpInfo associatedVnfcCpId(String associatedVnfcCpId) {
    this.associatedVnfcCpId = associatedVnfcCpId;
    return this;
  }

  /**
   * Identifier of the \"vnfcCpInfo\" structure in \"VnfcResourceInfo\" structure that represents the VNFC CP which is exposed by this external CP instance. Shall be present in case this CP instance maps to a VNFC CP. See note. 
   * @return associatedVnfcCpId
  **/
  @ApiModelProperty(value = "Identifier of the \"vnfcCpInfo\" structure in \"VnfcResourceInfo\" structure that represents the VNFC CP which is exposed by this external CP instance. Shall be present in case this CP instance maps to a VNFC CP. See note. ")


  public String getAssociatedVnfcCpId() {
    return associatedVnfcCpId;
  }

  public void setAssociatedVnfcCpId(String associatedVnfcCpId) {
    this.associatedVnfcCpId = associatedVnfcCpId;
  }

  public VnfExtCpInfo associatedVnfVirtualLinkId(String associatedVnfVirtualLinkId) {
    this.associatedVnfVirtualLinkId = associatedVnfVirtualLinkId;
    return this;
  }

  /**
   * Identifier of the \"VnfVirtualLinkResourceInfo\" structure that represents the internal VL which is exposed by this external CP instance. Shall be present in case this CP instance maps to an internal VL. See note. 
   * @return associatedVnfVirtualLinkId
  **/
  @ApiModelProperty(value = "Identifier of the \"VnfVirtualLinkResourceInfo\" structure that represents the internal VL which is exposed by this external CP instance. Shall be present in case this CP instance maps to an internal VL. See note. ")


  public String getAssociatedVnfVirtualLinkId() {
    return associatedVnfVirtualLinkId;
  }

  public void setAssociatedVnfVirtualLinkId(String associatedVnfVirtualLinkId) {
    this.associatedVnfVirtualLinkId = associatedVnfVirtualLinkId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfExtCpInfo vnfExtCpInfo = (VnfExtCpInfo) o;
    return Objects.equals(this.id, vnfExtCpInfo.id) &&
        Objects.equals(this.cpdId, vnfExtCpInfo.cpdId) &&
        Objects.equals(this.cpProtocolInfo, vnfExtCpInfo.cpProtocolInfo) &&
        Objects.equals(this.extLinkPortId, vnfExtCpInfo.extLinkPortId) &&
        Objects.equals(this.metadata, vnfExtCpInfo.metadata) &&
        Objects.equals(this.associatedVnfcCpId, vnfExtCpInfo.associatedVnfcCpId) &&
        Objects.equals(this.associatedVnfVirtualLinkId, vnfExtCpInfo.associatedVnfVirtualLinkId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cpdId, cpProtocolInfo, extLinkPortId, metadata, associatedVnfcCpId, associatedVnfVirtualLinkId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfExtCpInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    cpProtocolInfo: ").append(toIndentedString(cpProtocolInfo)).append("\n");
    sb.append("    extLinkPortId: ").append(toIndentedString(extLinkPortId)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    associatedVnfcCpId: ").append(toIndentedString(associatedVnfcCpId)).append("\n");
    sb.append("    associatedVnfVirtualLinkId: ").append(toIndentedString(associatedVnfVirtualLinkId)).append("\n");
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

