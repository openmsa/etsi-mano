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
package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.CpProtocolInfo;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents information about an external CP of a VNF. It shall comply  with the provisions defined in Table 6.5.3.70-1. 
 */
@Schema(description = "This type represents information about an external CP of a VNF. It shall comply  with the provisions defined in Table 6.5.3.70-1. ")
@Validated


public class VnfExtCpInfo  implements OneOfVnfExtCpInfo {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("cpdId")
  private String cpdId = null;

  @JsonProperty("cpConfigId")
  private String cpConfigId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("cpProtocolInfo")
  @Valid
  private List<CpProtocolInfo> cpProtocolInfo = null;

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
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
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
   * Get cpdId
   * @return cpdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public VnfExtCpInfo cpConfigId(String cpConfigId) {
    this.cpConfigId = cpConfigId;
    return this;
  }

  /**
   * Get cpConfigId
   * @return cpConfigId
   **/
  @Schema(description = "")
  
    public String getCpConfigId() {
    return cpConfigId;
  }

  public void setCpConfigId(String cpConfigId) {
    this.cpConfigId = cpConfigId;
  }

  public VnfExtCpInfo vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(description = "")
  
    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfExtCpInfo cpProtocolInfo(List<CpProtocolInfo> cpProtocolInfo) {
    this.cpProtocolInfo = cpProtocolInfo;
    return this;
  }

  public VnfExtCpInfo addCpProtocolInfoItem(CpProtocolInfo cpProtocolInfoItem) {
    if (this.cpProtocolInfo == null) {
      this.cpProtocolInfo = new ArrayList<>();
    }
    this.cpProtocolInfo.add(cpProtocolInfoItem);
    return this;
  }

  /**
   * Network protocol information for this CP. 
   * @return cpProtocolInfo
   **/
  @Schema(description = "Network protocol information for this CP. ")
      @Valid
  @Size(min=1)   public List<CpProtocolInfo> getCpProtocolInfo() {
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
   * Get extLinkPortId
   * @return extLinkPortId
   **/
  @Schema(description = "")
  
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
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
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
   * Get associatedVnfcCpId
   * @return associatedVnfcCpId
   **/
  @Schema(description = "")
  
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
   * Get associatedVnfVirtualLinkId
   * @return associatedVnfVirtualLinkId
   **/
  @Schema(description = "")
  
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
        Objects.equals(this.cpConfigId, vnfExtCpInfo.cpConfigId) &&
        Objects.equals(this.vnfdId, vnfExtCpInfo.vnfdId) &&
        Objects.equals(this.cpProtocolInfo, vnfExtCpInfo.cpProtocolInfo) &&
        Objects.equals(this.extLinkPortId, vnfExtCpInfo.extLinkPortId) &&
        Objects.equals(this.metadata, vnfExtCpInfo.metadata) &&
        Objects.equals(this.associatedVnfcCpId, vnfExtCpInfo.associatedVnfcCpId) &&
        Objects.equals(this.associatedVnfVirtualLinkId, vnfExtCpInfo.associatedVnfVirtualLinkId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, cpdId, cpConfigId, vnfdId, cpProtocolInfo, extLinkPortId, metadata, associatedVnfcCpId, associatedVnfVirtualLinkId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfExtCpInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    cpConfigId: ").append(toIndentedString(cpConfigId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
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
