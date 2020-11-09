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
package com.ubiqube.etsi.mano.nfvo.v271.model.lcmgrant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v271.model.lcmgrant.KeyValuePairs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents parameters to connect to a VIM for managing the resources of a VNF instance. This structure is used to convey VIM-related parameters over the Or-Vnfm interface. Additional parameters for a VIM may be configured into the VNFM by means outside the scope of the present document, and bound to the identifier of that VIM. 
 */
@ApiModel(description = "This type represents parameters to connect to a VIM for managing the resources of a VNF instance. This structure is used to convey VIM-related parameters over the Or-Vnfm interface. Additional parameters for a VIM may be configured into the VNFM by means outside the scope of the present document, and bound to the identifier of that VIM. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T17:47:42.824+01:00")

public class VimConnectionInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vimId")
  private String vimId = null;

  @JsonProperty("vimType")
  private String vimType = null;

  @JsonProperty("interfaceInfo")
  private KeyValuePairs interfaceInfo = null;

  @JsonProperty("accessInfo")
  private KeyValuePairs accessInfo = null;

  @JsonProperty("extra")
  private KeyValuePairs extra = null;

  public VimConnectionInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the VIM Connection. This identifier is managed by the NFVO. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "The identifier of the VIM Connection. This identifier is managed by the NFVO. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VimConnectionInfo vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * The identifier of the VIM instance. This identifier is managed by the NFVO. Shall be present to address additional information about the VIM if such information has been configured into the VNFM by means outside the scope of the present document, and should be absent otherwise. 
   * @return vimId
  **/
  @ApiModelProperty(value = "The identifier of the VIM instance. This identifier is managed by the NFVO. Shall be present to address additional information about the VIM if such information has been configured into the VNFM by means outside the scope of the present document, and should be absent otherwise. ")


  public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public VimConnectionInfo vimType(String vimType) {
    this.vimType = vimType;
    return this;
  }

  /**
   * Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM. The set of permitted values is expected to change over time as new types or versions of VIMs become available. The ETSI NFV registry of VIM-related information provides access to information about VimConnectionInfo definitions for various VIM types. The structure of the registry is defined in Annex C of SOL003. 
   * @return vimType
  **/
  @ApiModelProperty(required = true, value = "Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM. The set of permitted values is expected to change over time as new types or versions of VIMs become available. The ETSI NFV registry of VIM-related information provides access to information about VimConnectionInfo definitions for various VIM types. The structure of the registry is defined in Annex C of SOL003. ")
  @NotNull


  public String getVimType() {
    return vimType;
  }

  public void setVimType(String vimType) {
    this.vimType = vimType;
  }

  public VimConnectionInfo interfaceInfo(KeyValuePairs interfaceInfo) {
    this.interfaceInfo = interfaceInfo;
    return this;
  }

  /**
   * Information about the interface or interfaces to the VIM, if applicable, such as the URI of an interface endpoint to communicate with the VIM. The applicable keys are dependent on the content of vimType. Alternatively, such information may have been configured into the VNFM and bound to the vimId. 
   * @return interfaceInfo
  **/
  @ApiModelProperty(value = "Information about the interface or interfaces to the VIM, if applicable, such as the URI of an interface endpoint to communicate with the VIM. The applicable keys are dependent on the content of vimType. Alternatively, such information may have been configured into the VNFM and bound to the vimId. ")

  @Valid

  public KeyValuePairs getInterfaceInfo() {
    return interfaceInfo;
  }

  public void setInterfaceInfo(KeyValuePairs interfaceInfo) {
    this.interfaceInfo = interfaceInfo;
  }

  public VimConnectionInfo accessInfo(KeyValuePairs accessInfo) {
    this.accessInfo = accessInfo;
    return this;
  }

  /**
   * Authentication credentials for accessing the VIM, and other access-related information such as tenants or infrastructure resource groups (see note). The applicable keys are dependent on the content of vimType. If the VimConnectionInfo structure is part of an HTTP response payload body, sensitive attributes that are children of this attributes (such as passwords) shall not be included. If the VimConnectionInfo structure is part of an HTTP request payload body, sensitive attributes that are children of this attribute (such as passwords) shall be present if they have not been provisioned out of band. If applicable, this attribute also provides information about the resourceGroupIds that are accessible using a particular set of credentials. See definition of \"resourceGroupId\" in clause 9.5.3.3. Once the connectivity between VNFM and VIM is provided through a secure connection over HTTP Secure (HTTP over SSL/TLS), and the connection might also be established through a VPN (for example TLS-based VPN tunnelling) for site-to-site connection, the \"accessInfo\" JSON data structure, and the sensitive data related information (\"username\"/\"password\" as required properties for authentication purpose), will be transmitted as plain text through a TLS tunnel without additional encoding/encryption before transmitting it, making the sensitive data visible to the endpoint. The base64 encoded certificates are only used by the VNFM to verify the authenticity of the interface endpoint of the VIM. 
   * @return accessInfo
  **/
  @ApiModelProperty(value = "Authentication credentials for accessing the VIM, and other access-related information such as tenants or infrastructure resource groups (see note). The applicable keys are dependent on the content of vimType. If the VimConnectionInfo structure is part of an HTTP response payload body, sensitive attributes that are children of this attributes (such as passwords) shall not be included. If the VimConnectionInfo structure is part of an HTTP request payload body, sensitive attributes that are children of this attribute (such as passwords) shall be present if they have not been provisioned out of band. If applicable, this attribute also provides information about the resourceGroupIds that are accessible using a particular set of credentials. See definition of \"resourceGroupId\" in clause 9.5.3.3. Once the connectivity between VNFM and VIM is provided through a secure connection over HTTP Secure (HTTP over SSL/TLS), and the connection might also be established through a VPN (for example TLS-based VPN tunnelling) for site-to-site connection, the \"accessInfo\" JSON data structure, and the sensitive data related information (\"username\"/\"password\" as required properties for authentication purpose), will be transmitted as plain text through a TLS tunnel without additional encoding/encryption before transmitting it, making the sensitive data visible to the endpoint. The base64 encoded certificates are only used by the VNFM to verify the authenticity of the interface endpoint of the VIM. ")

  @Valid

  public KeyValuePairs getAccessInfo() {
    return accessInfo;
  }

  public void setAccessInfo(KeyValuePairs accessInfo) {
    this.accessInfo = accessInfo;
  }

  public VimConnectionInfo extra(KeyValuePairs extra) {
    this.extra = extra;
    return this;
  }

  /**
   * VIM type specific additional information. The applicable structure, and whether or not this attribute is available, is dependent on the content of vimType. 
   * @return extra
  **/
  @ApiModelProperty(value = "VIM type specific additional information. The applicable structure, and whether or not this attribute is available, is dependent on the content of vimType. ")

  @Valid

  public KeyValuePairs getExtra() {
    return extra;
  }

  public void setExtra(KeyValuePairs extra) {
    this.extra = extra;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VimConnectionInfo vimConnectionInfo = (VimConnectionInfo) o;
    return Objects.equals(this.id, vimConnectionInfo.id) &&
        Objects.equals(this.vimId, vimConnectionInfo.vimId) &&
        Objects.equals(this.vimType, vimConnectionInfo.vimType) &&
        Objects.equals(this.interfaceInfo, vimConnectionInfo.interfaceInfo) &&
        Objects.equals(this.accessInfo, vimConnectionInfo.accessInfo) &&
        Objects.equals(this.extra, vimConnectionInfo.extra);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vimId, vimType, interfaceInfo, accessInfo, extra);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimConnectionInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    vimType: ").append(toIndentedString(vimType)).append("\n");
    sb.append("    interfaceInfo: ").append(toIndentedString(interfaceInfo)).append("\n");
    sb.append("    accessInfo: ").append(toIndentedString(accessInfo)).append("\n");
    sb.append("    extra: ").append(toIndentedString(extra)).append("\n");
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

