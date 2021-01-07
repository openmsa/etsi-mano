package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.SecurityInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TransportDescriptor
 */
@Validated
public class TransportDescriptor   {
  @JsonProperty("protocol")
  private String protocol = null;

  @JsonProperty("security")
  private SecurityInfo security = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("version")
  private String version = null;

  public TransportDescriptor protocol(String protocol) {
    this.protocol = protocol;
    return this;
  }

  /**
   * The name of the protocol used. Shall be set to HTTP for a REST API.
   * @return protocol
  **/
  @ApiModelProperty(required = true, value = "The name of the protocol used. Shall be set to HTTP for a REST API.")
      @NotNull

    public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public TransportDescriptor security(SecurityInfo security) {
    this.security = security;
    return this;
  }

  /**
   * Get security
   * @return security
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public SecurityInfo getSecurity() {
    return security;
  }

  public void setSecurity(SecurityInfo security) {
    this.security = security;
  }

  public TransportDescriptor type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TransportDescriptor version(String version) {
    this.version = version;
    return this;
  }

  /**
   * The version of the protocol used.
   * @return version
  **/
  @ApiModelProperty(required = true, value = "The version of the protocol used.")
      @NotNull

    public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransportDescriptor transportDescriptor = (TransportDescriptor) o;
    return Objects.equals(this.protocol, transportDescriptor.protocol) &&
        Objects.equals(this.security, transportDescriptor.security) &&
        Objects.equals(this.type, transportDescriptor.type) &&
        Objects.equals(this.version, transportDescriptor.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(protocol, security, type, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransportDescriptor {\n");
    
    sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
    sb.append("    security: ").append(toIndentedString(security)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
