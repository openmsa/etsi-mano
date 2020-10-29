/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsPmSubscriptionRequestAuthentication  {
  

@XmlType(name="AuthTypeEnum")
@XmlEnum(String.class)
public enum AuthTypeEnum {

@XmlEnumValue("BASIC") BASIC(String.valueOf("BASIC")), @XmlEnumValue("OAUTH2_CLIENT_CREDENTIALS") OAUTH2_CLIENT_CREDENTIALS(String.valueOf("OAUTH2_CLIENT_CREDENTIALS")), @XmlEnumValue("TLS_CERT") TLS_CERT(String.valueOf("TLS_CERT"));


    private String value;

    AuthTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static AuthTypeEnum fromValue(String v) {
        for (AuthTypeEnum b : AuthTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials.  - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. ")
 /**
   * Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials.  - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. 
  **/
  private List<AuthTypeEnum> authType = new ArrayList<AuthTypeEnum>();

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic paramsBasic = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsPmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;
 /**
   * Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials.  - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. 
   * @return authType
  **/
  @JsonProperty("authType")
  @NotNull
  public List<AuthTypeEnum> getAuthType() {
    return authType;
  }

  public void setAuthType(List<AuthTypeEnum> authType) {
    this.authType = authType;
  }

  public SubscriptionsPmSubscriptionRequestAuthentication authType(List<AuthTypeEnum> authType) {
    this.authType = authType;
    return this;
  }

  public SubscriptionsPmSubscriptionRequestAuthentication addAuthTypeItem(AuthTypeEnum authTypeItem) {
    this.authType.add(authTypeItem);
    return this;
  }

 /**
   * Get paramsBasic
   * @return paramsBasic
  **/
  @JsonProperty("paramsBasic")
  public SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic getParamsBasic() {
    return paramsBasic;
  }

  public void setParamsBasic(SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic paramsBasic) {
    this.paramsBasic = paramsBasic;
  }

  public SubscriptionsPmSubscriptionRequestAuthentication paramsBasic(SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic paramsBasic) {
    this.paramsBasic = paramsBasic;
    return this;
  }

 /**
   * Get paramsOauth2ClientCredentials
   * @return paramsOauth2ClientCredentials
  **/
  @JsonProperty("paramsOauth2ClientCredentials")
  public SubscriptionsPmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
    return paramsOauth2ClientCredentials;
  }

  public void setParamsOauth2ClientCredentials(SubscriptionsPmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
  }

  public SubscriptionsPmSubscriptionRequestAuthentication paramsOauth2ClientCredentials(SubscriptionsPmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPmSubscriptionRequestAuthentication {\n");
    
    sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
    sb.append("    paramsBasic: ").append(toIndentedString(paramsBasic)).append("\n");
    sb.append("    paramsOauth2ClientCredentials: ").append(toIndentedString(paramsOauth2ClientCredentials)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

