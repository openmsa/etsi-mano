package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents attribute modifications that were performed on an \&quot;Individual VNF instance\&quot; resource. The attributes that can be included consist of those requested to be modified explicitly in the \&quot;VnfInfoModificationRequest\&quot; data structure, and additional attributes of the \&quot;VnfInstance\&quot; data structure that were modified implicitly e.g. when modifying the referenced VNF package.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents attribute modifications that were performed on an \"Individual VNF instance\" resource. The attributes that can be included consist of those requested to be modified explicitly in the \"VnfInfoModificationRequest\" data structure, and additional attributes of the \"VnfInstance\" data structure that were modified implicitly e.g. when modifying the referenced VNF package. ")

public class VnfInfoModifications {

	private @Valid String vnfInstanceName = null;
	private @Valid String vnfInstanceDescription = null;
	private @Valid Object vnfConfigurableProperties = null;
	private @Valid Object metadata = null;
	private @Valid Object extensions = null;
	private @Valid List<VnfInstancesvnfInstanceIdVimConnectionInfo> vimConnectionInfo = new ArrayList<VnfInstancesvnfInstanceIdVimConnectionInfo>();
	private @Valid String vnfPkgId = null;
	private @Valid String vnfdId = null;
	private @Valid String vnfProvider = null;
	private @Valid String vnfProductName = null;
	private @Valid String vnfSoftwareVersion = null;
	private @Valid String vnfdVersion = null;

	/**
	 * If present, this attribute signals modifications of the
	 * \&quot;vnfInstanceName\&quot; attribute in \&quot;VnfInstance\&quot;.
	 **/
	public VnfInfoModifications vnfInstanceName(String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
		return this;
	}

	@ApiModelProperty(value = "If present, this attribute signals modifications of the \"vnfInstanceName\" attribute in \"VnfInstance\". ")
	@JsonProperty("vnfInstanceName")
	public String getVnfInstanceName() {
		return vnfInstanceName;
	}

	public void setVnfInstanceName(String vnfInstanceName) {
		this.vnfInstanceName = vnfInstanceName;
	}

	/**
	 * If present, this attribute signals modifications of the
	 * \&quot;vnfInstanceDescription\&quot; attribute in \&quot;VnfInstance\&quot;.
	 **/
	public VnfInfoModifications vnfInstanceDescription(String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
		return this;
	}

	@ApiModelProperty(value = "If present, this attribute signals modifications of the \"vnfInstanceDescription\" attribute in \"VnfInstance\". ")
	@JsonProperty("vnfInstanceDescription")
	public String getVnfInstanceDescription() {
		return vnfInstanceDescription;
	}

