package com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Parameters for authentication/authorization using OAuth 2.0. 
 */
@Schema(description = "Parameters for authentication/authorization using OAuth 2.0. ")
@Validated


public class UploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials   {
  @JsonProperty("clientId")
  private String clientId = null;

  @JsonProperty("clientPassword")
  private String clientPassword = null;

  @JsonProperty("tokenEndpoint")
  private String tokenEndpoint = null;

  public UploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  /**
   * Client identifier to be used in the access token request of the OAuth 2.0 client  credentials grant type. 
   * @return clientId
   **/
  @Schema(required = true, description = "Client identifier to be used in the access token request of the OAuth 2.0 client  credentials grant type. ")
      @NotNull

    public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public UploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials clientPassword(String clientPassword) {
    this.clientPassword = clientPassword;
    return this;
  }

  /**
   * Client password to be used in the access token request of the OAuth 2.0 client  credentials grant type. 
   * @return clientPassword
   **/
  @Schema(required = true, description = "Client password to be used in the access token request of the OAuth 2.0 client  credentials grant type. ")
      @NotNull

    public String getClientPassword() {
    return clientPassword;
  }

  public void setClientPassword(String clientPassword) {
    this.clientPassword = clientPassword;
  }

  public UploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials tokenEndpoint(String tokenEndpoint) {
    this.tokenEndpoint = tokenEndpoint;
    return this;
  }

  /**
   * The token endpoint from which the access token can be obtained. 
   * @return tokenEndpoint
   **/
  @Schema(required = true, description = "The token endpoint from which the access token can be obtained. ")
      @NotNull

    public String getTokenEndpoint() {
    return tokenEndpoint;
  }

  public void setTokenEndpoint(String tokenEndpoint) {
    this.tokenEndpoint = tokenEndpoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials uploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials = (UploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials) o;
    return Objects.equals(this.clientId, uploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials.clientId) &&
        Objects.equals(this.clientPassword, uploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials.clientPassword) &&
        Objects.equals(this.tokenEndpoint, uploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials.tokenEndpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, clientPassword, tokenEndpoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UploadVnfSnapshotPkgFromUriRequestParamsOauth2ClientCredentials {\n");
    
    sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
    sb.append("    clientPassword: ").append(toIndentedString(clientPassword)).append("\n");
    sb.append("    tokenEndpoint: ").append(toIndentedString(tokenEndpoint)).append("\n");
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
