package com.ubiqube.etsi.mano.vnfm.v351.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.ExtLinkPortData;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.VnfExtCpData;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an external VL. * NOTE: A link port is not needed for an external CP instance that exposes a VIP CP in the following cases:       1 For a VIP CP directly exposed as extCP:         1.1 No dedicated IP address is allocated as VIP address, as indicated in the VNFD.         1.2 A dedicated IP addresss is allocated as VIP address, but the NFVO indicates that no port               is needed (createExtLinkPort in VnfExtCpconfig set to false).       2 For a VIP CP exposed as extCP via a floating IP address:         2.1 No dedicated IP address is allocated as VIP address, as indicated in the VNFD, and the VNFC               CP associated to the VIP CP is also exposed via a floating IP addresss. 
 */
@Schema(description = "This type represents an external VL. * NOTE: A link port is not needed for an external CP instance that exposes a VIP CP in the following cases:       1 For a VIP CP directly exposed as extCP:         1.1 No dedicated IP address is allocated as VIP address, as indicated in the VNFD.         1.2 A dedicated IP addresss is allocated as VIP address, but the NFVO indicates that no port               is needed (createExtLinkPort in VnfExtCpconfig set to false).       2 For a VIP CP exposed as extCP via a floating IP address:         2.1 No dedicated IP address is allocated as VIP address, as indicated in the VNFD, and the VNFC               CP associated to the VIP CP is also exposed via a floating IP addresss. ")
@Validated


public class ExtVirtualLinkData   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("extCps")
  @Valid
  private List<VnfExtCpData> extCps = new ArrayList<>();

  @JsonProperty("extLinkPorts")
  @Valid
  private List<ExtLinkPortData> extLinkPorts = null;

  public ExtVirtualLinkData id(String id) {
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

  public ExtVirtualLinkData vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Get vimConnectionId
   * @return vimConnectionId
   **/
  @Schema(description = "")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public ExtVirtualLinkData resourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
    return this;
  }

  /**
   * Get resourceProviderId
   * @return resourceProviderId
   **/
  @Schema(description = "")
  
    public String getResourceProviderId() {
    return resourceProviderId;
  }

  public void setResourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
  }

  public ExtVirtualLinkData resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * Get resourceId
   * @return resourceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public ExtVirtualLinkData extCps(List<VnfExtCpData> extCps) {
    this.extCps = extCps;
    return this;
  }

  public ExtVirtualLinkData addExtCpsItem(VnfExtCpData extCpsItem) {
    this.extCps.add(extCpsItem);
    return this;
  }

  /**
   * External CPs of the VNF to be connected to this external VL. Entries in the list of external CP data that are unchanged need not be supplied if the ExtVirtualLinkData structure is part of a request or response that modifies the external connectivity. 
   * @return extCps
   **/
  @Schema(required = true, description = "External CPs of the VNF to be connected to this external VL. Entries in the list of external CP data that are unchanged need not be supplied if the ExtVirtualLinkData structure is part of a request or response that modifies the external connectivity. ")
      @NotNull
    @Valid
    public List<VnfExtCpData> getExtCps() {
    return extCps;
  }

  public void setExtCps(List<VnfExtCpData> extCps) {
    this.extCps = extCps;
  }

  public ExtVirtualLinkData extLinkPorts(List<ExtLinkPortData> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
    return this;
  }

  public ExtVirtualLinkData addExtLinkPortsItem(ExtLinkPortData extLinkPortsItem) {
    if (this.extLinkPorts == null) {
      this.extLinkPorts = new ArrayList<>();
    }
    this.extLinkPorts.add(extLinkPortsItem);
    return this;
  }

  /**
   * Externally provided link ports to be used to connect external connection points to this external VL. If this attribute is not present, the VNFM shall create the link ports on the external VL unless the extCp exposes a VIP CP and a link port is not needed for it based on the conditions defined below. See note. 
   * @return extLinkPorts
   **/
  @Schema(description = "Externally provided link ports to be used to connect external connection points to this external VL. If this attribute is not present, the VNFM shall create the link ports on the external VL unless the extCp exposes a VIP CP and a link port is not needed for it based on the conditions defined below. See note. ")
      @Valid
    public List<ExtLinkPortData> getExtLinkPorts() {
    return extLinkPorts;
  }

  public void setExtLinkPorts(List<ExtLinkPortData> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtVirtualLinkData extVirtualLinkData = (ExtVirtualLinkData) o;
    return Objects.equals(this.id, extVirtualLinkData.id) &&
        Objects.equals(this.vimConnectionId, extVirtualLinkData.vimConnectionId) &&
        Objects.equals(this.resourceProviderId, extVirtualLinkData.resourceProviderId) &&
        Objects.equals(this.resourceId, extVirtualLinkData.resourceId) &&
        Objects.equals(this.extCps, extVirtualLinkData.extCps) &&
        Objects.equals(this.extLinkPorts, extVirtualLinkData.extLinkPorts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vimConnectionId, resourceProviderId, resourceId, extCps, extLinkPorts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtVirtualLinkData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    extCps: ").append(toIndentedString(extCps)).append("\n");
    sb.append("    extLinkPorts: ").append(toIndentedString(extLinkPorts)).append("\n");
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
