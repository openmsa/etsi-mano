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
package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VimConnectionInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents attribute modifications that were performed on an \&quot;Individual VNF instance\&quot; resource. The attributes that can be included consist of those requested to be modified explicitly in the \&quot;VnfInfoModificationRequest\&quot; data structure, and additional attributes of the \&quot;VnfInstance\&quot; data structure that were modified implicitly e.g. when modifying the referenced VNF package. 
 */
@Schema(description = "This type represents attribute modifications that were performed on an \"Individual VNF instance\" resource. The attributes that can be included consist of those requested to be modified explicitly in the \"VnfInfoModificationRequest\" data structure, and additional attributes of the \"VnfInstance\" data structure that were modified implicitly e.g. when modifying the referenced VNF package. ")
@Validated


public class VnfInfoModifications   {
  @JsonProperty("vnfInstanceName")
  private String vnfInstanceName = null;

  @JsonProperty("vnfInstanceDescription")
  private String vnfInstanceDescription = null;

  @JsonProperty("vnfConfigurableProperties")
  private KeyValuePairs vnfConfigurableProperties = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  @JsonProperty("extensions")
  private KeyValuePairs extensions = null;

  @JsonProperty("vimConnectionInfo")
  @Valid
  private Map<String, VimConnectionInfo> vimConnectionInfo = null;

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

  public VnfInfoModifications vnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
    return this;
  }

  /**
   * Get vnfInstanceName
   * @return vnfInstanceName
   **/
  @Schema(description = "")
  
    public String getVnfInstanceName() {
    return vnfInstanceName;
  }

  public void setVnfInstanceName(String vnfInstanceName) {
    this.vnfInstanceName = vnfInstanceName;
  }

  public VnfInfoModifications vnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
    return this;
  }

  /**
   * Get vnfInstanceDescription
   * @return vnfInstanceDescription
   **/
  @Schema(description = "")
  
    public String getVnfInstanceDescription() {
    return vnfInstanceDescription;
  }

  public void setVnfInstanceDescription(String vnfInstanceDescription) {
    this.vnfInstanceDescription = vnfInstanceDescription;
  }

  public VnfInfoModifications vnfConfigurableProperties(KeyValuePairs vnfConfigurableProperties) {
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

  public VnfInfoModifications metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }

  public VnfInfoModifications extensions(KeyValuePairs extensions) {
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

  public VnfInfoModifications vimConnectionInfo(Map<String, VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public VnfInfoModifications putVimConnectionInfoItem(String key, VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new HashMap<>();
    }
    this.vimConnectionInfo.put(key, vimConnectionInfoItem);
    return this;
  }

  /**
   * If present, this attribute signals modifications the \"vimConnectionInfo\"  attribute array in \"VnfInstance\". 
   * @return vimConnectionInfo
   **/
  @Schema(description = "If present, this attribute signals modifications the \"vimConnectionInfo\"  attribute array in \"VnfInstance\". ")
      @Valid
    public Map<String, VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(Map<String, VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public VnfInfoModifications vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(description = "")
  
    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfInfoModifications vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Get vnfProvider
   * @return vnfProvider
   **/
  @Schema(description = "")
  
    public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public VnfInfoModifications vnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
    return this;
  }

  /**
   * Get vnfProductName
   * @return vnfProductName
   **/
  @Schema(description = "")
  
    public String getVnfProductName() {
    return vnfProductName;
  }

  public void setVnfProductName(String vnfProductName) {
    this.vnfProductName = vnfProductName;
  }

  public VnfInfoModifications vnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
    return this;
  }

  /**
   * Get vnfSoftwareVersion
   * @return vnfSoftwareVersion
   **/
  @Schema(description = "")
  
    public String getVnfSoftwareVersion() {
    return vnfSoftwareVersion;
  }

  public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
    this.vnfSoftwareVersion = vnfSoftwareVersion;
  }

  public VnfInfoModifications vnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
    return this;
  }

  /**
   * Get vnfdVersion
   * @return vnfdVersion
   **/
  @Schema(description = "")
  
    public String getVnfdVersion() {
    return vnfdVersion;
  }

  public void setVnfdVersion(String vnfdVersion) {
    this.vnfdVersion = vnfdVersion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfInfoModifications vnfInfoModifications = (VnfInfoModifications) o;
    return Objects.equals(this.vnfInstanceName, vnfInfoModifications.vnfInstanceName) &&
        Objects.equals(this.vnfInstanceDescription, vnfInfoModifications.vnfInstanceDescription) &&
        Objects.equals(this.vnfConfigurableProperties, vnfInfoModifications.vnfConfigurableProperties) &&
        Objects.equals(this.metadata, vnfInfoModifications.metadata) &&
        Objects.equals(this.extensions, vnfInfoModifications.extensions) &&
        Objects.equals(this.vimConnectionInfo, vnfInfoModifications.vimConnectionInfo) &&
        Objects.equals(this.vnfdId, vnfInfoModifications.vnfdId) &&
        Objects.equals(this.vnfProvider, vnfInfoModifications.vnfProvider) &&
        Objects.equals(this.vnfProductName, vnfInfoModifications.vnfProductName) &&
        Objects.equals(this.vnfSoftwareVersion, vnfInfoModifications.vnfSoftwareVersion) &&
        Objects.equals(this.vnfdVersion, vnfInfoModifications.vnfdVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceName, vnfInstanceDescription, vnfConfigurableProperties, metadata, extensions, vimConnectionInfo, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfInfoModifications {\n");
    
    sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
    sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
    sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
    sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
    sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
    sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
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
