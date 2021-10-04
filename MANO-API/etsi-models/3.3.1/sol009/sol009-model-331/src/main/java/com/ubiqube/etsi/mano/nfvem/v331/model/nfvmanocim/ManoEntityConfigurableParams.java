package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ClockSyncInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents list of parameters that can be configured on the  NFV MANO functional entity.  
 */
@ApiModel(description = "This type represents list of parameters that can be configured on the  NFV MANO functional entity.  ")
@Validated
public class ManoEntityConfigurableParams   {
  @JsonProperty("clockSyncs")
  @Valid
  private List<ClockSyncInfo> clockSyncs = new ArrayList<>();

  @JsonProperty("defaultLogCompileBySizeValue")
  private BigDecimal defaultLogCompileBySizeValue = null;

  @JsonProperty("defaultLogCompileByTimerValue")
  private BigDecimal defaultLogCompileByTimerValue = null;

  public ManoEntityConfigurableParams clockSyncs(List<ClockSyncInfo> clockSyncs) {
    this.clockSyncs = clockSyncs;
    return this;
  }

  public ManoEntityConfigurableParams addClockSyncsItem(ClockSyncInfo clockSyncsItem) {
    this.clockSyncs.add(clockSyncsItem);
    return this;
  }

  /**
   * Properties of the clock synchronization to be used by the NFV-MANO functional entity.
   * @return clockSyncs
  **/
  @ApiModelProperty(required = true, value = "Properties of the clock synchronization to be used by the NFV-MANO functional entity.")
      @NotNull
    @Valid
  @Size(min=1)   public List<ClockSyncInfo> getClockSyncs() {
    return clockSyncs;
  }

  public void setClockSyncs(List<ClockSyncInfo> clockSyncs) {
    this.clockSyncs = clockSyncs;
  }

  public ManoEntityConfigurableParams defaultLogCompileBySizeValue(BigDecimal defaultLogCompileBySizeValue) {
    this.defaultLogCompileBySizeValue = defaultLogCompileBySizeValue;
    return this;
  }

  /**
   * Get defaultLogCompileBySizeValue
   * @return defaultLogCompileBySizeValue
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public BigDecimal getDefaultLogCompileBySizeValue() {
    return defaultLogCompileBySizeValue;
  }

  public void setDefaultLogCompileBySizeValue(BigDecimal defaultLogCompileBySizeValue) {
    this.defaultLogCompileBySizeValue = defaultLogCompileBySizeValue;
  }

  public ManoEntityConfigurableParams defaultLogCompileByTimerValue(BigDecimal defaultLogCompileByTimerValue) {
    this.defaultLogCompileByTimerValue = defaultLogCompileByTimerValue;
    return this;
  }

  /**
   * Get defaultLogCompileByTimerValue
   * @return defaultLogCompileByTimerValue
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public BigDecimal getDefaultLogCompileByTimerValue() {
    return defaultLogCompileByTimerValue;
  }

  public void setDefaultLogCompileByTimerValue(BigDecimal defaultLogCompileByTimerValue) {
    this.defaultLogCompileByTimerValue = defaultLogCompileByTimerValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManoEntityConfigurableParams manoEntityConfigurableParams = (ManoEntityConfigurableParams) o;
    return Objects.equals(this.clockSyncs, manoEntityConfigurableParams.clockSyncs) &&
        Objects.equals(this.defaultLogCompileBySizeValue, manoEntityConfigurableParams.defaultLogCompileBySizeValue) &&
        Objects.equals(this.defaultLogCompileByTimerValue, manoEntityConfigurableParams.defaultLogCompileByTimerValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clockSyncs, defaultLogCompileBySizeValue, defaultLogCompileByTimerValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoEntityConfigurableParams {\n");
    
    sb.append("    clockSyncs: ").append(toIndentedString(clockSyncs)).append("\n");
    sb.append("    defaultLogCompileBySizeValue: ").append(toIndentedString(defaultLogCompileBySizeValue)).append("\n");
    sb.append("    defaultLogCompileByTimerValue: ").append(toIndentedString(defaultLogCompileByTimerValue)).append("\n");
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
