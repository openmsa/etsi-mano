package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information and configuration related to the use of TLS tunnel.  Shall be present if authType contains \&quot;TLS_TUNNEL\&quot;. 
 */
@ApiModel(description = "Information and configuration related to the use of TLS tunnel.  Shall be present if authType contains \"TLS_TUNNEL\". ")
@Validated
public class ServerInterfaceSecurityInfoTlsTunnelInfo   {
  @JsonProperty("tlsTunnelCipherSuites")
  @Valid
  private List<String> tlsTunnelCipherSuites = new ArrayList<>();

  public ServerInterfaceSecurityInfoTlsTunnelInfo tlsTunnelCipherSuites(List<String> tlsTunnelCipherSuites) {
    this.tlsTunnelCipherSuites = tlsTunnelCipherSuites;
    return this;
  }

  public ServerInterfaceSecurityInfoTlsTunnelInfo addTlsTunnelCipherSuitesItem(String tlsTunnelCipherSuitesItem) {
    this.tlsTunnelCipherSuites.add(tlsTunnelCipherSuitesItem);
    return this;
  }

  /**
   * List of cipher suites that shall be declared as supported by the API producer when performing the SSL or TLS negotiation with the API client. Valid values of cipher suites are defined in IETF RFC 8447.
   * @return tlsTunnelCipherSuites
  **/
  @ApiModelProperty(required = true, value = "List of cipher suites that shall be declared as supported by the API producer when performing the SSL or TLS negotiation with the API client. Valid values of cipher suites are defined in IETF RFC 8447.")
      @NotNull

  @Size(min=1)   public List<String> getTlsTunnelCipherSuites() {
    return tlsTunnelCipherSuites;
  }

  public void setTlsTunnelCipherSuites(List<String> tlsTunnelCipherSuites) {
    this.tlsTunnelCipherSuites = tlsTunnelCipherSuites;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServerInterfaceSecurityInfoTlsTunnelInfo serverInterfaceSecurityInfoTlsTunnelInfo = (ServerInterfaceSecurityInfoTlsTunnelInfo) o;
    return Objects.equals(this.tlsTunnelCipherSuites, serverInterfaceSecurityInfoTlsTunnelInfo.tlsTunnelCipherSuites);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tlsTunnelCipherSuites);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServerInterfaceSecurityInfoTlsTunnelInfo {\n");
    
    sb.append("    tlsTunnelCipherSuites: ").append(toIndentedString(tlsTunnelCipherSuites)).append("\n");
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
