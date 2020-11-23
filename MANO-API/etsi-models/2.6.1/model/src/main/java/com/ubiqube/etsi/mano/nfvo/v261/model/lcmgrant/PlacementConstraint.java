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

package com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type provides information regarding a resource placement constraint. A
 * set of such constraints may be sent by the VNFM to the NFVO to influence the
 * resource placement decisions made by the NFVO as part of the granting
 * process. A placement constraint defines a condition to the placement of new
 * resources, considering other new resources as well as existing resources.
 * EXAMPLE: The following rules influence the placement of a set of resources
 * such that they are placed in the same Network Function Virtualisation
 * Infrastructure Point of Presence (NFVI-PoP) but in different resource zones:
 * {type&#x3D;\&quot;affinity\&quot;; scope&#x3D;\&quot;NFVI_POP\&quot;;
 * {resource1,resource2}} {type&#x3D;\&quot;anti-affinity\&quot;;
 * scope&#x3D;\&quot;ZONE\&quot;; {resource1,resource2}}
 */
@ApiModel(description = "This type provides information regarding a resource placement constraint. A set of such constraints may be sent by the VNFM to the NFVO to influence the resource placement decisions made by the NFVO as part of the granting process. A placement constraint defines a condition to the placement of new resources, considering other new resources as well as existing resources. EXAMPLE: The following rules influence the placement of a set of resources such that they are placed in the same Network Function Virtualisation Infrastructure Point of Presence (NFVI-PoP) but in different resource zones: {type=\"affinity\"; scope=\"NFVI_POP\"; {resource1,resource2}} {type=\"anti-affinity\"; scope=\"ZONE\"; {resource1,resource2}} ")
@Validated


public class PlacementConstraint {
	/**
	 * The type of the constraint. Permitted values: * AFFINITY * ANTI_AFFINITY
	 */
	public enum AffinityOrAntiAffinityEnum {
		AFFINITY("AFFINITY"),

		ANTI_AFFINITY("ANTI_AFFINITY");

		private final String value;

		AffinityOrAntiAffinityEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AffinityOrAntiAffinityEnum fromValue(final String text) {
			for (final AffinityOrAntiAffinityEnum b : AffinityOrAntiAffinityEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("affinityOrAntiAffinity")
	private AffinityOrAntiAffinityEnum affinityOrAntiAffinity = null;

	/**
	 * The scope of the placement constraint indicating the category of the
	 * \"place\" where the constraint applies. Permitted values: * NFVI_POP * ZONE *
	 * ZONE_GROUP * NFVI_NODE
	 */
	public enum ScopeEnum {
		NFVI_POP("NFVI_POP"),

		ZONE("ZONE"),

		ZONE_GROUP("ZONE_GROUP"),

		NFVI_NODE("NFVI_NODE");

		private final String value;

		ScopeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ScopeEnum fromValue(final String text) {
			for (final ScopeEnum b : ScopeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("scope")
	private ScopeEnum scope = null;

	@JsonProperty("resource")
	@Valid
	private List<ConstraintResourceRef> resource = new ArrayList<>();

	@JsonProperty("fallbackBestEffort")
	private Boolean fallbackBestEffort = null;

	public PlacementConstraint affinityOrAntiAffinity(final AffinityOrAntiAffinityEnum affinityOrAntiAffinity) {
		this.affinityOrAntiAffinity = affinityOrAntiAffinity;
		return this;
	}

	/**
	 * The type of the constraint. Permitted values: * AFFINITY * ANTI_AFFINITY
	 * 
	 * @return affinityOrAntiAffinity
	 **/
	@ApiModelProperty(required = true, value = "The type of the constraint. Permitted values: * AFFINITY * ANTI_AFFINITY ")
	@NotNull

	public AffinityOrAntiAffinityEnum getAffinityOrAntiAffinity() {
		return affinityOrAntiAffinity;
	}

	public void setAffinityOrAntiAffinity(final AffinityOrAntiAffinityEnum affinityOrAntiAffinity) {
		this.affinityOrAntiAffinity = affinityOrAntiAffinity;
	}

	public PlacementConstraint scope(final ScopeEnum scope) {
		this.scope = scope;
		return this;
	}

	/**
	 * The scope of the placement constraint indicating the category of the
	 * \"place\" where the constraint applies. Permitted values: * NFVI_POP * ZONE *
	 * ZONE_GROUP * NFVI_NODE
	 * 
	 * @return scope
	 **/
	@ApiModelProperty(required = true, value = "The scope of the placement constraint indicating the category of the \"place\" where the constraint applies. Permitted values: * NFVI_POP * ZONE * ZONE_GROUP * NFVI_NODE ")
	@NotNull

	public ScopeEnum getScope() {
		return scope;
	}

	public void setScope(final ScopeEnum scope) {
		this.scope = scope;
	}

	public PlacementConstraint resource(final List<ConstraintResourceRef> resource) {
		this.resource = resource;
		return this;
	}

	public PlacementConstraint addResourceItem(final ConstraintResourceRef resourceItem) {
		this.resource.add(resourceItem);
		return this;
	}

	/**
	 * References to resources in the constraint rule.
	 * 
	 * @return resource
	 **/
	@ApiModelProperty(required = true, value = "References to resources in the constraint rule. ")
	@NotNull

	@Valid
	@Size(min = 2)
	public List<ConstraintResourceRef> getResource() {
		return resource;
	}

	public void setResource(final List<ConstraintResourceRef> resource) {
		this.resource = resource;
	}

	public PlacementConstraint fallbackBestEffort(final Boolean fallbackBestEffort) {
		this.fallbackBestEffort = fallbackBestEffort;
		return this;
	}

	/**
	 * Indication if the constraint is handled with fall back best effort. Default
	 * value is “false”. If set to true, the Affinity/Anti_Affinity placement
	 * constraint need not be fully satisfied, i.e. if the allocation cannot be
	 * honoured with the placement constraint, the request is processed in a best
	 * effort manner.
	 * 
	 * @return fallbackBestEffort
	 **/
	@ApiModelProperty(value = "Indication if the constraint is handled with fall back best effort. Default value is “false”. If set to true, the Affinity/Anti_Affinity placement constraint need not be fully satisfied, i.e. if the allocation cannot be honoured with the placement constraint, the request is processed in a best effort manner. ")

	public Boolean getFallbackBestEffort() {
		return fallbackBestEffort;
	}

	public void setFallbackBestEffort(final Boolean fallbackBestEffort) {
		this.fallbackBestEffort = fallbackBestEffort;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PlacementConstraint placementConstraint = (PlacementConstraint) o;
		return Objects.equals(this.affinityOrAntiAffinity, placementConstraint.affinityOrAntiAffinity) &&
				Objects.equals(this.scope, placementConstraint.scope) &&
				Objects.equals(this.resource, placementConstraint.resource) &&
				Objects.equals(this.fallbackBestEffort, placementConstraint.fallbackBestEffort);
	}

	@Override
	public int hashCode() {
		return Objects.hash(affinityOrAntiAffinity, scope, resource, fallbackBestEffort);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PlacementConstraint {\n");

		sb.append("    affinityOrAntiAffinity: ").append(toIndentedString(affinityOrAntiAffinity)).append("\n");
		sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
		sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
		sb.append("    fallbackBestEffort: ").append(toIndentedString(fallbackBestEffort)).append("\n");
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
