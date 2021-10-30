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
package com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents modifications to the information of a VNF snapshot package.  It shall comply with the provisions defined in table 11.5.2.4-1. NOTE: At least one of the three parameters shall be present. If the VNF snapshot is not uploaded or built, the operation is used only to update existing or add additional user defined data using the userDefinedData attribute. 
 */
@Schema(description = "This type represents modifications to the information of a VNF snapshot package.  It shall comply with the provisions defined in table 11.5.2.4-1. NOTE: At least one of the three parameters shall be present. If the VNF snapshot is not uploaded or built, the operation is used only to update existing or add additional user defined data using the userDefinedData attribute. ")
@Validated


public class VnfSnapshotPkgInfoModifications  implements AnyOfVnfSnapshotPkgInfoModifications {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  /**
   * New value of the \"state\" of the VNF snapshot package. Permitted values:  - AVAILABLE: to change the \"Individual VNF snapshot package\" resource state to \"AVAILABLE\". Explicit change of state is only permitted from the following states: - ERROR_EXTRACTING  See note. 
   */
  public enum StateEnum {
    AVAILABLE("AVAILABLE");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("state")
  private StateEnum state = null;

  public VnfSnapshotPkgInfoModifications name(String name) {
    this.name = name;
    return this;
  }

  /**
   * New value of the human-readable name of the VNF snapshot package.  See note. 
   * @return name
   **/
  @Schema(description = "New value of the human-readable name of the VNF snapshot package.  See note. ")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VnfSnapshotPkgInfoModifications userDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  /**
   * Get userDefinedData
   * @return userDefinedData
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
  }

  public VnfSnapshotPkgInfoModifications state(StateEnum state) {
    this.state = state;
    return this;
  }

  /**
   * New value of the \"state\" of the VNF snapshot package. Permitted values:  - AVAILABLE: to change the \"Individual VNF snapshot package\" resource state to \"AVAILABLE\". Explicit change of state is only permitted from the following states: - ERROR_EXTRACTING  See note. 
   * @return state
   **/
  @Schema(description = "New value of the \"state\" of the VNF snapshot package. Permitted values:  - AVAILABLE: to change the \"Individual VNF snapshot package\" resource state to \"AVAILABLE\". Explicit change of state is only permitted from the following states: - ERROR_EXTRACTING  See note. ")
  
    public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfSnapshotPkgInfoModifications vnfSnapshotPkgInfoModifications = (VnfSnapshotPkgInfoModifications) o;
    return Objects.equals(this.name, vnfSnapshotPkgInfoModifications.name) &&
        Objects.equals(this.userDefinedData, vnfSnapshotPkgInfoModifications.userDefinedData) &&
        Objects.equals(this.state, vnfSnapshotPkgInfoModifications.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, userDefinedData, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfSnapshotPkgInfoModifications {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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
