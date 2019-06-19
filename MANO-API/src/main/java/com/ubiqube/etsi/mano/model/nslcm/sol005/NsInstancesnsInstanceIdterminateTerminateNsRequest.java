package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
  * This type represents a NS termination request.  It shall comply with the provisions defined in Table 6.5.2.15-1. 
 **/
@ApiModel(description="This type represents a NS termination request.  It shall comply with the provisions defined in Table 6.5.2.15-1. ")
public class NsInstancesnsInstanceIdterminateTerminateNsRequest  {
  
  @ApiModelProperty(value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date terminationTime = null;
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return terminationTime
  **/
  @JsonProperty("terminationTime")
  public Date getTerminationTime() {
    return terminationTime;
  }

  public void setTerminationTime(Date terminationTime) {
    this.terminationTime = terminationTime;
  }

  public NsInstancesnsInstanceIdterminateTerminateNsRequest terminationTime(Date terminationTime) {
    this.terminationTime = terminationTime;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdterminateTerminateNsRequest {\n");
    
    sb.append("    terminationTime: ").append(toIndentedString(terminationTime)).append("\n");
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

