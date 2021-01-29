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
package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppProductsVersions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;If present, match application instances that belong to application products with certain product names, from one particular provider.&#x27;
 */
@ApiModel(description = "'If present, match application instances that belong to application products with certain product names, from one particular provider.'")
@Validated
public class AppProducts   {
  @JsonProperty("appName")
  private String appName = null;

  @JsonProperty("versions")
  private AppProductsVersions versions = null;

  public AppProducts appName(String appName) {
    this.appName = appName;
    return this;
  }

  /**
   * Get appName
   * @return appName
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public AppProducts versions(AppProductsVersions versions) {
    this.versions = versions;
    return this;
  }

  /**
   * Get versions
   * @return versions
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AppProductsVersions getVersions() {
    return versions;
  }

  public void setVersions(AppProductsVersions versions) {
    this.versions = versions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppProducts appProducts = (AppProducts) o;
    return Objects.equals(this.appName, appProducts.appName) &&
        Objects.equals(this.versions, appProducts.versions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appName, versions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppProducts {\n");
    
    sb.append("    appName: ").append(toIndentedString(appName)).append("\n");
    sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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
