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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v271.model.nslcm.LocationConstraintsCivicAddressElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents location constraints for a VNF to be instantiated. The location constraints shall be presented as a country code, optionally followed by a civic address based on the format defined by IETF RFC 4776 [13]. 
 */
@ApiModel(description = "This type represents location constraints for a VNF to be instantiated. The location constraints shall be presented as a country code, optionally followed by a civic address based on the format defined by IETF RFC 4776 [13]. ")
@Validated
public class LocationConstraints   {
  @JsonProperty("countryCode")
  private String countryCode = null;

  @JsonProperty("civicAddressElement")
  @Valid
  private List<LocationConstraintsCivicAddressElement> civicAddressElement = null;

  public LocationConstraints countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * The two-letter ISO 3166 [29] country code in capital letters. 
   * @return countryCode
  **/
  @ApiModelProperty(required = true, value = "The two-letter ISO 3166 [29] country code in capital letters. ")
      @NotNull

    public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public LocationConstraints civicAddressElement(List<LocationConstraintsCivicAddressElement> civicAddressElement) {
    this.civicAddressElement = civicAddressElement;
    return this;
  }

  public LocationConstraints addCivicAddressElementItem(LocationConstraintsCivicAddressElement civicAddressElementItem) {
    if (this.civicAddressElement == null) {
      this.civicAddressElement = new ArrayList<>();
    }
    this.civicAddressElement.add(civicAddressElementItem);
    return this;
  }

  /**
   * Zero or more elements comprising the civic address. 
   * @return civicAddressElement
  **/
  @ApiModelProperty(value = "Zero or more elements comprising the civic address. ")
      @Valid
    public List<LocationConstraintsCivicAddressElement> getCivicAddressElement() {
    return civicAddressElement;
  }

  public void setCivicAddressElement(List<LocationConstraintsCivicAddressElement> civicAddressElement) {
    this.civicAddressElement = civicAddressElement;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationConstraints locationConstraints = (LocationConstraints) o;
    return Objects.equals(this.countryCode, locationConstraints.countryCode) &&
        Objects.equals(this.civicAddressElement, locationConstraints.civicAddressElement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countryCode, civicAddressElement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationConstraints {\n");
    
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    civicAddressElement: ").append(toIndentedString(civicAddressElement)).append("\n");
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
