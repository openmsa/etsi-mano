package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
  * This type represents the information to scale a NS. 
 **/
@ApiModel(description="This type represents the information to scale a NS. ")
public class NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData  {
  
  @ApiModelProperty(value = "An existing VNF instance to be added to the NS instance as part of the scaling operation. If needed, the VNF Profile to be used for this VNF instance may also be provided. ")
  @Valid
 /**
   * An existing VNF instance to be added to the NS instance as part of the scaling operation. If needed, the VNF Profile to be used for this VNF instance may also be provided. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> vnfInstanceToBeAdded = null;

  @ApiModelProperty(value = "The VNF instance to be removed from the NS instance as part of the scaling operation. ")
 /**
   * The VNF instance to be removed from the NS instance as part of the scaling operation. 
  **/
  private List<String> vnfInstanceToBeRemoved = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData scaleNsByStepsData = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData scaleNsToLevelData = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf additionalParamsForNs = null;

  @ApiModelProperty(value = "Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamforNs). This is for VNFs that are to be created by the NFVO as part of the NS scaling and not for existing VNF that are covered by the scaleVnfData. ")
  @Valid
 /**
   * Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamforNs). This is for VNFs that are to be created by the NFVO as part of the NS scaling and not for existing VNF that are covered by the scaleVnfData. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf> additionalParamsForVnf = null;

  @ApiModelProperty(value = "The location constraints for the VNF to be instantiated as part of the NS scaling. An example can be a constraint for the VNF to be in a specific geographic location. ")
  @Valid
 /**
   * The location constraints for the VNF to be instantiated as part of the NS scaling. An example can be a constraint for the VNF to be in a specific geographic location. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage> locationConstraints = null;
 /**
   * An existing VNF instance to be added to the NS instance as part of the scaling operation. If needed, the VNF Profile to be used for this VNF instance may also be provided. 
   * @return vnfInstanceToBeAdded
  **/
  @JsonProperty("vnfInstanceToBeAdded")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> getVnfInstanceToBeAdded() {
    return vnfInstanceToBeAdded;
  }

  public void setVnfInstanceToBeAdded(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> vnfInstanceToBeAdded) {
    this.vnfInstanceToBeAdded = vnfInstanceToBeAdded;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData vnfInstanceToBeAdded(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> vnfInstanceToBeAdded) {
    this.vnfInstanceToBeAdded = vnfInstanceToBeAdded;
    return this;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData addVnfInstanceToBeAddedItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData vnfInstanceToBeAddedItem) {
    this.vnfInstanceToBeAdded.add(vnfInstanceToBeAddedItem);
    return this;
  }

 /**
   * The VNF instance to be removed from the NS instance as part of the scaling operation. 
   * @return vnfInstanceToBeRemoved
  **/
  @JsonProperty("vnfInstanceToBeRemoved")
  public List<String> getVnfInstanceToBeRemoved() {
    return vnfInstanceToBeRemoved;
  }

  public void setVnfInstanceToBeRemoved(List<String> vnfInstanceToBeRemoved) {
    this.vnfInstanceToBeRemoved = vnfInstanceToBeRemoved;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData vnfInstanceToBeRemoved(List<String> vnfInstanceToBeRemoved) {
    this.vnfInstanceToBeRemoved = vnfInstanceToBeRemoved;
    return this;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData addVnfInstanceToBeRemovedItem(String vnfInstanceToBeRemovedItem) {
    this.vnfInstanceToBeRemoved.add(vnfInstanceToBeRemovedItem);
    return this;
  }

 /**
   * Get scaleNsByStepsData
   * @return scaleNsByStepsData
  **/
  @JsonProperty("scaleNsByStepsData")
  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData getScaleNsByStepsData() {
    return scaleNsByStepsData;
  }

  public void setScaleNsByStepsData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData scaleNsByStepsData) {
    this.scaleNsByStepsData = scaleNsByStepsData;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData scaleNsByStepsData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsByStepsData scaleNsByStepsData) {
    this.scaleNsByStepsData = scaleNsByStepsData;
    return this;
  }

 /**
   * Get scaleNsToLevelData
   * @return scaleNsToLevelData
  **/
  @JsonProperty("scaleNsToLevelData")
  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData getScaleNsToLevelData() {
    return scaleNsToLevelData;
  }

  public void setScaleNsToLevelData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData scaleNsToLevelData) {
    this.scaleNsToLevelData = scaleNsToLevelData;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData scaleNsToLevelData(NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsDataScaleNsToLevelData scaleNsToLevelData) {
    this.scaleNsToLevelData = scaleNsToLevelData;
    return this;
  }

 /**
   * Get additionalParamsForNs
   * @return additionalParamsForNs
  **/
  @JsonProperty("additionalParamsForNs")
  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf getAdditionalParamsForNs() {
    return additionalParamsForNs;
  }

  public void setAdditionalParamsForNs(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf additionalParamsForNs) {
    this.additionalParamsForNs = additionalParamsForNs;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData additionalParamsForNs(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf additionalParamsForNs) {
    this.additionalParamsForNs = additionalParamsForNs;
    return this;
  }

 /**
   * Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamforNs). This is for VNFs that are to be created by the NFVO as part of the NS scaling and not for existing VNF that are covered by the scaleVnfData. 
   * @return additionalParamsForVnf
  **/
  @JsonProperty("additionalParamsForVnf")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf> getAdditionalParamsForVnf() {
    return additionalParamsForVnf;
  }

  public void setAdditionalParamsForVnf(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf> additionalParamsForVnf) {
    this.additionalParamsForVnf = additionalParamsForVnf;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData additionalParamsForVnf(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf> additionalParamsForVnf) {
    this.additionalParamsForVnf = additionalParamsForVnf;
    return this;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData addAdditionalParamsForVnfItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf additionalParamsForVnfItem) {
    this.additionalParamsForVnf.add(additionalParamsForVnfItem);
    return this;
  }

 /**
   * The location constraints for the VNF to be instantiated as part of the NS scaling. An example can be a constraint for the VNF to be in a specific geographic location. 
   * @return locationConstraints
  **/
  @JsonProperty("locationConstraints")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage> getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage> locationConstraints) {
    this.locationConstraints = locationConstraints;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData locationConstraints(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage> locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }

  public NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData addLocationConstraintsItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage locationConstraintsItem) {
    this.locationConstraints.add(locationConstraintsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdscaleScaleNsRequestScaleNsData {\n");
    
    sb.append("    vnfInstanceToBeAdded: ").append(toIndentedString(vnfInstanceToBeAdded)).append("\n");
    sb.append("    vnfInstanceToBeRemoved: ").append(toIndentedString(vnfInstanceToBeRemoved)).append("\n");
    sb.append("    scaleNsByStepsData: ").append(toIndentedString(scaleNsByStepsData)).append("\n");
    sb.append("    scaleNsToLevelData: ").append(toIndentedString(scaleNsToLevelData)).append("\n");
    sb.append("    additionalParamsForNs: ").append(toIndentedString(additionalParamsForNs)).append("\n");
    sb.append("    additionalParamsForVnf: ").append(toIndentedString(additionalParamsForVnf)).append("\n");
    sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
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

