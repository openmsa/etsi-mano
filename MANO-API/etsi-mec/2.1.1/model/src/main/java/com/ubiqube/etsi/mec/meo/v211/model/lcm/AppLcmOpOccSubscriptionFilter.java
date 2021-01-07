package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceSubscriptionFilter;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.NotificationTypes;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.OperationStates;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.OperationTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AppLcmOpOccSubscriptionFilter
 */
@Validated
public class AppLcmOpOccSubscriptionFilter   {
  @JsonProperty("appInstanceSubscriptionFilter")
  private AppInstanceSubscriptionFilter appInstanceSubscriptionFilter = null;

  @JsonProperty("notificationTypes")
  private NotificationTypes notificationTypes = null;

  @JsonProperty("operationStates")
  private OperationStates operationStates = null;

  @JsonProperty("operationTypes")
  private OperationTypes operationTypes = null;

  public AppLcmOpOccSubscriptionFilter appInstanceSubscriptionFilter(AppInstanceSubscriptionFilter appInstanceSubscriptionFilter) {
    this.appInstanceSubscriptionFilter = appInstanceSubscriptionFilter;
    return this;
  }

  /**
   * Get appInstanceSubscriptionFilter
   * @return appInstanceSubscriptionFilter
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AppInstanceSubscriptionFilter getAppInstanceSubscriptionFilter() {
    return appInstanceSubscriptionFilter;
  }

  public void setAppInstanceSubscriptionFilter(AppInstanceSubscriptionFilter appInstanceSubscriptionFilter) {
    this.appInstanceSubscriptionFilter = appInstanceSubscriptionFilter;
  }

  public AppLcmOpOccSubscriptionFilter notificationTypes(NotificationTypes notificationTypes) {
    this.notificationTypes = notificationTypes;
    return this;
  }

  /**
   * Get notificationTypes
   * @return notificationTypes
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public NotificationTypes getNotificationTypes() {
    return notificationTypes;
  }

  public void setNotificationTypes(NotificationTypes notificationTypes) {
    this.notificationTypes = notificationTypes;
  }

  public AppLcmOpOccSubscriptionFilter operationStates(OperationStates operationStates) {
    this.operationStates = operationStates;
    return this;
  }

  /**
   * Get operationStates
   * @return operationStates
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public OperationStates getOperationStates() {
    return operationStates;
  }

  public void setOperationStates(OperationStates operationStates) {
    this.operationStates = operationStates;
  }

  public AppLcmOpOccSubscriptionFilter operationTypes(OperationTypes operationTypes) {
    this.operationTypes = operationTypes;
    return this;
  }

  /**
   * Get operationTypes
   * @return operationTypes
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public OperationTypes getOperationTypes() {
    return operationTypes;
  }

  public void setOperationTypes(OperationTypes operationTypes) {
    this.operationTypes = operationTypes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppLcmOpOccSubscriptionFilter appLcmOpOccSubscriptionFilter = (AppLcmOpOccSubscriptionFilter) o;
    return Objects.equals(this.appInstanceSubscriptionFilter, appLcmOpOccSubscriptionFilter.appInstanceSubscriptionFilter) &&
        Objects.equals(this.notificationTypes, appLcmOpOccSubscriptionFilter.notificationTypes) &&
        Objects.equals(this.operationStates, appLcmOpOccSubscriptionFilter.operationStates) &&
        Objects.equals(this.operationTypes, appLcmOpOccSubscriptionFilter.operationTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appInstanceSubscriptionFilter, notificationTypes, operationStates, operationTypes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppLcmOpOccSubscriptionFilter {\n");
    
    sb.append("    appInstanceSubscriptionFilter: ").append(toIndentedString(appInstanceSubscriptionFilter)).append("\n");
    sb.append("    notificationTypes: ").append(toIndentedString(notificationTypes)).append("\n");
    sb.append("    operationStates: ").append(toIndentedString(operationStates)).append("\n");
    sb.append("    operationTypes: ").append(toIndentedString(operationTypes)).append("\n");
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
