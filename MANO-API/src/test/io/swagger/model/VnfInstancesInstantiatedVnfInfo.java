package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

public class VnfInstancesInstantiatedVnfInfo {

	private @Valid String flavourId = null;

	public enum VnfStateEnum {

		STARTED(String.valueOf("STARTED")), STOPPED(String.valueOf("STOPPED"));

		private final String value;

		VnfStateEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static VnfStateEnum fromValue(String v) {
			for (final VnfStateEnum b : VnfStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid VnfStateEnum vnfState = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoScaleStatus> scaleStatus = new ArrayList<VnfInstancesInstantiatedVnfInfoScaleStatus>();
	private @Valid List<VnfInstancesInstantiatedVnfInfoExtCpInfo> extCpInfo = new ArrayList<VnfInstancesInstantiatedVnfInfoExtCpInfo>();
	private @Valid List<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo> extVirtualLinkInfo = new ArrayList<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo>();
	private @Valid List<VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo = new ArrayList<VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo>();
	private @Valid List<VnfInstancesInstantiatedVnfInfoMonitoringParameters> monitoringParameters = new ArrayList<VnfInstancesInstantiatedVnfInfoMonitoringParameters>();
	private @Valid String localizationLanguage = null;
	private @Valid List<VnfInstancesInstantiatedVnfInfoVnfcResourceInfo> vnfcResourceInfo = new ArrayList<VnfInstancesInstantiatedVnfInfoVnfcResourceInfo>();
	private @Valid List<VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo> virtualLinkResourceInfo = new ArrayList<VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo>();
	private @Valid List<VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo> virtualStorageResourceInfo = new ArrayList<VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo>();
	private @Valid String vnfcInfo = null;
	private @Valid Object metadata = null;
	private @Valid Object extensions = null;
	private @Valid VnfInstancesInstantiatedVnfInfoLinks links = null;

	/**
	 * An identifier that is unique within a VNF descriptor.
	 **/
	public VnfInstancesInstantiatedVnfInfo flavourId(String flavourId) {
		this.flavourId = flavourId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier that is unique within a VNF descriptor. ")
	@JsonProperty("flavourId")
	@NotNull
	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(String flavourId) {
		this.flavourId = flavourId;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfo vnfState(VnfStateEnum vnfState) {
		this.vnfState = vnfState;
		return this;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("vnfState")
	@NotNull
	public VnfStateEnum getVnfState() {
		return vnfState;
	}

	public void setVnfState(VnfStateEnum vnfState) {
		this.vnfState = vnfState;
	}

	/**
	 * Scale status of the VNF, one entry per aspect. Represents for every scaling
	 * aspect how \&quot;big\&quot; the VNF has been scaled w.r.t. that aspect.
	 **/
	public VnfInstancesInstantiatedVnfInfo scaleStatus(List<VnfInstancesInstantiatedVnfInfoScaleStatus> scaleStatus) {
		this.scaleStatus = scaleStatus;
		return this;
	}

	@ApiModelProperty(value = "Scale status of the VNF, one entry per aspect. Represents for every scaling aspect how \"big\" the VNF has been scaled w.r.t. that aspect. ")
	@JsonProperty("scaleStatus")
	public List<VnfInstancesInstantiatedVnfInfoScaleStatus> getScaleStatus() {
		return scaleStatus;
	}

	public void setScaleStatus(List<VnfInstancesInstantiatedVnfInfoScaleStatus> scaleStatus) {
		this.scaleStatus = scaleStatus;
	}

	/**
	 * Information about the external CPs exposed by the VNF instance.
	 **/
	public VnfInstancesInstantiatedVnfInfo extCpInfo(List<VnfInstancesInstantiatedVnfInfoExtCpInfo> extCpInfo) {
		this.extCpInfo = extCpInfo;
		return this;
	}

	@ApiModelProperty(value = "Information about the external CPs exposed by the VNF instance. ")
	@JsonProperty("extCpInfo")
	@Size(min = 1)
	public List<VnfInstancesInstantiatedVnfInfoExtCpInfo> getExtCpInfo() {
		return extCpInfo;
	}

	public void setExtCpInfo(List<VnfInstancesInstantiatedVnfInfoExtCpInfo> extCpInfo) {
		this.extCpInfo = extCpInfo;
	}

	/**
	 * Information about the external VLs the VNF instance is connected to.
	 **/
	public VnfInstancesInstantiatedVnfInfo extVirtualLinkInfo(List<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo> extVirtualLinkInfo) {
		this.extVirtualLinkInfo = extVirtualLinkInfo;
		return this;
	}

	@ApiModelProperty(value = "Information about the external VLs the VNF instance is connected to. ")
	@JsonProperty("extVirtualLinkInfo")
	public List<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo> getExtVirtualLinkInfo() {
		return extVirtualLinkInfo;
	}

	public void setExtVirtualLinkInfo(List<VnfInstancesInstantiatedVnfInfoExtVirtualLinkInfo> extVirtualLinkInfo) {
		this.extVirtualLinkInfo = extVirtualLinkInfo;
	}

	/**
	 * External virtual links the VNF instance is connected to.
	 **/
	public VnfInstancesInstantiatedVnfInfo extManagedVirtualLinkInfo(List<VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo) {
		this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
		return this;
	}

	@ApiModelProperty(value = "External virtual links the VNF instance is connected to. ")
	@JsonProperty("extManagedVirtualLinkInfo")
	public List<VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo> getExtManagedVirtualLinkInfo() {
		return extManagedVirtualLinkInfo;
	}

	public void setExtManagedVirtualLinkInfo(List<VnfInstancesInstantiatedVnfInfoExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo) {
		this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
	}

	/**
	 * Active monitoring parameters.
	 **/
	public VnfInstancesInstantiatedVnfInfo monitoringParameters(List<VnfInstancesInstantiatedVnfInfoMonitoringParameters> monitoringParameters) {
		this.monitoringParameters = monitoringParameters;
		return this;
	}

	@ApiModelProperty(value = "Active monitoring parameters. ")
	@JsonProperty("monitoringParameters")
	public List<VnfInstancesInstantiatedVnfInfoMonitoringParameters> getMonitoringParameters() {
		return monitoringParameters;
	}

	public void setMonitoringParameters(List<VnfInstancesInstantiatedVnfInfoMonitoringParameters> monitoringParameters) {
		this.monitoringParameters = monitoringParameters;
	}

	/**
	 * Information about localization language of the VNF (includes e.g. strings in
	 * the VNFD). The localization languages supported by a VNF can be declared in
	 * the VNFD, and localization language selection can take place at instantiation
	 * time. The value shall comply with the format defined in IETF RFC 5646.
	 **/
	public VnfInstancesInstantiatedVnfInfo localizationLanguage(String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
		return this;
	}

	@ApiModelProperty(value = "Information about localization language of the VNF (includes e.g. strings in the VNFD). The localization languages supported by a VNF can be declared in the VNFD, and localization language selection can take place at instantiation time. The value shall comply with the format defined in IETF RFC 5646. ")
	@JsonProperty("localizationLanguage")
	public String getLocalizationLanguage() {
		return localizationLanguage;
	}

	public void setLocalizationLanguage(String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
	}

	/**
	 * Information about the virtualised compute and storage resources used by the
	 * VNFCs of the VNF instance.
	 **/
	public VnfInstancesInstantiatedVnfInfo vnfcResourceInfo(List<VnfInstancesInstantiatedVnfInfoVnfcResourceInfo> vnfcResourceInfo) {
		this.vnfcResourceInfo = vnfcResourceInfo;
		return this;
	}

	@ApiModelProperty(value = "Information about the virtualised compute and storage resources used by the VNFCs of the VNF instance. ")
	@JsonProperty("vnfcResourceInfo")
	public List<VnfInstancesInstantiatedVnfInfoVnfcResourceInfo> getVnfcResourceInfo() {
		return vnfcResourceInfo;
	}

	public void setVnfcResourceInfo(List<VnfInstancesInstantiatedVnfInfoVnfcResourceInfo> vnfcResourceInfo) {
		this.vnfcResourceInfo = vnfcResourceInfo;
	}

	/**
	 * Information about the virtualised network resources used by the VLs of the
	 * VNF instance.
	 **/
	public VnfInstancesInstantiatedVnfInfo virtualLinkResourceInfo(List<VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo> virtualLinkResourceInfo) {
		this.virtualLinkResourceInfo = virtualLinkResourceInfo;
		return this;
	}

	@ApiModelProperty(value = "Information about the virtualised network resources used by the VLs of the VNF instance. ")
	@JsonProperty("virtualLinkResourceInfo")
	public List<VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo> getVirtualLinkResourceInfo() {
		return virtualLinkResourceInfo;
	}

	public void setVirtualLinkResourceInfo(List<VnfInstancesInstantiatedVnfInfoVirtualLinkResourceInfo> virtualLinkResourceInfo) {
		this.virtualLinkResourceInfo = virtualLinkResourceInfo;
	}

	/**
	 * Information on the virtualised storage resource(s) used as storage for the
	 * VNF instance.
	 **/
	public VnfInstancesInstantiatedVnfInfo virtualStorageResourceInfo(List<VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo> virtualStorageResourceInfo) {
		this.virtualStorageResourceInfo = virtualStorageResourceInfo;
		return this;
	}

	@ApiModelProperty(value = "Information on the virtualised storage resource(s) used as storage for the VNF instance. ")
	@JsonProperty("virtualStorageResourceInfo")
	public List<VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo> getVirtualStorageResourceInfo() {
		return virtualStorageResourceInfo;
	}

	public void setVirtualStorageResourceInfo(List<VnfInstancesInstantiatedVnfInfoVirtualStorageResourceInfo> virtualStorageResourceInfo) {
		this.virtualStorageResourceInfo = virtualStorageResourceInfo;
	}

	/**
	 * TBD
	 **/
	public VnfInstancesInstantiatedVnfInfo vnfcInfo(String vnfcInfo) {
		this.vnfcInfo = vnfcInfo;
		return this;
	}

	@ApiModelProperty(value = "TBD")
	@JsonProperty("vnfcInfo")
	public String getVnfcInfo() {
		return vnfcInfo;
	}

	public void setVnfcInfo(String vnfcInfo) {
		this.vnfcInfo = vnfcInfo;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInstancesInstantiatedVnfInfo metadata(Object metadata) {
		this.metadata = metadata;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("metadata")
	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInstancesInstantiatedVnfInfo extensions(Object extensions) {
		this.extensions = extensions;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("extensions")
	public Object getExtensions() {
		return extensions;
	}

	public void setExtensions(Object extensions) {
		this.extensions = extensions;
	}

	/**
	 **/
	public VnfInstancesInstantiatedVnfInfo links(VnfInstancesInstantiatedVnfInfoLinks links) {
		this.links = links;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("_links")
	public VnfInstancesInstantiatedVnfInfoLinks getLinks() {
		return links;
	}

	public void setLinks(VnfInstancesInstantiatedVnfInfoLinks links) {
		this.links = links;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfo vnfInstancesInstantiatedVnfInfo = (VnfInstancesInstantiatedVnfInfo) o;
		return Objects.equals(flavourId, vnfInstancesInstantiatedVnfInfo.flavourId) &&
				Objects.equals(vnfState, vnfInstancesInstantiatedVnfInfo.vnfState) &&
				Objects.equals(scaleStatus, vnfInstancesInstantiatedVnfInfo.scaleStatus) &&
				Objects.equals(extCpInfo, vnfInstancesInstantiatedVnfInfo.extCpInfo) &&
				Objects.equals(extVirtualLinkInfo, vnfInstancesInstantiatedVnfInfo.extVirtualLinkInfo) &&
				Objects.equals(extManagedVirtualLinkInfo, vnfInstancesInstantiatedVnfInfo.extManagedVirtualLinkInfo) &&
				Objects.equals(monitoringParameters, vnfInstancesInstantiatedVnfInfo.monitoringParameters) &&
				Objects.equals(localizationLanguage, vnfInstancesInstantiatedVnfInfo.localizationLanguage) &&
				Objects.equals(vnfcResourceInfo, vnfInstancesInstantiatedVnfInfo.vnfcResourceInfo) &&
				Objects.equals(virtualLinkResourceInfo, vnfInstancesInstantiatedVnfInfo.virtualLinkResourceInfo) &&
				Objects.equals(virtualStorageResourceInfo, vnfInstancesInstantiatedVnfInfo.virtualStorageResourceInfo) &&
				Objects.equals(vnfcInfo, vnfInstancesInstantiatedVnfInfo.vnfcInfo) &&
				Objects.equals(metadata, vnfInstancesInstantiatedVnfInfo.metadata) &&
				Objects.equals(extensions, vnfInstancesInstantiatedVnfInfo.extensions) &&
				Objects.equals(links, vnfInstancesInstantiatedVnfInfo.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(flavourId, vnfState, scaleStatus, extCpInfo, extVirtualLinkInfo, extManagedVirtualLinkInfo, monitoringParameters, localizationLanguage, vnfcResourceInfo, virtualLinkResourceInfo, virtualStorageResourceInfo, vnfcInfo, metadata, extensions, links);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfo {\n");

		sb.append("    flavourId: ").append(toIndentedString(flavourId)).append("\n");
		sb.append("    vnfState: ").append(toIndentedString(vnfState)).append("\n");
		sb.append("    scaleStatus: ").append(toIndentedString(scaleStatus)).append("\n");
		sb.append("    extCpInfo: ").append(toIndentedString(extCpInfo)).append("\n");
		sb.append("    extVirtualLinkInfo: ").append(toIndentedString(extVirtualLinkInfo)).append("\n");
		sb.append("    extManagedVirtualLinkInfo: ").append(toIndentedString(extManagedVirtualLinkInfo)).append("\n");
		sb.append("    monitoringParameters: ").append(toIndentedString(monitoringParameters)).append("\n");
		sb.append("    localizationLanguage: ").append(toIndentedString(localizationLanguage)).append("\n");
		sb.append("    vnfcResourceInfo: ").append(toIndentedString(vnfcResourceInfo)).append("\n");
		sb.append("    virtualLinkResourceInfo: ").append(toIndentedString(virtualLinkResourceInfo)).append("\n");
		sb.append("    virtualStorageResourceInfo: ").append(toIndentedString(virtualStorageResourceInfo)).append("\n");
		sb.append("    vnfcInfo: ").append(toIndentedString(vnfcInfo)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
