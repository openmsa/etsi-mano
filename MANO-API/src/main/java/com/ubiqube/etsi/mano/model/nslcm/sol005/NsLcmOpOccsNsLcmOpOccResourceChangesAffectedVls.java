package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type provides information about added, deleted and modified VLs.  It shall comply with the provisions in Table 6.5.3.4-1. 
 **/
@ApiModel(description="This type provides information about added, deleted and modified VLs.  It shall comply with the provisions in Table 6.5.3.4-1. ")
public class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique with respect to a NS.  Representation: string of variable length. ")
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
  **/
  private String nsVirtualLinkInstanceId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String nsVirtualLinkDescId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String vlProfileId = null;


@XmlType(name="ChangeTypeEnum")
@XmlEnum(String.class)
public enum ChangeTypeEnum {

@XmlEnumValue("ADD") ADD(String.valueOf("ADD")), @XmlEnumValue("DELETE") DELETE(String.valueOf("DELETE")), @XmlEnumValue("MODIFY") MODIFY(String.valueOf("MODIFY")), @XmlEnumValue("ADD_LINK_PORT") ADD_LINK_PORT(String.valueOf("ADD_LINK_PORT")), @XmlEnumValue("REMOVE_LINK_PORT") REMOVE_LINK_PORT(String.valueOf("REMOVE_LINK_PORT"));


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

  @ApiModelProperty(value = "Signals the type of change. Permitted values: - ADD - DELETE - MODIFY - ADD_LINK_PORT - REMOVE_LINK_PORT ")
 /**
   * Signals the type of change. Permitted values: - ADD - DELETE - MODIFY - ADD_LINK_PORT - REMOVE_LINK_PORT 
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
   * @return nsVirtualLinkInstanceId
  **/
  @JsonProperty("nsVirtualLinkInstanceId")
  @NotNull
  public String getNsVirtualLinkInstanceId() {
    return nsVirtualLinkInstanceId;
  }

  public void setNsVirtualLinkInstanceId(String nsVirtualLinkInstanceId) {
    this.nsVirtualLinkInstanceId = nsVirtualLinkInstanceId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls nsVirtualLinkInstanceId(String nsVirtualLinkInstanceId) {
    this.nsVirtualLinkInstanceId = nsVirtualLinkInstanceId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return nsVirtualLinkDescId
  **/
  @JsonProperty("nsVirtualLinkDescId")
  @NotNull
  public String getNsVirtualLinkDescId() {
    return nsVirtualLinkDescId;
  }

  public void setNsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls nsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return vlProfileId
  **/
  @JsonProperty("vlProfileId")
  @NotNull
  public String getVlProfileId() {
    return vlProfileId;
  }

  public void setVlProfileId(String vlProfileId) {
    this.vlProfileId = vlProfileId;
  }

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls vlProfileId(String vlProfileId) {
    this.vlProfileId = vlProfileId;
    return this;
  }

 /**
   * Signals the type of change. Permitted values: - ADD - DELETE - MODIFY - ADD_LINK_PORT - REMOVE_LINK_PORT 
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls changeType(ChangeTypeEnum changeType) {
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

  public NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsLcmOpOccsNsLcmOpOccResourceChangesAffectedVls {\n");
    
    sb.append("    nsVirtualLinkInstanceId: ").append(toIndentedString(nsVirtualLinkInstanceId)).append("\n");
    sb.append("    nsVirtualLinkDescId: ").append(toIndentedString(nsVirtualLinkDescId)).append("\n");
    sb.append("    vlProfileId: ").append(toIndentedString(vlProfileId)).append("\n");
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

