package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.GrantRequestLinks;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.GrantedLcmOperationType;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.PlacementConstraint;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.ResourceDefinition;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.VimConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a grant request. 
 */
@Schema(description = "This type represents a grant request. ")
@Validated


public class GrantRequest   {
  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("vnfLcmOpOccId")
  private String vnfLcmOpOccId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("dstVnfdId")
  private String dstVnfdId = null;

  @JsonProperty("flavourId")
  private String flavourId = null;

  @JsonProperty("operation")
  private GrantedLcmOperationType operation = null;

  @JsonProperty("isAutomaticInvocation")
  private Boolean isAutomaticInvocation = null;

  @JsonProperty("instantiationLevelId")
  private String instantiationLevelId = null;

  @JsonProperty("addResources")
  @Valid
  private List<ResourceDefinition> addResources = null;

  @JsonProperty("tempResources")
  @Valid
  private List<ResourceDefinition> tempResources = null;

  @JsonProperty("removeResources")
  @Valid
  private List<ResourceDefinition> removeResources = null;

  @JsonProperty("updateResources")
  @Valid
  private List<ResourceDefinition> updateResources = null;

  @JsonProperty("placementConstraints")
  @Valid
  private List<PlacementConstraint> placementConstraints = null;

  @JsonProperty("vimConstraints")
  @Valid
  private List<VimConstraint> vimConstraints = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("_links")
  private GrantRequestLinks _links = null;

