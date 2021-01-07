package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.grant.KeyValuePairs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VimConnectionInfo
 */
@Validated
public class VimConnectionInfo   {
  @JsonProperty("accessInfo")
  private KeyValuePairs accessInfo = null;

  @JsonProperty("extra")
  private KeyValuePairs extra = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("interfaceInfo")
  private KeyValuePairs interfaceInfo = null;

  @JsonProperty("vimId")
  private String vimId = null;

  @JsonProperty("vimType")
  private String vimType = null;

  public VimConnectionInfo accessInfo(KeyValuePairs accessInfo) {
    this.accessInfo = accessInfo;
    return this;
  }

  /**
   * Get accessInfo
   * @return accessInfo
  **/
  @ApiModelProperty(value = "")
  
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
  @ApiModelProperty(value = "")
  
    @Valid
    public KeyValuePairs getExtra() {
    return extra;
  }

  public void setExtra(KeyValuePairs extra) {
    this.extra = extra;
  }

  public VimConnectionInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the VIM Connection. This identifier is managed by the MEO.
   * @return id
  **/
  @ApiModelProperty(required = true, value = "The identifier of the VIM Connection. This identifier is managed by the MEO.")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VimConnectionInfo interfaceInfo(KeyValuePairs interfaceInfo) {
    this.interfaceInfo = interfaceInfo;
    return this;
  }

  /**
   * Get interfaceInfo
   * @return interfaceInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public KeyValuePairs getInterfaceInfo() {
    return interfaceInfo;
  }

  public void setInterfaceInfo(KeyValuePairs interfaceInfo) {
    this.interfaceInfo = interfaceInfo;
  }

  public VimConnectionInfo vimId(String vimId) {
    this.vimId = vimId;
    return this;
  }

  /**
   * The identifier of the VIM instance. This identifier is managed by the MEO.Shall be present to address additional information about the VIM if such information has been configured into the MEPM by means outside the scope of the present document, and should be absent otherwise.
   * @return vimId
  **/
  @ApiModelProperty(value = "The identifier of the VIM instance. This identifier is managed by the MEO.Shall be present to address additional information about the VIM if such information has been configured into the MEPM by means outside the scope of the present document, and should be absent otherwise.")
  
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
   * Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM.The set of permitted values is expected to change over time as new types or versions of VIMs become available. 
   * @return vimType
  **/
  @ApiModelProperty(required = true, value = "Discriminator for the different types of the VIM information. The value of this attribute determines the structure of the \"interfaceInfo\" and \"accessInfo\" attributes, based on the type of the VIM.The set of permitted values is expected to change over time as new types or versions of VIMs become available. ")
      @NotNull

    public String getVimType() {
    return vimType;
  }

  public void setVimType(String vimType) {
    this.vimType = vimType;
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
    return Objects.equals(this.accessInfo, vimConnectionInfo.accessInfo) &&
        Objects.equals(this.extra, vimConnectionInfo.extra) &&
        Objects.equals(this.id, vimConnectionInfo.id) &&
        Objects.equals(this.interfaceInfo, vimConnectionInfo.interfaceInfo) &&
        Objects.equals(this.vimId, vimConnectionInfo.vimId) &&
        Objects.equals(this.vimType, vimConnectionInfo.vimType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessInfo, extra, id, interfaceInfo, vimId, vimType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimConnectionInfo {\n");
    
    sb.append("    accessInfo: ").append(toIndentedString(accessInfo)).append("\n");
    sb.append("    extra: ").append(toIndentedString(extra)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    interfaceInfo: ").append(toIndentedString(interfaceInfo)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    vimType: ").append(toIndentedString(vimType)).append("\n");
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
