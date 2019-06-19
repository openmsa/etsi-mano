package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * The POST request to this resource shall include a CancelMode structure in the payload body to choose between \"graceful\" and \"forceful\" cancellation. 
 **/
@ApiModel(description="The POST request to this resource shall include a CancelMode structure in the payload body to choose between \"graceful\" and \"forceful\" cancellation. ")
public class NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private Nslcmv1nsLcmOpOccsnsLcmOpOccIdcancelCancelMode cancelMode = null;
 /**
   * Get cancelMode
   * @return cancelMode
  **/
  @JsonProperty("CancelMode")
  @NotNull
  public Nslcmv1nsLcmOpOccsnsLcmOpOccIdcancelCancelMode getCancelMode() {
    return cancelMode;
  }

  public void setCancelMode(Nslcmv1nsLcmOpOccsnsLcmOpOccIdcancelCancelMode cancelMode) {
    this.cancelMode = cancelMode;
  }

  public NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery cancelMode(Nslcmv1nsLcmOpOccsnsLcmOpOccIdcancelCancelMode cancelMode) {
    this.cancelMode = cancelMode;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body6 {\n");
    
    sb.append("    cancelMode: ").append(toIndentedString(cancelMode)).append("\n");
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

