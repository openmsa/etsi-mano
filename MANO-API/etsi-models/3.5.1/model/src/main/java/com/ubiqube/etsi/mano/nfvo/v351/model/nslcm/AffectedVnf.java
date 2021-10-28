package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.AffectedVnfChangedInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted and modified VNFs. NOTE: At least one of the attributes \&quot;changedVnfInfo\&quot;, \&quot;changedExtConnectivity\&quot; or \&quot;modificationsTriggeredByVnfPkgChange\&quot; shall be present. Not more than one of \&quot;changedVnfInfo\&quot; and \&quot;modificationsTriggeredByVnfPkgChange\&quot; shall be present. 
 */
@Schema(description = "This type provides information about added, deleted and modified VNFs. NOTE: At least one of the attributes \"changedVnfInfo\", \"changedExtConnectivity\" or \"modificationsTriggeredByVnfPkgChange\" shall be present. Not more than one of \"changedVnfInfo\" and \"modificationsTriggeredByVnfPkgChange\" shall be present. ")
@Validated


public class AffectedVnf  implements AnyOfAffectedVnf {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfProfileId")
  private String vnfProfileId = null;

  @JsonProperty("vnfName")
  private String vnfName = null;

  /**
   * Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY - CHANGE_VNFPKG 
   */
  public enum ChangeTypeEnum {
    ADD("ADD"),
    
    REMOVE("REMOVE"),
    
    INSTANTIATE("INSTANTIATE"),
    
    TERMINATE("TERMINATE"),
    
    SCALE("SCALE"),
    
    CHANGE_FLAVOUR("CHANGE_FLAVOUR"),
    
    HEAL("HEAL"),
    
    OPERATE("OPERATE"),
    
    MODIFY_INFORMATION("MODIFY_INFORMATION"),
    
    CHANGE_EXTERNAL_VNF_CONNECTIVITY("CHANGE_EXTERNAL_VNF_CONNECTIVITY"),
    
    CHANGE_VNFPKG("CHANGE_VNFPKG");

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

  @JsonProperty("changedInfo")
  private AffectedVnfChangedInfo changedInfo = null;

  public AffectedVnf vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public AffectedVnf vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public AffectedVnf vnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
    return this;
  }

  /**
   * Get vnfProfileId
   * @return vnfProfileId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfProfileId() {
    return vnfProfileId;
  }

  public void setVnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
  }

  public AffectedVnf vnfName(String vnfName) {
    this.vnfName = vnfName;
    return this;
  }

  /**
   * Name of the VNF Instance. 
   * @return vnfName
   **/
  @Schema(required = true, description = "Name of the VNF Instance. ")
      @NotNull

    public String getVnfName() {
    return vnfName;
  }

  public void setVnfName(String vnfName) {
    this.vnfName = vnfName;
  }

  public AffectedVnf changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY - CHANGE_VNFPKG 
   * @return changeType
   **/
  @Schema(required = true, description = "Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY - CHANGE_VNFPKG ")
      @NotNull

    public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedVnf changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }

  /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED 
   * @return changeResult
   **/
  @Schema(required = true, description = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED ")
      @NotNull

    public ChangeResultEnum getChangeResult() {
    return changeResult;
  }

  public void setChangeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
  }

  public AffectedVnf changedInfo(AffectedVnfChangedInfo changedInfo) {
    this.changedInfo = changedInfo;
    return this;
  }

  /**
   * Get changedInfo
   * @return changedInfo
   **/
  @Schema(description = "")
  
    @Valid
    public AffectedVnfChangedInfo getChangedInfo() {
    return changedInfo;
  }

  public void setChangedInfo(AffectedVnfChangedInfo changedInfo) {
    this.changedInfo = changedInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectedVnf affectedVnf = (AffectedVnf) o;
    return Objects.equals(this.vnfInstanceId, affectedVnf.vnfInstanceId) &&
        Objects.equals(this.vnfdId, affectedVnf.vnfdId) &&
        Objects.equals(this.vnfProfileId, affectedVnf.vnfProfileId) &&
        Objects.equals(this.vnfName, affectedVnf.vnfName) &&
        Objects.equals(this.changeType, affectedVnf.changeType) &&
        Objects.equals(this.changeResult, affectedVnf.changeResult) &&
        Objects.equals(this.changedInfo, affectedVnf.changedInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, vnfdId, vnfProfileId, vnfName, changeType, changeResult, changedInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVnf {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
