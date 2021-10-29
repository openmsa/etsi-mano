package com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm.VnfSnapshotPkgExtArtifactsAccessConfigParamsOauth2ClientCredentials;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VnfSnapshotPkgExtArtifactsAccessConfigArtifact
 */
@Validated


public class VnfSnapshotPkgExtArtifactsAccessConfigArtifact   {
  @JsonProperty("artifactUri")
  private String artifactUri = null;

  @JsonProperty("overrideUri")
  private String overrideUri = null;

  /**
   * Defines the type of authentication / authorization for downloading the VNF package. Permitted values: - BASIC: Only the \"username\" and \"password\" attributes shall be present. - OAUTH2_CLIENT_CREDENTIALS: Only the \"paramsOauth2ClientCredentials\" attribute shall  be present. This attribute shall not be present if no credentials are provided for the artifact. 
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
  private VnfSnapshotPkgExtArtifactsAccessConfigParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;

  public VnfSnapshotPkgExtArtifactsAccessConfigArtifact artifactUri(String artifactUri) {
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

  public VnfSnapshotPkgExtArtifactsAccessConfigArtifact overrideUri(String overrideUri) {
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

  public VnfSnapshotPkgExtArtifactsAccessConfigArtifact authType(AuthTypeEnum authType) {
    this.authType = authType;
    return this;
  }

  /**
   * Defines the type of authentication / authorization for downloading the VNF package. Permitted values: - BASIC: Only the \"username\" and \"password\" attributes shall be present. - OAUTH2_CLIENT_CREDENTIALS: Only the \"paramsOauth2ClientCredentials\" attribute shall  be present. This attribute shall not be present if no credentials are provided for the artifact. 
   * @return authType
   **/
  @Schema(description = "Defines the type of authentication / authorization for downloading the VNF package. Permitted values: - BASIC: Only the \"username\" and \"password\" attributes shall be present. - OAUTH2_CLIENT_CREDENTIALS: Only the \"paramsOauth2ClientCredentials\" attribute shall  be present. This attribute shall not be present if no credentials are provided for the artifact. ")
  
    public AuthTypeEnum getAuthType() {
    return authType;
  }

  public void setAuthType(AuthTypeEnum authType) {
    this.authType = authType;
  }

  public VnfSnapshotPkgExtArtifactsAccessConfigArtifact username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Username to be used for authentication. 
   * @return username
   **/
  @Schema(description = "Username to be used for authentication. ")
  
    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public VnfSnapshotPkgExtArtifactsAccessConfigArtifact password(String password) {
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

  public VnfSnapshotPkgExtArtifactsAccessConfigArtifact paramsOauth2ClientCredentials(VnfSnapshotPkgExtArtifactsAccessConfigParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
    this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
    return this;
  }

  /**
   * Get paramsOauth2ClientCredentials
   * @return paramsOauth2ClientCredentials
   **/
  @Schema(description = "")
  
    @Valid
    public VnfSnapshotPkgExtArtifactsAccessConfigParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
    return paramsOauth2ClientCredentials;
  }

  public void setParamsOauth2ClientCredentials(VnfSnapshotPkgExtArtifactsAccessConfigParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
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
    VnfSnapshotPkgExtArtifactsAccessConfigArtifact vnfSnapshotPkgExtArtifactsAccessConfigArtifact = (VnfSnapshotPkgExtArtifactsAccessConfigArtifact) o;
    return Objects.equals(this.artifactUri, vnfSnapshotPkgExtArtifactsAccessConfigArtifact.artifactUri) &&
        Objects.equals(this.overrideUri, vnfSnapshotPkgExtArtifactsAccessConfigArtifact.overrideUri) &&
        Objects.equals(this.authType, vnfSnapshotPkgExtArtifactsAccessConfigArtifact.authType) &&
        Objects.equals(this.username, vnfSnapshotPkgExtArtifactsAccessConfigArtifact.username) &&
        Objects.equals(this.password, vnfSnapshotPkgExtArtifactsAccessConfigArtifact.password) &&
        Objects.equals(this.paramsOauth2ClientCredentials, vnfSnapshotPkgExtArtifactsAccessConfigArtifact.paramsOauth2ClientCredentials);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifactUri, overrideUri, authType, username, password, paramsOauth2ClientCredentials);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfSnapshotPkgExtArtifactsAccessConfigArtifact {\n");
    
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
