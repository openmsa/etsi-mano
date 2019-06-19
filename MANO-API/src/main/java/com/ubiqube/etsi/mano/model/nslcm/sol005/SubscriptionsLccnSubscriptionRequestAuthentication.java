package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionsLccnSubscriptionRequestAuthentication  {
  

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

  @ApiModelProperty(required = true, value = "Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: * BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials.  * OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. * TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. ")
 /**
   * Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: * BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials.  * OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. * TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. 
  **/
  private List<AuthTypeEnum> authType = new ArrayList<AuthTypeEnum>();

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsLccnSubscriptionRequestAuthenticationParamsBasic paramsBasic = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsLccnSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;
 /**
   * Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: * BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials.  * OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. * TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. 
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

  public SubscriptionsLccnSubscriptionRequestAuthentication authType(List<AuthTypeEnum> authType) {
    this.authType = authType;
    return this;
  }

  public SubscriptionsLccnSubscriptionRequestAuthentication addAuthTypeItem(AuthTypeEnum authTypeItem) {
    this.authType.add(authTypeItem);
    return this;
  }

 /**
   * Get paramsBasic
   * @return paramsBasic
  **/
  @JsonProperty("paramsBasic")
  public SubscriptionsLccnSubscriptionRequestAuthenticationParamsBasic getParamsBasic() {
    return paramsBasic;
  }

  public void setParamsBasic(SubscriptionsLccnSubscriptionRequestAuthenticationParamsBasic paramsBasic) {
    this.paramsBasic = paramsBasic;
  }

  public SubscriptionsLccnSubscriptionRequestAuthentication paramsBasic(SubscriptionsLccnSubscriptionRequestAuthenticationParamsBasic paramsBasic) {
    this.paramsBasic = paramsBasic;
    return this;
  }

 /**
   * Get paramsOauth2ClientCredentials
   * @return paramsOauth2ClientCredentials
  **/
  @JsonProperty("paramsOauth2ClientCredentials")
  public SubscriptionsLccnSubscriptionRequestAuthenticationParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
    return paramsOauth2ClientCredentials;
  }

  public void setParamsOauth2ClientCredentials(SubscriptionsLccnSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
  }

  public SubscriptionsLccnSubscriptionRequestAuthentication paramsOauth2ClientCredentials(SubscriptionsLccnSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsLccnSubscriptionRequestAuthentication {\n");
    
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

