package com.ubiqube.etsi.mano.nfvo.v331.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type describes the information invoked by the NFVO to change the external VNF connectivity information maintained by the VNFM. The types of changes that this operation supports are: 1) Disconnect the external CPs that are connected to a particular external VL, and connect them to a different    external VL. 2) Disconnect and delete external CPs that are connected to a particular external VL and that represent subports    in a trunk, i.e. CP instances that are created from external CPDs that have trunk mode configured according to    clause 7.1.6.3 in ETSI GS NFV-IFA 011. If the parent port is exposed as an \&quot;extCp\&quot;, the VNFM shall ensure that    the parent port is not deleted. If the parent port is exposed as an \&quot;extCp\&quot; and there are other subports connected,    the VNFM shall ensure that the parent port is not disconnected, unless it is reconnected to a different external    VL in the same operation. 3) Change the connectivity parameters of the existing external CPs, including changing addresses. NOTE: Depending on the capabilities of the underlying VIM resources, certain changes (e.g. modifying the IP address       assignment) might not be supported without deleting the resource and creating another one with the modified       configuration.  4) Create new CPs that represent supports in a trunk, i.e. CP instances that are created from external CPDs that    have trunk mode configured according to clause 7.1.6.3 in ETSI GS NFV-IFA 011, and connect them to a particular    external VL. Creation of the parent port with this operation is not supported. This type shall comply with the    provisions defined in Table 6.5.3.33-1. 
 */
@Schema(description = "This type describes the information invoked by the NFVO to change the external VNF connectivity information maintained by the VNFM. The types of changes that this operation supports are: 1) Disconnect the external CPs that are connected to a particular external VL, and connect them to a different    external VL. 2) Disconnect and delete external CPs that are connected to a particular external VL and that represent subports    in a trunk, i.e. CP instances that are created from external CPDs that have trunk mode configured according to    clause 7.1.6.3 in ETSI GS NFV-IFA 011. If the parent port is exposed as an \"extCp\", the VNFM shall ensure that    the parent port is not deleted. If the parent port is exposed as an \"extCp\" and there are other subports connected,    the VNFM shall ensure that the parent port is not disconnected, unless it is reconnected to a different external    VL in the same operation. 3) Change the connectivity parameters of the existing external CPs, including changing addresses. NOTE: Depending on the capabilities of the underlying VIM resources, certain changes (e.g. modifying the IP address       assignment) might not be supported without deleting the resource and creating another one with the modified       configuration.  4) Create new CPs that represent supports in a trunk, i.e. CP instances that are created from external CPDs that    have trunk mode configured according to clause 7.1.6.3 in ETSI GS NFV-IFA 011, and connect them to a particular    external VL. Creation of the parent port with this operation is not supported. This type shall comply with the    provisions defined in Table 6.5.3.33-1. ")
@Validated


public class ChangeExtVnfConnectivityData   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("extVirtualLinks")
  @Valid
  private List<ExtVirtualLinkData> extVirtualLinks = new ArrayList<>();

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  public ChangeExtVnfConnectivityData vnfInstanceId(String vnfInstanceId) {
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

  public ChangeExtVnfConnectivityData extVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
    return this;
  }

  public ChangeExtVnfConnectivityData addExtVirtualLinksItem(ExtVirtualLinkData extVirtualLinksItem) {
    this.extVirtualLinks.add(extVirtualLinksItem);
    return this;
  }

  /**
   * Information about external VLs to change (e.g. connect the VNF to). 
   * @return extVirtualLinks
   **/
  @Schema(required = true, description = "Information about external VLs to change (e.g. connect the VNF to). ")
      @NotNull
    @Valid
    public List<ExtVirtualLinkData> getExtVirtualLinks() {
    return extVirtualLinks;
  }

  public void setExtVirtualLinks(List<ExtVirtualLinkData> extVirtualLinks) {
    this.extVirtualLinks = extVirtualLinks;
  }

  public ChangeExtVnfConnectivityData additionalParams(KeyValuePairs additionalParams) {
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
    ChangeExtVnfConnectivityData changeExtVnfConnectivityData = (ChangeExtVnfConnectivityData) o;
    return Objects.equals(this.vnfInstanceId, changeExtVnfConnectivityData.vnfInstanceId) &&
        Objects.equals(this.extVirtualLinks, changeExtVnfConnectivityData.extVirtualLinks) &&
        Objects.equals(this.additionalParams, changeExtVnfConnectivityData.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, extVirtualLinks, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeExtVnfConnectivityData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    extVirtualLinks: ").append(toIndentedString(extVirtualLinks)).append("\n");
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
