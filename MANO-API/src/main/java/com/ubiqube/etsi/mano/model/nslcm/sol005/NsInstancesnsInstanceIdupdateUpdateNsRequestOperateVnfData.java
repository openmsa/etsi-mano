package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type represents a VNF instance for which the operational state  needs to be changed and the requested new state. It shall comply with the provisions defined in Table 6.5.3.31-1. 
 **/
@ApiModel(description="This type represents a VNF instance for which the operational state  needs to be changed and the requested new state. It shall comply with the provisions defined in Table 6.5.3.31-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfInstanceId = null;


@XmlType(name="ChangeStateToEnum")
@XmlEnum(String.class)
public enum ChangeStateToEnum {

@XmlEnumValue("STARTED") STARTED(String.valueOf("STARTED")), @XmlEnumValue("STOPPED") STOPPED(String.valueOf("STOPPED"));


    private String value;

    ChangeStateToEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ChangeStateToEnum fromValue(String v) {
        for (ChangeStateToEnum b : ChangeStateToEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "STARTED - The VNF instance is up and running. STOPPED - The VNF instance has been shut down. ")
 /**
   * STARTED - The VNF instance is up and running. STOPPED - The VNF instance has been shut down. 
  **/
  private ChangeStateToEnum changeStateTo = null;


@XmlType(name="StopTypeEnum")
@XmlEnum(String.class)
public enum StopTypeEnum {

@XmlEnumValue("FORCEFUL") FORCEFUL(String.valueOf("FORCEFUL")), @XmlEnumValue("GRACEFUL") GRACEFUL(String.valueOf("GRACEFUL"));


    private String value;

    StopTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static StopTypeEnum fromValue(String v) {
        for (StopTypeEnum b : StopTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "* FORCEFUL: The VNFM will stop the VNF immediately after accepting the   request. * GRACEFUL: The VNFM will first arrange to take the VNF out of service   after accepting the request. Once that operation is successful or once   the timer value specified in the \"gracefulStopTimeout\" attribute   expires, the VNFM will stop the VNF. ")
 /**
   * * FORCEFUL: The VNFM will stop the VNF immediately after accepting the   request. * GRACEFUL: The VNFM will first arrange to take the VNF out of service   after accepting the request. Once that operation is successful or once   the timer value specified in the \"gracefulStopTimeout\" attribute   expires, the VNFM will stop the VNF. 
  **/
  private StopTypeEnum stopType = null;

  @ApiModelProperty(value = "The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF. ")
 /**
   * The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF. 
  **/
  private Integer gracefulStopTimeout = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfInstanceId
  **/
  @JsonProperty("vnfInstanceId")
  @NotNull
  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

 /**
   * STARTED - The VNF instance is up and running. STOPPED - The VNF instance has been shut down. 
   * @return changeStateTo
  **/
  @JsonProperty("changeStateTo")
  @NotNull
  public String getChangeStateTo() {
    if (changeStateTo == null) {
      return null;
    }
    return changeStateTo.value();
  }

  public void setChangeStateTo(ChangeStateToEnum changeStateTo) {
    this.changeStateTo = changeStateTo;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData changeStateTo(ChangeStateToEnum changeStateTo) {
    this.changeStateTo = changeStateTo;
    return this;
  }

 /**
   * * FORCEFUL: The VNFM will stop the VNF immediately after accepting the   request. * GRACEFUL: The VNFM will first arrange to take the VNF out of service   after accepting the request. Once that operation is successful or once   the timer value specified in the \&quot;gracefulStopTimeout\&quot; attribute   expires, the VNFM will stop the VNF. 
   * @return stopType
  **/
  @JsonProperty("stopType")
  public String getStopType() {
    if (stopType == null) {
      return null;
    }
    return stopType.value();
  }

  public void setStopType(StopTypeEnum stopType) {
    this.stopType = stopType;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData stopType(StopTypeEnum stopType) {
    this.stopType = stopType;
    return this;
  }

 /**
   * The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF. 
   * @return gracefulStopTimeout
  **/
  @JsonProperty("gracefulStopTimeout")
  public Integer getGracefulStopTimeout() {
    return gracefulStopTimeout;
  }

  public void setGracefulStopTimeout(Integer gracefulStopTimeout) {
    this.gracefulStopTimeout = gracefulStopTimeout;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData gracefulStopTimeout(Integer gracefulStopTimeout) {
    this.gracefulStopTimeout = gracefulStopTimeout;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    changeStateTo: ").append(toIndentedString(changeStateTo)).append("\n");
    sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
    sb.append("    gracefulStopTimeout: ").append(toIndentedString(gracefulStopTimeout)).append("\n");
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

