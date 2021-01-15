package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;If present, match application instances that belong to application products with certain versions and a certain product name, from one particular provider.&#x27;
 */
@ApiModel(description = "'If present, match application instances that belong to application products with certain versions and a certain product name, from one particular provider.'")
@Validated
public class AppProductsVersions   {
  @JsonProperty("appSoftVersion")
  private String appSoftVersion = null;

  @JsonProperty("appDVersion")
  @Valid
  private List<String> appDVersion = null;

  public AppProductsVersions appSoftVersion(String appSoftVersion) {
    this.appSoftVersion = appSoftVersion;
    return this;
  }

  /**
   * Get appSoftVersion
   * @return appSoftVersion
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppSoftVersion() {
    return appSoftVersion;
  }

  public void setAppSoftVersion(String appSoftVersion) {
    this.appSoftVersion = appSoftVersion;
  }

  public AppProductsVersions appDVersion(List<String> appDVersion) {
    this.appDVersion = appDVersion;
    return this;
  }

  public AppProductsVersions addAppDVersionItem(String appDVersionItem) {
    if (this.appDVersion == null) {
      this.appDVersion = new ArrayList<>();
    }
    this.appDVersion.add(appDVersionItem);
    return this;
  }

  /**
   * Get appDVersion
   * @return appDVersion
  **/
  @ApiModelProperty(value = "")
  
    public List<String> getAppDVersion() {
    return appDVersion;
  }

  public void setAppDVersion(List<String> appDVersion) {
    this.appDVersion = appDVersion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppProductsVersions appProductsVersions = (AppProductsVersions) o;
    return Objects.equals(this.appSoftVersion, appProductsVersions.appSoftVersion) &&
        Objects.equals(this.appDVersion, appProductsVersions.appDVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appSoftVersion, appDVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppProductsVersions {\n");
    
    sb.append("    appSoftVersion: ").append(toIndentedString(appSoftVersion)).append("\n");
    sb.append("    appDVersion: ").append(toIndentedString(appDVersion)).append("\n");
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
