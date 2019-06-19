package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * The procedure defined in clause 4.5.2 allows an API consumer to  obtain authorization to perform API requests towards the API producer, including subscription requests.  For sending the actual notifications matching a subscription, the API producer needs to obtain separate authorization to actually send the notification to the API consumer. If an API consumer requires the API producer to authorize for sending notifications to that API consumer, it shall include in the subscription request a data structure that defines the authorization requirements, as defined in Table 4.5.3.4-1. 
 **/
@ApiModel(description="The procedure defined in clause 4.5.2 allows an API consumer to  obtain authorization to perform API requests towards the API producer, including subscription requests.  For sending the actual notifications matching a subscription, the API producer needs to obtain separate authorization to actually send the notification to the API consumer. If an API consumer requires the API producer to authorize for sending notifications to that API consumer, it shall include in the subscription request a data structure that defines the authorization requirements, as defined in Table 4.5.3.4-1. ")
public class SubscriptionsNsdmSubscriptionRequestAuthentication  {
  

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

  @ApiModelProperty(required = true, value = "Defines the types of Authentication Authorization the API consumer is willing to accept when receiving a notification. Permitted values: BASIC: In every HTTP request to the notification endpoint, use HTTP Basic authentication with the client credentials. OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the notification endpoint, use an OAuth 2.0 Bearer token, obtained using the client credentials grant type. TLS_CERT: Every HTTP request to the notification endpoint is sent over a mutually authenticated TLS session. i.e. not only server is authenticated, but also the client is authenticated during the TLS tunnel setup ")
 /**
   * Defines the types of Authentication Authorization the API consumer is willing to accept when receiving a notification. Permitted values: BASIC: In every HTTP request to the notification endpoint, use HTTP Basic authentication with the client credentials. OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the notification endpoint, use an OAuth 2.0 Bearer token, obtained using the client credentials grant type. TLS_CERT: Every HTTP request to the notification endpoint is sent over a mutually authenticated TLS session. i.e. not only server is authenticated, but also the client is authenticated during the TLS tunnel setup 
  **/
  private AuthTypeEnum authType = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsNsdmSubscriptionRequestAuthenticationParamsBasic paramsBasic = null;

  @ApiModelProperty(value = "")
  @Valid
  private SubscriptionsNsdmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;
 /**
   * Defines the types of Authentication Authorization the API consumer is willing to accept when receiving a notification. Permitted values: BASIC: In every HTTP request to the notification endpoint, use HTTP Basic authentication with the client credentials. OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the notification endpoint, use an OAuth 2.0 Bearer token, obtained using the client credentials grant type. TLS_CERT: Every HTTP request to the notification endpoint is sent over a mutually authenticated TLS session. i.e. not only server is authenticated, but also the client is authenticated during the TLS tunnel setup 
   * @return authType
  **/
  @JsonProperty("authType")
  @NotNull
  public String getAuthType() {
    if (authType == null) {
      return null;
    }
    return authType.value();
  }

  public void setAuthType(AuthTypeEnum authType) {
    this.authType = authType;
  }

  public SubscriptionsNsdmSubscriptionRequestAuthentication authType(AuthTypeEnum authType) {
    this.authType = authType;
    return this;
  }

 /**
   * Get paramsBasic
   * @return paramsBasic
  **/
  @JsonProperty("paramsBasic")
  public SubscriptionsNsdmSubscriptionRequestAuthenticationParamsBasic getParamsBasic() {
    return paramsBasic;
  }

  public void setParamsBasic(SubscriptionsNsdmSubscriptionRequestAuthenticationParamsBasic paramsBasic) {
    this.paramsBasic = paramsBasic;
  }

  public SubscriptionsNsdmSubscriptionRequestAuthentication paramsBasic(SubscriptionsNsdmSubscriptionRequestAuthenticationParamsBasic paramsBasic) {
    this.paramsBasic = paramsBasic;
    return this;
  }

 /**
   * Get paramsOauth2ClientCredentials
   * @return paramsOauth2ClientCredentials
  **/
  @JsonProperty("paramsOauth2ClientCredentials")
  public SubscriptionsNsdmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
    return paramsOauth2ClientCredentials;
  }

  public void setParamsOauth2ClientCredentials(SubscriptionsNsdmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
  }

  public SubscriptionsNsdmSubscriptionRequestAuthentication paramsOauth2ClientCredentials(SubscriptionsNsdmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsNsdmSubscriptionRequestAuthentication {\n");
    
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

