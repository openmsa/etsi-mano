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
  * This type represents a VNF instance. 
 **/
@ApiModel(description="This type represents a VNF instance. ")
public class NsInstancesNsInstanceVnfInstance  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(value = "Name of the VNF instance. This attribute can be modified with the PATCH method. ")
 /**
   * Name of the VNF instance. This attribute can be modified with the PATCH method. 
  **/
  private String vnfInstanceName = null;

  @ApiModelProperty(value = "Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. ")
 /**
   * Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. 
  **/
  private String vnfInstanceDescription = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfdId = null;

  @ApiModelProperty(required = true, value = "Provider of the VNF and the VNFD. The value is copied from the VNFD. ")
 /**
   * Provider of the VNF and the VNFD. The value is copied from the VNFD. 
  **/
  private String vnfProvider = null;

  @ApiModelProperty(required = true, value = "Name to identify the VNF Product. The value is copied from the VNFD. ")
 /**
   * Name to identify the VNF Product. The value is copied from the VNFD. 
  **/
  private String vnfProductName = null;

  @ApiModelProperty(required = true, value = "A Version. ")
 /**
   * A Version. 
  **/
  private String vnfSoftwareVersion = null;

  @ApiModelProperty(required = true, value = "A Version. ")
 /**
   * A Version. 
  **/
  private String vnfdVersion = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfPkgId = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object vnfConfigurableProperties = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vimId = null;


@XmlType(name="InstantiationStateEnum")
@XmlEnum(String.class)
public enum InstantiationStateEnum {

@XmlEnumValue("NOT_INSTANTIATED") NOT_INSTANTIATED(String.valueOf("NOT_INSTANTIATED")), @XmlEnumValue("INSTANTIATED") INSTANTIATED(String.valueOf("INSTANTIATED"));


    private String value;

    InstantiationStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static InstantiationStateEnum fromValue(String v) {
        for (InstantiationStateEnum b : InstantiationStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "The instantiation state of the VNF. ")
 /**
   * The instantiation state of the VNF. 
  **/
  private InstantiationStateEnum instantiationState = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfo instantiatedVnfInfo = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object metadata = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object extensions = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NsInstancesNsInstanceVnfInstance id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Name of the VNF instance. This attribute can be modified with the PATCH method. 
   * @return vnfInstanceName
  **/
  @JsonProperty("vnfInstanceName")
  public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public NsInstancesNsInstanceVnfInstance vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

 /**
   * Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. 
   * @return vnfInstanceDescription
  **/
  @JsonProperty("vnfInstanceDescription")
  public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public NsInstancesNsInstanceVnfInstance vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfdId
  **/
  @JsonProperty("vnfdId")
  @NotNull
  public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public NsInstancesNsInstanceVnfInstance vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

 /**
   * Provider of the VNF and the VNFD. The value is copied from the VNFD. 
   * @return vnfProvider
  **/
  @JsonProperty("vnfProvider")
  @NotNull
  public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public NsInstancesNsInstanceVnfInstance vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

 /**
   * Name to identify the VNF Product. The value is copied from the VNFD. 
   * @return vnfProductName
  **/
  @JsonProperty("vnfProductName")
  @NotNull
  public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public NsInstancesNsInstanceVnfInstance vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

 /**
   * A Version. 
   * @return vnfSoftwareVersion
  **/
  @JsonProperty("vnfSoftwareVersion")
  @NotNull
  public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public NsInstancesNsInstanceVnfInstance vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

 /**
   * A Version. 
   * @return vnfdVersion
  **/
  @JsonProperty("vnfdVersion")
  @NotNull
  public String getVnfdVersion() {
    return vnfdVersion;
  }

  public void setVnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
  }

  public NsInstancesNsInstanceVnfInstance vnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfPkgId
  **/
  @JsonProperty("vnfPkgId")
  @NotNull
  public String getVnfPkgId() {
    return vnfPkgId;
  }

  public void setVnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
  }

  public NsInstancesNsInstanceVnfInstance vnfPkgId(String vnfPkgId) {
    this.vnfPkgId = vnfPkgId;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return vnfConfigurableProperties
  **/
  @JsonProperty("vnfConfigurableProperties")
  public Object getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(Object vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public NsInstancesNsInstanceVnfInstance vnfConfigurableProperties(Object vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return vimId
  **/
  @JsonProperty("vimId")
  public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public NsInstancesNsInstanceVnfInstance vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

 /**
   * The instantiation state of the VNF. 
   * @return instantiationState
  **/
  @JsonProperty("instantiationState")
  @NotNull
  public String getInstantiationState() {
    if (instantiationState == null) {
      return null;
    }
    return instantiationState.value();
  }

  public void setInstantiationState(InstantiationStateEnum instantiationState) {
    this.instantiationState = instantiationState;
  }

  public NsInstancesNsInstanceVnfInstance instantiationState(InstantiationStateEnum instantiationState) {
    this.instantiationState = instantiationState;
    return this;
  }

 /**
   * Get instantiatedVnfInfo
   * @return instantiatedVnfInfo
  **/
  @JsonProperty("instantiatedVnfInfo")
  public NsInstancesNsInstanceInstantiatedVnfInfo getInstantiatedVnfInfo() {
    return instantiatedVnfInfo;
  }

  public void setInstantiatedVnfInfo(NsInstancesNsInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
    this.instantiatedVnfInfo = instantiatedVnfInfo;
  }

  public NsInstancesNsInstanceVnfInstance instantiatedVnfInfo(NsInstancesNsInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
    this.instantiatedVnfInfo = instantiatedVnfInfo;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return metadata
  **/
  @JsonProperty("metadata")
  public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }

  public NsInstancesNsInstanceVnfInstance metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return extensions
  **/
  @JsonProperty("extensions")
  public Object getExtensions() {
    return extensions;
  }

  public void setExtensions(Object extensions) {
    this.extensions = extensions;
  }

  public NsInstancesNsInstanceVnfInstance extensions(Object extensions) {
    this.extensions = extensions;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceVnfInstance {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
    sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
    sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
    sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
    sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
    sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
    sb.append("    instantiatedVnfInfo: ").append(toIndentedString(instantiatedVnfInfo)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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

