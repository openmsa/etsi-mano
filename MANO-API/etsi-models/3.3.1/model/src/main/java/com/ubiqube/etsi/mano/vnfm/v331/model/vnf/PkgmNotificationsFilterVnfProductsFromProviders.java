package com.ubiqube.etsi.mano.vnfm.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.PkgmNotificationsFilterVnfProducts;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PkgmNotificationsFilterVnfProductsFromProviders
 */
@Validated


public class PkgmNotificationsFilterVnfProductsFromProviders   {
  @JsonProperty("vnfProvider")
  private String vnfProvider = null;

  @JsonProperty("vnfProducts")
  @Valid
  private List<PkgmNotificationsFilterVnfProducts> vnfProducts = null;

  public PkgmNotificationsFilterVnfProductsFromProviders vnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
    return this;
  }

  /**
   * Get vnfProvider
   * @return vnfProvider
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfProvider() {
    return vnfProvider;
  }

  public void setVnfProvider(String vnfProvider) {
    this.vnfProvider = vnfProvider;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders vnfProducts(List<PkgmNotificationsFilterVnfProducts> vnfProducts) {
    this.vnfProducts = vnfProducts;
    return this;
  }

  public PkgmNotificationsFilterVnfProductsFromProviders addVnfProductsItem(PkgmNotificationsFilterVnfProducts vnfProductsItem) {
    if (this.vnfProducts == null) {
      this.vnfProducts = new ArrayList<>();
    }
    this.vnfProducts.add(vnfProductsItem);
    return this;
  }

  /**
   * If present, match VNF packages that contain VNF products with certain product names, from one particular provider. 
   * @return vnfProducts
   **/
  @Schema(description = "If present, match VNF packages that contain VNF products with certain product names, from one particular provider. ")
      @Valid
    public List<PkgmNotificationsFilterVnfProducts> getVnfProducts() {
    return vnfProducts;
  }

  public void setVnfProducts(List<PkgmNotificationsFilterVnfProducts> vnfProducts) {
    this.vnfProducts = vnfProducts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PkgmNotificationsFilterVnfProductsFromProviders pkgmNotificationsFilterVnfProductsFromProviders = (PkgmNotificationsFilterVnfProductsFromProviders) o;
    return Objects.equals(this.vnfProvider, pkgmNotificationsFilterVnfProductsFromProviders.vnfProvider) &&
        Objects.equals(this.vnfProducts, pkgmNotificationsFilterVnfProductsFromProviders.vnfProducts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfProvider, vnfProducts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PkgmNotificationsFilterVnfProductsFromProviders {\n");
    
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
