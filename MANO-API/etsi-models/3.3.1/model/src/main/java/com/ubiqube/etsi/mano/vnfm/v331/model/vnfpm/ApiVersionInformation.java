package com.ubiqube.etsi.mano.vnfm.v331.model.vnfpm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnfpm.ApiVersionInformationApiVersions;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents API version information. 
 */
@Schema(description = "This type represents API version information. ")
@Validated


public class ApiVersionInformation   {
  @JsonProperty("uriPrefix")
  private String uriPrefix = null;

  @JsonProperty("apiVersions")
  @Valid
  private List<ApiVersionInformationApiVersions> apiVersions = new ArrayList<>();

  public ApiVersionInformation uriPrefix(String uriPrefix) {
    this.uriPrefix = uriPrefix;
    return this;
  }

  /**
   * Specifies the URI prefix for the API, in the following form {apiRoot}/{apiName}/{apiMajorVersion}/. 
   * @return uriPrefix
   **/
  @Schema(required = true, description = "Specifies the URI prefix for the API, in the following form {apiRoot}/{apiName}/{apiMajorVersion}/. ")
      @NotNull

    public String getUriPrefix() {
    return uriPrefix;
  }

  public void setUriPrefix(String uriPrefix) {
    this.uriPrefix = uriPrefix;
  }

  public ApiVersionInformation apiVersions(List<ApiVersionInformationApiVersions> apiVersions) {
    this.apiVersions = apiVersions;
    return this;
  }

  public ApiVersionInformation addApiVersionsItem(ApiVersionInformationApiVersions apiVersionsItem) {
    this.apiVersions.add(apiVersionsItem);
    return this;
  }

  /**
   * Version(s) supported for the API signaled by the uriPrefix attribute. 
   * @return apiVersions
   **/
  @Schema(required = true, description = "Version(s) supported for the API signaled by the uriPrefix attribute. ")
      @NotNull
    @Valid
    public List<ApiVersionInformationApiVersions> getApiVersions() {
    return apiVersions;
  }

  public void setApiVersions(List<ApiVersionInformationApiVersions> apiVersions) {
    this.apiVersions = apiVersions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiVersionInformation apiVersionInformation = (ApiVersionInformation) o;
    return Objects.equals(this.uriPrefix, apiVersionInformation.uriPrefix) &&
        Objects.equals(this.apiVersions, apiVersionInformation.apiVersions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uriPrefix, apiVersions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiVersionInformation {\n");
    
    sb.append("    uriPrefix: ").append(toIndentedString(uriPrefix)).append("\n");
    sb.append("    apiVersions: ").append(toIndentedString(apiVersions)).append("\n");
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
