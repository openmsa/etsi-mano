package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class InlineResponse2001  {
  
  @ApiModelProperty(value = "")
  @Valid
  private NsDescriptorsnsdInfoIdNsdInfoModifications nsdInfoModifications = null;
 /**
   * Get nsdInfoModifications
   * @return nsdInfoModifications
  **/
  @JsonProperty("NsdInfoModifications")
  public NsDescriptorsnsdInfoIdNsdInfoModifications getNsdInfoModifications() {
    return nsdInfoModifications;
  }

  public void setNsdInfoModifications(NsDescriptorsnsdInfoIdNsdInfoModifications nsdInfoModifications) {
    this.nsdInfoModifications = nsdInfoModifications;
  }

  public InlineResponse2001 nsdInfoModifications(NsDescriptorsnsdInfoIdNsdInfoModifications nsdInfoModifications) {
    this.nsdInfoModifications = nsdInfoModifications;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPmJobIdReportsReportIdGetResponse {\n");
    
    sb.append("    nsdInfoModifications: ").append(toIndentedString(nsdInfoModifications)).append("\n");
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

