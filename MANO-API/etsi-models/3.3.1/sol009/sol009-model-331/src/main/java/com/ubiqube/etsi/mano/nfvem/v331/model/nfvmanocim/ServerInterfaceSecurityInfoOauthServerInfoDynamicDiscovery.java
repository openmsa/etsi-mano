package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Configuration data used when performing dynamic discovery of  the authorization server identifier.  NOTE: Provided configuration of the OAuth 2.0 authorization  server information and configuration shall be supported, and  dynamic configuration may be supported. 
 */
@ApiModel(description = "Configuration data used when performing dynamic discovery of  the authorization server identifier.  NOTE: Provided configuration of the OAuth 2.0 authorization  server information and configuration shall be supported, and  dynamic configuration may be supported. ")
@Validated
public class ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery   {
  @JsonProperty("webFingerHost")
  private String webFingerHost = null;

  public ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery webFingerHost(String webFingerHost) {
    this.webFingerHost = webFingerHost;
    return this;
  }

  /**
   * Get webFingerHost
   * @return webFingerHost
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getWebFingerHost() {
    return webFingerHost;
  }

  public void setWebFingerHost(String webFingerHost) {
    this.webFingerHost = webFingerHost;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery serverInterfaceSecurityInfoOauthServerInfoDynamicDiscovery = (ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery) o;
    return Objects.equals(this.webFingerHost, serverInterfaceSecurityInfoOauthServerInfoDynamicDiscovery.webFingerHost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(webFingerHost);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServerInterfaceSecurityInfoOauthServerInfoDynamicDiscovery {\n");
    
    sb.append("    webFingerHost: ").append(toIndentedString(webFingerHost)).append("\n");
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
