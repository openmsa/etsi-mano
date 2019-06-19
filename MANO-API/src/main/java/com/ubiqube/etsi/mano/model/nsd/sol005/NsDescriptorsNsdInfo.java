package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
  * \"This type represents a response for the query NSD operation.\"  
 **/
@ApiModel(description="\"This type represents a response for the query NSD operation.\"  ")
public class NsDescriptorsNsdInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsdId = null;

  @ApiModelProperty(value = "\"Name of the on boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.\" ")
 /**
   * \"Name of the on boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.\" 
  **/
  private String nsdName = null;

  @ApiModelProperty(value = "A Version. ")
 /**
   * A Version. 
  **/
  private String nsdVersion = null;

  @ApiModelProperty(value = "\"Designer of the on-boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.\" ")
 /**
   * \"Designer of the on-boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.\" 
  **/
  private String nsdDesigner = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsdInvariantId = null;

  @ApiModelProperty(value = "Identifies the VNF package for the VNFD referenced by the on-boarded NS descriptor resource. ")
 /**
   * Identifies the VNF package for the VNFD referenced by the on-boarded NS descriptor resource. 
  **/
  private List<String> vnfPkgIds = null;

  @ApiModelProperty(value = "Identifies the PnfdInfo element for the PNFD referenced by the on-boarded NS descriptor resource. ")
 /**
   * Identifies the PnfdInfo element for the PNFD referenced by the on-boarded NS descriptor resource. 
  **/
  private List<String> pnfdInfoIds = null;

  @ApiModelProperty(value = "Identifies the NsdInfo element for the nested NSD referenced by the on-boarded NS descriptor resource. ")
 /**
   * Identifies the NsdInfo element for the nested NSD referenced by the on-boarded NS descriptor resource. 
  **/
  private List<String> nestedNsdInfoIds = null;


@XmlType(name="NsdOnboardingStateEnum")
@XmlEnum(String.class)
public enum NsdOnboardingStateEnum {

@XmlEnumValue("CREATED") CREATED(String.valueOf("CREATED")), @XmlEnumValue("UPLOADING") UPLOADING(String.valueOf("UPLOADING")), @XmlEnumValue("PROCESSING") PROCESSING(String.valueOf("PROCESSING")), @XmlEnumValue("ONBOARDED") ONBOARDED(String.valueOf("ONBOARDED"));


    private String value;

    NsdOnboardingStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NsdOnboardingStateEnum fromValue(String v) {
        for (NsdOnboardingStateEnum b : NsdOnboardingStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "\"The enumeration NsdOnboardingStateType shall comply with the provisions defined in Table 5.5.4.5-1 of GS NFV-SOL 005. It indicates the on-boarding state of the NSD. CREATED = The NSD information object is created.  UPLOADING = The associated NSD content is being uploaded.  PROCESSING = The associated NSD content is being processed, e.g. validation.  ONBOARDED = The associated NSD content is on-boarded.\" ")
 /**
   * \"The enumeration NsdOnboardingStateType shall comply with the provisions defined in Table 5.5.4.5-1 of GS NFV-SOL 005. It indicates the on-boarding state of the NSD. CREATED = The NSD information object is created.  UPLOADING = The associated NSD content is being uploaded.  PROCESSING = The associated NSD content is being processed, e.g. validation.  ONBOARDED = The associated NSD content is on-boarded.\" 
  **/
  private NsdOnboardingStateEnum nsdOnboardingState = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsDescriptorsNsdInfoOnboardingFailureDetails onboardingFailureDetails = null;


@XmlType(name="NsdOperationalStateEnum")
@XmlEnum(String.class)
public enum NsdOperationalStateEnum {

@XmlEnumValue("ENABLED") ENABLED(String.valueOf("ENABLED")), @XmlEnumValue("DISABLED") DISABLED(String.valueOf("DISABLED"));


    private String value;

    NsdOperationalStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NsdOperationalStateEnum fromValue(String v) {
        for (NsdOperationalStateEnum b : NsdOperationalStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "\"The enumeration NsdOperationalStateType shall comply with the provisions defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational state of the resource. ENABLED = The operational state of the resource is enabled.  DISABLED = The operational state of the resource is disabled.\" ")
 /**
   * \"The enumeration NsdOperationalStateType shall comply with the provisions defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational state of the resource. ENABLED = The operational state of the resource is enabled.  DISABLED = The operational state of the resource is disabled.\" 
  **/
  private NsdOperationalStateEnum nsdOperationalState = null;


@XmlType(name="NsdUsageStateEnum")
@XmlEnum(String.class)
public enum NsdUsageStateEnum {

@XmlEnumValue("IN_USE") IN_USE(String.valueOf("IN_USE")), @XmlEnumValue("NOT_IN_USE") NOT_IN_USE(String.valueOf("NOT_IN_USE"));


    private String value;

    NsdUsageStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NsdUsageStateEnum fromValue(String v) {
        for (NsdUsageStateEnum b : NsdUsageStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "\"The enumeration NsdUsageStateType shall comply with the provisions defined in Table 5.5.4.4-1 of GS NFV-SOL 005. It indicates the usage state of the resource.IN_USE = The resource is in use.NOT_IN_USE = The resource is not-in-use.\" ")
 /**
   * \"The enumeration NsdUsageStateType shall comply with the provisions defined in Table 5.5.4.4-1 of GS NFV-SOL 005. It indicates the usage state of the resource.IN_USE = The resource is in use.NOT_IN_USE = The resource is not-in-use.\" 
  **/
  private NsdUsageStateEnum nsdUsageState = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object userDefinedData = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsDescriptorsNsdInfoLinks links = null;
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

  public NsDescriptorsNsdInfo id(String id) {
    this.id = id;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return nsdId
  **/
  @JsonProperty("nsdId")
  public String getNsdId() {
    return nsdId;
  }

  public void setNsdId(String nsdId) {
    this.nsdId = nsdId;
  }

  public NsDescriptorsNsdInfo nsdId(String nsdId) {
    this.nsdId = nsdId;
    return this;
  }

 /**
   * \&quot;Name of the on boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.\&quot; 
   * @return nsdName
  **/
  @JsonProperty("nsdName")
  public String getNsdName() {
    return nsdName;
  }

  public void setNsdName(String nsdName) {
    this.nsdName = nsdName;
  }

  public NsDescriptorsNsdInfo nsdName(String nsdName) {
    this.nsdName = nsdName;
    return this;
  }

 /**
   * A Version. 
   * @return nsdVersion
  **/
  @JsonProperty("nsdVersion")
  public String getNsdVersion() {
    return nsdVersion;
  }

  public void setNsdVersion(String nsdVersion) {
    this.nsdVersion = nsdVersion;
  }

  public NsDescriptorsNsdInfo nsdVersion(String nsdVersion) {
    this.nsdVersion = nsdVersion;
    return this;
  }

 /**
   * \&quot;Designer of the on-boarded NSD. This information is copied from the NSD content and shall be present after the NSD content is on-boarded.\&quot; 
   * @return nsdDesigner
  **/
  @JsonProperty("nsdDesigner")
  public String getNsdDesigner() {
    return nsdDesigner;
  }

  public void setNsdDesigner(String nsdDesigner) {
    this.nsdDesigner = nsdDesigner;
  }

  public NsDescriptorsNsdInfo nsdDesigner(String nsdDesigner) {
    this.nsdDesigner = nsdDesigner;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return nsdInvariantId
  **/
  @JsonProperty("nsdInvariantId")
  public String getNsdInvariantId() {
    return nsdInvariantId;
  }

  public void setNsdInvariantId(String nsdInvariantId) {
    this.nsdInvariantId = nsdInvariantId;
  }

  public NsDescriptorsNsdInfo nsdInvariantId(String nsdInvariantId) {
    this.nsdInvariantId = nsdInvariantId;
    return this;
  }

 /**
   * Identifies the VNF package for the VNFD referenced by the on-boarded NS descriptor resource. 
   * @return vnfPkgIds
  **/
  @JsonProperty("vnfPkgIds")
  public List<String> getVnfPkgIds() {
    return vnfPkgIds;
  }

  public void setVnfPkgIds(List<String> vnfPkgIds) {
    this.vnfPkgIds = vnfPkgIds;
  }

  public NsDescriptorsNsdInfo vnfPkgIds(List<String> vnfPkgIds) {
    this.vnfPkgIds = vnfPkgIds;
    return this;
  }

  public NsDescriptorsNsdInfo addVnfPkgIdsItem(String vnfPkgIdsItem) {
    this.vnfPkgIds.add(vnfPkgIdsItem);
    return this;
  }

 /**
   * Identifies the PnfdInfo element for the PNFD referenced by the on-boarded NS descriptor resource. 
   * @return pnfdInfoIds
  **/
  @JsonProperty("pnfdInfoIds")
  public List<String> getPnfdInfoIds() {
    return pnfdInfoIds;
  }

  public void setPnfdInfoIds(List<String> pnfdInfoIds) {
    this.pnfdInfoIds = pnfdInfoIds;
  }

  public NsDescriptorsNsdInfo pnfdInfoIds(List<String> pnfdInfoIds) {
    this.pnfdInfoIds = pnfdInfoIds;
    return this;
  }

  public NsDescriptorsNsdInfo addPnfdInfoIdsItem(String pnfdInfoIdsItem) {
    this.pnfdInfoIds.add(pnfdInfoIdsItem);
    return this;
  }

 /**
   * Identifies the NsdInfo element for the nested NSD referenced by the on-boarded NS descriptor resource. 
   * @return nestedNsdInfoIds
  **/
  @JsonProperty("nestedNsdInfoIds")
  public List<String> getNestedNsdInfoIds() {
    return nestedNsdInfoIds;
  }

  public void setNestedNsdInfoIds(List<String> nestedNsdInfoIds) {
    this.nestedNsdInfoIds = nestedNsdInfoIds;
  }

  public NsDescriptorsNsdInfo nestedNsdInfoIds(List<String> nestedNsdInfoIds) {
    this.nestedNsdInfoIds = nestedNsdInfoIds;
    return this;
  }

  public NsDescriptorsNsdInfo addNestedNsdInfoIdsItem(String nestedNsdInfoIdsItem) {
    this.nestedNsdInfoIds.add(nestedNsdInfoIdsItem);
    return this;
  }

 /**
   * \&quot;The enumeration NsdOnboardingStateType shall comply with the provisions defined in Table 5.5.4.5-1 of GS NFV-SOL 005. It indicates the on-boarding state of the NSD. CREATED &#x3D; The NSD information object is created.  UPLOADING &#x3D; The associated NSD content is being uploaded.  PROCESSING &#x3D; The associated NSD content is being processed, e.g. validation.  ONBOARDED &#x3D; The associated NSD content is on-boarded.\&quot; 
   * @return nsdOnboardingState
  **/
  @JsonProperty("nsdOnboardingState")
  @NotNull
  public String getNsdOnboardingState() {
    if (nsdOnboardingState == null) {
      return null;
    }
    return nsdOnboardingState.value();
  }

  public void setNsdOnboardingState(NsdOnboardingStateEnum nsdOnboardingState) {
    this.nsdOnboardingState = nsdOnboardingState;
  }

  public NsDescriptorsNsdInfo nsdOnboardingState(NsdOnboardingStateEnum nsdOnboardingState) {
    this.nsdOnboardingState = nsdOnboardingState;
    return this;
  }

 /**
   * Get onboardingFailureDetails
   * @return onboardingFailureDetails
  **/
  @JsonProperty("onboardingFailureDetails")
  public NsDescriptorsNsdInfoOnboardingFailureDetails getOnboardingFailureDetails() {
    return onboardingFailureDetails;
  }

  public void setOnboardingFailureDetails(NsDescriptorsNsdInfoOnboardingFailureDetails onboardingFailureDetails) {
    this.onboardingFailureDetails = onboardingFailureDetails;
  }

  public NsDescriptorsNsdInfo onboardingFailureDetails(NsDescriptorsNsdInfoOnboardingFailureDetails onboardingFailureDetails) {
    this.onboardingFailureDetails = onboardingFailureDetails;
    return this;
  }

 /**
   * \&quot;The enumeration NsdOperationalStateType shall comply with the provisions defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational state of the resource. ENABLED &#x3D; The operational state of the resource is enabled.  DISABLED &#x3D; The operational state of the resource is disabled.\&quot; 
   * @return nsdOperationalState
  **/
  @JsonProperty("nsdOperationalState")
  @NotNull
  public String getNsdOperationalState() {
    if (nsdOperationalState == null) {
      return null;
    }
    return nsdOperationalState.value();
  }

  public void setNsdOperationalState(NsdOperationalStateEnum nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
  }

  public NsDescriptorsNsdInfo nsdOperationalState(NsdOperationalStateEnum nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
    return this;
  }

 /**
   * \&quot;The enumeration NsdUsageStateType shall comply with the provisions defined in Table 5.5.4.4-1 of GS NFV-SOL 005. It indicates the usage state of the resource.IN_USE &#x3D; The resource is in use.NOT_IN_USE &#x3D; The resource is not-in-use.\&quot; 
   * @return nsdUsageState
  **/
  @JsonProperty("nsdUsageState")
  @NotNull
  public String getNsdUsageState() {
    if (nsdUsageState == null) {
      return null;
    }
    return nsdUsageState.value();
  }

  public void setNsdUsageState(NsdUsageStateEnum nsdUsageState) {
    this.nsdUsageState = nsdUsageState;
  }

  public NsDescriptorsNsdInfo nsdUsageState(NsdUsageStateEnum nsdUsageState) {
    this.nsdUsageState = nsdUsageState;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return userDefinedData
  **/
  @JsonProperty("userDefinedData")
  public Object getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(Object userDefinedData) {
    this.userDefinedData = userDefinedData;
  }

  public NsDescriptorsNsdInfo userDefinedData(Object userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

 /**
   * Get links
   * @return links
  **/
  @JsonProperty("_links")
  @NotNull
  public NsDescriptorsNsdInfoLinks getLinks() {
    return links;
  }

  public void setLinks(NsDescriptorsNsdInfoLinks links) {
    this.links = links;
  }

  public NsDescriptorsNsdInfo links(NsDescriptorsNsdInfoLinks links) {
    this.links = links;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsDescriptorsNsdInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
    sb.append("    nsdName: ").append(toIndentedString(nsdName)).append("\n");
    sb.append("    nsdVersion: ").append(toIndentedString(nsdVersion)).append("\n");
    sb.append("    nsdDesigner: ").append(toIndentedString(nsdDesigner)).append("\n");
    sb.append("    nsdInvariantId: ").append(toIndentedString(nsdInvariantId)).append("\n");
    sb.append("    vnfPkgIds: ").append(toIndentedString(vnfPkgIds)).append("\n");
    sb.append("    pnfdInfoIds: ").append(toIndentedString(pnfdInfoIds)).append("\n");
    sb.append("    nestedNsdInfoIds: ").append(toIndentedString(nestedNsdInfoIds)).append("\n");
    sb.append("    nsdOnboardingState: ").append(toIndentedString(nsdOnboardingState)).append("\n");
    sb.append("    onboardingFailureDetails: ").append(toIndentedString(onboardingFailureDetails)).append("\n");
    sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
    sb.append("    nsdUsageState: ").append(toIndentedString(nsdUsageState)).append("\n");
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

