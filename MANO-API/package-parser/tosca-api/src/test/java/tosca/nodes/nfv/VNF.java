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

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.annotations.Capability;
import com.ubiqube.parser.tosca.annotations.Occurence;
import com.ubiqube.parser.tosca.annotations.Relationship;
import com.ubiqube.parser.tosca.api.ToscaInernalBase;

import jakarta.validation.constraints.NotNull;
import tosca.datatypes.nfv.ScaleInfo;
import tosca.datatypes.nfv.VnfLcmOperationsConfiguration;
import tosca.datatypes.nfv.VnfMonitoringParameter;
import tosca.datatypes.nfv.VnfProfile;
import tosca.interfaces.nfv.Vnflcm;

public class VNF extends ToscaInernalBase {
	/**
	 * Describes a profile for instantiating VNFs of a particular NS DF according to
	 * a specific VNFD and VNF DF
	 */
	@JsonProperty("vnf_profile")
	private VnfProfile vnfProfile;

	/**
	 * Human readable name for the VNF Product
	 */
	@JsonProperty("product_info_name")
	private String productInfoName;

	/**
	 * Default localization language that is instantiated if no information about
	 * selected localization language is available
	 */
	@JsonProperty("default_localization_language")
	private String defaultLocalizationLanguage;

	/**
	 * Identifier of the Deployment Flavour within the VNFD
	 */
	@NotNull
	@JsonProperty("flavour_id")
	private String flavourId;

	/**
	 * Describes the configuration parameters for the VNF LCM operations
	 */
	@JsonProperty("lcm_operations_configuration")
	private VnfLcmOperationsConfiguration lcmOperationsConfiguration;

	/**
	 * Human readable description of the DF
	 */
	@NotNull
	@JsonProperty("flavour_description")
	private String flavourDescription;

	/**
	 * Human readable name for the VNF Product
	 */
	@NotNull
	@JsonProperty("product_name")
	private String productName;

	/**
	 * Identifier of this VNFD information element. This attribute shall be globally
	 * unique
	 */
	@NotNull
	@JsonProperty("descriptor_id")
	private String descriptorId;

	/**
	 * Human readable description of the VNF Product
	 */
	@JsonProperty("product_info_description")
	private String productInfoDescription;

	/**
	 * Provider of the VNF and of the VNFD
	 */
	@NotNull
	@JsonProperty("provider")
	private String provider;

	/**
	 * Describes monitoring parameters applicable to the VNF.
	 */
	@JsonProperty("monitoring_parameters")
	private Map<String, VnfMonitoringParameter> monitoringParameters;

	/**
	 * Identifies the version of the VNFD
	 */
	@NotNull
	@JsonProperty("descriptor_version")
	private String descriptorVersion;

	/**
	 * Identifies VNFM(s) compatible with the VNF
	 */
	@NotNull
	@JsonProperty("vnfm_info")
	private List<String> vnfmInfo;

	/**
	 * Software version of the VNF
	 */
	@NotNull
	@JsonProperty("software_version")
	private String softwareVersion;

	/**
	 * Information about localization languages of the VNF
	 */
	@JsonProperty("localization_languages")
	private List<String> localizationLanguages;

	/**
	 * Scale status of the VNF, one entry per aspect. Represents for every scaling
	 * aspect how "big" the VNF has been scaled w.r.t. that aspect.
	 */
	@NotNull
	@JsonProperty("scale_status")
	private Map<String, ScaleInfo> scaleStatus;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_9")
	private String virtualLink9Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_2")
	private String virtualLink2Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_10")
	private String virtualLink10Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_1")
	private String virtualLink1Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link")
	private String virtualLinkReq;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_4")
	private String virtualLink4Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_3")
	private String virtualLink3Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_6")
	private String virtualLink6Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_5")
	private String virtualLink5Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_8")
	private String virtualLink8Req;

