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
package com.ubiqube.etsi.mano.nfvo.v281.model.nslcm;

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
 * This type provides information about added, deleted and modified SAP of a NS. It shall comply with the provisions in Table 6.5.3.7-1. 
 */
@ApiModel(description = "This type provides information about added, deleted and modified SAP of a NS. It shall comply with the provisions in Table 6.5.3.7-1. ")
@Validated

public class AffectedSap   {
  @JsonProperty("sapInstanceId")
  private String sapInstanceId = null;

  @JsonProperty("sapdId")
  private String sapdId = null;

  @JsonProperty("sapName")
  private String sapName = null;

  /**
   * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - MODIFY 
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

  public AffectedSap sapInstanceId(String sapInstanceId) {
    this.sapInstanceId = sapInstanceId;
    return this;
  }

  /**
   * Identifier of the nested NS instance. 
   * @return sapInstanceId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the nested NS instance. ")
  @NotNull


  public String getSapInstanceId() {
    return sapInstanceId;
  }

  public void setSapInstanceId(String sapInstanceId) {
    this.sapInstanceId = sapInstanceId;
  }

  public AffectedSap sapdId(String sapdId) {
    this.sapdId = sapdId;
    return this;
  }

  /**
   * Identifier of the NSD of the nested NS instance. 
   * @return sapdId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the NSD of the nested NS instance. ")
  @NotNull


  public String getSapdId() {
    return sapdId;
  }

  public void setSapdId(String sapdId) {
    this.sapdId = sapdId;
  }

  public AffectedSap sapName(String sapName) {
    this.sapName = sapName;
    return this;
  }

  /**
   * Human readable name for the SAP. 
   * @return sapName
  **/
  @ApiModelProperty(value = "Human readable name for the SAP. ")


  public String getSapName() {
    return sapName;
  }

  public void setSapName(String sapName) {
    this.sapName = sapName;
  }

  public AffectedSap changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - MODIFY 
   * @return changeType
  **/
  @ApiModelProperty(value = "Signals the type of lifecycle change. Permitted values: - ADD - REMOVE - MODIFY ")


  public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedSap changeResult(ChangeResultEnum changeResult) {
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
    AffectedSap affectedSap = (AffectedSap) o;
    return Objects.equals(this.sapInstanceId, affectedSap.sapInstanceId) &&
        Objects.equals(this.sapdId, affectedSap.sapdId) &&
        Objects.equals(this.sapName, affectedSap.sapName) &&
        Objects.equals(this.changeType, affectedSap.changeType) &&
        Objects.equals(this.changeResult, affectedSap.changeResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sapInstanceId, sapdId, sapName, changeType, changeResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedSap {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

