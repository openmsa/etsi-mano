package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus  {
  
  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String aspectId = null;

  @ApiModelProperty(required = true, value = "Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD. ")
 /**
   * Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD. 
  **/
  private Integer scaleLevel = null;
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return aspectId
  **/
  @JsonProperty("aspectId")
  @NotNull
  public String getAspectId() {
    return aspectId;
  }

  public void setAspectId(String aspectId) {
    this.aspectId = aspectId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus aspectId(String aspectId) {
    this.aspectId = aspectId;
    return this;
  }

 /**
   * Indicates the scale level. The minimum value shall be 0 and the maximum value shall be &lt;&#x3D; maxScaleLevel as described in the VNFD. 
   * @return scaleLevel
  **/
  @JsonProperty("scaleLevel")
  @NotNull
  public Integer getScaleLevel() {
    return scaleLevel;
  }

  public void setScaleLevel(Integer scaleLevel) {
    this.scaleLevel = scaleLevel;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus scaleLevel(Integer scaleLevel) {
    this.scaleLevel = scaleLevel;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus {\n");
    
    sb.append("    aspectId: ").append(toIndentedString(aspectId)).append("\n");
    sb.append("    scaleLevel: ").append(toIndentedString(scaleLevel)).append("\n");
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

