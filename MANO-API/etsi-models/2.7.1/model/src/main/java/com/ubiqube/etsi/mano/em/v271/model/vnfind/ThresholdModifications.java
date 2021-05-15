package com.ubiqube.etsi.mano.em.v271.model.vnfind;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.SubscriptionAuthentication;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents modifications to a threshold. It shall comply with the provisions defined in table 6.5.2.11-1. 
 */
@ApiModel(description = "This type represents modifications to a threshold. It shall comply with the provisions defined in table 6.5.2.11-1. ")
@Validated

public class ThresholdModifications   {
  @JsonProperty("callbackUri")
  private String callbackUri = null;

  @JsonProperty("authentication")
  private SubscriptionAuthentication authentication = null;

  public ThresholdModifications callbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
    return this;
  }

  /**
   * New value of the \"callbackUri\" attribute. The value \"null\" is not permitted. See note. 
   * @return callbackUri
  **/
  @ApiModelProperty(value = "New value of the \"callbackUri\" attribute. The value \"null\" is not permitted. See note. ")


  public String getCallbackUri() {
    return callbackUri;
  }

  public void setCallbackUri(String callbackUri) {
    this.callbackUri = callbackUri;
  }

  public ThresholdModifications authentication(SubscriptionAuthentication authentication) {
    this.authentication = authentication;
    return this;
  }

  /**
   * New value of the \"authentication\" attribute, or \"null\" to remove the attribute. If present in a request body, these modifications shall be applied according to the rules of JSON Merge PATCH. This attribute shall not be present in response bodies. At least one of the attributes defined in this type shall be present in request bodies. 
   * @return authentication
  **/
  @ApiModelProperty(value = "New value of the \"authentication\" attribute, or \"null\" to remove the attribute. If present in a request body, these modifications shall be applied according to the rules of JSON Merge PATCH. This attribute shall not be present in response bodies. At least one of the attributes defined in this type shall be present in request bodies. ")

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
    ThresholdModifications thresholdModifications = (ThresholdModifications) o;
    return Objects.equals(this.callbackUri, thresholdModifications.callbackUri) &&
        Objects.equals(this.authentication, thresholdModifications.authentication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callbackUri, authentication);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThresholdModifications {\n");
    
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

