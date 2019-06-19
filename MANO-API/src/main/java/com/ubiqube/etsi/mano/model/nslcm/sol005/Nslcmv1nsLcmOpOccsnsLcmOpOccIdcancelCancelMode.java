package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type represents a parameter to select the mode of canceling an ongoing NS LCM operation occurrence.  It shall comply with the provisions defined in Table 6.5.2.16-1.. 
 **/
@ApiModel(description="This type represents a parameter to select the mode of canceling an ongoing NS LCM operation occurrence.  It shall comply with the provisions defined in Table 6.5.2.16-1.. ")
public class Nslcmv1nsLcmOpOccsnsLcmOpOccIdcancelCancelMode  {
  

@XmlType(name="CancelModeEnum")
@XmlEnum(String.class)
public enum CancelModeEnum {

@XmlEnumValue("GRACEFUL") GRACEFUL(String.valueOf("GRACEFUL")), @XmlEnumValue("FORCEFUL") FORCEFUL(String.valueOf("FORCEFUL"));


    private String value;

    CancelModeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CancelModeEnum fromValue(String v) {
        for (CancelModeEnum b : CancelModeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "Cancellation mode. The NFVO shall not start any new VNF lifecycle management and resource management operation, and shall wait for the ongoing VNF lifecycle management and resource management operations in the underlying system, typically the VNFM and VIM, to finish execution or to time out. After that, the NFVO shall put the operation occurrence into the FAILED_TEMP state. The NFVO shall not start any new VNF lifecycle management and resource management operation, shall cancel the ongoing VNF lifecycle management and resource management operations in the underlying system, typically the VNFM and VIM, and shall wait for the cancellation to finish or to time out. After that, the NFVO shall put the operation occurrence into the FAILED_TEMP state. ")
 /**
   * Cancellation mode. The NFVO shall not start any new VNF lifecycle management and resource management operation, and shall wait for the ongoing VNF lifecycle management and resource management operations in the underlying system, typically the VNFM and VIM, to finish execution or to time out. After that, the NFVO shall put the operation occurrence into the FAILED_TEMP state. The NFVO shall not start any new VNF lifecycle management and resource management operation, shall cancel the ongoing VNF lifecycle management and resource management operations in the underlying system, typically the VNFM and VIM, and shall wait for the cancellation to finish or to time out. After that, the NFVO shall put the operation occurrence into the FAILED_TEMP state. 
  **/
  private CancelModeEnum cancelMode = null;
 /**
   * Cancellation mode. The NFVO shall not start any new VNF lifecycle management and resource management operation, and shall wait for the ongoing VNF lifecycle management and resource management operations in the underlying system, typically the VNFM and VIM, to finish execution or to time out. After that, the NFVO shall put the operation occurrence into the FAILED_TEMP state. The NFVO shall not start any new VNF lifecycle management and resource management operation, shall cancel the ongoing VNF lifecycle management and resource management operations in the underlying system, typically the VNFM and VIM, and shall wait for the cancellation to finish or to time out. After that, the NFVO shall put the operation occurrence into the FAILED_TEMP state. 
   * @return cancelMode
  **/
  @JsonProperty("cancelMode")
  @NotNull
  public String getCancelMode() {
    if (cancelMode == null) {
      return null;
    }
    return cancelMode.value();
  }

  public void setCancelMode(CancelModeEnum cancelMode) {
    this.cancelMode = cancelMode;
  }

  public Nslcmv1nsLcmOpOccsnsLcmOpOccIdcancelCancelMode cancelMode(CancelModeEnum cancelMode) {
    this.cancelMode = cancelMode;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Nslcmv1nsLcmOpOccsnsLcmOpOccIdcancelCancelMode {\n");
    
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

