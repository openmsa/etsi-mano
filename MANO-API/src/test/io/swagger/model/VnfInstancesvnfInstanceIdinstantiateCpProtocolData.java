package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * This type represents network protocol data.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents network protocol data.  ")

public class VnfInstancesvnfInstanceIdinstantiateCpProtocolData {

	public enum LayerProtocolEnum {

		IP_OVER_ETHERNET(String.valueOf("IP_OVER_ETHERNET"));

		private final String value;

		LayerProtocolEnum(String v) {
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
		public static LayerProtocolEnum fromValue(String v) {
			for (final LayerProtocolEnum b : LayerProtocolEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid LayerProtocolEnum layerProtocol = null;
	private @Valid VnfInstancesvnfInstanceIdinstantiateIpOverEthernet ipOverEthernet = null;

	/**
	 * Identifier of layer(s) and protocol(s). This attribute allows to signal the
	 * addition of further types of layer and protocol in future versions of the
	 * present document in a backwards-compatible way. In the current version of the
	 * present document, only IP over Ethernet is supported.
	 **/
	public VnfInstancesvnfInstanceIdinstantiateCpProtocolData layerProtocol(LayerProtocolEnum layerProtocol) {
		this.layerProtocol = layerProtocol;
		return this;
	}

	@ApiModelProperty(required = true, value = "Identifier of layer(s) and protocol(s). This attribute allows to signal the addition of further types of layer and protocol in future versions of the present document in a backwards-compatible way. In the current version of the present document, only IP over Ethernet is supported. ")
	@JsonProperty("layerProtocol")
	@NotNull
	public LayerProtocolEnum getLayerProtocol() {
		return layerProtocol;
	}

	public void setLayerProtocol(LayerProtocolEnum layerProtocol) {
		this.layerProtocol = layerProtocol;
	}

	/**
	 **/
	public VnfInstancesvnfInstanceIdinstantiateCpProtocolData ipOverEthernet(VnfInstancesvnfInstanceIdinstantiateIpOverEthernet ipOverEthernet) {
		this.ipOverEthernet = ipOverEthernet;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("ipOverEthernet")
	public VnfInstancesvnfInstanceIdinstantiateIpOverEthernet getIpOverEthernet() {
		return ipOverEthernet;
	}

	public void setIpOverEthernet(VnfInstancesvnfInstanceIdinstantiateIpOverEthernet ipOverEthernet) {
		this.ipOverEthernet = ipOverEthernet;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesvnfInstanceIdinstantiateCpProtocolData vnfInstancesvnfInstanceIdinstantiateCpProtocolData = (VnfInstancesvnfInstanceIdinstantiateCpProtocolData) o;
		return Objects.equals(layerProtocol, vnfInstancesvnfInstanceIdinstantiateCpProtocolData.layerProtocol) &&
				Objects.equals(ipOverEthernet, vnfInstancesvnfInstanceIdinstantiateCpProtocolData.ipOverEthernet);
	}

	@Override
	public int hashCode() {
		return Objects.hash(layerProtocol, ipOverEthernet);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesvnfInstanceIdinstantiateCpProtocolData {\n");

		sb.append("    layerProtocol: ").append(toIndentedString(layerProtocol)).append("\n");
		sb.append("    ipOverEthernet: ").append(toIndentedString(ipOverEthernet)).append("\n");
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
