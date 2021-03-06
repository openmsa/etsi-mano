package com.ubiqube.etsi.mano.em.v261.model.vnfconfig;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v261.model.vnfconfig.VnfConfigurationData;
import com.ubiqube.etsi.mano.em.v261.model.vnfconfig.VnfcConfigurationData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents configuration parameters of a VNF instance and its VNFC instances. 
 */
@ApiModel(description = "This type represents configuration parameters of a VNF instance and its VNFC instances. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-11T19:16:20.445+01:00")

public class VnfConfiguration   {
  @JsonProperty("vnfConfigurationData")
  private VnfConfigurationData vnfConfigurationData = null;

  @JsonProperty("vnfcConfigurationData")
  @Valid
  private List<VnfcConfigurationData> vnfcConfigurationData = null;

  public VnfConfiguration vnfConfigurationData(VnfConfigurationData vnfConfigurationData) {
    this.vnfConfigurationData = vnfConfigurationData;
    return this;
  }

  /**
   * Configuration parameters of the VNF instance. 
   * @return vnfConfigurationData
  **/
  @ApiModelProperty(required = true, value = "Configuration parameters of the VNF instance. ")
  @NotNull

  @Valid

  public VnfConfigurationData getVnfConfigurationData() {
    return vnfConfigurationData;
  }

  public void setVnfConfigurationData(VnfConfigurationData vnfConfigurationData) {
    this.vnfConfigurationData = vnfConfigurationData;
  }

  public VnfConfiguration vnfcConfigurationData(List<VnfcConfigurationData> vnfcConfigurationData) {
    this.vnfcConfigurationData = vnfcConfigurationData;
    return this;
  }

  public VnfConfiguration addVnfcConfigurationDataItem(VnfcConfigurationData vnfcConfigurationDataItem) {
    if (this.vnfcConfigurationData == null) {
      this.vnfcConfigurationData = new ArrayList<>();
    }
    this.vnfcConfigurationData.add(vnfcConfigurationDataItem);
    return this;
  }

  /**
   * Configuration parameters of the VNFC instances. 
   * @return vnfcConfigurationData
  **/
  @ApiModelProperty(value = "Configuration parameters of the VNFC instances. ")

  @Valid

  public List<VnfcConfigurationData> getVnfcConfigurationData() {
    return vnfcConfigurationData;
  }

  public void setVnfcConfigurationData(List<VnfcConfigurationData> vnfcConfigurationData) {
    this.vnfcConfigurationData = vnfcConfigurationData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfConfiguration vnfConfiguration = (VnfConfiguration) o;
    return Objects.equals(this.vnfConfigurationData, vnfConfiguration.vnfConfigurationData) &&
        Objects.equals(this.vnfcConfigurationData, vnfConfiguration.vnfcConfigurationData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfConfigurationData, vnfcConfigurationData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfConfiguration {\n");
    
    sb.append("    vnfConfigurationData: ").append(toIndentedString(vnfConfigurationData)).append("\n");
    sb.append("    vnfcConfigurationData: ").append(toIndentedString(vnfcConfigurationData)).append("\n");
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

