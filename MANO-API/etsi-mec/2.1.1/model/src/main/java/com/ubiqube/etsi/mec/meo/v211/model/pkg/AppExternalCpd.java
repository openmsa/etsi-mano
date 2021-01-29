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
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AppExternalCpd
 */
@Validated
public class AppExternalCpd   {
  @JsonProperty("inherited_attributes")
  private Object inheritedAttributes = null;

  @JsonProperty("virtualNetworkInterfaceRequirements")
  @Valid
  private List<String> virtualNetworkInterfaceRequirements = null;

  public AppExternalCpd inheritedAttributes(Object inheritedAttributes) {
    this.inheritedAttributes = inheritedAttributes;
    return this;
  }

  /**
   * All attributes inherited from Cpd.
   * @return inheritedAttributes
  **/
  @ApiModelProperty(required = true, value = "All attributes inherited from Cpd.")
      @NotNull

    public Object getInheritedAttributes() {
    return inheritedAttributes;
  }

  public void setInheritedAttributes(Object inheritedAttributes) {
    this.inheritedAttributes = inheritedAttributes;
  }

  public AppExternalCpd virtualNetworkInterfaceRequirements(List<String> virtualNetworkInterfaceRequirements) {
    this.virtualNetworkInterfaceRequirements = virtualNetworkInterfaceRequirements;
    return this;
  }

  public AppExternalCpd addVirtualNetworkInterfaceRequirementsItem(String virtualNetworkInterfaceRequirementsItem) {
    if (this.virtualNetworkInterfaceRequirements == null) {
      this.virtualNetworkInterfaceRequirements = new ArrayList<>();
    }
    this.virtualNetworkInterfaceRequirements.add(virtualNetworkInterfaceRequirementsItem);
    return this;
  }

  /**
   * Specifies requirements on a virtual network interface realizing the CPs instantiated from this CPD.
   * @return virtualNetworkInterfaceRequirements
  **/
  @ApiModelProperty(value = "Specifies requirements on a virtual network interface realizing the CPs instantiated from this CPD.")
  
    public List<String> getVirtualNetworkInterfaceRequirements() {
    return virtualNetworkInterfaceRequirements;
  }

  public void setVirtualNetworkInterfaceRequirements(List<String> virtualNetworkInterfaceRequirements) {
    this.virtualNetworkInterfaceRequirements = virtualNetworkInterfaceRequirements;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppExternalCpd appExternalCpd = (AppExternalCpd) o;
    return Objects.equals(this.inheritedAttributes, appExternalCpd.inheritedAttributes) &&
        Objects.equals(this.virtualNetworkInterfaceRequirements, appExternalCpd.virtualNetworkInterfaceRequirements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inheritedAttributes, virtualNetworkInterfaceRequirements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppExternalCpd {\n");
    
    sb.append("    inheritedAttributes: ").append(toIndentedString(inheritedAttributes)).append("\n");
    sb.append("    virtualNetworkInterfaceRequirements: ").append(toIndentedString(virtualNetworkInterfaceRequirements)).append("\n");
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
