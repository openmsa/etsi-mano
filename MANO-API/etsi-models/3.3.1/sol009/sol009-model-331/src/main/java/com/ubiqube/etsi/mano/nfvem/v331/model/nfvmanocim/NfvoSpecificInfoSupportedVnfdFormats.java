package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

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
 * Supported VNFD data formats. 
 */
@ApiModel(description = "Supported VNFD data formats. ")
@Validated
public class NfvoSpecificInfoSupportedVnfdFormats   {
  /**
   * Name of the VNFD format. Permitted values:   - TOSCA: The VNFD follows TOSCA definition, according to ETSI    GS NFV-SOL 001 standard.   - YANG: The VNFD follows YANG definition according to ETSI    GS NFV-SOL 006 standard. 
   */
  public enum VnfdFormatEnum {
    TOSCA("TOSCA"),
    
    YANG("YANG");

    private String value;

    VnfdFormatEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static VnfdFormatEnum fromValue(String text) {
      for (VnfdFormatEnum b : VnfdFormatEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("vnfdFormat")
  private VnfdFormatEnum vnfdFormat = null;

  @JsonProperty("standardVersion")
  private String standardVersion = null;

  public NfvoSpecificInfoSupportedVnfdFormats vnfdFormat(VnfdFormatEnum vnfdFormat) {
    this.vnfdFormat = vnfdFormat;
    return this;
  }

  /**
   * Name of the VNFD format. Permitted values:   - TOSCA: The VNFD follows TOSCA definition, according to ETSI    GS NFV-SOL 001 standard.   - YANG: The VNFD follows YANG definition according to ETSI    GS NFV-SOL 006 standard. 
   * @return vnfdFormat
  **/
  @ApiModelProperty(required = true, value = "Name of the VNFD format. Permitted values:   - TOSCA: The VNFD follows TOSCA definition, according to ETSI    GS NFV-SOL 001 standard.   - YANG: The VNFD follows YANG definition according to ETSI    GS NFV-SOL 006 standard. ")
      @NotNull

    public VnfdFormatEnum getVnfdFormat() {
    return vnfdFormat;
  }

  public void setVnfdFormat(VnfdFormatEnum vnfdFormat) {
    this.vnfdFormat = vnfdFormat;
  }

  public NfvoSpecificInfoSupportedVnfdFormats standardVersion(String standardVersion) {
    this.standardVersion = standardVersion;
    return this;
  }

  /**
   * Get standardVersion
   * @return standardVersion
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getStandardVersion() {
    return standardVersion;
  }

  public void setStandardVersion(String standardVersion) {
    this.standardVersion = standardVersion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NfvoSpecificInfoSupportedVnfdFormats nfvoSpecificInfoSupportedVnfdFormats = (NfvoSpecificInfoSupportedVnfdFormats) o;
    return Objects.equals(this.vnfdFormat, nfvoSpecificInfoSupportedVnfdFormats.vnfdFormat) &&
        Objects.equals(this.standardVersion, nfvoSpecificInfoSupportedVnfdFormats.standardVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfdFormat, standardVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NfvoSpecificInfoSupportedVnfdFormats {\n");
    
    sb.append("    vnfdFormat: ").append(toIndentedString(vnfdFormat)).append("\n");
    sb.append("    standardVersion: ").append(toIndentedString(standardVersion)).append("\n");
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
