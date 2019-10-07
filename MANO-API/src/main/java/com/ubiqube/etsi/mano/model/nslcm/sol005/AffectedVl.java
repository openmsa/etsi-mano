package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted and modified VLs.  It shall comply with the provisions in Table 6.5.3.4-1. 
 */
@ApiModel(description = "This type provides information about added, deleted and modified VLs.  It shall comply with the provisions in Table 6.5.3.4-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class AffectedVl   {
  @JsonProperty("nsVirtualLinkInstanceId")
  private String nsVirtualLinkInstanceId = null;

  @JsonProperty("nsVirtualLinkDescId")
  private String nsVirtualLinkDescId = null;

  @JsonProperty("vlProfileId")
  private String vlProfileId = null;

  /**
   * Signals the type of change. Permitted values: - ADD - DELETE - MODIFY - ADD_LINK_PORT - REMOVE_LINK_PORT 
   */
  public enum ChangeTypeEnum {
    ADD("ADD"),
    
    DELETE("DELETE"),
    
    MODIFY("MODIFY"),
    
    ADD_LINK_PORT("ADD_LINK_PORT"),
    
    REMOVE_LINK_PORT("REMOVE_LINK_PORT");

    private String value;

    ChangeTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ChangeTypeEnum fromValue(String text) {
      for (ChangeTypeEnum b : ChangeTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("changeType")
  private ChangeTypeEnum changeType = null;

  /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED 
   */
  public enum ChangeResultEnum {
    COMPLETED("COMPLETED"),
    
    ROLLED_BACK("ROLLED_BACK"),
    
    FAILED("FAILED");

    private String value;

    ChangeResultEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ChangeResultEnum fromValue(String text) {
      for (ChangeResultEnum b : ChangeResultEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("changeResult")
  private ChangeResultEnum changeResult = null;

  public AffectedVl nsVirtualLinkInstanceId(String nsVirtualLinkInstanceId) {
    this.nsVirtualLinkInstanceId = nsVirtualLinkInstanceId;
    return this;
  }

  /**
   * Identifier of the VL Instance. 
   * @return nsVirtualLinkInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VL Instance. ")
  @NotNull


  public String getNsVirtualLinkInstanceId() {
    return nsVirtualLinkInstanceId;
  }

  public void setNsVirtualLinkInstanceId(String nsVirtualLinkInstanceId) {
    this.nsVirtualLinkInstanceId = nsVirtualLinkInstanceId;
  }

  public AffectedVl nsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
    return this;
  }

  /**
   * Identifier of the VLD in the NSD for this VL. 
   * @return nsVirtualLinkDescId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VLD in the NSD for this VL. ")
  @NotNull


  public String getNsVirtualLinkDescId() {
    return nsVirtualLinkDescId;
  }

  public void setNsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
  }

  public AffectedVl vlProfileId(String vlProfileId) {
    this.vlProfileId = vlProfileId;
    return this;
  }

  /**
   * Identifier of the VLD in the NSD for this VL. 
   * @return vlProfileId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VLD in the NSD for this VL. ")
  @NotNull


  public String getVlProfileId() {
    return vlProfileId;
  }

  public void setVlProfileId(String vlProfileId) {
    this.vlProfileId = vlProfileId;
  }

  public AffectedVl changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: - ADD - DELETE - MODIFY - ADD_LINK_PORT - REMOVE_LINK_PORT 
   * @return changeType
  **/
  @ApiModelProperty(value = "Signals the type of change. Permitted values: - ADD - DELETE - MODIFY - ADD_LINK_PORT - REMOVE_LINK_PORT ")


  public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedVl changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }

  /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED 
   * @return changeResult
  **/
  @ApiModelProperty(value = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED ")


  public ChangeResultEnum getChangeResult() {
    return changeResult;
  }

  public void setChangeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectedVl affectedVl = (AffectedVl) o;
    return Objects.equals(this.nsVirtualLinkInstanceId, affectedVl.nsVirtualLinkInstanceId) &&
        Objects.equals(this.nsVirtualLinkDescId, affectedVl.nsVirtualLinkDescId) &&
        Objects.equals(this.vlProfileId, affectedVl.vlProfileId) &&
        Objects.equals(this.changeType, affectedVl.changeType) &&
        Objects.equals(this.changeResult, affectedVl.changeResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsVirtualLinkInstanceId, nsVirtualLinkDescId, vlProfileId, changeType, changeResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVl {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

