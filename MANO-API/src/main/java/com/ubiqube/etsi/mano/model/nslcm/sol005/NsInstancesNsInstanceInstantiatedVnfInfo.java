package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
  * Information specific to an instantiated VNF instance. This attribute shall be present if the instantiateState attribute value is INSTANTIATED. 
 **/
@ApiModel(description="Information specific to an instantiated VNF instance. This attribute shall be present if the instantiateState attribute value is INSTANTIATED. ")
public class NsInstancesNsInstanceInstantiatedVnfInfo  {
  
  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String flavourId = null;


@XmlType(name="VnfStateEnum")
@XmlEnum(String.class)
public enum VnfStateEnum {

@XmlEnumValue("STARTED") STARTED(String.valueOf("STARTED")), @XmlEnumValue("STOPPED") STOPPED(String.valueOf("STOPPED"));


    private String value;

    VnfStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static VnfStateEnum fromValue(String v) {
        for (VnfStateEnum b : VnfStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "")
  private VnfStateEnum vnfState = null;

  @ApiModelProperty(value = "Scale status of the VNF, one entry per aspect. Represents for every scaling aspect how \"big\" the VNF has been scaled w.r.t. that aspect. ")
  @Valid
 /**
   * Scale status of the VNF, one entry per aspect. Represents for every scaling aspect how \"big\" the VNF has been scaled w.r.t. that aspect. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus> scaleStatus = null;

  @ApiModelProperty(value = "Information about the external CPs exposed by the VNF instance. ")
  @Valid
 /**
   * Information about the external CPs exposed by the VNF instance. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo> extCpInfo = null;

  @ApiModelProperty(value = "Information about the external VLs the VNF instance is connected to. ")
  @Valid
 /**
   * Information about the external VLs the VNF instance is connected to. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo> extVirtualLinkInfo = null;

  @ApiModelProperty(value = "External virtual links the VNF instance is connected to. ")
  @Valid
 /**
   * External virtual links the VNF instance is connected to. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo = null;

  @ApiModelProperty(value = "Active monitoring parameters. ")
  @Valid
 /**
   * Active monitoring parameters. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters> monitoringParameters = null;

  @ApiModelProperty(value = "Information about localization language of the VNF (includes e.g. strings in the VNFD). The localization languages supported by a VNF can be declared in the VNFD, and localization language selection can take place at instantiation time. The value shall comply with the format defined in IETF RFC 5646. ")
 /**
   * Information about localization language of the VNF (includes e.g. strings in the VNFD). The localization languages supported by a VNF can be declared in the VNFD, and localization language selection can take place at instantiation time. The value shall comply with the format defined in IETF RFC 5646. 
  **/
  private String localizationLanguage = null;

  @ApiModelProperty(value = "Information about the virtualised compute and storage resources used by the VNFCs of the VNF instance. ")
  @Valid
 /**
   * Information about the virtualised compute and storage resources used by the VNFCs of the VNF instance. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo> vnfcResourceInfo = null;

  @ApiModelProperty(value = "Information about the virtualised network resources used by the VLs of the VNF instance. ")
  @Valid
 /**
   * Information about the virtualised network resources used by the VLs of the VNF instance. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo> virtualLinkResourceInfo = null;

  @ApiModelProperty(value = "Information on the virtualised storage resource(s) used as storage for the VNF instance. ")
  @Valid
 /**
   * Information on the virtualised storage resource(s) used as storage for the VNF instance. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo> virtualStorageResourceInfo = null;
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return flavourId
  **/
  @JsonProperty("flavourId")
  @NotNull
  public String getFlavourId() {
    return flavourId;
  }

  public void setFlavourId(String flavourId) {
    this.flavourId = flavourId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo flavourId(String flavourId) {
    this.flavourId = flavourId;
    return this;
  }

 /**
   * Get vnfState
   * @return vnfState
  **/
  @JsonProperty("vnfState")
  @NotNull
  public String getVnfState() {
    if (vnfState == null) {
      return null;
    }
    return vnfState.value();
  }

  public void setVnfState(VnfStateEnum vnfState) {
    this.vnfState = vnfState;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo vnfState(VnfStateEnum vnfState) {
    this.vnfState = vnfState;
    return this;
  }

 /**
   * Scale status of the VNF, one entry per aspect. Represents for every scaling aspect how \&quot;big\&quot; the VNF has been scaled w.r.t. that aspect. 
   * @return scaleStatus
  **/
  @JsonProperty("scaleStatus")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus> getScaleStatus() {
    return scaleStatus;
  }

  public void setScaleStatus(List<NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus> scaleStatus) {
    this.scaleStatus = scaleStatus;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo scaleStatus(List<NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus> scaleStatus) {
    this.scaleStatus = scaleStatus;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo addScaleStatusItem(NsInstancesNsInstanceInstantiatedVnfInfoScaleStatus scaleStatusItem) {
    this.scaleStatus.add(scaleStatusItem);
    return this;
  }

 /**
   * Information about the external CPs exposed by the VNF instance. 
   * @return extCpInfo
  **/
  @JsonProperty("extCpInfo")
 @Size(min=1)  public List<NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo> getExtCpInfo() {
    return extCpInfo;
  }

  public void setExtCpInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo> extCpInfo) {
    this.extCpInfo = extCpInfo;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo extCpInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo> extCpInfo) {
    this.extCpInfo = extCpInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo addExtCpInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoExtCpInfo extCpInfoItem) {
    this.extCpInfo.add(extCpInfoItem);
    return this;
  }

 /**
   * Information about the external VLs the VNF instance is connected to. 
   * @return extVirtualLinkInfo
  **/
  @JsonProperty("extVirtualLinkInfo")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo> getExtVirtualLinkInfo() {
    return extVirtualLinkInfo;
  }

  public void setExtVirtualLinkInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo> extVirtualLinkInfo) {
    this.extVirtualLinkInfo = extVirtualLinkInfo;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo extVirtualLinkInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo> extVirtualLinkInfo) {
    this.extVirtualLinkInfo = extVirtualLinkInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo addExtVirtualLinkInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo extVirtualLinkInfoItem) {
    this.extVirtualLinkInfo.add(extVirtualLinkInfoItem);
    return this;
  }

 /**
   * External virtual links the VNF instance is connected to. 
   * @return extManagedVirtualLinkInfo
  **/
  @JsonProperty("extManagedVirtualLinkInfo")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo> getExtManagedVirtualLinkInfo() {
    return extManagedVirtualLinkInfo;
  }

  public void setExtManagedVirtualLinkInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo) {
    this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo extManagedVirtualLinkInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo) {
    this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo addExtManagedVirtualLinkInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo extManagedVirtualLinkInfoItem) {
    this.extManagedVirtualLinkInfo.add(extManagedVirtualLinkInfoItem);
    return this;
  }

 /**
   * Active monitoring parameters. 
   * @return monitoringParameters
  **/
  @JsonProperty("monitoringParameters")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters> getMonitoringParameters() {
    return monitoringParameters;
  }

  public void setMonitoringParameters(List<NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters> monitoringParameters) {
    this.monitoringParameters = monitoringParameters;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo monitoringParameters(List<NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters> monitoringParameters) {
    this.monitoringParameters = monitoringParameters;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo addMonitoringParametersItem(NsInstancesNsInstanceInstantiatedVnfInfoMonitoringParameters monitoringParametersItem) {
    this.monitoringParameters.add(monitoringParametersItem);
    return this;
  }

 /**
   * Information about localization language of the VNF (includes e.g. strings in the VNFD). The localization languages supported by a VNF can be declared in the VNFD, and localization language selection can take place at instantiation time. The value shall comply with the format defined in IETF RFC 5646. 
   * @return localizationLanguage
  **/
  @JsonProperty("localizationLanguage")
  public String getLocalizationLanguage() {
    return localizationLanguage;
  }

  public void setLocalizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo localizationLanguage(String localizationLanguage) {
    this.localizationLanguage = localizationLanguage;
    return this;
  }

 /**
   * Information about the virtualised compute and storage resources used by the VNFCs of the VNF instance. 
   * @return vnfcResourceInfo
  **/
  @JsonProperty("vnfcResourceInfo")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo> getVnfcResourceInfo() {
    return vnfcResourceInfo;
  }

  public void setVnfcResourceInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo> vnfcResourceInfo) {
    this.vnfcResourceInfo = vnfcResourceInfo;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo vnfcResourceInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo> vnfcResourceInfo) {
    this.vnfcResourceInfo = vnfcResourceInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo addVnfcResourceInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo vnfcResourceInfoItem) {
    this.vnfcResourceInfo.add(vnfcResourceInfoItem);
    return this;
  }

 /**
   * Information about the virtualised network resources used by the VLs of the VNF instance. 
   * @return virtualLinkResourceInfo
  **/
  @JsonProperty("virtualLinkResourceInfo")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo> getVirtualLinkResourceInfo() {
    return virtualLinkResourceInfo;
  }

  public void setVirtualLinkResourceInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo> virtualLinkResourceInfo) {
    this.virtualLinkResourceInfo = virtualLinkResourceInfo;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo virtualLinkResourceInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo> virtualLinkResourceInfo) {
    this.virtualLinkResourceInfo = virtualLinkResourceInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo addVirtualLinkResourceInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo virtualLinkResourceInfoItem) {
    this.virtualLinkResourceInfo.add(virtualLinkResourceInfoItem);
    return this;
  }

 /**
   * Information on the virtualised storage resource(s) used as storage for the VNF instance. 
   * @return virtualStorageResourceInfo
  **/
  @JsonProperty("virtualStorageResourceInfo")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo> getVirtualStorageResourceInfo() {
    return virtualStorageResourceInfo;
  }

  public void setVirtualStorageResourceInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo> virtualStorageResourceInfo) {
    this.virtualStorageResourceInfo = virtualStorageResourceInfo;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo virtualStorageResourceInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo> virtualStorageResourceInfo) {
    this.virtualStorageResourceInfo = virtualStorageResourceInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfo addVirtualStorageResourceInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo virtualStorageResourceInfoItem) {
    this.virtualStorageResourceInfo.add(virtualStorageResourceInfoItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfo {\n");
    
    sb.append("    flavourId: ").append(toIndentedString(flavourId)).append("\n");
    sb.append("    vnfState: ").append(toIndentedString(vnfState)).append("\n");
    sb.append("    scaleStatus: ").append(toIndentedString(scaleStatus)).append("\n");
    sb.append("    extCpInfo: ").append(toIndentedString(extCpInfo)).append("\n");
    sb.append("    extVirtualLinkInfo: ").append(toIndentedString(extVirtualLinkInfo)).append("\n");
    sb.append("    extManagedVirtualLinkInfo: ").append(toIndentedString(extManagedVirtualLinkInfo)).append("\n");
    sb.append("    monitoringParameters: ").append(toIndentedString(monitoringParameters)).append("\n");
    sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
    sb.append("    vnfcResourceInfo: ").append(toIndentedString(vnfcResourceInfo)).append("\n");
    sb.append("    virtualLinkResourceInfo: ").append(toIndentedString(virtualLinkResourceInfo)).append("\n");
    sb.append("    virtualStorageResourceInfo: ").append(toIndentedString(virtualStorageResourceInfo)).append("\n");
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

