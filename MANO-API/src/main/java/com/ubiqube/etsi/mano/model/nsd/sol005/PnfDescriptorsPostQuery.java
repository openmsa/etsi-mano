package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Parameters of creating a PNF descriptor resource.         
 **/
@ApiModel(description="Parameters of creating a PNF descriptor resource.         ")
public class PnfDescriptorsPostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private PnfDescriptorsCreatePnfdInfoRequest createPnfdInfoRequest = null;
 /**
   * Get createPnfdInfoRequest
   * @return createPnfdInfoRequest
  **/
  @JsonProperty("CreatePnfdInfoRequest")
  @NotNull
  public PnfDescriptorsCreatePnfdInfoRequest getCreatePnfdInfoRequest() {
    return createPnfdInfoRequest;
  }

  public void setCreatePnfdInfoRequest(PnfDescriptorsCreatePnfdInfoRequest createPnfdInfoRequest) {
    this.createPnfdInfoRequest = createPnfdInfoRequest;
  }

  public PnfDescriptorsPostQuery createPnfdInfoRequest(PnfDescriptorsCreatePnfdInfoRequest createPnfdInfoRequest) {
    this.createPnfdInfoRequest = createPnfdInfoRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body2 {\n");
    
    sb.append("    createPnfdInfoRequest: ").append(toIndentedString(createPnfdInfoRequest)).append("\n");
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

