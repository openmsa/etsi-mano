package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanopm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents subscription filter criteria to match NFV-MANO functional  entities and their associated managed objects.  
 */
@Schema(description = "This type represents subscription filter criteria to match NFV-MANO functional  entities and their associated managed objects.  ")
@Validated


public class ManoEntitySubscriptionFilter   {
  @JsonProperty("manoEntityId")
  private String manoEntityId = null;

  @JsonProperty("manoServiceIds")
  @Valid
  private List<String> manoServiceIds = null;

  @JsonProperty("manoServiceNames")
  @Valid
  private List<String> manoServiceNames = null;

  @JsonProperty("manoServiceInterfaceIds")
  @Valid
  private List<String> manoServiceInterfaceIds = null;

  @JsonProperty("manoServiceInterfaceNames")
  @Valid
  private List<String> manoServiceInterfaceNames = null;

  @JsonProperty("consumedManoInterfaceIds")
  @Valid
  private List<String> consumedManoInterfaceIds = null;

  @JsonProperty("consumedManoInterfaceNames")
  @Valid
  private List<String> consumedManoInterfaceNames = null;

  public ManoEntitySubscriptionFilter manoEntityId(String manoEntityId) {
    this.manoEntityId = manoEntityId;
    return this;
  }

  /**
   * Get manoEntityId
   * @return manoEntityId
   **/
  @Schema(description = "")
  
    public String getManoEntityId() {
    return manoEntityId;
  }

  public void setManoEntityId(String manoEntityId) {
    this.manoEntityId = manoEntityId;
  }

  public ManoEntitySubscriptionFilter manoServiceIds(List<String> manoServiceIds) {
    this.manoServiceIds = manoServiceIds;
    return this;
  }

  public ManoEntitySubscriptionFilter addManoServiceIdsItem(String manoServiceIdsItem) {
    if (this.manoServiceIds == null) {
      this.manoServiceIds = new ArrayList<>();
    }
    this.manoServiceIds.add(manoServiceIdsItem);
    return this;
  }

  /**
   * manoServiceIds IdentifierInManoEntity 0..N If present, match NFV-MANO  services with an instance identifier listed in this attribute. The attributes \"manoServiceIds\" and \"manoServiceNames\" are alternatives  to reference to NFV-MANO services in a filter. They should not be used  together in the same filter instance, but one alternative should be chosen. 
   * @return manoServiceIds
   **/
  @Schema(description = "manoServiceIds IdentifierInManoEntity 0..N If present, match NFV-MANO  services with an instance identifier listed in this attribute. The attributes \"manoServiceIds\" and \"manoServiceNames\" are alternatives  to reference to NFV-MANO services in a filter. They should not be used  together in the same filter instance, but one alternative should be chosen. ")
  
    public List<String> getManoServiceIds() {
    return manoServiceIds;
  }

  public void setManoServiceIds(List<String> manoServiceIds) {
    this.manoServiceIds = manoServiceIds;
  }

  public ManoEntitySubscriptionFilter manoServiceNames(List<String> manoServiceNames) {
    this.manoServiceNames = manoServiceNames;
    return this;
  }

  public ManoEntitySubscriptionFilter addManoServiceNamesItem(String manoServiceNamesItem) {
    if (this.manoServiceNames == null) {
      this.manoServiceNames = new ArrayList<>();
    }
    this.manoServiceNames.add(manoServiceNamesItem);
    return this;
  }

  /**
   * If present, match NFV-MANO services with an NFV-MANO service name listed  in this attribute. The attributes \"manoServiceIds\" and \"manoServiceNames\" are alternatives  to reference to NFV-MANO services in a filter. They should not be used  together in the same filter instance, but one alternative should be chosen. 
   * @return manoServiceNames
   **/
  @Schema(description = "If present, match NFV-MANO services with an NFV-MANO service name listed  in this attribute. The attributes \"manoServiceIds\" and \"manoServiceNames\" are alternatives  to reference to NFV-MANO services in a filter. They should not be used  together in the same filter instance, but one alternative should be chosen. ")
  
    public List<String> getManoServiceNames() {
    return manoServiceNames;
  }

  public void setManoServiceNames(List<String> manoServiceNames) {
    this.manoServiceNames = manoServiceNames;
  }

