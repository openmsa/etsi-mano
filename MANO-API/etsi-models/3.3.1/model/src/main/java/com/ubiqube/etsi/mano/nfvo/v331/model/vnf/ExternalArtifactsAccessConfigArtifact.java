/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnf.ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Access configuration information for an external artifact. 
 */
@Schema(description = "Access configuration information for an external artifact. ")
@Validated


public class ExternalArtifactsAccessConfigArtifact   {
  @JsonProperty("artifactUri")
  private String artifactUri = null;

  @JsonProperty("overrideUri")
  private String overrideUri = null;

  /**
   * Defines the type of authentication / authorization for downloading the VNF package. Permitted values: - BASIC: Only the \"username\" and \"password\" attributes shall be present. - OAUTH2_CLIENT_CREDENTIAL S: Only the \"paramsOauth2ClientCredentials\" attribute shall be present. This attribute shall not be present if no credentials are provided for the artifact. 
   */
  public enum AuthTypeEnum {
    BASIC("BASIC"),
    
    OAUTH2_CLIENT_CREDENTIALS("OAUTH2_CLIENT_CREDENTIALS");

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
  private AuthTypeEnum authType = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("paramsOauth2ClientCredentials")
  private ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;

  public ExternalArtifactsAccessConfigArtifact artifactUri(String artifactUri) {
    this.artifactUri = artifactUri;
    return this;
  }

  /**
   * Get artifactUri
   * @return artifactUri
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getArtifactUri() {
    return artifactUri;
  }

  public void setArtifactUri(String artifactUri) {
    this.artifactUri = artifactUri;
  }

  public ExternalArtifactsAccessConfigArtifact overrideUri(String overrideUri) {
    this.overrideUri = overrideUri;
    return this;
  }

  /**
   * Get overrideUri
   * @return overrideUri
   **/
  @Schema(description = "")
  
    public String getOverrideUri() {
    return overrideUri;
  }

  public void setOverrideUri(String overrideUri) {
    this.overrideUri = overrideUri;
  }

  public ExternalArtifactsAccessConfigArtifact authType(AuthTypeEnum authType) {
    this.authType = authType;
    return this;
  }

  /**
   * Defines the type of authentication / authorization for downloading the VNF package. Permitted values: - BASIC: Only the \"username\" and \"password\" attributes shall be present. - OAUTH2_CLIENT_CREDENTIAL S: Only the \"paramsOauth2ClientCredentials\" attribute shall be present. This attribute shall not be present if no credentials are provided for the artifact. 
   * @return authType
   **/
  @Schema(description = "Defines the type of authentication / authorization for downloading the VNF package. Permitted values: - BASIC: Only the \"username\" and \"password\" attributes shall be present. - OAUTH2_CLIENT_CREDENTIAL S: Only the \"paramsOauth2ClientCredentials\" attribute shall be present. This attribute shall not be present if no credentials are provided for the artifact. ")
  
    public AuthTypeEnum getAuthType() {
    return authType;
  }

  public void setAuthType(AuthTypeEnum authType) {
    this.authType = authType;
  }

  public ExternalArtifactsAccessConfigArtifact username(String username) {
    this.username = username;
    return this;
  }

  /**
   * User name to be used for authentication. 
   * @return username
   **/
  @Schema(description = "User name to be used for authentication. ")
  
    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public ExternalArtifactsAccessConfigArtifact password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Password to be used for authentication. Shall not be present in response bodies. 
   * @return password
   **/
  @Schema(description = "Password to be used for authentication. Shall not be present in response bodies. ")
  
    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ExternalArtifactsAccessConfigArtifact paramsOauth2ClientCredentials(ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
    return this;
  }

  /**
   * Get paramsOauth2ClientCredentials
   * @return paramsOauth2ClientCredentials
   **/
  @Schema(description = "")
  
    @Valid
    public ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
    return paramsOauth2ClientCredentials;
  }

  public void setParamsOauth2ClientCredentials(ExternalArtifactsAccessConfigArtifactParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
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
    ExternalArtifactsAccessConfigArtifact externalArtifactsAccessConfigArtifact = (ExternalArtifactsAccessConfigArtifact) o;
    return Objects.equals(this.artifactUri, externalArtifactsAccessConfigArtifact.artifactUri) &&
        Objects.equals(this.overrideUri, externalArtifactsAccessConfigArtifact.overrideUri) &&
        Objects.equals(this.authType, externalArtifactsAccessConfigArtifact.authType) &&
        Objects.equals(this.username, externalArtifactsAccessConfigArtifact.username) &&
        Objects.equals(this.password, externalArtifactsAccessConfigArtifact.password) &&
        Objects.equals(this.paramsOauth2ClientCredentials, externalArtifactsAccessConfigArtifact.paramsOauth2ClientCredentials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifactUri, overrideUri, authType, username, password, paramsOauth2ClientCredentials);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExternalArtifactsAccessConfigArtifact {\n");
    
    sb.append("    artifactUri: ").append(toIndentedString(artifactUri)).append("\n");
    sb.append("    overrideUri: ").append(toIndentedString(overrideUri)).append("\n");
    sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
