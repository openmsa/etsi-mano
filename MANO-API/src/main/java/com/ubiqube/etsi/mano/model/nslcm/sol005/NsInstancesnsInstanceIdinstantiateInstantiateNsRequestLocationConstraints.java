package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type represents location constraints for a VNF to be instantiated.  The location constraints shall be presented as a country code, optionally followed by a civic address based on  the format defined by IETF RFC 4776 [13]. 
 **/
@ApiModel(description="This type represents location constraints for a VNF to be instantiated.  The location constraints shall be presented as a country code, optionally followed by a civic address based on  the format defined by IETF RFC 4776 [13]. ")
public class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints  {
  
  @ApiModelProperty(required = true, value = "The two-letter ISO 3166 [29] country code in capital letters. ")
 /**
   * The two-letter ISO 3166 [29] country code in capital letters. 
  **/
  private String countryCode = null;

  @ApiModelProperty(value = "Zero or more elements comprising the civic address. ")
  @Valid
 /**
   * Zero or more elements comprising the civic address. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement> civicAddressElement = null;
 /**
   * The two-letter ISO 3166 [29] country code in capital letters. 
   * @return countryCode
  **/
  @JsonProperty("countryCode")
  @NotNull
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

 /**
   * Zero or more elements comprising the civic address. 
   * @return civicAddressElement
  **/
  @JsonProperty("civicAddressElement")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement> getCivicAddressElement() {
    return civicAddressElement;
  }

  public void setCivicAddressElement(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement> civicAddressElement) {
    this.civicAddressElement = civicAddressElement;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints civicAddressElement(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement> civicAddressElement) {
    this.civicAddressElement = civicAddressElement;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints addCivicAddressElementItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement civicAddressElementItem) {
    this.civicAddressElement.add(civicAddressElementItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints {\n");
    
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    civicAddressElement: ").append(toIndentedString(civicAddressElement)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