	@Occurence({ "0", "1" })
	@Capability("tosca.capabilities.nfv.VirtualLinkable")
	@Relationship("tosca.relationships.nfv.VirtualLinksTo")
	@JsonProperty("virtual_link_7")
	private String virtualLink7Req;

	private Vnflcm Vnflcm;

	public VnfProfile getVnfProfile() {
		return this.vnfProfile;
	}

	public void setVnfProfile(final VnfProfile vnfProfile) {
		this.vnfProfile = vnfProfile;
	}

	public String getProductInfoName() {
		return this.productInfoName;
	}

	public void setProductInfoName(final String productInfoName) {
		this.productInfoName = productInfoName;
	}

	public String getDefaultLocalizationLanguage() {
		return this.defaultLocalizationLanguage;
	}

	public void setDefaultLocalizationLanguage(final String defaultLocalizationLanguage) {
		this.defaultLocalizationLanguage = defaultLocalizationLanguage;
	}

	@NotNull
	public String getFlavourId() {
		return this.flavourId;
	}

	public void setFlavourId(@NotNull final String flavourId) {
		this.flavourId = flavourId;
	}

	public VnfLcmOperationsConfiguration getLcmOperationsConfiguration() {
		return this.lcmOperationsConfiguration;
	}

	public void setLcmOperationsConfiguration(
			final VnfLcmOperationsConfiguration lcmOperationsConfiguration) {
		this.lcmOperationsConfiguration = lcmOperationsConfiguration;
	}

	@NotNull
	public String getFlavourDescription() {
		return this.flavourDescription;
	}

	public void setFlavourDescription(@NotNull final String flavourDescription) {
		this.flavourDescription = flavourDescription;
	}

	@NotNull
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(@NotNull final String productName) {
		this.productName = productName;
	}

	@NotNull
	public String getDescriptorId() {
		return this.descriptorId;
	}

	public void setDescriptorId(@NotNull final String descriptorId) {
		this.descriptorId = descriptorId;
	}

	public String getProductInfoDescription() {
		return this.productInfoDescription;
	}

	public void setProductInfoDescription(final String productInfoDescription) {
		this.productInfoDescription = productInfoDescription;
	}

	@NotNull
	public String getProvider() {
		return this.provider;
	}

	public void setProvider(@NotNull final String provider) {
		this.provider = provider;
	}

	public Map<String, VnfMonitoringParameter> getMonitoringParameters() {
		return this.monitoringParameters;
	}

	public void setMonitoringParameters(
			final Map<String, VnfMonitoringParameter> monitoringParameters) {
		this.monitoringParameters = monitoringParameters;
	}

	@NotNull
	public String getDescriptorVersion() {
		return this.descriptorVersion;
	}

	public void setDescriptorVersion(@NotNull final String descriptorVersion) {
		this.descriptorVersion = descriptorVersion;
	}

	@NotNull
	public List<String> getVnfmInfo() {
		return this.vnfmInfo;
	}

	public void setVnfmInfo(@NotNull final List<String> vnfmInfo) {
		this.vnfmInfo = vnfmInfo;
	}

	@NotNull
	public String getSoftwareVersion() {
		return this.softwareVersion;
	}

	public void setSoftwareVersion(@NotNull final String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public List<String> getLocalizationLanguages() {
		return this.localizationLanguages;
	}

	public void setLocalizationLanguages(final List<String> localizationLanguages) {
		this.localizationLanguages = localizationLanguages;
	}

	@NotNull
	public Map<String, ScaleInfo> getScaleStatus() {
		return this.scaleStatus;
	}

	public void setScaleStatus(@NotNull final Map<String, ScaleInfo> scaleStatus) {
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

	public String getVirtualLink10Req() {
		return this.virtualLink10Req;
	}

	public void setVirtualLink10Req(final String virtualLink10Req) {
		this.virtualLink10Req = virtualLink10Req;
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

	public Vnflcm getVnflcm() {
		return this.Vnflcm;
	}

	public void setVnflcm(final Vnflcm Vnflcm) {
		this.Vnflcm = Vnflcm;
	}

}
