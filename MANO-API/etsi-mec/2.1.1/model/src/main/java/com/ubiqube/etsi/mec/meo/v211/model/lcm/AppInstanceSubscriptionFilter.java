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
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstSelectorType;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppsFromProviders;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;This data type represents subscription filter criteria to match application instances. &#x27;
 */
@ApiModel(description = "'This data type represents subscription filter criteria to match application instances. '")
@Validated
public class AppInstanceSubscriptionFilter   {
  @JsonProperty("appInstSelectorType")
  private AppInstSelectorType appInstSelectorType = null;

  @JsonProperty("appInstances")
  @Valid
  private List<String> appInstances = null;

  @JsonProperty("appsFromProviders")
  @Valid
  private List<AppsFromProviders> appsFromProviders = null;

  public AppInstanceSubscriptionFilter appInstSelectorType(AppInstSelectorType appInstSelectorType) {
    this.appInstSelectorType = appInstSelectorType;
    return this;
  }

  /**
   * Get appInstSelectorType
   * @return appInstSelectorType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppInstSelectorType getAppInstSelectorType() {
    return appInstSelectorType;
  }

  public void setAppInstSelectorType(AppInstSelectorType appInstSelectorType) {
    this.appInstSelectorType = appInstSelectorType;
  }

  public AppInstanceSubscriptionFilter appInstances(List<String> appInstances) {
    this.appInstances = appInstances;
    return this;
  }

  public AppInstanceSubscriptionFilter addAppInstancesItem(String appInstancesItem) {
    if (this.appInstances == null) {
      this.appInstances = new ArrayList<>();
    }
    this.appInstances.add(appInstancesItem);
    return this;
  }

  /**
   * Get appInstances
   * @return appInstances
  **/
  @ApiModelProperty(value = "")
  
    public List<String> getAppInstances() {
    return appInstances;
  }

  public void setAppInstances(List<String> appInstances) {
    this.appInstances = appInstances;
  }

  public AppInstanceSubscriptionFilter appsFromProviders(List<AppsFromProviders> appsFromProviders) {
    this.appsFromProviders = appsFromProviders;
    return this;
  }

  public AppInstanceSubscriptionFilter addAppsFromProvidersItem(AppsFromProviders appsFromProvidersItem) {
    if (this.appsFromProviders == null) {
      this.appsFromProviders = new ArrayList<>();
    }
    this.appsFromProviders.add(appsFromProvidersItem);
    return this;
  }

  /**
   * Get appsFromProviders
   * @return appsFromProviders
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<AppsFromProviders> getAppsFromProviders() {
    return appsFromProviders;
  }

  public void setAppsFromProviders(List<AppsFromProviders> appsFromProviders) {
    this.appsFromProviders = appsFromProviders;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppInstanceSubscriptionFilter appInstanceSubscriptionFilter = (AppInstanceSubscriptionFilter) o;
    return Objects.equals(this.appInstSelectorType, appInstanceSubscriptionFilter.appInstSelectorType) &&
        Objects.equals(this.appInstances, appInstanceSubscriptionFilter.appInstances) &&
        Objects.equals(this.appsFromProviders, appInstanceSubscriptionFilter.appsFromProviders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appInstSelectorType, appInstances, appsFromProviders);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppInstanceSubscriptionFilter {\n");
    
    sb.append("    appInstSelectorType: ").append(toIndentedString(appInstSelectorType)).append("\n");
    sb.append("    appInstances: ").append(toIndentedString(appInstances)).append("\n");
    sb.append("    appsFromProviders: ").append(toIndentedString(appsFromProviders)).append("\n");
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
