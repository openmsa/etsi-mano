package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This type represents a VNF instance.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents a VNF instance. ")

public class InlineResponse200 {

	private @Valid String id = null;
	private @Valid String vnfInstanceName = null;
	private @Valid String vnfInstanceDescription = null;
	private @Valid String vnfdId = null;
	private @Valid String vnfProvider = null;
	private @Valid String vnfProductName = null;
	private @Valid String vnfSoftwareVersion = null;
	private @Valid String vnfdVersion = null;
	private @Valid String onboardedVnfPkgInfoId = null;
	private @Valid Object vnfConfigurableProperties = null;

	public enum InstantiationStateEnum {

		NOT_INSTANTIATED(String.valueOf("NOT_INSTANTIATED")), INSTANTIATED(String.valueOf("INSTANTIATED"));

		private final String value;

		InstantiationStateEnum(String v) {
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
		public static InstantiationStateEnum fromValue(String v) {
			for (final InstantiationStateEnum b : InstantiationStateEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid InstantiationStateEnum instantiationState = null;
	private @Valid VnfInstancesInstantiatedVnfInfo instantiatedVnfInfo = null;
	private @Valid List<String> vnfInstanceIds = new ArrayList<String>();
	private @Valid List<String> vnfInstanceNames = new ArrayList<String>();

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public InlineResponse200 id(String id) {
		this.id = id;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("id")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Name of the VNF instance. This attribute can be modified with the PATCH
	 * method.
	 **/
	public InlineResponse200 vnfInstanceName(String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
		return this;
	}

	@ApiModelProperty(value = "Name of the VNF instance. This attribute can be modified with the PATCH method. ")
	@JsonProperty("vnfInstanceName")
	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public void setVnfInstanceName(String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	/**
	 * Human-readable description of the VNF instance. This attribute can be
	 * modified with the PATCH method.
	 **/
	public InlineResponse200 vnfInstanceDescription(String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
		return this;
	}

	@ApiModelProperty(value = "Human-readable description of the VNF instance. This attribute can be modified with the PATCH method. ")
	@JsonProperty("vnfInstanceDescription")
	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public void setVnfInstanceDescription(String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public InlineResponse200 vnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vnfdId")
	@NotNull
	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
	}

	/**
	 * Provider of the VNF and the VNFD. The value is copied from the VNFD.
	 **/
	public InlineResponse200 vnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	@ApiModelProperty(required = true, value = "Provider of the VNF and the VNFD. The value is copied from the VNFD. ")
	@JsonProperty("vnfProvider")
	@NotNull
	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	/**
	 * Name to identify the VNF Product. The value is copied from the VNFD.
	 **/
	public InlineResponse200 vnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	@ApiModelProperty(required = true, value = "Name to identify the VNF Product. The value is copied from the VNFD. ")
	@JsonProperty("vnfProductName")
	@NotNull
	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	/**
	 * A Version.
	 **/
	public InlineResponse200 vnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	@ApiModelProperty(required = true, value = "A Version. ")
	@JsonProperty("vnfSoftwareVersion")
	@NotNull
	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	/**
	 * A Version.
	 **/
	public InlineResponse200 vnfdVersion(String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
		return this;
	}

	@ApiModelProperty(required = true, value = "A Version. ")
	@JsonProperty("vnfdVersion")
	@NotNull
	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public InlineResponse200 onboardedVnfPkgInfoId(String onboardedVnfPkgInfoId) {
		this.onboardedVnfPkgInfoId = onboardedVnfPkgInfoId;
		return this;
	}

	@ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("onboardedVnfPkgInfoId")
	@NotNull
	public String getOnboardedVnfPkgInfoId() {
		return onboardedVnfPkgInfoId;
	}

	public void setOnboardedVnfPkgInfoId(String onboardedVnfPkgInfoId) {
		this.onboardedVnfPkgInfoId = onboardedVnfPkgInfoId;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public InlineResponse200 vnfConfigurableProperties(Object vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("vnfConfigurableProperties")
	public Object getVnfConfigurableProperties() {
		return vnfConfigurableProperties;
	}

	public void setVnfConfigurableProperties(Object vnfConfigurableProperties) {
		this.vnfConfigurableProperties = vnfConfigurableProperties;
	}

	/**
	 * The instantiation state of the VNF.
	 **/
	public InlineResponse200 instantiationState(InstantiationStateEnum instantiationState) {
		this.instantiationState = instantiationState;
		return this;
	}

	@ApiModelProperty(required = true, value = "The instantiation state of the VNF. ")
	@JsonProperty("instantiationState")
	@NotNull
	public InstantiationStateEnum getInstantiationState() {
		return instantiationState;
	}

	public void setInstantiationState(InstantiationStateEnum instantiationState) {
		this.instantiationState = instantiationState;
	}

	/**
	 **/
	public InlineResponse200 instantiatedVnfInfo(VnfInstancesInstantiatedVnfInfo instantiatedVnfInfo) {
		this.instantiatedVnfInfo = instantiatedVnfInfo;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("instantiatedVnfInfo")
	public VnfInstancesInstantiatedVnfInfo getInstantiatedVnfInfo() {
		return instantiatedVnfInfo;
	}

	public void setInstantiatedVnfInfo(VnfInstancesInstantiatedVnfInfo instantiatedVnfInfo) {
		this.instantiatedVnfInfo = instantiatedVnfInfo;
	}

	/**
	 * If present, match VNF instances with an instance identifier listed in this
	 * attribute. The attributes \&quot;vnfInstanceIds\&quot; and
	 * \&quot;vnfInstanceNames\&quot; are alternatives to reference to particular
	 * VNF Instances in a filter. They should not be used both in the same filter
	 * instance, but one alternative should be chosen.
	 **/
	public InlineResponse200 vnfInstanceIds(List<String> vnfInstanceIds) {
		this.vnfInstanceIds = vnfInstanceIds;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances with an instance identifier listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	@JsonProperty("vnfInstanceIds")
	public List<String> getVnfInstanceIds() {
		return vnfInstanceIds;
	}

	public void setVnfInstanceIds(List<String> vnfInstanceIds) {
		this.vnfInstanceIds = vnfInstanceIds;
	}

	/**
	 * If present, match VNF instances with a VNF Instance Name listed in this
	 * attribute. The attributes \&quot;vnfInstanceIds\&quot; and
	 * \&quot;vnfInstanceNames\&quot; are alternatives to reference to particular
	 * VNF Instances in a filter. They should not be used both in the same filter
	 * instance, but one alternative should be chosen.
	 **/
	public InlineResponse200 vnfInstanceNames(List<String> vnfInstanceNames) {
		this.vnfInstanceNames = vnfInstanceNames;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances with a VNF Instance Name listed in this attribute. The attributes \"vnfInstanceIds\" and \"vnfInstanceNames\" are alternatives to reference to particular VNF Instances in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. ")
	@JsonProperty("vnfInstanceNames")
	public List<String> getVnfInstanceNames() {
		return vnfInstanceNames;
	}

	public void setVnfInstanceNames(List<String> vnfInstanceNames) {
		this.vnfInstanceNames = vnfInstanceNames;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse200 inlineResponse200 = (InlineResponse200) o;
		return Objects.equals(id, inlineResponse200.id) &&
				Objects.equals(vnfInstanceName, inlineResponse200.vnfInstanceName) &&
				Objects.equals(vnfInstanceDescription, inlineResponse200.vnfInstanceDescription) &&
				Objects.equals(vnfdId, inlineResponse200.vnfdId) &&
				Objects.equals(vnfProvider, inlineResponse200.vnfProvider) &&
				Objects.equals(vnfProductName, inlineResponse200.vnfProductName) &&
				Objects.equals(vnfSoftwareVersion, inlineResponse200.vnfSoftwareVersion) &&
				Objects.equals(vnfdVersion, inlineResponse200.vnfdVersion) &&
				Objects.equals(onboardedVnfPkgInfoId, inlineResponse200.onboardedVnfPkgInfoId) &&
				Objects.equals(vnfConfigurableProperties, inlineResponse200.vnfConfigurableProperties) &&
				Objects.equals(instantiationState, inlineResponse200.instantiationState) &&
				Objects.equals(instantiatedVnfInfo, inlineResponse200.instantiatedVnfInfo) &&
				Objects.equals(vnfInstanceIds, inlineResponse200.vnfInstanceIds) &&
				Objects.equals(vnfInstanceNames, inlineResponse200.vnfInstanceNames);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfInstanceName, vnfInstanceDescription, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion, onboardedVnfPkgInfoId, vnfConfigurableProperties, instantiationState, instantiatedVnfInfo, vnfInstanceIds, vnfInstanceNames);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse200 {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
		sb.append("    onboardedVnfPkgInfoId: ").append(toIndentedString(onboardedVnfPkgInfoId)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    instantiationState: ").append(toIndentedString(instantiationState)).append("\n");
		sb.append("    instantiatedVnfInfo: ").append(toIndentedString(instantiatedVnfInfo)).append("\n");
		sb.append("    vnfInstanceIds: ").append(toIndentedString(vnfInstanceIds)).append("\n");
		sb.append("    vnfInstanceNames: ").append(toIndentedString(vnfInstanceNames)).append("\n");
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
