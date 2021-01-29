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
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceInfoLinks;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.InstantiatedAppState;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.InstantiationState;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.VimConnectionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type of AppInstanceInfo represents the parameters of instantiated application instance resources.&#x27;
 */
@ApiModel(description = "'The data type of AppInstanceInfo represents the parameters of instantiated application instance resources.'")
@Validated
public class AppInstanceInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("appInstanceName")
  private String appInstanceName = null;

  @JsonProperty("appInstanceDescription")
  private String appInstanceDescription = null;

  @JsonProperty("appDId")
  private String appDId = null;

  @JsonProperty("appProvider")
  private String appProvider = null;

  @JsonProperty("appName")
  private String appName = null;

  @JsonProperty("appSoftVersion")
  private String appSoftVersion = null;

  @JsonProperty("appDVersion")
  private String appDVersion = null;

  @JsonProperty("appPkgId")
  private String appPkgId = null;

  @JsonProperty("vimConnectionInfo")
  @Valid
  private List<VimConnectionInfo> vimConnectionInfo = null;

  @JsonProperty("instantiationState")
  private InstantiationState instantiationState = null;

  @JsonProperty("instantiatedAppState")
  private InstantiatedAppState instantiatedAppState = null;

  @JsonProperty("_links")
  private AppInstanceInfoLinks _links = null;

  public AppInstanceInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AppInstanceInfo appInstanceName(String appInstanceName) {
    this.appInstanceName = appInstanceName;
    return this;
  }

  /**
   * Get appInstanceName
   * @return appInstanceName
  **/
  @ApiModelProperty(value = "")
  
    public String getAppInstanceName() {
    return appInstanceName;
  }

  public void setAppInstanceName(String appInstanceName) {
    this.appInstanceName = appInstanceName;
  }

  public AppInstanceInfo appInstanceDescription(String appInstanceDescription) {
    this.appInstanceDescription = appInstanceDescription;
    return this;
  }

  /**
   * Get appInstanceDescription
   * @return appInstanceDescription
  **/
  @ApiModelProperty(value = "")
  
    public String getAppInstanceDescription() {
    return appInstanceDescription;
  }

  public void setAppInstanceDescription(String appInstanceDescription) {
    this.appInstanceDescription = appInstanceDescription;
  }

  public AppInstanceInfo appDId(String appDId) {
    this.appDId = appDId;
    return this;
  }

  /**
   * Get appDId
   * @return appDId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppDId() {
    return appDId;
  }

  public void setAppDId(String appDId) {
    this.appDId = appDId;
  }

  public AppInstanceInfo appProvider(String appProvider) {
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

  public AppInstanceInfo appName(String appName) {
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

  public AppInstanceInfo appSoftVersion(String appSoftVersion) {
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

  public AppInstanceInfo appDVersion(String appDVersion) {
    this.appDVersion = appDVersion;
    return this;
  }

  /**
   * Get appDVersion
   * @return appDVersion
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppDVersion() {
    return appDVersion;
  }

  public void setAppDVersion(String appDVersion) {
    this.appDVersion = appDVersion;
  }

  public AppInstanceInfo appPkgId(String appPkgId) {
    this.appPkgId = appPkgId;
    return this;
  }

  /**
   * Get appPkgId
   * @return appPkgId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppPkgId() {
    return appPkgId;
  }

  public void setAppPkgId(String appPkgId) {
    this.appPkgId = appPkgId;
  }

  public AppInstanceInfo vimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public AppInstanceInfo addVimConnectionInfoItem(VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new ArrayList<>();
    }
    this.vimConnectionInfo.add(vimConnectionInfoItem);
    return this;
  }

  /**
   * Get vimConnectionInfo
   * @return vimConnectionInfo
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(List<VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public AppInstanceInfo instantiationState(InstantiationState instantiationState) {
    this.instantiationState = instantiationState;
    return this;
  }

  /**
   * Get instantiationState
   * @return instantiationState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public InstantiationState getInstantiationState() {
    return instantiationState;
  }

  public void setInstantiationState(InstantiationState instantiationState) {
    this.instantiationState = instantiationState;
  }

  public AppInstanceInfo instantiatedAppState(InstantiatedAppState instantiatedAppState) {
    this.instantiatedAppState = instantiatedAppState;
    return this;
  }

  /**
   * Get instantiatedAppState
   * @return instantiatedAppState
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public InstantiatedAppState getInstantiatedAppState() {
    return instantiatedAppState;
  }

  public void setInstantiatedAppState(InstantiatedAppState instantiatedAppState) {
    this.instantiatedAppState = instantiatedAppState;
  }

  public AppInstanceInfo _links(AppInstanceInfoLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppInstanceInfoLinks getLinks() {
    return _links;
  }

  public void setLinks(AppInstanceInfoLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppInstanceInfo appInstanceInfo = (AppInstanceInfo) o;
    return Objects.equals(this.id, appInstanceInfo.id) &&
        Objects.equals(this.appInstanceName, appInstanceInfo.appInstanceName) &&
        Objects.equals(this.appInstanceDescription, appInstanceInfo.appInstanceDescription) &&
        Objects.equals(this.appDId, appInstanceInfo.appDId) &&
        Objects.equals(this.appProvider, appInstanceInfo.appProvider) &&
        Objects.equals(this.appName, appInstanceInfo.appName) &&
        Objects.equals(this.appSoftVersion, appInstanceInfo.appSoftVersion) &&
        Objects.equals(this.appDVersion, appInstanceInfo.appDVersion) &&
        Objects.equals(this.appPkgId, appInstanceInfo.appPkgId) &&
        Objects.equals(this.vimConnectionInfo, appInstanceInfo.vimConnectionInfo) &&
        Objects.equals(this.instantiationState, appInstanceInfo.instantiationState) &&
        Objects.equals(this.instantiatedAppState, appInstanceInfo.instantiatedAppState) &&
        Objects.equals(this._links, appInstanceInfo._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, appInstanceName, appInstanceDescription, appDId, appProvider, appName, appSoftVersion, appDVersion, appPkgId, vimConnectionInfo, instantiationState, instantiatedAppState, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppInstanceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    appInstanceName: ").append(toIndentedString(appInstanceName)).append("\n");
    sb.append("    appInstanceDescription: ").append(toIndentedString(appInstanceDescription)).append("\n");
    sb.append("    appDId: ").append(toIndentedString(appDId)).append("\n");
    sb.append("    appProvider: ").append(toIndentedString(appProvider)).append("\n");
    sb.append("    appName: ").append(toIndentedString(appName)).append("\n");
    sb.append("    appSoftVersion: ").append(toIndentedString(appSoftVersion)).append("\n");
    sb.append("    appDVersion: ").append(toIndentedString(appDVersion)).append("\n");
    sb.append("    appPkgId: ").append(toIndentedString(appPkgId)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
    sb.append("    instantiatedAppState: ").append(toIndentedString(instantiatedAppState)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
