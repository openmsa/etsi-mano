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
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.Serializers;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.TransportDescriptor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;Indicates transports and serialization formats supported made available to the service-consuming application. Defaults to REST + JSON if absent.&#x27;
 */
@ApiModel(description = "'Indicates transports and serialization formats supported made available to the service-consuming application. Defaults to REST + JSON if absent.'")
@Validated
public class TransportsSupported   {
  @JsonProperty("transport")
  private TransportDescriptor transport = null;

  @JsonProperty("serializers")
  private Serializers serializers = null;

  public TransportsSupported transport(TransportDescriptor transport) {
    this.transport = transport;
    return this;
  }

  /**
   * Get transport
   * @return transport
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public TransportDescriptor getTransport() {
    return transport;
  }

  public void setTransport(TransportDescriptor transport) {
    this.transport = transport;
  }

  public TransportsSupported serializers(Serializers serializers) {
    this.serializers = serializers;
    return this;
  }

  /**
   * Get serializers
   * @return serializers
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Serializers getSerializers() {
    return serializers;
  }

  public void setSerializers(Serializers serializers) {
    this.serializers = serializers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransportsSupported transportsSupported = (TransportsSupported) o;
    return Objects.equals(this.transport, transportsSupported.transport) &&
        Objects.equals(this.serializers, transportsSupported.serializers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transport, serializers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransportsSupported {\n");
    
    sb.append("    transport: ").append(toIndentedString(transport)).append("\n");
    sb.append("    serializers: ").append(toIndentedString(serializers)).append("\n");
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
