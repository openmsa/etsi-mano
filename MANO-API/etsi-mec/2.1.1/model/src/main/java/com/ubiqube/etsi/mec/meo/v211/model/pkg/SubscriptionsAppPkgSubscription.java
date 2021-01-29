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
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type represents the input parameters of \&quot;subscription operation\&quot; to notification of application package management for the onboarding, or operational state change of application package.&#x27;
 */
@ApiModel(description = "'The data type represents the input parameters of \"subscription operation\" to notification of application package management for the onboarding, or operational state change of application package.'")
@Validated
public class SubscriptionsAppPkgSubscription   {
  @JsonProperty("href")
  private String href = null;

  @JsonProperty("subsctiptionType")
  private SubsctiptionTypeAppPkg subsctiptionType = null;

  public SubscriptionsAppPkgSubscription href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Get href
   * @return href
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public SubscriptionsAppPkgSubscription subsctiptionType(SubsctiptionTypeAppPkg subsctiptionType) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionsAppPkgSubscription subscriptionsAppPkgSubscription = (SubscriptionsAppPkgSubscription) o;
    return Objects.equals(this.href, subscriptionsAppPkgSubscription.href) &&
        Objects.equals(this.subsctiptionType, subscriptionsAppPkgSubscription.subsctiptionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, subsctiptionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsAppPkgSubscription {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    subsctiptionType: ").append(toIndentedString(subsctiptionType)).append("\n");
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
