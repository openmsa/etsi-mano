package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.SubscriptionAuthenticationParamsBasic;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.SubscriptionAuthenticationParamsOauth2ClientCredentials;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SubscriptionAuthentication
 */
@Validated


public class SubscriptionAuthentication   {
  /**
   * Gets or Sets authType
   */
  public enum AuthTypeEnum {
    BASIC("BASIC"),
    
    OAUTH2_CLIENT_CREDENTIALS("OAUTH2_CLIENT_CREDENTIALS"),
    
    TLS_CERT("TLS_CERT");

    private String value;

    AuthTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AuthTypeEnum fromValue(String text) {
      for (AuthTypeEnum b : AuthTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("authType")
  @Valid
  private List<AuthTypeEnum> authType = new ArrayList<>();

  @JsonProperty("paramsBasic")
  private SubscriptionAuthenticationParamsBasic paramsBasic = null;

  @JsonProperty("paramsOauth2ClientCredentials")
  private SubscriptionAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;

  public SubscriptionAuthentication authType(List<AuthTypeEnum> authType) {
    this.authType = authType;
    return this;
  }

  public SubscriptionAuthentication addAuthTypeItem(AuthTypeEnum authTypeItem) {
    this.authType.add(authTypeItem);
    return this;
  }

  /**
   * Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials. - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. 
   * @return authType
   **/
  @Schema(required = true, description = "Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials. - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. ")
      @NotNull

    public List<AuthTypeEnum> getAuthType() {
    return authType;
  }

  public void setAuthType(List<AuthTypeEnum> authType) {
    this.authType = authType;
  }

  public SubscriptionAuthentication paramsBasic(SubscriptionAuthenticationParamsBasic paramsBasic) {
    this.paramsBasic = paramsBasic;
    return this;
  }

  /**
   * Get paramsBasic
   * @return paramsBasic
   **/
  @Schema(description = "")
  
    @Valid
    public SubscriptionAuthenticationParamsBasic getParamsBasic() {
    return paramsBasic;
  }

  public void setParamsBasic(SubscriptionAuthenticationParamsBasic paramsBasic) {
    this.paramsBasic = paramsBasic;
  }

  public SubscriptionAuthentication paramsOauth2ClientCredentials(SubscriptionAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
    return this;
  }

  /**
   * Get paramsOauth2ClientCredentials
   * @return paramsOauth2ClientCredentials
   **/
  @Schema(description = "")
  
    @Valid
    public SubscriptionAuthenticationParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
    return paramsOauth2ClientCredentials;
  }

  public void setParamsOauth2ClientCredentials(SubscriptionAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionAuthentication subscriptionAuthentication = (SubscriptionAuthentication) o;
    return Objects.equals(this.authType, subscriptionAuthentication.authType) &&
        Objects.equals(this.paramsBasic, subscriptionAuthentication.paramsBasic) &&
        Objects.equals(this.paramsOauth2ClientCredentials, subscriptionAuthentication.paramsOauth2ClientCredentials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authType, paramsBasic, paramsOauth2ClientCredentials);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionAuthentication {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
