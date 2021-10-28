package com.ubiqube.etsi.mano.vnfm.v351.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents parameters to connect to a VIM for managing the resources of a VNF instance. * NOTE 1: If applicable, this attribute also provides information about the resourceGroupIds           that are accessible using a particular set of credentials. See definition of           \&quot;resourceGroupId\&quot; in clause 9.5.3.3. * NOTE 2: Once the connectivity between VNFM and VIM is provided through a secure connection over           HTTP Secure (HTTP over SSL/TLS), and the connection might also be established through a VPN           (for example TLS-based VPN tunnelling) for site-to-site connection, the \&quot;accessInfo\&quot; JSON data           structure, and the sensitive data related information (\&quot;username\&quot;/\&quot;password\&quot; as required properties           for authentication purpose), will be transmitted as plain text through a TLS tunnel without additional           encoding/encryption before transmitting it, making the sensitive data visible to the endpoint.           The base64 encoded certificates are only used by the VNFM to verify the authenticity of the           interface endpoint of the VIM. 
 */
@Schema(description = "This type represents parameters to connect to a VIM for managing the resources of a VNF instance. * NOTE 1: If applicable, this attribute also provides information about the resourceGroupIds           that are accessible using a particular set of credentials. See definition of           \"resourceGroupId\" in clause 9.5.3.3. * NOTE 2: Once the connectivity between VNFM and VIM is provided through a secure connection over           HTTP Secure (HTTP over SSL/TLS), and the connection might also be established through a VPN           (for example TLS-based VPN tunnelling) for site-to-site connection, the \"accessInfo\" JSON data           structure, and the sensitive data related information (\"username\"/\"password\" as required properties           for authentication purpose), will be transmitted as plain text through a TLS tunnel without additional           encoding/encryption before transmitting it, making the sensitive data visible to the endpoint.           The base64 encoded certificates are only used by the VNFM to verify the authenticity of the           interface endpoint of the VIM. ")
@Validated


public class VimConnectionInfo   {
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

  public VimConnectionInfo vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * Get vimId
   * @return vimId
   **/
  @Schema(description = "")
  
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
  @Schema(required = true, description = "Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM. The set of permitted values is expected to change over time as new types or versions of VIMs become available. The ETSI NFV registry of VIM-related information provides access to information about VimConnectionInfo definitions for various VIM types. The structure of the registry is defined in Annex C of SOL003. ")
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
   * Get interfaceInfo
   * @return interfaceInfo
   **/
  @Schema(description = "")
  
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
   * Get accessInfo
   * @return accessInfo
   **/
  @Schema(description = "")
  
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
   * Get extra
   * @return extra
   **/
  @Schema(description = "")
  
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
    return Objects.equals(this.vimId, vimConnectionInfo.vimId) &&
        Objects.equals(this.vimType, vimConnectionInfo.vimType) &&
        Objects.equals(this.interfaceInfo, vimConnectionInfo.interfaceInfo) &&
        Objects.equals(this.accessInfo, vimConnectionInfo.accessInfo) &&
        Objects.equals(this.extra, vimConnectionInfo.extra);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vimId, vimType, interfaceInfo, accessInfo, extra);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimConnectionInfo {\n");
    
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
