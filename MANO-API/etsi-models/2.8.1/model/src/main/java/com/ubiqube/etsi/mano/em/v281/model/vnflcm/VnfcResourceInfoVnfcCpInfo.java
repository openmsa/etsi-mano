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
 * VnfcResourceInfoVnfcCpInfo
 */
@Validated

public class VnfcResourceInfoVnfcCpInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("cpdId")
  private String cpdId = null;

  @JsonProperty("vnfExtCpId")
  private String vnfExtCpId = null;

  @JsonProperty("cpProtocolInfo")
  @Valid
  private List<CpProtocolInfo> cpProtocolInfo = null;

  @JsonProperty("vnfLinkPortId")
  private String vnfLinkPortId = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public VnfcResourceInfoVnfcCpInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of this VNFC CP instance and the associated array entry. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this VNFC CP instance and the associated array entry. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfcResourceInfoVnfcCpInfo cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

  /**
   * Identifier of the VDU CPD, cpdId, in the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. 
   * @return cpdId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VDU CPD, cpdId, in the VNFD. ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. ")
  @NotNull


  public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public VnfcResourceInfoVnfcCpInfo vnfExtCpId(String vnfExtCpId) {
    this.vnfExtCpId = vnfExtCpId;
    return this;
  }

  /**
   * Identifier of the related external CP. Shall be present when the VNFC CP is exposed as an external CP of the VNF instance or connected to an external CP of the VNF instance (see note 2) and shall be absent otherwise. NOTE 2: A VNFC CP is \"connected to\" an external CP if the VNFC CP is connected to an internal VL that exposes an external CP. A VNFC CP is \"exposed as\" an external CP if it is connected directly to an external VL. 
   * @return vnfExtCpId
  **/
  @ApiModelProperty(value = "Identifier of the related external CP. Shall be present when the VNFC CP is exposed as an external CP of the VNF instance or connected to an external CP of the VNF instance (see note 2) and shall be absent otherwise. NOTE 2: A VNFC CP is \"connected to\" an external CP if the VNFC CP is connected to an internal VL that exposes an external CP. A VNFC CP is \"exposed as\" an external CP if it is connected directly to an external VL. ")


  public String getVnfExtCpId() {
    return vnfExtCpId;
  }

  public void setVnfExtCpId(String vnfExtCpId) {
    this.vnfExtCpId = vnfExtCpId;
  }

  public VnfcResourceInfoVnfcCpInfo cpProtocolInfo(List<CpProtocolInfo> cpProtocolInfo) {
    this.cpProtocolInfo = cpProtocolInfo;
    return this;
  }

  public VnfcResourceInfoVnfcCpInfo addCpProtocolInfoItem(CpProtocolInfo cpProtocolInfoItem) {
    if (this.cpProtocolInfo == null) {
      this.cpProtocolInfo = new ArrayList<>();
    }
    this.cpProtocolInfo.add(cpProtocolInfoItem);
    return this;
  }

  /**
   * Network protocol information for this CP. May be omitted if the VNFC CP is exposed as an external CP. See note 3. NOTE 3: The information can be omitted because it is already available as part of the external CP information. 
   * @return cpProtocolInfo
  **/
  @ApiModelProperty(value = "Network protocol information for this CP. May be omitted if the VNFC CP is exposed as an external CP. See note 3. NOTE 3: The information can be omitted because it is already available as part of the external CP information. ")

  @Valid

  public List<CpProtocolInfo> getCpProtocolInfo() {
    return cpProtocolInfo;
  }

  public void setCpProtocolInfo(List<CpProtocolInfo> cpProtocolInfo) {
    this.cpProtocolInfo = cpProtocolInfo;
  }

  public VnfcResourceInfoVnfcCpInfo vnfLinkPortId(String vnfLinkPortId) {
    this.vnfLinkPortId = vnfLinkPortId;
    return this;
  }

  /**
   * Identifier of the \"VnfLinkPorts\" structure in the \"VnfVirtualLinkResourceInfo\" structure. Shall be present if the CP is associated to a link port on an internal VL of the VNF instance and shall be absent otherwise. 
   * @return vnfLinkPortId
  **/
  @ApiModelProperty(value = "Identifier of the \"VnfLinkPorts\" structure in the \"VnfVirtualLinkResourceInfo\" structure. Shall be present if the CP is associated to a link port on an internal VL of the VNF instance and shall be absent otherwise. ")


  public String getVnfLinkPortId() {
    return vnfLinkPortId;
  }

  public void setVnfLinkPortId(String vnfLinkPortId) {
    this.vnfLinkPortId = vnfLinkPortId;
  }

  public VnfcResourceInfoVnfcCpInfo metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Metadata about this CP. 
   * @return metadata
  **/
  @ApiModelProperty(value = "Metadata about this CP. ")

  @Valid

  public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfcResourceInfoVnfcCpInfo vnfcResourceInfoVnfcCpInfo = (VnfcResourceInfoVnfcCpInfo) o;
    return Objects.equals(this.id, vnfcResourceInfoVnfcCpInfo.id) &&
        Objects.equals(this.cpdId, vnfcResourceInfoVnfcCpInfo.cpdId) &&
        Objects.equals(this.vnfExtCpId, vnfcResourceInfoVnfcCpInfo.vnfExtCpId) &&
        Objects.equals(this.cpProtocolInfo, vnfcResourceInfoVnfcCpInfo.cpProtocolInfo) &&
        Objects.equals(this.vnfLinkPortId, vnfcResourceInfoVnfcCpInfo.vnfLinkPortId) &&
        Objects.equals(this.metadata, vnfcResourceInfoVnfcCpInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cpdId, vnfExtCpId, cpProtocolInfo, vnfLinkPortId, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcResourceInfoVnfcCpInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    vnfExtCpId: ").append(toIndentedString(vnfExtCpId)).append("\n");
    sb.append("    cpProtocolInfo: ").append(toIndentedString(cpProtocolInfo)).append("\n");
    sb.append("    vnfLinkPortId: ").append(toIndentedString(vnfLinkPortId)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

