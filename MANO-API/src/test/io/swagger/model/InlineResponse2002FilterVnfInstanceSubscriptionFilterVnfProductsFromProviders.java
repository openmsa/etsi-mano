package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders {

	private @Valid String vnfProvider = null;
	private @Valid List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts> vnfProducts = new ArrayList<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts>();

	/**
	 * Name of the VNF provider to match.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders vnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	@ApiModelProperty(required = true, value = "Name of the VNF provider to match. ")
	@JsonProperty("vnfProvider")
	@NotNull
	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	/**
	 * If present, match VNF instances that belong to VNF products with certain
	 * product names, from one particular provider.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders vnfProducts(List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts> vnfProducts) {
		this.vnfProducts = vnfProducts;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances that belong to VNF products with certain product names, from one particular provider. ")
	@JsonProperty("vnfProducts")
	public List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts> getVnfProducts() {
		return vnfProducts;
	}

	public void setVnfProducts(List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts> vnfProducts) {
		this.vnfProducts = vnfProducts;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders inlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders = (InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders) o;
		return Objects.equals(vnfProvider, inlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders.vnfProvider) &&
				Objects.equals(vnfProducts, inlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders.vnfProducts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProvider, vnfProducts);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProductsFromProviders {\n");

		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProducts: ").append(toIndentedString(vnfProducts)).append("\n");
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
