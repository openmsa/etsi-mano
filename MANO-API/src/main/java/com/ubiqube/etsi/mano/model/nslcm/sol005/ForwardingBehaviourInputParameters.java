package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents provides input parameters to configure the forwarding
 * behaviour. It shall comply with the provisions defined in Table 6.5.3.73-1.
 */
@ApiModel(description = "This type represents provides input parameters to configure the forwarding behaviour.  It shall comply with the provisions defined in Table 6.5.3.73-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-05T16:49:58.135+01:00")

public class ForwardingBehaviourInputParameters {
	/**
	 * May be included if forwarding behaviour is equal to LB. Shall not be included
	 * otherwise. Permitted values: * ROUND_ROBIN * LEAST_CONNECTION * LEAST_TRAFFIC
	 * * LEAST_RESPONSE_TIME * CHAINED_FAILOVER * SOURCE_IP_HASH * SOURCE_MAC_HASH
	 */
	public enum AlgortihmNameEnum {
		ROUND_ROBIN("ROUND_ROBIN"),

		LEAST_CONNECTION("LEAST_CONNECTION"),

		LEAST_TRAFFIC("LEAST_TRAFFIC"),

		LEAST_RESPONSE_TIME("LEAST_RESPONSE_TIME"),

		CHAINED_FAILOVER("CHAINED_FAILOVER"),

		SOURCE_IP_HASH("SOURCE_IP_HASH"),

		SOURCE_MAC_HASH("SOURCE_MAC_HASH");

		private final String value;

		AlgortihmNameEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AlgortihmNameEnum fromValue(final String text) {
			for (final AlgortihmNameEnum b : AlgortihmNameEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("algortihmName")
	private AlgortihmNameEnum algortihmName = null;

	@JsonProperty("algorithmWeights")
	@Valid
	private List<Integer> algorithmWeights = null;

	public ForwardingBehaviourInputParameters algortihmName(final AlgortihmNameEnum algortihmName) {
		this.algortihmName = algortihmName;
		return this;
	}

	/**
	 * May be included if forwarding behaviour is equal to LB. Shall not be included
	 * otherwise. Permitted values: * ROUND_ROBIN * LEAST_CONNECTION * LEAST_TRAFFIC
	 * * LEAST_RESPONSE_TIME * CHAINED_FAILOVER * SOURCE_IP_HASH * SOURCE_MAC_HASH
	 * 
	 * @return algortihmName
	 **/
	@ApiModelProperty(value = "May be included if forwarding behaviour is equal to LB. Shall not be included otherwise. Permitted values: * ROUND_ROBIN * LEAST_CONNECTION * LEAST_TRAFFIC * LEAST_RESPONSE_TIME * CHAINED_FAILOVER * SOURCE_IP_HASH * SOURCE_MAC_HASH ")

	public AlgortihmNameEnum getAlgortihmName() {
		return algortihmName;
	}

	public void setAlgortihmName(final AlgortihmNameEnum algortihmName) {
		this.algortihmName = algortihmName;
	}

	public ForwardingBehaviourInputParameters algorithmWeights(final List<Integer> algorithmWeights) {
		this.algorithmWeights = algorithmWeights;
		return this;
	}

	public ForwardingBehaviourInputParameters addAlgorithmWeightsItem(final Integer algorithmWeightsItem) {
		if (this.algorithmWeights == null) {
			this.algorithmWeights = new ArrayList<>();
		}
		this.algorithmWeights.add(algorithmWeightsItem);
		return this;
	}

	/**
	 * Percentage of messages sent to a CP instance. May be included if applicable
	 * to the algorithm. If applicable to the algorithm but not provided, default
	 * values determined by the VIM or NFVI are expected to be used. Weight applies
	 * to the CP instances in the order they have been created.
	 * 
	 * @return algorithmWeights
	 **/
	@ApiModelProperty(value = "Percentage of messages sent to a CP instance. May be included if applicable to  the algorithm. If applicable to the algorithm but not provided, default values determined by  the VIM or NFVI are expected to be used. Weight applies to the CP instances in the order they have been created. ")

	public List<Integer> getAlgorithmWeights() {
		return algorithmWeights;
	}

	public void setAlgorithmWeights(final List<Integer> algorithmWeights) {
		this.algorithmWeights = algorithmWeights;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ForwardingBehaviourInputParameters forwardingBehaviourInputParameters = (ForwardingBehaviourInputParameters) o;
		return Objects.equals(this.algortihmName, forwardingBehaviourInputParameters.algortihmName) &&
				Objects.equals(this.algorithmWeights, forwardingBehaviourInputParameters.algorithmWeights);
	}

	@Override
	public int hashCode() {
		return Objects.hash(algortihmName, algorithmWeights);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ForwardingBehaviourInputParameters {\n");

		sb.append("    algortihmName: ").append(toIndentedString(algortihmName)).append("\n");
		sb.append("    algorithmWeights: ").append(toIndentedString(algorithmWeights)).append("\n");
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
