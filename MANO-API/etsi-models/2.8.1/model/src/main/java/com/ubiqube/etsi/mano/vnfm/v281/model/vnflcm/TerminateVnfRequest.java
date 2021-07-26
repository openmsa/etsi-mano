/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm.KeyValuePairs;
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
   * Indicates whether forceful or graceful termination is requested. Permitted values: * FORCEFUL: The VNFM will shut down the VNF and release the   resources immediately after accepting the request. * GRACEFUL: The VNFM will first arrange to take the VNF out of service after accepting the request. Once the operation of taking the VNF out of service finishes (irrespective of whether it has succeeded or failed) or once the timer value specified in the \"gracefulTerminationTimeout\" attribute expires, the VNFM will shut down the VNF and release the resources. 
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

  public TerminateVnfRequest terminationType(TerminationTypeEnum terminationType) {
    this.terminationType = terminationType;
    return this;
  }

  /**
   * Indicates whether forceful or graceful termination is requested. Permitted values: * FORCEFUL: The VNFM will shut down the VNF and release the   resources immediately after accepting the request. * GRACEFUL: The VNFM will first arrange to take the VNF out of service after accepting the request. Once the operation of taking the VNF out of service finishes (irrespective of whether it has succeeded or failed) or once the timer value specified in the \"gracefulTerminationTimeout\" attribute expires, the VNFM will shut down the VNF and release the resources. 
   * @return terminationType
  **/
  @ApiModelProperty(required = true, value = "Indicates whether forceful or graceful termination is requested. Permitted values: * FORCEFUL: The VNFM will shut down the VNF and release the   resources immediately after accepting the request. * GRACEFUL: The VNFM will first arrange to take the VNF out of service after accepting the request. Once the operation of taking the VNF out of service finishes (irrespective of whether it has succeeded or failed) or once the timer value specified in the \"gracefulTerminationTimeout\" attribute expires, the VNFM will shut down the VNF and release the resources. ")
  @NotNull


  public TerminationTypeEnum getTerminationType() {
    return terminationType;
  }

  public void setTerminationType(TerminationTypeEnum terminationType) {
    this.terminationType = terminationType;
  }

  public TerminateVnfRequest gracefulTerminationTimeout(Integer gracefulTerminationTimeout) {
    this.gracefulTerminationTimeout = gracefulTerminationTimeout;
    return this;
  }

  /**
   * This attribute is only applicable in case of graceful termination. It defines the time to wait for the VNF to be taken out of service before shutting down the VNF and releasing the resources. The unit is seconds. If not given and the \"terminationType\" attribute is set to \"GRACEFUL\", it is expected that the VNFM waits for the successful taking out of service of the VNF, no matter how long it takes, before shutting down the VNF and releasing the resources. 
   * @return gracefulTerminationTimeout
  **/
  @ApiModelProperty(value = "This attribute is only applicable in case of graceful termination. It defines the time to wait for the VNF to be taken out of service before shutting down the VNF and releasing the resources. The unit is seconds. If not given and the \"terminationType\" attribute is set to \"GRACEFUL\", it is expected that the VNFM waits for the successful taking out of service of the VNF, no matter how long it takes, before shutting down the VNF and releasing the resources. ")


  public Integer getGracefulTerminationTimeout() {
    return gracefulTerminationTimeout;
  }

  public void setGracefulTerminationTimeout(Integer gracefulTerminationTimeout) {
    this.gracefulTerminationTimeout = gracefulTerminationTimeout;
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
        Objects.equals(this.gracefulTerminationTimeout, terminateVnfRequest.gracefulTerminationTimeout) &&
        Objects.equals(this.additionalParams, terminateVnfRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(terminationType, gracefulTerminationTimeout, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerminateVnfRequest {\n");
    
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

