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
package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.ConstraintResourceRef;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information regarding a VIM selection constraint. A set of such constraints may be sent by the VNFM to the NFVO to influence the VIM selection decisions made by the NFVO as part of the granting process. 
 */
@Schema(description = "This type provides information regarding a VIM selection constraint. A set of such constraints may be sent by the VNFM to the NFVO to influence the VIM selection decisions made by the NFVO as part of the granting process. ")
@Validated


public class VimConstraint   {
  @JsonProperty("sameResourceGroup")
  private Boolean sameResourceGroup = null;

  @JsonProperty("resource")
  @Valid
  private List<ConstraintResourceRef> resource = new ArrayList<>();

  public VimConstraint sameResourceGroup(Boolean sameResourceGroup) {
    this.sameResourceGroup = sameResourceGroup;
    return this;
  }

  /**
   * If present and set to true, this signals that the constraint applies not only to the same VIM connection, but also to the same infrastructure resource group. 
   * @return sameResourceGroup
   **/
  @Schema(description = "If present and set to true, this signals that the constraint applies not only to the same VIM connection, but also to the same infrastructure resource group. ")
  
    public Boolean isSameResourceGroup() {
    return sameResourceGroup;
  }

  public void setSameResourceGroup(Boolean sameResourceGroup) {
    this.sameResourceGroup = sameResourceGroup;
  }

  public VimConstraint resource(List<ConstraintResourceRef> resource) {
    this.resource = resource;
    return this;
  }

  public VimConstraint addResourceItem(ConstraintResourceRef resourceItem) {
    this.resource.add(resourceItem);
    return this;
  }

  /**
   * References to resources in the constraint rule. The NFVO shall ensure that all resources in this list are managed through the same VIM connection. If \"sameResourceGroup\" is set to true, the NFVO shall further ensure that all resources in this list are part of the same infrastructure resource group in that VIM connection. 
   * @return resource
   **/
  @Schema(required = true, description = "References to resources in the constraint rule. The NFVO shall ensure that all resources in this list are managed through the same VIM connection. If \"sameResourceGroup\" is set to true, the NFVO shall further ensure that all resources in this list are part of the same infrastructure resource group in that VIM connection. ")
      @NotNull
    @Valid
  @Size(min=2)   public List<ConstraintResourceRef> getResource() {
    return resource;
  }

  public void setResource(List<ConstraintResourceRef> resource) {
    this.resource = resource;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VimConstraint vimConstraint = (VimConstraint) o;
    return Objects.equals(this.sameResourceGroup, vimConstraint.sameResourceGroup) &&
        Objects.equals(this.resource, vimConstraint.resource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sameResourceGroup, resource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimConstraint {\n");
    
    sb.append("    sameResourceGroup: ").append(toIndentedString(sameResourceGroup)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
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
