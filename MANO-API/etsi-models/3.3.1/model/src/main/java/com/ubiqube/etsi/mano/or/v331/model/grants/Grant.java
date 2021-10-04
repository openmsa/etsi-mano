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
package com.ubiqube.etsi.mano.or.v331.model.grants;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.or.v331.model.grants.GrantLinks;
import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a grant. It shall comply with the provisions defined in table 7.6.2.3-1.
 */
@Schema(description = "This type represents a grant. It shall comply with the provisions defined in table 7.6.2.3-1.")
@Validated


public class Grant   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("nsInstanceId")
  private String nsInstanceId = null;

  @JsonProperty("nsLcmOpOccId")
  private String nsLcmOpOccId = null;

  @JsonProperty("additionalParams")
  private Map<String, String> additionalParams = null;

  @JsonProperty("_links")
  private GrantLinks _links = null;

  public Grant id(String id) {
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

  public Grant nsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
    return this;
  }

  /**
   * Get nsInstanceId
   * @return nsInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsInstanceId() {
    return nsInstanceId;
  }

  public void setNsInstanceId(String nsInstanceId) {
    this.nsInstanceId = nsInstanceId;
  }

  public Grant nsLcmOpOccId(String nsLcmOpOccId) {
    this.nsLcmOpOccId = nsLcmOpOccId;
    return this;
  }

  /**
   * Get nsLcmOpOccId
   * @return nsLcmOpOccId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsLcmOpOccId() {
    return nsLcmOpOccId;
  }

  public void setNsLcmOpOccId(String nsLcmOpOccId) {
    this.nsLcmOpOccId = nsLcmOpOccId;
  }

  public Grant additionalParams(Map<String, String> additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
   **/
  @Schema(description = "")
  
    @Valid
    public Map<String, String> getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(Map<String, String> additionalParams) {
    this.additionalParams = additionalParams;
  }

  public Grant _links(GrantLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public GrantLinks getLinks() {
    return _links;
  }

  public void setLinks(GrantLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Grant grant = (Grant) o;
    return Objects.equals(this.id, grant.id) &&
        Objects.equals(this.nsInstanceId, grant.nsInstanceId) &&
        Objects.equals(this.nsLcmOpOccId, grant.nsLcmOpOccId) &&
        Objects.equals(this.additionalParams, grant.additionalParams) &&
        Objects.equals(this._links, grant._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nsInstanceId, nsLcmOpOccId, additionalParams, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Grant {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nsInstanceId: ").append(toIndentedString(nsInstanceId)).append("\n");
    sb.append("    nsLcmOpOccId: ").append(toIndentedString(nsLcmOpOccId)).append("\n");
    sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
