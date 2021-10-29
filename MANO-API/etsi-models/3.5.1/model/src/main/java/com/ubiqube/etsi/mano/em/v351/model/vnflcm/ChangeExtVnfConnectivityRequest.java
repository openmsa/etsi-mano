package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ExtVirtualLinkData;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
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
        Objects.equals(this.additionalParams, changeExtVnfConnectivityRequest.additionalParams);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extVirtualLinks, additionalParams);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChangeExtVnfConnectivityRequest {\n");
    
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
