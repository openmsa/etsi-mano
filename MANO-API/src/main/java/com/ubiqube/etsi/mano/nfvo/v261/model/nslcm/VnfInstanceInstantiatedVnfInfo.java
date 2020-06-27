package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information specific to an instantiated VNF instance. This attribute shall be
 * present if the instantiateState attribute value is INSTANTIATED.
 */
@ApiModel(description = "Information specific to an instantiated VNF instance. This attribute shall be present if the instantiateState attribute value is INSTANTIATED. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class VnfInstanceInstantiatedVnfInfo {
	@JsonProperty("flavourId")
	private String flavourId = null;

	@JsonProperty("vnfState")
	private VnfOperationalStateType vnfState = null;

	@JsonProperty("scaleStatus")
	@Valid
	private List<VnfScaleInfo> scaleStatus = null;

	@JsonProperty("extCpInfo")
	@Valid
	private List<VnfExtCpInfo> extCpInfo = null;

	@JsonProperty("extVirtualLinkInfo")
	@Valid
	private List<ExtVirtualLinkInfo> extVirtualLinkInfo = null;

	@JsonProperty("extManagedVirtualLinkInfo")
	@Valid
	private List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo = null;

	@JsonProperty("monitoringParameters")
	@Valid
	private List<VnfMonitoringParameter> monitoringParameters = null;

	@JsonProperty("localizationLanguage")
	private String localizationLanguage = null;

	@JsonProperty("vnfcResourceInfo")
	@Valid
	private List<VnfcResourceInfo> vnfcResourceInfo = null;

	@JsonProperty("virtualLinkResourceInfo")
	@Valid
	private List<VnfVirtualLinkResourceInfo> virtualLinkResourceInfo = null;

	@JsonProperty("virtualStorageResourceInfo")
	@Valid
	private List<VirtualStorageResourceInfo> virtualStorageResourceInfo = null;

	public VnfInstanceInstantiatedVnfInfo flavourId(final String flavourId) {
		this.flavourId = flavourId;
		return this;
	}

	/**
	 * Identifier of the VNF deployment flavor applied to this VNF instance.
	 *
	 * @return flavourId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the VNF deployment flavor applied to this VNF instance. ")
	@NotNull

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public VnfInstanceInstantiatedVnfInfo vnfState(final VnfOperationalStateType vnfState) {
		this.vnfState = vnfState;
		return this;
	}

	/**
	 * The state of the VNF instance.
	 *
	 * @return vnfState
	 **/
	@ApiModelProperty(required = true, value = "The state of the VNF instance. ")
	@NotNull

	@Valid

	public VnfOperationalStateType getVnfState() {
		return vnfState;
	}

	public void setVnfState(final VnfOperationalStateType vnfState) {
		this.vnfState = vnfState;
	}

	public VnfInstanceInstantiatedVnfInfo scaleStatus(final List<VnfScaleInfo> scaleStatus) {
		this.scaleStatus = scaleStatus;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfo addScaleStatusItem(final VnfScaleInfo scaleStatusItem) {
		if (this.scaleStatus == null) {
			this.scaleStatus = new ArrayList<>();
		}
		this.scaleStatus.add(scaleStatusItem);
		return this;
	}

	/**
	 * Scale status of the VNF, one entry per aspect. Represents for every scaling
	 * aspect how \"big\" the VNF has been scaled w.r.t. that aspect.
	 *
	 * @return scaleStatus
	 **/
	@ApiModelProperty(value = "Scale status of the VNF, one entry per aspect. Represents for every scaling aspect how \"big\" the VNF has been scaled w.r.t. that aspect. ")

	@Valid

	public List<VnfScaleInfo> getScaleStatus() {
		return scaleStatus;
	}

	public void setScaleStatus(final List<VnfScaleInfo> scaleStatus) {
		this.scaleStatus = scaleStatus;
	}

	public VnfInstanceInstantiatedVnfInfo extCpInfo(final List<VnfExtCpInfo> extCpInfo) {
		this.extCpInfo = extCpInfo;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfo addExtCpInfoItem(final VnfExtCpInfo extCpInfoItem) {
		if (this.extCpInfo == null) {
			this.extCpInfo = new ArrayList<>();
		}
		this.extCpInfo.add(extCpInfoItem);
		return this;
	}

	/**
	 * Information about the external CPs exposed by the VNF instance.
	 *
	 * @return extCpInfo
	 **/
	@ApiModelProperty(value = "Information about the external CPs exposed by the VNF instance. ")

	@Valid
	@Size(min = 1)
	public List<VnfExtCpInfo> getExtCpInfo() {
		return extCpInfo;
	}

	public void setExtCpInfo(final List<VnfExtCpInfo> extCpInfo) {
		this.extCpInfo = extCpInfo;
	}

	public VnfInstanceInstantiatedVnfInfo extVirtualLinkInfo(final List<ExtVirtualLinkInfo> extVirtualLinkInfo) {
		this.extVirtualLinkInfo = extVirtualLinkInfo;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfo addExtVirtualLinkInfoItem(final ExtVirtualLinkInfo extVirtualLinkInfoItem) {
		if (this.extVirtualLinkInfo == null) {
			this.extVirtualLinkInfo = new ArrayList<>();
		}
		this.extVirtualLinkInfo.add(extVirtualLinkInfoItem);
		return this;
	}

	/**
	 * Information about the external VLs the VNF instance is connected to.
	 *
	 * @return extVirtualLinkInfo
	 **/
	@ApiModelProperty(value = "Information about the external VLs the VNF instance is connected to. ")

	@Valid

	public List<ExtVirtualLinkInfo> getExtVirtualLinkInfo() {
		return extVirtualLinkInfo;
	}

	public void setExtVirtualLinkInfo(final List<ExtVirtualLinkInfo> extVirtualLinkInfo) {
		this.extVirtualLinkInfo = extVirtualLinkInfo;
	}

	public VnfInstanceInstantiatedVnfInfo extManagedVirtualLinkInfo(final List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo) {
		this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfo addExtManagedVirtualLinkInfoItem(final ExtManagedVirtualLinkInfo extManagedVirtualLinkInfoItem) {
		if (this.extManagedVirtualLinkInfo == null) {
			this.extManagedVirtualLinkInfo = new ArrayList<>();
		}
		this.extManagedVirtualLinkInfo.add(extManagedVirtualLinkInfoItem);
		return this;
	}

	/**
	 * External virtual links the VNF instance is connected to.
	 *
	 * @return extManagedVirtualLinkInfo
	 **/
	@ApiModelProperty(value = "External virtual links the VNF instance is connected to. ")

	@Valid

	public List<ExtManagedVirtualLinkInfo> getExtManagedVirtualLinkInfo() {
		return extManagedVirtualLinkInfo;
	}

	public void setExtManagedVirtualLinkInfo(final List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo) {
		this.extManagedVirtualLinkInfo = extManagedVirtualLinkInfo;
	}

	public VnfInstanceInstantiatedVnfInfo monitoringParameters(final List<VnfMonitoringParameter> monitoringParameters) {
		this.monitoringParameters = monitoringParameters;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfo addMonitoringParametersItem(final VnfMonitoringParameter monitoringParametersItem) {
		if (this.monitoringParameters == null) {
			this.monitoringParameters = new ArrayList<>();
		}
		this.monitoringParameters.add(monitoringParametersItem);
		return this;
	}

	/**
	 * Performance metrics tracked by the VNFM (e.g. for auto-scaling purposes) as
	 * identified by the VNF provider in the VNFD.
	 *
	 * @return monitoringParameters
	 **/
	@ApiModelProperty(value = "Performance metrics tracked by the VNFM (e.g. for  auto-scaling purposes) as identified by the VNF  provider in the VNFD. ")

	@Valid

	public List<VnfMonitoringParameter> getMonitoringParameters() {
		return monitoringParameters;
	}

	public void setMonitoringParameters(final List<VnfMonitoringParameter> monitoringParameters) {
		this.monitoringParameters = monitoringParameters;
	}

	public VnfInstanceInstantiatedVnfInfo localizationLanguage(final String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
		return this;
	}

	/**
	 * Information about localization language of the VNF (includes e.g. strings in
	 * the VNFD). The localization languages supported by a VNF can be declared in
	 * the VNFD, and localization language selection can take place at instantiation
	 * time. The value shall comply with the format defined in IETF RFC 5646.
	 *
	 * @return localizationLanguage
	 **/
	@ApiModelProperty(value = "Information about localization language of the VNF (includes e.g. strings in the VNFD). The localization languages supported by a VNF can be declared in the VNFD, and localization language selection can take place at instantiation time. The value shall comply with the format defined in IETF RFC 5646. ")

	public String getLocalizationLanguage() {
		return localizationLanguage;
	}

	public void setLocalizationLanguage(final String localizationLanguage) {
		this.localizationLanguage = localizationLanguage;
	}

	public VnfInstanceInstantiatedVnfInfo vnfcResourceInfo(final List<VnfcResourceInfo> vnfcResourceInfo) {
		this.vnfcResourceInfo = vnfcResourceInfo;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfo addVnfcResourceInfoItem(final VnfcResourceInfo vnfcResourceInfoItem) {
		if (this.vnfcResourceInfo == null) {
			this.vnfcResourceInfo = new ArrayList<>();
		}
		this.vnfcResourceInfo.add(vnfcResourceInfoItem);
		return this;
	}

	/**
	 * Information about the virtualised compute and storage resources used by the
	 * VNFCs of the VNF instance.
	 *
	 * @return vnfcResourceInfo
	 **/
	@ApiModelProperty(value = "Information about the virtualised compute and storage resources used by the VNFCs of the VNF instance. ")

	@Valid

	public List<VnfcResourceInfo> getVnfcResourceInfo() {
		return vnfcResourceInfo;
	}

	public void setVnfcResourceInfo(final List<VnfcResourceInfo> vnfcResourceInfo) {
		this.vnfcResourceInfo = vnfcResourceInfo;
	}

	public VnfInstanceInstantiatedVnfInfo virtualLinkResourceInfo(final List<VnfVirtualLinkResourceInfo> virtualLinkResourceInfo) {
		this.virtualLinkResourceInfo = virtualLinkResourceInfo;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfo addVirtualLinkResourceInfoItem(final VnfVirtualLinkResourceInfo virtualLinkResourceInfoItem) {
		if (this.virtualLinkResourceInfo == null) {
			this.virtualLinkResourceInfo = new ArrayList<>();
		}
		this.virtualLinkResourceInfo.add(virtualLinkResourceInfoItem);
		return this;
	}

	/**
	 * Information about the virtualised network resources used by the VLs of the
	 * VNF instance.
	 *
	 * @return virtualLinkResourceInfo
	 **/
	@ApiModelProperty(value = "Information about the virtualised network resources used by the VLs of the VNF instance. ")

	@Valid

	public List<VnfVirtualLinkResourceInfo> getVirtualLinkResourceInfo() {
		return virtualLinkResourceInfo;
	}

	public void setVirtualLinkResourceInfo(final List<VnfVirtualLinkResourceInfo> virtualLinkResourceInfo) {
		this.virtualLinkResourceInfo = virtualLinkResourceInfo;
	}

	public VnfInstanceInstantiatedVnfInfo virtualStorageResourceInfo(final List<VirtualStorageResourceInfo> virtualStorageResourceInfo) {
		this.virtualStorageResourceInfo = virtualStorageResourceInfo;
		return this;
	}

	public VnfInstanceInstantiatedVnfInfo addVirtualStorageResourceInfoItem(final VirtualStorageResourceInfo virtualStorageResourceInfoItem) {
		if (this.virtualStorageResourceInfo == null) {
			this.virtualStorageResourceInfo = new ArrayList<>();
		}
		this.virtualStorageResourceInfo.add(virtualStorageResourceInfoItem);
		return this;
	}

	/**
	 * Information on the virtualised storage resource(s) used as storage for the
	 * VNF instance.
	 *
	 * @return virtualStorageResourceInfo
	 **/
	@ApiModelProperty(value = "Information on the virtualised storage resource(s) used as storage for the VNF instance. ")

	@Valid

	public List<VirtualStorageResourceInfo> getVirtualStorageResourceInfo() {
		return virtualStorageResourceInfo;
	}

	public void setVirtualStorageResourceInfo(final List<VirtualStorageResourceInfo> virtualStorageResourceInfo) {
		this.virtualStorageResourceInfo = virtualStorageResourceInfo;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstanceInstantiatedVnfInfo vnfInstanceInstantiatedVnfInfo = (VnfInstanceInstantiatedVnfInfo) o;
		return Objects.equals(this.flavourId, vnfInstanceInstantiatedVnfInfo.flavourId) &&
				Objects.equals(this.vnfState, vnfInstanceInstantiatedVnfInfo.vnfState) &&
				Objects.equals(this.scaleStatus, vnfInstanceInstantiatedVnfInfo.scaleStatus) &&
				Objects.equals(this.extCpInfo, vnfInstanceInstantiatedVnfInfo.extCpInfo) &&
				Objects.equals(this.extVirtualLinkInfo, vnfInstanceInstantiatedVnfInfo.extVirtualLinkInfo) &&
				Objects.equals(this.extManagedVirtualLinkInfo, vnfInstanceInstantiatedVnfInfo.extManagedVirtualLinkInfo) &&
				Objects.equals(this.monitoringParameters, vnfInstanceInstantiatedVnfInfo.monitoringParameters) &&
				Objects.equals(this.localizationLanguage, vnfInstanceInstantiatedVnfInfo.localizationLanguage) &&
				Objects.equals(this.vnfcResourceInfo, vnfInstanceInstantiatedVnfInfo.vnfcResourceInfo) &&
				Objects.equals(this.virtualLinkResourceInfo, vnfInstanceInstantiatedVnfInfo.virtualLinkResourceInfo) &&
				Objects.equals(this.virtualStorageResourceInfo, vnfInstanceInstantiatedVnfInfo.virtualStorageResourceInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(flavourId, vnfState, scaleStatus, extCpInfo, extVirtualLinkInfo, extManagedVirtualLinkInfo, monitoringParameters, localizationLanguage, vnfcResourceInfo, virtualLinkResourceInfo, virtualStorageResourceInfo);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstanceInstantiatedVnfInfo {\n");

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
