package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted and modified PNFs.  It shall comply with the provisions in Table 6.5.3.3-1. 
 */
@ApiModel(description = "This type provides information about added, deleted and modified PNFs.  It shall comply with the provisions in Table 6.5.3.3-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class AffectedPnf   {
  @JsonProperty("pnfId")
  private String pnfId = null;

  @JsonProperty("pnfdId")
  private String pnfdId = null;

  @JsonProperty("pnfProfileId")
  private String pnfProfileId = null;

  @JsonProperty("pnfName")
  private String pnfName = null;

  @JsonProperty("cpInstanceId")
  @Valid
  private List<String> cpInstanceId = new ArrayList<>();

  /**
   * Signals the type of change. Permitted values: - ADD - REMOVE - MODIFY 
   */
  public enum ChangeTypeEnum {
    ADD("ADD"),
    
    REMOVE("REMOVE"),
    
    MODIFY("MODIFY");

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

  public AffectedPnf pnfId(String pnfId) {
    this.pnfId = pnfId;
    return this;
  }

  /**
   * Identifier of the affected PNF. This identifier is allocated by the OSS/BSS. 
   * @return pnfId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the affected PNF. This identifier is allocated by the OSS/BSS. ")
  @NotNull


  public String getPnfId() {
    return pnfId;
  }

  public void setPnfId(String pnfId) {
    this.pnfId = pnfId;
  }

  public AffectedPnf pnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
    return this;
  }

  /**
   * Identifier of the PNFD on which the PNF is based. 
   * @return pnfdId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the PNFD on which the PNF is based. ")
  @NotNull


  public String getPnfdId() {
    return pnfdId;
  }

  public void setPnfdId(String pnfdId) {
    this.pnfdId = pnfdId;
  }

  public AffectedPnf pnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
    return this;
  }

  /**
   * Identifier of the VNF profile of the NSD. 
   * @return pnfProfileId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the VNF profile of the NSD. ")
  @NotNull


  public String getPnfProfileId() {
    return pnfProfileId;
  }

  public void setPnfProfileId(String pnfProfileId) {
    this.pnfProfileId = pnfProfileId;
  }

  public AffectedPnf pnfName(String pnfName) {
    this.pnfName = pnfName;
    return this;
  }

  /**
   * Name of the PNF. 
   * @return pnfName
  **/
  @ApiModelProperty(value = "Name of the PNF. ")


  public String getPnfName() {
    return pnfName;
  }

  public void setPnfName(String pnfName) {
    this.pnfName = pnfName;
  }

  public AffectedPnf cpInstanceId(List<String> cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

  public AffectedPnf addCpInstanceIdItem(String cpInstanceIdItem) {
    this.cpInstanceId.add(cpInstanceIdItem);
    return this;
  }

  /**
   * Identifier of the CP in the scope of the PNF. 
   * @return cpInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the CP in the scope of the PNF. ")
  @NotNull


  public List<String> getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(List<String> cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public AffectedPnf changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: - ADD - REMOVE - MODIFY 
   * @return changeType
  **/
  @ApiModelProperty(value = "Signals the type of change. Permitted values: - ADD - REMOVE - MODIFY ")


  public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedPnf changeResult(ChangeResultEnum changeResult) {
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
    AffectedPnf affectedPnf = (AffectedPnf) o;
    return Objects.equals(this.pnfId, affectedPnf.pnfId) &&
        Objects.equals(this.pnfdId, affectedPnf.pnfdId) &&
        Objects.equals(this.pnfProfileId, affectedPnf.pnfProfileId) &&
        Objects.equals(this.pnfName, affectedPnf.pnfName) &&
        Objects.equals(this.cpInstanceId, affectedPnf.cpInstanceId) &&
        Objects.equals(this.changeType, affectedPnf.changeType) &&
        Objects.equals(this.changeResult, affectedPnf.changeResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pnfId, pnfdId, pnfProfileId, pnfName, cpInstanceId, changeType, changeResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedPnf {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