  public ManoEntitySubscriptionFilter manoServiceInterfaceIds(List<String> manoServiceInterfaceIds) {
    this.manoServiceInterfaceIds = manoServiceInterfaceIds;
    return this;
  }

  public ManoEntitySubscriptionFilter addManoServiceInterfaceIdsItem(String manoServiceInterfaceIdsItem) {
    if (this.manoServiceInterfaceIds == null) {
      this.manoServiceInterfaceIds = new ArrayList<>();
    }
    this.manoServiceInterfaceIds.add(manoServiceInterfaceIdsItem);
    return this;
  }

  /**
   * If present, match NFV-MANO functional entity produced interfaces with an  instance identifier listed in this attribute. The attributes \"manoServiceInterfaceIds\" and \"manoServiceInterfaceNames\"  are alternatives to reference to NFV-MANO functional entity produced  interfaces in a filter. They should not be used both in the same filter  instance, but one alternative should be chosen. 
   * @return manoServiceInterfaceIds
   **/
  @Schema(description = "If present, match NFV-MANO functional entity produced interfaces with an  instance identifier listed in this attribute. The attributes \"manoServiceInterfaceIds\" and \"manoServiceInterfaceNames\"  are alternatives to reference to NFV-MANO functional entity produced  interfaces in a filter. They should not be used both in the same filter  instance, but one alternative should be chosen. ")
  
    public List<String> getManoServiceInterfaceIds() {
    return manoServiceInterfaceIds;
  }

  public void setManoServiceInterfaceIds(List<String> manoServiceInterfaceIds) {
    this.manoServiceInterfaceIds = manoServiceInterfaceIds;
  }

  public ManoEntitySubscriptionFilter manoServiceInterfaceNames(List<String> manoServiceInterfaceNames) {
    this.manoServiceInterfaceNames = manoServiceInterfaceNames;
    return this;
  }

  public ManoEntitySubscriptionFilter addManoServiceInterfaceNamesItem(String manoServiceInterfaceNamesItem) {
    if (this.manoServiceInterfaceNames == null) {
      this.manoServiceInterfaceNames = new ArrayList<>();
    }
    this.manoServiceInterfaceNames.add(manoServiceInterfaceNamesItem);
    return this;
  }

  /**
   * If present, match NFV-MANO functional entity produced interfaces with an  instance Name listed in this attribute. The attributes \"manoServiceInterfaceIds\" and \"manoServiceInterfaceNames\"  are alternatives to reference to NFV-MANO functional entity produced  interfaces in a filter. They should not be used both in the same filter  instance, but one alternative should be chosen. 
   * @return manoServiceInterfaceNames
   **/
  @Schema(description = "If present, match NFV-MANO functional entity produced interfaces with an  instance Name listed in this attribute. The attributes \"manoServiceInterfaceIds\" and \"manoServiceInterfaceNames\"  are alternatives to reference to NFV-MANO functional entity produced  interfaces in a filter. They should not be used both in the same filter  instance, but one alternative should be chosen. ")
  
    public List<String> getManoServiceInterfaceNames() {
    return manoServiceInterfaceNames;
  }

  public void setManoServiceInterfaceNames(List<String> manoServiceInterfaceNames) {
    this.manoServiceInterfaceNames = manoServiceInterfaceNames;
  }

  public ManoEntitySubscriptionFilter consumedManoInterfaceIds(List<String> consumedManoInterfaceIds) {
    this.consumedManoInterfaceIds = consumedManoInterfaceIds;
    return this;
  }

  public ManoEntitySubscriptionFilter addConsumedManoInterfaceIdsItem(String consumedManoInterfaceIdsItem) {
    if (this.consumedManoInterfaceIds == null) {
      this.consumedManoInterfaceIds = new ArrayList<>();
    }
    this.consumedManoInterfaceIds.add(consumedManoInterfaceIdsItem);
    return this;
  }

  /**
   * If present, match NFV-MANO functional entity consumed interfaces with an  instance identifier listed in this attribute. The attributes \"consumedManoInterfaceIds\" and \"consumedManoInterfaceNames\"  are alternatives to reference to NFV-MANO functional entity consumed  interfaces in a filter. They should not be used both in the same filter  instance, but one alternative should be chosen. 
   * @return consumedManoInterfaceIds
   **/
  @Schema(description = "If present, match NFV-MANO functional entity consumed interfaces with an  instance identifier listed in this attribute. The attributes \"consumedManoInterfaceIds\" and \"consumedManoInterfaceNames\"  are alternatives to reference to NFV-MANO functional entity consumed  interfaces in a filter. They should not be used both in the same filter  instance, but one alternative should be chosen. ")
  
