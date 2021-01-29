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
package com.ubiqube.etsi.mec.meo.v211.model.grant;

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
 * &#x27;This type represents a grant request. Refer to clause 9.5.2.2 of ETSI GS NFV-SOL 003&#x27;
 */
@ApiModel(description = "'This type represents a grant request. Refer to clause 9.5.2.2 of ETSI GS NFV-SOL 003'")
@Validated
public class GrantRequest {
	@JsonProperty("appInstanceId")
	private String appInstanceId = null;

	@JsonProperty("appLcmOpOccId")
	private String appLcmOpOccId = null;

	@JsonProperty("appDId")
	private String appDId = null;

	@JsonProperty("operation")
	private GrantRequestOperation operation = null;

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

	@JsonProperty("additionalParams")
	private Map<String, String> additionalParams = null;

	@JsonProperty("_links")
	private GrantRequestLinks _links = null;

	public GrantRequest appInstanceId(final String appInstanceId) {
		this.appInstanceId = appInstanceId;
		return this;
	}

	/**
	 * Get appInstanceId
	 *
	 * @return appInstanceId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getAppInstanceId() {
		return appInstanceId;
	}

	public void setAppInstanceId(final String appInstanceId) {
		this.appInstanceId = appInstanceId;
	}

	public GrantRequest appLcmOpOccId(final String appLcmOpOccId) {
		this.appLcmOpOccId = appLcmOpOccId;
		return this;
	}

	/**
	 * Get appLcmOpOccId
	 *
	 * @return appLcmOpOccId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getAppLcmOpOccId() {
		return appLcmOpOccId;
	}

	public void setAppLcmOpOccId(final String appLcmOpOccId) {
		this.appLcmOpOccId = appLcmOpOccId;
	}

	public GrantRequest appDId(final String appDId) {
		this.appDId = appDId;
		return this;
	}

	/**
	 * Get appDId
	 *
	 * @return appDId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getAppDId() {
		return appDId;
	}

	public void setAppDId(final String appDId) {
		this.appDId = appDId;
	}

	public GrantRequest operation(final GrantRequestOperation operation) {
		this.operation = operation;
		return this;
	}

	/**
	 * Get operation
	 *
	 * @return operation
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public GrantRequestOperation getOperation() {
		return operation;
	}

	public void setOperation(final GrantRequestOperation operation) {
		this.operation = operation;
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
	 * Get addResources
	 *
	 * @return addResources
	 **/
	@ApiModelProperty(value = "")
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
	 * Get tempResources
	 *
	 * @return tempResources
	 **/
	@ApiModelProperty(value = "")
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
	 * Get removeResources
	 *
	 * @return removeResources
	 **/
	@ApiModelProperty(value = "")
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
	 * Get updateResources
	 *
	 * @return updateResources
	 **/
	@ApiModelProperty(value = "")
	@Valid
	public List<ResourceDefinition> getUpdateResources() {
		return updateResources;
	}

	public void setUpdateResources(final List<ResourceDefinition> updateResources) {
		this.updateResources = updateResources;
	}

	public GrantRequest additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Get additionalParams
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public GrantRequest _links(final GrantRequestLinks _links) {
		this._links = _links;
		return this;
	}

	/**
	 * Get _links
	 *
	 * @return _links
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid
	public GrantRequestLinks getLinks() {
		return _links;
	}

	public void setLinks(final GrantRequestLinks _links) {
		this._links = _links;
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
		return Objects.equals(this.appInstanceId, grantRequest.appInstanceId) &&
				Objects.equals(this.appLcmOpOccId, grantRequest.appLcmOpOccId) &&
				Objects.equals(this.appDId, grantRequest.appDId) &&
				Objects.equals(this.operation, grantRequest.operation) &&
				Objects.equals(this.addResources, grantRequest.addResources) &&
				Objects.equals(this.tempResources, grantRequest.tempResources) &&
				Objects.equals(this.removeResources, grantRequest.removeResources) &&
				Objects.equals(this.updateResources, grantRequest.updateResources) &&
				Objects.equals(this.additionalParams, grantRequest.additionalParams) &&
				Objects.equals(this._links, grantRequest._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(appInstanceId, appLcmOpOccId, appDId, operation, addResources, tempResources, removeResources, updateResources, additionalParams, _links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class GrantRequest {\n");

		sb.append("    appInstanceId: ").append(toIndentedString(appInstanceId)).append("\n");
		sb.append("    appLcmOpOccId: ").append(toIndentedString(appLcmOpOccId)).append("\n");
		sb.append("    appDId: ").append(toIndentedString(appDId)).append("\n");
		sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
		sb.append("    addResources: ").append(toIndentedString(addResources)).append("\n");
		sb.append("    tempResources: ").append(toIndentedString(tempResources)).append("\n");
		sb.append("    removeResources: ").append(toIndentedString(removeResources)).append("\n");
		sb.append("    updateResources: ").append(toIndentedString(updateResources)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
		sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
