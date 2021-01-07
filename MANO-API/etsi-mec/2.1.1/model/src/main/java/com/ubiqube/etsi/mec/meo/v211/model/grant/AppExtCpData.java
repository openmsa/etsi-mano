package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.grant.AppExtCpConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AppExtCpData
 */
@Validated
public class AppExtCpData   {
  @JsonProperty("cpConfig")
  @Valid
  private List<AppExtCpConfig> cpConfig = new ArrayList<>();

  @JsonProperty("cpdId")
  private String cpdId = null;

  public AppExtCpData cpConfig(List<AppExtCpConfig> cpConfig) {
    this.cpConfig = cpConfig;
    return this;
  }

  public AppExtCpData addCpConfigItem(AppExtCpConfig cpConfigItem) {
    this.cpConfig.add(cpConfigItem);
    return this;
  }

  /**
   * List of instance data that need to be configured on the CP instances created from the respective CPD.
   * @return cpConfig
  **/
  @ApiModelProperty(required = true, value = "List of instance data that need to be configured on the CP instances created from the respective CPD.")
      @NotNull
    @Valid
  @Size(min=1)   public List<AppExtCpConfig> getCpConfig() {
    return cpConfig;
  }

  public void setCpConfig(List<AppExtCpConfig> cpConfig) {
    this.cpConfig = cpConfig;
  }

  public AppExtCpData cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

  /**
   * The identifier of the CPD in the AppD.
   * @return cpdId
  **/
  @ApiModelProperty(required = true, value = "The identifier of the CPD in the AppD.")
      @NotNull

    public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppExtCpData appExtCpData = (AppExtCpData) o;
    return Objects.equals(this.cpConfig, appExtCpData.cpConfig) &&
        Objects.equals(this.cpdId, appExtCpData.cpdId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpConfig, cpdId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppExtCpData {\n");
    
    sb.append("    cpConfig: ").append(toIndentedString(cpConfig)).append("\n");
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
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
