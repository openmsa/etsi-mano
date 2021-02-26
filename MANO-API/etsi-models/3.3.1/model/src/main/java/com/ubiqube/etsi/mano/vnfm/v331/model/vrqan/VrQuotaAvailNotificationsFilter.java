package com.ubiqube.etsi.mano.vnfm.v331.model.vrqan;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a subscription filter related to notifications about the availability of the virtualised resources quotas. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). 
 */
@Schema(description = "This type represents a subscription filter related to notifications about the availability of the virtualised resources quotas. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \"and\" between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \"or\" between the values of one filter attribute). ")
@Validated


public class VrQuotaAvailNotificationsFilter   {
  @JsonProperty("vimIds")
  @Valid
  private List<String> vimIds = null;

  @JsonProperty("resourceProviderIds")
  @Valid
  private List<String> resourceProviderIds = null;

  /**
   * Gets or Sets resourceTypes
   */
  public enum ResourceTypesEnum {
    COMPUTE("COMPUTE"),
    
    STORAGE("STORAGE"),
    
    NETWORK("NETWORK");

    private String value;

    ResourceTypesEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ResourceTypesEnum fromValue(String text) {
      for (ResourceTypesEnum b : ResourceTypesEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("resourceTypes")
  @Valid
  private List<ResourceTypesEnum> resourceTypes = null;

  @JsonProperty("resourceGroupIds")
  @Valid
  private List<String> resourceGroupIds = null;

  public VrQuotaAvailNotificationsFilter vimIds(List<String> vimIds) {
    this.vimIds = vimIds;
    return this;
  }

  public VrQuotaAvailNotificationsFilter addVimIdsItem(String vimIdsItem) {
    if (this.vimIds == null) {
      this.vimIds = new ArrayList<>();
    }
    this.vimIds.add(vimIdsItem);
    return this;
  }

  /**
   * Match VIMs that were created the quota for a consumer of the virtualised resources. This attribute shall only be supported when VNF-related Resource Management in direct mode is applicable. 
   * @return vimIds
   **/
  @Schema(description = "Match VIMs that were created the quota for a consumer of the virtualised resources. This attribute shall only be supported when VNF-related Resource Management in direct mode is applicable. ")
  
    public List<String> getVimIds() {
    return vimIds;
  }

  public void setVimIds(List<String> vimIds) {
    this.vimIds = vimIds;
  }

  public VrQuotaAvailNotificationsFilter resourceProviderIds(List<String> resourceProviderIds) {
    this.resourceProviderIds = resourceProviderIds;
    return this;
  }

  public VrQuotaAvailNotificationsFilter addResourceProviderIdsItem(String resourceProviderIdsItem) {
    if (this.resourceProviderIds == null) {
      this.resourceProviderIds = new ArrayList<>();
    }
    this.resourceProviderIds.add(resourceProviderIdsItem);
    return this;
  }

  /**
   * Match the entities responsible for the management of the virtualised resources that were allocated by the NFVO. This attribute shall only be supported when VNF-related Resource Management in indirect mode is applicable. The identification scheme is outside the scope of the present document. 
   * @return resourceProviderIds
   **/
  @Schema(description = "Match the entities responsible for the management of the virtualised resources that were allocated by the NFVO. This attribute shall only be supported when VNF-related Resource Management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")
  
    public List<String> getResourceProviderIds() {
    return resourceProviderIds;
  }

  public void setResourceProviderIds(List<String> resourceProviderIds) {
    this.resourceProviderIds = resourceProviderIds;
  }

  public VrQuotaAvailNotificationsFilter resourceTypes(List<ResourceTypesEnum> resourceTypes) {
    this.resourceTypes = resourceTypes;
    return this;
  }

  public VrQuotaAvailNotificationsFilter addResourceTypesItem(ResourceTypesEnum resourceTypesItem) {
    if (this.resourceTypes == null) {
      this.resourceTypes = new ArrayList<>();
    }
    this.resourceTypes.add(resourceTypesItem);
    return this;
  }

  /**
   * Match particular resource types. 
   * @return resourceTypes
   **/
  @Schema(description = "Match particular resource types. ")
  
    public List<ResourceTypesEnum> getResourceTypes() {
    return resourceTypes;
  }

  public void setResourceTypes(List<ResourceTypesEnum> resourceTypes) {
    this.resourceTypes = resourceTypes;
  }

  public VrQuotaAvailNotificationsFilter resourceGroupIds(List<String> resourceGroupIds) {
    this.resourceGroupIds = resourceGroupIds;
    return this;
  }

  public VrQuotaAvailNotificationsFilter addResourceGroupIdsItem(String resourceGroupIdsItem) {
    if (this.resourceGroupIds == null) {
      this.resourceGroupIds = new ArrayList<>();
    }
    this.resourceGroupIds.add(resourceGroupIdsItem);
    return this;
  }

  /**
   * Match the \"infrastructure resource groups\" that are logical groupings of the virtualised resources assigned to a tenant within an infrastructure Domain. 
   * @return resourceGroupIds
   **/
  @Schema(description = "Match the \"infrastructure resource groups\" that are logical groupings of the virtualised resources assigned to a tenant within an infrastructure Domain. ")
  
    public List<String> getResourceGroupIds() {
    return resourceGroupIds;
  }

  public void setResourceGroupIds(List<String> resourceGroupIds) {
    this.resourceGroupIds = resourceGroupIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VrQuotaAvailNotificationsFilter vrQuotaAvailNotificationsFilter = (VrQuotaAvailNotificationsFilter) o;
    return Objects.equals(this.vimIds, vrQuotaAvailNotificationsFilter.vimIds) &&
        Objects.equals(this.resourceProviderIds, vrQuotaAvailNotificationsFilter.resourceProviderIds) &&
        Objects.equals(this.resourceTypes, vrQuotaAvailNotificationsFilter.resourceTypes) &&
        Objects.equals(this.resourceGroupIds, vrQuotaAvailNotificationsFilter.resourceGroupIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vimIds, resourceProviderIds, resourceTypes, resourceGroupIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VrQuotaAvailNotificationsFilter {\n");
    
    sb.append("    vimIds: ").append(toIndentedString(vimIds)).append("\n");
    sb.append("    resourceProviderIds: ").append(toIndentedString(resourceProviderIds)).append("\n");
    sb.append("    resourceTypes: ").append(toIndentedString(resourceTypes)).append("\n");
    sb.append("    resourceGroupIds: ").append(toIndentedString(resourceGroupIds)).append("\n");
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
