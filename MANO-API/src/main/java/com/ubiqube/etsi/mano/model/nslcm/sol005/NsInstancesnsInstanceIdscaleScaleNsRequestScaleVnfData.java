package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type represents defines the information to scale a VNF instance  to a given level, or to scale a VNF instance by steps. 
 **/
@ApiModel(description="This type represents defines the information to scale a VNF instance  to a given level, or to scale a VNF instance by steps. ")
public class NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfInstanceid = null;


@XmlType(name="ScaleVnfTypeEnum")
@XmlEnum(String.class)
public enum ScaleVnfTypeEnum {

@XmlEnumValue("SCALE_OUT") OUT(String.valueOf("SCALE_OUT")), @XmlEnumValue("SCALE_IN") IN(String.valueOf("SCALE_IN")), @XmlEnumValue("SCALE_TO_INSTANTIATION_LEVEL") TO_INSTANTIATION_LEVEL(String.valueOf("SCALE_TO_INSTANTIATION_LEVEL")), @XmlEnumValue("SCALE_TO_SCALE_LEVEL(S)") TO_SCALE_LEVEL_S_(String.valueOf("SCALE_TO_SCALE_LEVEL(S)"));


    private String value;

    ScaleVnfTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ScaleVnfTypeEnum fromValue(String v) {
        for (ScaleVnfTypeEnum b : ScaleVnfTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT - SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of types actually supported depends on the capabilities of the VNF being managed. ")
 /**
   * Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT - SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of types actually supported depends on the capabilities of the VNF being managed. 
  **/
  private ScaleVnfTypeEnum scaleVnfType = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData scaleToLevelData = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData scaleByStepData = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfInstanceid
  **/
  @JsonProperty("vnfInstanceid")
  @NotNull
  public String getVnfInstanceid() {
    return vnfInstanceid;
  }

  public void setVnfInstanceid(String vnfInstanceid) {
    this.vnfInstanceid = vnfInstanceid;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData vnfInstanceid(String vnfInstanceid) {
    this.vnfInstanceid = vnfInstanceid;
    return this;
  }

 /**
   * Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT - SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of types actually supported depends on the capabilities of the VNF being managed. 
   * @return scaleVnfType
  **/
  @JsonProperty("scaleVnfType")
  @NotNull
  public String getScaleVnfType() {
    if (scaleVnfType == null) {
      return null;
    }
    return scaleVnfType.value();
  }

  public void setScaleVnfType(ScaleVnfTypeEnum scaleVnfType) {
    this.scaleVnfType = scaleVnfType;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData scaleVnfType(ScaleVnfTypeEnum scaleVnfType) {
    this.scaleVnfType = scaleVnfType;
    return this;
  }

 /**
   * Get scaleToLevelData
   * @return scaleToLevelData
  **/
  @JsonProperty("scaleToLevelData")
  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData getScaleToLevelData() {
    return scaleToLevelData;
  }

  public void setScaleToLevelData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData scaleToLevelData) {
    this.scaleToLevelData = scaleToLevelData;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData scaleToLevelData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData scaleToLevelData) {
    this.scaleToLevelData = scaleToLevelData;
    return this;
  }

 /**
   * Get scaleByStepData
   * @return scaleByStepData
  **/
  @JsonProperty("scaleByStepData")
  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData getScaleByStepData() {
    return scaleByStepData;
  }

  public void setScaleByStepData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData scaleByStepData) {
    this.scaleByStepData = scaleByStepData;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData scaleByStepData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleByStepData scaleByStepData) {
    this.scaleByStepData = scaleByStepData;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData {\n");
    
    sb.append("    vnfInstanceid: ").append(toIndentedString(vnfInstanceid)).append("\n");
    sb.append("    scaleVnfType: ").append(toIndentedString(scaleVnfType)).append("\n");
    sb.append("    scaleToLevelData: ").append(toIndentedString(scaleToLevelData)).append("\n");
    sb.append("    scaleByStepData: ").append(toIndentedString(scaleByStepData)).append("\n");
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

