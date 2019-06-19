package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Parameters for the instantiate NS operation, as defined in clause 6.5.2.10. 
 **/
@ApiModel(description="Parameters for the instantiate NS operation, as defined in clause 6.5.2.10. ")
public class NsInstancesNsInstanceIdInstantiatePostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesnsInstanceIdinstantiateInstantiateNsRequest instantiateNsRequest = null;
 /**
   * Get instantiateNsRequest
   * @return instantiateNsRequest
  **/
  @JsonProperty("InstantiateNsRequest")
  @NotNull
  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest getInstantiateNsRequest() {
    return instantiateNsRequest;
  }

  public void setInstantiateNsRequest(NsInstancesnsInstanceIdinstantiateInstantiateNsRequest instantiateNsRequest) {
    this.instantiateNsRequest = instantiateNsRequest;
  }

  public NsInstancesNsInstanceIdInstantiatePostQuery instantiateNsRequest(NsInstancesnsInstanceIdinstantiateInstantiateNsRequest instantiateNsRequest) {
    this.instantiateNsRequest = instantiateNsRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class vnfPackagesVnfPkgIdPatchQuery {\n");
    
    sb.append("    instantiateNsRequest: ").append(toIndentedString(instantiateNsRequest)).append("\n");
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

