package com.ubiqube.etsi.mano.nfvo.v351.model.nsd;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsd.Link;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@Validated


public class NsdInfoLinks   {
  @JsonProperty("self")
  private Link self = null;

  @JsonProperty("nsd_content")
  private Link nsdContent = null;

  public NsdInfoLinks self(Link self) {
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

  public NsdInfoLinks nsdContent(Link nsdContent) {
    this.nsdContent = nsdContent;
    return this;
  }

  /**
   * Get nsdContent
   * @return nsdContent
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Link getNsdContent() {
    return nsdContent;
  }

  public void setNsdContent(Link nsdContent) {
    this.nsdContent = nsdContent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsdInfoLinks nsdInfoLinks = (NsdInfoLinks) o;
    return Objects.equals(this.self, nsdInfoLinks.self) &&
        Objects.equals(this.nsdContent, nsdInfoLinks.nsdContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, nsdContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    nsdContent: ").append(toIndentedString(nsdContent)).append("\n");
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
