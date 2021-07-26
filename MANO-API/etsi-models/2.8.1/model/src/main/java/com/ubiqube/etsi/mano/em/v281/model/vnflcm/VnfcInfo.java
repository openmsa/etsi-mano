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
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.KeyValuePairs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information about a VNFC instance that is part of a VNF instance. It shall comply with the provisions defined in table 5.5.3.23-1. 
 */
@ApiModel(description = "This type represents the information about a VNFC instance that is part of a VNF instance. It shall comply with the provisions defined in table 5.5.3.23-1. ")
@Validated

public class VnfcInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("vnfcResourceInfoId")
  private String vnfcResourceInfoId = null;

  /**
   * State of the VNFC instance. Permitted values: • STARTED: The VNFC instance is up and running. • STOPPED: The VNFC instance has been shut down 
   */
  public enum VnfcStateEnum {
    STARTED("STARTED"),
    
    STOPPED("STOPPED");

    private String value;

    VnfcStateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static VnfcStateEnum fromValue(String text) {
      for (VnfcStateEnum b : VnfcStateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("vnfcState")
  private VnfcStateEnum vnfcState = null;

  @JsonProperty("vnfcConfigurableProperties")
  private KeyValuePairs vnfcConfigurableProperties = null;

  public VnfcInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the VNFC instance. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNFC instance. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfcInfo vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * Reference to the applicable VDU information element in the VNFD. 
   * @return vduId
  **/
  @ApiModelProperty(required = true, value = "Reference to the applicable VDU information element in the VNFD. ")
  @NotNull


  public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }

  public VnfcInfo vnfcResourceInfoId(String vnfcResourceInfoId) {
    this.vnfcResourceInfoId = vnfcResourceInfoId;
    return this;
  }

  /**
   * Identifier of the VnfcResourceInfo instance representing the virtualised resources used by this VNFC instance. Shall be present in case a corresponding VnfcResourceInfo instance exists. See note. NOTE: This allows to represent the error condition that a VNFC instance has lost its resources. 
   * @return vnfcResourceInfoId
  **/
  @ApiModelProperty(value = "Identifier of the VnfcResourceInfo instance representing the virtualised resources used by this VNFC instance. Shall be present in case a corresponding VnfcResourceInfo instance exists. See note. NOTE: This allows to represent the error condition that a VNFC instance has lost its resources. ")


  public String getVnfcResourceInfoId() {
    return vnfcResourceInfoId;
  }

  public void setVnfcResourceInfoId(String vnfcResourceInfoId) {
    this.vnfcResourceInfoId = vnfcResourceInfoId;
  }

  public VnfcInfo vnfcState(VnfcStateEnum vnfcState) {
    this.vnfcState = vnfcState;
    return this;
  }

  /**
   * State of the VNFC instance. Permitted values: • STARTED: The VNFC instance is up and running. • STOPPED: The VNFC instance has been shut down 
   * @return vnfcState
  **/
  @ApiModelProperty(required = true, value = "State of the VNFC instance. Permitted values: • STARTED: The VNFC instance is up and running. • STOPPED: The VNFC instance has been shut down ")
  @NotNull


  public VnfcStateEnum getVnfcState() {
    return vnfcState;
  }

  public void setVnfcState(VnfcStateEnum vnfcState) {
    this.vnfcState = vnfcState;
  }

  public VnfcInfo vnfcConfigurableProperties(KeyValuePairs vnfcConfigurableProperties) {
    this.vnfcConfigurableProperties = vnfcConfigurableProperties;
    return this;
  }

  /**
   * Current values of the configurable properties of the VNFC instance. Configurable properties referred in this attribute are declared in the VNFD. This attribute can be modified with the PATCH method. 
   * @return vnfcConfigurableProperties
  **/
  @ApiModelProperty(value = "Current values of the configurable properties of the VNFC instance. Configurable properties referred in this attribute are declared in the VNFD. This attribute can be modified with the PATCH method. ")

  @Valid

  public KeyValuePairs getVnfcConfigurableProperties() {
    return vnfcConfigurableProperties;
  }

  public void setVnfcConfigurableProperties(KeyValuePairs vnfcConfigurableProperties) {
    this.vnfcConfigurableProperties = vnfcConfigurableProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfcInfo vnfcInfo = (VnfcInfo) o;
    return Objects.equals(this.id, vnfcInfo.id) &&
        Objects.equals(this.vduId, vnfcInfo.vduId) &&
        Objects.equals(this.vnfcResourceInfoId, vnfcInfo.vnfcResourceInfoId) &&
        Objects.equals(this.vnfcState, vnfcInfo.vnfcState) &&
        Objects.equals(this.vnfcConfigurableProperties, vnfcInfo.vnfcConfigurableProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vduId, vnfcResourceInfoId, vnfcState, vnfcConfigurableProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
    sb.append("    vnfcResourceInfoId: ").append(toIndentedString(vnfcResourceInfoId)).append("\n");
    sb.append("    vnfcState: ").append(toIndentedString(vnfcState)).append("\n");
    sb.append("    vnfcConfigurableProperties: ").append(toIndentedString(vnfcConfigurableProperties)).append("\n");
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

