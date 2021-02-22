package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents modifications of an entry in an array of \&quot;VnfcInfo\&quot; objects. It shall comply with the provisions defined in table 5.5.3.24-1. 
 */
@Schema(description = "This type represents modifications of an entry in an array of \"VnfcInfo\" objects. It shall comply with the provisions defined in table 5.5.3.24-1. ")
@Validated


public class VnfcInfoModifications   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfcConfigurableProperties")
  private KeyValuePairs vnfcConfigurableProperties = null;

  public VnfcInfoModifications id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfcInfoModifications vnfcConfigurableProperties(KeyValuePairs vnfcConfigurableProperties) {
    this.vnfcConfigurableProperties = vnfcConfigurableProperties;
    return this;
  }

  /**
   * Get vnfcConfigurableProperties
   * @return vnfcConfigurableProperties
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public KeyValuePairs getVnfcConfigurableProperties() {
    return vnfcConfigurableProperties;
  }

  public void setVnfcConfigurableProperties(KeyValuePairs vnfcConfigurableProperties) {
    this.vnfcConfigurableProperties = vnfcConfigurableProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfcInfoModifications vnfcInfoModifications = (VnfcInfoModifications) o;
    return Objects.equals(this.id, vnfcInfoModifications.id) &&
        Objects.equals(this.vnfcConfigurableProperties, vnfcInfoModifications.vnfcConfigurableProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfcConfigurableProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcInfoModifications {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfcConfigurableProperties: ").append(toIndentedString(vnfcConfigurableProperties)).append("\n");
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
