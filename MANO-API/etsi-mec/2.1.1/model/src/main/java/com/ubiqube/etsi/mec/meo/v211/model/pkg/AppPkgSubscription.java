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
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.SubsctiptionTypeAppPkg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type represents the input parameters of \&quot;subscription operation\&quot; to notification of application package management for the onboarding, or operational state change of application package.&#x27;
 */
@ApiModel(description = "'The data type represents the input parameters of \"subscription operation\" to notification of application package management for the onboarding, or operational state change of application package.'")
@Validated
public class AppPkgSubscription   {
  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("subsctiptionType")
  private SubsctiptionTypeAppPkg subsctiptionType = null;

  @JsonProperty("appPkgFilter")
  @Valid
  private List<String> appPkgFilter = null;

  public AppPkgSubscription callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * Get callbackUri
   * @return callbackUri
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public AppPkgSubscription subsctiptionType(SubsctiptionTypeAppPkg subsctiptionType) {
    this.subsctiptionType = subsctiptionType;
    return this;
  }

  /**
   * Get subsctiptionType
   * @return subsctiptionType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public SubsctiptionTypeAppPkg getSubsctiptionType() {
    return subsctiptionType;
  }

  public void setSubsctiptionType(SubsctiptionTypeAppPkg subsctiptionType) {
    this.subsctiptionType = subsctiptionType;
  }

  public AppPkgSubscription appPkgFilter(List<String> appPkgFilter) {
    this.appPkgFilter = appPkgFilter;
    return this;
  }

  public AppPkgSubscription addAppPkgFilterItem(String appPkgFilterItem) {
    if (this.appPkgFilter == null) {
      this.appPkgFilter = new ArrayList<>();
    }
    this.appPkgFilter.add(appPkgFilterItem);
    return this;
  }

  /**
   * Get appPkgFilter
   * @return appPkgFilter
  **/
  @ApiModelProperty(value = "")
  
    public List<String> getAppPkgFilter() {
    return appPkgFilter;
  }

  public void setAppPkgFilter(List<String> appPkgFilter) {
    this.appPkgFilter = appPkgFilter;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppPkgSubscription appPkgSubscription = (AppPkgSubscription) o;
    return Objects.equals(this.callbackUri, appPkgSubscription.callbackUri) &&
        Objects.equals(this.subsctiptionType, appPkgSubscription.subsctiptionType) &&
        Objects.equals(this.appPkgFilter, appPkgSubscription.appPkgFilter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callbackUri, subsctiptionType, appPkgFilter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppPkgSubscription {\n");
    
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    subsctiptionType: ").append(toIndentedString(subsctiptionType)).append("\n");
    sb.append("    appPkgFilter: ").append(toIndentedString(appPkgFilter)).append("\n");
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
