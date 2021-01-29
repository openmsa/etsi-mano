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
import com.ubiqube.etsi.mec.meo.v211.model.pkg.CategoryRef;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.TransportDependency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ServiceDependency
 */
@Validated
public class ServiceDependency   {
  @JsonProperty("requestedPermissions")
  @Valid
  private List<String> requestedPermissions = null;

  @JsonProperty("serCategory")
  private CategoryRef serCategory = null;

  @JsonProperty("serName")
  private String serName = null;

  @JsonProperty("serTransportDependencies")
  @Valid
  private List<TransportDependency> serTransportDependencies = null;

  @JsonProperty("version")
  private String version = null;

  public ServiceDependency requestedPermissions(List<String> requestedPermissions) {
    this.requestedPermissions = requestedPermissions;
    return this;
  }

  public ServiceDependency addRequestedPermissionsItem(String requestedPermissionsItem) {
    if (this.requestedPermissions == null) {
      this.requestedPermissions = new ArrayList<>();
    }
    this.requestedPermissions.add(requestedPermissionsItem);
    return this;
  }

  /**
   * Requested permissions regarding the access of the application to the service. See clause 8.2 of ETSI GS MEC 009 [4]. The format of this attribute is left for the data model design stage.
   * @return requestedPermissions
  **/
  @ApiModelProperty(value = "Requested permissions regarding the access of the application to the service. See clause 8.2 of ETSI GS MEC 009 [4]. The format of this attribute is left for the data model design stage.")
  
    public List<String> getRequestedPermissions() {
    return requestedPermissions;
  }

  public void setRequestedPermissions(List<String> requestedPermissions) {
    this.requestedPermissions = requestedPermissions;
  }

  public ServiceDependency serCategory(CategoryRef serCategory) {
    this.serCategory = serCategory;
    return this;
  }

  /**
   * Get serCategory
   * @return serCategory
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public CategoryRef getSerCategory() {
    return serCategory;
  }

  public void setSerCategory(CategoryRef serCategory) {
    this.serCategory = serCategory;
  }

  public ServiceDependency serName(String serName) {
    this.serName = serName;
    return this;
  }

  /**
   * The name of the service, for example, RNIS, LocationService, etc.
   * @return serName
  **/
  @ApiModelProperty(required = true, value = "The name of the service, for example, RNIS, LocationService, etc.")
      @NotNull

    public String getSerName() {
    return serName;
  }

  public void setSerName(String serName) {
    this.serName = serName;
  }

  public ServiceDependency serTransportDependencies(List<TransportDependency> serTransportDependencies) {
    this.serTransportDependencies = serTransportDependencies;
    return this;
  }

  public ServiceDependency addSerTransportDependenciesItem(TransportDependency serTransportDependenciesItem) {
    if (this.serTransportDependencies == null) {
      this.serTransportDependencies = new ArrayList<>();
    }
    this.serTransportDependencies.add(serTransportDependenciesItem);
    return this;
  }

  /**
   * Indicates transport and serialization format dependencies of consuming the service. Defaults to REST + JSON if absent. See note.
   * @return serTransportDependencies
  **/
  @ApiModelProperty(value = "Indicates transport and serialization format dependencies of consuming the service. Defaults to REST + JSON if absent. See note.")
      @Valid
    public List<TransportDependency> getSerTransportDependencies() {
    return serTransportDependencies;
  }

  public void setSerTransportDependencies(List<TransportDependency> serTransportDependencies) {
    this.serTransportDependencies = serTransportDependencies;
  }

  public ServiceDependency version(String version) {
    this.version = version;
    return this;
  }

  /**
   * The version of the service.
   * @return version
  **/
  @ApiModelProperty(required = true, value = "The version of the service.")
      @NotNull

    public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceDependency serviceDependency = (ServiceDependency) o;
    return Objects.equals(this.requestedPermissions, serviceDependency.requestedPermissions) &&
        Objects.equals(this.serCategory, serviceDependency.serCategory) &&
        Objects.equals(this.serName, serviceDependency.serName) &&
        Objects.equals(this.serTransportDependencies, serviceDependency.serTransportDependencies) &&
        Objects.equals(this.version, serviceDependency.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestedPermissions, serCategory, serName, serTransportDependencies, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceDependency {\n");
    
    sb.append("    requestedPermissions: ").append(toIndentedString(requestedPermissions)).append("\n");
    sb.append("    serCategory: ").append(toIndentedString(serCategory)).append("\n");
    sb.append("    serName: ").append(toIndentedString(serName)).append("\n");
    sb.append("    serTransportDependencies: ").append(toIndentedString(serTransportDependencies)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
