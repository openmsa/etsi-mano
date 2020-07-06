package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type describes the additional affinity or anti-affinity rule applicable
 * between the VNF instances to be instantiated in the NS instantiation
 * operation request or between the VNF instances to be instantiated in the NS
 * instantiation operation request and the existing VNF instances..
 */
@ApiModel(description = "This type describes the additional affinity or anti-affinity rule applicable between the VNF instances to be instantiated in the NS instantiation operation request or between the VNF instances to be instantiated in the NS instantiation operation request and the existing VNF instances.. ")
@Validated


public class AffinityOrAntiAffinityRule {
	@JsonProperty("vnfdId")
	@Valid
	private List<String> vnfdId = null;

	@JsonProperty("vnfProfileId")
	@Valid
	private List<String> vnfProfileId = null;

	@JsonProperty("vnfInstanceId")
	@Valid
	private List<String> vnfInstanceId = null;

	/**
	 * The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY.
	 */
	public enum AffinityOrAntiAffiintyEnum {
		AFFINITY("AFFINITY"),

		ANTI_AFFINITY("ANTI_AFFINITY");

		private final String value;

		AffinityOrAntiAffiintyEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AffinityOrAntiAffiintyEnum fromValue(final String text) {
			for (final AffinityOrAntiAffiintyEnum b : AffinityOrAntiAffiintyEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("affinityOrAntiAffiinty")
	private AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty = null;

	/**
	 * Specifies the scope of the rule where the placement constraint applies.
	 * Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE.
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

	public AffinityOrAntiAffinityRule vnfdId(final List<String> vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	public AffinityOrAntiAffinityRule addVnfdIdItem(final String vnfdIdItem) {
		if (this.vnfdId == null) {
			this.vnfdId = new ArrayList<>();
		}
		this.vnfdId.add(vnfdIdItem);
		return this;
	}

	/**
	 * Reference to a VNFD. When the VNFD which is not used to instantiate VNF, it
	 * presents all VNF instances of this type as the subjects of the affinity or
	 * anti-affinity rule. The VNF instance which the VNFD presents is not necessary
	 * as a part of the NS to be instantiated.
	 * 
	 * @return vnfdId
	 **/
	@ApiModelProperty(value = "Reference to a VNFD. When the VNFD which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VNFD presents is not necessary as a part of the NS to be instantiated. ")

	public List<String> getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final List<String> vnfdId) {
		this.vnfdId = vnfdId;
	}

	public AffinityOrAntiAffinityRule vnfProfileId(final List<String> vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
		return this;
	}

	public AffinityOrAntiAffinityRule addVnfProfileIdItem(final String vnfProfileIdItem) {
		if (this.vnfProfileId == null) {
			this.vnfProfileId = new ArrayList<>();
		}
		this.vnfProfileId.add(vnfProfileIdItem);
		return this;
	}

	/**
	 * Reference to a vnfProfile defined in the NSD. At least one VnfProfile which
	 * is used to instantiate VNF for the NS to be instantiated as the subject of
	 * the affinity or anti-affinity rule shall be present. When the VnfProfile
	 * which is not used to instantiate VNF, it presents all VNF instances of this
	 * type as the subjects of the affinity or anti-affinity rule. The VNF instance
	 * which the VnfProfile presents is not necessary as a part of the NS to be
	 * instantiated.
	 * 
	 * @return vnfProfileId
	 **/
	@ApiModelProperty(value = "Reference to a vnfProfile defined in the NSD. At least one VnfProfile which is used to instantiate VNF for the NS to be instantiated as the subject of the affinity or anti-affinity rule shall be present. When the VnfProfile which is not used to instantiate VNF, it presents all VNF instances of this type as the subjects of the affinity or anti-affinity rule. The VNF instance which the VnfProfile presents is not necessary as a part of the NS to be instantiated. ")

	public List<String> getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final List<String> vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	public AffinityOrAntiAffinityRule vnfInstanceId(final List<String> vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	public AffinityOrAntiAffinityRule addVnfInstanceIdItem(final String vnfInstanceIdItem) {
		if (this.vnfInstanceId == null) {
			this.vnfInstanceId = new ArrayList<>();
		}
		this.vnfInstanceId.add(vnfInstanceIdItem);
		return this;
	}

	/**
	 * Reference to the existing VNF instance as the subject of the affinity or
	 * anti-affinity rule. The existing VNF instance is not necessary as a part of
	 * the NS to be instantiated.
	 * 
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(value = "Reference to the existing VNF instance as the subject of the affinity or anti-affinity rule. The existing VNF instance is not necessary as a part of the NS to be instantiated. ")

	public List<String> getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final List<String> vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public AffinityOrAntiAffinityRule affinityOrAntiAffiinty(final AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty) {
		this.affinityOrAntiAffiinty = affinityOrAntiAffiinty;
		return this;
	}

	/**
	 * The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY.
	 * 
	 * @return affinityOrAntiAffiinty
	 **/
	@ApiModelProperty(required = true, value = "The type of the constraint. Permitted values: AFFINITY ANTI_AFFINITY. ")
	@NotNull

	public AffinityOrAntiAffiintyEnum getAffinityOrAntiAffiinty() {
		return affinityOrAntiAffiinty;
	}

	public void setAffinityOrAntiAffiinty(final AffinityOrAntiAffiintyEnum affinityOrAntiAffiinty) {
		this.affinityOrAntiAffiinty = affinityOrAntiAffiinty;
	}

	public AffinityOrAntiAffinityRule scope(final ScopeEnum scope) {
		this.scope = scope;
		return this;
	}

	/**
	 * Specifies the scope of the rule where the placement constraint applies.
	 * Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE.
	 * 
	 * @return scope
	 **/
	@ApiModelProperty(required = true, value = "Specifies the scope of the rule where the placement constraint applies. Permitted values: NFVI_POP ZONE ZONE_GROUP NFVI_NODE. ")
	@NotNull

	public ScopeEnum getScope() {
		return scope;
	}

	public void setScope(final ScopeEnum scope) {
		this.scope = scope;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AffinityOrAntiAffinityRule affinityOrAntiAffinityRule = (AffinityOrAntiAffinityRule) o;
		return Objects.equals(this.vnfdId, affinityOrAntiAffinityRule.vnfdId) &&
				Objects.equals(this.vnfProfileId, affinityOrAntiAffinityRule.vnfProfileId) &&
				Objects.equals(this.vnfInstanceId, affinityOrAntiAffinityRule.vnfInstanceId) &&
				Objects.equals(this.affinityOrAntiAffiinty, affinityOrAntiAffinityRule.affinityOrAntiAffiinty) &&
				Objects.equals(this.scope, affinityOrAntiAffinityRule.scope);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfdId, vnfProfileId, vnfInstanceId, affinityOrAntiAffiinty, scope);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AffinityOrAntiAffinityRule {\n");

		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    affinityOrAntiAffiinty: ").append(toIndentedString(affinityOrAntiAffiinty)).append("\n");
		sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
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
