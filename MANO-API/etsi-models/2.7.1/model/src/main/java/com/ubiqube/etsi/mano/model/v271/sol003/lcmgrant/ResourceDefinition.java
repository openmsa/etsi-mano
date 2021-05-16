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
package com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.ResourceHandle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information of an existing or proposed resource used by the VNF. 
 */
@ApiModel(description = "This type provides information of an existing or proposed resource used by the VNF. ")
@Validated


public class ResourceDefinition   {
  @JsonProperty("id")
  private String id = null;

  /**
   * Type of the resource definition referenced. Permitted values: * COMPUTE * VL * STORAGE * LINKPORT 
   */
  public enum TypeEnum {
    COMPUTE("COMPUTE"),
    
    VL("VL"),
    
    STORAGE("STORAGE"),
    
    LINKPORT("LINKPORT");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("resourceTemplateId")
  private String resourceTemplateId = null;

  @JsonProperty("resource")
  private ResourceHandle resource = null;

  public ResourceDefinition id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of this \"ResourceDefinition\" structure, unique at least within the scope of the \"GrantRequest\" structure. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this \"ResourceDefinition\" structure, unique at least within the scope of the \"GrantRequest\" structure. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ResourceDefinition type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Type of the resource definition referenced. Permitted values: * COMPUTE * VL * STORAGE * LINKPORT 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "Type of the resource definition referenced. Permitted values: * COMPUTE * VL * STORAGE * LINKPORT ")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public ResourceDefinition vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * Reference to the related VDU in the VNFD applicable to this resource. Shall only be present if a VDU is applicable to this resource. 
   * @return vduId
  **/
  @ApiModelProperty(value = "Reference to the related VDU in the VNFD applicable to this resource. Shall only be present if a VDU is applicable to this resource. ")


  public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }

  public ResourceDefinition resourceTemplateId(String resourceTemplateId) {
    this.resourceTemplateId = resourceTemplateId;
    return this;
  }

  /**
   * Reference to a resource template (VnfVirtualLinkDesc, VirtualComputeDesc, VnfExtCpd, VirtualStorageDesc) in the VNFD. 
   * @return resourceTemplateId
  **/
  @ApiModelProperty(value = "Reference to a resource template (VnfVirtualLinkDesc, VirtualComputeDesc, VnfExtCpd, VirtualStorageDesc) in the VNFD. ")


  public String getResourceTemplateId() {
    return resourceTemplateId;
  }

  public void setResourceTemplateId(String resourceTemplateId) {
    this.resourceTemplateId = resourceTemplateId;
  }

  public ResourceDefinition resource(ResourceHandle resource) {
    this.resource = resource;
    return this;
  }

  /**
   * Resource information for an existing resource. Shall be present for resources that are planned to be deleted or modified. Shall be absent otherwise. 
   * @return resource
  **/
  @ApiModelProperty(value = "Resource information for an existing resource. Shall be present for resources that are planned to be deleted or modified. Shall be absent otherwise. ")

  @Valid

  public ResourceHandle getResource() {
    return resource;
  }

  public void setResource(ResourceHandle resource) {
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
    ResourceDefinition resourceDefinition = (ResourceDefinition) o;
    return Objects.equals(this.id, resourceDefinition.id) &&
        Objects.equals(this.type, resourceDefinition.type) &&
        Objects.equals(this.vduId, resourceDefinition.vduId) &&
        Objects.equals(this.resourceTemplateId, resourceDefinition.resourceTemplateId) &&
        Objects.equals(this.resource, resourceDefinition.resource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, vduId, resourceTemplateId, resource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceDefinition {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
    sb.append("    resourceTemplateId: ").append(toIndentedString(resourceTemplateId)).append("\n");
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

