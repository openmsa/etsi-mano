package com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * VnfInstanceSubscriptionFilterVnfProductsFromProviders
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T16:01:09.127+02:00")

public class VnfInstanceSubscriptionFilterVnfProductsFromProviders {
	@JsonProperty("vnfProvider")
	private String vnfProvider = null;

	@JsonProperty("vnfProducts")
	@Valid
	private List<VnfInstanceSubscriptionFilterVnfProducts> vnfProducts = null;

	public VnfInstanceSubscriptionFilterVnfProductsFromProviders vnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
		return this;
	}

	/**
	 * Name of the VNF provider to match.
	 * 
	 * @return vnfProvider
	 **/
	@ApiModelProperty(required = true, value = "Name of the VNF provider to match. ")
	@NotNull

	public String getVnfProvider() {
		return vnfProvider;
	}

	public void setVnfProvider(final String vnfProvider) {
		this.vnfProvider = vnfProvider;
	}

	public VnfInstanceSubscriptionFilterVnfProductsFromProviders vnfProducts(final List<VnfInstanceSubscriptionFilterVnfProducts> vnfProducts) {
		this.vnfProducts = vnfProducts;
		return this;
	}

	public VnfInstanceSubscriptionFilterVnfProductsFromProviders addVnfProductsItem(final VnfInstanceSubscriptionFilterVnfProducts vnfProductsItem) {
		if (this.vnfProducts == null) {
			this.vnfProducts = new ArrayList<>();
		}
		this.vnfProducts.add(vnfProductsItem);
		return this;
	}

	/**
	 * If present, match VNF instances that belong to VNF products with certain
	 * product names, from one particular provider.
	 * 
	 * @return vnfProducts
	 **/
	@ApiModelProperty(value = "If present, match VNF instances that belong to VNF products with certain product names, from one particular provider. ")

	@Valid

	public List<VnfInstanceSubscriptionFilterVnfProducts> getVnfProducts() {
		return vnfProducts;
	}

	public void setVnfProducts(final List<VnfInstanceSubscriptionFilterVnfProducts> vnfProducts) {
		this.vnfProducts = vnfProducts;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstanceSubscriptionFilterVnfProductsFromProviders vnfInstanceSubscriptionFilterVnfProductsFromProviders = (VnfInstanceSubscriptionFilterVnfProductsFromProviders) o;
		return Objects.equals(this.vnfProvider, vnfInstanceSubscriptionFilterVnfProductsFromProviders.vnfProvider) &&
				Objects.equals(this.vnfProducts, vnfInstanceSubscriptionFilterVnfProductsFromProviders.vnfProducts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProvider, vnfProducts);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstanceSubscriptionFilterVnfProductsFromProviders {\n");

		sb.append("    vnfProvider: ").append(toIndentedString(vnfProvider)).append("\n");
		sb.append("    vnfProducts: ").append(toIndentedString(vnfProducts)).append("\n");
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
