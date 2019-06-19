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
  * This type represents the information used to heal a NS.  It shall comply with the provisions defined in Table 6.5.3.43-1. 
 **/
@ApiModel(description="This type represents the information used to heal a NS.  It shall comply with the provisions defined in Table 6.5.3.43-1. ")
public class NsInstancesnsInstanceIdhealHealNsRequestHealNsData  {
  

@XmlType(name="DegreeHealingEnum")
@XmlEnum(String.class)
public enum DegreeHealingEnum {

@XmlEnumValue("HEAL_RESTORE") HEAL_RESTORE(String.valueOf("HEAL_RESTORE")), @XmlEnumValue("HEAL_QOS") HEAL_QOS(String.valueOf("HEAL_QOS")), @XmlEnumValue("HEAL_RESET") HEAL_RESET(String.valueOf("HEAL_RESET")), @XmlEnumValue("PARTIAL_HEALING") PARTIAL_HEALING(String.valueOf("PARTIAL_HEALING"));


    private String value;

    DegreeHealingEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static DegreeHealingEnum fromValue(String v) {
        for (DegreeHealingEnum b : DegreeHealingEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "Indicates the degree of healing. Possible values include: - HEAL_RESTORE: Complete the healing of the NS restoring the state of the NS before the failure occurred - HEAL_QOS: Complete the healing of the NS based on the newest QoS values - HEAL_RESET: Complete the healing of the NS resetting to the original instantiation state of the NS - PARTIAL_HEALING ")
 /**
   * Indicates the degree of healing. Possible values include: - HEAL_RESTORE: Complete the healing of the NS restoring the state of the NS before the failure occurred - HEAL_QOS: Complete the healing of the NS based on the newest QoS values - HEAL_RESET: Complete the healing of the NS resetting to the original instantiation state of the NS - PARTIAL_HEALING 
  **/
  private DegreeHealingEnum degreeHealing = null;

  @ApiModelProperty(value = "Used to specify dedicated healing actions in a particular order (e.g. as a script). The actionsHealing attribute can be used to provide a specific script whose content and actions might only be possible to be derived during runtime. ")
 /**
   * Used to specify dedicated healing actions in a particular order (e.g. as a script). The actionsHealing attribute can be used to provide a specific script whose content and actions might only be possible to be derived during runtime. 
  **/
  private List<String> actionsHealing = null;

  @ApiModelProperty(value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String healScript = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object additionalParamsforNs = null;
 /**
   * Indicates the degree of healing. Possible values include: - HEAL_RESTORE: Complete the healing of the NS restoring the state of the NS before the failure occurred - HEAL_QOS: Complete the healing of the NS based on the newest QoS values - HEAL_RESET: Complete the healing of the NS resetting to the original instantiation state of the NS - PARTIAL_HEALING 
   * @return degreeHealing
  **/
  @JsonProperty("degreeHealing")
  @NotNull
  public String getDegreeHealing() {
    if (degreeHealing == null) {
      return null;
    }
    return degreeHealing.value();
  }

  public void setDegreeHealing(DegreeHealingEnum degreeHealing) {
    this.degreeHealing = degreeHealing;
  }

  public NsInstancesnsInstanceIdhealHealNsRequestHealNsData degreeHealing(DegreeHealingEnum degreeHealing) {
    this.degreeHealing = degreeHealing;
    return this;
  }

 /**
   * Used to specify dedicated healing actions in a particular order (e.g. as a script). The actionsHealing attribute can be used to provide a specific script whose content and actions might only be possible to be derived during runtime. 
   * @return actionsHealing
  **/
  @JsonProperty("actionsHealing")
  public List<String> getActionsHealing() {
    return actionsHealing;
  }

  public void setActionsHealing(List<String> actionsHealing) {
    this.actionsHealing = actionsHealing;
  }

  public NsInstancesnsInstanceIdhealHealNsRequestHealNsData actionsHealing(List<String> actionsHealing) {
    this.actionsHealing = actionsHealing;
    return this;
  }

  public NsInstancesnsInstanceIdhealHealNsRequestHealNsData addActionsHealingItem(String actionsHealingItem) {
    this.actionsHealing.add(actionsHealingItem);
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return healScript
  **/
  @JsonProperty("healScript")
  public String getHealScript() {
    return healScript;
  }

  public void setHealScript(String healScript) {
    this.healScript = healScript;
  }

  public NsInstancesnsInstanceIdhealHealNsRequestHealNsData healScript(String healScript) {
    this.healScript = healScript;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return additionalParamsforNs
  **/
  @JsonProperty("additionalParamsforNs")
  public Object getAdditionalParamsforNs() {
    return additionalParamsforNs;
  }

  public void setAdditionalParamsforNs(Object additionalParamsforNs) {
    this.additionalParamsforNs = additionalParamsforNs;
  }

  public NsInstancesnsInstanceIdhealHealNsRequestHealNsData additionalParamsforNs(Object additionalParamsforNs) {
    this.additionalParamsforNs = additionalParamsforNs;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdhealHealNsRequestHealNsData {\n");
    
    sb.append("    degreeHealing: ").append(toIndentedString(degreeHealing)).append("\n");
    sb.append("    actionsHealing: ").append(toIndentedString(actionsHealing)).append("\n");
    sb.append("    healScript: ").append(toIndentedString(healScript)).append("\n");
    sb.append("    additionalParamsforNs: ").append(toIndentedString(additionalParamsforNs)).append("\n");
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

