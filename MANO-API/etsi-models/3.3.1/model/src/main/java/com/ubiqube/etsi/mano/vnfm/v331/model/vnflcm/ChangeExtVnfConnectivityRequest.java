package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VimConnectionInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the \&quot;Change external VNF connectivity\&quot; operation to modify the external connectivity of a VNF instance. 
 */
@Schema(description = "This type represents request parameters for the \"Change external VNF connectivity\" operation to modify the external connectivity of a VNF instance. ")
@Validated


public class ChangeExtVnfConnectivityRequest   {
  @JsonProperty("extVirtualLinks")
  @Valid
  private List<ExtVirtualLinkData> extVirtualLinks = new ArrayList<>();

  @JsonProperty("vimConnectionInfo")
  @Valid
  private Map<String, VimConnectionInfo> vimConnectionInfo = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public ChangeExtVnfConnectivityRequest extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public ChangeExtVnfConnectivityRequest addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to change (e.g. connect the VNF to). Entries in the list of external VLs that are unchanged need not be supplied as part of this request. 
   * @return extVirtualLinks
   **/
  @Schema(required = true, description = "Information about external VLs to change (e.g. connect the VNF to). Entries in the list of external VLs that are unchanged need not be supplied as part of this request. ")
      @NotNull
    @Valid
    public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public ChangeExtVnfConnectivityRequest vimConnectionInfo(Map<String, VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
    return this;
  }

  public ChangeExtVnfConnectivityRequest putVimConnectionInfoItem(String key, VimConnectionInfo vimConnectionInfoItem) {
    if (this.vimConnectionInfo == null) {
      this.vimConnectionInfo = new HashMap<>();
    }
    this.vimConnectionInfo.put(key, vimConnectionInfoItem);
    return this;
  }

  /**
   * Information about VIM connections to be used for managing the resources for the VNF instance, or refer to  external virtual links. This attribute shall only be supported and may be present if VNF-related resource  management in direct mode is applicable. The VNFM shall apply the content of this attribute to the  \"vimConnectionInfo\" attribute of \"VnfInstance\" according to the rules of JSON Merge Patch (see IETF RFC 7396). 
   * @return vimConnectionInfo
   **/
  @Schema(description = "Information about VIM connections to be used for managing the resources for the VNF instance, or refer to  external virtual links. This attribute shall only be supported and may be present if VNF-related resource  management in direct mode is applicable. The VNFM shall apply the content of this attribute to the  \"vimConnectionInfo\" attribute of \"VnfInstance\" according to the rules of JSON Merge Patch (see IETF RFC 7396). ")
      @Valid
    public Map<String, VimConnectionInfo> getVimConnectionInfo() {
    return vimConnectionInfo;
  }

  public void setVimConnectionInfo(Map<String, VimConnectionInfo> vimConnectionInfo) {
    this.vimConnectionInfo = vimConnectionInfo;
  }

  public ChangeExtVnfConnectivityRequest additionalParams(KeyValuePairs additionalParams) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChangeExtVnfConnectivityRequest changeExtVnfConnectivityRequest = (ChangeExtVnfConnectivityRequest) o;
    return Objects.equals(this.extVirtualLinks, changeExtVnfConnectivityRequest.extVirtualLinks) &&
        Objects.equals(this.vimConnectionInfo, changeExtVnfConnectivityRequest.vimConnectionInfo) &&
        Objects.equals(this.additionalParams, changeExtVnfConnectivityRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extVirtualLinks, vimConnectionInfo, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeExtVnfConnectivityRequest {\n");
    
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
    sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
