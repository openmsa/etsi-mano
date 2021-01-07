package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.grant.ResourceDefinitionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;This type provides information of an existing or proposed resource used by the application. Refer to clause 9.5.3.2 of ETSI GS NFV-SOL 003 &#x27;
 */
@ApiModel(description = "'This type provides information of an existing or proposed resource used by the application. Refer to clause 9.5.3.2 of ETSI GS NFV-SOL 003 '")
@Validated
public class ResourceDefinition   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("type")
  private ResourceDefinitionType type = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("resourceTemplateId")
  private String resourceTemplateId = null;

  @JsonProperty("resource")
  private Resource resource = null;

  public ResourceDefinition id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ResourceDefinition type(ResourceDefinitionType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ResourceDefinitionType getType() {
    return type;
  }

  public void setType(ResourceDefinitionType type) {
    this.type = type;
  }

  public ResourceDefinition vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * Get vduId
   * @return vduId
  **/
  @ApiModelProperty(value = "")
  
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
   * Get resourceTemplateId
   * @return resourceTemplateId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getResourceTemplateId() {
    return resourceTemplateId;
  }

  public void setResourceTemplateId(String resourceTemplateId) {
    this.resourceTemplateId = resourceTemplateId;
  }

  public ResourceDefinition resource(Resource resource) {
    this.resource = resource;
    return this;
  }

  /**
   * Get resource
   * @return resource
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Resource getResource() {
    return resource;
  }

  public void setResource(Resource resource) {
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
