package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ClockSyncInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoConfigModificationRequestManoServiceModifications;
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
 * This type represents attribute modifications for configuration parameters  of an NFV-MANO functional entity.  
 */
@ApiModel(description = "This type represents attribute modifications for configuration parameters  of an NFV-MANO functional entity.  ")
@Validated
public class ManoConfigModificationRequest   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("clockSyncs")
  @Valid
  private List<ClockSyncInfo> clockSyncs = null;

  @JsonProperty("clockSyncsDeleteIds")
  @Valid
  private List<String> clockSyncsDeleteIds = null;

  @JsonProperty("defaultLogCompileBySizeValue")
  private BigDecimal defaultLogCompileBySizeValue = null;

  @JsonProperty("defaultLogCompileByTimerValue")
  private BigDecimal defaultLogCompileByTimerValue = null;

  @JsonProperty("manoServiceModifications")
  @Valid
  private List<ManoConfigModificationRequestManoServiceModifications> manoServiceModifications = null;

  public ManoConfigModificationRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * New value of the \"name\" attribute in \"ManoEntity\". 
   * @return name
  **/
  @ApiModelProperty(value = "New value of the \"name\" attribute in \"ManoEntity\". ")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ManoConfigModificationRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * New value of the \"description\" attribute in \"ManoEntity\". 
   * @return description
  **/
  @ApiModelProperty(value = "New value of the \"description\" attribute in \"ManoEntity\". ")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ManoConfigModificationRequest clockSyncs(List<ClockSyncInfo> clockSyncs) {
    this.clockSyncs = clockSyncs;
    return this;
  }

  public ManoConfigModificationRequest addClockSyncsItem(ClockSyncInfo clockSyncsItem) {
    if (this.clockSyncs == null) {
      this.clockSyncs = new ArrayList<>();
    }
    this.clockSyncs.add(clockSyncsItem);
    return this;
  }

  /**
   * New content of certain entries in the \"clockSyncs\" attribute array in  the \"ManoEntityConfigurableParams\", as defined below this table.  
   * @return clockSyncs
  **/
  @ApiModelProperty(value = "New content of certain entries in the \"clockSyncs\" attribute array in  the \"ManoEntityConfigurableParams\", as defined below this table.  ")
      @Valid
    public List<ClockSyncInfo> getClockSyncs() {
    return clockSyncs;
  }

  public void setClockSyncs(List<ClockSyncInfo> clockSyncs) {
    this.clockSyncs = clockSyncs;
  }

  public ManoConfigModificationRequest clockSyncsDeleteIds(List<String> clockSyncsDeleteIds) {
    this.clockSyncsDeleteIds = clockSyncsDeleteIds;
    return this;
  }

  public ManoConfigModificationRequest addClockSyncsDeleteIdsItem(String clockSyncsDeleteIdsItem) {
    if (this.clockSyncsDeleteIds == null) {
      this.clockSyncsDeleteIds = new ArrayList<>();
    }
    this.clockSyncsDeleteIds.add(clockSyncsDeleteIdsItem);
    return this;
  }

  /**
   * List of identifiers entries to be deleted from the \"clockSyncs\"  attribute array in the \"ManoEntityConfigurableParams\", as defined  below this table. 
   * @return clockSyncsDeleteIds
  **/
  @ApiModelProperty(value = "List of identifiers entries to be deleted from the \"clockSyncs\"  attribute array in the \"ManoEntityConfigurableParams\", as defined  below this table. ")
  
    public List<String> getClockSyncsDeleteIds() {
    return clockSyncsDeleteIds;
  }

  public void setClockSyncsDeleteIds(List<String> clockSyncsDeleteIds) {
    this.clockSyncsDeleteIds = clockSyncsDeleteIds;
  }

  public ManoConfigModificationRequest defaultLogCompileBySizeValue(BigDecimal defaultLogCompileBySizeValue) {
    this.defaultLogCompileBySizeValue = defaultLogCompileBySizeValue;
    return this;
  }

  /**
   * Get defaultLogCompileBySizeValue
   * @return defaultLogCompileBySizeValue
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public BigDecimal getDefaultLogCompileBySizeValue() {
    return defaultLogCompileBySizeValue;
  }

  public void setDefaultLogCompileBySizeValue(BigDecimal defaultLogCompileBySizeValue) {
    this.defaultLogCompileBySizeValue = defaultLogCompileBySizeValue;
  }

  public ManoConfigModificationRequest defaultLogCompileByTimerValue(BigDecimal defaultLogCompileByTimerValue) {
    this.defaultLogCompileByTimerValue = defaultLogCompileByTimerValue;
    return this;
  }

  /**
   * Get defaultLogCompileByTimerValue
   * @return defaultLogCompileByTimerValue
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public BigDecimal getDefaultLogCompileByTimerValue() {
    return defaultLogCompileByTimerValue;
  }

  public void setDefaultLogCompileByTimerValue(BigDecimal defaultLogCompileByTimerValue) {
    this.defaultLogCompileByTimerValue = defaultLogCompileByTimerValue;
  }

  public ManoConfigModificationRequest manoServiceModifications(List<ManoConfigModificationRequestManoServiceModifications> manoServiceModifications) {
    this.manoServiceModifications = manoServiceModifications;
    return this;
  }

  public ManoConfigModificationRequest addManoServiceModificationsItem(ManoConfigModificationRequestManoServiceModifications manoServiceModificationsItem) {
    if (this.manoServiceModifications == null) {
      this.manoServiceModifications = new ArrayList<>();
    }
    this.manoServiceModifications.add(manoServiceModificationsItem);
    return this;
  }

  /**
   * New content of certain entries in the \"manoServices\" attribute array  in the \"ManoEntity\", as defined below this table. 
   * @return manoServiceModifications
  **/
  @ApiModelProperty(value = "New content of certain entries in the \"manoServices\" attribute array  in the \"ManoEntity\", as defined below this table. ")
      @Valid
    public List<ManoConfigModificationRequestManoServiceModifications> getManoServiceModifications() {
    return manoServiceModifications;
  }

  public void setManoServiceModifications(List<ManoConfigModificationRequestManoServiceModifications> manoServiceModifications) {
    this.manoServiceModifications = manoServiceModifications;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManoConfigModificationRequest manoConfigModificationRequest = (ManoConfigModificationRequest) o;
    return Objects.equals(this.name, manoConfigModificationRequest.name) &&
        Objects.equals(this.description, manoConfigModificationRequest.description) &&
        Objects.equals(this.clockSyncs, manoConfigModificationRequest.clockSyncs) &&
        Objects.equals(this.clockSyncsDeleteIds, manoConfigModificationRequest.clockSyncsDeleteIds) &&
        Objects.equals(this.defaultLogCompileBySizeValue, manoConfigModificationRequest.defaultLogCompileBySizeValue) &&
        Objects.equals(this.defaultLogCompileByTimerValue, manoConfigModificationRequest.defaultLogCompileByTimerValue) &&
        Objects.equals(this.manoServiceModifications, manoConfigModificationRequest.manoServiceModifications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, clockSyncs, clockSyncsDeleteIds, defaultLogCompileBySizeValue, defaultLogCompileByTimerValue, manoServiceModifications);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoConfigModificationRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    clockSyncs: ").append(toIndentedString(clockSyncs)).append("\n");
    sb.append("    clockSyncsDeleteIds: ").append(toIndentedString(clockSyncsDeleteIds)).append("\n");
    sb.append("    defaultLogCompileBySizeValue: ").append(toIndentedString(defaultLogCompileBySizeValue)).append("\n");
    sb.append("    defaultLogCompileByTimerValue: ").append(toIndentedString(defaultLogCompileByTimerValue)).append("\n");
    sb.append("    manoServiceModifications: ").append(toIndentedString(manoServiceModifications)).append("\n");
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
