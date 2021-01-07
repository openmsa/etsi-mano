package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.grant.ExtVirtualLinkData;
import com.ubiqube.etsi.mec.meo.v211.model.grant.GrantInfo;
import com.ubiqube.etsi.mec.meo.v211.model.grant.GrantLinks;
import com.ubiqube.etsi.mec.meo.v211.model.grant.KeyValuePairs;
import com.ubiqube.etsi.mec.meo.v211.model.grant.VimAssets;
import com.ubiqube.etsi.mec.meo.v211.model.grant.VimConnectionInfo;
import com.ubiqube.etsi.mec.meo.v211.model.grant.ZoneGroupInfo;
import com.ubiqube.etsi.mec.meo.v211.model.grant.ZoneInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;This type represents a grant. Refer to clause 9.5.2.3 of ETSI GS NFV-SOL 003 &#x27;
 */
@ApiModel(description = "'This type represents a grant. Refer to clause 9.5.2.3 of ETSI GS NFV-SOL 003 '")
@Validated
public class Grant   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("appInstanceId")
  private String appInstanceId = null;

  @JsonProperty("appLcmOpOccId")
  private String appLcmOpOccId = null;

  @JsonProperty("vimConnections")
  @Valid
  private List<VimConnectionInfo> vimConnections = null;

  @JsonProperty("zones")
  @Valid
  private List<ZoneInfo> zones = null;

  @JsonProperty("zoneGroups")
  @Valid
  private List<ZoneGroupInfo> zoneGroups = null;

  @JsonProperty("addResources")
  @Valid
  private List<GrantInfo> addResources = null;

  @JsonProperty("tempResources")
  @Valid
  private List<GrantInfo> tempResources = null;

  @JsonProperty("removeResources")
  @Valid
  private List<GrantInfo> removeResources = null;

  @JsonProperty("updateResources")
  @Valid
  private List<GrantInfo> updateResources = null;

  @JsonProperty("vimAssets")
  private VimAssets vimAssets = null;

  @JsonProperty("extVirtualLinks")
  @Valid
  private List<ExtVirtualLinkData> extVirtualLinks = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("_links")
  private GrantLinks _links = null;

  public Grant id(String id) {
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

  public Grant appInstanceId(String appInstanceId) {
    this.appInstanceId = appInstanceId;
    return this;
  }

  /**
   * Get appInstanceId
   * @return appInstanceId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppInstanceId() {
    return appInstanceId;
  }

  public void setAppInstanceId(String appInstanceId) {
    this.appInstanceId = appInstanceId;
  }

  public Grant appLcmOpOccId(String appLcmOpOccId) {
    this.appLcmOpOccId = appLcmOpOccId;
    return this;
  }

  /**
   * Get appLcmOpOccId
   * @return appLcmOpOccId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppLcmOpOccId() {
    return appLcmOpOccId;
  }

  public void setAppLcmOpOccId(String appLcmOpOccId) {
    this.appLcmOpOccId = appLcmOpOccId;
  }

  public Grant vimConnections(List<VimConnectionInfo> vimConnections) {
    this.vimConnections = vimConnections;
    return this;
  }

  public Grant addVimConnectionsItem(VimConnectionInfo vimConnectionsItem) {
    if (this.vimConnections == null) {
      this.vimConnections = new ArrayList<>();
    }
    this.vimConnections.add(vimConnectionsItem);
    return this;
  }

  /**
   * Get vimConnections
   * @return vimConnections
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<VimConnectionInfo> getVimConnections() {
    return vimConnections;
  }

  public void setVimConnections(List<VimConnectionInfo> vimConnections) {
    this.vimConnections = vimConnections;
  }

  public Grant zones(List<ZoneInfo> zones) {
    this.zones = zones;
    return this;
  }

  public Grant addZonesItem(ZoneInfo zonesItem) {
    if (this.zones == null) {
      this.zones = new ArrayList<>();
    }
    this.zones.add(zonesItem);
    return this;
  }

  /**
   * Get zones
   * @return zones
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ZoneInfo> getZones() {
    return zones;
  }

  public void setZones(List<ZoneInfo> zones) {
    this.zones = zones;
  }

  public Grant zoneGroups(List<ZoneGroupInfo> zoneGroups) {
    this.zoneGroups = zoneGroups;
    return this;
  }

  public Grant addZoneGroupsItem(ZoneGroupInfo zoneGroupsItem) {
    if (this.zoneGroups == null) {
      this.zoneGroups = new ArrayList<>();
    }
    this.zoneGroups.add(zoneGroupsItem);
    return this;
  }

  /**
   * Get zoneGroups
   * @return zoneGroups
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ZoneGroupInfo> getZoneGroups() {
    return zoneGroups;
  }

  public void setZoneGroups(List<ZoneGroupInfo> zoneGroups) {
    this.zoneGroups = zoneGroups;
  }

  public Grant addResources(List<GrantInfo> addResources) {
    this.addResources = addResources;
    return this;
  }

  public Grant addAddResourcesItem(GrantInfo addResourcesItem) {
    if (this.addResources == null) {
      this.addResources = new ArrayList<>();
    }
    this.addResources.add(addResourcesItem);
    return this;
  }

  /**
   * Get addResources
   * @return addResources
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<GrantInfo> getAddResources() {
    return addResources;
  }

  public void setAddResources(List<GrantInfo> addResources) {
    this.addResources = addResources;
  }

  public Grant tempResources(List<GrantInfo> tempResources) {
    this.tempResources = tempResources;
    return this;
  }

  public Grant addTempResourcesItem(GrantInfo tempResourcesItem) {
    if (this.tempResources == null) {
      this.tempResources = new ArrayList<>();
    }
    this.tempResources.add(tempResourcesItem);
    return this;
  }

  /**
   * Get tempResources
   * @return tempResources
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<GrantInfo> getTempResources() {
    return tempResources;
  }

  public void setTempResources(List<GrantInfo> tempResources) {
    this.tempResources = tempResources;
  }

  public Grant removeResources(List<GrantInfo> removeResources) {
    this.removeResources = removeResources;
    return this;
  }

  public Grant addRemoveResourcesItem(GrantInfo removeResourcesItem) {
    if (this.removeResources == null) {
      this.removeResources = new ArrayList<>();
    }
    this.removeResources.add(removeResourcesItem);
    return this;
  }

  /**
   * Get removeResources
   * @return removeResources
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<GrantInfo> getRemoveResources() {
    return removeResources;
  }

  public void setRemoveResources(List<GrantInfo> removeResources) {
    this.removeResources = removeResources;
  }

  public Grant updateResources(List<GrantInfo> updateResources) {
    this.updateResources = updateResources;
    return this;
  }

  public Grant addUpdateResourcesItem(GrantInfo updateResourcesItem) {
    if (this.updateResources == null) {
      this.updateResources = new ArrayList<>();
    }
    this.updateResources.add(updateResourcesItem);
    return this;
  }

  /**
   * Get updateResources
   * @return updateResources
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<GrantInfo> getUpdateResources() {
    return updateResources;
  }

  public void setUpdateResources(List<GrantInfo> updateResources) {
    this.updateResources = updateResources;
  }

  public Grant vimAssets(VimAssets vimAssets) {
    this.vimAssets = vimAssets;
    return this;
  }

  /**
   * Get vimAssets
   * @return vimAssets
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public VimAssets getVimAssets() {
    return vimAssets;
  }

  public void setVimAssets(VimAssets vimAssets) {
    this.vimAssets = vimAssets;
  }

  public Grant extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public Grant addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    if (this.extVirtualLinks == null) {
      this.extVirtualLinks = new ArrayList<>();
    }
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Get extVirtualLinks
   * @return extVirtualLinks
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public Grant additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }

  public Grant _links(GrantLinks _links) {
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
    public GrantLinks getLinks() {
    return _links;
  }

  public void setLinks(GrantLinks _links) {
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
    Grant grant = (Grant) o;
    return Objects.equals(this.id, grant.id) &&
        Objects.equals(this.appInstanceId, grant.appInstanceId) &&
        Objects.equals(this.appLcmOpOccId, grant.appLcmOpOccId) &&
        Objects.equals(this.vimConnections, grant.vimConnections) &&
        Objects.equals(this.zones, grant.zones) &&
        Objects.equals(this.zoneGroups, grant.zoneGroups) &&
        Objects.equals(this.addResources, grant.addResources) &&
        Objects.equals(this.tempResources, grant.tempResources) &&
        Objects.equals(this.removeResources, grant.removeResources) &&
        Objects.equals(this.updateResources, grant.updateResources) &&
        Objects.equals(this.vimAssets, grant.vimAssets) &&
        Objects.equals(this.extVirtualLinks, grant.extVirtualLinks) &&
        Objects.equals(this.additionalParams, grant.additionalParams) &&
        Objects.equals(this._links, grant._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, appInstanceId, appLcmOpOccId, vimConnections, zones, zoneGroups, addResources, tempResources, removeResources, updateResources, vimAssets, extVirtualLinks, additionalParams, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Grant {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    appInstanceId: ").append(toIndentedString(appInstanceId)).append("\n");
    sb.append("    appLcmOpOccId: ").append(toIndentedString(appLcmOpOccId)).append("\n");
    sb.append("    vimConnections: ").append(toIndentedString(vimConnections)).append("\n");
    sb.append("    zones: ").append(toIndentedString(zones)).append("\n");
    sb.append("    zoneGroups: ").append(toIndentedString(zoneGroups)).append("\n");
    sb.append("    addResources: ").append(toIndentedString(addResources)).append("\n");
    sb.append("    tempResources: ").append(toIndentedString(tempResources)).append("\n");
    sb.append("    removeResources: ").append(toIndentedString(removeResources)).append("\n");
    sb.append("    updateResources: ").append(toIndentedString(updateResources)).append("\n");
    sb.append("    vimAssets: ").append(toIndentedString(vimAssets)).append("\n");
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