    public List<String> getConsumedManoInterfaceIds() {
    return consumedManoInterfaceIds;
  }

  public void setConsumedManoInterfaceIds(List<String> consumedManoInterfaceIds) {
    this.consumedManoInterfaceIds = consumedManoInterfaceIds;
  }

  public ManoEntitySubscriptionFilter consumedManoInterfaceNames(List<String> consumedManoInterfaceNames) {
    this.consumedManoInterfaceNames = consumedManoInterfaceNames;
    return this;
  }

  public ManoEntitySubscriptionFilter addConsumedManoInterfaceNamesItem(String consumedManoInterfaceNamesItem) {
    if (this.consumedManoInterfaceNames == null) {
      this.consumedManoInterfaceNames = new ArrayList<>();
    }
    this.consumedManoInterfaceNames.add(consumedManoInterfaceNamesItem);
    return this;
  }

  /**
   * If present, match NFV-MANO functional entity consumed interfaces with an  instance Name listed in this attribute. The attributes \"consumedManoInterfaceIds\" and \"consumedManoInterfaceNames\"  are alternatives to reference to NFV-MANO functional entity consumed  interfaces in a filter. They should not be used both in the same filter  instance, but one alternative should be chosen. 
   * @return consumedManoInterfaceNames
   **/
  @Schema(description = "If present, match NFV-MANO functional entity consumed interfaces with an  instance Name listed in this attribute. The attributes \"consumedManoInterfaceIds\" and \"consumedManoInterfaceNames\"  are alternatives to reference to NFV-MANO functional entity consumed  interfaces in a filter. They should not be used both in the same filter  instance, but one alternative should be chosen. ")
  
    public List<String> getConsumedManoInterfaceNames() {
    return consumedManoInterfaceNames;
  }

  public void setConsumedManoInterfaceNames(List<String> consumedManoInterfaceNames) {
    this.consumedManoInterfaceNames = consumedManoInterfaceNames;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManoEntitySubscriptionFilter manoEntitySubscriptionFilter = (ManoEntitySubscriptionFilter) o;
    return Objects.equals(this.manoEntityId, manoEntitySubscriptionFilter.manoEntityId) &&
        Objects.equals(this.manoServiceIds, manoEntitySubscriptionFilter.manoServiceIds) &&
        Objects.equals(this.manoServiceNames, manoEntitySubscriptionFilter.manoServiceNames) &&
        Objects.equals(this.manoServiceInterfaceIds, manoEntitySubscriptionFilter.manoServiceInterfaceIds) &&
        Objects.equals(this.manoServiceInterfaceNames, manoEntitySubscriptionFilter.manoServiceInterfaceNames) &&
        Objects.equals(this.consumedManoInterfaceIds, manoEntitySubscriptionFilter.consumedManoInterfaceIds) &&
        Objects.equals(this.consumedManoInterfaceNames, manoEntitySubscriptionFilter.consumedManoInterfaceNames);
  }

  @Override
  public int hashCode() {
    return Objects.hash(manoEntityId, manoServiceIds, manoServiceNames, manoServiceInterfaceIds, manoServiceInterfaceNames, consumedManoInterfaceIds, consumedManoInterfaceNames);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoEntitySubscriptionFilter {\n");
    
    sb.append("    manoEntityId: ").append(toIndentedString(manoEntityId)).append("\n");
    sb.append("    manoServiceIds: ").append(toIndentedString(manoServiceIds)).append("\n");
    sb.append("    manoServiceNames: ").append(toIndentedString(manoServiceNames)).append("\n");
    sb.append("    manoServiceInterfaceIds: ").append(toIndentedString(manoServiceInterfaceIds)).append("\n");
    sb.append("    manoServiceInterfaceNames: ").append(toIndentedString(manoServiceInterfaceNames)).append("\n");
    sb.append("    consumedManoInterfaceIds: ").append(toIndentedString(consumedManoInterfaceIds)).append("\n");
    sb.append("    consumedManoInterfaceNames: ").append(toIndentedString(consumedManoInterfaceNames)).append("\n");
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
