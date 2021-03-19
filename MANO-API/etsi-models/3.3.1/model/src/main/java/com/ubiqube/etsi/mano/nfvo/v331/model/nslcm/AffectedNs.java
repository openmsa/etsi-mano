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
package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.AffectedNsChangedInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted and modified nested NSs. It shall comply with the provisions in Table 6.5.3.6-1. 
 */
@Schema(description = "This type provides information about added, deleted and modified nested NSs. It shall comply with the provisions in Table 6.5.3.6-1. ")
@Validated


public class AffectedNs   {
  @JsonProperty("nsInstanceId")
  private String nsInstanceId = null;

  @JsonProperty("nsdId")
  private String nsdId = null;

  /**
   * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE 
   */
  public enum ChangeTypeEnum {
    ADD("ADD"),
    
    REMOVE("REMOVE"),
    
    INSTANTIATE("INSTANTIATE"),
    
    SCALE("SCALE"),
    
    UPDATE("UPDATE"),
    
    HEAL("HEAL"),
    
    TERMINATE("TERMINATE");

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
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED 
   */
  public enum ChangeResultEnum {
    COMPLETED("COMPLETED"),
    
    ROLLED_BACK("ROLLED_BACK"),
    
    FAILED("FAILED"),
    
    PARTIALLY_COMPLETED("PARTIALLY_COMPLETED");

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
  private AffectedNsChangedInfo changedInfo = null;

  public AffectedNs nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

  /**
   * Get nsInstanceId
   * @return nsInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public AffectedNs nsdId(String nsdId) {
    this.nsdId = nsdId;
    return this;
  }

  /**
   * Get nsdId
   * @return nsdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsdId() {
    return nsdId;
  }

  public void setNsdId(String nsdId) {
    this.nsdId = nsdId;
  }

  public AffectedNs changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE 
   * @return changeType
   **/
  @Schema(required = true, description = "Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - INSTANTIATE - SCALE - UPDATE - HEAL - TERMINATE ")
      @NotNull

    public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedNs changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }

  /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED 
   * @return changeResult
   **/
  @Schema(required = true, description = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED - PARTIALLY_COMPLETED ")
      @NotNull

    public ChangeResultEnum getChangeResult() {
    return changeResult;
  }

  public void setChangeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
  }

  public AffectedNs changedInfo(AffectedNsChangedInfo changedInfo) {
    this.changedInfo = changedInfo;
    return this;
  }

  /**
   * Get changedInfo
   * @return changedInfo
   **/
  @Schema(description = "")
  
    @Valid
    public AffectedNsChangedInfo getChangedInfo() {
    return changedInfo;
  }

  public void setChangedInfo(AffectedNsChangedInfo changedInfo) {
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
    AffectedNs affectedNs = (AffectedNs) o;
    return Objects.equals(this.nsInstanceId, affectedNs.nsInstanceId) &&
        Objects.equals(this.nsdId, affectedNs.nsdId) &&
        Objects.equals(this.changeType, affectedNs.changeType) &&
        Objects.equals(this.changeResult, affectedNs.changeResult) &&
        Objects.equals(this.changedInfo, affectedNs.changedInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsInstanceId, nsdId, changeType, changeResult, changedInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedNs {\n");
    
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
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
