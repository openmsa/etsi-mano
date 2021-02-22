package com.ubiqube.etsi.mano.em.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.CancelModeType;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ExtVirtualLinkInfo;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.LcmOperationType;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ModificationsTriggeredByVnfPkgChange;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ProblemDetails;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfInfoModifications;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfLcmOpOccLinks;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.VnfLcmOpOccResourceChanges;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a VNF lifecycle management operation occurrence. Shall be set to the value of the \&quot;id\&quot; attribute in the \&quot;Grant\&quot; representing the associated \&quot;Individual Grant\&quot;, if such grant exists. 
 */
@Schema(description = "This type represents a VNF lifecycle management operation occurrence. Shall be set to the value of the \"id\" attribute in the \"Grant\" representing the associated \"Individual Grant\", if such grant exists. ")
@Validated


public class VnfLcmOpOcc  implements OneOfVnfLcmOpOcc {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("operationState")
  private LcmOperationStateType operationState = null;

  @JsonProperty("stateEnteredTime")
  private OffsetDateTime stateEnteredTime = null;

  @JsonProperty("startTime")
  private OffsetDateTime startTime = null;

  @JsonProperty("vnfInstanceId")
  private String vnfInstanceId = null;

  @JsonProperty("grantId")
  private String grantId = null;

  @JsonProperty("operation")
  private LcmOperationType operation = null;

  @JsonProperty("isAutomaticInvocation")
  private Boolean isAutomaticInvocation = null;

  @JsonProperty("operationParams")
  private Object operationParams = null;

  @JsonProperty("isCancelPending")
  private Boolean isCancelPending = null;

  @JsonProperty("cancelMode")
  private CancelModeType cancelMode = null;

  @JsonProperty("error")
  private ProblemDetails error = null;

  @JsonProperty("resourceChanges")
  private VnfLcmOpOccResourceChanges resourceChanges = null;

  @JsonProperty("changedInfo")
  private VnfInfoModifications changedInfo = null;

  @JsonProperty("changedExtConnectivity")
  @Valid
  private List<ExtVirtualLinkInfo> changedExtConnectivity = null;

  @JsonProperty("modificationsTriggeredByVnfPkgChange")
  private ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange = null;

  @JsonProperty("vnfSnapshotInfoId")
  private String vnfSnapshotInfoId = null;

  @JsonProperty("_links")
  private VnfLcmOpOccLinks _links = null;

