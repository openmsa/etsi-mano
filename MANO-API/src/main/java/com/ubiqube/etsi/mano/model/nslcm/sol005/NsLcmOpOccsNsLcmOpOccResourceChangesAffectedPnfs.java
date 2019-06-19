package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
  * This type provides information about added, deleted and modified PNFs.  It shall comply with the provisions in Table 6.5.3.3-1. 
 **/
@ApiModel(description="This type provides information about added, deleted and modified PNFs.  It shall comply with the provisions in Table 6.5.3.3-1. ")
public class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String pnfId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String pnfdId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String pnfProfileId = null;

  @ApiModelProperty(value = "Name of the PNF. ")
 /**
   * Name of the PNF. 
  **/
  private String pnfName = null;

  @ApiModelProperty(required = true, value = "Identifier of the CP in the scope of the PNF. ")
 /**
   * Identifier of the CP in the scope of the PNF. 
  **/
  private List<String> cpInstanceId = new ArrayList<String>();


@XmlType(name="ChangeTypeEnum")
@XmlEnum(String.class)
public enum ChangeTypeEnum {

@XmlEnumValue("ADD") ADD(String.valueOf("ADD")), @XmlEnumValue("REMOVE") REMOVE(String.valueOf("REMOVE")), @XmlEnumValue("MODIFY") MODIFY(String.valueOf("MODIFY"));


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

  @ApiModelProperty(value = "Signals the type of change. Permitted values: - ADD - REMOVE - MODIFY ")
 /**
   * Signals the type of change. Permitted values: - ADD - REMOVE - MODIFY 
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
 /**
   * An identifier with the intention of being globally unique. 
   * @return pnfId
  **/
  @JsonProperty("pnfId")
  @NotNull
  public String getPnfId() {
    return pnfId;
  }

  public void setPnfId(String pnfId) {
    this.pnfId = pnfId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs pnfId(String pnfId) {
    this.pnfId = pnfId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return pnfdId
  **/
  @JsonProperty("pnfdId")
  @NotNull
  public String getPnfdId() {
    return pnfdId;
  }

  public void setPnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs pnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return pnfProfileId
  **/
  @JsonProperty("pnfProfileId")
  @NotNull
  public String getPnfProfileId() {
    return pnfProfileId;
  }

  public void setPnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs pnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
    return this;
  }

 /**
   * Name of the PNF. 
   * @return pnfName
  **/
  @JsonProperty("pnfName")
  public String getPnfName() {
    return pnfName;
  }

  public void setPnfName(String pnfName) {
    this.pnfName = pnfName;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs pnfName(String pnfName) {
    this.pnfName = pnfName;
    return this;
  }

 /**
   * Identifier of the CP in the scope of the PNF. 
   * @return cpInstanceId
  **/
  @JsonProperty("cpInstanceId")
  @NotNull
  public List<String> getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(List<String> cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs cpInstanceId(List<String> cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs addCpInstanceIdItem(String cpInstanceIdItem) {
    this.cpInstanceId.add(cpInstanceIdItem);
    return this;
  }

 /**
   * Signals the type of change. Permitted values: - ADD - REMOVE - MODIFY 
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs changeType(ChangeTypeEnum changeType) {
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedPnfs {\n");
    
    sb.append("    pnfId: ").append(toIndentedString(pnfId)).append("\n");
    sb.append("    pnfdId: ").append(toIndentedString(pnfdId)).append("\n");
    sb.append("    pnfProfileId: ").append(toIndentedString(pnfProfileId)).append("\n");
    sb.append("    pnfName: ").append(toIndentedString(pnfName)).append("\n");
    sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    changeResult: ").append(toIndentedString(changeResult)).append("\n");
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

