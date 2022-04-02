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
package tosca.nodes.nfv;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.annotations.Capability;
import com.ubiqube.parser.tosca.annotations.Node;
import com.ubiqube.parser.tosca.annotations.Occurence;
import com.ubiqube.parser.tosca.annotations.Relationship;
import com.ubiqube.parser.tosca.api.ToscaInernalBase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import tosca.datatypes.nfv.NsProfile;

public class NS extends ToscaInernalBase {
	/**
	 * Identifier of this NS descriptor
	 */
	@Valid
	@NotNull
	@JsonProperty("descriptor_id")
	private String descriptorId;

	/**
	 * Identifies an NSD in a version independent manner. This attribute is
	 * invariant across versions of NSD
	 */
	@Valid
	@NotNull
	@JsonProperty("invariant_id")
	private String invariantId;

	/**
	 * Specifies a profile of a NS, when this NS is used as nested NS within another
	 * NS.
	 */
	@Valid
	@JsonProperty("ns_profile")
	private NsProfile nsProfile;

	/**
	 * Provides the human readable name of the NSD.
	 */
	@Valid
	@NotNull
	@JsonProperty("name")
	private String name;

	/**
	 * Specifies the service availability level for the NS instance.
	 */
	@Valid
	@JsonProperty("service_availability_level")
	@DecimalMin(value = "1", inclusive = true)
	private Integer serviceAvailabilityLevel;

	/**
	 * Identifier of the NS Deployment Flavour within the NSD
	 */
	@Valid
	@NotNull
	@JsonProperty("flavour_id")
	private String flavourId;

	/**
	 * Identifies the designer of the NSD.
	 */
	@Valid
	@NotNull
	@JsonProperty("designer")
	private String designer;

	/**
	 * Specifies the priority for the NS instance. Examples for the usage of
	 * priority include conflict resolution in case of resource shortage.
	 */
	@Valid
	@JsonProperty("priority")
	@DecimalMin(value = "0", inclusive = true)
	private Integer priority;

	/**
	 * Identifies the version of the NSD.
	 */
	@Valid
	@NotNull
	@JsonProperty("version")
	private String version;

	/**
	 * Scale status of the NS, one entry per aspect. Represents for every scaling
	 * aspect how "big" the NS has been scaled w.r.t. that aspect.
	 */
	@Valid
	@NotNull
	@JsonProperty("scale_status")
	private Map<String, Integer> scaleStatus;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_9")
	private String virtualLink9Req;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_2")
	private String virtualLink2Req;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_1")
	private String virtualLink1Req;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link")
	private String virtualLinkReq;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_4")
	private String virtualLink4Req;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_3")
	private String virtualLink3Req;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_6")
	private String virtualLink6Req;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_5")
	private String virtualLink5Req;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_8")
	private String virtualLink8Req;

	@Occurence({ "0", "1" })
	@Node("tosca.nodes.nfv.NsVirtualLink")
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_7")
	private String virtualLink7Req;

	@NotNull
	public String getDescriptorId() {
		return this.descriptorId;
	}

	public void setDescriptorId(@NotNull final String descriptorId) {
		this.descriptorId = descriptorId;
	}

	@NotNull
	public String getInvariantId() {
		return this.invariantId;
	}

	public void setInvariantId(@NotNull final String invariantId) {
		this.invariantId = invariantId;
	}

	public NsProfile getNsProfile() {
		return this.nsProfile;
	}

	public void setNsProfile(final NsProfile nsProfile) {
		this.nsProfile = nsProfile;
	}

	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(@NotNull final String name) {
		this.name = name;
	}

	public Integer getServiceAvailabilityLevel() {
		return this.serviceAvailabilityLevel;
	}

	public void setServiceAvailabilityLevel(final Integer serviceAvailabilityLevel) {
		this.serviceAvailabilityLevel = serviceAvailabilityLevel;
	}

	@NotNull
	public String getFlavourId() {
		return this.flavourId;
	}

	public void setFlavourId(@NotNull final String flavourId) {
		this.flavourId = flavourId;
	}

	@NotNull
	public String getDesigner() {
		return this.designer;
	}

	public void setDesigner(@NotNull final String designer) {
		this.designer = designer;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(final Integer priority) {
		this.priority = priority;
	}

	@NotNull
	public String getVersion() {
		return this.version;
	}

	public void setVersion(@NotNull final String version) {
		this.version = version;
	}

	@NotNull
	public Map<String, Integer> getScaleStatus() {
		return this.scaleStatus;
	}

	public void setScaleStatus(@NotNull final Map<String, Integer> scaleStatus) {
		this.scaleStatus = scaleStatus;
	}

	public String getVirtualLink9Req() {
		return this.virtualLink9Req;
	}

	public void setVirtualLink9Req(final String virtualLink9Req) {
		this.virtualLink9Req = virtualLink9Req;
	}

	public String getVirtualLink2Req() {
		return this.virtualLink2Req;
	}

	public void setVirtualLink2Req(final String virtualLink2Req) {
		this.virtualLink2Req = virtualLink2Req;
	}

	public String getVirtualLink1Req() {
		return this.virtualLink1Req;
	}

	public void setVirtualLink1Req(final String virtualLink1Req) {
		this.virtualLink1Req = virtualLink1Req;
	}

	public String getVirtualLinkReq() {
		return this.virtualLinkReq;
	}

	public void setVirtualLinkReq(final String virtualLinkReq) {
		this.virtualLinkReq = virtualLinkReq;
	}

	public String getVirtualLink4Req() {
		return this.virtualLink4Req;
	}

	public void setVirtualLink4Req(final String virtualLink4Req) {
		this.virtualLink4Req = virtualLink4Req;
	}

	public String getVirtualLink3Req() {
		return this.virtualLink3Req;
	}

	public void setVirtualLink3Req(final String virtualLink3Req) {
		this.virtualLink3Req = virtualLink3Req;
	}

	public String getVirtualLink6Req() {
		return this.virtualLink6Req;
	}

	public void setVirtualLink6Req(final String virtualLink6Req) {
		this.virtualLink6Req = virtualLink6Req;
	}

	public String getVirtualLink5Req() {
		return this.virtualLink5Req;
	}

	public void setVirtualLink5Req(final String virtualLink5Req) {
		this.virtualLink5Req = virtualLink5Req;
	}

	public String getVirtualLink8Req() {
		return this.virtualLink8Req;
	}

	public void setVirtualLink8Req(final String virtualLink8Req) {
		this.virtualLink8Req = virtualLink8Req;
	}

	public String getVirtualLink7Req() {
		return this.virtualLink7Req;
	}

	public void setVirtualLink7Req(final String virtualLink7Req) {
		this.virtualLink7Req = virtualLink7Req;
	}

}
