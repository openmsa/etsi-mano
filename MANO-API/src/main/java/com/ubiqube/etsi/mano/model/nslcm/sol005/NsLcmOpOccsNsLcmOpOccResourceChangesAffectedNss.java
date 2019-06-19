package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type provides information about added, deleted and modified nested NSs.  It shall comply with the provisions in Table 6.5.3.6-1. 
 **/
@ApiModel(description="This type provides information about added, deleted and modified nested NSs.  It shall comply with the provisions in Table 6.5.3.6-1. ")
public class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsInstanceId = null;

  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsdId = null;


@XmlType(name="ChangeTypeEnum")
@XmlEnum(String.class)
public enum ChangeTypeEnum {

@XmlEnumValue("ADD") ADD(String.valueOf("ADD")), @XmlEnumValue("REMOVE") REMOVE(String.valueOf("REMOVE")), @XmlEnumValue("INSTANTIATE") INSTANTIATE(String.valueOf("INSTANTIATE")), @XmlEnumValue("SCALE") SCALE(String.valueOf("SCALE")), @XmlEnumValue("UPDATE") UPDATE(String.valueOf("UPDATE")), @XmlEnumValue("HEAL") HEAL(String.valueOf("HEAL")), @XmlEnumValue("TERMINATE") TERMINATE(String.valueOf("TERMINATE"));


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

  @ApiModelProperty(value = "Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE ")
 /**
   * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE 
  **/
  private ChangeTypeEnum changeType = null;


@XmlType(name="ChangeResultEnum")
@XmlEnum(String.class)
public enum ChangeResultEnum {

@XmlEnumValue("COMPLETED") COMPLETED(String.valueOf("COMPLETED")), @XmlEnumValue("ROLLED_BACK") ROLLED_BACK(String.valueOf("ROLLED_BACK")), @XmlEnumValue("FAILED") FAILED(String.valueOf("FAILED")), @XmlEnumValue("PARTIALLY_COMPLETED") PARTIALLY_COMPLETED(String.valueOf("PARTIALLY_COMPLETED"));


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

  @ApiModelProperty(value = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED            ")
 /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED            
  **/
  private ChangeResultEnum changeResult = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return nsInstanceId
  **/
  @JsonProperty("nsInstanceId")
  @NotNull
  public String getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return nsdId
  **/
  @JsonProperty("nsdId")
  @NotNull
  public String getNsdId() {
    return nsdId;
  }

  public void setNsdId(String nsdId) {
    this.nsdId = nsdId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss nsdId(String nsdId) {
    this.nsdId = nsdId;
    return this;
  }

 /**
   * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE 
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

 /**
   * Signals the result of change identified by the \&quot;changeType\&quot; attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED            
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedNss {\n");
    
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
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

