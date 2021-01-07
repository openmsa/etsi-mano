package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.grant.GrantRequestLinks;
import com.ubiqube.etsi.mec.meo.v211.model.grant.GrantRequestOperation;
import com.ubiqube.etsi.mec.meo.v211.model.grant.KeyValuePairs;
import com.ubiqube.etsi.mec.meo.v211.model.grant.ResourceDefinition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;This type represents a grant request. Refer to clause 9.5.2.2 of ETSI GS NFV-SOL 003&#x27;
 */
@ApiModel(description = "'This type represents a grant request. Refer to clause 9.5.2.2 of ETSI GS NFV-SOL 003'")
@Validated
public class GrantRequest   {
  @JsonProperty("appInstanceId")
  private String appInstanceId = null;

  @JsonProperty("appLcmOpOccId")
  private String appLcmOpOccId = null;

  @JsonProperty("appDId")
  private String appDId = null;

  @JsonProperty("operation")
  private GrantRequestOperation operation = null;

  @JsonProperty("addResources")
  @Valid
  private List<ResourceDefinition> addResources = null;

  @JsonProperty("tempResources")
  @Valid
  private List<ResourceDefinition> tempResources = null;

  @JsonProperty("removeResources")
  @Valid
  private List<ResourceDefinition> removeResources = null;

  @JsonProperty("updateResources")
  @Valid
  private List<ResourceDefinition> updateResources = null;

  @JsonProperty("additionalParams")
  private KeyValuePairs additionalParams = null;

  @JsonProperty("_links")
  private GrantRequestLinks _links = null;

  public GrantRequest appInstanceId(String appInstanceId) {
    this.appInstanceId = appInstanceId;
    return this;
  }

  /**
   * Get appInstanceId
   * @return appInstanceId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppInstanceId() {
    return appInstanceId;
  }

  public void setAppInstanceId(String appInstanceId) {
    this.appInstanceId = appInstanceId;
  }

  public GrantRequest appLcmOpOccId(String appLcmOpOccId) {
    this.appLcmOpOccId = appLcmOpOccId;
    return this;
  }

  /**
   * Get appLcmOpOccId
   * @return appLcmOpOccId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppLcmOpOccId() {
    return appLcmOpOccId;
  }

  public void setAppLcmOpOccId(String appLcmOpOccId) {
    this.appLcmOpOccId = appLcmOpOccId;
  }

  public GrantRequest appDId(String appDId) {
    this.appDId = appDId;
    return this;
  }

  /**
   * Get appDId
   * @return appDId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getAppDId() {
    return appDId;
  }

  public void setAppDId(String appDId) {
    this.appDId = appDId;
  }

  public GrantRequest operation(GrantRequestOperation operation) {
    this.operation = operation;
    return this;
  }

  /**
   * Get operation
   * @return operation
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public GrantRequestOperation getOperation() {
    return operation;
  }

  public void setOperation(GrantRequestOperation operation) {
    this.operation = operation;
  }

  public GrantRequest addResources(List<ResourceDefinition> addResources) {
    this.addResources = addResources;
    return this;
  }

  public GrantRequest addAddResourcesItem(ResourceDefinition addResourcesItem) {
    if (this.addResources == null) {
      this.addResources = new ArrayList<>();
    }
    this.addResources.add(addResourcesItem);
    return this;
  }

  /**
   * Get addResources
   * @return addResources
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ResourceDefinition> getAddResources() {
    return addResources;
  }

  public void setAddResources(List<ResourceDefinition> addResources) {
    this.addResources = addResources;
  }

  public GrantRequest tempResources(List<ResourceDefinition> tempResources) {
    this.tempResources = tempResources;
    return this;
  }

  public GrantRequest addTempResourcesItem(ResourceDefinition tempResourcesItem) {
    if (this.tempResources == null) {
      this.tempResources = new ArrayList<>();
    }
    this.tempResources.add(tempResourcesItem);
    return this;
  }

  /**
   * Get tempResources
   * @return tempResources
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ResourceDefinition> getTempResources() {
    return tempResources;
  }

  public void setTempResources(List<ResourceDefinition> tempResources) {
    this.tempResources = tempResources;
  }

  public GrantRequest removeResources(List<ResourceDefinition> removeResources) {
    this.removeResources = removeResources;
    return this;
  }

  public GrantRequest addRemoveResourcesItem(ResourceDefinition removeResourcesItem) {
    if (this.removeResources == null) {
      this.removeResources = new ArrayList<>();
    }
    this.removeResources.add(removeResourcesItem);
    return this;
  }

  /**
   * Get removeResources
   * @return removeResources
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ResourceDefinition> getRemoveResources() {
    return removeResources;
  }

  public void setRemoveResources(List<ResourceDefinition> removeResources) {
    this.removeResources = removeResources;
  }

  public GrantRequest updateResources(List<ResourceDefinition> updateResources) {
    this.updateResources = updateResources;
    return this;
  }

  public GrantRequest addUpdateResourcesItem(ResourceDefinition updateResourcesItem) {
    if (this.updateResources == null) {
      this.updateResources = new ArrayList<>();
    }
    this.updateResources.add(updateResourcesItem);
    return this;
  }

  /**
   * Get updateResources
   * @return updateResources
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<ResourceDefinition> getUpdateResources() {
    return updateResources;
  }

  public void setUpdateResources(List<ResourceDefinition> updateResources) {
    this.updateResources = updateResources;
  }

  public GrantRequest additionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
    return this;
  }

  /**
   * Get additionalParams
   * @return additionalParams
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public KeyValuePairs getAdditionalParams() {
    return additionalParams;
  }

  public void setAdditionalParams(KeyValuePairs additionalParams) {
    this.additionalParams = additionalParams;
  }

  public GrantRequest _links(GrantRequestLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public GrantRequestLinks getLinks() {
    return _links;
  }

  public void setLinks(GrantRequestLinks _links) {
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
    GrantRequest grantRequest = (GrantRequest) o;
    return Objects.equals(this.appInstanceId, grantRequest.appInstanceId) &&
        Objects.equals(this.appLcmOpOccId, grantRequest.appLcmOpOccId) &&
        Objects.equals(this.appDId, grantRequest.appDId) &&
        Objects.equals(this.operation, grantRequest.operation) &&
        Objects.equals(this.addResources, grantRequest.addResources) &&
        Objects.equals(this.tempResources, grantRequest.tempResources) &&
        Objects.equals(this.removeResources, grantRequest.removeResources) &&
        Objects.equals(this.updateResources, grantRequest.updateResources) &&
        Objects.equals(this.additionalParams, grantRequest.additionalParams) &&
        Objects.equals(this._links, grantRequest._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appInstanceId, appLcmOpOccId, appDId, operation, addResources, tempResources, removeResources, updateResources, additionalParams, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantRequest {\n");
    
    sb.append("    appInstanceId: ").append(toIndentedString(appInstanceId)).append("\n");
    sb.append("    appLcmOpOccId: ").append(toIndentedString(appLcmOpOccId)).append("\n");
    sb.append("    appDId: ").append(toIndentedString(appDId)).append("\n");
    sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
    sb.append("    addResources: ").append(toIndentedString(addResources)).append("\n");
    sb.append("    tempResources: ").append(toIndentedString(tempResources)).append("\n");
    sb.append("    removeResources: ").append(toIndentedString(removeResources)).append("\n");
    sb.append("    updateResources: ").append(toIndentedString(updateResources)).append("\n");
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
