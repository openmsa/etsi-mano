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
  * This type provides information about added, deleted and modified VNFs.  It shall comply with the provisions in Table 6.5.3.2-1. 
 **/
@ApiModel(description="This type provides information about added, deleted and modified VNFs.  It shall comply with the provisions in Table 6.5.3.2-1. ")
public class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfInstanceId = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfdId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String vnfProfileId = null;

  @ApiModelProperty(value = "Name of the VNF Instance. ")
 /**
   * Name of the VNF Instance. 
  **/
  private String vnfName = null;


@XmlType(name="ChangeTypeEnum")
@XmlEnum(String.class)
public enum ChangeTypeEnum {

@XmlEnumValue("ADD") ADD(String.valueOf("ADD")), @XmlEnumValue("REMOVE") REMOVE(String.valueOf("REMOVE")), @XmlEnumValue("INSTANTIATE") INSTANTIATE(String.valueOf("INSTANTIATE")), @XmlEnumValue("TERMINATE") TERMINATE(String.valueOf("TERMINATE")), @XmlEnumValue("SCALE") SCALE(String.valueOf("SCALE")), @XmlEnumValue("CHANGE_FLAVOUR") CHANGE_FLAVOUR(String.valueOf("CHANGE_FLAVOUR")), @XmlEnumValue("HEAL") HEAL(String.valueOf("HEAL")), @XmlEnumValue("OPERATE") OPERATE(String.valueOf("OPERATE")), @XmlEnumValue("MODIFY_INFORMATION") MODIFY_INFORMATION(String.valueOf("MODIFY_INFORMATION")), @XmlEnumValue("CHANGE_EXTERNAL_VNF_CONNECTIVITY") CHANGE_EXTERNAL_VNF_CONNECTIVITY(String.valueOf("CHANGE_EXTERNAL_VNF_CONNECTIVITY"));


    private String value;

    ChangeTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ChangeTypeEnum fromValue(String v) {
        for (ChangeTypeEnum b : ChangeTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY ")
 /**
   * Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY 
  **/
  private ChangeTypeEnum changeType = null;


@XmlType(name="ChangeResultEnum")
@XmlEnum(String.class)
public enum ChangeResultEnum {

@XmlEnumValue("COMPLETED") COMPLETED(String.valueOf("COMPLETED")), @XmlEnumValue("ROLLED_BACK") ROLLED_BACK(String.valueOf("ROLLED_BACK")), @XmlEnumValue("FAILED") FAILED(String.valueOf("FAILED"));


    private String value;

    ChangeResultEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ChangeResultEnum fromValue(String v) {
        for (ChangeResultEnum b : ChangeResultEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED ")
 /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED 
  **/
  private ChangeResultEnum changeResult = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsLcmOpOccsNsLcmOpOccResourceChangesChangedInfo changedInfo = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfInstanceId
  **/
  @JsonProperty("vnfInstanceId")
  @NotNull
  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return vnfProfileId
  **/
  @JsonProperty("vnfProfileId")
  @NotNull
  public String getVnfProfileId() {
    return vnfProfileId;
  }

  public void setVnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs vnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
    return this;
  }

 /**
   * Name of the VNF Instance. 
   * @return vnfName
  **/
  @JsonProperty("vnfName")
  public String getVnfName() {
    return vnfName;
  }

  public void setVnfName(String vnfName) {
    this.vnfName = vnfName;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs vnfName(String vnfName) {
    this.vnfName = vnfName;
    return this;
  }

 /**
   * Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY 
   * @return changeType
  **/
  @JsonProperty("changeType")
  public String getChangeType() {
    if (changeType == null) {
      return null;
    }
    return changeType.value();
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

 /**
   * Signals the result of change identified by the \&quot;changeType\&quot; attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED 
   * @return changeResult
  **/
  @JsonProperty("changeResult")
  public String getChangeResult() {
    if (changeResult == null) {
      return null;
    }
    return changeResult.value();
  }

  public void setChangeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }

 /**
   * Get changedInfo
   * @return changedInfo
  **/
  @JsonProperty("changedInfo")
  public NsLcmOpOccsNsLcmOpOccResourceChangesChangedInfo getChangedInfo() {
    return changedInfo;
  }

  public void setChangedInfo(NsLcmOpOccsNsLcmOpOccResourceChangesChangedInfo changedInfo) {
    this.changedInfo = changedInfo;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs changedInfo(NsLcmOpOccsNsLcmOpOccResourceChangesChangedInfo changedInfo) {
    this.changedInfo = changedInfo;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnfs {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
    sb.append("    vnfName: ").append(toIndentedString(vnfName)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    changeResult: ").append(toIndentedString(changeResult)).append("\n");
    sb.append("    changedInfo: ").append(toIndentedString(changedInfo)).append("\n");
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

