package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
  * This type describes the information used to scale a VNF instance to a target size. The target size is either expressed as an instantiation level of that DF as defined in the VNFD, or given as a list of scale levels, one per scaling aspect of that DF. Instantiation levels and scaling aspects are declared in the VNFD. The NFVO shall then invoke the ScaleVnfToLevel operation towards the appropriate VNFM.. 
 **/
@ApiModel(description="This type describes the information used to scale a VNF instance to a target size. The target size is either expressed as an instantiation level of that DF as defined in the VNFD, or given as a list of scale levels, one per scaling aspect of that DF. Instantiation levels and scaling aspects are declared in the VNFD. The NFVO shall then invoke the ScaleVnfToLevel operation towards the appropriate VNFM.. ")
public class NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData  {
  
  @ApiModelProperty(value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String vnfInstantiationLevelId = null;

  @ApiModelProperty(value = "For each scaling aspect of the current deployment flavor, indicates the target scale level to which the VNF is to be scaled. ")
  @Valid
 /**
   * For each scaling aspect of the current deployment flavor, indicates the target scale level to which the VNF is to be scaled. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus> vnfScaleInfo = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object additionalParams = null;
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return vnfInstantiationLevelId
  **/
  @JsonProperty("vnfInstantiationLevelId")
  public String getVnfInstantiationLevelId() {
    return vnfInstantiationLevelId;
  }

  public void setVnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData vnfInstantiationLevelId(String vnfInstantiationLevelId) {
    this.vnfInstantiationLevelId = vnfInstantiationLevelId;
    return this;
  }

 /**
   * For each scaling aspect of the current deployment flavor, indicates the target scale level to which the VNF is to be scaled. 
   * @return vnfScaleInfo
  **/
  @JsonProperty("vnfScaleInfo")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus> getVnfScaleInfo() {
    return vnfScaleInfo;
  }

  public void setVnfScaleInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus> vnfScaleInfo) {
    this.vnfScaleInfo = vnfScaleInfo;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData vnfScaleInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus> vnfScaleInfo) {
    this.vnfScaleInfo = vnfScaleInfo;
    return this;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData addVnfScaleInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus vnfScaleInfoItem) {
    this.vnfScaleInfo.add(vnfScaleInfoItem);
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return additionalParams
  **/
  @JsonProperty("additionalParams")
  public Object getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(Object additionalParams) {
    this.additionalParams = additionalParams;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData additionalParams(Object additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdscaleScaleNsRequestScaleToLevelData {\n");
    
    sb.append("    vnfInstantiationLevelId: ").append(toIndentedString(vnfInstantiationLevelId)).append("\n");
    sb.append("    vnfScaleInfo: ").append(toIndentedString(vnfScaleInfo)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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

