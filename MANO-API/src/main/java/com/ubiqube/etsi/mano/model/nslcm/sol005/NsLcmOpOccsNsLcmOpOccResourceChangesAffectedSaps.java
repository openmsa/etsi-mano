package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type provides information about added, deleted and modified SAP of a NS.  It shall comply with the provisions in Table 6.5.3.7-1. 
 **/
@ApiModel(description="This type provides information about added, deleted and modified SAP of a NS.  It shall comply with the provisions in Table 6.5.3.7-1. ")
public class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String sapInstanceId = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String sapdId = null;

  @ApiModelProperty(value = "Human readable name for the SAP. ")
 /**
   * Human readable name for the SAP. 
  **/
  private String sapName = null;


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

  @ApiModelProperty(value = "Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - MODIFY ")
 /**
   * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - MODIFY 
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
   * @return sapInstanceId
  **/
  @JsonProperty("sapInstanceId")
  @NotNull
  public String getSapInstanceId() {
    return sapInstanceId;
  }

  public void setSapInstanceId(String sapInstanceId) {
    this.sapInstanceId = sapInstanceId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps sapInstanceId(String sapInstanceId) {
    this.sapInstanceId = sapInstanceId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return sapdId
  **/
  @JsonProperty("sapdId")
  @NotNull
  public String getSapdId() {
    return sapdId;
  }

  public void setSapdId(String sapdId) {
    this.sapdId = sapdId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps sapdId(String sapdId) {
    this.sapdId = sapdId;
    return this;
  }

 /**
   * Human readable name for the SAP. 
   * @return sapName
  **/
  @JsonProperty("sapName")
  public String getSapName() {
    return sapName;
  }

  public void setSapName(String sapName) {
    this.sapName = sapName;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps sapName(String sapName) {
    this.sapName = sapName;
    return this;
  }

 /**
   * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - MODIFY 
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps changeType(ChangeTypeEnum changeType) {
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedSaps {\n");
    
    sb.append("    sapInstanceId: ").append(toIndentedString(sapInstanceId)).append("\n");
    sb.append("    sapdId: ").append(toIndentedString(sapdId)).append("\n");
    sb.append("    sapName: ").append(toIndentedString(sapName)).append("\n");
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

