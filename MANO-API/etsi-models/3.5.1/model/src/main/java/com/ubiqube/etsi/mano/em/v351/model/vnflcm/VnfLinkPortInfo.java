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
package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a link port of an internal VL of a VNF. It shall comply with the provisions  defined in table 5.5.3.8 1. NOTE 1: Either cpInstanceId with cpInstanceType set to \&quot;EXT_CP\&quot; or any combination of cpInstanceId         with cpInstanceType set to \&quot;VNFC_CP\&quot; and vipCpInstanceId (i.e. one or both of them) shall be          present for a VnfLinkPortInfo. In case both cpInstanceId with cpInstanceType set to \&quot;VNFC_CP\&quot;          and vipCpInstanceId are present, the two different CP instances share the linkport. NOTE 2: Annex A.4 of ETSI GS NFV-IFA 007 provides examples for configurations where both vipCpInstanceId         and vnfcCpInstanceId are present (UC#5 and UC#5-b), only vnfcCpInstanceId is present (UC#2), or          only vipCpInstanceId is present (UC6 and UC#6-b). NOTE 3: The value of \&quot;trunkResourceId\&quot; is scoped by the value of \&quot;vimConnectionId\&quot; in the \&quot;resourceHandle\&quot;         attribute. 
 */
@Schema(description = "This type represents a link port of an internal VL of a VNF. It shall comply with the provisions  defined in table 5.5.3.8 1. NOTE 1: Either cpInstanceId with cpInstanceType set to \"EXT_CP\" or any combination of cpInstanceId         with cpInstanceType set to \"VNFC_CP\" and vipCpInstanceId (i.e. one or both of them) shall be          present for a VnfLinkPortInfo. In case both cpInstanceId with cpInstanceType set to \"VNFC_CP\"          and vipCpInstanceId are present, the two different CP instances share the linkport. NOTE 2: Annex A.4 of ETSI GS NFV-IFA 007 provides examples for configurations where both vipCpInstanceId         and vnfcCpInstanceId are present (UC#5 and UC#5-b), only vnfcCpInstanceId is present (UC#2), or          only vipCpInstanceId is present (UC6 and UC#6-b). NOTE 3: The value of \"trunkResourceId\" is scoped by the value of \"vimConnectionId\" in the \"resourceHandle\"         attribute. ")
@Validated


public class VnfLinkPortInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("resourceHandle")
  private ResourceHandle resourceHandle = null;

  @JsonProperty("cpInstanceId")
  private String cpInstanceId = null;

  /**
   * Type of the CP instance that is identified by cpInstanceId.  Shall be present if \"cpInstanceId\" is present and shall be absent otherwise. Permitted values: - VNFC_CP: The link port is connected to a VNFC CP. - EXT_CP: The link port is associated to an external CP. See note 1. 
   */
  public enum CpInstanceTypeEnum {
    VNFC_CP("VNFC_CP"),
    
    EXT_CP("EXT_CP");

    private String value;

    CpInstanceTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CpInstanceTypeEnum fromValue(String text) {
      for (CpInstanceTypeEnum b : CpInstanceTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("cpInstanceType")
  private CpInstanceTypeEnum cpInstanceType = null;

  @JsonProperty("vipCpInstanceId")
  private String vipCpInstanceId = null;

  @JsonProperty("trunkResourceId")
  private String trunkResourceId = null;

  public VnfLinkPortInfo id(String id) {
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

  public VnfLinkPortInfo resourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

  /**
   * Get resourceHandle
   * @return resourceHandle
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getResourceHandle() {
    return resourceHandle;
  }

  public void setResourceHandle(ResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
  }

  public VnfLinkPortInfo cpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

  /**
   * Get cpInstanceId
   * @return cpInstanceId
   **/
  @Schema(description = "")
  
    public String getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public VnfLinkPortInfo cpInstanceType(CpInstanceTypeEnum cpInstanceType) {
    this.cpInstanceType = cpInstanceType;
    return this;
  }

  /**
   * Type of the CP instance that is identified by cpInstanceId.  Shall be present if \"cpInstanceId\" is present and shall be absent otherwise. Permitted values: - VNFC_CP: The link port is connected to a VNFC CP. - EXT_CP: The link port is associated to an external CP. See note 1. 
   * @return cpInstanceType
   **/
  @Schema(description = "Type of the CP instance that is identified by cpInstanceId.  Shall be present if \"cpInstanceId\" is present and shall be absent otherwise. Permitted values: - VNFC_CP: The link port is connected to a VNFC CP. - EXT_CP: The link port is associated to an external CP. See note 1. ")
  
    public CpInstanceTypeEnum getCpInstanceType() {
    return cpInstanceType;
  }

  public void setCpInstanceType(CpInstanceTypeEnum cpInstanceType) {
    this.cpInstanceType = cpInstanceType;
  }

  public VnfLinkPortInfo vipCpInstanceId(String vipCpInstanceId) {
    this.vipCpInstanceId = vipCpInstanceId;
    return this;
  }

  /**
   * Get vipCpInstanceId
   * @return vipCpInstanceId
   **/
  @Schema(description = "")
  
    public String getVipCpInstanceId() {
    return vipCpInstanceId;
  }

  public void setVipCpInstanceId(String vipCpInstanceId) {
    this.vipCpInstanceId = vipCpInstanceId;
  }

  public VnfLinkPortInfo trunkResourceId(String trunkResourceId) {
    this.trunkResourceId = trunkResourceId;
    return this;
  }

  /**
   * Get trunkResourceId
   * @return trunkResourceId
   **/
  @Schema(description = "")
  
    public String getTrunkResourceId() {
    return trunkResourceId;
  }

  public void setTrunkResourceId(String trunkResourceId) {
    this.trunkResourceId = trunkResourceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfLinkPortInfo vnfLinkPortInfo = (VnfLinkPortInfo) o;
    return Objects.equals(this.id, vnfLinkPortInfo.id) &&
        Objects.equals(this.resourceHandle, vnfLinkPortInfo.resourceHandle) &&
        Objects.equals(this.cpInstanceId, vnfLinkPortInfo.cpInstanceId) &&
        Objects.equals(this.cpInstanceType, vnfLinkPortInfo.cpInstanceType) &&
        Objects.equals(this.vipCpInstanceId, vnfLinkPortInfo.vipCpInstanceId) &&
        Objects.equals(this.trunkResourceId, vnfLinkPortInfo.trunkResourceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceHandle, cpInstanceId, cpInstanceType, vipCpInstanceId, trunkResourceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfLinkPortInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
    sb.append("    cpInstanceType: ").append(toIndentedString(cpInstanceType)).append("\n");
    sb.append("    vipCpInstanceId: ").append(toIndentedString(vipCpInstanceId)).append("\n");
    sb.append("    trunkResourceId: ").append(toIndentedString(trunkResourceId)).append("\n");
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
