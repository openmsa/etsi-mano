package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
  * This type describes the additional affinity or anti-affinity rule  applicable between the VNF instances to be instantiated in the NS instantiation operation request or between the VNF instances  to be instantiated in the NS instantiation operation request and the existing VNF instances.. 
 **/
@ApiModel(description="This type describes the additional affinity or anti-affinity rule  applicable between the VNF instances to be instantiated in the NS instantiation operation request or between the VNF instances  to be instantiated in the NS instantiation operation request and the existing VNF instances.. ")
public class NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule  {
  
  @ApiModelProperty(value = "Reference to a VNFD. When the VNFD which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VNFD presents is not necessary as a part of the NS to be instantiated. ")
 /**
   * Reference to a VNFD. When the VNFD which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VNFD presents is not necessary as a part of the NS to be instantiated. 
  **/
  private List<String> vnfdId = null;

  @ApiModelProperty(value = "Reference to a vnfProfile defined in the NSD. At least one VnfProfile which is used to instantiate VNF for the NS to be instantiated as the subject of the affinity or anti-affinity rule shall be present. When the VnfProfile which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VnfProfile presents is not necessary as a part of the NS to be instantiated. ")
 /**
   * Reference to a vnfProfile defined in the NSD. At least one VnfProfile which is used to instantiate VNF for the NS to be instantiated as the subject of the affinity or anti-affinity rule shall be present. When the VnfProfile which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VnfProfile presents is not necessary as a part of the NS to be instantiated. 
  **/
  private List<String> vnfProfileId = null;

  @ApiModelProperty(value = "Reference to the existing VNF instance as the subject of the affinity or anti-affinity rule. The existing VNF instance is not necessary as a part of the NS to be instantiated. ")
 /**
   * Reference to the existing VNF instance as the subject of the affinity or anti-affinity rule. The existing VNF instance is not necessary as a part of the NS to be instantiated. 
  **/
  private List<String> vnfInstanceId = null;


@XmlType(name="AffinityOrAntiAffiintyEnum")
@XmlEnum(String.class)
public enum AffinityOrAntiAffiintyEnum {

@XmlEnumValue("AFFINITY") AFFINITY(String.valueOf("AFFINITY")), @XmlEnumValue("ANTI_AFFINITY") ANTI_AFFINITY(String.valueOf("ANTI_AFFINITY"));


    private String value;

    AffinityOrAntiAffiintyEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static AffinityOrAntiAffiintyEnum fromValue(String v) {
        for (AffinityOrAntiAffiintyEnum b : AffinityOrAntiAffiintyEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY. ")
 /**
   * The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY. 
  **/
  private AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty = null;


@XmlType(name="ScopeEnum")
@XmlEnum(String.class)
public enum ScopeEnum {

@XmlEnumValue("NFVI_POP") NFVI_POP(String.valueOf("NFVI_POP")), @XmlEnumValue("ZONE") ZONE(String.valueOf("ZONE")), @XmlEnumValue("ZONE_GROUP") ZONE_GROUP(String.valueOf("ZONE_GROUP")), @XmlEnumValue("NFVI_NODE") NFVI_NODE(String.valueOf("NFVI_NODE"));


    private String value;

    ScopeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ScopeEnum fromValue(String v) {
        for (ScopeEnum b : ScopeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "Specifies the scope of the rule where the placement constraint applies. Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE. ")
 /**
   * Specifies the scope of the rule where the placement constraint applies. Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE. 
  **/
  private ScopeEnum scope = null;
 /**
   * Reference to a VNFD. When the VNFD which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VNFD presents is not necessary as a part of the NS to be instantiated. 
   * @return vnfdId
  **/
  @JsonProperty("vnfdId")
  public List<String> getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(List<String> vnfdId) {
    this.vnfdId = vnfdId;
  }

  public NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule vnfdId(List<String> vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  public NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule addVnfdIdItem(String vnfdIdItem) {
    this.vnfdId.add(vnfdIdItem);
    return this;
  }

 /**
   * Reference to a vnfProfile defined in the NSD. At least one VnfProfile which is used to instantiate VNF for the NS to be instantiated as the subject of the affinity or anti-affinity rule shall be present. When the VnfProfile which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VnfProfile presents is not necessary as a part of the NS to be instantiated. 
   * @return vnfProfileId
  **/
  @JsonProperty("vnfProfileId")
  public List<String> getVnfProfileId() {
    return vnfProfileId;
  }

  public void setVnfProfileId(List<String> vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
  }

  public NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule vnfProfileId(List<String> vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
    return this;
  }

  public NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule addVnfProfileIdItem(String vnfProfileIdItem) {
    this.vnfProfileId.add(vnfProfileIdItem);
    return this;
  }

 /**
   * Reference to the existing VNF instance as the subject of the affinity or anti-affinity rule. The existing VNF instance is not necessary as a part of the NS to be instantiated. 
   * @return vnfInstanceId
  **/
  @JsonProperty("vnfInstanceId")
  public List<String> getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule vnfInstanceId(List<String> vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  public NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule addVnfInstanceIdItem(String vnfInstanceIdItem) {
    this.vnfInstanceId.add(vnfInstanceIdItem);
    return this;
  }

 /**
   * The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY. 
   * @return affinityOrAntiAffiinty
  **/
  @JsonProperty("affinityOrAntiAffiinty")
  @NotNull
  public String getAffinityOrAntiAffiinty() {
    if (affinityOrAntiAffiinty == null) {
      return null;
    }
    return affinityOrAntiAffiinty.value();
  }

  public void setAffinityOrAntiAffiinty(AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty) {
    this.affinityOrAntiAffiinty = affinityOrAntiAffiinty;
  }

  public NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule affinityOrAntiAffiinty(AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty) {
    this.affinityOrAntiAffiinty = affinityOrAntiAffiinty;
    return this;
  }

 /**
   * Specifies the scope of the rule where the placement constraint applies. Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE. 
   * @return scope
  **/
  @JsonProperty("scope")
  @NotNull
  public String getScope() {
    if (scope == null) {
      return null;
    }
    return scope.value();
  }

  public void setScope(ScopeEnum scope) {
    this.scope = scope;
  }

  public NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule scope(ScopeEnum scope) {
    this.scope = scope;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceAdditionalAffinityOrAntiAffinityRule {\n");
    
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    affinityOrAntiAffiinty: ").append(toIndentedString(affinityOrAntiAffiinty)).append("\n");
    sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
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

