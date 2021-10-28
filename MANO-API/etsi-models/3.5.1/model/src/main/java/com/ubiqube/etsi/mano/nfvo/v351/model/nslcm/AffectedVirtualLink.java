package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

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
 * This type provides information about added, deleted, modified and temporary VLs. NOTE: The resource handles of the affected NS link ports can be found by dereferencing the identifiers in the \&quot;linkPortIds\&quot; attribute. 
 */
@Schema(description = "This type provides information about added, deleted, modified and temporary VLs. NOTE: The resource handles of the affected NS link ports can be found by dereferencing the identifiers in the \"linkPortIds\" attribute. ")
@Validated


public class AffectedVirtualLink   {
  @JsonProperty("nsVirtualLinkInstanceId")
  private String nsVirtualLinkInstanceId = null;

  @JsonProperty("nsVirtualLinkDescId")
  private String nsVirtualLinkDescId = null;

  @JsonProperty("vlProfileId")
  private String vlProfileId = null;

  /**
   * Signals the type of change. Permitted values: * ADD * DELETE * MODIFY * ADD_LINK_PORT * REMOVE_LINK_PORT 
   */
  public enum ChangeTypeEnum {
    ADD("ADD"),
    
    DELETE("DELETE"),
    
    MODIFY("MODIFY"),
    
    ADD_LINK_PORT("ADD_LINK_PORT"),
    
    REMOVE_LINK_PORT("REMOVE_LINK_PORT");

    private String value;

    ChangeTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ChangeTypeEnum fromValue(String text) {
      for (ChangeTypeEnum b : ChangeTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("changeType")
  private ChangeTypeEnum changeType = null;

  @JsonProperty("linkPortIds")
  @Valid
  private List<String> linkPortIds = null;

  /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: * COMPLETED * ROLLED_BACK * FAILED 
   */
  public enum ChangeResultEnum {
    COMPLETED("COMPLETED"),
    
    ROLLED_BACK("ROLLED_BACK"),
    
    FAILED("FAILED");

    private String value;

    ChangeResultEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ChangeResultEnum fromValue(String text) {
      for (ChangeResultEnum b : ChangeResultEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("changeResult")
  private ChangeResultEnum changeResult = null;

  public AffectedVirtualLink nsVirtualLinkInstanceId(String nsVirtualLinkInstanceId) {
    this.nsVirtualLinkInstanceId = nsVirtualLinkInstanceId;
    return this;
  }

  /**
   * Get nsVirtualLinkInstanceId
   * @return nsVirtualLinkInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsVirtualLinkInstanceId() {
    return nsVirtualLinkInstanceId;
  }

  public void setNsVirtualLinkInstanceId(String nsVirtualLinkInstanceId) {
    this.nsVirtualLinkInstanceId = nsVirtualLinkInstanceId;
  }

  public AffectedVirtualLink nsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
    return this;
  }

  /**
   * Get nsVirtualLinkDescId
   * @return nsVirtualLinkDescId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNsVirtualLinkDescId() {
    return nsVirtualLinkDescId;
  }

  public void setNsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
  }

  public AffectedVirtualLink vlProfileId(String vlProfileId) {
    this.vlProfileId = vlProfileId;
    return this;
  }

  /**
   * Get vlProfileId
   * @return vlProfileId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVlProfileId() {
    return vlProfileId;
  }

  public void setVlProfileId(String vlProfileId) {
    this.vlProfileId = vlProfileId;
  }

  public AffectedVirtualLink changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: * ADD * DELETE * MODIFY * ADD_LINK_PORT * REMOVE_LINK_PORT 
   * @return changeType
   **/
  @Schema(required = true, description = "Signals the type of change. Permitted values: * ADD * DELETE * MODIFY * ADD_LINK_PORT * REMOVE_LINK_PORT ")
      @NotNull

    public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedVirtualLink linkPortIds(List<String> linkPortIds) {
    this.linkPortIds = linkPortIds;
    return this;
  }

  public AffectedVirtualLink addLinkPortIdsItem(String linkPortIdsItem) {
    if (this.linkPortIds == null) {
      this.linkPortIds = new ArrayList<>();
    }
    this.linkPortIds.add(linkPortIdsItem);
    return this;
  }

  /**
   * Identifiers of the link ports of the affected VL related to the change. Each identifier references an \"NsLinkPortInfo\" structure. Shall be set when changeType is equal to \"ADD_LINK_PORT\" or \"REMOVE_LINK_PORT\", and the related \"NsLinkPortInfo\" structures are present (case \"add\") or have been present (case \"remove\") in the \"NsVirtualLinkInfo\" structure that is represented by the \"virtualLink¬Info\" attribute in the \"NsInstance\" structure. See note. 
   * @return linkPortIds
   **/
  @Schema(description = "Identifiers of the link ports of the affected VL related to the change. Each identifier references an \"NsLinkPortInfo\" structure. Shall be set when changeType is equal to \"ADD_LINK_PORT\" or \"REMOVE_LINK_PORT\", and the related \"NsLinkPortInfo\" structures are present (case \"add\") or have been present (case \"remove\") in the \"NsVirtualLinkInfo\" structure that is represented by the \"virtualLink¬Info\" attribute in the \"NsInstance\" structure. See note. ")
  
    public List<String> getLinkPortIds() {
    return linkPortIds;
  }

  public void setLinkPortIds(List<String> linkPortIds) {
    this.linkPortIds = linkPortIds;
  }

  public AffectedVirtualLink changeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
    return this;
  }

  /**
   * Signals the result of change identified by the \"changeType\" attribute. Permitted values: * COMPLETED * ROLLED_BACK * FAILED 
   * @return changeResult
   **/
  @Schema(required = true, description = "Signals the result of change identified by the \"changeType\" attribute. Permitted values: * COMPLETED * ROLLED_BACK * FAILED ")
      @NotNull

    public ChangeResultEnum getChangeResult() {
    return changeResult;
  }

  public void setChangeResult(ChangeResultEnum changeResult) {
    this.changeResult = changeResult;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectedVirtualLink affectedVirtualLink = (AffectedVirtualLink) o;
    return Objects.equals(this.nsVirtualLinkInstanceId, affectedVirtualLink.nsVirtualLinkInstanceId) &&
        Objects.equals(this.nsVirtualLinkDescId, affectedVirtualLink.nsVirtualLinkDescId) &&
        Objects.equals(this.vlProfileId, affectedVirtualLink.vlProfileId) &&
        Objects.equals(this.changeType, affectedVirtualLink.changeType) &&
        Objects.equals(this.linkPortIds, affectedVirtualLink.linkPortIds) &&
        Objects.equals(this.changeResult, affectedVirtualLink.changeResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsVirtualLinkInstanceId, nsVirtualLinkDescId, vlProfileId, changeType, linkPortIds, changeResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVirtualLink {\n");
    
    sb.append("    nsVirtualLinkInstanceId: ").append(toIndentedString(nsVirtualLinkInstanceId)).append("\n");
    sb.append("    nsVirtualLinkDescId: ").append(toIndentedString(nsVirtualLinkDescId)).append("\n");
    sb.append("    vlProfileId: ").append(toIndentedString(vlProfileId)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    linkPortIds: ").append(toIndentedString(linkPortIds)).append("\n");
    sb.append("    changeResult: ").append(toIndentedString(changeResult)).append("\n");
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
