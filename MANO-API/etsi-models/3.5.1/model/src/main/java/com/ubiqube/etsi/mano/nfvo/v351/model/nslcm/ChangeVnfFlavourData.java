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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The type represents the information that is requested to be changed deployment flavor for an existing VNF instance. NOTE 1: The indication of externally-managed internal VLs is needed in case networks have been pre-configured for use with certain VNFs, for instance to ensure that these networks have certain properties such as security or acceleration features, or to address particular network topologies. The present document assumes that externally-managed internal VLs are managed by the NFVO and created towards the VIM. NOTE 2: It is possible to have several ExtManagedVirtualLinkData for the same VNF internal VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkData corresponding to the same VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 6.5.3.27). 
 */
@Schema(description = "The type represents the information that is requested to be changed deployment flavor for an existing VNF instance. NOTE 1: The indication of externally-managed internal VLs is needed in case networks have been pre-configured for use with certain VNFs, for instance to ensure that these networks have certain properties such as security or acceleration features, or to address particular network topologies. The present document assumes that externally-managed internal VLs are managed by the NFVO and created towards the VIM. NOTE 2: It is possible to have several ExtManagedVirtualLinkData for the same VNF internal VL in case of a multi-site VNF spanning several VIMs. The set of ExtManagedVirtualLinkData corresponding to the same VNF internal VL shall indicate so by referencing to the same VnfVirtualLinkDesc and externally-managed multi-site VL instance (refer to clause 6.5.3.27). ")
@Validated


public class ChangeVnfFlavourData   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

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

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  public ChangeVnfFlavourData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public ChangeVnfFlavourData newFlavourId(String newFlavourId) {
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

  public ChangeVnfFlavourData instantiationLevelId(String instantiationLevelId) {
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

  public ChangeVnfFlavourData extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public ChangeVnfFlavourData addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    if (this.extVirtualLinks == null) {
      this.extVirtualLinks = new ArrayList<>();
    }
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to connect the VNF to. 
   * @return extVirtualLinks
   **/
  @Schema(description = "Information about external VLs to connect the VNF to. ")
      @Valid
    public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public ChangeVnfFlavourData extManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
    return this;
  }

  public ChangeVnfFlavourData addExtManagedVirtualLinksItem(ExtManagedVirtualLinkData extManagedVirtualLinksItem) {
    if (this.extManagedVirtualLinks == null) {
      this.extManagedVirtualLinks = new ArrayList<>();
    }
    this.extManagedVirtualLinks.add(extManagedVirtualLinksItem);
    return this;
  }

  /**
   * information about internal VLs that are managed by NFVO. See note 1 and note 2. 
   * @return extManagedVirtualLinks
   **/
  @Schema(description = "information about internal VLs that are managed by NFVO. See note 1 and note 2. ")
      @Valid
    public List<ExtManagedVirtualLinkData> getExtManagedVirtualLinks() {
    return extManagedVirtualLinks;
  }

  public void setExtManagedVirtualLinks(List<ExtManagedVirtualLinkData> extManagedVirtualLinks) {
    this.extManagedVirtualLinks = extManagedVirtualLinks;
  }

  public ChangeVnfFlavourData additionalParams(KeyValuePairs additionalParams) {
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

  public ChangeVnfFlavourData extensions(KeyValuePairs extensions) {
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

  public ChangeVnfFlavourData vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
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
    ChangeVnfFlavourData changeVnfFlavourData = (ChangeVnfFlavourData) o;
    return Objects.equals(this.vnfInstanceId, changeVnfFlavourData.vnfInstanceId) &&
        Objects.equals(this.newFlavourId, changeVnfFlavourData.newFlavourId) &&
        Objects.equals(this.instantiationLevelId, changeVnfFlavourData.instantiationLevelId) &&
        Objects.equals(this.extVirtualLinks, changeVnfFlavourData.extVirtualLinks) &&
        Objects.equals(this.extManagedVirtualLinks, changeVnfFlavourData.extManagedVirtualLinks) &&
        Objects.equals(this.additionalParams, changeVnfFlavourData.additionalParams) &&
        Objects.equals(this.extensions, changeVnfFlavourData.extensions) &&
        Objects.equals(this.vnfConfigurableProperties, changeVnfFlavourData.vnfConfigurableProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, newFlavourId, instantiationLevelId, extVirtualLinks, extManagedVirtualLinks, additionalParams, extensions, vnfConfigurableProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeVnfFlavourData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    newFlavourId: ").append(toIndentedString(newFlavourId)).append("\n");
    sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
    sb.append("    extManagedVirtualLinks: ").append(toIndentedString(extManagedVirtualLinks)).append("\n");
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
