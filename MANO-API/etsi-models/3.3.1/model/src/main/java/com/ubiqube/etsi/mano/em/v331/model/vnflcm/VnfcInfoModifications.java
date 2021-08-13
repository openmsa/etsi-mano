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
package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents modifications of an entry in an array of \&quot;VnfcInfo\&quot; objects. It shall comply with the provisions defined in table 5.5.3.24-1. 
 */
@Schema(description = "This type represents modifications of an entry in an array of \"VnfcInfo\" objects. It shall comply with the provisions defined in table 5.5.3.24-1. ")
@Validated


public class VnfcInfoModifications   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfcConfigurableProperties")
  private Map<String, String> vnfcConfigurableProperties = null;

  public VnfcInfoModifications id(String id) {
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

  public VnfcInfoModifications vnfcConfigurableProperties(Map<String, String> vnfcConfigurableProperties) {
    this.vnfcConfigurableProperties = vnfcConfigurableProperties;
    return this;
  }

  /**
   * Get vnfcConfigurableProperties
   * @return vnfcConfigurableProperties
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Map<String, String> getVnfcConfigurableProperties() {
    return vnfcConfigurableProperties;
  }

  public void setVnfcConfigurableProperties(Map<String, String> vnfcConfigurableProperties) {
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
    VnfcInfoModifications vnfcInfoModifications = (VnfcInfoModifications) o;
    return Objects.equals(this.id, vnfcInfoModifications.id) &&
        Objects.equals(this.vnfcConfigurableProperties, vnfcInfoModifications.vnfcConfigurableProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfcConfigurableProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcInfoModifications {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
