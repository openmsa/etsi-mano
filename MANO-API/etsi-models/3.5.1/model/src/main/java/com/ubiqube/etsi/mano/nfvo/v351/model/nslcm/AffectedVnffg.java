package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted and modified VNFFG instances. It shall comply with the provisions in Table 6.5.3.5-1. 
 */
@Schema(description = "This type provides information about added, deleted and modified VNFFG instances. It shall comply with the provisions in Table 6.5.3.5-1. ")
@Validated


public class AffectedVnffg   {
  @JsonProperty("vnffgInstanceId")
  private String vnffgInstanceId = null;

  @JsonProperty("vnffgdId")
  private String vnffgdId = null;

  /**
   * Signals the type of change. Permitted values: - ADD - DELETE - MODIFY 
   */
  public enum ChangeTypeEnum {
    ADD("ADD"),
    
    DELETE("DELETE"),
    
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

  public AffectedVnffg vnffgInstanceId(String vnffgInstanceId) {
    this.vnffgInstanceId = vnffgInstanceId;
    return this;
  }

  /**
   * Get vnffgInstanceId
   * @return vnffgInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnffgInstanceId() {
    return vnffgInstanceId;
  }

  public void setVnffgInstanceId(String vnffgInstanceId) {
    this.vnffgInstanceId = vnffgInstanceId;
  }

  public AffectedVnffg vnffgdId(String vnffgdId) {
    this.vnffgdId = vnffgdId;
    return this;
  }

  /**
   * Get vnffgdId
   * @return vnffgdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnffgdId() {
    return vnffgdId;
  }

  public void setVnffgdId(String vnffgdId) {
    this.vnffgdId = vnffgdId;
  }

  public AffectedVnffg changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: - ADD - DELETE - MODIFY 
   * @return changeType
   **/
  @Schema(description = "Signals the type of change. Permitted values: - ADD - DELETE - MODIFY ")
  
    public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedVnffg changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }

  /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED 
   * @return changeResult
   **/
  @Schema(description = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: - COMPLETED - ROLLED_BACK - FAILED ")
  
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
    AffectedVnffg affectedVnffg = (AffectedVnffg) o;
    return Objects.equals(this.vnffgInstanceId, affectedVnffg.vnffgInstanceId) &&
        Objects.equals(this.vnffgdId, affectedVnffg.vnffgdId) &&
        Objects.equals(this.changeType, affectedVnffg.changeType) &&
        Objects.equals(this.changeResult, affectedVnffg.changeResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnffgInstanceId, vnffgdId, changeType, changeResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVnffg {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