	public void setVnfInstanceDescription(String vnfInstanceDescription) {
		this.vnfInstanceDescription = vnfInstanceDescription;
	}

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInfoModifications vnfConfigurableProperties(Object vnfConfigurableProperties) {
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
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfInfoModifications metadata(Object metadata) {
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
	public VnfInfoModifications extensions(Object extensions) {
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
	 * If present, this attribute signals modifications of certain entries in the
	 * \&quot;vimConnectionInfo\&quot; attribute array in \&quot;VnfInstance\&quot;.
	 **/
	public VnfInfoModifications vimConnectionInfo(List<VnfInstancesvnfInstanceIdVimConnectionInfo> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
		return this;
	}

	@ApiModelProperty(value = "If present, this attribute signals modifications of certain entries in the \"vimConnectionInfo\" attribute array in \"VnfInstance\". ")
	@JsonProperty("vimConnectionInfo")
	public List<VnfInstancesvnfInstanceIdVimConnectionInfo> getVimConnectionInfo() {
		return vimConnectionInfo;
	}

	public void setVimConnectionInfo(List<VnfInstancesvnfInstanceIdVimConnectionInfo> vimConnectionInfo) {
		this.vimConnectionInfo = vimConnectionInfo;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInfoModifications vnfPkgId(String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vnfPkgId")
	public String getVnfPkgId() {
		return vnfPkgId;
	}

	public void setVnfPkgId(String vnfPkgId) {
		this.vnfPkgId = vnfPkgId;
	}

	/**
	 * An identifier with the intention of being globally unique.
	 **/
	public VnfInfoModifications vnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	@ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
	@JsonProperty("vnfdId")
	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(String vnfdId) {
		this.vnfdId = vnfdId;
	}

	/**
	 * If present, this attribute signals modifications of the
	 * \&quot;vnfProvider\&quot; attribute in \&quot;VnfInstance\&quot;. If present,
	 * this attribute (which depends on the value of the \&quot;vnfPkgId\&quot;
	 * attribute) was modified implicitly following a request to modify the
	 * \&quot;vnfPkgId\&quot; attribute, by copying the value of this attribute from
	 * the VNFD in the VNF Package identified by the \&quot;vnfPkgId” attribute.
	 **/
	public VnfInfoModifications vnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	@ApiModelProperty(value = "If present, this attribute signals modifications of the \"vnfProvider\" attribute in \"VnfInstance\". If present, this attribute (which depends on the value of the \"vnfPkgId\" attribute) was modified implicitly following a request to modify the \"vnfPkgId\" attribute, by copying the value of this attribute from the VNFD in the VNF Package identified by the \"vnfPkgId” attribute. ")
	@JsonProperty("vnfProvider")
	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	/**
	 * If present, this attribute signals modifications of the
	 * \&quot;vnfProductName\&quot; attribute in \&quot;VnfInstance\&quot;. If
	 * present, this attribute (which depends on the value of the
	 * \&quot;vnfPkgId\&quot; attribute) was modified implicitly following a request
	 * to modify the \&quot;vnfPkgId\&quot; attribute, by copying the value of this
	 * attribute from the VNFD in the VNF Package identified by the \&quot;vnfPkgId”
	 * attribute.
	 **/
	public VnfInfoModifications vnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	@ApiModelProperty(value = "If present, this attribute signals modifications of the \"vnfProductName\" attribute in \"VnfInstance\". If present, this attribute (which depends on the value of the \"vnfPkgId\" attribute) was modified implicitly following a request to modify the \"vnfPkgId\" attribute, by copying the value of this attribute from the VNFD in the VNF Package identified by the \"vnfPkgId” attribute. ")
	@JsonProperty("vnfProductName")
	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	/**
	 * A Version.
	 **/
	public VnfInfoModifications vnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	@ApiModelProperty(value = "A Version. ")
	@JsonProperty("vnfSoftwareVersion")
	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	/**
	 * A Version.
	 **/
	public VnfInfoModifications vnfdVersion(String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
		return this;
	}

	@ApiModelProperty(value = "A Version. ")
	@JsonProperty("vnfdVersion")
	public String getVnfdVersion() {
		return vnfdVersion;
	}

	public void setVnfdVersion(String vnfdVersion) {
		this.vnfdVersion = vnfdVersion;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInfoModifications vnfInfoModifications = (VnfInfoModifications) o;
		return Objects.equals(vnfInstanceName, vnfInfoModifications.vnfInstanceName) &&
				Objects.equals(vnfInstanceDescription, vnfInfoModifications.vnfInstanceDescription) &&
				Objects.equals(vnfConfigurableProperties, vnfInfoModifications.vnfConfigurableProperties) &&
				Objects.equals(metadata, vnfInfoModifications.metadata) &&
				Objects.equals(extensions, vnfInfoModifications.extensions) &&
				Objects.equals(vimConnectionInfo, vnfInfoModifications.vimConnectionInfo) &&
				Objects.equals(vnfPkgId, vnfInfoModifications.vnfPkgId) &&
				Objects.equals(vnfdId, vnfInfoModifications.vnfdId) &&
				Objects.equals(vnfProvider, vnfInfoModifications.vnfProvider) &&
				Objects.equals(vnfProductName, vnfInfoModifications.vnfProductName) &&
				Objects.equals(vnfSoftwareVersion, vnfInfoModifications.vnfSoftwareVersion) &&
				Objects.equals(vnfdVersion, vnfInfoModifications.vnfdVersion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceName, vnfInstanceDescription, vnfConfigurableProperties, metadata, extensions, vimConnectionInfo, vnfPkgId, vnfdId, vnfProvider, vnfProductName, vnfSoftwareVersion, vnfdVersion);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInfoModifications {\n");

		sb.append("    vnfInstanceName: ").append(toIndentedString(vnfInstanceName)).append("\n");
		sb.append("    vnfInstanceDescription: ").append(toIndentedString(vnfInstanceDescription)).append("\n");
		sb.append("    vnfConfigurableProperties: ").append(toIndentedString(vnfConfigurableProperties)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    extensions: ").append(toIndentedString(extensions)).append("\n");
		sb.append("    vimConnectionInfo: ").append(toIndentedString(vimConnectionInfo)).append("\n");
		sb.append("    vnfPkgId: ").append(toIndentedString(vnfPkgId)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersion: ").append(toIndentedString(vnfdVersion)).append("\n");
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
