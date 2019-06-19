package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;

/**
  * This type represents a request for the scale NS operation.        
 **/
@ApiModel(description="This type represents a request for the scale NS operation.        ")
public class NsInstancesnsInstanceIdscaleScaleNsRequest  {
  

@XmlType(name="ScaleTypeEnum")
@XmlEnum(String.class)
public enum ScaleTypeEnum {

@XmlEnumValue("SCALE_NS") NS(String.valueOf("SCALE_NS")), @XmlEnumValue("SCALE_VNF") VNF(String.valueOf("SCALE_VNF"));


    private String value;

    ScaleTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ScaleTypeEnum fromValue(String v) {
        for (ScaleTypeEnum b : ScaleTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "Indicates the type of scaling to be performed. Possible values: - SCALE_NS - SCALE_VNF ")
 /**
   * Indicates the type of scaling to be performed. Possible values: - SCALE_NS - SCALE_VNF 
  **/
  private ScaleTypeEnum scaleType = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData scaleNsData = null;

  @ApiModelProperty(value = "The necessary information to scale the referenced NS instance. It shall be present when scaleType = SCALE_VNF. ")
  @Valid
 /**
   * The necessary information to scale the referenced NS instance. It shall be present when scaleType = SCALE_VNF. 
  **/
  private List<NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData> scaleVnfData = null;

  @ApiModelProperty(value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date scaleTime = null;
 /**
   * Indicates the type of scaling to be performed. Possible values: - SCALE_NS - SCALE_VNF 
   * @return scaleType
  **/
  @JsonProperty("scaleType")
  @NotNull
  public String getScaleType() {
    if (scaleType == null) {
      return null;
    }
    return scaleType.value();
  }

  public void setScaleType(ScaleTypeEnum scaleType) {
    this.scaleType = scaleType;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequest scaleType(ScaleTypeEnum scaleType) {
    this.scaleType = scaleType;
    return this;
  }

 /**
   * Get scaleNsData
   * @return scaleNsData
  **/
  @JsonProperty("scaleNsData")
  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData getScaleNsData() {
    return scaleNsData;
  }

  public void setScaleNsData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData scaleNsData) {
    this.scaleNsData = scaleNsData;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequest scaleNsData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData scaleNsData) {
    this.scaleNsData = scaleNsData;
    return this;
  }

 /**
   * The necessary information to scale the referenced NS instance. It shall be present when scaleType &#x3D; SCALE_VNF. 
   * @return scaleVnfData
  **/
  @JsonProperty("scaleVnfData")
  public List<NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData> getScaleVnfData() {
    return scaleVnfData;
  }

  public void setScaleVnfData(List<NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData> scaleVnfData) {
    this.scaleVnfData = scaleVnfData;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequest scaleVnfData(List<NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData> scaleVnfData) {
    this.scaleVnfData = scaleVnfData;
    return this;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequest addScaleVnfDataItem(NsInstancesnsInstanceIdscaleScaleNsRequestScaleVnfData scaleVnfDataItem) {
    this.scaleVnfData.add(scaleVnfDataItem);
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return scaleTime
  **/
  @JsonProperty("scaleTime")
  public Date getScaleTime() {
    return scaleTime;
  }

  public void setScaleTime(Date scaleTime) {
    this.scaleTime = scaleTime;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequest scaleTime(Date scaleTime) {
    this.scaleTime = scaleTime;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdscaleScaleNsRequest {\n");
    
    sb.append("    scaleType: ").append(toIndentedString(scaleType)).append("\n");
    sb.append("    scaleNsData: ").append(toIndentedString(scaleNsData)).append("\n");
    sb.append("    scaleVnfData: ").append(toIndentedString(scaleVnfData)).append("\n");
    sb.append("    scaleTime: ").append(toIndentedString(scaleTime)).append("\n");
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

