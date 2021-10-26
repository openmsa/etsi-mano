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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.VimConnectionInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Change VNF flavour\&quot; operation.  It shall comply with the provisions defined in table 5.5.2.7-1. NOTE 1: The indication of externally-managed internal VLs is needed in case networks          have been pre-configured for use with certain VNFs, for instance to ensure          that these networks have certain properties such as security or acceleration          features, or to address particular network topologies. The present document          assumes that externally-managed internal VLs are managed by the NFVO and created          towards the VIM. NOTE 2: It is possible to have several ExtManagedVirtualLinkData for the same VNF internal          VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkData          corresponding to the same VNF internal VL shall indicate so by referencing to the same          VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 4.4.1.12). 
 */
@Schema(description = "This type represents request parameters for the \"Change VNF flavour\" operation.  It shall comply with the provisions defined in table 5.5.2.7-1. NOTE 1: The indication of externally-managed internal VLs is needed in case networks          have been pre-configured for use with certain VNFs, for instance to ensure          that these networks have certain properties such as security or acceleration          features, or to address particular network topologies. The present document          assumes that externally-managed internal VLs are managed by the NFVO and created          towards the VIM. NOTE 2: It is possible to have several ExtManagedVirtualLinkData for the same VNF internal          VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkData          corresponding to the same VNF internal VL shall indicate so by referencing to the same          VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 4.4.1.12). ")
@Validated


public class ChangeVnfFlavourRequest   {
  @JsonProperty("newFlavourId")
  private String newFlavourId = null;

  @JsonProperty("instantiationLevelId")
  private String instantiationLevelId = null;

  @JsonProperty("extVirtualLinks")
  @Valid
  private List<ExtVirtualLinkData> extVirtualLinks = null;

  @JsonProperty("extManagedVirtualLinks")
  @Valid
  private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

  @JsonProperty("vimConnectionInfo")
  @Valid
  private Map<String, VimConnectionInfo> vimConnectionInfo = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  public ChangeVnfFlavourRequest newFlavourId(String newFlavourId) {
    this.newFlavourId = newFlavourId;
    return this;
  }

  /**
   * Get newFlavourId
   * @return newFlavourId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNewFlavourId() {
    return newFlavourId;
  }

  public void setNewFlavourId(String newFlavourId) {
    this.newFlavourId = newFlavourId;
  }

  public ChangeVnfFlavourRequest instantiationLevelId(String instantiationLevelId) {
    this.instantiationLevelId = instantiationLevelId;
    return this;
  }

  /**
   * Get instantiationLevelId
   * @return instantiationLevelId
   **/
  @Schema(description = "")
  
    public String getInstantiationLevelId() {
    return instantiationLevelId;
  }

  public void setInstantiationLevelId(String instantiationLevelId) {
    this.instantiationLevelId = instantiationLevelId;
  }

  public ChangeVnfFlavourRequest extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public ChangeVnfFlavourRequest addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    if (this.extVirtualLinks == null) {
      this.extVirtualLinks = new ArrayList<>();
    }
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to connect the VNF to. Entries in the list of external VLs that are unchanged need not be supplied as part of this request. 
   * @return extVirtualLinks
   **/
  @Schema(description = "Information about external VLs to connect the VNF to. Entries in the list of external VLs that are unchanged need not be supplied as part of this request. ")
      @Valid
    public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public ChangeVnfFlavourRequest extManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
    return this;
  }

  public ChangeVnfFlavourRequest addExtManagedVirtualLinksItem(ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
    if (this.extManagedVirtualLinks == null) {
      this.extManagedVirtualLinks = new ArrayList<>();
    }
    this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
    return this;
  }

  /**
   * Information about internal VLs that are managed by the NFVO. See notes 1 and 2. 
   * @return extManagedVirtualLinks
   **/
  @Schema(description = "Information about internal VLs that are managed by the NFVO. See notes 1 and 2. ")
      @Valid
    public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
    return extManagedVirtualLinks;
  }

  public void setExtManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
  }

  public ChangeVnfFlavourRequest vimConnectionInfo(Map<String, VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public ChangeVnfFlavourRequest putVimConnectionInfoItem(String key, VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new HashMap<>();
    }
    this.vimConnectionInfo.put(key, vimConnectionInfoItem);
    return this;
  }

  /**
   * Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if  VNF-related resource management in direct mode is applicable. The VNFM shall apply the content of this  attribute to the \"vimConnectionInfo\" attribute of \"VnfInstance\" according to the rules of JSON Merge Patch  (see IETF RFC 7396). 
   * @return vimConnectionInfo
   **/
  @Schema(description = "Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if  VNF-related resource management in direct mode is applicable. The VNFM shall apply the content of this  attribute to the \"vimConnectionInfo\" attribute of \"VnfInstance\" according to the rules of JSON Merge Patch  (see IETF RFC 7396). ")
      @Valid
    public Map<String, VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(Map<String, VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public ChangeVnfFlavourRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }

  public ChangeVnfFlavourRequest extensions(KeyValuePairs extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * Get extensions
   * @return extensions
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getExtensions() {
    return extensions;
  }

  public void setExtensions(KeyValuePairs extensions) {
    this.extensions = extensions;
  }

  public ChangeVnfFlavourRequest vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Get vnfConfigurableProperties
   * @return vnfConfigurableProperties
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeVnfFlavourRequest changeVnfFlavourRequest = (ChangeVnfFlavourRequest) o;
    return Objects.equals(this.newFlavourId, changeVnfFlavourRequest.newFlavourId) &&
        Objects.equals(this.instantiationLevelId, changeVnfFlavourRequest.instantiationLevelId) &&
        Objects.equals(this.extVirtualLinks, changeVnfFlavourRequest.extVirtualLinks) &&
        Objects.equals(this.extManagedVirtualLinks, changeVnfFlavourRequest.extManagedVirtualLinks) &&
        Objects.equals(this.vimConnectionInfo, changeVnfFlavourRequest.vimConnectionInfo) &&
        Objects.equals(this.additionalParams, changeVnfFlavourRequest.additionalParams) &&
        Objects.equals(this.extensions, changeVnfFlavourRequest.extensions) &&
        Objects.equals(this.vnfConfigurableProperties, changeVnfFlavourRequest.vnfConfigurableProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newFlavourId, instantiationLevelId, extVirtualLinks, extManagedVirtualLinks, vimConnectionInfo, additionalParams, extensions, vnfConfigurableProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeVnfFlavourRequest {\n");
    
    sb.append("    newFlavourId: ").append(toIndentedString(newFlavourId)).append("\n");
    sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
    sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
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
