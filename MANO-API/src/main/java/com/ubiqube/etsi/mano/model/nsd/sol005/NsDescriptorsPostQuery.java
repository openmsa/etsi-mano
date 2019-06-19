package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * Parameters of creating an NS descriptor resource, as defined in clause 5.5.2.3 
 **/
@ApiModel(description="Parameters of creating an NS descriptor resource, as defined in clause 5.5.2.3 ")
public class NsDescriptorsPostQuery  {
  
  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsDescriptorsCreateNsdInfoRequest createNsdInfoRequest = null;
 /**
   * Get createNsdInfoRequest
   * @return createNsdInfoRequest
  **/
  @JsonProperty("CreateNsdInfoRequest")
  @NotNull
  public NsDescriptorsCreateNsdInfoRequest getCreateNsdInfoRequest() {
    return createNsdInfoRequest;
  }

  public void setCreateNsdInfoRequest(NsDescriptorsCreateNsdInfoRequest createNsdInfoRequest) {
    this.createNsdInfoRequest = createNsdInfoRequest;
  }

  public NsDescriptorsPostQuery createNsdInfoRequest(NsDescriptorsCreateNsdInfoRequest createNsdInfoRequest) {
    this.createNsdInfoRequest = createNsdInfoRequest;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagePostQuery {\n");
    
    sb.append("    createNsdInfoRequest: ").append(toIndentedString(createNsdInfoRequest)).append("\n");
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

