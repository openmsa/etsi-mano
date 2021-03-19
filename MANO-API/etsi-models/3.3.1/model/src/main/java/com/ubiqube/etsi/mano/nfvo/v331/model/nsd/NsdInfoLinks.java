/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nsd.Link;
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
