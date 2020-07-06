package com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a grant request.
 */
@ApiModel(description = "This type represents a grant request. ")
@Validated


public class GrantRequest {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("vnfLcmOpOccId")
	private String vnfLcmOpOccId = null;

	@JsonProperty("vnfdId")
	private String vnfdId = null;

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
	private Map<String, String> additionalParams = null;

	@JsonProperty("_links")
	private GrantRequestLinks links = null;

	public GrantRequest vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Identifier of the VNF instance which this grant request is related to. Shall
	 * also be provided for VNFs that not yet exist but are planned to exist in the
	 * future, i.e. if the grant is requested for InstantiateVNF.
	 *
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF instance which this grant request is related to. Shall also be provided for VNFs that not yet exist but are planned to exist in the future, i.e. if the grant is requested for InstantiateVNF. ")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public GrantRequest vnfLcmOpOccId(final String vnfLcmOpOccId) {
		this.vnfLcmOpOccId = vnfLcmOpOccId;
		return this;
	}

	/**
	 * The identifier of the VNF lifecycle management operation occurrence
	 * associated to the GrantRequest.
	 *
	 * @return vnfLcmOpOccId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the VNF lifecycle management operation occurrence associated to the GrantRequest. ")
	@NotNull

	public String getVnfLcmOpOccId() {
		return vnfLcmOpOccId;
	}

	public void setVnfLcmOpOccId(final String vnfLcmOpOccId) {
		this.vnfLcmOpOccId = vnfLcmOpOccId;
	}

	public GrantRequest vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Identifier of the VNFD that defines the VNF for which the LCM operation is to
	 * be granted.
	 *
	 * @return vnfdId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNFD that defines the VNF for which the LCM operation is to be granted. ")
	@NotNull

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public GrantRequest flavourId(final String flavourId) {
		this.flavourId = flavourId;
		return this;
	}

	/**
	 * Identifier of the VNF deployment flavour of the VNFD that defines the VNF for
	 * which the LCM operation is to be granted. Shall be provided when
	 * instantiating the VNF or changing the deployment flavour of the VNF instance.
	 *
	 * @return flavourId
	 **/
	@ApiModelProperty(value = "Identifier of the VNF deployment flavour of the VNFD that defines the VNF for which the LCM operation is to be granted. Shall be provided when instantiating the VNF or changing the deployment flavour of the VNF instance. ")

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public GrantRequest operation(final GrantedLcmOperationType operation) {
		this.operation = operation;
		return this;
	}

	/**
	 * The lifecycle management operation for which granting is requested. The VNF
	 * LCM operations CreateVnfIdentifier, DeleteVnfIdentifier, QueryVnf and
	 * ModifyVnfInformation can be executed by the VNFM without requesting granting.
	 *
	 * @return operation
	 **/
	@ApiModelProperty(required = true, value = "The lifecycle management operation for which granting is requested. The VNF LCM operations CreateVnfIdentifier, DeleteVnfIdentifier, QueryVnf and ModifyVnfInformation can be executed by the VNFM without requesting granting. ")
	@NotNull

	@Valid

	public GrantedLcmOperationType getOperation() {
		return operation;
	}

	public void setOperation(final GrantedLcmOperationType operation) {
		this.operation = operation;
	}

	public GrantRequest isAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
		return this;
	}

	/**
	 * Set to true if this VNF LCM operation occurrence has been triggered by an
	 * automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel
	 * triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false
	 * otherwise.
	 *
	 * @return isAutomaticInvocation
	 **/
	@ApiModelProperty(required = true, value = "Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise. ")
	@NotNull

	public Boolean isAutomaticInvocation() {
		return isAutomaticInvocation;
	}

	public void setAutomaticInvocation(final Boolean isAutomaticInvocation) {
		this.isAutomaticInvocation = isAutomaticInvocation;
	}

	public GrantRequest instantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
		return this;
	}

	/**
	 * If operation=INSTANTIATE, the identifier of the instantiation level may be
	 * provided as an alternative way to define the resources to be added. This
	 * attribute shall only be used if operation=INSTANTIATE.
	 *
	 * @return instantiationLevelId
	 **/
	@ApiModelProperty(value = "If operation=INSTANTIATE, the identifier of the instantiation level may be provided as an alternative way to define the resources to be added. This attribute shall only be used if operation=INSTANTIATE. ")

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public GrantRequest addResources(final List<ResourceDefinition> addResources) {
		this.addResources = addResources;
		return this;
	}

	public GrantRequest addAddResourcesItem(final ResourceDefinition addResourcesItem) {
		if (this.addResources == null) {
			this.addResources = new ArrayList<>();
		}
		this.addResources.add(addResourcesItem);
		return this;
	}

	/**
	 * List of resource definitions in the VNFD for resources to be added by the LCM
	 * operation which is related to this grant request, with one entry per
	 * resource. If the granting request is for InstantiateVNF, either
	 * instantiationLevel or addResources shall be present.
	 *
	 * @return addResources
	 **/
	@ApiModelProperty(value = "List of resource definitions in the VNFD for resources to be added by the LCM operation which is related to this grant request, with one entry per resource. If the granting request is for InstantiateVNF, either instantiationLevel or addResources shall be present. ")

	@Valid

	public List<ResourceDefinition> getAddResources() {
		return addResources;
	}

	public void setAddResources(final List<ResourceDefinition> addResources) {
		this.addResources = addResources;
	}

	public GrantRequest tempResources(final List<ResourceDefinition> tempResources) {
		this.tempResources = tempResources;
		return this;
	}

	public GrantRequest addTempResourcesItem(final ResourceDefinition tempResourcesItem) {
		if (this.tempResources == null) {
			this.tempResources = new ArrayList<>();
		}
		this.tempResources.add(tempResourcesItem);
		return this;
	}

	/**
	 * List of resource definitions in the VNFD for resources to be temporarily
	 * instantiated during the runtime of the LCM operation which is related to this
	 * grant request, with one entry per resource. The NFVO will assume that the
	 * VNFM will be responsible to both allocate and release the temporary resource
	 * during the runtime of the LCM operation. This means, the resource can be
	 * allocated and consumed after the \"start\" notification for the LCM operation
	 * is sent by the VNFM, and the resource will be released before the \"result\"
	 * notification of the VNF LCM operation is sent by the VNFM.
	 *
	 * @return tempResources
	 **/
	@ApiModelProperty(value = "List of resource definitions in the VNFD for resources to be temporarily instantiated during the runtime of the LCM operation which is related to this grant request, with one entry per resource. The NFVO will assume that the VNFM will be responsible to both allocate and release the temporary resource during the runtime of the LCM operation. This means, the resource can be allocated and consumed after the \"start\" notification for the LCM operation is sent by the VNFM, and the resource will be released before the \"result\" notification of the VNF LCM operation is sent by the VNFM. ")

	@Valid

	public List<ResourceDefinition> getTempResources() {
		return tempResources;
	}

	public void setTempResources(final List<ResourceDefinition> tempResources) {
		this.tempResources = tempResources;
	}

	public GrantRequest removeResources(final List<ResourceDefinition> removeResources) {
		this.removeResources = removeResources;
		return this;
	}

	public GrantRequest addRemoveResourcesItem(final ResourceDefinition removeResourcesItem) {
		if (this.removeResources == null) {
			this.removeResources = new ArrayList<>();
		}
		this.removeResources.add(removeResourcesItem);
		return this;
	}

	/**
	 * Provides the definitions of resources to be removed by the LCM operation
	 * which is related to this grant request, with one entry per resource.
	 *
	 * @return removeResources
	 **/
	@ApiModelProperty(value = "Provides the definitions of resources to be removed by the LCM operation which is related to this grant request, with one entry per resource. ")

	@Valid

	public List<ResourceDefinition> getRemoveResources() {
		return removeResources;
	}

	public void setRemoveResources(final List<ResourceDefinition> removeResources) {
		this.removeResources = removeResources;
	}

	public GrantRequest updateResources(final List<ResourceDefinition> updateResources) {
		this.updateResources = updateResources;
		return this;
	}

	public GrantRequest addUpdateResourcesItem(final ResourceDefinition updateResourcesItem) {
		if (this.updateResources == null) {
			this.updateResources = new ArrayList<>();
		}
		this.updateResources.add(updateResourcesItem);
		return this;
	}

	/**
	 * Provides the definitions of resources to be modified by the LCM operation
	 * which is related to this grant request, with one entry per resource.
	 *
	 * @return updateResources
	 **/
	@ApiModelProperty(value = "Provides the definitions of resources to be modified by the LCM operation which is related to this grant request, with one entry per resource. ")

	@Valid

	public List<ResourceDefinition> getUpdateResources() {
		return updateResources;
	}

	public void setUpdateResources(final List<ResourceDefinition> updateResources) {
		this.updateResources = updateResources;
	}

	public GrantRequest placementConstraints(final List<PlacementConstraint> placementConstraints) {
		this.placementConstraints = placementConstraints;
		return this;
	}

	public GrantRequest addPlacementConstraintsItem(final PlacementConstraint placementConstraintsItem) {
		if (this.placementConstraints == null) {
			this.placementConstraints = new ArrayList<>();
		}
		this.placementConstraints.add(placementConstraintsItem);
		return this;
	}

	/**
	 * Placement constraints that the VNFM may send to the NFVO in order to
	 * influence the resource placement decision. If sent, the NFVO shall take the
	 * constraints into consideration when making resource placement decisions, and
	 * shall reject the grant if they cannot be honoured. The affinity/anti-affinity
	 * rules defined in the VNFD , and the placement constraints in the
	 * GrantVnfLifecycleOperation as defined in this clause should be conflict-free.
	 * In case of conflicts, the placement constraints in the
	 * GrantVnfLifecycleOperation shall take precedence. Passing constraints allows
	 * the VNFM or the lifecycle management scripts to influence resource placement
	 * decisions by the NFVO to ensure VNF properties such as performance or fault
	 * tolerance. If fallbackBestEffort is present in placement constraints and set
	 * to “true”, the NFVO shall process the Affinity/AntiAffinity constraint in a
	 * best effort manner, in which case, if specified resources cannot be allocated
	 * based on specified placement constraint, the NFVO looks for an alternate best
	 * effort placement for the specified resources to be granted. In the best
	 * effort anti-affinity case, the resources are expected to be spread optimally
	 * over all available instances of scope (e.g. zones), and in the best effort
	 * affinity case, they are expected to be distributed optimally over fewer
	 * possible instances of scope.
	 *
	 * @return placementConstraints
	 **/
	@ApiModelProperty(value = "Placement constraints that the VNFM may send to the NFVO in order to influence the resource placement decision. If sent, the NFVO shall take the constraints into consideration when making resource placement decisions, and shall reject the grant if they cannot be honoured. The affinity/anti-affinity rules defined in the VNFD , and the placement constraints in the GrantVnfLifecycleOperation as defined in this clause should be conflict-free. In case of conflicts, the placement constraints in the GrantVnfLifecycleOperation shall take precedence. Passing constraints allows the VNFM or the lifecycle management scripts to influence resource placement decisions by the NFVO to ensure VNF properties such as performance or fault tolerance. If fallbackBestEffort is present in placement constraints and set to “true”, the NFVO shall process the Affinity/AntiAffinity constraint in a best effort manner, in which case, if specified resources cannot be allocated based on specified placement constraint, the NFVO looks for an alternate best effort placement for the specified resources to be granted. In the best effort anti-affinity case, the resources are expected to be spread optimally over all available instances of scope (e.g. zones), and in the best effort affinity case, they are expected to be distributed optimally over fewer possible instances of scope. ")

	@Valid

	public List<PlacementConstraint> getPlacementConstraints() {
		return placementConstraints;
	}

	public void setPlacementConstraints(final List<PlacementConstraint> placementConstraints) {
		this.placementConstraints = placementConstraints;
	}

	public GrantRequest vimConstraints(final List<VimConstraint> vimConstraints) {
		this.vimConstraints = vimConstraints;
		return this;
	}

	public GrantRequest addVimConstraintsItem(final VimConstraint vimConstraintsItem) {
		if (this.vimConstraints == null) {
			this.vimConstraints = new ArrayList<>();
		}
		this.vimConstraints.add(vimConstraintsItem);
		return this;
	}

	/**
	 * Used by the VNFM to require that multiple resources are managed through the
	 * same VIM connection. If sent, the NFVO shall take the constraints into
	 * consideration when making VIM selection decisions, and shall reject the grant
	 * if they cannot be honoured. This attribute shall be supported if VNF-related
	 * Resource Management in direct mode is applicable. The applicability and
	 * further details of this attribute for indirect mode are left for future
	 * specification.
	 *
	 * @return vimConstraints
	 **/
	@ApiModelProperty(value = "Used by the VNFM to require that multiple resources are managed through the same VIM connection. If sent, the NFVO shall take the constraints into consideration when making VIM selection decisions, and shall reject the grant if they cannot be honoured. This attribute shall be supported if VNF-related Resource Management in direct mode is applicable. The applicability and further details of this attribute for indirect mode are left for future specification. ")

	@Valid

	public List<VimConstraint> getVimConstraints() {
		return vimConstraints;
	}

	public void setVimConstraints(final List<VimConstraint> vimConstraints) {
		this.vimConstraints = vimConstraints;
	}

	public GrantRequest additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional parameters passed by the VNFM, specific to the VNF and the LCM
	 * operation.
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "Additional parameters passed by the VNFM, specific to the VNF and the LCM operation. ")

	@Valid

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public GrantRequest links(final GrantRequestLinks links) {
		this.links = links;
		return this;
	}

	/**
	 * Get links
	 *
	 * @return links
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public GrantRequestLinks getLinks() {
		return links;
	}

	public void setLinks(final GrantRequestLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final GrantRequest grantRequest = (GrantRequest) o;
		return Objects.equals(this.vnfInstanceId, grantRequest.vnfInstanceId) &&
				Objects.equals(this.vnfLcmOpOccId, grantRequest.vnfLcmOpOccId) &&
				Objects.equals(this.vnfdId, grantRequest.vnfdId) &&
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
				Objects.equals(this.links, grantRequest.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, vnfLcmOpOccId, vnfdId, flavourId, operation, isAutomaticInvocation, instantiationLevelId, addResources, tempResources, removeResources, updateResources, placementConstraints, vimConstraints, additionalParams, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class GrantRequest {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    vnfLcmOpOccId: ").append(toIndentedString(vnfLcmOpOccId)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
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
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
