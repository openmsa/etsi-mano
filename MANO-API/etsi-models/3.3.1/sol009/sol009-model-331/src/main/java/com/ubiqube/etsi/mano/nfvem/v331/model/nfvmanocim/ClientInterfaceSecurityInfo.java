package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ClientInterfaceSecurityInfoOauthServerInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ClientInterfaceSecurityInfoTlsTunnelInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents security related information for accessing an NFV-MANO  service interface produced by an NFV-MANO functional entity. 
 */
@ApiModel(description = "This type represents security related information for accessing an NFV-MANO  service interface produced by an NFV-MANO functional entity. ")
@Validated
public class ClientInterfaceSecurityInfo   {
  /**
   * Gets or Sets authType
   */
  public enum AuthTypeEnum {
    TLS_TUNNEL("TLS_TUNNEL"),
    
    OAUTH2("OAUTH2");

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

  @JsonProperty("oauthServerInfo")
  private ClientInterfaceSecurityInfoOauthServerInfo oauthServerInfo = null;

  @JsonProperty("tlsTunnelInfo")
  private ClientInterfaceSecurityInfoTlsTunnelInfo tlsTunnelInfo = null;

  public ClientInterfaceSecurityInfo authType(List<AuthTypeEnum> authType) {
    this.authType = authType;
    return this;
  }

  public ClientInterfaceSecurityInfo addAuthTypeItem(AuthTypeEnum authTypeItem) {
    this.authType.add(authTypeItem);
    return this;
  }

  /**
   * Type of API request authorization to be used by the API consumer accessing  the API. The support of authorization methods for the API consumer is specified in  clause 8.3.6 of ETSI GS NFV-SOL 013. Permitted values: - TLS_TUNNEL: Using TLS tunnel, as defined by TLS 1.2 in IETF RFC 5246. - OAUTH2: Using access token, as defined by the OAuth 2.0 specification  in IETF RFC 6749.  
   * @return authType
  **/
  @ApiModelProperty(required = true, value = "Type of API request authorization to be used by the API consumer accessing  the API. The support of authorization methods for the API consumer is specified in  clause 8.3.6 of ETSI GS NFV-SOL 013. Permitted values: - TLS_TUNNEL: Using TLS tunnel, as defined by TLS 1.2 in IETF RFC 5246. - OAUTH2: Using access token, as defined by the OAuth 2.0 specification  in IETF RFC 6749.  ")
      @NotNull

  @Size(min=1)   public List<AuthTypeEnum> getAuthType() {
    return authType;
  }

  public void setAuthType(List<AuthTypeEnum> authType) {
    this.authType = authType;
  }

  public ClientInterfaceSecurityInfo oauthServerInfo(ClientInterfaceSecurityInfoOauthServerInfo oauthServerInfo) {
    this.oauthServerInfo = oauthServerInfo;
    return this;
  }

  /**
   * Get oauthServerInfo
   * @return oauthServerInfo
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ClientInterfaceSecurityInfoOauthServerInfo getOauthServerInfo() {
    return oauthServerInfo;
  }

  public void setOauthServerInfo(ClientInterfaceSecurityInfoOauthServerInfo oauthServerInfo) {
    this.oauthServerInfo = oauthServerInfo;
  }

  public ClientInterfaceSecurityInfo tlsTunnelInfo(ClientInterfaceSecurityInfoTlsTunnelInfo tlsTunnelInfo) {
    this.tlsTunnelInfo = tlsTunnelInfo;
    return this;
  }

  /**
   * Get tlsTunnelInfo
   * @return tlsTunnelInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ClientInterfaceSecurityInfoTlsTunnelInfo getTlsTunnelInfo() {
    return tlsTunnelInfo;
  }

  public void setTlsTunnelInfo(ClientInterfaceSecurityInfoTlsTunnelInfo tlsTunnelInfo) {
    this.tlsTunnelInfo = tlsTunnelInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientInterfaceSecurityInfo clientInterfaceSecurityInfo = (ClientInterfaceSecurityInfo) o;
    return Objects.equals(this.authType, clientInterfaceSecurityInfo.authType) &&
        Objects.equals(this.oauthServerInfo, clientInterfaceSecurityInfo.oauthServerInfo) &&
        Objects.equals(this.tlsTunnelInfo, clientInterfaceSecurityInfo.tlsTunnelInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authType, oauthServerInfo, tlsTunnelInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClientInterfaceSecurityInfo {\n");
    
    sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
    sb.append("    oauthServerInfo: ").append(toIndentedString(oauthServerInfo)).append("\n");
    sb.append("    tlsTunnelInfo: ").append(toIndentedString(tlsTunnelInfo)).append("\n");
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
