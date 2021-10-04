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
package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfInstanceLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF instance. 
 */
@Schema(description = "This type represents a VNF instance. ")
@Validated


public class VnfInstance   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfInstanceName")
  private String vnfInstanceName = null;

  @JsonProperty("vnfInstanceDescription")
  private String vnfInstanceDescription = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vnfProvider")
  private String vnfProvider = null;

  @JsonProperty("vnfProductName")
  private String vnfProductName = null;

  @JsonProperty("vnfSoftwareVersion")
  private String vnfSoftwareVersion = null;

  @JsonProperty("vnfdVersion")
  private String vnfdVersion = null;

  @JsonProperty("vnfConfigurableProperties")
  private Map<String, String> vnfConfigurableProperties = null;

  /**
   * The instantiation state of the VNF. 
   */
  public enum InstantiationStateEnum {
    NOT_INSTANTIATED("NOT_INSTANTIATED"),
    
    INSTANTIATED("INSTANTIATED");

    private String value;

    InstantiationStateEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static InstantiationStateEnum fromValue(String text) {
      for (InstantiationStateEnum b : InstantiationStateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("instantiationState")
  private InstantiationStateEnum instantiationState = null;

  @JsonProperty("instantiatedVnfInfo")
  private VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = null;

  @JsonProperty("metadata")
  private Map<String, String> metadata = null;

  @JsonProperty("extensions")
  private Map<String, String> extensions = null;

  @JsonProperty("_links")
  private VnfInstanceLinks _links = null;

  public VnfInstance id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfInstance vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

  /**
   * Name of the VNF instance. This attribute can be modified with the PATCH method. 
   * @return vnfInstanceName
   **/
  @Schema(description = "Name of the VNF instance. This attribute can be modified with the PATCH method. ")
  
    public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public VnfInstance vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

  /**
   * Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. 
   * @return vnfInstanceDescription
   **/
  @Schema(description = "Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. ")
  
    public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public VnfInstance vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfInstance vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Provider of the VNF and the VNFD. The value is copied from the VNFD. 
   * @return vnfProvider
   **/
  @Schema(required = true, description = "Provider of the VNF and the VNFD. The value is copied from the VNFD. ")
      @NotNull

    public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public VnfInstance vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

  /**
   * Name to identify the VNF Product. The value is copied from the VNFD. 
   * @return vnfProductName
   **/
  @Schema(required = true, description = "Name to identify the VNF Product. The value is copied from the VNFD. ")
      @NotNull

    public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public VnfInstance vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

  /**
   * Get vnfSoftwareVersion
   * @return vnfSoftwareVersion
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public VnfInstance vnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
    return this;
  }

  /**
   * Get vnfdVersion
   * @return vnfdVersion
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdVersion() {
    return vnfdVersion;
  }

  public void setVnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
  }

  public VnfInstance vnfConfigurableProperties(Map<String, String> vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
    return this;
  }

  /**
   * Get vnfConfigurableProperties
   * @return vnfConfigurableProperties
   **/
  @Schema(description = "")
  
    @Valid
    public Map<String, String> getVnfConfigurableProperties() {
    return vnfConfigurableProperties;
  }

  public void setVnfConfigurableProperties(Map<String, String> vnfConfigurableProperties) {
    this.vnfConfigurableProperties = vnfConfigurableProperties;
  }

  public VnfInstance instantiationState(InstantiationStateEnum instantiationState) {
    this.instantiationState = instantiationState;
    return this;
  }

  /**
   * The instantiation state of the VNF. 
   * @return instantiationState
   **/
  @Schema(required = true, description = "The instantiation state of the VNF. ")
      @NotNull

    public InstantiationStateEnum getInstantiationState() {
    return instantiationState;
  }

  public void setInstantiationState(InstantiationStateEnum instantiationState) {
    this.instantiationState = instantiationState;
  }

  public VnfInstance instantiatedVnfInfo(VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
    this.instantiatedVnfInfo = instantiatedVnfInfo;
    return this;
  }

  /**
   * Get instantiatedVnfInfo
   * @return instantiatedVnfInfo
   **/
  @Schema(description = "")
  
    @Valid
    public VnfInstanceInstantiatedVnfInfo getInstantiatedVnfInfo() {
    return instantiatedVnfInfo;
  }

  public void setInstantiatedVnfInfo(VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo) {
    this.instantiatedVnfInfo = instantiatedVnfInfo;
  }

  public VnfInstance metadata(Map<String, String> metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
    @Valid
    public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }

  public VnfInstance extensions(Map<String, String> extensions) {
    this.extensions = extensions;
    return this;
  }

  /**
   * Get extensions
   * @return extensions
   **/
  @Schema(description = "")
  
    @Valid
    public Map<String, String> getExtensions() {
    return extensions;
  }

  public void setExtensions(Map<String, String> extensions) {
    this.extensions = extensions;
  }

  public VnfInstance _links(VnfInstanceLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public VnfInstanceLinks getLinks() {
    return _links;
  }

  public void setLinks(VnfInstanceLinks _links) {
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
    VnfInstance vnfInstance = (VnfInstance) o;
    return Objects.equals(this.id, vnfInstance.id) &&
        Objects.equals(this.vnfInstanceName, vnfInstance.vnfInstanceName) &&
        Objects.equals(this.vnfInstanceDescription, vnfInstance.vnfInstanceDescription) &&
        Objects.equals(this.vnfdId, vnfInstance.vnfdId) &&
        Objects.equals(this.vnfProvider, vnfInstance.vnfProvider) &&
        Objects.equals(this.vnfProductName, vnfInstance.vnfProductName) &&
        Objects.equals(this.vnfSoftwareVersion, vnfInstance.vnfSoftwareVersion) &&
        Objects.equals(this.vnfdVersion, vnfInstance.vnfdVersion) &&
        Objects.equals(this.vnfConfigurableProperties, vnfInstance.vnfConfigurableProperties) &&
        Objects.equals(this.instantiationState, vnfInstance.instantiationState) &&
        Objects.equals(this.instantiatedVnfInfo, vnfInstance.instantiatedVnfInfo) &&
        Objects.equals(this.metadata, vnfInstance.metadata) &&
        Objects.equals(this.extensions, vnfInstance.extensions) &&
        Objects.equals(this._links, vnfInstance._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfInstanceName, vnfInstanceDescription, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion, vnfConfigurableProperties, instantiationState, instantiatedVnfInfo, metadata, extensions, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInstance {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
    sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
    sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
    sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
    sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
    sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
    sb.append("    instantiatedVnfInfo: ").append(toIndentedString(instantiatedVnfInfo)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
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
