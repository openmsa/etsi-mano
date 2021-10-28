package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information to terminate a VNF that is part of an NS. NOTE: If the VNF is still in service, requesting forceful termination can adversely impact network service. 
 */
@Schema(description = "This type represents the information to terminate a VNF that is part of an NS. NOTE: If the VNF is still in service, requesting forceful termination can adversely impact network service. ")
@Validated


public class TerminateVnfData   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  /**
   * Indicates whether forceful or graceful termination is requested. See note. Permitted values: - FORCEFUL - GRACEFUL 
   */
  public enum TerminationTypeEnum {
    FORCEFUL("FORCEFUL"),
    
    GRACEFUL("GRACEFUL");

    private String value;

    TerminationTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TerminationTypeEnum fromValue(String text) {
      for (TerminationTypeEnum b : TerminationTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("terminationType")
  private TerminationTypeEnum terminationType = null;

  @JsonProperty("gracefulTerminationTimeout")
  private Integer gracefulTerminationTimeout = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public TerminateVnfData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public TerminateVnfData terminationType(TerminationTypeEnum terminationType) {
    this.terminationType = terminationType;
    return this;
  }

  /**
   * Indicates whether forceful or graceful termination is requested. See note. Permitted values: - FORCEFUL - GRACEFUL 
   * @return terminationType
   **/
  @Schema(description = "Indicates whether forceful or graceful termination is requested. See note. Permitted values: - FORCEFUL - GRACEFUL ")
  
    public TerminationTypeEnum getTerminationType() {
    return terminationType;
  }

  public void setTerminationType(TerminationTypeEnum terminationType) {
    this.terminationType = terminationType;
  }

  public TerminateVnfData gracefulTerminationTimeout(Integer gracefulTerminationTimeout) {
    this.gracefulTerminationTimeout = gracefulTerminationTimeout;
    return this;
  }

  /**
   * The attribute is only applicable in case of graceful termination. It defines the time to wait for the VNF to be taken out of service before shutting down the VNF and releasing the resources. The unit is seconds. 
   * @return gracefulTerminationTimeout
   **/
  @Schema(description = "The attribute is only applicable in case of graceful termination. It defines the time to wait for the VNF to be taken out of service before shutting down the VNF and releasing the resources. The unit is seconds. ")
  
    public Integer getGracefulTerminationTimeout() {
    return gracefulTerminationTimeout;
  }

  public void setGracefulTerminationTimeout(Integer gracefulTerminationTimeout) {
    this.gracefulTerminationTimeout = gracefulTerminationTimeout;
  }

  public TerminateVnfData additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminateVnfData terminateVnfData = (TerminateVnfData) o;
    return Objects.equals(this.vnfInstanceId, terminateVnfData.vnfInstanceId) &&
        Objects.equals(this.terminationType, terminateVnfData.terminationType) &&
        Objects.equals(this.gracefulTerminationTimeout, terminateVnfData.gracefulTerminationTimeout) &&
        Objects.equals(this.additionalParams, terminateVnfData.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, terminationType, gracefulTerminationTimeout, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerminateVnfData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    terminationType: ").append(toIndentedString(terminationType)).append("\n");
    sb.append("    gracefulTerminationTimeout: ").append(toIndentedString(gracefulTerminationTimeout)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
