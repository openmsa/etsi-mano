package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
  * This type represents the information used to scale an NS instance to a target size. The target size is either expressed as an NS instantiation level or as a list of NS scale levels, one per NS scaling aspect, of the current DF. The NS instantiation levels, the NS scaling aspects and their corresponding NS scale levels applicable to the NS instance are declared in the NSD. 
 **/
@ApiModel(description="This type represents the information used to scale an NS instance to a target size. The target size is either expressed as an NS instantiation level or as a list of NS scale levels, one per NS scaling aspect, of the current DF. The NS instantiation levels, the NS scaling aspects and their corresponding NS scale levels applicable to the NS instance are declared in the NSD. ")
public class NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData  {
  
  @ApiModelProperty(value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String nsInstantiationLevel = null;

  @ApiModelProperty(value = "For each NS scaling aspect of the current DF, defines the target NS scale level to which the NS instance is to be scaled. ")
  @Valid
 /**
   * For each NS scaling aspect of the current DF, defines the target NS scale level to which the NS instance is to be scaled. 
  **/
  private List<NsInstancesNsInstanceNsScaleStatus> nsScaleInfo = null;
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return nsInstantiationLevel
  **/
  @JsonProperty("nsInstantiationLevel")
  public String getNsInstantiationLevel() {
    return nsInstantiationLevel;
  }

  public void setNsInstantiationLevel(String nsInstantiationLevel) {
    this.nsInstantiationLevel = nsInstantiationLevel;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData nsInstantiationLevel(String nsInstantiationLevel) {
    this.nsInstantiationLevel = nsInstantiationLevel;
    return this;
  }

 /**
   * For each NS scaling aspect of the current DF, defines the target NS scale level to which the NS instance is to be scaled. 
   * @return nsScaleInfo
  **/
  @JsonProperty("nsScaleInfo")
  public List<NsInstancesNsInstanceNsScaleStatus> getNsScaleInfo() {
    return nsScaleInfo;
  }

  public void setNsScaleInfo(List<NsInstancesNsInstanceNsScaleStatus> nsScaleInfo) {
    this.nsScaleInfo = nsScaleInfo;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData nsScaleInfo(List<NsInstancesNsInstanceNsScaleStatus> nsScaleInfo) {
    this.nsScaleInfo = nsScaleInfo;
    return this;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData addNsScaleInfoItem(NsInstancesNsInstanceNsScaleStatus nsScaleInfoItem) {
    this.nsScaleInfo.add(nsScaleInfoItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData {\n");
    
    sb.append("    nsInstantiationLevel: ").append(toIndentedString(nsInstantiationLevel)).append("\n");
    sb.append("    nsScaleInfo: ").append(toIndentedString(nsScaleInfo)).append("\n");
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

