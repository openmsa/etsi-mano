package com.ubiqube.etsi.mano.model.nsperfo.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * The VNF creation parameters.             
 **/
@ApiModel(description="The VNF creation parameters.             ")
public class CreatePmJobRequest  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private PmJobsCreatePmJobRequest createPmJobRequest = null;
 /**
   * Get createPmJobRequest
   * @return createPmJobRequest
  **/
  @JsonProperty("CreatePmJobRequest")
  @NotNull
  public PmJobsCreatePmJobRequest getCreatePmJobRequest() {
    return createPmJobRequest;
  }

  public void setCreatePmJobRequest(PmJobsCreatePmJobRequest createPmJobRequest) {
    this.createPmJobRequest = createPmJobRequest;
  }

  public CreatePmJobRequest createPmJobRequest(PmJobsCreatePmJobRequest createPmJobRequest) {
    this.createPmJobRequest = createPmJobRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePmJobRequest {\n");
    
    sb.append("    createPmJobRequest: ").append(toIndentedString(createPmJobRequest)).append("\n");
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