  public VnfLcmOpOcc id(String id) {
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

  public VnfLcmOpOcc operationState(LcmOperationStateType operationState) {
    this.operationState = operationState;
    return this;
  }

  /**
   * Get operationState
   * @return operationState
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LcmOperationStateType getOperationState() {
    return operationState;
  }

  public void setOperationState(LcmOperationStateType operationState) {
    this.operationState = operationState;
  }

  public VnfLcmOpOcc stateEnteredTime(OffsetDateTime stateEnteredTime) {
    this.stateEnteredTime = stateEnteredTime;
    return this;
  }

  /**
   * Get stateEnteredTime
   * @return stateEnteredTime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getStateEnteredTime() {
    return stateEnteredTime;
  }

  public void setStateEnteredTime(OffsetDateTime stateEnteredTime) {
    this.stateEnteredTime = stateEnteredTime;
  }

  public VnfLcmOpOcc startTime(OffsetDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }

  public VnfLcmOpOcc vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

  /**
   * Get vnfInstanceId
   * @return vnfInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public VnfLcmOpOcc grantId(String grantId) {
    this.grantId = grantId;
    return this;
  }

  /**
   * Get grantId
   * @return grantId
   **/
  @Schema(description = "")
  
    public String getGrantId() {
    return grantId;
  }

  public void setGrantId(String grantId) {
    this.grantId = grantId;
  }

  public VnfLcmOpOcc operation(LcmOperationType operation) {
    this.operation = operation;
    return this;
  }

  /**
   * Get operation
   * @return operation
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LcmOperationType getOperation() {
    return operation;
  }

  public void setOperation(LcmOperationType operation) {
    this.operation = operation;
  }

  public VnfLcmOpOcc isAutomaticInvocation(Boolean isAutomaticInvocation) {
    this.isAutomaticInvocation = isAutomaticInvocation;
    return this;
  }

  /**
   * Get isAutomaticInvocation
   * @return isAutomaticInvocation
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean getIsAutomaticInvocation() {
    return isAutomaticInvocation;
  }

  public void setIsAutomaticInvocation(Boolean isAutomaticInvocation) {
    this.isAutomaticInvocation = isAutomaticInvocation;
  }

  public VnfLcmOpOcc operationParams(Object operationParams) {
    this.operationParams = operationParams;
    return this;
  }

  /**
   * Input parameters of the LCM operation. This attribute shall be formatted according to the request data type of the related LCM operation. The following mapping between operationType and the data type of this attribute shall apply: * INSTANTIATE: InstantiateVnfRequest * SCALE: ScaleVnfRequest * SCALE_TO_LEVEL: ScaleVnfToLevelRequest * CHANGE_FLAVOUR: ChangeVnfFlavourRequest * OPERATE: OperateVnfRequest * HEAL: HealVnfRequest * CHANGE_EXT_CONN: ChangeExtVnfConnectivityRequest * TERMINATE: TerminateVnfRequest * MODIFY_INFO: VnfInfoModifications * CREATE_SNAPSHOT: CreateVnfSnapshotRequest * REVERT_TO_SNAPSHOT: RevertToVnfSnapshotRequest * CHANGE_VNFPKG: ChangeCurrentVnfPkgRequest 
   * @return operationParams
   **/
  @Schema(description = "Input parameters of the LCM operation. This attribute shall be formatted according to the request data type of the related LCM operation. The following mapping between operationType and the data type of this attribute shall apply: * INSTANTIATE: InstantiateVnfRequest * SCALE: ScaleVnfRequest * SCALE_TO_LEVEL: ScaleVnfToLevelRequest * CHANGE_FLAVOUR: ChangeVnfFlavourRequest * OPERATE: OperateVnfRequest * HEAL: HealVnfRequest * CHANGE_EXT_CONN: ChangeExtVnfConnectivityRequest * TERMINATE: TerminateVnfRequest * MODIFY_INFO: VnfInfoModifications * CREATE_SNAPSHOT: CreateVnfSnapshotRequest * REVERT_TO_SNAPSHOT: RevertToVnfSnapshotRequest * CHANGE_VNFPKG: ChangeCurrentVnfPkgRequest ")
  
    public Object getOperationParams() {
    return operationParams;
  }

  public void setOperationParams(Object operationParams) {
    this.operationParams = operationParams;
  }

  public VnfLcmOpOcc isCancelPending(Boolean isCancelPending) {
    this.isCancelPending = isCancelPending;
    return this;
  }

  /**
   * Get isCancelPending
   * @return isCancelPending
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean getIsCancelPending() {
    return isCancelPending;
  }

  public void setIsCancelPending(Boolean isCancelPending) {
    this.isCancelPending = isCancelPending;
  }

  public VnfLcmOpOcc cancelMode(CancelModeType cancelMode) {
    this.cancelMode = cancelMode;
    return this;
  }

  /**
   * Get cancelMode
   * @return cancelMode
   **/
  @Schema(description = "")
  
    @Valid
    public CancelModeType getCancelMode() {
    return cancelMode;
  }

  public void setCancelMode(CancelModeType cancelMode) {
    this.cancelMode = cancelMode;
  }

  public VnfLcmOpOcc error(ProblemDetails error) {
    this.error = error;
    return this;
  }

  /**
   * Get error
   * @return error
   **/
  @Schema(description = "")
  
    @Valid
    public ProblemDetails getError() {
    return error;
  }

  public void setError(ProblemDetails error) {
    this.error = error;
  }

  public VnfLcmOpOcc resourceChanges(VnfLcmOpOccResourceChanges resourceChanges) {
    this.resourceChanges = resourceChanges;
    return this;
  }

  /**
   * Get resourceChanges
   * @return resourceChanges
   **/
  @Schema(description = "")
  
    @Valid
    public VnfLcmOpOccResourceChanges getResourceChanges() {
    return resourceChanges;
  }

  public void setResourceChanges(VnfLcmOpOccResourceChanges resourceChanges) {
    this.resourceChanges = resourceChanges;
  }

  public VnfLcmOpOcc changedInfo(VnfInfoModifications changedInfo) {
    this.changedInfo = changedInfo;
    return this;
  }

  /**
   * Get changedInfo
   * @return changedInfo
   **/
  @Schema(description = "")
  
    @Valid
    public VnfInfoModifications getChangedInfo() {
    return changedInfo;
  }

  public void setChangedInfo(VnfInfoModifications changedInfo) {
    this.changedInfo = changedInfo;
  }

  public VnfLcmOpOcc changedExtConnectivity(List<ExtVirtualLinkInfo> changedExtConnectivity) {
    this.changedExtConnectivity = changedExtConnectivity;
    return this;
  }

  public VnfLcmOpOcc addChangedExtConnectivityItem(ExtVirtualLinkInfo changedExtConnectivityItem) {
    if (this.changedExtConnectivity == null) {
      this.changedExtConnectivity = new ArrayList<>();
    }
    this.changedExtConnectivity.add(changedExtConnectivityItem);
    return this;
  }

  /**
   * Information about changed external connectivity, if applicable. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. 
   * @return changedExtConnectivity
   **/
  @Schema(description = "Information about changed external connectivity, if applicable. This allows the NFVO to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")
      @Valid
    public List<ExtVirtualLinkInfo> getChangedExtConnectivity() {
    return changedExtConnectivity;
  }

  public void setChangedExtConnectivity(List<ExtVirtualLinkInfo> changedExtConnectivity) {
    this.changedExtConnectivity = changedExtConnectivity;
  }

  public VnfLcmOpOcc modificationsTriggeredByVnfPkgChange(ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange) {
    this.modificationsTriggeredByVnfPkgChange = modificationsTriggeredByVnfPkgChange;
    return this;
  }

  /**
   * Get modificationsTriggeredByVnfPkgChange
   * @return modificationsTriggeredByVnfPkgChange
   **/
  @Schema(description = "")
  
    @Valid
    public ModificationsTriggeredByVnfPkgChange getModificationsTriggeredByVnfPkgChange() {
    return modificationsTriggeredByVnfPkgChange;
  }

  public void setModificationsTriggeredByVnfPkgChange(ModificationsTriggeredByVnfPkgChange modificationsTriggeredByVnfPkgChange) {
    this.modificationsTriggeredByVnfPkgChange = modificationsTriggeredByVnfPkgChange;
  }

  public VnfLcmOpOcc vnfSnapshotInfoId(String vnfSnapshotInfoId) {
    this.vnfSnapshotInfoId = vnfSnapshotInfoId;
    return this;
  }

  /**
   * Get vnfSnapshotInfoId
   * @return vnfSnapshotInfoId
   **/
  @Schema(description = "")
  
    public String getVnfSnapshotInfoId() {
    return vnfSnapshotInfoId;
  }

  public void setVnfSnapshotInfoId(String vnfSnapshotInfoId) {
    this.vnfSnapshotInfoId = vnfSnapshotInfoId;
  }

  public VnfLcmOpOcc _links(VnfLcmOpOccLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
   **/
  @Schema(description = "")
  
    @Valid
    public VnfLcmOpOccLinks getLinks() {
    return _links;
  }

  public void setLinks(VnfLcmOpOccLinks _links) {
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
    VnfLcmOpOcc vnfLcmOpOcc = (VnfLcmOpOcc) o;
    return Objects.equals(this.id, vnfLcmOpOcc.id) &&
        Objects.equals(this.operationState, vnfLcmOpOcc.operationState) &&
        Objects.equals(this.stateEnteredTime, vnfLcmOpOcc.stateEnteredTime) &&
        Objects.equals(this.startTime, vnfLcmOpOcc.startTime) &&
        Objects.equals(this.vnfInstanceId, vnfLcmOpOcc.vnfInstanceId) &&
        Objects.equals(this.grantId, vnfLcmOpOcc.grantId) &&
        Objects.equals(this.operation, vnfLcmOpOcc.operation) &&
        Objects.equals(this.isAutomaticInvocation, vnfLcmOpOcc.isAutomaticInvocation) &&
        Objects.equals(this.operationParams, vnfLcmOpOcc.operationParams) &&
        Objects.equals(this.isCancelPending, vnfLcmOpOcc.isCancelPending) &&
        Objects.equals(this.cancelMode, vnfLcmOpOcc.cancelMode) &&
        Objects.equals(this.error, vnfLcmOpOcc.error) &&
        Objects.equals(this.resourceChanges, vnfLcmOpOcc.resourceChanges) &&
        Objects.equals(this.changedInfo, vnfLcmOpOcc.changedInfo) &&
        Objects.equals(this.changedExtConnectivity, vnfLcmOpOcc.changedExtConnectivity) &&
        Objects.equals(this.modificationsTriggeredByVnfPkgChange, vnfLcmOpOcc.modificationsTriggeredByVnfPkgChange) &&
        Objects.equals(this.vnfSnapshotInfoId, vnfLcmOpOcc.vnfSnapshotInfoId) &&
        Objects.equals(this._links, vnfLcmOpOcc._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, operationState, stateEnteredTime, startTime, vnfInstanceId, grantId, operation, isAutomaticInvocation, operationParams, isCancelPending, cancelMode, error, resourceChanges, changedInfo, changedExtConnectivity, modificationsTriggeredByVnfPkgChange, vnfSnapshotInfoId, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfLcmOpOcc {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    operationState: ").append(toIndentedString(operationState)).append("\n");
    sb.append("    stateEnteredTime: ").append(toIndentedString(stateEnteredTime)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    grantId: ").append(toIndentedString(grantId)).append("\n");
    sb.append("    operation: ").append(toIndentedString(operation)).append("\n");
    sb.append("    isAutomaticInvocation: ").append(toIndentedString(isAutomaticInvocation)).append("\n");
    sb.append("    operationParams: ").append(toIndentedString(operationParams)).append("\n");
    sb.append("    isCancelPending: ").append(toIndentedString(isCancelPending)).append("\n");
    sb.append("    cancelMode: ").append(toIndentedString(cancelMode)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    resourceChanges: ").append(toIndentedString(resourceChanges)).append("\n");
    sb.append("    changedInfo: ").append(toIndentedString(changedInfo)).append("\n");
    sb.append("    changedExtConnectivity: ").append(toIndentedString(changedExtConnectivity)).append("\n");
    sb.append("    modificationsTriggeredByVnfPkgChange: ").append(toIndentedString(modificationsTriggeredByVnfPkgChange)).append("\n");
    sb.append("    vnfSnapshotInfoId: ").append(toIndentedString(vnfSnapshotInfoId)).append("\n");
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
