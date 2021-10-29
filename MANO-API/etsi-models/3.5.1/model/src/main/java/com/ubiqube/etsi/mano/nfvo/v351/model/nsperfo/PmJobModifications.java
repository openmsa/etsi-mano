package com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsperfo.SubscriptionAuthentication;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents modifications to a PM job. It shall comply with the provisions defined in Table 7.5.2.12-1. NOTE: At least one of the attributes defined in this type shall be present in request bodies. 
 */
@Schema(description = "This type represents modifications to a PM job. It shall comply with the provisions defined in Table 7.5.2.12-1. NOTE: At least one of the attributes defined in this type shall be present in request bodies. ")
@Validated


public class PmJobModifications  implements AnyOfPmJobModifications {
  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("authentication")
  private SubscriptionAuthentication authentication = null;

  public PmJobModifications callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * Get callbackUri
   * @return callbackUri
   **/
  @Schema(description = "")
  
    public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public PmJobModifications authentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }

  /**
   * Get authentication
   * @return authentication
   **/
  @Schema(description = "")
  
    @Valid
    public SubscriptionAuthentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PmJobModifications pmJobModifications = (PmJobModifications) o;
    return Objects.equals(this.callbackUri, pmJobModifications.callbackUri) &&
        Objects.equals(this.authentication, pmJobModifications.authentication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callbackUri, authentication);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmJobModifications {\n");
    
    sb.append("    callbackUri: ").append(toIndentedString(callbackUri)).append("\n");
    sb.append("    authentication: ").append(toIndentedString(authentication)).append("\n");
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
