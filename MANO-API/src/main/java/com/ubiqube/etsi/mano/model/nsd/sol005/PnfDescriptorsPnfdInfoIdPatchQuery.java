package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Parameters for the modification of an individual PNF descriptor resource, as defined in clause 5.5.2.4.            
 **/
@ApiModel(description="Parameters for the modification of an individual PNF descriptor resource, as defined in clause 5.5.2.4.            ")
public class PnfDescriptorsPnfdInfoIdPatchQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private PnfDescriptorspnfdInfoIdPnfdInfoModifications pnfdInfoModifications = null;
 /**
   * Get pnfdInfoModifications
   * @return pnfdInfoModifications
  **/
  @JsonProperty("PnfdInfoModifications")
  @NotNull
  public PnfDescriptorspnfdInfoIdPnfdInfoModifications getPnfdInfoModifications() {
    return pnfdInfoModifications;
  }

  public void setPnfdInfoModifications(PnfDescriptorspnfdInfoIdPnfdInfoModifications pnfdInfoModifications) {
    this.pnfdInfoModifications = pnfdInfoModifications;
  }

  public PnfDescriptorsPnfdInfoIdPatchQuery pnfdInfoModifications(PnfDescriptorspnfdInfoIdPnfdInfoModifications pnfdInfoModifications) {
    this.pnfdInfoModifications = pnfdInfoModifications;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPostQuery {\n");
    
    sb.append("    pnfdInfoModifications: ").append(toIndentedString(pnfdInfoModifications)).append("\n");
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

