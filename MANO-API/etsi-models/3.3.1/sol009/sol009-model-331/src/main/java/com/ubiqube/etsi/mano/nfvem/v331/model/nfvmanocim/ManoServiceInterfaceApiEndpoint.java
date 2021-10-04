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
 * Exposed API endpoint of the interface. 
 */
@ApiModel(description = "Exposed API endpoint of the interface. ")
@Validated
public class ManoServiceInterfaceApiEndpoint   {
  @JsonProperty("apiRoot")
  private String apiRoot = null;

  @JsonProperty("apiName")
  private String apiName = null;

  @JsonProperty("apiMajorVersion")
  private String apiMajorVersion = null;

  @JsonProperty("apiUri")
  private String apiUri = null;

  public ManoServiceInterfaceApiEndpoint apiRoot(String apiRoot) {
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

  public ManoServiceInterfaceApiEndpoint apiName(String apiName) {
    this.apiName = apiName;
    return this;
  }

  /**
   * Indicates the interface name in an abbreviated form. Shall be present  for ETSI NFV specified RESTful NFV-MANO APIs. The {apiName} of each  interface is defined in the standard the interface is compliant to  (see also clause 4.1 of ETSI GS NFV-SOL 013). May be present otherwise. 
   * @return apiName
  **/
  @ApiModelProperty(value = "Indicates the interface name in an abbreviated form. Shall be present  for ETSI NFV specified RESTful NFV-MANO APIs. The {apiName} of each  interface is defined in the standard the interface is compliant to  (see also clause 4.1 of ETSI GS NFV-SOL 013). May be present otherwise. ")
  
    public String getApiName() {
    return apiName;
  }

  public void setApiName(String apiName) {
    this.apiName = apiName;
  }

  public ManoServiceInterfaceApiEndpoint apiMajorVersion(String apiMajorVersion) {
    this.apiMajorVersion = apiMajorVersion;
    return this;
  }

  /**
   * Indicates the current major version of the API. Shall be present for  ETSI NFV specified RESTful NFV-MANO APIs. The major version is defined  in the standard the interface is compliant to (see also clause 4.1 of  ETSI GS NFV-SOL 013). May be present otherwise. 
   * @return apiMajorVersion
  **/
  @ApiModelProperty(value = "Indicates the current major version of the API. Shall be present for  ETSI NFV specified RESTful NFV-MANO APIs. The major version is defined  in the standard the interface is compliant to (see also clause 4.1 of  ETSI GS NFV-SOL 013). May be present otherwise. ")
  
    public String getApiMajorVersion() {
    return apiMajorVersion;
  }

  public void setApiMajorVersion(String apiMajorVersion) {
    this.apiMajorVersion = apiMajorVersion;
  }

  public ManoServiceInterfaceApiEndpoint apiUri(String apiUri) {
    this.apiUri = apiUri;
    return this;
  }

  /**
   * Get apiUri
   * @return apiUri
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getApiUri() {
    return apiUri;
  }

  public void setApiUri(String apiUri) {
    this.apiUri = apiUri;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManoServiceInterfaceApiEndpoint manoServiceInterfaceApiEndpoint = (ManoServiceInterfaceApiEndpoint) o;
    return Objects.equals(this.apiRoot, manoServiceInterfaceApiEndpoint.apiRoot) &&
        Objects.equals(this.apiName, manoServiceInterfaceApiEndpoint.apiName) &&
        Objects.equals(this.apiMajorVersion, manoServiceInterfaceApiEndpoint.apiMajorVersion) &&
        Objects.equals(this.apiUri, manoServiceInterfaceApiEndpoint.apiUri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(apiRoot, apiName, apiMajorVersion, apiUri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoServiceInterfaceApiEndpoint {\n");
    
    sb.append("    apiRoot: ").append(toIndentedString(apiRoot)).append("\n");
    sb.append("    apiName: ").append(toIndentedString(apiName)).append("\n");
    sb.append("    apiMajorVersion: ").append(toIndentedString(apiMajorVersion)).append("\n");
    sb.append("    apiUri: ").append(toIndentedString(apiUri)).append("\n");
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
