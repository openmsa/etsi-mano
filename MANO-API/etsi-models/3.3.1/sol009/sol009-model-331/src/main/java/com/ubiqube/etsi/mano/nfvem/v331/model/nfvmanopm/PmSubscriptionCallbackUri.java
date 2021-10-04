package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The URI of the endpoint to send the notification to. 
 */
@Schema(description = "The URI of the endpoint to send the notification to. ")
@Validated


public class PmSubscriptionCallbackUri   {
  @JsonProperty("links")
  private String links = null;

  @JsonProperty("self")
  private Link self = null;

  public PmSubscriptionCallbackUri links(String links) {
    this.links = links;
    return this;
  }

  /**
   * Get links
   * @return links
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getLinks() {
    return links;
  }

  public void setLinks(String links) {
    this.links = links;
  }

  public PmSubscriptionCallbackUri self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PmSubscriptionCallbackUri pmSubscriptionCallbackUri = (PmSubscriptionCallbackUri) o;
    return Objects.equals(this.links, pmSubscriptionCallbackUri.links) &&
        Objects.equals(this.self, pmSubscriptionCallbackUri.self);
  }

  @Override
  public int hashCode() {
    return Objects.hash(links, self);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PmSubscriptionCallbackUri {\n");
    
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
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
