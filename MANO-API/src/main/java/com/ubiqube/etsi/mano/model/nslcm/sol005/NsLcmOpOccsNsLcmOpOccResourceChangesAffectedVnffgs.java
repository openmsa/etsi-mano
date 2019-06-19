package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type provides information about added, deleted and modified VNFFG instances.  It shall comply with the provisions in Table 6.5.3.5-1. 
 **/
@ApiModel(description="This type provides information about added, deleted and modified VNFFG instances.  It shall comply with the provisions in Table 6.5.3.5-1. ")
public class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique with respect to a NS.  Representation: string of variable length. ")
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
  **/
  private String vnffgInstanceId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String vnffgdId = null;


@XmlType(name="ChangeTypeEnum")
@XmlEnum(String.class)
public enum ChangeTypeEnum {

@XmlEnumValue("ADD") ADD(String.valueOf("ADD")), @XmlEnumValue("DELETE") DELETE(String.valueOf("DELETE")), @XmlEnumValue("MODIFY") MODIFY(String.valueOf("MODIFY"));


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

  @ApiModelProperty(value = "Signals the type of change. Permitted values: - ADD - DELETE - MODIFY ")
 /**
   * Signals the type of change. Permitted values: - ADD - DELETE - MODIFY 
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
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
   * @return vnffgInstanceId
  **/
  @JsonProperty("vnffgInstanceId")
  @NotNull
  public String getVnffgInstanceId() {
    return vnffgInstanceId;
  }

  public void setVnffgInstanceId(String vnffgInstanceId) {
    this.vnffgInstanceId = vnffgInstanceId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs vnffgInstanceId(String vnffgInstanceId) {
    this.vnffgInstanceId = vnffgInstanceId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return vnffgdId
  **/
  @JsonProperty("vnffgdId")
  @NotNull
  public String getVnffgdId() {
    return vnffgdId;
  }

  public void setVnffgdId(String vnffgdId) {
    this.vnffgdId = vnffgdId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs vnffgdId(String vnffgdId) {
    this.vnffgdId = vnffgdId;
    return this;
  }

 /**
   * Signals the type of change. Permitted values: - ADD - DELETE - MODIFY 
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs changeType(ChangeTypeEnum changeType) {
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVnffgs {\n");
    
    sb.append("    vnffgInstanceId: ").append(toIndentedString(vnffgInstanceId)).append("\n");
    sb.append("    vnffgdId: ").append(toIndentedString(vnffgdId)).append("\n");
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

