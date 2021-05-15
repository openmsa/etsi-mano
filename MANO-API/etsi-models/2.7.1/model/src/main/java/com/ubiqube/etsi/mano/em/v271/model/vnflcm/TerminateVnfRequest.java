package com.ubiqube.etsi.mano.em.v271.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.KeyValuePairs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TerminateVnfRequest
 */
@Validated

public class TerminateVnfRequest   {
  /**
   * Indicates the type of termination is requested. Permitted values: * FORCEFUL: The VNFM will shut down the VNF and release the   resources immediately after accepting the request. 
   */
  public enum TerminationTypeEnum {
    FORCEFUL("FORCEFUL");

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

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public TerminateVnfRequest terminationType(TerminationTypeEnum terminationType) {
    this.terminationType = terminationType;
    return this;
  }

  /**
   * Indicates the type of termination is requested. Permitted values: * FORCEFUL: The VNFM will shut down the VNF and release the   resources immediately after accepting the request. 
   * @return terminationType
  **/
  @ApiModelProperty(required = true, value = "Indicates the type of termination is requested. Permitted values: * FORCEFUL: The VNFM will shut down the VNF and release the   resources immediately after accepting the request. ")
  @NotNull


  public TerminationTypeEnum getTerminationType() {
    return terminationType;
  }

  public void setTerminationType(TerminationTypeEnum terminationType) {
    this.terminationType = terminationType;
  }

  public TerminateVnfRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Additional parameters passed by the NFVO as input to the termination process, specific to the VNF being terminated, as declared in the VNFD as part of \"TerminateVnfOpConfig\". 
   * @return additionalParams
  **/
  @ApiModelProperty(value = "Additional parameters passed by the NFVO as input to the termination process, specific to the VNF being terminated, as declared in the VNFD as part of \"TerminateVnfOpConfig\". ")

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
    TerminateVnfRequest terminateVnfRequest = (TerminateVnfRequest) o;
    return Objects.equals(this.terminationType, terminateVnfRequest.terminationType) &&
        Objects.equals(this.additionalParams, terminateVnfRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(terminationType, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerminateVnfRequest {\n");
    
    sb.append("    terminationType: ").append(toIndentedString(terminationType)).append("\n");
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

