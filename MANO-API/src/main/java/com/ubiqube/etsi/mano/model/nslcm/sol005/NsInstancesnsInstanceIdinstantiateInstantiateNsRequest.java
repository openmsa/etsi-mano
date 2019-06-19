package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class NsInstancesnsInstanceIdinstantiateInstantiateNsRequest  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String nsFlavourId = null;

  @ApiModelProperty(value = "Create data concerning the SAPs of this NS. ")
  @Valid
 /**
   * Create data concerning the SAPs of this NS. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData> sapData = null;

  @ApiModelProperty(value = "Information on the PNF(s) that are part of this NS. ")
  @Valid
 /**
   * Information on the PNF(s) that are part of this NS. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData> addpnfData = null;

  @ApiModelProperty(value = "Specify an existing VNF instance to be used in the NS. If needed, the VNF Profile to be used for this VNF instance is also provided. ")
  @Valid
 /**
   * Specify an existing VNF instance to be used in the NS. If needed, the VNF Profile to be used for this VNF instance is also provided. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> vnfInstanceData = null;

  @ApiModelProperty(value = "Specify an existing NS instance to be used as a nested NS within the NS. ")
 /**
   * Specify an existing NS instance to be used as a nested NS within the NS. 
  **/
  private List<String> nestedNsInstanceId = null;

  @ApiModelProperty(value = "Defines the location constraints for the VNF to be instantiated as part of the NS instantiation. An example can be a constraint for the VNF to be in a specific geographic location.. ")
  @Valid
 /**
   * Defines the location constraints for the VNF to be instantiated as part of the NS instantiation. An example can be a constraint for the VNF to be in a specific geographic location.. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage> localizationLanguage = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object additionalParamsForNs = null;

  @ApiModelProperty(value = "Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamsForNs). This is for VNFs that are to be created by the NFVO as part of the NS instantiation and not for existing VNF that are referenced for reuse.. ")
  @Valid
 /**
   * Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamsForNs). This is for VNFs that are to be created by the NFVO as part of the NS instantiation and not for existing VNF that are referenced for reuse.. 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf> additionalParamsForVnf = null;

  @ApiModelProperty(value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date startTime = null;

  @ApiModelProperty(value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String nsInstantiationLevelId = null;

  @ApiModelProperty(value = "Specifies additional affinity or anti-affinity constraint for the VNF instances to be instantiated as part of the NS instantiation. Shall not conflict with rules already specified in the NSD. ")
  @Valid
 /**
   * Specifies additional affinity or anti-affinity constraint for the VNF instances to be instantiated as part of the NS instantiation. Shall not conflict with rules already specified in the NSD. 
  **/
  private List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> additionalAffinityOrAntiAffiniityRule = null;
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return nsFlavourId
  **/
  @JsonProperty("nsFlavourId")
  @NotNull
  public String getNsFlavourId() {
    return nsFlavourId;
  }

  public void setNsFlavourId(String nsFlavourId) {
    this.nsFlavourId = nsFlavourId;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest nsFlavourId(String nsFlavourId) {
    this.nsFlavourId = nsFlavourId;
    return this;
  }

 /**
   * Create data concerning the SAPs of this NS. 
   * @return sapData
  **/
  @JsonProperty("sapData")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData> getSapData() {
    return sapData;
  }

  public void setSapData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData> sapData) {
    this.sapData = sapData;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest sapData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData> sapData) {
    this.sapData = sapData;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest addSapDataItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData sapDataItem) {
    this.sapData.add(sapDataItem);
    return this;
  }

 /**
   * Information on the PNF(s) that are part of this NS. 
   * @return addpnfData
  **/
  @JsonProperty("addpnfData")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData> getAddpnfData() {
    return addpnfData;
  }

  public void setAddpnfData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData> addpnfData) {
    this.addpnfData = addpnfData;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest addpnfData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData> addpnfData) {
    this.addpnfData = addpnfData;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest addAddpnfDataItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData addpnfDataItem) {
    this.addpnfData.add(addpnfDataItem);
    return this;
  }

 /**
   * Specify an existing VNF instance to be used in the NS. If needed, the VNF Profile to be used for this VNF instance is also provided. 
   * @return vnfInstanceData
  **/
  @JsonProperty("vnfInstanceData")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> getVnfInstanceData() {
    return vnfInstanceData;
  }

  public void setVnfInstanceData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> vnfInstanceData) {
    this.vnfInstanceData = vnfInstanceData;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest vnfInstanceData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> vnfInstanceData) {
    this.vnfInstanceData = vnfInstanceData;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest addVnfInstanceDataItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData vnfInstanceDataItem) {
    this.vnfInstanceData.add(vnfInstanceDataItem);
    return this;
  }

 /**
   * Specify an existing NS instance to be used as a nested NS within the NS. 
   * @return nestedNsInstanceId
  **/
  @JsonProperty("nestedNsInstanceId")
  public List<String> getNestedNsInstanceId() {
    return nestedNsInstanceId;
  }

  public void setNestedNsInstanceId(List<String> nestedNsInstanceId) {
    this.nestedNsInstanceId = nestedNsInstanceId;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest nestedNsInstanceId(List<String> nestedNsInstanceId) {
    this.nestedNsInstanceId = nestedNsInstanceId;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest addNestedNsInstanceIdItem(String nestedNsInstanceIdItem) {
    this.nestedNsInstanceId.add(nestedNsInstanceIdItem);
    return this;
  }

 /**
   * Defines the location constraints for the VNF to be instantiated as part of the NS instantiation. An example can be a constraint for the VNF to be in a specific geographic location.. 
   * @return localizationLanguage
  **/
  @JsonProperty("localizationLanguage")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage> getLocalizationLanguage() {
    return localizationLanguage;
  }

  public void setLocalizationLanguage(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage> localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest localizationLanguage(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage> localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest addLocalizationLanguageItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage localizationLanguageItem) {
    this.localizationLanguage.add(localizationLanguageItem);
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return additionalParamsForNs
  **/
  @JsonProperty("additionalParamsForNs")
  public Object getAdditionalParamsForNs() {
    return additionalParamsForNs;
  }

  public void setAdditionalParamsForNs(Object additionalParamsForNs) {
    this.additionalParamsForNs = additionalParamsForNs;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest additionalParamsForNs(Object additionalParamsForNs) {
    this.additionalParamsForNs = additionalParamsForNs;
    return this;
  }

 /**
   * Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamsForNs). This is for VNFs that are to be created by the NFVO as part of the NS instantiation and not for existing VNF that are referenced for reuse.. 
   * @return additionalParamsForVnf
  **/
  @JsonProperty("additionalParamsForVnf")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf> getAdditionalParamsForVnf() {
    return additionalParamsForVnf;
  }

  public void setAdditionalParamsForVnf(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf> additionalParamsForVnf) {
    this.additionalParamsForVnf = additionalParamsForVnf;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest additionalParamsForVnf(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf> additionalParamsForVnf) {
    this.additionalParamsForVnf = additionalParamsForVnf;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest addAdditionalParamsForVnfItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAdditionalParamsForVnf additionalParamsForVnfItem) {
    this.additionalParamsForVnf.add(additionalParamsForVnfItem);
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return startTime
  **/
  @JsonProperty("startTime")
  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest startTime(Date startTime) {
    this.startTime = startTime;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return nsInstantiationLevelId
  **/
  @JsonProperty("nsInstantiationLevelId")
  public String getNsInstantiationLevelId() {
    return nsInstantiationLevelId;
  }

  public void setNsInstantiationLevelId(String nsInstantiationLevelId) {
    this.nsInstantiationLevelId = nsInstantiationLevelId;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest nsInstantiationLevelId(String nsInstantiationLevelId) {
    this.nsInstantiationLevelId = nsInstantiationLevelId;
    return this;
  }

 /**
   * Specifies additional affinity or anti-affinity constraint for the VNF instances to be instantiated as part of the NS instantiation. Shall not conflict with rules already specified in the NSD. 
   * @return additionalAffinityOrAntiAffiniityRule
  **/
  @JsonProperty("additionalAffinityOrAntiAffiniityRule")
  public List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> getAdditionalAffinityOrAntiAffiniityRule() {
    return additionalAffinityOrAntiAffiniityRule;
  }

  public void setAdditionalAffinityOrAntiAffiniityRule(List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> additionalAffinityOrAntiAffiniityRule) {
    this.additionalAffinityOrAntiAffiniityRule = additionalAffinityOrAntiAffiniityRule;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest additionalAffinityOrAntiAffiniityRule(List<NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule> additionalAffinityOrAntiAffiniityRule) {
    this.additionalAffinityOrAntiAffiniityRule = additionalAffinityOrAntiAffiniityRule;
    return this;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequest addAdditionalAffinityOrAntiAffiniityRuleItem(NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule additionalAffinityOrAntiAffiniityRuleItem) {
    this.additionalAffinityOrAntiAffiniityRule.add(additionalAffinityOrAntiAffiniityRuleItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdinstantiateInstantiateNsRequest {\n");
    
    sb.append("    nsFlavourId: ").append(toIndentedString(nsFlavourId)).append("\n");
    sb.append("    sapData: ").append(toIndentedString(sapData)).append("\n");
    sb.append("    addpnfData: ").append(toIndentedString(addpnfData)).append("\n");
    sb.append("    vnfInstanceData: ").append(toIndentedString(vnfInstanceData)).append("\n");
    sb.append("    nestedNsInstanceId: ").append(toIndentedString(nestedNsInstanceId)).append("\n");
    sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
    sb.append("    additionalParamsForNs: ").append(toIndentedString(additionalParamsForNs)).append("\n");
    sb.append("    additionalParamsForVnf: ").append(toIndentedString(additionalParamsForVnf)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    nsInstantiationLevelId: ").append(toIndentedString(nsInstantiationLevelId)).append("\n");
    sb.append("    additionalAffinityOrAntiAffiniityRule: ").append(toIndentedString(additionalAffinityOrAntiAffiniityRule)).append("\n");
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

