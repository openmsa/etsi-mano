package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

public class PmJobsPostResponse {
  
  @ApiModelProperty(value = "")
  @Valid
  private PmJobsPmJob pmJob = null;
 /**
   * Get pmJob
   * @return pmJob
  **/
  @JsonProperty("PmJob")
  public PmJobsPmJob getPmJob() {
    return pmJob;
  }

  public void setPmJob(PmJobsPmJob pmJob) {
    this.pmJob = pmJob;
  }

  public PmJobsPostResponse pmJob(PmJobsPmJob pmJob) {
    this.pmJob = pmJob;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobsPostResponse {\n");
    
    sb.append("    pmJob: ").append(toIndentedString(pmJob)).append("\n");
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

