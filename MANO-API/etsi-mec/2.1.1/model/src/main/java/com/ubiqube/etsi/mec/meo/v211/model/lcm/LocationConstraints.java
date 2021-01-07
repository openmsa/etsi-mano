package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.CivicAddressElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The LocationConstraints data type supports the specification of MEC application requirements related to MEC application deployment location constraints. The location constraints shall be presented as a country code, optionally followed by a civic address based on the format defined by IETF RFC 4776&#x27;
 */
@ApiModel(description = "'The LocationConstraints data type supports the specification of MEC application requirements related to MEC application deployment location constraints. The location constraints shall be presented as a country code, optionally followed by a civic address based on the format defined by IETF RFC 4776'")
@Validated
public class LocationConstraints   {
  @JsonProperty("countryCode")
  private String countryCode = null;

  @JsonProperty("civicAddressElement")
  @Valid
  private List<CivicAddressElement> civicAddressElement = null;

  public LocationConstraints countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Get countryCode
   * @return countryCode
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public LocationConstraints civicAddressElement(List<CivicAddressElement> civicAddressElement) {
    this.civicAddressElement = civicAddressElement;
    return this;
  }

  public LocationConstraints addCivicAddressElementItem(CivicAddressElement civicAddressElementItem) {
    if (this.civicAddressElement == null) {
      this.civicAddressElement = new ArrayList<>();
    }
    this.civicAddressElement.add(civicAddressElementItem);
    return this;
  }

  /**
   * Get civicAddressElement
   * @return civicAddressElement
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<CivicAddressElement> getCivicAddressElement() {
    return civicAddressElement;
  }

  public void setCivicAddressElement(List<CivicAddressElement> civicAddressElement) {
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