  public GrantRequest vnfInstanceId(String vnfInstanceId) {
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

  public GrantRequest vnfLcmOpOccId(String vnfLcmOpOccId) {
    this.vnfLcmOpOccId = vnfLcmOpOccId;
    return this;
  }

  /**
   * Get vnfLcmOpOccId
   * @return vnfLcmOpOccId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfLcmOpOccId() {
    return vnfLcmOpOccId;
  }

  public void setVnfLcmOpOccId(String vnfLcmOpOccId) {
    this.vnfLcmOpOccId = vnfLcmOpOccId;
  }

  public GrantRequest vnfdId(String vnfdId) {
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

  public GrantRequest dstVnfdId(String dstVnfdId) {
    this.dstVnfdId = dstVnfdId;
    return this;
  }

  /**
   * Get dstVnfdId
   * @return dstVnfdId
   **/
  @Schema(description = "")
  
    public String getDstVnfdId() {
    return dstVnfdId;
  }

  public void setDstVnfdId(String dstVnfdId) {
    this.dstVnfdId = dstVnfdId;
  }

  public GrantRequest flavourId(String flavourId) {
    this.flavourId = flavourId;
    return this;
  }

  /**
   * Get flavourId
   * @return flavourId
   **/
  @Schema(description = "")
  
    public String getFlavourId() {
    return flavourId;
  }

  public void setFlavourId(String flavourId) {
    this.flavourId = flavourId;
  }

  public GrantRequest operation(GrantedLcmOperationType operation) {
    this.operation = operation;
    return this;
  }

  /**
   * Get operation
   * @return operation
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public GrantedLcmOperationType getOperation() {
    return operation;
  }

  public void setOperation(GrantedLcmOperationType operation) {
    this.operation = operation;
  }

  public GrantRequest isAutomaticInvocation(Boolean isAutomaticInvocation) {
    this.isAutomaticInvocation = isAutomaticInvocation;
    return this;
  }

  /**
   * Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise. 
   * @return isAutomaticInvocation
   **/
  @Schema(required = true, description = "Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise. ")
      @NotNull

    public Boolean isIsAutomaticInvocation() {
    return isAutomaticInvocation;
  }

  public void setIsAutomaticInvocation(Boolean isAutomaticInvocation) {
    this.isAutomaticInvocation = isAutomaticInvocation;
  }

  public GrantRequest instantiationLevelId(String instantiationLevelId) {
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

  public GrantRequest addResources(List<ResourceDefinition> addResources) {
    this.addResources = addResources;
    return this;
  }

  public GrantRequest addAddResourcesItem(ResourceDefinition addResourcesItem) {
    if (this.addResources == null) {
      this.addResources = new ArrayList<>();
    }
    this.addResources.add(addResourcesItem);
    return this;
  }

  /**
   * List of resource definitions in the VNFD for resources to be added by the LCM operation which is related to this grant request, with one entry per resource. If the granting request is for InstantiateVNF, either instantiationLevel or addResources shall be present. 
   * @return addResources
   **/
  @Schema(description = "List of resource definitions in the VNFD for resources to be added by the LCM operation which is related to this grant request, with one entry per resource. If the granting request is for InstantiateVNF, either instantiationLevel or addResources shall be present. ")
      @Valid
    public List<ResourceDefinition> getAddResources() {
    return addResources;
  }

  public void setAddResources(List<ResourceDefinition> addResources) {
    this.addResources = addResources;
  }

  public GrantRequest tempResources(List<ResourceDefinition> tempResources) {
    this.tempResources = tempResources;
    return this;
  }

  public GrantRequest addTempResourcesItem(ResourceDefinition tempResourcesItem) {
    if (this.tempResources == null) {
      this.tempResources = new ArrayList<>();
    }
    this.tempResources.add(tempResourcesItem);
    return this;
  }

  /**
   * List of resource definitions in the VNFD for resources to be temporarily instantiated during the runtime of the LCM operation which is related to this grant request, with one entry per resource. The NFVO will assume that the VNFM will be responsible to both allocate and release the temporary resource during the runtime of the LCM operation. This means, the resource can be allocated and consumed after the \"start\" notification for the LCM operation is sent by the VNFM, and the resource will be released before the \"result\" notification of the VNF LCM operation is sent by the VNFM. 
   * @return tempResources
   **/
  @Schema(description = "List of resource definitions in the VNFD for resources to be temporarily instantiated during the runtime of the LCM operation which is related to this grant request, with one entry per resource. The NFVO will assume that the VNFM will be responsible to both allocate and release the temporary resource during the runtime of the LCM operation. This means, the resource can be allocated and consumed after the \"start\" notification for the LCM operation is sent by the VNFM, and the resource will be released before the \"result\" notification of the VNF LCM operation is sent by the VNFM. ")
      @Valid
    public List<ResourceDefinition> getTempResources() {
    return tempResources;
  }

  public void setTempResources(List<ResourceDefinition> tempResources) {
    this.tempResources = tempResources;
  }

  public GrantRequest removeResources(List<ResourceDefinition> removeResources) {
    this.removeResources = removeResources;
    return this;
  }

  public GrantRequest addRemoveResourcesItem(ResourceDefinition removeResourcesItem) {
    if (this.removeResources == null) {
      this.removeResources = new ArrayList<>();
    }
    this.removeResources.add(removeResourcesItem);
    return this;
  }

  /**
   * Provides the definitions of resources to be removed by the LCM operation which is related to this grant request, with one entry per resource. 
   * @return removeResources
   **/
  @Schema(description = "Provides the definitions of resources to be removed by the LCM operation which is related to this grant request, with one entry per resource. ")
      @Valid
    public List<ResourceDefinition> getRemoveResources() {
    return removeResources;
  }

  public void setRemoveResources(List<ResourceDefinition> removeResources) {
    this.removeResources = removeResources;
  }

  public GrantRequest updateResources(List<ResourceDefinition> updateResources) {
    this.updateResources = updateResources;
    return this;
  }

  public GrantRequest addUpdateResourcesItem(ResourceDefinition updateResourcesItem) {
    if (this.updateResources == null) {
      this.updateResources = new ArrayList<>();
    }
    this.updateResources.add(updateResourcesItem);
    return this;
  }

  /**
   * Provides the definitions of resources to be modified by the LCM operation which is related to this grant request, with one entry per resource. 
   * @return updateResources
   **/
  @Schema(description = "Provides the definitions of resources to be modified by the LCM operation which is related to this grant request, with one entry per resource. ")
      @Valid
    public List<ResourceDefinition> getUpdateResources() {
    return updateResources;
  }

  public void setUpdateResources(List<ResourceDefinition> updateResources) {
    this.updateResources = updateResources;
  }

  public GrantRequest placementConstraints(List<PlacementConstraint> placementConstraints) {
    this.placementConstraints = placementConstraints;
    return this;
  }

  public GrantRequest addPlacementConstraintsItem(PlacementConstraint placementConstraintsItem) {
    if (this.placementConstraints == null) {
      this.placementConstraints = new ArrayList<>();
    }
    this.placementConstraints.add(placementConstraintsItem);
    return this;
  }

  /**
   * Placement constraints that the VNFM may send to the NFVO in order to influence the resource placement decision. If sent, the NFVO shall take the constraints into consideration when making resource placement decisions, and shall reject the grant if they cannot be honoured. The affinity/anti-affinity rules defined in the VNFD , and the placement constraints in the GrantVnfLifecycleOperation as defined in this clause should be conflict-free. In case of conflicts, the placement constraints in the GrantVnfLifecycleOperation shall take precedence. Passing constraints allows the VNFM or the lifecycle management scripts to influence resource placement decisions by the NFVO to ensure VNF properties such as performance or fault tolerance. If fallbackBestEffort is present in placement constraints and set to “true”, the NFVO shall process the Affinity/AntiAffinity constraint in a best effort manner, in which case, if specified resources cannot be allocated based on specified placement constraint, the NFVO looks for an alternate best effort placement for the specified resources to be granted. In the best effort anti-affinity case, the resources are expected to be spread optimally over all available instances of scope (e.g. zones), and in the best effort affinity case, they are expected to be distributed optimally over fewer possible instances of scope. 
   * @return placementConstraints
   **/
  @Schema(description = "Placement constraints that the VNFM may send to the NFVO in order to influence the resource placement decision. If sent, the NFVO shall take the constraints into consideration when making resource placement decisions, and shall reject the grant if they cannot be honoured. The affinity/anti-affinity rules defined in the VNFD , and the placement constraints in the GrantVnfLifecycleOperation as defined in this clause should be conflict-free. In case of conflicts, the placement constraints in the GrantVnfLifecycleOperation shall take precedence. Passing constraints allows the VNFM or the lifecycle management scripts to influence resource placement decisions by the NFVO to ensure VNF properties such as performance or fault tolerance. If fallbackBestEffort is present in placement constraints and set to “true”, the NFVO shall process the Affinity/AntiAffinity constraint in a best effort manner, in which case, if specified resources cannot be allocated based on specified placement constraint, the NFVO looks for an alternate best effort placement for the specified resources to be granted. In the best effort anti-affinity case, the resources are expected to be spread optimally over all available instances of scope (e.g. zones), and in the best effort affinity case, they are expected to be distributed optimally over fewer possible instances of scope. ")
      @Valid
    public List<PlacementConstraint> getPlacementConstraints() {
    return placementConstraints;
  }

  public void setPlacementConstraints(List<PlacementConstraint> placementConstraints) {
    this.placementConstraints = placementConstraints;
  }

  public GrantRequest vimConstraints(List<VimConstraint> vimConstraints) {
    this.vimConstraints = vimConstraints;
    return this;
  }

  public GrantRequest addVimConstraintsItem(VimConstraint vimConstraintsItem) {
    if (this.vimConstraints == null) {
      this.vimConstraints = new ArrayList<>();
    }
    this.vimConstraints.add(vimConstraintsItem);
    return this;
  }

  /**
   * Used by the VNFM to require that multiple resources are managed through the same VIM connection. If sent, the NFVO shall take the constraints into consideration when making VIM selection decisions, and shall reject the grant if they cannot be honoured. This attribute shall be supported if VNF-related Resource Management in direct mode is applicable. The applicability and further details of this attribute for indirect mode are left for future specification. 
   * @return vimConstraints
   **/
  @Schema(description = "Used by the VNFM to require that multiple resources are managed through the same VIM connection. If sent, the NFVO shall take the constraints into consideration when making VIM selection decisions, and shall reject the grant if they cannot be honoured. This attribute shall be supported if VNF-related Resource Management in direct mode is applicable. The applicability and further details of this attribute for indirect mode are left for future specification. ")
      @Valid
    public List<VimConstraint> getVimConstraints() {
    return vimConstraints;
  }

  public void setVimConstraints(List<VimConstraint> vimConstraints) {
    this.vimConstraints = vimConstraints;
  }

  public GrantRequest additionalParams(KeyValuePairs additionalParams) {
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

  public GrantRequest _links(GrantRequestLinks _links) {
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
    public GrantRequestLinks getLinks() {
    return _links;
  }

  public void setLinks(GrantRequestLinks _links) {
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
    GrantRequest grantRequest = (GrantRequest) o;
    return Objects.equals(this.vnfInstanceId, grantRequest.vnfInstanceId) &&
        Objects.equals(this.vnfLcmOpOccId, grantRequest.vnfLcmOpOccId) &&
        Objects.equals(this.vnfdId, grantRequest.vnfdId) &&
        Objects.equals(this.dstVnfdId, grantRequest.dstVnfdId) &&
        Objects.equals(this.flavourId, grantRequest.flavourId) &&
        Objects.equals(this.operation, grantRequest.operation) &&
        Objects.equals(this.isAutomaticInvocation, grantRequest.isAutomaticInvocation) &&
        Objects.equals(this.instantiationLevelId, grantRequest.instantiationLevelId) &&
        Objects.equals(this.addResources, grantRequest.addResources) &&
        Objects.equals(this.tempResources, grantRequest.tempResources) &&
        Objects.equals(this.removeResources, grantRequest.removeResources) &&
        Objects.equals(this.updateResources, grantRequest.updateResources) &&
        Objects.equals(this.placementConstraints, grantRequest.placementConstraints) &&
        Objects.equals(this.vimConstraints, grantRequest.vimConstraints) &&
        Objects.equals(this.additionalParams, grantRequest.additionalParams) &&
        Objects.equals(this._links, grantRequest._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceId, vnfLcmOpOccId, vnfdId, dstVnfdId, flavourId, operation, isAutomaticInvocation, instantiationLevelId, addResources, tempResources, removeResources, updateResources, placementConstraints, vimConstraints, additionalParams, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantRequest {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfLcmOpOccId: ").append(toIndentedString(vnfLcmOpOccId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    dstVnfdId: ").append(toIndentedString(dstVnfdId)).append("\n");
    sb.append("    flavourId: ").append(toIndentedString(flavourId)).append("\n");
    sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
    sb.append("    isAutomaticInvocation: ").append(toIndentedString(isAutomaticInvocation)).append("\n");
    sb.append("    instantiationLevelId: ").append(toIndentedString(instantiationLevelId)).append("\n");
    sb.append("    addResources: ").append(toIndentedString(addResources)).append("\n");
    sb.append("    tempResources: ").append(toIndentedString(tempResources)).append("\n");
    sb.append("    removeResources: ").append(toIndentedString(removeResources)).append("\n");
    sb.append("    updateResources: ").append(toIndentedString(updateResources)).append("\n");
    sb.append("    placementConstraints: ").append(toIndentedString(placementConstraints)).append("\n");
    sb.append("    vimConstraints: ").append(toIndentedString(vimConstraints)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
