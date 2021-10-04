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
package com.ubiqube.etsi.mano.nfvo.v331.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnf.ExternalArtifactsAccessConfigArtifact;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the access configuration information for downloading external VNF package artifacts. The NFVO can obtain the external VNF package artifact file through the information provided in this structure, together with information provided in the manifest / VNFD. The data structure shall comply with the provisions defined in Table 9.5.2.10-1. If the data structure is part of a response body, security-sensitive attributes shall be excluded as specified in Table 9.5.2.10-1. 
 */
@Schema(description = "This type represents the access configuration information for downloading external VNF package artifacts. The NFVO can obtain the external VNF package artifact file through the information provided in this structure, together with information provided in the manifest / VNFD. The data structure shall comply with the provisions defined in Table 9.5.2.10-1. If the data structure is part of a response body, security-sensitive attributes shall be excluded as specified in Table 9.5.2.10-1. ")
@Validated


public class ExternalArtifactsAccessConfig   {
  @JsonProperty("artifact")
  private ExternalArtifactsAccessConfigArtifact artifact = null;

  public ExternalArtifactsAccessConfig artifact(ExternalArtifactsAccessConfigArtifact artifact) {
    this.artifact = artifact;
    return this;
  }

  /**
   * Get artifact
   * @return artifact
   **/
  @Schema(description = "")
  
    @Valid
    public ExternalArtifactsAccessConfigArtifact getArtifact() {
    return artifact;
  }

  public void setArtifact(ExternalArtifactsAccessConfigArtifact artifact) {
    this.artifact = artifact;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExternalArtifactsAccessConfig externalArtifactsAccessConfig = (ExternalArtifactsAccessConfig) o;
    return Objects.equals(this.artifact, externalArtifactsAccessConfig.artifact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifact);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExternalArtifactsAccessConfig {\n");
    
    sb.append("    artifact: ").append(toIndentedString(artifact)).append("\n");
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
