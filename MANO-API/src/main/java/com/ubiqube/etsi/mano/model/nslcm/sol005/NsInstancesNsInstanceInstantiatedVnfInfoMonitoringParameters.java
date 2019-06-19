package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters  {
  
  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String id = null;

  @ApiModelProperty(value = "Human readable name of the monitoring parameter, as defined in the VNFD. ")
 /**
   * Human readable name of the monitoring parameter, as defined in the VNFD. 
  **/
  private String name = null;

  @ApiModelProperty(required = true, value = "Value of the monitoring parameter known to the VNFM (e.g. obtained for auto-scaling purposes). The type of the \"value\" attribute (i.e. scalar, structure (Object in JSON), or array (of scalars, arrays or structures/Objects)) is assumed to be defined in an external measurement specification. ")
 /**
   * Value of the monitoring parameter known to the VNFM (e.g. obtained for auto-scaling purposes). The type of the \"value\" attribute (i.e. scalar, structure (Object in JSON), or array (of scalars, arrays or structures/Objects)) is assumed to be defined in an external measurement specification. 
  **/
  private Object value = null;

  @ApiModelProperty(required = true, value = "Represents the point in time when the measurement has been performed, as known to the VNFM. Should be formatted according to ETF RFC 3339. ")
 /**
   * Represents the point in time when the measurement has been performed, as known to the VNFM. Should be formatted according to ETF RFC 3339. 
  **/
  private String timeStamp = null;
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Human readable name of the monitoring parameter, as defined in the VNFD. 
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Value of the monitoring parameter known to the VNFM (e.g. obtained for auto-scaling purposes). The type of the \&quot;value\&quot; attribute (i.e. scalar, structure (Object in JSON), or array (of scalars, arrays or structures/Objects)) is assumed to be defined in an external measurement specification. 
   * @return value
  **/
  @JsonProperty("value")
  @NotNull
  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters value(Object value) {
    this.value = value;
    return this;
  }

 /**
   * Represents the point in time when the measurement has been performed, as known to the VNFM. Should be formatted according to ETF RFC 3339. 
   * @return timeStamp
  **/
  @JsonProperty("timeStamp")
  @NotNull
  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters timeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
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

