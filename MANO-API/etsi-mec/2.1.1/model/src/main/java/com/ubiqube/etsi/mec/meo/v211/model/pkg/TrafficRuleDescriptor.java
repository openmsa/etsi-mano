package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.Action;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.FilterType;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.InterfaceDescriptor;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.TrafficFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TrafficRuleDescriptor
 */
@Validated
public class TrafficRuleDescriptor   {
  @JsonProperty("action")
  private Action action = null;

  @JsonProperty("dstInterface")
  @Valid
  private List<InterfaceDescriptor> dstInterface = null;

  @JsonProperty("filterType")
  private FilterType filterType = null;

  @JsonProperty("priority")
  private Integer priority = null;

  @JsonProperty("trafficFilter")
  @Valid
  private List<TrafficFilter> trafficFilter = new ArrayList<>();

  @JsonProperty("trafficRuleId")
  private String trafficRuleId = null;

  public TrafficRuleDescriptor action(Action action) {
    this.action = action;
    return this;
  }

  /**
   * Get action
   * @return action
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Action getAction() {
    return action;
  }

  public void setAction(Action action) {
    this.action = action;
  }

  public TrafficRuleDescriptor dstInterface(List<InterfaceDescriptor> dstInterface) {
    this.dstInterface = dstInterface;
    return this;
  }

  public TrafficRuleDescriptor addDstInterfaceItem(InterfaceDescriptor dstInterfaceItem) {
    if (this.dstInterface == null) {
      this.dstInterface = new ArrayList<>();
    }
    this.dstInterface.add(dstInterfaceItem);
    return this;
  }

  /**
   * Get dstInterface
   * @return dstInterface
  **/
  @ApiModelProperty(value = "")
      @Valid
  @Size(max=2)   public List<InterfaceDescriptor> getDstInterface() {
    return dstInterface;
  }

  public void setDstInterface(List<InterfaceDescriptor> dstInterface) {
    this.dstInterface = dstInterface;
  }

  public TrafficRuleDescriptor filterType(FilterType filterType) {
    this.filterType = filterType;
    return this;
  }

  /**
   * Get filterType
   * @return filterType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public FilterType getFilterType() {
    return filterType;
  }

  public void setFilterType(FilterType filterType) {
    this.filterType = filterType;
  }

  public TrafficRuleDescriptor priority(Integer priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Priority of this traffic rule. If traffic rule conflicts, the one with higher priority take precedence.
   * @return priority
  **/
  @ApiModelProperty(required = true, value = "Priority of this traffic rule. If traffic rule conflicts, the one with higher priority take precedence.")
      @NotNull

    public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public TrafficRuleDescriptor trafficFilter(List<TrafficFilter> trafficFilter) {
    this.trafficFilter = trafficFilter;
    return this;
  }

  public TrafficRuleDescriptor addTrafficFilterItem(TrafficFilter trafficFilterItem) {
    this.trafficFilter.add(trafficFilterItem);
    return this;
  }

  /**
   * The filter used to identify specific flow/packets that need to be handled by the MEC host.
   * @return trafficFilter
  **/
  @ApiModelProperty(required = true, value = "The filter used to identify specific flow/packets that need to be handled by the MEC host.")
      @NotNull
    @Valid
  @Size(min=1)   public List<TrafficFilter> getTrafficFilter() {
    return trafficFilter;
  }

  public void setTrafficFilter(List<TrafficFilter> trafficFilter) {
    this.trafficFilter = trafficFilter;
  }

  public TrafficRuleDescriptor trafficRuleId(String trafficRuleId) {
    this.trafficRuleId = trafficRuleId;
    return this;
  }

  /**
   * Identifies the traffic rule.
   * @return trafficRuleId
  **/
  @ApiModelProperty(required = true, value = "Identifies the traffic rule.")
      @NotNull

    public String getTrafficRuleId() {
    return trafficRuleId;
  }

  public void setTrafficRuleId(String trafficRuleId) {
    this.trafficRuleId = trafficRuleId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrafficRuleDescriptor trafficRuleDescriptor = (TrafficRuleDescriptor) o;
    return Objects.equals(this.action, trafficRuleDescriptor.action) &&
        Objects.equals(this.dstInterface, trafficRuleDescriptor.dstInterface) &&
        Objects.equals(this.filterType, trafficRuleDescriptor.filterType) &&
        Objects.equals(this.priority, trafficRuleDescriptor.priority) &&
        Objects.equals(this.trafficFilter, trafficRuleDescriptor.trafficFilter) &&
        Objects.equals(this.trafficRuleId, trafficRuleDescriptor.trafficRuleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(action, dstInterface, filterType, priority, trafficFilter, trafficRuleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TrafficRuleDescriptor {\n");
    
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    dstInterface: ").append(toIndentedString(dstInterface)).append("\n");
    sb.append("    filterType: ").append(toIndentedString(filterType)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    trafficFilter: ").append(toIndentedString(trafficFilter)).append("\n");
    sb.append("    trafficRuleId: ").append(toIndentedString(trafficRuleId)).append("\n");
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
