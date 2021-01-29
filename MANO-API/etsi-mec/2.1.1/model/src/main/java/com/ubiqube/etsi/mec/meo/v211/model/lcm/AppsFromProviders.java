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
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppProducts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;Present only if appInstIdSelector &#x3D; APP_FROM_PROVIDER. Match existing application instances, or those created in the future whilst the subscription is active, that belong to applications from certain providers.&#x27;
 */
@ApiModel(description = "'Present only if appInstIdSelector = APP_FROM_PROVIDER. Match existing application instances, or those created in the future whilst the subscription is active, that belong to applications from certain providers.'")
@Validated
public class AppsFromProviders   {
  @JsonProperty("appProvider")
  private String appProvider = null;

  @JsonProperty("appProducts")
  private AppProducts appProducts = null;

  public AppsFromProviders appProvider(String appProvider) {
    this.appProvider = appProvider;
    return this;
  }

  /**
   * Get appProvider
   * @return appProvider
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppProvider() {
    return appProvider;
  }

  public void setAppProvider(String appProvider) {
    this.appProvider = appProvider;
  }

  public AppsFromProviders appProducts(AppProducts appProducts) {
    this.appProducts = appProducts;
    return this;
  }

  /**
   * Get appProducts
   * @return appProducts
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AppProducts getAppProducts() {
    return appProducts;
  }

  public void setAppProducts(AppProducts appProducts) {
    this.appProducts = appProducts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppsFromProviders appsFromProviders = (AppsFromProviders) o;
    return Objects.equals(this.appProvider, appsFromProviders.appProvider) &&
        Objects.equals(this.appProducts, appsFromProviders.appProducts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appProvider, appProducts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppsFromProviders {\n");
    
    sb.append("    appProvider: ").append(toIndentedString(appProvider)).append("\n");
    sb.append("    appProducts: ").append(toIndentedString(appProducts)).append("\n");
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
