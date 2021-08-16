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
 * Configuration data used to setup the authorization server identifier.  NOTE: Provided configuration of the OAuth 2.0 authorization server information  and configuration shall be supported, and dynamic configuration may be supported. 
 */
@ApiModel(description = "Configuration data used to setup the authorization server identifier.  NOTE: Provided configuration of the OAuth 2.0 authorization server information  and configuration shall be supported, and dynamic configuration may be supported. ")
@Validated
public class ClientInterfaceSecurityInfoOauthServerInfoProvidedConfiguration   {
  @JsonProperty("authServerId")
  private String authServerId = null;

  public ClientInterfaceSecurityInfoOauthServerInfoProvidedConfiguration authServerId(String authServerId) {
    this.authServerId = authServerId;
    return this;
  }

  /**
   * Authorization server identifier as defined in ETSI GS NFV-SEC 022. 
   * @return authServerId
  **/
  @ApiModelProperty(required = true, value = "Authorization server identifier as defined in ETSI GS NFV-SEC 022. ")
      @NotNull

    public String getAuthServerId() {
    return authServerId;
  }

  public void setAuthServerId(String authServerId) {
    this.authServerId = authServerId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientInterfaceSecurityInfoOauthServerInfoProvidedConfiguration clientInterfaceSecurityInfoOauthServerInfoProvidedConfiguration = (ClientInterfaceSecurityInfoOauthServerInfoProvidedConfiguration) o;
    return Objects.equals(this.authServerId, clientInterfaceSecurityInfoOauthServerInfoProvidedConfiguration.authServerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authServerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientInterfaceSecurityInfoOauthServerInfoProvidedConfiguration {\n");
    
    sb.append("    authServerId: ").append(toIndentedString(authServerId)).append("\n");
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
