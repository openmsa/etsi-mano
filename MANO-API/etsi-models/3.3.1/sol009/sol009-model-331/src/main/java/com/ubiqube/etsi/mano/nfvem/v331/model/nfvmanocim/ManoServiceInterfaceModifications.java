package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ServerInterfaceSecurityInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents attribute modifications that were performed on an  Individual NFV-MANO service interface\&quot; resource. The attributes that can  be included consist of those requested to be modified explicitly in the  \&quot;ManoServiceInterfaceModificationRequest\&quot; data structure. If applicable,  additional attributes of the \&quot;ManoServiceInterface\&quot; data structure that  were modified implicitly shall also be provided.  
 */
@ApiModel(description = "This type represents attribute modifications that were performed on an  Individual NFV-MANO service interface\" resource. The attributes that can  be included consist of those requested to be modified explicitly in the  \"ManoServiceInterfaceModificationRequest\" data structure. If applicable,  additional attributes of the \"ManoServiceInterface\" data structure that  were modified implicitly shall also be provided.  ")
@Validated
public class ManoServiceInterfaceModifications   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("apiRoot")
  private String apiRoot = null;

  @JsonProperty("apiUri")
  private String apiUri = null;

  @JsonProperty("securityInfo")
  private ServerInterfaceSecurityInfo securityInfo = null;

  @JsonProperty("metadata")
  private Map<String, String> metadata = null;

  public ManoServiceInterfaceModifications name(String name) {
    this.name = name;
    return this;
  }

  /**
   * If present, this attribute signals modifications of the \"name\" attribute in  \"ManoServiceInterface\", as defined in clause 5.6.2.11. 
   * @return name
  **/
  @ApiModelProperty(value = "If present, this attribute signals modifications of the \"name\" attribute in  \"ManoServiceInterface\", as defined in clause 5.6.2.11. ")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ManoServiceInterfaceModifications apiRoot(String apiRoot) {
    this.apiRoot = apiRoot;
    return this;
  }

  /**
   * Get apiRoot
   * @return apiRoot
  **/
  @ApiModelProperty(value = "")
  
    public String getApiRoot() {
    return apiRoot;
  }

  public void setApiRoot(String apiRoot) {
    this.apiRoot = apiRoot;
  }

  public ManoServiceInterfaceModifications apiUri(String apiUri) {
    this.apiUri = apiUri;
    return this;
  }

  /**
   * Get apiUri
   * @return apiUri
  **/
  @ApiModelProperty(value = "")
  
    public String getApiUri() {
    return apiUri;
  }

  public void setApiUri(String apiUri) {
    this.apiUri = apiUri;
  }

  public ManoServiceInterfaceModifications securityInfo(ServerInterfaceSecurityInfo securityInfo) {
    this.securityInfo = securityInfo;
    return this;
  }

  /**
   * Get securityInfo
   * @return securityInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ServerInterfaceSecurityInfo getSecurityInfo() {
    return securityInfo;
  }

  public void setSecurityInfo(ServerInterfaceSecurityInfo securityInfo) {
    this.securityInfo = securityInfo;
  }

  public ManoServiceInterfaceModifications metadata(Map<String, String> metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManoServiceInterfaceModifications manoServiceInterfaceModifications = (ManoServiceInterfaceModifications) o;
    return Objects.equals(this.name, manoServiceInterfaceModifications.name) &&
        Objects.equals(this.apiRoot, manoServiceInterfaceModifications.apiRoot) &&
        Objects.equals(this.apiUri, manoServiceInterfaceModifications.apiUri) &&
        Objects.equals(this.securityInfo, manoServiceInterfaceModifications.securityInfo) &&
        Objects.equals(this.metadata, manoServiceInterfaceModifications.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, apiRoot, apiUri, securityInfo, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoServiceInterfaceModifications {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    apiRoot: ").append(toIndentedString(apiRoot)).append("\n");
    sb.append("    apiUri: ").append(toIndentedString(apiUri)).append("\n");
    sb.append("    securityInfo: ").append(toIndentedString(securityInfo)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
