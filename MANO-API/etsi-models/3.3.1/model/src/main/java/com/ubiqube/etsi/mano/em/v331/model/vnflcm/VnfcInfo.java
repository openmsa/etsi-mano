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
 * This type represents the information about a VNFC instance that is part of a VNF instance. It shall comply with the provisions defined in table 5.5.3.23-1. 
 */
@Schema(description = "This type represents the information about a VNFC instance that is part of a VNF instance. It shall comply with the provisions defined in table 5.5.3.23-1. ")
@Validated


public class VnfcInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("vnfcResourceInfoId")
  private String vnfcResourceInfoId = null;

  @JsonProperty("vnfcState")
  private String vnfcState = null;

  @JsonProperty("vnfcConfigurableProperties")
  private KeyValuePairs vnfcConfigurableProperties = null;

  public VnfcInfo id(String id) {
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

  public VnfcInfo vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * Get vduId
   * @return vduId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }

  public VnfcInfo vnfcResourceInfoId(String vnfcResourceInfoId) {
    this.vnfcResourceInfoId = vnfcResourceInfoId;
    return this;
  }

  /**
   * Get vnfcResourceInfoId
   * @return vnfcResourceInfoId
   **/
  @Schema(description = "")
  
    public String getVnfcResourceInfoId() {
    return vnfcResourceInfoId;
  }

  public void setVnfcResourceInfoId(String vnfcResourceInfoId) {
    this.vnfcResourceInfoId = vnfcResourceInfoId;
  }

  public VnfcInfo vnfcState(String vnfcState) {
    this.vnfcState = vnfcState;
    return this;
  }

  /**
   * Get vnfcState
   * @return vnfcState
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfcState() {
    return vnfcState;
  }

  public void setVnfcState(String vnfcState) {
    this.vnfcState = vnfcState;
  }

  public VnfcInfo vnfcConfigurableProperties(KeyValuePairs vnfcConfigurableProperties) {
    this.vnfcConfigurableProperties = vnfcConfigurableProperties;
    return this;
  }

  /**
   * Get vnfcConfigurableProperties
   * @return vnfcConfigurableProperties
   **/
  @Schema(description = "")
  
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
    VnfcInfo vnfcInfo = (VnfcInfo) o;
    return Objects.equals(this.id, vnfcInfo.id) &&
        Objects.equals(this.vduId, vnfcInfo.vduId) &&
        Objects.equals(this.vnfcResourceInfoId, vnfcInfo.vnfcResourceInfoId) &&
        Objects.equals(this.vnfcState, vnfcInfo.vnfcState) &&
        Objects.equals(this.vnfcConfigurableProperties, vnfcInfo.vnfcConfigurableProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vduId, vnfcResourceInfoId, vnfcState, vnfcConfigurableProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
    sb.append("    vnfcResourceInfoId: ").append(toIndentedString(vnfcResourceInfoId)).append("\n");
    sb.append("    vnfcState: ").append(toIndentedString(vnfcState)).append("\n");
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
