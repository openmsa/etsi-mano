package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ClockSyncInfoNtpServerInfo;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents parameters for connecting to an NTP server.  
 */
@ApiModel(description = "This type represents parameters for connecting to an NTP server.  ")
@Validated
public class ClockSyncInfo   {
  @JsonProperty("id")
  private String id = null;

  /**
   * Type of clock synchronization. Permitted values:   - NTP: For Network Time Protocol (NTP) based clock synchronization.   - OTHER: For other types of clock synchronization. 
   */
  public enum TypeEnum {
    NTP("NTP"),
    
    OTHER("OTHER");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("ntpServerInfo")
  private ClockSyncInfoNtpServerInfo ntpServerInfo = null;

  @JsonProperty("otherClockSyncParams")
  private Map<String, String> otherClockSyncParams = null;

  public ClockSyncInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ClockSyncInfo type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Type of clock synchronization. Permitted values:   - NTP: For Network Time Protocol (NTP) based clock synchronization.   - OTHER: For other types of clock synchronization. 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "Type of clock synchronization. Permitted values:   - NTP: For Network Time Protocol (NTP) based clock synchronization.   - OTHER: For other types of clock synchronization. ")
      @NotNull

    public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public ClockSyncInfo ntpServerInfo(ClockSyncInfoNtpServerInfo ntpServerInfo) {
    this.ntpServerInfo = ntpServerInfo;
    return this;
  }

  /**
   * Get ntpServerInfo
   * @return ntpServerInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ClockSyncInfoNtpServerInfo getNtpServerInfo() {
    return ntpServerInfo;
  }

  public void setNtpServerInfo(ClockSyncInfoNtpServerInfo ntpServerInfo) {
    this.ntpServerInfo = ntpServerInfo;
  }

  public ClockSyncInfo otherClockSyncParams(Map<String, String> otherClockSyncParams) {
    this.otherClockSyncParams = otherClockSyncParams;
    return this;
  }

  /**
   * Get otherClockSyncParams
   * @return otherClockSyncParams
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Map<String, String> getOtherClockSyncParams() {
    return otherClockSyncParams;
  }

  public void setOtherClockSyncParams(Map<String, String> otherClockSyncParams) {
    this.otherClockSyncParams = otherClockSyncParams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClockSyncInfo clockSyncInfo = (ClockSyncInfo) o;
    return Objects.equals(this.id, clockSyncInfo.id) &&
        Objects.equals(this.type, clockSyncInfo.type) &&
        Objects.equals(this.ntpServerInfo, clockSyncInfo.ntpServerInfo) &&
        Objects.equals(this.otherClockSyncParams, clockSyncInfo.otherClockSyncParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, ntpServerInfo, otherClockSyncParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClockSyncInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    ntpServerInfo: ").append(toIndentedString(ntpServerInfo)).append("\n");
    sb.append("    otherClockSyncParams: ").append(toIndentedString(otherClockSyncParams)).append("\n");
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
