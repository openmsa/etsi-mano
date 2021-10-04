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
 * Supported NSD data formats. 
 */
@ApiModel(description = "Supported NSD data formats. ")
@Validated
public class NfvoSpecificInfoSupportedNsdFormats   {
  /**
   * Name of the NSD format. Permitted values:   - TOSCA: The VNFD follows TOSCA definition, according to ETSI    GS NFV-SOL 001 standard.   - YANG: The VNFD follows YANG definition according to ETSI    GS NFV-SOL 006 standard. 
   */
  public enum NsdFormatEnum {
    TOSCA("TOSCA"),
    
    YANG("YANG");

    private String value;

    NsdFormatEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NsdFormatEnum fromValue(String text) {
      for (NsdFormatEnum b : NsdFormatEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("nsdFormat")
  private NsdFormatEnum nsdFormat = null;

  @JsonProperty("standardVersion")
  private String standardVersion = null;

  public NfvoSpecificInfoSupportedNsdFormats nsdFormat(NsdFormatEnum nsdFormat) {
    this.nsdFormat = nsdFormat;
    return this;
  }

  /**
   * Name of the NSD format. Permitted values:   - TOSCA: The VNFD follows TOSCA definition, according to ETSI    GS NFV-SOL 001 standard.   - YANG: The VNFD follows YANG definition according to ETSI    GS NFV-SOL 006 standard. 
   * @return nsdFormat
  **/
  @ApiModelProperty(required = true, value = "Name of the NSD format. Permitted values:   - TOSCA: The VNFD follows TOSCA definition, according to ETSI    GS NFV-SOL 001 standard.   - YANG: The VNFD follows YANG definition according to ETSI    GS NFV-SOL 006 standard. ")
      @NotNull

    public NsdFormatEnum getNsdFormat() {
    return nsdFormat;
  }

  public void setNsdFormat(NsdFormatEnum nsdFormat) {
    this.nsdFormat = nsdFormat;
  }

  public NfvoSpecificInfoSupportedNsdFormats standardVersion(String standardVersion) {
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
    NfvoSpecificInfoSupportedNsdFormats nfvoSpecificInfoSupportedNsdFormats = (NfvoSpecificInfoSupportedNsdFormats) o;
    return Objects.equals(this.nsdFormat, nfvoSpecificInfoSupportedNsdFormats.nsdFormat) &&
        Objects.equals(this.standardVersion, nfvoSpecificInfoSupportedNsdFormats.standardVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsdFormat, standardVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NfvoSpecificInfoSupportedNsdFormats {\n");
    
    sb.append("    nsdFormat: ").append(toIndentedString(nsdFormat)).append("\n");
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
