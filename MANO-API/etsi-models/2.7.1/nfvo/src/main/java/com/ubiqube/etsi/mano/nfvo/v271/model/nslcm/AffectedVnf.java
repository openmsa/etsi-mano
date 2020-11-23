/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v271.model.nslcm.AffectedVnfChangedInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted and modified VNFs.  It shall comply with the provisions in Table 6.5.3.2-1. 
 */
@ApiModel(description = "This type provides information about added, deleted and modified VNFs.  It shall comply with the provisions in Table 6.5.3.2-1. ")
@Validated
public class AffectedVnf   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfProfileId")
  private String vnfProfileId = null;

  @JsonProperty("vnfName")
  private String vnfName = null;

  /**
   * Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY 
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
    
    CHANGE_EXTERNAL_VNF_CONNECTIVITY("CHANGE_EXTERNAL_VNF_CONNECTIVITY");

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
  @ApiModelProperty(required = true, value = "")
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
  @ApiModelProperty(required = true, value = "")
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
  @ApiModelProperty(required = true, value = "")
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
  @ApiModelProperty(required = true, value = "Name of the VNF Instance. ")
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
   * Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY 
   * @return changeType
  **/
  @ApiModelProperty(required = true, value = "Signals the type of change Permitted values: - ADD - REMOVE - INSTANTIATE - TERMINATE - SCALE - CHANGE_FLAVOUR - HEAL - OPERATE - MODIFY_INFORMATION - CHANGE_EXTERNAL_VNF_CONNECTIVITY ")
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
  @ApiModelProperty(required = true, value = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED ")
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
  @ApiModelProperty(value = "")
  
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
