package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.CancelModeType;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a parameter to select the mode of canceling an ongoing NS LCM operation occurrence.  It shall comply with the provisions defined in Table 6.5.2.16-1. 
 */
@Schema(description = "This type represents a parameter to select the mode of canceling an ongoing NS LCM operation occurrence.  It shall comply with the provisions defined in Table 6.5.2.16-1. ")
@Validated


public class CancelMode   {
  @JsonProperty("cancelMode")
  private CancelModeType cancelMode = null;

  public CancelMode cancelMode(CancelModeType cancelMode) {
    this.cancelMode = cancelMode;
    return this;
  }

  /**
   * Get cancelMode
   * @return cancelMode
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public CancelModeType getCancelMode() {
    return cancelMode;
  }

  public void setCancelMode(CancelModeType cancelMode) {
    this.cancelMode = cancelMode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CancelMode cancelMode = (CancelMode) o;
    return Objects.equals(this.cancelMode, cancelMode.cancelMode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cancelMode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CancelMode {\n");
    
    sb.append("    cancelMode: ").append(toIndentedString(cancelMode)).append("\n");
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
