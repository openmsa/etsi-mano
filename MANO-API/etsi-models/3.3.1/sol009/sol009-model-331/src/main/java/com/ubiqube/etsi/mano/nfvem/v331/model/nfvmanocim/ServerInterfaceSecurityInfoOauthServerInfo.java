package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ServerInterfaceSecurityInfoOauthServerInfoProvidedConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OAuth 2.0 authorization server information and configuration. 
 */
@ApiModel(description = "OAuth 2.0 authorization server information and configuration. ")
@Validated
public class ServerInterfaceSecurityInfoOauthServerInfo   {
  @JsonProperty("dynamicDiscovery")
  private ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery dynamicDiscovery = null;

  @JsonProperty("providedConfiguration")
  private ServerInterfaceSecurityInfoOauthServerInfoProvidedConfiguration providedConfiguration = null;

  @JsonProperty("tlsCipherSuites")
  @Valid
  private List<String> tlsCipherSuites = new ArrayList<>();

  public ServerInterfaceSecurityInfoOauthServerInfo dynamicDiscovery(ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery dynamicDiscovery) {
    this.dynamicDiscovery = dynamicDiscovery;
    return this;
  }

  /**
   * Get dynamicDiscovery
   * @return dynamicDiscovery
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery getDynamicDiscovery() {
    return dynamicDiscovery;
  }

  public void setDynamicDiscovery(ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery dynamicDiscovery) {
    this.dynamicDiscovery = dynamicDiscovery;
  }

  public ServerInterfaceSecurityInfoOauthServerInfo providedConfiguration(ServerInterfaceSecurityInfoOauthServerInfoProvidedConfiguration providedConfiguration) {
    this.providedConfiguration = providedConfiguration;
    return this;
  }

  /**
   * Get providedConfiguration
   * @return providedConfiguration
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ServerInterfaceSecurityInfoOauthServerInfoProvidedConfiguration getProvidedConfiguration() {
    return providedConfiguration;
  }

  public void setProvidedConfiguration(ServerInterfaceSecurityInfoOauthServerInfoProvidedConfiguration providedConfiguration) {
    this.providedConfiguration = providedConfiguration;
  }

  public ServerInterfaceSecurityInfoOauthServerInfo tlsCipherSuites(List<String> tlsCipherSuites) {
    this.tlsCipherSuites = tlsCipherSuites;
    return this;
  }

  public ServerInterfaceSecurityInfoOauthServerInfo addTlsCipherSuitesItem(String tlsCipherSuitesItem) {
    this.tlsCipherSuites.add(tlsCipherSuitesItem);
    return this;
  }

  /**
   * List of cipher suites that shall be declared as supported by the  API producer when performing the SSL or TLS negotiation with the  authorization server. Valid values of cipher suites are defined  in IETF RFC 8447. 
   * @return tlsCipherSuites
  **/
  @ApiModelProperty(required = true, value = "List of cipher suites that shall be declared as supported by the  API producer when performing the SSL or TLS negotiation with the  authorization server. Valid values of cipher suites are defined  in IETF RFC 8447. ")
      @NotNull

  @Size(min=1)   public List<String> getTlsCipherSuites() {
    return tlsCipherSuites;
  }

  public void setTlsCipherSuites(List<String> tlsCipherSuites) {
    this.tlsCipherSuites = tlsCipherSuites;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServerInterfaceSecurityInfoOauthServerInfo serverInterfaceSecurityInfoOauthServerInfo = (ServerInterfaceSecurityInfoOauthServerInfo) o;
    return Objects.equals(this.dynamicDiscovery, serverInterfaceSecurityInfoOauthServerInfo.dynamicDiscovery) &&
        Objects.equals(this.providedConfiguration, serverInterfaceSecurityInfoOauthServerInfo.providedConfiguration) &&
        Objects.equals(this.tlsCipherSuites, serverInterfaceSecurityInfoOauthServerInfo.tlsCipherSuites);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dynamicDiscovery, providedConfiguration, tlsCipherSuites);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServerInterfaceSecurityInfoOauthServerInfo {\n");
    
    sb.append("    dynamicDiscovery: ").append(toIndentedString(dynamicDiscovery)).append("\n");
    sb.append("    providedConfiguration: ").append(toIndentedString(providedConfiguration)).append("\n");
    sb.append("    tlsCipherSuites: ").append(toIndentedString(tlsCipherSuites)).append("\n");
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
