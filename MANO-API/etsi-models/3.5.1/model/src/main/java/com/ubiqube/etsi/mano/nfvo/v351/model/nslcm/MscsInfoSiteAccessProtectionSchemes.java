package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.LocationConstraints;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MscsInfoSiteAccessProtectionSchemes
 */
@Validated


public class MscsInfoSiteAccessProtectionSchemes   {
  @JsonProperty("locationConstraints")
  private LocationConstraints locationConstraints = null;

  /**
   * Defines the protection scheme. Permitted values: - UNPROTECTED: to indicate no protection. - ONE_TO_ONE: to indicate an active-passive access protection. - ONE_PLUS_ONE: to indicate an active-active access protection. - ONE_TO_N: to indicate an N active to 1 passive access protection. 
   */
  public enum ProtectionSchemeEnum {
    UNPROTECTED("UNPROTECTED"),
    
    ONE_TO_ONE("ONE_TO_ONE"),
    
    ONE_PLUS_ONE("ONE_PLUS_ONE"),
    
    ONE_TO_N("ONE_TO_N");

    private String value;

    ProtectionSchemeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ProtectionSchemeEnum fromValue(String text) {
      for (ProtectionSchemeEnum b : ProtectionSchemeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("protectionScheme")
  private ProtectionSchemeEnum protectionScheme = null;

  public MscsInfoSiteAccessProtectionSchemes locationConstraints(LocationConstraints locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }

  /**
   * Get locationConstraints
   * @return locationConstraints
   **/
  @Schema(description = "")
  
    @Valid
    public LocationConstraints getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(LocationConstraints locationConstraints) {
    this.locationConstraints = locationConstraints;
  }

  public MscsInfoSiteAccessProtectionSchemes protectionScheme(ProtectionSchemeEnum protectionScheme) {
    this.protectionScheme = protectionScheme;
    return this;
  }

  /**
   * Defines the protection scheme. Permitted values: - UNPROTECTED: to indicate no protection. - ONE_TO_ONE: to indicate an active-passive access protection. - ONE_PLUS_ONE: to indicate an active-active access protection. - ONE_TO_N: to indicate an N active to 1 passive access protection. 
   * @return protectionScheme
   **/
  @Schema(description = "Defines the protection scheme. Permitted values: - UNPROTECTED: to indicate no protection. - ONE_TO_ONE: to indicate an active-passive access protection. - ONE_PLUS_ONE: to indicate an active-active access protection. - ONE_TO_N: to indicate an N active to 1 passive access protection. ")
  
    public ProtectionSchemeEnum getProtectionScheme() {
    return protectionScheme;
  }

  public void setProtectionScheme(ProtectionSchemeEnum protectionScheme) {
    this.protectionScheme = protectionScheme;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MscsInfoSiteAccessProtectionSchemes mscsInfoSiteAccessProtectionSchemes = (MscsInfoSiteAccessProtectionSchemes) o;
    return Objects.equals(this.locationConstraints, mscsInfoSiteAccessProtectionSchemes.locationConstraints) &&
        Objects.equals(this.protectionScheme, mscsInfoSiteAccessProtectionSchemes.protectionScheme);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locationConstraints, protectionScheme);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MscsInfoSiteAccessProtectionSchemes {\n");
    
    sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
    sb.append("    protectionScheme: ").append(toIndentedString(protectionScheme)).append("\n");
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
