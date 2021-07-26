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
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an identifier of the CP or SAP instance.  It shall comply with the provisions defined in Table 6.5.3.56-1. 
 */
@Schema(description = "This type represents an identifier of the CP or SAP instance.  It shall comply with the provisions defined in Table 6.5.3.56-1. ")
@Validated


public class NsCpHandle  implements OneOfNsCpHandle {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfExtCpInstanceId")
  private String vnfExtCpInstanceId = null;

  @JsonProperty("pnfInfoId")
  private String pnfInfoId = null;

  @JsonProperty("pnfExtCpInstanceId")
  private String pnfExtCpInstanceId = null;

  @JsonProperty("nsInstanceId")
  private String nsInstanceId = null;

  @JsonProperty("nsSapInstanceId")
  private String nsSapInstanceId = null;

  public NsCpHandle vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(description = "")
  
    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsCpHandle vnfExtCpInstanceId(String vnfExtCpInstanceId) {
    this.vnfExtCpInstanceId = vnfExtCpInstanceId;
    return this;
  }

  /**
   * Get vnfExtCpInstanceId
   * @return vnfExtCpInstanceId
   **/
  @Schema(description = "")
  
    public String getVnfExtCpInstanceId() {
    return vnfExtCpInstanceId;
  }

  public void setVnfExtCpInstanceId(String vnfExtCpInstanceId) {
    this.vnfExtCpInstanceId = vnfExtCpInstanceId;
  }

  public NsCpHandle pnfInfoId(String pnfInfoId) {
    this.pnfInfoId = pnfInfoId;
    return this;
  }

  /**
   * Get pnfInfoId
   * @return pnfInfoId
   **/
  @Schema(description = "")
  
    public String getPnfInfoId() {
    return pnfInfoId;
  }

  public void setPnfInfoId(String pnfInfoId) {
    this.pnfInfoId = pnfInfoId;
  }

  public NsCpHandle pnfExtCpInstanceId(String pnfExtCpInstanceId) {
    this.pnfExtCpInstanceId = pnfExtCpInstanceId;
    return this;
  }

  /**
   * Get pnfExtCpInstanceId
   * @return pnfExtCpInstanceId
   **/
  @Schema(description = "")
  
    public String getPnfExtCpInstanceId() {
    return pnfExtCpInstanceId;
  }

  public void setPnfExtCpInstanceId(String pnfExtCpInstanceId) {
    this.pnfExtCpInstanceId = pnfExtCpInstanceId;
  }

  public NsCpHandle nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

  /**
   * Get nsInstanceId
   * @return nsInstanceId
   **/
  @Schema(description = "")
  
    public String getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public NsCpHandle nsSapInstanceId(String nsSapInstanceId) {
    this.nsSapInstanceId = nsSapInstanceId;
    return this;
  }

  /**
   * Get nsSapInstanceId
   * @return nsSapInstanceId
   **/
  @Schema(description = "")
  
    public String getNsSapInstanceId() {
    return nsSapInstanceId;
  }

  public void setNsSapInstanceId(String nsSapInstanceId) {
    this.nsSapInstanceId = nsSapInstanceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsCpHandle nsCpHandle = (NsCpHandle) o;
    return Objects.equals(this.vnfInstanceId, nsCpHandle.vnfInstanceId) &&
        Objects.equals(this.vnfExtCpInstanceId, nsCpHandle.vnfExtCpInstanceId) &&
        Objects.equals(this.pnfInfoId, nsCpHandle.pnfInfoId) &&
        Objects.equals(this.pnfExtCpInstanceId, nsCpHandle.pnfExtCpInstanceId) &&
        Objects.equals(this.nsInstanceId, nsCpHandle.nsInstanceId) &&
        Objects.equals(this.nsSapInstanceId, nsCpHandle.nsSapInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, vnfExtCpInstanceId, pnfInfoId, pnfExtCpInstanceId, nsInstanceId, nsSapInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsCpHandle {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfExtCpInstanceId: ").append(toIndentedString(vnfExtCpInstanceId)).append("\n");
    sb.append("    pnfInfoId: ").append(toIndentedString(pnfInfoId)).append("\n");
    sb.append("    pnfExtCpInstanceId: ").append(toIndentedString(pnfExtCpInstanceId)).append("\n");
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsSapInstanceId: ").append(toIndentedString(nsSapInstanceId)).append("\n");
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
